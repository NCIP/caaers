package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.security.Credential;

import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public class PasswordLengthValidator implements PolicyValidator{
	
	public PasswordLengthValidator(){
		
	}

	public boolean validate(PasswordPolicy policy,Credential credential) throws ValidationException {
		// TODO Auto-generated method stub
		
		String password = credential.getPassword();
		int minLength = policy.getPasswordCreationPolicy().getMinimumLength();
		
		
		
		if(password.length()<minLength){
			throw new ValidationException("The minimum length of password should be of "+minLength+" characters");
		}
		return true;
	}

}
