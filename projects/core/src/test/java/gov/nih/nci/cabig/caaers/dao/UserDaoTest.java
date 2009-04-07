package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.User;

import java.util.Date;

public class UserDaoTest extends DaoNoSecurityTestCase<UserDao> {
	private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
    "organizationDao");

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testDomainClass() {
		assertEquals(User.class,getDao().domainClass() );
	}

	
	//REVISIT
//	public void testSaveUser_ResearchStaff() {
//		 Integer savedId;
//	        {
//	            ResearchStaff researchStaff = new LocalResearchStaff();
//	            researchStaff.setFirstName("Jeff");
//	            researchStaff.setLastName("Someone");
//	            researchStaff.setEmailAddress("abc@def.com");
//	            researchStaff.setPhoneNumber("123-456-789");
//	            researchStaff.setNciIdentifier("nci id");
//
//	            researchStaff.setOrganization(organizationDao.getById(-1000));
//
//	            getDao().save(researchStaff);
//
//	            savedId = researchStaff.getId();
//	            assertNotNull("The saved researchStaff id", savedId);
//	        }
//
//	        interruptSession();
//
//	        {
//	            User loaded = getDao().getById(savedId);
//	            assertNotNull("Could not reload researchStaff id " + savedId, loaded);
//	            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
//	            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
//	        }
//	}
	
	public void testSaveUser_Investigator(){
		 Integer savedId;
	        {
	            Investigator investigator = new LocalInvestigator();
	            investigator.setFirstName("Jeff");
	            investigator.setLastName("Someone");

	            investigator.setEmailAddress("abc@def.com");
	            investigator.setPhoneNumber("123-456-789");
	            
	            SiteInvestigator siteInvestigator = Fixtures.createSiteInvestigator(organizationDao.getById(-1000), investigator);
	            siteInvestigator.setStatusCode("ACTIVE");
	            siteInvestigator.setStatusDate(new Date());
	            investigator.addSiteInvestigator(siteInvestigator);
	            getDao().save(investigator);
	            savedId = investigator.getId();
	            assertNotNull("The saved investigator id", savedId);
	        }

	        interruptSession();

	        {
	        	System.out.println(savedId);
	            User loaded = getDao().getById(savedId);
	            assertNotNull("Could not reload investigator id " + savedId, loaded);
	            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
	            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
	        }
	}

	public void testGetByEmailAddress() {
		User user = getDao().getByEmailAddress("1003@def.com");
		assertNotNull(user);
		assertTrue(user instanceof ResearchStaff);
		assertEquals("Kennedy", user.getLastName());
	}

	public void testGetByLoginId() {
		User user = getDao().getByLoginId("1003@def.com");
		assertNotNull(user);
		assertTrue(user instanceof ResearchStaff);
		assertEquals("Kennedy", user.getLastName());
	}

}
