package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;

public interface PasswordPolicyValidator {
	public boolean validate(PasswordPolicy policy, Credential credential) throws ValidationException;
}
