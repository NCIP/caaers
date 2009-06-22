package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ChemoAgentTest extends AbstractTestCase {
	
	
	ChemoAgent a1;
	ChemoAgent a2;
	
	protected void setUp() throws Exception {
		super.setUp();
		a1 = Fixtures.createChemoAgent("a1", "b1");
		a2 = Fixtures.createChemoAgent("a2", "b2");
	}

	public void testGetName() {
		assertEquals("a1", a1.getName());
	}

	public void testSetName() {
		a1.setName("a11");
		assertEquals("a11", a1.getName());
	}

	public void testGetGenericName() {
		assertEquals("b1", a1.getGenericName());
	}

	public void testSetGenericName() {
		a1.setGenericName("b11");
		assertEquals("b11", a1.getGenericName());
	}

	public void testGetFullName() {
		assertEquals("a1 (b1)", a1.getFullName());
	}
	
	public void testToString(){
		assertEquals("a1 (b1)", a1.toString());
	}
	public void testEqualsObject() {
		assertFalse(a1.equals(a2));
		a1.setGenericName("b2");
		a1.setName("a2");
		assertTrue(a1.equals(a2));
	}

}
