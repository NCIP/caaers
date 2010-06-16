package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import junit.framework.TestCase;

import java.util.Date;

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
        Organization org = Fixtures.createOrganization("test");
        SiteResearchStaff siteResearchStaff1 = Fixtures.createSiteResearchStaff(org, staff);
        siteResearchStaff1.addSiteResearchStaffRole(Fixtures.createSiteResearchStaffRole("abcd", new Date(), null));
        SiteResearchStaff siteResearchStaff2 = Fixtures.createSiteResearchStaff(org, staff);
        siteResearchStaff2.addSiteResearchStaffRole(Fixtures.createSiteResearchStaffRole("efg",  DateUtils.yesterday(), DateUtils.yesterday()));

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
	
    public void testGetActiveSiteResearchStaff(){
        assertEquals(2, staff.getSiteResearchStaffs().size());
        assertEquals(1, staff.getActiveSiteResearchStaff().size());
        assertEquals("abcd", staff.getActiveSiteResearchStaff().get(0).getSiteResearchStaffRoles().get(0).getRoleCode());
    }

}
