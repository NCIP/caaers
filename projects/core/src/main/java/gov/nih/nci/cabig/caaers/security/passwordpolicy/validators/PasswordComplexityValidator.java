package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.security.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.ComplexityPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public class PasswordComplexityValidator implements PolicyValidator{

	public boolean validate(PasswordPolicy policy,Credential credential) throws ValidationException {
           boolean validationPassed = true;
		
		
		ArrayList<PolicyValidator> validators = new ArrayList<PolicyValidator>();
		
		ComplexityPolicy cp = policy.getPasswordCreationPolicy().getComplexityPolicy();
		
		if(cp.getMinLengthPolicy()!=null){
			validators.add(new PasswordLengthValidator());
		}
		if(cp.getCombinationPolicy()!=null){
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
