package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

/**
 * @author Ram Seethiraju
 */
public interface PasswordPolicyValidator {
    public boolean validate(PasswordPolicy policy, Credential credential, ValidationErrors validationErrors)
                    throws ValidationException;
}
