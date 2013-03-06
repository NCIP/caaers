/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.workflow;

import junit.framework.TestCase;

public class TransitionConfigTest extends TestCase {
	TransitionConfig tranConf;
	protected void setUp() throws Exception {
		super.setUp();
		tranConf = new TransitionConfig();
	}

	public void testAddTransitionOwner() {
		tranConf.addTransitionOwner(new PersonTransitionOwner());
		assertNotNull(tranConf.getOwners());
		assertEquals(1, tranConf.getOwners().size());
	}

}
