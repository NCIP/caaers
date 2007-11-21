package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.security.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public class PasswordCreationPolicyValidator implements PolicyValidator{
	
	public PasswordCreationPolicyValidator(){
		
	}

	public boolean validate(PasswordPolicy policy,Credential credential) throws ValidationException {
		boolean validationPassed = true;
		
		if(credential.getPassword()==null){
			throw new ValidationException("The password can't be empty or null");
		}
		ArrayList<PolicyValidator> validators = new ArrayList<PolicyValidator>();
		
		PasswordCreationPolicy pcp = policy.getPasswordCreationPolicy();
		
		if(pcp.getPreviousPasswordCount()>0){
			validators.add(new PasswordHistoryValidator());
		}
		if(pcp.getMinimumLength()>0){
			validators.add(new PasswordLengthValidator());
		}
		if(pcp.getMinimumAge()!=null){
			if(pcp.getMinimumAge().getValue()>0){
				validators.add(new MinimumAgeValidator());
			}
			
		}
		if(pcp.getCombinationPolicy()!=null){
			validators.add(new CombinationValidator());
		}
		StringBuffer exceptions = new StringBuffer();
		for(int i=0; i<validators.size();i++){
			PolicyValidator validator = validators.get(i);
			try{
				boolean ok= validator.validate(policy, credential);
			}catch(ValidationException ve){
				exceptions.append(ve.getMessage());
			}
		}
		String exceptionMessage = exceptions.toString();
		if(exceptionMessage.length()>0){
			throw new ValidationException(exceptionMessage);
		}
		
		return validationPassed;
	}

}
