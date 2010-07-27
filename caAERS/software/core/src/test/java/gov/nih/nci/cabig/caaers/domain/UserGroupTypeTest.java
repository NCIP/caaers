package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class UserGroupTypeTest extends TestCase {
	
	private UserGroupType grpType = UserGroupType.ae_reporter;;
	
	public void testGetCsmName() {
		assertEquals("ae_reporter", grpType.getCsmName());
	}

	public void testGetByCode() {
		assertSame(grpType, UserGroupType.getByCode(-117));
	}
	
	public void testGetByCode_WrongCode() {
		assertNull(UserGroupType.getByCode(-87777777));
	}

	public void testToString() {
		assertEquals("ae_reporter", grpType.toString());
	}


	
	public void testGetDisplayName(){
		assertEquals("Ae Reporter", grpType.getDisplayName());
	}
	
	public void testGetCode(){
		assertEquals(-117, grpType.getCode().intValue());
	}

    public void testValueOf(){
        assertEquals(UserGroupType.ae_reporter, UserGroupType.valueOf("ae_reporter"));
    }
	
	
}
