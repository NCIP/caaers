package gov.nih.nci.cabig.caaers.domain.security.user;

import java.sql.Timestamp;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CaaersUserTest extends TestCase {
	CaaersUser user;
	protected void setUp() throws Exception {
		super.setUp();
		user = new CaaersUser();
	}

	public void testGetUser() {
		assertNull(user.getUser("test"));
	}

	public void testGetName() {
		user.setName("joel");
		assertEquals("joel", user.getName());
	}

	

	public void testGetSalt() {
		user.setSalt("abcd");
		assertEquals("abcd", user.getSalt());
	}

	

	public void testGetToken() {
		user.setToken("abcd");
		assertEquals("abcd", user.getToken());
	}


	public void testGetTokenTime() {
		Timestamp ts = new Timestamp(1000L);
		user.setTokenTime(ts);
		assertSame(ts, user.getTokenTime());
	}

	
	

	public void testAddPasswordToHistory() {
		user.addPasswordToHistory(4);
	}


	public void testSetFailedLoginAttempts() {
		user.setFailedLoginAttempts(4);
		assertEquals(4, user.getFailedLoginAttempts());
	}

	

	public void testIsPassword() {
		assertFalse(user.isPassword(""));
	}

}
