package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;

/**
 * @author Jared Flatow
 */
public class PasswordPolicyConfigurationController extends SimpleFormController {

    private PasswordPolicyService passwordPolicyService;

    public PasswordPolicyConfigurationController() {
	setFormView("admin/password_policy_configure");
	setBindOnNewForm(true);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
	return passwordPolicyService.getPasswordPolicy();
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
	if (errors.hasErrors()) return new ModelAndView(getFormView(), errors.getModel());
	passwordPolicyService.setPasswordPolicy((PasswordPolicy) command);
	return new ModelAndView("redirectToPasswordPolicyConfiguration", "updated", true);
    }

    @Required
    public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
	this.passwordPolicyService = passwordPolicyService;
    }
}
