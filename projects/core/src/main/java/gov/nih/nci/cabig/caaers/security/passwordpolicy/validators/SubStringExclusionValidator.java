package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.security.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.SubStringExclusionPolicy;

public class SubStringExclusionValidator implements PolicyValidator{
	
	

	public boolean validate(PasswordPolicy policy, Credential credential) throws ValidationException {
		SubStringExclusionPolicy ssev = policy.getPasswordCreationPolicy().getComplexityPolicy().getCombinationPolicy().getSubStringExclusionPolicy();
		
		int subStringLength = ssev.getSubStringLength();
		String password = credential.getPassword();
		ArrayList<String> possibleSubStrings = this.getSubStrings(credential.getPassword(), subStringLength);
		for(int i=0;i<possibleSubStrings.size();i++){
			if(password.indexOf((String)possibleSubStrings.get(i))!=-1){
				throw new ValidationException("The password can't contain a substring of "+subStringLength+" charcters from user name");
				
			}
		}
		return true;
	}

	
	private ArrayList<String> getSubStrings(String userName,int len){
		ArrayList<String> list = new ArrayList<String>();
		if(len>=userName.length()){
			list.add(userName);
		}
		
		int loop = (userName.length()-len)+1;
		String temp = userName;
		for(int i=0;i<loop;i++){
			String str = temp.substring(0,len);
			list.add(str);
			temp = temp.substring(1,temp.length());
		}
		return list;
	}
	
}
