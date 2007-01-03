package gov.nih.nci.cabig.caaers.web;

import org.easymock.EasyMock;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSException;


public class LoginCommandTest extends WebTestCase {
    private static final String USERNAME = "alice";
    private static final String PASSWORD = "wonderland";
    private static final String IPADDRESS = "123.0.0.1";    
	
	private LoginCommand loginCommand;

    private AuthenticationManager authenticationManager;

    protected void setUp() throws Exception {
        super.setUp();
        authenticationManager = registerMockFor(AuthenticationManager.class);
        loginCommand = new LoginCommand();
        loginCommand.setAuthenticationManager(authenticationManager);
        loginCommand.setUsername(USERNAME);
        loginCommand.setPassword(PASSWORD);
    }

    public void testLoginSuccessfulWhenSuccessful() throws Exception {
        EasyMock.expect(authenticationManager.login(USERNAME, PASSWORD)).andReturn(true);
        replayMocks();
        assertTrue(loginCommand.login(IPADDRESS));
        verifyMocks();
    }

    public void testLoginFailsWhenFails() throws Exception {
    	EasyMock.expect(authenticationManager.login(USERNAME, PASSWORD)).andReturn(false);
        replayMocks();
        assertFalse(loginCommand.login(IPADDRESS));
        verifyMocks();
    }
    
    public void testLoginFailsWhenException() throws Exception {
    	EasyMock.expect(authenticationManager.login(USERNAME, PASSWORD)).andThrow(new CSException());
        replayMocks();
        assertFalse(loginCommand.login(IPADDRESS));
        verifyMocks();
    }
}