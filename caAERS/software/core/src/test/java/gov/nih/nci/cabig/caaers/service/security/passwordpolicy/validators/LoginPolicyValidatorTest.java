package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;

import junit.framework.TestCase;

/**
 * @author Ram Seethiraju
 */
public class LoginPolicyValidatorTest extends TestCase {

	private LoginPolicyValidator loginPolicyValidator;
	private PasswordPolicy passwordPolicy;
	private LoginPolicy loginPolicy;
	private Credential credential;
	private User user;	
	private String userName;    
	private String password;
	
	protected void setUp() throws Exception {
		super.setUp();
		userName = "xyz";
		password = "Abcdef1!";  
		credential = new Credential(userName, password);
		Organization org = Fixtures.createOrganization("test");        
		user =  Fixtures.createResearchStaff(org, Arrays.asList(new UserGroupType[] {UserGroupType.caaers_admin, UserGroupType.caaers_ae_cd}) , "Test");
		credential.setUser(user);
		loginPolicyValidator = new LoginPolicyValidator();
		loginPolicy = new LoginPolicy();
		loginPolicy.setAllowedFailedLoginAttempts(3); // 3 attempts
		loginPolicy.setMaxPasswordAge(172800); // 2 days
		loginPolicy.setLockOutDuration(180); // 3 minutes
		passwordPolicy = new PasswordPolicy();
		passwordPolicy.setLoginPolicy(loginPolicy);
	}
	
	public void testForFailedLoginAttempts_CheckingSuccess() {		
		user.setFailedLoginAttempts(2);
		assertTrue(loginPolicyValidator.validateAllowedFailedLoginAttempts(loginPolicy, credential));
	}
	public void testForFailedLoginAttempts_CheckingFailure1() {		
		user.setFailedLoginAttempts(4);
		try {
			loginPolicyValidator.validateAllowedFailedLoginAttempts(loginPolicy, credential);
			fail("Testcase Failed: AllowedFailedLoginAttemps limit reached but exception was not thrown");
		} catch (TooManyAllowedFailedLoginAttemptsException e) {
		}
	}
	public void testForFailedLoginAttempts_CheckingFailure2() {		
		user.setFailedLoginAttempts(3);
		try {
			loginPolicyValidator.validateAllowedFailedLoginAttempts(loginPolicy, credential);
			fail("Testcase Failed: AllowedFailedLoginAttemps limit reached but exception was not thrown");
		} catch (TooManyAllowedFailedLoginAttemptsException e) {
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
		} catch (PasswordTooOldException e) {
		}
	}
	
	public void testForLockOutDuration_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -4); // last attempt made 4 minute ago
		user.setLastLoginAttemptTime(cal.getTime());
		assertTrue(loginPolicyValidator.validateLockOutDuration(loginPolicy, credential));
	}
	public void testForLockOutDuration_CheckingFailure1() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1); // last attempt made 1 minute ago
		user.setLastLoginAttemptTime(cal.getTime());
		try {
			loginPolicyValidator.validateLockOutDuration(loginPolicy, credential);
			fail("Testcase Failed: LockOutDuration limit has not been reached but exception was not thrown.");
		} catch (UserLockedOutException e) {
		}
	}
	
	public void testValidateMethod_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -4);  // last attempt made 4 minute ago
		user.setLastLoginAttemptTime(cal.getTime());
		user.setFailedLoginAttempts(2); // 2 failed attempts
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -1);// last set 1 day ago
		user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
		assertTrue(loginPolicyValidator.validate(passwordPolicy, credential));
	}
	public void testValidateMethod_CheckingFailure1() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -4);  // last attempt made 4 minute ago
			user.setLastLoginAttemptTime(cal.getTime());
			user.setFailedLoginAttempts(2); // 2 failed attempts
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -3);// last set 3 days ago
			user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
			loginPolicyValidator.validate(passwordPolicy, credential);
		} catch (PasswordTooOldException e) {
		}
	}
	public void testValidateMethod_CheckingFailure2() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -4);  // last attempt made 4 minute ago
			user.setLastLoginAttemptTime(cal.getTime());
			user.setFailedLoginAttempts(4); // 2 failed attempts
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -1);// last set 1 day ago
			user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
			loginPolicyValidator.validate(passwordPolicy, credential);
		} catch (TooManyAllowedFailedLoginAttemptsException e) {
		}
	}
	public void testValidateMethod_CheckingFailure3() {
		try {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -2);  // last attempt made 4 minute ago
			user.setLastLoginAttemptTime(cal.getTime());
			user.setFailedLoginAttempts(2); // 2 failed attempts
			Calendar cal1 = Calendar.getInstance();
			cal1.add(Calendar.DATE, -1);// last set 1 day ago
			user.setPasswordLastSet(new Timestamp(cal1.getTime().getTime()));
			loginPolicyValidator.validate(passwordPolicy, credential);
		} catch (UserLockedOutException e) {
		}
	}
}
