package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.context.SecurityContextHolder;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class RunAsAuthenticationPopulatorTest extends CaaersTestCase {

	RunAsAuthenticationPopulator populator;
	
	protected void setUp() throws Exception {
		super.setUp();
		populator = (RunAsAuthenticationPopulator) getDeployedApplicationContext().getBean("runAsAuthenticationPopulator");
	}

	public void testPopulate() {
		populator.populate();
		assertNotNull(SecurityContextHolder.getContext().getAuthentication());
		assertNotNull("SYSTEM", SecurityUtils.getUserLoginName());
	}

	public void testGetUsername() {
		assertEquals("SYSTEM", populator.getUsername());
	}

	public void testGetAuthorities() {
		assertTrue(populator.getAuthorities().contains("ROLE_caaers_super_user"));
		assertTrue(populator.getAuthorities().contains("ROLE_caaers_admin"));
		assertTrue(populator.getAuthorities().contains("ROLE_caaers_grid_user"));
	}

}
