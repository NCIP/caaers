package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.Address;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain._User;

import java.util.Locale;

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

		if(command.getCreateAsUser()){
            try {
    			createOrUpdateUser(request,command.getUser());
            }catch (MailException e) {
                logger.error("Could not send email to user.", e);
            }
            processRoleMemberships(command.getUser().getCsmUser(),command.getRoleMemberships());
		}
		if(command.getCreateAsPerson()){
			personRepository.save(command.getPerson());
		}
        if (!errors.hasErrors()) {
        	String statusMessage = "";
        	if(command.getCreateAsPerson() && command.getCreateAsUser()){
        		statusMessage = "Created " +command.getPersonType()+ " with login capability"; 
        	}
        	if(command.getCreateAsPerson() && !command.getCreateAsUser()){
        		statusMessage = "Created " +command.getPersonType()+ " without login capability";
        	}
        	if(!command.getCreateAsPerson() && command.getCreateAsUser()){
        		statusMessage = "Created a User with login capability";
        	}
            modelAndView.getModel().put("flashMessage", statusMessage);
        }
        modelAndView.addAllObjects(errors.getModel());
		return modelAndView;
	}
	
	
	@Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException {
		UserCommand command = new UserCommand();
		command.setUser(new _User());
        ResearchStaff rs = new LocalResearchStaff();
        rs.setAddress(new Address());
        command.setResearchStaff(rs);
        command.buildRolesHelper();
		return command;
	}
	
    @Override
    protected boolean suppressValidation(HttpServletRequest request, Object command) {
        if (isAjaxRequest(request)) return true;
        return super.suppressValidation(request, command); 
    }

}
