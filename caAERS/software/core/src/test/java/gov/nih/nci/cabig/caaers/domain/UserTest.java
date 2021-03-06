/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.util.Calendar;

import junit.framework.TestCase;

/**
 * @author Ram Seethiraju
 */

public class UserTest extends TestCase {
	private User user;
	
	protected void setUp() throws Exception {
		super.setUp();
		user =  new User();
	}

	public void testIsLocked_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		assertTrue(user.isPasswordLocked());
	}
	
	public void testIsLocked_CheckingFailure1() {
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(null);
		assertFalse(user.isPasswordLocked());
	}
	
	public void testIsLocked_CheckingFailure2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		assertFalse(user.isPasswordLocked());
	}
	
	public void testUnlock_CheckingSuccess() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		user.unlockPassword();
		assertTrue(user.getFailedLoginAttempts()==0);
		assertTrue(user.getLastFailedLoginAttemptTime()==null);
	}
	
	public void testUnlock_CheckingFailure1() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		user.unlockPassword();
		assertFalse(user.getFailedLoginAttempts()==-1);
		assertTrue(user.getLastFailedLoginAttemptTime()==null);
	}

	public void testUnlock_CheckingFailure2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -100);
		user.setFailedLoginAttempts(-1);
		user.setLastFailedLoginAttemptTime(cal.getTime());
		user.unlockPassword();
		assertTrue(user.getFailedLoginAttempts()==0);
		assertFalse(user.getLastFailedLoginAttemptTime()==cal.getTime());
	}

    public void testSync(){
        
       assertNull(user.getFirstName());
       assertNull(user.getLastName());
       assertNull(user.getMiddleName());
       assertTrue(user.getRoleMembershipMap().isEmpty());

       User x  = new User();
       x.setFirstName("x");
       x.setLastName("y");
       x.findRoleMembership(UserGroupType.ae_reporter).addOrganizationNCICode("xyz");

       user.sync(x);
       assertEquals("x", user.getFirstName());
       assertEquals("y", user.getLastName());

       assertTrue(user.findRoleMembership(UserGroupType.ae_reporter).getOrganizationNCICodes().contains("xyz"));


    }

}
