/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.PersonRole;
import junit.framework.TestCase;

public class RoleTransitionOwnerTest extends TestCase {
	RoleTransitionOwner ra =  new RoleTransitionOwner();
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testIsRole() {
		assertTrue(ra.isRole());
	}

	public void testIsUser() {
		assertFalse(ra.isPerson());
	}

	public void testGetUserRole() {
		ra.setUserRole(PersonRole.ADVERSE_EVENT_COORDINATOR);
		assertSame(PersonRole.ADVERSE_EVENT_COORDINATOR, ra.getUserRole());
	}

	public void testSetUserRole() {
		ra.setUserRole(PersonRole.ADVERSE_EVENT_COORDINATOR);
		assertSame(PersonRole.ADVERSE_EVENT_COORDINATOR, ra.getUserRole());
	}
	
	public void testSetName() {
		ra.setName("joel");
		assertEquals("joel", ra.getName());
	}

	public void testGetName() {
		ra.setName("joel");
		assertEquals("joel", ra.getName());
	}
}
