package gov.nih.nci.cabig.caaers.security;



import gov.nih.nci.cabig.caaers.security.passwordpolicy.Duration;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.PasswordCreationPolicyValidator;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.PolicyValidator;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.ValidationException;

import java.io.File;

public class PasswordPolicyServiceImpl implements PasswordPolicyService{
	
	private String policyFile ="/vinaykumar/passwordpolicyinstance.xml";

	public PasswordPolicy getPasswordPolicy() throws Exception {
       /**
        * Now implement the way you want
        */
		return null;
	}

	public String publishPasswordPolicy() {
		StringBuffer policy = new StringBuffer();
		policy.append("The Password policy:");
		policy.append("\n");
		policy.append("--------------------");
		policy.append("\n");
		
		PasswordPolicy passwordPolicy = null;
		try {
			passwordPolicy = this.getPasswordPolicy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PasswordCreationPolicy pcp = passwordPolicy.getPasswordCreationPolicy();
		int i=0;
		Duration dr = pcp.getMinimumAge();
		if(dr!=null){
			int age = dr.getValue();
			i++;
		policy.append("\t");
		policy.append(i+") The password can't be changed before "+age+" days from the day it is created");
		policy.append("\t");
		policy.append("\n");
		}
		
		int historyCount = pcp.getPreviousPasswordCount();
		if(historyCount>0){
			i++;
			policy.append("\t");
			policy.append(i+") The password can't be resused from past "+historyCount+" passwords");
			policy.append("\t");
			policy.append("\n");
		}
		
		int minLength = pcp.getMinimumLength();
		i++;
		policy.append("\t");
		policy.append(i+")Complexity Requirements:");
		policy.append("\n");
		policy.append("\t");
		policy.append("------------------------");
		policy.append("\n");
		policy.append("\t\t");
		policy.append("A) The password should be of minimum "+minLength+" characters");
		policy.append("\n");
		policy.append("\t\t");
		policy.append("B) The password should meet atleast "+pcp.getCombinationPolicy().getMinimumRequired()+" requirements from the following:");
		policy.append("\n");
		policy.append("\t\t\t");
		 
		int j=0;
		if(pcp.getCombinationPolicy().isUpperCaseAlphabetRequired()){
			j++;
			policy.append(j+") The password should have atleast one upper case letter");
			policy.append("\n");
			policy.append("\t\t\t");
		}
		if(pcp.getCombinationPolicy().isLowerCaseAlphabetRequired()){
			j++;
			policy.append(j+") The password should have atleast one lower case letter");
			policy.append("\n");
			policy.append("\t\t\t");
			
		}
		if(pcp.getCombinationPolicy().isBaseTenDigitRequired()){
			j++;
			policy.append(j+") The password should have atleast one numeral digit{0-9}");
			policy.append("\n");
			policy.append("\t\t\t");
		}
		if(pcp.getCombinationPolicy().isNonAlphaNumericRequired()){
			j++;
			policy.append(j+") The password should have atleast one non alpha numeric character");
			policy.append("\n");
			policy.append("\t\t\t");
		}
		if(pcp.getCombinationPolicy().getSubStringExclusionLength()>0){
			j++;
			policy.append(j+") The password should not contain a substring of "+pcp.getCombinationPolicy().getSubStringExclusionLength()+"characters from user name");
			policy.append("\n");
			policy.append("\t\t\t");
		}
		
		
		
		return policy.toString();
	}

	public String publishPasswordPolicy(File xsltFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOrUpdatePolicy(PasswordPolicy passwordPolicy) throws Exception {
		/**
		 * Do what ever way it can be done
		 */
		
	}

	public boolean validatePasswordAgainstPolicy(PasswordPolicy passwordPolicy, Credential credential) throws ValidationException {
		PolicyValidator policyValidator = new PasswordCreationPolicyValidator();
		
		
		return policyValidator.validate(passwordPolicy, credential);
	}

}
