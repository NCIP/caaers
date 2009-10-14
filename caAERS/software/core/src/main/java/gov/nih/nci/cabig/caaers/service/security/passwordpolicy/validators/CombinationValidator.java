package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

/**
 * @author Ram Seethiraju
 */
public class CombinationValidator implements PasswordPolicyValidator {
	
    public boolean validate(PasswordPolicy policy, Credential credential, ValidationErrors validationErrors)
                    throws ValidationException {
        CombinationPolicy combinationPolicy = policy.getPasswordCreationPolicy().getCombinationPolicy();

        if(validateLowerCaseAlphabet(combinationPolicy, credential, validationErrors)
                        & validateUpperCaseAlphabet(combinationPolicy, credential, validationErrors)
                        & validateNonAlphaNumeric(combinationPolicy, credential, validationErrors)
                        & validateBaseTenDigit(combinationPolicy, credential, validationErrors)
                        & validateMaxSubstringLength(combinationPolicy, credential, validationErrors))
        	return true;
        return false;
    }

    private boolean validateLowerCaseAlphabet(CombinationPolicy policy, Credential credential, ValidationErrors validationErrors){
        if (policy.isLowerCaseAlphabetRequired()
                        && !credential.getPassword().matches(".*[\\p{javaLowerCase}].*")) {
            //throw new ValidationException("The password should have at least one lower case letter");
        	validationErrors.addValidationError("PCP_004", "The password should have at least one lower case letter");
        	return false;
        }
        return true;
    }

    private boolean validateUpperCaseAlphabet(CombinationPolicy policy, Credential credential, ValidationErrors validationErrors) {
        if (policy.isUpperCaseAlphabetRequired()
                        && !credential.getPassword().matches(".*[\\p{javaUpperCase}].*")) {
            //throw new ValidationException("The password should have at least one upper case letter");
        	validationErrors.addValidationError("PCP_005", "The password should have at least one upper case letter");
        	return false;
        }
        return true;
    }

    private boolean validateNonAlphaNumeric(CombinationPolicy policy, Credential credential, ValidationErrors validationErrors){
        if (policy.isNonAlphaNumericRequired() && credential.getPassword().matches("[\\p{Alnum}]+")) {
            //throw new ValidationException("The password should have at least one special charcter");
        	validationErrors.addValidationError("PCP_006", "The password should have at least one special charcter");
        	return false;
        }
        return true;
    }

    private boolean validateBaseTenDigit(CombinationPolicy policy, Credential credential, ValidationErrors validationErrors){
        if (policy.isBaseTenDigitRequired()
                        && !credential.getPassword().matches(".*[\\p{Digit}].*")) {
            //throw new ValidationException("The password should have at least one numeral digit{0-9}");
        	validationErrors.addValidationError("PCP_007", "The password should have at least one numeral digit{0-9}");
        	return false;
        }
        return true;
    }

    private boolean validateMaxSubstringLength(CombinationPolicy policy, Credential credential, ValidationErrors validationErrors){    	
        int substringLength = policy.getMaxSubstringLength() + 1;
        String userName = credential.getUserName(), password = credential.getPassword();
        for (int i = 0; i < userName.length() - substringLength; i++) {
            try {
                if (password.contains(userName.substring(i, i + substringLength))) {
                    //throw new ValidationException("The password should not contain a substring of "+ substringLength + " letters from the username");
                	validationErrors.addValidationError("PCP_008", "The password should not contain a substring of "+ substringLength + " letters from the username");
                	return false;
                }
            } catch (IndexOutOfBoundsException e) {
                return true;
            }
        }
        return true;
    }
}
