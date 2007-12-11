package gov.nih.nci.cabig.caaers.web.user;

import gov.nih.nci.cabig.caaers.service.security.PasswordManagerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

/**
 * @author Jared Flatow
 */
public class ResetPasswordController extends SimpleFormController {

    private PasswordManagerService passwordManagerService;
    
    public ResetPasswordController() {
	setFormView("user/resetPassword");
	setBindOnNewForm(true);
    }


    protected Object formBackingObject(HttpServletRequest request) throws Exception {
	return new UserName();
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
	String token = passwordManagerService.requestToken(((UserName) command).getUserName());
	// send an email to the user with a link to changePassword
	return new ModelAndView("user/emailSent");
    }

    @Required
    public void setPasswordManagerService(PasswordManagerService passwordManagerService) {
	this.passwordManagerService = passwordManagerService;
    }

    public class UserName {
	private String userName;

	public String getUserName() {
	    return userName;
	}

	public void setUserName(String userName) {
	    this.userName = userName;
	}       
    }
}
