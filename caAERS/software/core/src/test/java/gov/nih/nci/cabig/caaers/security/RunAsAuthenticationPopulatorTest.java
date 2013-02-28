/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
		assertTrue(populator.getAuthorities().contains("caaers_super_user"));
	}

}
