package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;

public class PasswordCreationPolicyValidator implements PasswordPolicyValidator {

    private PasswordPolicyValidator combinationValidator;

    private CSMUserRepository csmUserRepository;

    public PasswordCreationPolicyValidator() {
        combinationValidator = new CombinationValidator();
    }

    public boolean validate(PasswordPolicy policy, Credential credential)
            throws ValidationException {
        PasswordCreationPolicy passwordCreationPolicy = policy.getPasswordCreationPolicy();

        return validateMinPasswordAge(passwordCreationPolicy, credential)
                && validatePasswordHistory(passwordCreationPolicy, credential)
                && validateMinPasswordLength(passwordCreationPolicy, credential)
                && combinationValidator.validate(policy, credential);
    }

    private boolean validateMinPasswordAge(PasswordCreationPolicy policy, Credential credential)
            throws ValidationException {
        if (csmUserRepository.getUserByName(credential.getUserName()).getPasswordAge() < policy
                .getMinPasswordAge()) {
            throw new ValidationException("Password was changed too recently.");
        }
        return true;
    }

    private boolean validatePasswordHistory(PasswordCreationPolicy policy, Credential credential)
            throws ValidationException {
        if (csmUserRepository.userHasPassword(credential.getUserName(), credential.getPassword())
                || csmUserRepository.userHadPassword(credential.getUserName(), credential
                .getPassword())) {
            throw new ValidationException("Must choose a password that has not been used recently.");
        }
        return true;
    }

    private boolean validateMinPasswordLength(PasswordCreationPolicy policy, Credential credential)
            throws ValidationException {
        if (credential.getPassword().length() >= policy.getMinPasswordLength()) return true;
        throw new ValidationException("The minimum length of password must be at least "
                + policy.getMinPasswordLength() + " characters");
    }

    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }


}
