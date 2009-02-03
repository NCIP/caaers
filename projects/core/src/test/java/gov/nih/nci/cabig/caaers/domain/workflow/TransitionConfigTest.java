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
