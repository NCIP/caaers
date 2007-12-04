package gov.nih.nci.cabig.caaers.service.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.service.UserService;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.ValidationException;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordPolicyValidator;

import org.springframework.beans.factory.annotation.Required;

public class PasswordPolicyServiceImpl implements PasswordPolicyService {
    
    PasswordPolicyValidator passwordCreationPolicyValidator, loginPolicyValidator;
    UserService userService;

    public PasswordPolicy getPasswordPolicy() throws CaaersSystemException {
	return null;
    }
    
    public void setPasswordPolicy(PasswordPolicy passwordPolicy) throws CaaersSystemException {
	// TODO
    }
    
    public String publishPasswordPolicy() {
	return "TODO";
    }

    public String publishPasswordPolicy(String xsltFileName) {
	return "TODO";
    }
    
    public boolean validatePasswordAgainstCreationPolicy(String userName, String password) throws CaaersSystemException {
	return passwordCreationPolicyValidator.validate(getPasswordPolicy(), new Credential(userName, password));
    }

    /*
    public boolean validatePasswordAgainstLoginPolicy(String userName, String password) throws CaaersSystemException {
	return loginPolicyValidator.validate(getPasswordPolicy(), new Credential(userName, password));
    }
    */

    @Required
    public void setPasswordCreationPolicyValidator(PasswordPolicyValidator passwordCreationPolicyValidator) {
	this.passwordCreationPolicyValidator = passwordCreationPolicyValidator;
    }

    @Required
    public void setLoginPolicyValidator(PasswordPolicyValidator loginPolicyValidator) {
	this.loginPolicyValidator = loginPolicyValidator;
    }

    @Required
    public void setUserService(UserService userService) {
	this.userService = userService;
    }
}
