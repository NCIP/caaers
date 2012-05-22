package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersContextLoader;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;

import java.util.List;

/**
 * 
 * @author Monish
 *
 */
public class UserCommandTest extends AbstractTestCase {

	private UserCommand command;

    @Override
    protected void setUp() throws Exception {
        CaaersContextLoader.getApplicationContext().getBean("Messages");  //initialize messages
        super.setUp();
        switchToSuperUser();
        command = new UserCommand();
    }

    public void testGetAllGlobalRoles(){
		
		List<SuiteRole> globalRoles = command.getAllGlobalRoles();

		assertEquals(4,globalRoles.size());
		for(SuiteRole role : globalRoles){
			assertFalse(role.isScoped());
		}
	}
	
	
	public void testGetAllSiteScopedRoles(){
		
		List<SuiteRole> siteScopedRoles = command.getAllSiteScopedRoles();

		assertEquals(19,siteScopedRoles.size());
		for(SuiteRole role : siteScopedRoles){
			assertTrue(role.isSiteScoped());
		}
	}

	
	public void testGetAllSiteAndStudyScopedRoles(){
		
		List<SuiteRole> siteAndStudyScopedRoles = command.getAllStudyScopedRoles();

		assertEquals(10,siteAndStudyScopedRoles.size());
		for(SuiteRole role : siteAndStudyScopedRoles){
			assertTrue(role.isStudyScoped());
		}
	}
	
	
	public void testAddOneSuiteRoleMembership(){
		ProvisioningSessionFactory factory = new ProvisioningSessionFactory();
		command.addRoleMembership(factory.createSuiteRoleMembership(SuiteRole.AE_REPORTER));
		assertNotNull(command.getRoleMemberships());
		assertEquals(1, command.getRoleMemberships().size());
		assertTrue(command.getRoleMemberships().get(0).getRole().equals(SuiteRole.AE_REPORTER));
	}
	
	
	public void testAddManySuiteRoleMembership(){
		ProvisioningSessionFactory factory = new ProvisioningSessionFactory();
		command.addRoleMembership(factory.createSuiteRoleMembership(SuiteRole.AE_REPORTER));
		command.addRoleMembership(factory.createSuiteRoleMembership(SuiteRole.BUSINESS_ADMINISTRATOR));
		command.addRoleMembership(factory.createSuiteRoleMembership(SuiteRole.REGISTRAR));
		
		assertNotNull(command.getRoleMemberships());
		assertEquals(3, command.getRoleMemberships().size());
	}

}
