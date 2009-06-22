package gov.nih.nci.cabig.caaers.web.user;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.service.security.PasswordManagerService;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Jared Flatow
 */
public class ChangePasswordController extends SimpleFormController {

    private PasswordManagerService passwordManagerService;

    private PasswordPolicyService passwordPolicyService;

    public ChangePasswordController() {
        setFormView("user/changePassword");
        setBindOnNewForm(true);
    }

    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new ChangePasswordCommand();
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        ModelAndView modelAndView = new ModelAndView(getFormView(), errors.getModel());
        ChangePasswordCommand cmd = (ChangePasswordCommand) command;
        try {
            passwordManagerService.setPassword(cmd.getUserName(), cmd.confirmedPassword(), cmd.getToken());
            return modelAndView.addObject("updated", true);
        } catch (CaaersSystemException e) {
            return modelAndView.addObject("change_pwd_error", e);
        }
    }

    @Required
    public void setPasswordManagerService(PasswordManagerService passwordManagerService) {
        this.passwordManagerService = passwordManagerService;
    }

    @Required
    public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
        this.passwordPolicyService = passwordPolicyService;
    }

    public class ChangePasswordCommand {
        private String userName, password, passwordConfirm, token;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordConfirm() {
            return passwordConfirm;
        }

        public void setPasswordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String confirmedPassword() throws CaaersSystemException {
            if (password.equals(passwordConfirm)) return password;
            throw new CaaersSystemException("The two passwords entered are not the same,");
        }
    }
}
