package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.security.passwordpolicy.ComplexityPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.HistoryPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.MinAgePolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.PasswordCreationPolicyValidator;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.PolicyValidator;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.ValidationException;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.xml.ObjectFormatter;

import java.io.File;

public class PasswordPolicyServiceImpl implements PasswordPolicyService{
	
	private String policyFile ="/vinaykumar/passwordpolicyinstance.xml";

	public PasswordPolicy getPasswordPolicy() throws Exception {
       ObjectFormatter of = new ObjectFormatter();
	    
	    Object obj = (Object)of.formatObject("passwordPolicyInstance.xml");
	    
	   
	    
	    
	    PasswordPolicy pp = (PasswordPolicy)obj;
	    
	    return pp;
		
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
		MinAgePolicy map = pcp.getMinAgePolicy();
		if(map!=null){
			i++;
		policy.append("\t");
		policy.append(i+") The password can't be changed before "+map.getAge()+" days from the day it is created");
		policy.append("\t");
		policy.append("\n");
		}
		
		HistoryPolicy hp = pcp.getHistoryPolicy();
		if(hp!=null){
			i++;
			policy.append("\t");
			policy.append(i+") The password can't be resused from past "+hp.getCount()+" passwords");
			policy.append("\t");
			policy.append("\n");
		}
		
		ComplexityPolicy cp = pcp.getComplexityPolicy();
		i++;
		policy.append("\t");
		policy.append(i+")Complexity Requirements:");
		policy.append("\n");
		policy.append("\t");
		policy.append("------------------------");
		policy.append("\n");
		policy.append("\t\t");
		policy.append("A) The password should be of minimum "+cp.getMinLengthPolicy().getLength()+" characters");
		policy.append("\n");
		policy.append("\t\t");
		policy.append("B) The password should meet atleast "+cp.getCombinationPolicy().getMinRequired()+" requirements from the following:");
		policy.append("\n");
		policy.append("\t\t\t");
		 
		int j=0;
		if(cp.getCombinationPolicy().isUpperCaseAlphabet()){
			j++;
			policy.append(j+") The password should have atleast one upper case letter");
			policy.append("\n");
			policy.append("\t\t\t");
		}
		if(cp.getCombinationPolicy().isLowercaseAlphabet()){
			j++;
			policy.append(j+") The password should have atleast one lower case letter");
			policy.append("\n");
			policy.append("\t\t\t");
			
		}
		if(cp.getCombinationPolicy().isBaseTenDigit()){
			j++;
			policy.append(j+") The password should have atleast one numeral digit{0-9}");
			policy.append("\n");
			policy.append("\t\t\t");
		}
		if(cp.getCombinationPolicy().isNonAlphaNumeric()){
			j++;
			policy.append(j+") The password should have atleast one non alpha numeric character");
			policy.append("\n");
			policy.append("\t\t\t");
		}
		if(cp.getCombinationPolicy().getSubStringExclusionPolicy()!=null){
			j++;
			policy.append(j+") The password should not contain a substring of "+cp.getCombinationPolicy().getSubStringExclusionPolicy().getSubStringLength()+"characters from user name");
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
		ObjectFormatter of = new ObjectFormatter();
		of.saveObject(passwordPolicy, policyFile);
		
	}

	public boolean validatePasswordAgainstPolicy(PasswordPolicy passwordPolicy, Credential credential) throws ValidationException {
		PolicyValidator policyValidator = new PasswordCreationPolicyValidator();
		
		
		return policyValidator.validate(passwordPolicy, credential);
	}

}
