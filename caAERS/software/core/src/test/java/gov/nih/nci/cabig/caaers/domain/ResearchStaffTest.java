package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

/**
 * 
 * @author Biju Joseph
 *
 */
public class ResearchStaffTest extends AbstractTestCase {
	ResearchStaff staff;
	protected void setUp() throws Exception {
		super.setUp();
		staff = new  LocalResearchStaff();
        Organization org = Fixtures.createOrganization("test");
        SiteResearchStaff siteResearchStaff1 = Fixtures.createSiteResearchStaff(org, staff);
        SiteResearchStaff siteResearchStaff2 = Fixtures.createSiteResearchStaff(org, staff);
        siteResearchStaff2.setEndDate(DateUtils.yesterday());
        siteResearchStaff2.setStartDate(DateUtils.yesterday());

	}


    public void testGetActiveSiteResearchStaff(){
        assertEquals(2, staff.getSiteResearchStaffs().size());
        assertEquals(1, staff.getActiveSiteResearchStaff().size());
    }

    public void testSync(){
        ResearchStaff rs = new LocalResearchStaff();
        rs.setFirstName("fn");
        rs.setMiddleName("mn");
        rs.setLastName("ln");
        rs.setTitle("mr");
        rs.setPhoneNumber("1");
        rs.setFaxNumber("2");
        rs.setEmailAddress("a@a.com");
        rs.setNciIdentifier("nci");
        rs.setAddress(new Address());
        rs.getAddress().setCountry("IN");
        rs.setCaaersUser(Fixtures.createUser("x", "y"));

        staff.setCaaersUser(Fixtures.createUser("x","hello"));
        staff.sync(rs);


        assertEquals("fn", staff.getFirstName());
        assertEquals("mn", staff.getMiddleName());
        assertEquals("ln", staff.getLastName());
        assertEquals("mr", staff.getTitle());
        assertEquals("1", staff.getPhoneNumber());
        assertEquals("2", staff.getFaxNumber());
        assertEquals("a@a.com", staff.getEmailAddress());
        assertEquals("nci", staff.getNciIdentifier());
        assertEquals("IN", staff.getAddress().getCountry());
        assertEquals("y", staff.getCaaersUser().getFirstName());

    }

}
