package gov.nih.nci.cabig.caaers.security;


import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.security.authentication.LockoutManager;

public class CaaersCSMAuthenticationProviderTest extends CaaersTestCase {

	//private CaaersCSMAuthenticationProvider provider;
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testHere() {
		//assertNotNull(provider);
	}	
	
	public void testLockoutManagerLoading(){
        LockoutManager lockoutManager = (LockoutManager) getDeployedApplicationContext().getBean("csmLockoutManager");
        assertNotNull(lockoutManager);

        //fail the test user 10 times
        for(int i = 0; i < 3; i++) {
            lockoutManager.setFailedAttempt("test");
        }
        
        //since lockout is disabled, should not lockout user "test"
        assertFalse(lockoutManager.isUserLockedOut("test"));
    }
}
