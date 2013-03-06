/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.tools.Messages;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import gov.nih.nci.cabig.caaers.domain.Person;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.Locale;

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
        if (command.getCreateAsUser()) {
            try {
                createOrUpdateUser(request, command.getUser());
            } catch (MailException e) {
                mailSendIssue = Messages.get("LBL_userEmailFailure");
                logger.error("Could not send email to user.", e);
            }
            processRoleMemberships(command.getUser().getCsmUser(), command.getRoleMemberships());
        }

        if (command.getCreateAsPerson()) {
            personRepository.save(command.getPerson());
            getEventFactory().publishEntityModifiedEvent(command.getPerson());
        }

        String statusMessage = "";
        String personType = "";

        if (command.getPersonType() != null) {
            if (command.getPersonType().equals("Investigator")) personType = Messages.get("LBL_investigator");
            else personType = Messages.get("LBL_research.staff");
        }

        if (command.getCreateAsPerson() && command.getCreateAsUser()) {
            statusMessage = Messages.get("LBL_createWithLogin", new Object[] {personType, mailSendIssue});
        }

        if (command.getCreateAsPerson() && !command.getCreateAsUser()) {
            statusMessage = Messages.get("LBL_createWithoutLogin", new Object[] {personType});
        }

        if (!command.getCreateAsPerson() && command.getCreateAsUser()) {
            statusMessage = Messages.get("LBL_createWithLogin", new Object[] {"a User", mailSendIssue});
        }

        modelAndView.getModel().put("flashMessage", statusMessage);

        StringBuffer reqUrl = new StringBuffer(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/pages/admin/editUser?");

        String id = "";
        String userName = "";
        String recordType = "";
        User user = command.getUser();
        Person person = command.getPerson();

        id = Integer.toString((person != null) ? person.getId() : user.getId());
        recordType = "CSM_RECORD";
        if (person != null) {
            recordType = (person instanceof ResearchStaff ? "RESEARCHSTAFF_RECORD" : "INVESTIGATOR_RECORD");
        }

        userName = (user != null ? user.getLoginName() : null);
        reqUrl.append("id=" + id.toString()).append("&").append(userName != null ? "userName=" + userName : "").append(user != null ? "&" : "").append("recordType=" + recordType);

        command.setRequestURL(reqUrl.toString());
        modelAndView.addAllObjects(errors.getModel());
        return modelAndView;
	}
	
	
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		UserCommand command = new UserCommand();
		String loggedInPersonUserName = SecurityUtils.getUserLoginName();
		User loggedinUser =  userRepository.getUserByLoginName(loggedInPersonUserName);
		command.setLoggedInUser(loggedinUser);
        command.setCreateAsPerson(command.getPO());
        command.setCreateAsUser(command.getUA());

		command.setCreateMode(Boolean.TRUE);
		command.setEditMode(Boolean.FALSE);
        System.out.println(" " + command.getPersonType());

        command.buildRolesHelper();



        return command;
	}
}
