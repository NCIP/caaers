package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;

/**
 * @author Jared Flatow
 */
public class LoginPolicyValidator implements PasswordPolicyValidator {

    public static final PasswordPolicyValidator Singleton = new LoginPolicyValidator();
    private LoginPolicyValidator() {}

    public boolean validate(PasswordPolicy policy, Credential credential) throws ValidationException {
	LoginPolicy loginPolicy = policy.getLoginPolicy();

	return validateAllowedFailedLoginAttempts(loginPolicy, credential)
	    && validateLockOutDuration(loginPolicy, credential)
	    && validateMaxPasswordAge(loginPolicy, credential);
    }

    private boolean validateAllowedFailedLoginAttempts(LoginPolicy policy, Credential credential) 
	throws ValidationException {
	// TODO
	return true;
    }

    private boolean validateLockOutDuration(LoginPolicy policy, Credential credential) 
	throws ValidationException {
	// TODO
	return true;
    }

    private boolean validateMaxPasswordAge(LoginPolicy policy, Credential credential) 
	throws ValidationException {
	// TODO
	return true;
    }
}
