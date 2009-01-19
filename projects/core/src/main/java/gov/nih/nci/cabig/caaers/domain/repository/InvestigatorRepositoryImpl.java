package gov.nih.nci.cabig.caaers.domain.repository;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
/**
 * This is the repository class for managing investigators
 * @author Biju Joseph
 *
 */
@Transactional(readOnly = true)
public class InvestigatorRepositoryImpl implements InvestigatorRepository {
	private InvestigatorDao investigatorDao;
	private CSMUserRepository csmUserRepository;
	private String authenticationMode;
	private static final Log logger = LogFactory.getLog(InvestigatorRepositoryImpl.class); 
	 
	 /**
	 * Creates a new investigator in the system 
	 * OR
	 * Updates and existing investigator details
	 * 
	 * Will also update the corresponding features associated to the CSM groups
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public void save(Investigator investigator, String changeURL) {
		boolean createMode = investigator.getId() == null;
    	boolean webSSOAuthentication = authenticationMode.equals("webSSO");
    	
    	if (investigator.getEmailAddress() == null) {
            throw new CaaersSystemException("Email address is required");
        }
    	//loginId cannot be null in websso mode.
    	if(webSSOAuthentication && StringUtils.isBlank(investigator.getLoginId())){
    		throw new CaaersSystemException("Login Id cannot be null in webSSO mode");
    	}
    	
    	//if this is a new one, add the default group, set the login id if websso mode
    	if(createMode){
    		investigator.addUserGroupType(UserGroupType.caaers_physician);
    		//login id should be email id , if it is non websso mode
    		if(!webSSOAuthentication) investigator.setLoginId(investigator.getEmailAddress());
    	}
    	MailException mailException = null;
        try {
			csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator(investigator, changeURL);
		} catch (MailException e) {
			mailException = e;
		}
        investigatorDao.save(investigator);
        if(mailException != null) throw mailException;
        
	}
	
	
    public InvestigatorDao getInvestigatorDao() {
		return investigatorDao;
	}
    public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}
    public String getAuthenticationMode() {
		return authenticationMode;
	}
    public void setAuthenticationMode(String authenticationMode) {
		this.authenticationMode = authenticationMode;
	}
    public CSMUserRepository getCsmUserRepository() {
		return csmUserRepository;
	}
    public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}
}
