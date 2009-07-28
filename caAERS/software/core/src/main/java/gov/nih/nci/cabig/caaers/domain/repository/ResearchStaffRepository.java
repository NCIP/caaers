package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.OrganizationConverterDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffConverterDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ConverterOrganization;
import gov.nih.nci.cabig.caaers.domain.ConverterResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    private ResearchStaffConverterDao researchStaffConverterDao;
    private OrganizationConverterDao organizationConverterDao;
    private OrganizationRepository organizationRepository;
    private OrganizationDao organizationDao;
    private UserProvisioningManager userProvisioningManager;
    private String authenticationMode;
    private static final Log logger = LogFactory.getLog(ResearchStaffRepository.class);
    private StudyRepository studyRepository;
    
    public List<ResearchStaff> getAll() {
        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        return getResearchStaff(researchStaffQuery);
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
    	
    	if( webSSOAuthentication && StringUtils.isBlank(researchStaff.getLoginId())){
    		throw new CaaersSystemException("Login Id cannot be null in webSSO mode");
    	}
    	//update the loginId to email address if this is not webSSO mode
    	if(createMode && !webSSOAuthentication && StringUtilities.isBlank(researchStaff.getLoginId())) {
    		researchStaff.setLoginId(researchStaff.getLoginId());
    	}
    	MailException mailException = null;
    	try{
            // this should be fixed... 
    		// csmUserRepository.createOrUpdateCSMUserAndGroupsForResearchStaff(researchStaff, changeURL);
    	}catch(MailException e){
    		mailException = e;
    	}
    	researchStaffDao.save(researchStaff);

		try{
			studyRepository.associateStudyPersonnel(researchStaff);
		}catch(Exception e){
			throw new CaaersSystemException("Failed to associte researchstaff to all studies");
		}
    	
        if(mailException != null) throw mailException;
        
    }
    
    public void evict(ResearchStaff researchStaff){
    	researchStaffDao.evict(researchStaff);
    }
    
    @Transactional(readOnly = false)
    public void convertToRemote(ResearchStaff localResearchStaff, ResearchStaff remoteResearchStaff){
    	ConverterResearchStaff conRStaff = researchStaffConverterDao.getById(localResearchStaff.getId());
    	conRStaff.setType("REMOTE");
    	conRStaff.setExternalId(remoteResearchStaff.getExternalId());
    	conRStaff.setFirstName(remoteResearchStaff.getFirstName());
    	conRStaff.setLastName(remoteResearchStaff.getLastName());
    	conRStaff.setMiddleName(remoteResearchStaff.getMiddleName());
    	conRStaff.setPhoneNumber(remoteResearchStaff.getPhoneNumber());
    	conRStaff.setFaxNumber(remoteResearchStaff.getFaxNumber());
    	//Commented below 2 lines for ResearchStaff changes. Organization info is captured in SiteResearchStaff 
    	//ConverterOrganization conOrg = organizationConverterDao.getById(localResearchStaff.getOrganization().getId());
    	//conRStaff.setConverterOrganization(conOrg);
    	researchStaffConverterDao.save(conRStaff);
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
/*
            for (Group group : groups) {
                UserGroupType userGroupType = UserGroupType.getByCode(Long.valueOf(group.getGroupId()).intValue());
                if (userGroupType != null) {
                    researchStaff.addUserGroupType(userGroupType);
                }
            }
*/
        } catch (CSObjectNotFoundException e) {
            throw new CaaersSystemException("Error while retriving research staff", e);
        }
        return researchStaff;
    }
    
    @Transactional(readOnly = false)
    public List<ResearchStaff> getBySubnames(final String[] subnames, final int site) {
    	List<ResearchStaff> researchStaffs = researchStaffDao.getBySubnames(subnames, site);
    	ResearchStaff searchCriteria = new RemoteResearchStaff();
    	Organization org = organizationDao.getById(site);
    	//Commented below line for ResearchStaff changes. Organization is captured in SiteResearchStaff
    	//searchCriteria.setOrganization(org);
    	List<ResearchStaff> remoteResearchStaffs = getRemoteResearchStaff(searchCriteria); 
    	
    	return merge (researchStaffs,remoteResearchStaffs);
    }
    
    @Transactional(readOnly = false)
    public List<ResearchStaff> getResearchStaff(final ResearchStaffQuery query){
    	//Get all the RS from caAERS DB
        List<ResearchStaff> researchStaffs = researchStaffDao.getLocalResearchStaff(query);
        //Get all the RS from External System
        ResearchStaff searchCriteria = new RemoteResearchStaff();
        Map<String, Object> queryParameterMap = query.getParameterMap();
        for (String key : queryParameterMap.keySet()) {
            Object value = queryParameterMap.get(key);
            if (key.equals("firstName")) {
				searchCriteria.setFirstName(value.toString());
			}
            if (key.equals("lastName")) {
				searchCriteria.setLastName(value.toString());
			}
			if (key.equals("organizationNciInstituteCode")) {
				Organization organization = new RemoteOrganization();
				organization.setNciInstituteCode(value.toString());
				//Commented below line for ResearchStaff changes. Organization is captured in SiteResearchStaff
				//searchCriteria.setOrganization(organization);
			}	
        }
    	List<ResearchStaff> remoteResearchStaffs = researchStaffDao.getRemoteResearchStaff(searchCriteria); 
    	//Merge and Return
    	return merge (researchStaffs,remoteResearchStaffs);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    private List<ResearchStaff> merge(List<ResearchStaff> localList , List<ResearchStaff> remoteList) {
		for (ResearchStaff remoteResearchStaff:remoteList) {
			ResearchStaff rs = researchStaffDao.getByEmailAddress(remoteResearchStaff.getEmailAddress());
    		if (rs == null ) {
    			
    			//Commented below line for ResearchStaff changes. Organization is captured in SiteResearchStaff
    			/*
    			// look for his organization ;
    			Organization remoteOrganization = remoteResearchStaff.getOrganization();
    			//if associated organization is not there in our DB
    			Organization organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    			if (organization == null) {
    				organizationRepository.create(remoteOrganization);
    				organization = organizationDao.getByNCIcode(remoteOrganization.getNciInstituteCode());
    			} 
    			remoteResearchStaff.setOrganization(organization);
 				*/
    			try {
    				save(remoteResearchStaff,"URL");
    			} catch (MailException e) {
    				e.printStackTrace();
    			}
        		//researchStaffDao.save(remoteResearchStaff);
        		rs = researchStaffDao.getByEmailAddress(remoteResearchStaff.getEmailAddress());
        		rs.setFirstName(remoteResearchStaff.getFirstName());
        		rs.setLastName(remoteResearchStaff.getLastName());
        		rs.setMiddleName(remoteResearchStaff.getMiddleName());
        		localList.add(rs);
        	} else {
        		// if it exist in local list , remote interceptor would have loaded the rest of the details .
        		if (!localList.contains(rs)) {
        			localList.add(rs);
        		}
        	}
    	}
		return localList;
	}
    
    @Transactional(readOnly = false)
    public List<ResearchStaff> getByNciIdentifier(final String[] subnames, final int site){
    	List<ResearchStaff> researchStaffs = researchStaffDao.getByNciIdentifier(subnames,site);
    	ResearchStaff searchCriteria = new RemoteResearchStaff();
    	List<ResearchStaff> remoteResearchStaffs = getRemoteResearchStaff(searchCriteria); 
    	
    	return merge (researchStaffs,remoteResearchStaffs);
    }

    public List<ResearchStaff> getRemoteResearchStaff(final ResearchStaff researchStaff){
    	return researchStaffDao.getRemoteResearchStaff(researchStaff);
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

    @Required
	public void setResearchStaffConverterDao(
			ResearchStaffConverterDao researchStaffConverterDao) {
		this.researchStaffConverterDao = researchStaffConverterDao;
	}

	@Required
	public void setOrganizationConverterDao(
			OrganizationConverterDao organizationConverterDao) {
		this.organizationConverterDao = organizationConverterDao;
	}

	
	@Required
	public void setOrganizationRepository(
			OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}
	
	@Required
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Required
	public void setStudyRepository(StudyRepository studyRepository) {
		this.studyRepository = studyRepository;
	}
    
}