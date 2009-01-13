package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;

public class UserGroupTypeTest extends TestCase {
	
	private UserGroupType grpType = UserGroupType.caaers_physician;;
	
	public void testGetCsmName() {
		assertEquals("caaers_physician", grpType.getCsmName());
	}

	public void testGetByCode() {
		assertSame(grpType, UserGroupType.getByCode(-8));
	}

	public void testToString() {
		assertEquals("caaers_physician", grpType.toString());
	}

	public void testStrValues() {
		
		assertEquals(8, UserGroupType.strValues().length);
		assertEquals(grpType.toString(), UserGroupType.strValues()[7]);
	}
	
	public void testGetDisplayName(){
		assertEquals("Caaers physician", grpType.getDisplayName());
	}
	
	public void testGetCode(){
		assertEquals(-8, grpType.getCode().intValue());
	}
}
