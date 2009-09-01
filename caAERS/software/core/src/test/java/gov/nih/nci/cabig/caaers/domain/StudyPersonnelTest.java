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
		staff.setPhoneNumber("222-222-2222");
		staff.setFaxNumber("333-333-3333");
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
	
	public void testGetPhoneNumber(){
		studyperson.getSiteResearchStaff().setPhoneNumber("444-444-4444");
		assertEquals("444-444-4444", studyperson.getPhoneNumber());
	}
	
	public void testGetPhoneNumber_FromResearchStaff(){
		assertEquals("222-222-2222", studyperson.getPhoneNumber());
	}
	
	public void testGetPhoneNumber_NoPhoneNumber() {
		studyperson.setSiteResearchStaff(null);
		assertNull(studyperson.getPhoneNumber());
	}
	
	public void testGetFaxNumber(){
		studyperson.getSiteResearchStaff().setFaxNumber("555-555-5555");
		assertEquals("555-555-5555", studyperson.getFaxNumber());
	}
	
	public void testGetFaxNumber_FromResearchStaff(){
		assertEquals("333-333-3333", studyperson.getFaxNumber());
	}
	
	public void testGetFaxNumber_NoFaxNumber(){
		studyperson.setSiteResearchStaff(null);
		assertNull(studyperson.getFaxNumber());
	}
}
