package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class UserGroupTypeTest extends TestCase {
	
	private UserGroupType grpType = UserGroupType.caaers_physician;;
	
	public void testGetCsmName() {
		assertEquals("caaers_physician", grpType.getCsmName());
	}

	public void testGetByCode() {
		assertSame(grpType, UserGroupType.getByCode(-8));
	}
	
	public void testGetByCode_WrongCode() {
		assertNull(UserGroupType.getByCode(-87777777));
	}

	public void testToString() {
		assertEquals("caaers_physician", grpType.toString());
	}

	public void testStrValues() {
		
		assertEquals(10, UserGroupType.strValues().length);
		assertEquals(grpType.toString(), UserGroupType.strValues()[7]);
	}
	
	public void testGetDisplayName(){
		assertEquals("Caaers physician", grpType.getDisplayName());
	}
	
	public void testGetCode(){
		assertEquals(-8, grpType.getCode().intValue());
	}

    public void testValueOf(){
        assertEquals(UserGroupType.caaers_physician, UserGroupType.valueOf("caaers_physician"));
    }
	
	public void testCodes(){
		int[] codes = UserGroupType.codes();
		assertEquals(10, codes.length);
		assertEquals(-2, codes[0]);
		assertEquals(-8, codes[7]);
		assertEquals(-7942, codes[9]);
	}
}
