package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.service.security.user.Credential;

import java.util.Arrays;
import java.util.Calendar;

import junit.framework.TestCase;

/**
 * @author Ram Seethiraju
 */
public class UserTest extends TestCase {

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
	}

	public void testIsLocked_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		assertTrue(user.isLocked());
	}
	
	public void testIsLocked_CheckingFailure1() {
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(null);
		assertFalse(user.isLocked());
	}
	
	public void testIsLocked_CheckingFailure2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		assertFalse(user.isLocked());
	}
	
	public void testUnlock_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		user.unlock();
		assertTrue(user.getFailedLoginAttempts()==0);
		assertTrue(user.getLastFailedLoginAttemptTime()==null);
	}
	
	public void testUnlock_CheckingFailure1() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		user.unlock();
		assertFalse(user.getFailedLoginAttempts()==-1);
		assertTrue(user.getLastFailedLoginAttemptTime()==null);
	}

	public void testUnlock_CheckingFailure2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		user.unlock();
		assertTrue(user.getFailedLoginAttempts()==0);
		assertFalse(user.getLastFailedLoginAttemptTime()==cal.getTime());
	}

}
