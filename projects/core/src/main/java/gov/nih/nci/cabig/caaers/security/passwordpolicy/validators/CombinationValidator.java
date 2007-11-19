package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.security.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public class CombinationValidator implements PolicyValidator{

	public boolean validate(PasswordPolicy policy,Credential credential) throws ValidationException {
		// TODO Auto-generated method stub
		
		CombinationPolicy cp = policy.getPasswordCreationPolicy().getComplexityPolicy().getCombinationPolicy();
		
		int minRequired= cp.getMinRequired();
		
		int requirementsMet = 0;
		
		StringBuffer requirements = new StringBuffer();
		requirements.append("The password should meet "+minRequired+" requirements from following:\n");
		int j=0;
		
		ArrayList<PolicyValidator> validators = new ArrayList<PolicyValidator>();
		if(cp.isBaseTenDigit()){
			validators.add(new BaseTenDigitValidator());
			j++;
			requirements.append("\t"+j+") The password should have atleast one numeral digit{0-9}");
			requirements.append("\n");
		}
		if(cp.isLowercaseAlphabet()){
			validators.add(new LowerCaseAlphabetValidator());
			j++;
			requirements.append("\t"+j+") The password should have at least one lower case letter");
			requirements.append("\n");
		}
		if(cp.isNonAlphaNumeric()){
			validators.add(new SpecialCharacterValidator());
			j++;
			requirements.append("\t"+j+") The password should have at least one special charcter");
			requirements.append("\n");
		}
		if(cp.isUpperCaseAlphabet()){
			validators.add(new UpperCaseAlphabetValidator());
			j++;
			requirements.append("\t"+j+") The password should have at least one upper case letter");
			requirements.append("\n");
		}
		if(cp.getSubStringExclusionPolicy()!=null){
			validators.add(new SubStringExclusionValidator());
			int k = cp.getSubStringExclusionPolicy().getSubStringLength();
			j++;
			requirements.append("\t"+j+") The password should not have sub string with "+k+" letters from user name");
			requirements.append("\n");
		}
		
		StringBuffer exceptions = new StringBuffer();
		for(int i=0; i<validators.size();i++){
			PolicyValidator validator = validators.get(i);
			try{
				boolean ok= validator.validate(policy, credential);
				requirementsMet++;
			}catch(ValidationException ve){
				exceptions.append(ve.getMessage());
				exceptions.append("\n");
			}
		}
		
		if(requirementsMet<minRequired){
			
			
			throw new ValidationException(requirements.toString());
		}
		
		
		
		return true;
	}

}
