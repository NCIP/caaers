package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

/**
 * @author Ram Seethiraju
 */
public class PasswordCreationPolicyValidator implements PasswordPolicyValidator {

    private PasswordPolicyValidator combinationValidator;
    private _User user;

    public PasswordCreationPolicyValidator() {
        combinationValidator = new CombinationValidator();
    }

    /**
     * This method calls all the password creation policy validating methods
     */
    public boolean validate(PasswordPolicy policy, Credential credential, ValidationErrors validationErrors)
            throws ValidationException {
        PasswordCreationPolicy passwordCreationPolicy = policy.getPasswordCreationPolicy();

        if(validateMinPasswordAge(passwordCreationPolicy, credential, validationErrors) 
        		& validatePasswordHistory(passwordCreationPolicy, credential, validationErrors) 
        		& validateMinPasswordLength(passwordCreationPolicy, credential, validationErrors) 
        		& combinationValidator.validate(policy, credential, validationErrors))
        	return true;
        return false;
    }

    /**
     * Validates the minimum password age
     * @param policy
     * @param credential
     * @return
     * @throws ValidationException - if the user password age is less than one set in passwords creation policy 
     */
    public boolean validateMinPasswordAge(PasswordCreationPolicy policy, Credential credential, ValidationErrors validationErrors) {
        if (user.getPasswordAge() < policy.getMinPasswordAge()) {
            //throw new ValidationException("Password was changed too recently.");
        	validationErrors.addValidationError("PCP_001", "Password was changed too recently.");
        	return false;
        }
        return true;
    }

    /**
     * Validates password history
     * note: default value is 3
     * @param policy
     * @param credential
     * @return
     * @throws ValidationException - when user tries to set password which is same as the one which is already used before or currently using.
     */
    public boolean validatePasswordHistory(PasswordCreationPolicy policy, Credential credential, ValidationErrors validationErrors) {
        if (user.userHasPassword(credential.getPassword()) || user.userHadPassword(credential.getPassword())) {
            //throw new ValidationException("Must choose a password that has not been used recently.");
        	validationErrors.addValidationError("PCP_002", "Must choose a password that has not been used recently.");
        	return false;
        }
        return true;
    }

    /**
     * validates minimum password length
     * note: default value is 7
     * @param policy
     * @param credential
     * @return
     * @throws ValidationException - when user password length is greater than or equal to the one set in password creation policy.
     */
    public boolean validateMinPasswordLength(PasswordCreationPolicy policy, Credential credential, ValidationErrors validationErrors) {
        if (credential.getPassword().length() >= policy.getMinPasswordLength()) return true;
        //throw new ValidationException("The minimum length of password must be at least "+ policy.getMinPasswordLength() + " characters");
        else {
        	validationErrors.addValidationError("PCP_003", "The minimum length of password must be at least "+ policy.getMinPasswordLength() + " characters", policy.getMinPasswordLength());
        	return false;
        }
    }

    public _User getUser() {
		return user;
	}

	public void setUser(_User user) {
		this.user = user;
	}
}
