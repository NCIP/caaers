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
                mailSendIssue = ". But could not send email to the User";
                logger.error("Could not send email to user.", e);
            }
            processRoleMemberships(command.getUser().getCsmUser(), command.getRoleMemberships());
        }

        if (command.getCreateAsPerson()) {
            personRepository.save(command.getPerson());
            getEventFactory().publishEntityModifiedEvent(command.getPerson());
        }

        String statusMessage = "";
            String personType = Messages.get("LBL_research.staff");
            if (command.getPersonType().equals("Investigator")) personType = Messages.get("LBL_investigator");

            if (command.getCreateAsPerson() && command.getCreateAsUser()) {
                statusMessage = String.format("Created %s with login capability%s", personType, mailSendIssue) ;
            }

            if (command.getCreateAsPerson() && !command.getCreateAsUser()) {
                statusMessage = String.format("Created %s without login capability", personType);
            }

            if (!command.getCreateAsPerson() && command.getCreateAsUser()) {
                statusMessage = String.format("Created a User with login capability%s", mailSendIssue);
            }

            modelAndView.getModel().put("flashMessage", statusMessage);
                StringBuffer reqUrl =  new StringBuffer(request.getScheme()+ "://" + request.getServerName()  + ":" +  request.getServerPort() +  request.getContextPath() + "/pages/admin/editUser?");
                           String id = "";
                           String userName= "";
                           String recordType = "";
                           User user = command.getUser();
                           Person person = command.getPerson();

                           id = Integer.toString((person != null) ? person.getId() : user.getId());
                           recordType = "CSM_RECORD";
                            if(person != null){
                                recordType = (person instanceof ResearchStaff  ? "RESEARCHSTAFF_RECORD" :"INVESTIGATOR_RECORD");
                            }
                            userName = (user != null  ? user.getLoginName() : null);

                        reqUrl.append( "id=" +id.toString()).append("&").append(userName != null ? "userName=" + userName : "").append(user != null ? "&" : "").append("recordType=" +recordType);


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