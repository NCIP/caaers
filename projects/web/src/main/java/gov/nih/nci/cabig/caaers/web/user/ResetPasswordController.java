package gov.nih.nci.cabig.caaers.web.user;

import gov.nih.nci.cabig.caaers.service.UserService;
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
    private UserService userService;
    
    public ResetPasswordController() {
	setFormView("user/resetPassword");
	setBindOnNewForm(true);
    }


    protected Object formBackingObject(HttpServletRequest request) throws Exception {
	return new UserName(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath());
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
	UserName userName = (UserName) command;
	String token = passwordManagerService.requestToken(userName.getUserName());
	userService.sendUserEmail(userName.getUserName(), "Reset caAERS Password", userName.getURL() + "&token=" + token);
	return new ModelAndView("user/emailSent");
    }

    @Required
    public void setPasswordManagerService(PasswordManagerService passwordManagerService) {
	this.passwordManagerService = passwordManagerService;
    }

    @Required
    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    public class UserName {
	private static final String CHANGE_PATH = "/public/user/changePassword?";
	private String userName;
	private String url;

	public UserName(String scheme, String serverName, int serverPort, String contextPath) {
	    url = scheme + "://" + serverName + ":" + serverPort + contextPath + CHANGE_PATH;
	}

	public String getUserName() {
	    return userName;
	}

	public void setUserName(String userName) {
	    this.userName = userName;
	    this.url += "userName=" + userName;
	}

	public String getURL() {
	    return url;
	}
    }
}
