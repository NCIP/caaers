package gov.nih.nci.cabig.caaers.security;


import java.sql.Timestamp;
import java.util.Date;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyServiceImpl;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.security.authentication.CommonAuthenticationManager;
import gov.nih.nci.security.authentication.LockoutManager;

import javax.security.auth.Subject;

import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.GrantedAuthority;
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
		provider = (CaaersCSMAuthenticationProvider)getDeployedApplicationContext().getBean("localAuthenticationProvider");
	}
	
	private void createToken(String userName, String password){
		this.userName = userName;
		this.password = password;
		authorities = new GrantedAuthority[0];
		credential = new Credential(userName, password);
		user = new CaaersUser(userName, password, true, true, true, true, authorities);
		token = new UsernamePasswordAuthenticationToken(user, password, authorities);
	}
	
	private User loadUser(){
		//load the user and update the last password set time
		User user = provider.getUserDao().getByLoginId(token.getName());
		assertNotNull(user);
		return user;
	}
	private void saveUser(User user){
		provider.getUserDao().save(user);
	}
	
	public void testLoading() {
		assertNotNull(provider);
	}
	
	/*
	 * 1.  
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
			provider.setCsmAuthenticationManager(new CommonAuthenticationManager(){
				@Override
				public Subject authenticate(String userName, String password) {
					return null;
				}
			});
			provider.additionalAuthenticationChecks(user, token);
		}
		interruptSession();
		{
			User user = loadUser();
			assertEquals(0, user.getFailedLoginAttempts());
			assertEquals(0, DateUtils.compareDate(DateUtils.today(), user.getLastLoginAttemptTime()));
		}
		
	}
	
	/*
	 * 2. 
	 */
	public void testAdditionalChecks_ThrowingAuthenticationException() throws Exception{
		{
			//get provider
			provider.setCsmAuthenticationManager(new CommonAuthenticationManager(){
				@Override
				public Subject authenticate(String userName, String password) {
						if(true) throw new BadCredentialsException("test");
					return null;
				}
			});
		}
		
		provider.additionalAuthenticationChecks(user, token);
		interruptSession();
		{
			//load the user
			//check for correct values. 
		}
	}
	
	public void testLockoutManagerLoading(){
        LockoutManager lockoutManager = (LockoutManager) getDeployedApplicationContext().getBean("csmLockoutManager");
        assertNotNull(lockoutManager);

        //fail the test user 10 times
        for(int i = 0; i < 10; i++) {
            lockoutManager.setFailedAttempt("test");
        }
        
        //since lockout is disabled, should not lockout user "test"
        assertFalse(lockoutManager.isUserLockedOut("test"));        
    }
}
