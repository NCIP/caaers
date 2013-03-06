/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Monish
 *
 */
public class CreateUserController extends UserController<UserCommand>{
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,HttpServletResponse response, Object userCommand, BindException errors) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("admin/user_confirmation");
		UserCommand command = (UserCommand)userCommand;
		String mailSendIssue = "";
		if(command.getCreateAsUser()){
            try {
    			createOrUpdateUser(request,command.getUser());
            }catch (MailException e) {
            	mailSendIssue = ". But could not send email to the User";
                logger.error("Could not send email to user.", e);
            }
            processRoleMemberships(command.getUser().getCsmUser(),command.getRoleMemberships());
		}
		if(command.getCreateAsPerson()){
			personRepository.save(command.getPerson());
            getEventFactory().publishEntityModifiedEvent(command.getPerson());
		}

        String statusMessage = "";
        if(command.getCreateAsPerson() && command.getCreateAsUser()){
            statusMessage = "Created " +command.getPersonType()+ " with login capability"+mailSendIssue ;
        }
        if(command.getCreateAsPerson() && !command.getCreateAsUser()){
            statusMessage = "Created " +command.getPersonType()+ " without login capability";
        }
        if(!command.getCreateAsPerson() && command.getCreateAsUser()){
            statusMessage = "Created a User with login capability"+mailSendIssue;
        }
        modelAndView.getModel().put("flashMessage", statusMessage);

        modelAndView.addAllObjects(errors.getModel());
		return modelAndView;
	}
	
	
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		UserCommand command = new UserCommand();
		String loggedInPersonUserName = SecurityUtils.getUserLoginName();
		_User loggedinUser =  userRepository.getUserByLoginName(loggedInPersonUserName);
		command.setLoggedInUser(loggedinUser);
        command.setCreateAsPerson(command.getPO());
        command.setCreateAsUser(command.getUA());

		command.setCreateMode(Boolean.TRUE);
		command.setEditMode(Boolean.FALSE);

        command.buildRolesHelper();

        return command;
	}
}
