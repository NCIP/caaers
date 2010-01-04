package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AgentTest extends TestCase {
	Agent a1;
	Agent a2;
	protected void setUp() throws Exception {
		super.setUp();
		a1 = Fixtures.createAgent("test",new ArrayList<StudyAgent>());
		a2 = Fixtures.createAgent("test",new ArrayList<StudyAgent>());
		a2.setNscNumber("123");
	}

	public void testGetDisplayName() {
		assertEquals("test", a1.getDisplayName());
		assertEquals("123::test", a2.getDisplayName());
	}

}
