package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ResearchStaffTest extends TestCase {
	ResearchStaff staff;
	protected void setUp() throws Exception {
		super.setUp();
		staff = new  LocalResearchStaff();
	}

	public void testIsAssociatedToUserGroup() {
		assertFalse(staff.isAssociatedToUserGroup(UserGroupType.caaers_admin));
	}
	
	public void testGetAllRoles(){
		assertTrue(staff.getAllRoles().isEmpty());
	}
	
	public void testGetSiteRolesMapping(){
		assertTrue(staff.getSiteRolesMapping().isEmpty());
	}
	
	public void testGetActiveDate(){
		assertNull(staff.getActiveDate());
	}

}
