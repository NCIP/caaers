package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.user.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public interface PasswordPolicyValidator {
	public boolean validate(PasswordPolicy policy, Credential credential) throws ValidationException;
}
