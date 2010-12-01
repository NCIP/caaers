package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.security.CSMUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Monish
 *
 */
public class CreateUserController extends UserController<UserCommand>{
	
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		UserCommand command = new UserCommand();
		command.setCsmUser(new CSMUser());
		command.buildRolesHelper();
		return command;
	}

}
