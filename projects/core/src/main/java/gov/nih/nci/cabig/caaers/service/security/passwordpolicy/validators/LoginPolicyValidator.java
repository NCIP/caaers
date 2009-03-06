package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Jared Flatow
 */
public class LoginPolicyValidator implements PasswordPolicyValidator {

    private CSMUserRepository csmUserRepository;

    public boolean validate(PasswordPolicy policy, Credential credential)
            throws ValidationException {
        LoginPolicy loginPolicy = policy.getLoginPolicy();

        return validateAllowedFailedLoginAttempts(loginPolicy, credential)
                && validateLockOutDuration(loginPolicy, credential)
                && validateMaxPasswordAge(loginPolicy, credential);
    }

    private boolean validateAllowedFailedLoginAttempts(LoginPolicy policy, Credential credential)
            throws ValidationException {
        if (csmUserRepository.getUserByName(credential.getUserName()).getFailedLoginAttempts() > policy
                .getAllowedFailedLoginAttempts()) {
            throw new ValidationException("Too many failed logins.");
        }
        return true;
    }

    private boolean validateLockOutDuration(LoginPolicy policy, Credential credential)
            throws ValidationException {
        // TODO
        return true;
    }

    private boolean validateMaxPasswordAge(LoginPolicy policy, Credential credential)
            throws ValidationException {
        if (csmUserRepository.getUserByName(credential.getUserName()).getPasswordAge() > policy
                .getMaxPasswordAge()) {
            throw new ValidationException("Password is too old.");
        }
        return true;
    }

    @Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }


}
