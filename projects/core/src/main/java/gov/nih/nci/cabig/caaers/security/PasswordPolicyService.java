package gov.nih.nci.cabig.caaers.security;

import java.io.File;

import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.ValidationException;

public interface PasswordPolicyService {
	/**
	 * This method will return the stored password poicy 
	 * from xml configuration file
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	public PasswordPolicy getPasswordPolicy() throws Exception;
	
	/**
	 * This method serializes the PasswordPolicy Object to xml file
	 * and updates any cached PasswordPolicy Object
	 * @param passwordPolicy
	 * @throws Exception
	 */
	
	public void saveOrUpdatePolicy(PasswordPolicy passwordPolicy) throws Exception;
	
	/**
	 * This method will return a string in a readble format.
	 * @return
	 * @throws Exception
	 */
	
	public String publishPasswordPolicy();
	
	/**
	 * This method will apply to xslt to the password policy xml file and
	 * return in the desired format. This can be very useful when publishing the 
	 * password policy on web pages for users
	 * @param xsltFile
	 * @return
	 * @throws Exception
	 */
	
	public String publishPasswordPolicy(File xsltFile) throws Exception;
	
	/**
	 * This method will return
	 * @param passwordPolicy
	 * @param credential
	 * @return
	 * @throws ValidationException
	 */
	public boolean validatePasswordAgainstPolicy(PasswordPolicy passwordPolicy, Credential credential) throws ValidationException;
	
	
	

}
