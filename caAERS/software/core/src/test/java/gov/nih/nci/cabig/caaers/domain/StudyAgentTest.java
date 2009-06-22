package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyAgentTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testEqualsObject() {
		StudyAgent sa1 = Fixtures.createStudyAgent("test");
		StudyAgent sa2 = Fixtures.createStudyAgent("test");
		assertTrue(sa1.equals(sa2));
	}
	
	public void testEqualsObject_WhenOneIsRetired() {
		StudyAgent sa1 = Fixtures.createStudyAgent("test");
		StudyAgent sa2 = Fixtures.createStudyAgent("test");
		sa2.retire();
		assertFalse(sa1.equals(sa2));
	}
	
	public void testEqualsObject_WhenBothAreRetired() {
		StudyAgent sa1 = Fixtures.createStudyAgent("test");
		sa1.retire();
		StudyAgent sa2 = Fixtures.createStudyAgent("test");
		sa2.retire();
		assertFalse(sa1.equals(sa2));
	}
	
	public void testEquals_AddingInSet_NoneRetired(){
		List<StudyAgent> dummyList = new ArrayList<StudyAgent>();
		Agent a1 = Fixtures.createAgent("abcd", dummyList);
		Agent a2 = Fixtures.createAgent("efg",dummyList);
		StudyAgent sa1 = Fixtures.createStudyAgent("abcd");
		sa1.setAgent(a1);
		StudyAgent sa2 = Fixtures.createStudyAgent("abcd");
		sa2.setAgent(a1);
		StudyAgent sa3 = Fixtures.createStudyAgent("efg");
		sa3.setAgent(a2);
		StudyAgent sa4 = Fixtures.createStudyAgent("efg");
		sa4.setAgent(a2);
		
		List<StudyAgent> agents = new ArrayList<StudyAgent>();
		agents.add(sa1);
		agents.add(sa2);
		agents.add(sa3);
		agents.add(sa4);
		
		assertEquals(4, agents.size());
		
		HashSet<StudyAgent> set = new HashSet<StudyAgent>(agents);
		assertEquals(2, set.size());
		
	}
	
	public void testEquals_AddingInSet_OneRetired(){
		List<StudyAgent> dummyList = new ArrayList<StudyAgent>();
		Agent a1 = Fixtures.createAgent("abcd", dummyList);
		Agent a2 = Fixtures.createAgent("efg",dummyList);
		StudyAgent sa1 = Fixtures.createStudyAgent("abcd");
		sa1.setAgent(a1);
		sa1.retire();
		StudyAgent sa2 = Fixtures.createStudyAgent("abcd");
		sa2.setAgent(a1);
		StudyAgent sa3 = Fixtures.createStudyAgent("efg");
		sa3.setAgent(a2);
		StudyAgent sa4 = Fixtures.createStudyAgent("efg");
		sa4.setAgent(a2);
		
		List<StudyAgent> agents = new ArrayList<StudyAgent>();
		agents.add(sa1);
		agents.add(sa2);
		agents.add(sa3);
		agents.add(sa4);
		
		assertEquals(4, agents.size());
		
		HashSet<StudyAgent> set = new HashSet<StudyAgent>(agents);
		assertEquals(3, set.size());
		
	}
	

	public void testEquals_AddingInSet_TwoRetired(){
		List<StudyAgent> dummyList = new ArrayList<StudyAgent>();
		Agent a1 = Fixtures.createAgent("abcd", dummyList);
		Agent a2 = Fixtures.createAgent("efg",dummyList);
		StudyAgent sa1 = Fixtures.createStudyAgent("abcd");
		sa1.setAgent(a1);
		sa1.retire();
		StudyAgent sa2 = Fixtures.createStudyAgent("abcd");
		sa2.setAgent(a1);
		sa2.retire();
		StudyAgent sa3 = Fixtures.createStudyAgent("efg");
		sa3.setAgent(a2);
		sa3.retire();
		StudyAgent sa4 = Fixtures.createStudyAgent("efg");
		sa4.setAgent(a2);
		
		List<StudyAgent> agents = new ArrayList<StudyAgent>();
		agents.add(sa1);
		agents.add(sa2);
		agents.add(sa3);
		agents.add(sa4);
		
		assertEquals(4, agents.size());
		
		HashSet<StudyAgent> set = new HashSet<StudyAgent>(agents);
		assertEquals(4, set.size());
		
	}
}
