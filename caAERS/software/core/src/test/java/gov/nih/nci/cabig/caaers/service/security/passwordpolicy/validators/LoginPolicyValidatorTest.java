/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;

import java.sql.Timestamp;
import java.util.Calendar;

import junit.framework.TestCase;

import org.acegisecurity.CredentialsExpiredException;
import org.acegisecurity.DisabledException;
import org.acegisecurity.LockedException;

/**
 * @author Ram Seethiraju
 */
public class LoginPolicyValidatorTest extends TestCase {

	private LoginPolicyValidator loginPolicyValidator;
	private PasswordPolicy passwordPolicy;
	private LoginPolicy loginPolicy;
	private Credential credential;
	private _User user;	
	private String userName;    
	private String password;
	
	protected void setUp() throws Exception {
		super.setUp();
		userName = "xyz";
		password = "Abcdef1!";
		user = new _User();
		credential = new Credential(userName, password);
		credential.setUser(user);
		loginPolicyValidator = new LoginPolicyValidator();
		loginPolicy = new LoginPolicy();
		loginPolicy.setAllowedFailedLoginAttempts(3); // 3 attempts
		loginPolicy.setMaxPasswordAge(180800); // 2 days
		loginPolicy.setLockOutDuration(180); // 3 minutes
		passwordPolicy = new PasswordPolicy();
		passwordPolicy.setLoginPolicy(loginPolicy);
	}
	
	public void testForFailedLoginAttempts_CheckingSuccess() {		
		user.setFailedLoginAttempts(1);
		assertTrue(loginPolicyValidator.validateAllowedFailedLoginAttempts(loginPolicy, credential));
	}
	public void testForFailedLoginAttempts_CheckingFailure1() {		
		user.setFailedLoginAttempts(4);
		try {
			loginPolicyValidator.validateAllowedFailedLoginAttempts(loginPolicy, credential);
			fail("Testcase Failed: AllowedFailedLoginAttemps limit reached but exception was not thrown");
		} catch (DisabledException e) {
		}
	}
	public void testForFailedLoginAttempts_CheckingFailure2() {		
		user.setFailedLoginAttempts(3);
		try {
			loginPolicyValidator.validateAllowedFailedLoginAttempts(loginPolicy, credential);
			fail("Testcase Failed: AllowedFailedLoginAttemps limit reached but exception was not thrown");
		} catch (DisabledException e) {
		}
	}
	
	public void testForMaxPasswordAge_CheckingSuccess1() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);// last set today
		user.setPasswordLastSet(new Timestamp(cal.getTime().getTime()));
		assertTrue(loginPolicyValidator.validateMaxPasswordAge(loginPolicy, credential));
	}
	
	public void testForMaxPasswordAge_CheckingSuccess2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);// last set 2 days ago
		user.setPasswordLastSet(new Timestamp(cal.getTime().getTime()));
		assertTrue(loginPolicyValidator.validateMaxPasswordAge(loginPolicy, credential));
	}
	
	public void testForMaxPasswordAge_CheckingFailure() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -3);//  last set 3 days ago
		user.setPasswordLastSet(new Timestamp(cal.getTime().getTime()));// last set 3days ago
		try {
			loginPolicyValidator.validateMaxPasswordAge(loginPolicy, credential);
			fail("Testcase Failed: MaxPasswordAge limit reached but exception was not thrown.");
		} catch (CredentialsExpiredException e) {
		}
	}
	
	public void testForLockOutDuration_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -4); // last attempt made 4 minute ago
		user.setLastFailedLoginAttemptTime(cal.getTime());
		assertTrue(loginPolicyValidator.validateLockOutDuration(loginPolicy, credential));
	}
	public void testForLockOutDuration_CheckingFailure1() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1); // last attempt made 1 minute ago
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		try {
			loginPolicyValidator.validateLockOutDuration(loginPolicy, credential);
			fail("Testcase Failed: LockOutDuration limit has not been reached but exception was not thrown.");
		} catch (LockedException e) {
		}
	}
	
	public void testValidateMethod_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -6);  // last attempt made 4 minute ago
		user.setLastFailedLoginAttemptTime(cal.getTime());
		user.setFailedLoginAttempts(1); // 1 failed attempt
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -1);// last set 1 day ago
		user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
		assertTrue(loginPolicyValidator.validate(passwordPolicy, credential, null));
	}
	public void testValidateMethod_CheckingFailure1() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -4);  // last attempt made 4 minute ago
			user.setLastFailedLoginAttemptTime(cal.getTime());
			user.setFailedLoginAttempts(1); // 1 failed attempt
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -3);// last set 3 days ago
			user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
			loginPolicyValidator.validate(passwordPolicy, credential, null);
		} catch (CredentialsExpiredException e) {
		}
	}
	public void testValidateMethod_CheckingFailure2() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -4);  // last attempt made 4 minute ago
			user.setLastFailedLoginAttemptTime(cal.getTime());
			user.setFailedLoginAttempts(4); // 4 failed attempts
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -1);// last set 1 day ago
			user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
			loginPolicyValidator.validate(passwordPolicy, credential, null);
		} catch (DisabledException e) {
		}
	}
	public void testValidateMethod_CheckingFailure3() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -2);  // last attempt made 4 minute ago
			user.setLastFailedLoginAttemptTime(cal.getTime());
			user.setFailedLoginAttempts(1); // 1 failed attempt
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -1);// last set 1 day ago
			user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
			loginPolicyValidator.validate(passwordPolicy, credential, null);
		} catch (LockedException e) {
		}
	}
}
