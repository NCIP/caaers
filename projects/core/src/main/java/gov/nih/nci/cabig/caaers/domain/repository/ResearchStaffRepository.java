package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.mail.MailException;


import java.util.ArrayList;
import java.util.List;

/**
 * This is the repository class for managing the research staff domain object.
 *
 * @author Biju Joseph
 * @author Jared Flatow
 */
@Transactional(readOnly = true)
public class ResearchStaffRepository {

    private CSMUserRepository csmUserRepository;

    private ResearchStaffDao researchStaffDao;


    private UserProvisioningManager userProvisioningManager;
    private String authenticationMode;

    private static final Log logger = LogFactory.getLog(ResearchStaffRepository.class);

    public List<ResearchStaff> getAll() {
        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        return researchStaffDao.searchResearchStaff(researchStaffQuery);
    }
    
    /**
     * Saves or update the research staff.
     *
     * @param researchStaff the research staff
     * @throws CaaersSystemException if research staff can not be created.
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    public void save(final ResearchStaff researchStaff, String changeURL) {
    	
    	boolean createMode = researchStaff.getId() == null;
    	boolean webSSOAuthentication = authenticationMode.equals("webSSO");
    	
    	if (researchStaff.getEmailAddress() == null) {
            throw new CaaersSystemException("Email address is required");
        }
    	if( webSSOAuthentication && StringUtils.isBlank(researchStaff.getLoginId())){
    		throw new CaaersSystemException("Login Id cannot be null in webSSO mode");
    	}
    	//update the loginId to email address if this is not webSSO mode
    	if(createMode && !webSSOAuthentication){
    		researchStaff.setLoginId(researchStaff.getEmailAddress());
    	}
    	
        csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(researchStaff, changeURL);
        researchStaffDao.save(researchStaff);
        
        
    }

    public ResearchStaff getById(final int id) {
        ResearchStaff researchStaff = researchStaffDao.getById(id);
        initialize(researchStaff);
        return researchStaff;
    }

    public List<ResearchStaff> searchResearchStaff(final ResearchStaffQuery query) {
        return researchStaffDao.searchResearchStaff(query);
    }

    @SuppressWarnings("unchecked")
    public ResearchStaff initialize(final ResearchStaff researchStaff) {
        try {
            gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(researchStaff.getLoginId());
            List<Group> groups = new ArrayList(userProvisioningManager.getGroups(String.valueOf(csmUser.getUserId())));
            for (Group group : groups) {
                UserGroupType userGroupType = UserGroupType.getByCode(Long.valueOf(
                        group.getGroupId()).intValue());
                if (userGroupType != null) {
                    researchStaff.addUserGroupType(userGroupType);
                }
            }
        } catch (CSObjectNotFoundException e) {
            throw new CaaersSystemException("Error while retriving research staff", e);
        }
        return researchStaff;
    }

    public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {
        return researchStaffDao.getBySubnames(subnames, site);
    }

    @Required
    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }


    @Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }


    @Required
    public void setUserProvisioningManager(final UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }
    

    @Required
    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }
}