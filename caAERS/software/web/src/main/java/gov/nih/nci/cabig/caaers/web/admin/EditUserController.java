package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.CSMUser;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class EditUserController extends UserController<UserCommand> {
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		request.getSession().removeAttribute(getReplacedCommandSessionAttributeName(request));
		User csmUser = caaersSecurityFacade.getCsmUserRepository().getCSMUserByName(request.getParameter("userName"));
		CSMUser caaersUser = new CSMUser();
		UserCommand command = new UserCommand();
		if(csmUser != null){
			//Set user details
			caaersUser.setId(csmUser.getUserId().intValue());
			caaersUser.setFirstName(csmUser.getFirstName());
			caaersUser.setLastName(csmUser.getLastName());
			caaersUser.setEmailAddress(csmUser.getEmailId());
			caaersUser.setLoginId(csmUser.getLoginName());
			//Get all the suite role memberships for user
			populateRoleMemberships(csmUser,command);
		}
		command.setCsmUser(caaersUser);
		command.buildRolesHelper();
		return command;
	}
	
	private void populateRoleMemberships(User csmUser,UserCommand command){
		List<UserGroupType> userGroups = caaersSecurityFacade.getCsmUserRepository().getUserGroups(csmUser.getLoginName());
		ProvisioningSession session =  proSessionFactory.createSession(csmUser.getUserId());
		for(UserGroupType group : userGroups){
			command.addRoleMembership(session.getProvisionableRoleMembership(SuiteRole.getByCsmName(group.getCsmName())));
		}
	}
}
