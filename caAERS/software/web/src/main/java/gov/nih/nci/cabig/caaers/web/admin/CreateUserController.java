package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain._User;

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
		command.setUser(new _User());
		command.buildRolesHelper();
		return command;
	}

}
