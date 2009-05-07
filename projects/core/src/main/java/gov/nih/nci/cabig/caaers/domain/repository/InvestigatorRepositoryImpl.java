package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.InvestigatorConverterDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.ConverterInvestigator;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.util.StringUtilities;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is the repository class for managing investigators
 * @author Biju Joseph
 *
 */
@Transactional(readOnly = false)
public class InvestigatorRepositoryImpl implements InvestigatorRepository {
	private InvestigatorDao investigatorDao;
	private SiteInvestigatorDao siteInvestigatorDao;
	private InvestigatorConverterDao investigatorConverterDao;
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
    		investigator.addUserGroupType(UserGroupType.caaers_user);
    		//login id should be email id , if it is non websso mode
    		//if(!webSSOAuthentication) investigator.setLoginId(investigator.getEmailAddress());
    	}
    	if(createMode && !webSSOAuthentication && StringUtilities.isBlank(investigator.getLoginId())) {
    		investigator.setLoginId(investigator.getEmailAddress());
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
	
	public List<Investigator> searchInvestigator(final InvestigatorQuery query){
		List<Investigator> localInvestigators = investigatorDao.getLocalInvestigator(query);
		//TODO populate searchCriteria 
		RemoteInvestigator searchCriteria = new RemoteInvestigator(); 
		List<Investigator> remoteInvestigators = investigatorDao.getRemoteInvestigators(searchCriteria);
		return merge(localInvestigators,remoteInvestigators);
	}
	
	public List<SiteInvestigator> getBySubnames(String[] subnames, int siteId) {
		List<SiteInvestigator> siteInvestigators = siteInvestigatorDao.getBySubnames(subnames,siteId);
		//RemoteInvestigator searchCriteria = new RemoteInvestigator(); 
		//List<SiteInvestigator> remoteInvestigators = siteInvestigatorDao.getRemoteInvestigators(searchCriteria);
		//return merge(localInvestigators,remoteInvestigators);
		return siteInvestigators;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    private List<Investigator> merge(List<Investigator> localList , List<Investigator> remoteList) {
		for (Investigator remoteInvestigator:remoteList) {
			Investigator inv = investigatorDao.getByEmailAddress(remoteInvestigator.getEmailAddress());
    		if (inv == null ) {
        		save(remoteInvestigator,"");
        		localList.add(remoteInvestigator);
        	} else {
        		// if it exist in local list , remote interceptor would have loaded the rest of the details .
        		if (!localList.contains(remoteInvestigator)) {
        			localList.add(remoteInvestigator);
        		}
        	}
    	}
		return localList;
	}
	
	@Transactional(readOnly = false)
    public void convertToRemote(Investigator localInvestigator, Investigator remoteInvestigator){
    	ConverterInvestigator conInv = investigatorConverterDao.getById(localInvestigator.getId());
    	conInv.setType("REMOTE");
    	conInv.setExternalId(remoteInvestigator.getExternalId());
    	conInv.setFirstName(remoteInvestigator.getFirstName());
    	conInv.setLastName(remoteInvestigator.getLastName());
    	conInv.setMiddleName(remoteInvestigator.getMiddleName());
    	conInv.setPhoneNumber(remoteInvestigator.getPhoneNumber());
    	conInv.setFaxNumber(remoteInvestigator.getFaxNumber());
    	investigatorConverterDao.save(conInv);
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

	public void setInvestigatorConverterDao(
			InvestigatorConverterDao investigatorConverterDao) {
		this.investigatorConverterDao = investigatorConverterDao;
	}

	public void setSiteInvestigatorDao(SiteInvestigatorDao siteInvestigatorDao) {
		this.siteInvestigatorDao = siteInvestigatorDao;
	}

}
