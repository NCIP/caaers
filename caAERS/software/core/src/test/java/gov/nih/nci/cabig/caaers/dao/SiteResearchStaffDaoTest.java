package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;

import java.util.List;

public class SiteResearchStaffDaoTest extends DaoNoSecurityTestCase<SiteResearchStaffDao> {
	
	private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
    private ResearchStaffDao researchStaffDao = (ResearchStaffDao) getApplicationContext().getBean("researchStaffDao");

	public void testGetOrganizationResearchStaff() {
		Organization organization = organizationDao.getById(-1000);
		ResearchStaff researchStaff = researchStaffDao.getById(-1000);
		SiteResearchStaff siteResearchStaff = getDao().getOrganizationResearchStaff(organization, researchStaff);
		assertNotNull(siteResearchStaff);
		assertEquals("Park Centre Road", siteResearchStaff.getAddress().getStreet());
		assertEquals("Fairfax", siteResearchStaff.getAddress().getCity());
		assertEquals("Virginia", siteResearchStaff.getAddress().getState());
		assertEquals("22033", Integer.toString(siteResearchStaff.getAddress().getZip()));
		assertEquals("321-123-3214", siteResearchStaff.getPhoneNumber());
		assertEquals("666-777-1234", siteResearchStaff.getFaxNumber());
		assertEquals("fairfax@rest.com", siteResearchStaff.getEmailAddress());
		assertNotNull(siteResearchStaff.getSiteResearchStaffRoles());
		assertEquals(3,siteResearchStaff.getSiteResearchStaffRoles().size());
	}

	public void testGetOrganizationResearchStaffs() {
		Organization organization = organizationDao.getById(-1000);
		List<SiteResearchStaff> siteResearchStaffList = getDao().getOrganizationResearchStaffs(organization);
		assertNotNull(siteResearchStaffList);
		assertEquals(3, siteResearchStaffList.size());
	}

	public void testGetBySubnames() {
        Organization o = organizationDao.getById(-1001);
        List<SiteResearchStaff> siteResearchStaffList = getDao().getBySubnames(new String[] {"JF"}, o.getId());
        assertNotNull(siteResearchStaffList);
        assertEquals(1, siteResearchStaffList.size());
        assertEquals("123-456-789", siteResearchStaffList.get(0).getResearchStaff().getPhoneNumber());
	}

	public void testGetByNciIdentifier() {
        Organization o = organizationDao.getById(-1001);
        List<SiteResearchStaff> all = getDao().getByNciIdentifier(new String[] {"nci_id_4"}, o.getId());
        assertNotNull(all);
        assertEquals(1, all.size());
        assertEquals("123-456-789", all.get(0).getResearchStaff().getPhoneNumber());
	}

}
