package gov.nih.nci.cabig.caaers.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Padmaja Vedula
 * @author Rhett Sutphin
 * @author Sujith Vellat Thayyilthodi
 */
public class LoginController extends CaaersAbstractFormController {
	private static final String DEFAULT_TARGET_VIEW = "/pages/mainMenu";

	private static final Log log = LogFactory.getLog(LoginController.class);
	
	private LoginCommand loginCommand;

	private String targetView = DEFAULT_TARGET_VIEW;

	public String getTargetView() {
		return targetView;
	}

	public LoginController() {
		setCommandClass(LoginCommand.class);
		setBindOnNewForm(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		return loginCommand;
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		return new ModelAndView(getFormView(request), errors.getModel());
	}

	private String getFormView(HttpServletRequest request) {
		return request.getParameter("ajax") == null ? "login" : "relogin";
	}

	@SuppressWarnings("unchecked")
	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object oCommand, BindException errors)
			throws Exception {
		LoginCommand loginCommand = (LoginCommand) oCommand;
		log.debug("Username: " + loginCommand.getUsername());
		log.debug("System Config file is: "
				+ System.getProperty("gov.nih.nci.security.configFile"));
		boolean loginSuccess = loginCommand.login(request.getRemoteAddr());

		if (loginSuccess) {
			//ApplicationSecurityManager.setUser(request, loginCredentials.getUsername());
			return new ModelAndView(getTargetView(request));
		} else {
			Map<String, Object> model = errors.getModel();
			model.put("failed", true);
			return new ModelAndView(getFormView(request), model);
		}
	}

	private RedirectView getTargetView(HttpServletRequest request) {
		/*        
		 String targetUrl = LoginCheckInterceptor.getRequestedUrlOnce(request.getSession());
		 if (targetUrl == null) {
		 return new RedirectView(DEFAULT_TARGET_VIEW, true);
		 } else {
		 return new RedirectView(targetUrl);
		 }
		*/
		return new RedirectView(targetView, true);
	}

	public void setTargetView(String targetView) {
		this.targetView = targetView;
	}

	@Required
	public void setLoginCommand(LoginCommand loginCommand) {
		this.loginCommand = loginCommand;
	}
	
}