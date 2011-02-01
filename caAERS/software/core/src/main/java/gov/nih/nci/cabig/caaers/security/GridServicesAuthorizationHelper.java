package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteAuthorizationAccessException;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.UserProvisioningManager;

import java.util.List;

import org.globus.wsrf.security.SecurityManager;

/**
 * This class is used by caaers grid services to check users authorization.
 * @author Monish
 *
 */
public class GridServicesAuthorizationHelper {

	private UserProvisioningManager userProvisioningManager;
	private UserRepository userRepository;
	private ProvisioningSessionFactory provisioningSessionFactory;
	
	/**
	 * This method checks if the user has registrar role & checks Site & Study Scope to provide the right decision.
	 *  
	 * @param userName
	 * @return
	 */
	public boolean authorizedRegistrationConsumer(String siteIdetifier, String studyIdentifier){
		String gridIdentity = SecurityManager.getManager().getCaller();
		String userName = gridIdentity.substring(gridIdentity.indexOf("/CN=")+4, gridIdentity.length());
		gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(userName);
		if(csmUser == null){
			return false;
		}
		ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
		SuiteRole suiteRole = SuiteRole.getByCsmName(UserGroupType.registrar.getCsmName());
		SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
		List<String> siteIdentifiers = null;
		List<String> studyIdentifiers = null;
		try{
			siteIdentifiers = suiteRoleMembership.getSiteIdentifiers();
		}catch(SuiteAuthorizationAccessException siteE){
			//allSite = true;
		}
		try{
			studyIdentifiers = suiteRoleMembership.getStudyIdentifiers();
		}catch(SuiteAuthorizationAccessException studyE){
			//allStudy = true;
		}
		if(siteIdentifiers == null && studyIdentifiers == null){
			return true;
		}
		if(siteIdentifiers != null && studyIdentifiers != null){
			if(siteIdentifiers.contains(siteIdetifier)){
				if(studyIdentifiers.contains(studyIdentifier)){
					return true;
				}
			}
		}
		if(siteIdentifiers != null && studyIdentifiers == null){
			if(siteIdentifiers.contains(siteIdetifier)){
				return true;
			}
		}
		if(siteIdentifiers == null && studyIdentifiers != null){
			if(studyIdentifiers.contains(studyIdentifier)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method checks if the user has study_creator or study_qa_manager role & checks Site Scope to provide the right decision
	 * 
	 * @param userName
	 * @return
	 */
	public boolean authorizedStudyConsumer(String siteIdetifier){
		String gridIdentity = SecurityManager.getManager().getCaller();
		String userName = gridIdentity.substring(gridIdentity.indexOf("/CN=")+4, gridIdentity.length());
		gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(userName);
		if(csmUser == null){
			return false;
		}
		ProvisioningSession provisioningSession = provisioningSessionFactory.createSession(csmUser.getUserId());
		List<String> siteIdentifiers = null;
		
		//Process as study_creator
		SuiteRole suiteRole = SuiteRole.getByCsmName(UserGroupType.study_creator.getCsmName());
		SuiteRoleMembership suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
		try{
			siteIdentifiers = suiteRoleMembership.getSiteIdentifiers();
		}catch(SuiteAuthorizationAccessException siteE){
			//allSite = true;
			return true;
		}
		if(siteIdentifiers != null && siteIdentifiers.contains(siteIdetifier)){
			return true;
		}
		
		//Process as study_qa_manager
		suiteRole = SuiteRole.getByCsmName(UserGroupType.study_qa_manager.getCsmName());
		suiteRoleMembership = provisioningSession.getProvisionableRoleMembership(suiteRole);
		try{
			siteIdentifiers = suiteRoleMembership.getSiteIdentifiers();
		}catch(SuiteAuthorizationAccessException siteE){
			//allSite = true;
			return true;
		}
		if(siteIdentifiers != null && siteIdentifiers.contains(siteIdetifier)){
			return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the user has lab_data_user role.
	 * If user has this role, method will return true.
	 * @param userName
	 * @return
	 */
	public boolean authorizedLabConsumer(){
		String gridIdentity = SecurityManager.getManager().getCaller();
		String userName = gridIdentity.substring(gridIdentity.indexOf("/CN=")+4, gridIdentity.length());
		gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(userName);
		if(csmUser == null){
			return false;
		}
		List<UserGroupType> roles = userRepository.getUserGroups(csmUser.getUserId().toString());
		if(roles.contains(UserGroupType.lab_data_user)){
			return true;
		}else{
			return false;
		}
	}
	

	public void setUserProvisioningManager(
			UserProvisioningManager userProvisioningManager) {
		this.userProvisioningManager = userProvisioningManager;
	}
	
	

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setProvisioningSessionFactory(
			ProvisioningSessionFactory provisioningSessionFactory) {
		this.provisioningSessionFactory = provisioningSessionFactory;
	}
}