package gov.nih.nci.cabig.caaers.domain;

import java.util.Calendar;

import junit.framework.TestCase;

/**
 * @author Ram Seethiraju
 */

public class _UserTest extends TestCase {
	private _User user;	
	
	protected void setUp() throws Exception {
		super.setUp();
		user =  new _User();
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

    public void testSync(){
        
       assertNull(user.getFirstName());
       assertNull(user.getLastName());
       assertNull(user.getMiddleName());
       assertTrue(user.getRoleMembershipMap().isEmpty());

       _User x  = new _User();
       x.setFirstName("x");
       x.setLastName("y");
       x.findRoleMembership(UserGroupType.ae_reporter).addOrganizationNCICode("xyz");

       user.sync(x);
       assertEquals("x", user.getFirstName());
       assertEquals("y", user.getLastName());

       assertTrue(user.findRoleMembership(UserGroupType.ae_reporter).getOrganizationNCICodes().contains("xyz"));


    }

}
