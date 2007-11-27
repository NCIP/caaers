package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.user.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public class PasswordCreationPolicyValidator implements PasswordPolicyValidator {
    
    public static final PasswordPolicyValidator Singleton = new PasswordCreationPolicyValidator();
    private PasswordCreationPolicyValidator() {}
    
    public boolean validate(PasswordPolicy policy, Credential credential) throws ValidationException {
	PasswordCreationPolicy passwordCreationPolicy = policy.getPasswordCreationPolicy();
	
	return validateMinPasswordAge(passwordCreationPolicy, credential)
	    && validatePasswordHistorySize(passwordCreationPolicy, credential)
	    && CombinationValidator.Singleton.validate(policy, credential);	
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
}
