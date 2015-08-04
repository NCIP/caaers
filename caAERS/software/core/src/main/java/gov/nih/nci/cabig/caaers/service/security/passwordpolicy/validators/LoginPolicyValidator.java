/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import org.acegisecurity.*;

/**
 * @author Jared Flatow
 * @author Ram Seethiraju
 */
public class LoginPolicyValidator implements PasswordPolicyValidator {

	/**
	 * This method calls the actual login policy validating methods
	 */
    public boolean validate(PasswordPolicy policy, Credential credential, ValidationErrors validationErrors)
            throws AuthenticationException {
        LoginPolicy loginPolicy = policy.getLoginPolicy();

        return validateLockOutDuration(loginPolicy, credential)
                && validateAllowedFailedLoginAttempts(loginPolicy, credential)
                && validateMaxPasswordAge(loginPolicy, credential)
                && validateAccountExpiry(loginPolicy, credential);
    }
    
    /**
     * Validates the failed login attempts
     * note: default allowed login attempts is 3
     * @param policy
     * @param credential
     * @return
     * @throws DisabledException - when number of failed login attempts is greater than the one set in login policy
     */
    public boolean validateAllowedFailedLoginAttempts(LoginPolicy policy, Credential credential) throws DisabledException {
        if (credential.getUser().getFailedLoginAttempts() > policy.getAllowedFailedLoginAttempts()) {
        	throw new DisabledException("Too many failed login attempts resulted in account lock out for "+policy.getLockOutDuration()+" seconds.");
        }
       	return true;
    }
    
    /**
     * Validates the lock out duration
     * note: default lock out time is 3 minutes
     * @param policy
     * @param credential
     * @return
     * @throws LockedException - when login policy lock out duration is greater than the seconds past last failed login attempt
     */
    public boolean validateLockOutDuration(LoginPolicy policy, Credential credential)  throws LockedException {
        User user = credential.getUser();
        if(user.isLocked()) throw new LockedException("User account is locked or disabled.");

        long secondsPastLastFailedLoginAttempt = user.getSecondsPastLastFailedLoginAttempt();
    	if(secondsPastLastFailedLoginAttempt <= 0) return true;

        if(policy.getLockOutDuration() > secondsPastLastFailedLoginAttempt && user.getFailedLoginAttempts() > policy.getAllowedFailedLoginAttempts()) {
    		long timeLeft = policy.getLockOutDuration() - secondsPastLastFailedLoginAttempt;
    		throw new LockedException("Account is locked out for "+timeLeft+" more second(s).");
    	}

    	return true;
    }
    
    /**
     * Validates the Max password age of an account
     * note: default max password age is 48 hours
     * @param policy
     * @param credential
     * @return
     * @throws CredentialsExpiredException - when password age is greater than the one set in login policy 
     */
    public boolean validateMaxPasswordAge(LoginPolicy policy, Credential credential)  throws CredentialsExpiredException {
    	
        if (credential.getUser().getPasswordAge() > policy
                .getMaxPasswordAge()) {
        	throw new CredentialsExpiredException("Password is too old.");
        }
        return true;
    }

    /**
     * Checks if account is expired, yes then it will throw and Account Expired exception.
     * @param policy
     * @param credential
     * @return
     * @throws CredentialsExpiredException - when password age is greater than the one set in login policy
     */
    public boolean validateAccountExpiry(LoginPolicy policy, Credential credential)  throws AccountExpiredException {

        if(!credential.getUserDetails().isAccountNonExpired()){
            throw new AccountExpiredException((new StringBuilder()).append("Error authenticating: User is InActive").toString());
        }
        return true;
    }


}
