package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;

public class CombinationValidator implements PasswordPolicyValidator {
    public boolean validate(PasswordPolicy policy, Credential credential)
                    throws ValidationException {
        CombinationPolicy combinationPolicy = policy.getPasswordCreationPolicy().getCombinationPolicy();

        return validateLowerCaseAlphabet(combinationPolicy, credential)
                        && validateUpperCaseAlphabet(combinationPolicy, credential)
                        && validateNonAlphaNumeric(combinationPolicy, credential)
                        && validateBaseTenDigit(combinationPolicy, credential)
                        && validateMaxSubstringLength(combinationPolicy, credential);
    }

    private boolean validateLowerCaseAlphabet(CombinationPolicy policy, Credential credential)
                    throws ValidationException {
        if (policy.isLowerCaseAlphabetRequired()
                        && !credential.getPassword().matches(".*[\\p{javaLowerCase}].*")) {
            throw new ValidationException("The password should have at least one lower case letter");
        }
        return true;
    }

    private boolean validateUpperCaseAlphabet(CombinationPolicy policy, Credential credential)
                    throws ValidationException {
        if (policy.isUpperCaseAlphabetRequired()
                        && !credential.getPassword().matches(".*[\\p{javaUpperCase}].*")) {
            throw new ValidationException("The password should have at least one upper case letter");
        }
        return true;
    }

    private boolean validateNonAlphaNumeric(CombinationPolicy policy, Credential credential)
                    throws ValidationException {
        if (policy.isNonAlphaNumericRequired() && credential.getPassword().matches("[\\p{Alnum}]+")) {
            throw new ValidationException("The password should have at least one special charcter");
        }
        return true;
    }

    private boolean validateBaseTenDigit(CombinationPolicy policy, Credential credential)
                    throws ValidationException {
        if (policy.isBaseTenDigitRequired()
                        && !credential.getPassword().matches(".*[\\p{Digit}].*")) {
            throw new ValidationException(
                            "The password should have at least one numeral digit{0-9}");
        }
        return true;
    }

    private boolean validateMaxSubstringLength(CombinationPolicy policy, Credential credential)
                    throws ValidationException {    	
        int substringLength = policy.getMaxSubstringLength() + 1;
        String userName = credential.getUserName(), password = credential.getPassword();
        for (int i = 0; i < userName.length() - substringLength; i++) {
            try {
                if (password.contains(userName.substring(i, i + substringLength))) {
                    throw new ValidationException("The password should not contain a substring of "
                                    + substringLength + " letters from the username");
                }
            } catch (IndexOutOfBoundsException e) {
                return true;
            }
        }
        return true;
    }
}
