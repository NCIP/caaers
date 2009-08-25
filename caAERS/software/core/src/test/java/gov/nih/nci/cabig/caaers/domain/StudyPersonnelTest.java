package gov.nih.nci.cabig.caaers.domain;

import java.util.Arrays;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyPersonnelTest extends TestCase {
	
	StudyPersonnel studyperson;
	ResearchStaff staff; 
	
	protected void setUp() throws Exception {
		super.setUp();
		Organization organization = Fixtures.createOrganization("test");
		staff = Fixtures.createResearchStaff(organization, Arrays.asList(UserGroupType.caaers_ae_cd),"jank");
		studyperson = Fixtures.createStudyPersonnel(staff);
		staff.setEmailAddress("jj@jj.com");
	}

	public void testGetEmailAddress() {
		studyperson.getSiteResearchStaff().setEmailAddress("ab@kk.com");
		assertEquals("ab@kk.com", studyperson.getEmailAddress());
	}
	public void testGetEmailAddress_FromResearchStaff() {
		assertEquals("jj@jj.com", studyperson.getEmailAddress());
	}
	public void testGetEmailAddress_NoEmail() {
		studyperson.setSiteResearchStaff(null);
		assertNull(studyperson.getEmailAddress());
	}
}
