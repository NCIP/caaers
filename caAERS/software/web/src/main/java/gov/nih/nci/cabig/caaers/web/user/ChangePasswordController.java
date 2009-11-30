package gov.nih.nci.cabig.caaers.web.user;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.service.security.PasswordManagerService;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordCreationPolicyException;
import gov.nih.nci.cabig.caaers.validation.ValidationError;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
        } catch (PasswordCreationPolicyException e) {
        	for(ValidationError vError : e.getErrors().getErrors()){
        		errors.reject(vError.getCode(), vError.getReplacementVariables(), vError.getMessage());
        	}
            return modelAndView.addObject("change_pwd_error", e.getErrors());
        } catch(CaaersNoSuchUserException e){
        	errors.rejectValue("userName", "USR_015" , new Object[]{cmd.getUserName()} , "Username is invalid.");
        	return modelAndView;
        } catch(CaaersSystemException e){
        	errors.reject("USR_016", "Invalid token.");
        	return modelAndView;
        }
    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request,Object command, BindException errors) throws Exception {
    	 ChangePasswordCommand cmd = (ChangePasswordCommand) command;
    	if(!StringUtils.equals(cmd.getPasswordNew(), cmd.getPasswordConfirm())){
    		errors.rejectValue("passwordConfirm","USR_011", "The passwords provided do not match");
    	}
    	super.onBindAndValidate(request, command, errors);
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
        private String userName, passwordNew, passwordConfirm, token;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPasswordNew() {
            return passwordNew;
        }

        public void setPasswordNew(String passwordNew) {
            this.passwordNew = passwordNew;
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
            if (passwordNew.equals(passwordConfirm)) return passwordNew;
            throw new CaaersSystemException("The two passwords entered are not the same,");
        }
    }
}
