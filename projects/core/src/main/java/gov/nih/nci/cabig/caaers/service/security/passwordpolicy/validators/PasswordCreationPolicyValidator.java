package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;

public class PasswordCreationPolicyValidator implements PasswordPolicyValidator {
    
    private PasswordPolicyValidator combinationValidator;
    
    public boolean validate(PasswordPolicy policy, Credential credential) throws ValidationException {
	PasswordCreationPolicy passwordCreationPolicy = policy.getPasswordCreationPolicy();
	
	return validateMinPasswordAge(passwordCreationPolicy, credential)
	    && validatePasswordHistorySize(passwordCreationPolicy, credential)
	    && combinationValidator.validate(policy, credential);	
    }  

    private boolean validateMinPasswordAge(PasswordCreationPolicy policy, Credential credential)
	throws ValidationException {
	policy.getMinPasswordAge();
	// TODO
	return true;
    }

    private boolean validatePasswordHistorySize(PasswordCreationPolicy policy, Credential credential) 
	throws ValidationException {
	policy.getPasswordHistorySize();
	// TODO
	return false;
    }

    private boolean validateMinPasswordLength(PasswordCreationPolicy policy, Credential credential)
	throws ValidationException {
	if (credential.getPassword().length() >= policy.getMinPasswordLength()) return true;
	throw new ValidationException("The minimum length of password must be at least "
				      + policy.getMinPasswordLength() + " characters");
    }

    public void setCombinationPolicyValidator(PasswordPolicyValidator combinationValidator) {
	this.combinationValidator = combinationValidator;
    }
}
