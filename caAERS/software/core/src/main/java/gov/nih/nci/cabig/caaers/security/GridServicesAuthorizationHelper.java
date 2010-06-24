package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.List;

import org.globus.wsrf.security.SecurityManager;
import org.springframework.beans.factory.annotation.Required;

/**
 * This class is used by caaers grid services to check users authorization.
 * @author Monish
 *
 */
public class GridServicesAuthorizationHelper {

	CaaersSecurityFacadeImpl caaersSecurityFacade;
	
	/**
	 * This method checks if the user has registrar  role.
	 * If user has this role, method will return true. 
	 * @param userName
	 * @return
	 */
	public boolean authorizedRegistrationConsumer(){
		String gridIdentity = SecurityManager.getManager().getCaller();
		String userName = gridIdentity.substring(gridIdentity.indexOf("/CN=")+4, gridIdentity.length());
		List<UserGroupType> roles = caaersSecurityFacade.getCsmUserRepository().getUserGroups(userName);
		if(roles.contains(UserGroupType.registrar)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method checks if the user has study_creator or study_qa_manager role.
	 * If user has one of these roles, method will return true.
	 * @param userName
	 * @return
	 */
	public boolean authorizedStudyConsumer(){
		String gridIdentity = SecurityManager.getManager().getCaller();
		String userName = gridIdentity.substring(gridIdentity.indexOf("/CN=")+4, gridIdentity.length());
		List<UserGroupType> roles = caaersSecurityFacade.getCsmUserRepository().getUserGroups(userName);
		if(roles.contains(UserGroupType.study_creator) || roles.contains(UserGroupType.study_qa_manager)){
			return true;
		}else{
			return false;
		}
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
		List<UserGroupType> roles = caaersSecurityFacade.getCsmUserRepository().getUserGroups(userName);
		if(roles.contains(UserGroupType.lab_data_user)){
			return true;
		}else{
			return false;
		}
	}

	public CaaersSecurityFacade getCaaersSecurityFacade() {
		return caaersSecurityFacade;
	}

	@Required
	public void setCaaersSecurityFacade(CaaersSecurityFacadeImpl caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}
}
