package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.security.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public interface PolicyValidator {

	public boolean validate(PasswordPolicy policy,Credential credential) throws ValidationException;
}