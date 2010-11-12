package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.security.CSMUser;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mail.MailException;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class UserController extends SimpleFormController {
	
	private CaaersSecurityFacade caaersSecurityFacade;
	
	/**
	 * This method processes the given UserCommand.
	 * Creates a user in CSM.
	 * Provisions all the Roles & Role-Memberships for the User in CSM
	 * @param userCommand
	 */
	public void processUserCommand(HttpServletRequest request,UserCommand userCommand){
		User csmUser = null;
		try{
			//Create or Update User 
			csmUser = createOrUpdateCSMUser(request,userCommand.getCsmUser());
			//Process the RoleMemeberships for the User.
			processRoleMemberships(csmUser,userCommand.getRoleMemberships());
		}catch(MailException mEx){
			//TODO
			//Handle exception appropriately for UI purposes.
			processRoleMemberships(csmUser,userCommand.getRoleMemberships());
			mEx.printStackTrace();
		}catch(Exception ex){
			//TODO
			//Handle exception appropriately for UI purposes.
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method creates a User in CSM.
	 * @param request
	 * @param csmUser
	 * @return
	 */
	protected User createOrUpdateCSMUser(HttpServletRequest request,CSMUser caaersUser){
		
		return caaersSecurityFacade.createOrUpdateUser(caaersUser, 
													   ResetPasswordController.getURL(request.getScheme(), 
																					   request.getServerName(),
																					   request.getServerPort(),
																					   request.getContextPath()));
	}
	
	/**
	 * This method delegates the call to CaaersSecurityFacade to provision all the RoleMemberships for the given User in CSM.
	 * @param csmUser
	 * @param roleMemberships
	 */
	protected void processRoleMemberships(User csmUser, List<SuiteRoleMembership> roleMemberships){
		if(roleMemberships != null && roleMemberships.size() > 0){
			caaersSecurityFacade.provisionRoleMemberships(csmUser, roleMemberships);
		}
	}
	
	//Setter & Getters.
	
	public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
		this.caaersSecurityFacade = caaersSecurityFacade;
	}
}