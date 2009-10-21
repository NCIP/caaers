package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.security.authentication.CommonAuthenticationManager;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.security.auth.Subject;

import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.CredentialsExpiredException;
import org.acegisecurity.DisabledException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.LockedException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

/**
 * @author Ram Seethiraju
 */
public class CaaersCSMAuthenticationProviderTest extends CaaersDbTestCase {

	private CaaersCSMAuthenticationProvider provider;
	private CaaersUser user;
	private UsernamePasswordAuthenticationToken token;
	private Credential credential;
	private String userName;
	private String password;
	private GrantedAuthority[] authorities;
	private Timestamp now;

	protected void setUp() throws Exception {
		super.setUp();
		now = new Timestamp(new Date().getTime());
		provider = (CaaersCSMAuthenticationProvider) getDeployedApplicationContext()
				.getBean("localAuthenticationProvider");
	}

	private void createToken(String userName, String password) {
		this.userName = userName;
		this.password = password;
		authorities = new GrantedAuthority[0];
		credential = new Credential(userName, password);
		user = new CaaersUser(userName, password, true, true, true, true,
				authorities);
		token = new UsernamePasswordAuthenticationToken(user, password,
				authorities);
	}

	private User loadUser() {
		// load the user and update the last password set time
		User user = provider.getUserDao().getByLoginId(token.getName());
		assertNotNull(user);
		return user;
	}

	private void saveUser(User user) {
		provider.getUserDao().save(user);
	}

	public void testLoading() {
		assertNotNull(provider);
	}

	/*
	 * 1. Testcase to check the ideal case in which no exceptions are thrown.
	 */
	public void testAdditionalAuthChecks_CheckingSuccess() {
		createToken("abcd", "xxx");
		{
			User user = loadUser();
			user.setPasswordLastSet(now);
			saveUser(user);
		}

		interruptSession();

		{
			provider
					.setCsmAuthenticationManager(new CommonAuthenticationManager() {
						@Override
						public Subject authenticate(String userName,
								String password) {
							return null;
						}
					});
			provider.additionalAuthenticationChecks(user, token);
		}

		interruptSession();

		{
			User user = loadUser();
			assertEquals(0, user.getFailedLoginAttempts());
			assertNull(user.getLastFailedLoginAttemptTime());
		}
	}

	/*
	 * 2. Testcase to check if BadCredentials exception is thrown if wrong user
	 * credentials are given.
	 */
	public void testAdditionalChecks_ThrowingBadCredentialsException()
			throws Exception {
		createToken("abcd", "xxx");
		{
			User user = loadUser();
			user.setPasswordLastSet(now);
			user.setFailedLoginAttempts(0);
			saveUser(user);
		}

		interruptSession();

		{
			provider
					.setCsmAuthenticationManager(new CommonAuthenticationManager() {
						@Override
						public Subject authenticate(String userName,
								String password) {
							if (true)
								throw new BadCredentialsException("test");
							return null;
						}
					});
		}

		try {
			provider.additionalAuthenticationChecks(user, token);
			fail("Should not reach here as the user credentials given are wrong");
		} catch (BadCredentialsException e) {
		}

		interruptSession();

		{
			User user = loadUser();
			assertEquals(1, user.getFailedLoginAttempts());
		}
	}

	/*
	 * 3. Testcase to check if CredentialsExpired exception is thrown if the
	 * password age has exceeded the threshold.
	 */
	public void testAdditionalChecks_ThrowingCredentialsExpiredException()
			throws Exception {
		createToken("abcd", "xxx");
		{
			User user = loadUser();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -3);
			user.setPasswordLastSet(new Timestamp(cal.getTime().getTime()));// last
																			// set
																			// 3days
																			// ago
			// LoginPolicy max password age is 2 days
			saveUser(user);
		}

		interruptSession();

		{
			provider
					.setCsmAuthenticationManager(new CommonAuthenticationManager() {
						@Override
						public Subject authenticate(String userName,
								String password) {
							return null;
						}
					});
		}

		try {
			provider.additionalAuthenticationChecks(user, token);
			fail("Should not reach here as the password age is too old");
		} catch (CredentialsExpiredException e) {
		}

		interruptSession();

		{
			User user = loadUser();
			assertEquals(0, user.getFailedLoginAttempts());
		}
	}

	/*
	 * 4. Testcase to check if Disabled exception is thrown if the number of
	 * failed login attempts has exceeded the threshold.
	 */
	public void testAdditionalChecks_ThrowingDisabledException()
			throws Exception {
		createToken("abcd", "xxx");
		{
			User user = loadUser();
			user.setPasswordLastSet(now);
			user.setFailedLoginAttempts(4);
			// Login Policy Allowed failed login attempts = 3
			saveUser(user);
		}

		interruptSession();

		{
			provider
					.setCsmAuthenticationManager(new CommonAuthenticationManager() {
						@Override
						public Subject authenticate(String userName,
								String password) {
							return null;
						}
					});
		}

		try {
			provider.additionalAuthenticationChecks(user, token);
			fail("Should not reach here as there are too many failed login attempts ");
		} catch (DisabledException e) {
		}

		interruptSession();

		{
			User user = loadUser();
			assertEquals(0, user.getFailedLoginAttempts());
		}
	}

	/*
	 * 5. Testcase to check if Locked exception is thrown if the number of
	 * failed login attempts has exceeded the threshold.
	 */
	public void testAdditionalChecks_ThrowingLockedException() throws Exception {
		createToken("abcd", "xxx");
		{
			User user = loadUser();
			user.setPasswordLastSet(now);
			user.setLastFailedLoginAttemptTime(now);
			// Login Policy Lockout duration = 3 minutes
			saveUser(user);
		}

		interruptSession();

		{
			provider
					.setCsmAuthenticationManager(new CommonAuthenticationManager() {
						@Override
						public Subject authenticate(String userName,
								String password) {
							return null;
						}
					});
		}

		try {
			provider.additionalAuthenticationChecks(user, token);
			fail("Should not reach here as the account is still locked ");
		} catch (LockedException e) {
		}
	}
}
