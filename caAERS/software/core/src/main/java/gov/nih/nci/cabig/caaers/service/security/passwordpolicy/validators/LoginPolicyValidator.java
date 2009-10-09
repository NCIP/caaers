package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;

/**
 * @author Jared Flatow
 * @author Ram Seethiraju
 */
public class LoginPolicyValidator implements PasswordPolicyValidator {

    public boolean validate(PasswordPolicy policy, Credential credential)
            throws ValidationException {
        LoginPolicy loginPolicy = policy.getLoginPolicy();

        return validateLockOutDuration(loginPolicy, credential)
                && validateAllowedFailedLoginAttempts(loginPolicy, credential)
                && validateMaxPasswordAge(loginPolicy, credential);
    }
    public boolean validateAllowedFailedLoginAttempts(LoginPolicy policy, Credential credential)
            throws TooManyAllowedFailedLoginAttemptsException {
        if (credential.getUser().getFailedLoginAttempts() >= policy
                .getAllowedFailedLoginAttempts()) {
            throw new TooManyAllowedFailedLoginAttemptsException("Too many failed logins.");
        }
        return true;
    }
    public boolean validateLockOutDuration(LoginPolicy policy, Credential credential)
            throws UserLockedOutException {
    	if(credential.getUser().getSecondsPastLastFailedLoginAttempt() == -1) 
    		return true;
    	else if(policy.getLockOutDuration()>credential.getUser().getSecondsPastLastFailedLoginAttempt())
    		throw new UserLockedOutException("User is locked out");
    	return true;
    }
    public boolean validateMaxPasswordAge(LoginPolicy policy, Credential credential)
            throws PasswordTooOldException {
    	
        if (credential.getUser().getPasswordAge() > policy
                .getMaxPasswordAge()) {
            throw new PasswordTooOldException("Password is too old.");
        }
        return true;
    }
   /* @Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }*/
}
