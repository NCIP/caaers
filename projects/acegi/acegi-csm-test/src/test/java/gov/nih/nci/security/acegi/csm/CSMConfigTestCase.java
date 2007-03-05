package gov.nih.nci.security.acegi.csm;

import java.io.File;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.User;

public class CSMConfigTestCase extends AbstractCSMTestCase {

    public CSMConfigTestCase() {
        super();
    }
    
    public CSMConfigTestCase(String name){
        super(name);
    }
    
    public String[] getConfigLocations(){
        return new String[] { "classpath:applicationContext-csm.xml" };
    }
    
    public void testLoadUserProvisioningManager() {
        try {
            UserProvisioningManager mgr = (UserProvisioningManager) getApplicationContext()
                            .getBean("testCsmUserProvisioningManager");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }

    public void testLoadAuthenticationManager() {
        try {
            AuthenticationManager mgr = (AuthenticationManager) getApplicationContext().getBean(
                            "testCsmAuthenticationManager");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }
    
    public void testLoadUser() {
        try {
            UserProvisioningManager mgr = (UserProvisioningManager) getApplicationContext()
                            .getBean("testCsmUserProvisioningManager");
            mgr.setEncryptionEnabled(false);
            User user = mgr.getUser("user2");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }
    
    public void testGetGroups() {
        try {
            UserProvisioningManager mgr = (UserProvisioningManager) getApplicationContext()
                            .getBean("testCsmUserProvisioningManager");
            mgr.setEncryptionEnabled(false);
            User user = mgr.getUser("user2");
            mgr.getGroups(user.getUserId().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }
    
    public void testAuthenticate(){
    	try {
    		File jaasConfig = new File("src/test/resources/jaas.config");
    		System.setProperty("java.security.auth.login.config", jaasConfig.getAbsolutePath());
    		
            AuthenticationManager mgr = (AuthenticationManager) getApplicationContext()
                            .getBean("testCsmAuthenticationManager");
            assertTrue(mgr.login("user1", "user1"));
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }

}
