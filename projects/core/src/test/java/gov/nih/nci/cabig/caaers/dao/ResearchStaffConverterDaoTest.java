package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ConverterOrganization;
import gov.nih.nci.cabig.caaers.domain.ConverterResearchStaff;

public class ResearchStaffConverterDaoTest extends DaoNoSecurityTestCase<ResearchStaffConverterDao>{

	private OrganizationConverterDao organizationConverterDao = (OrganizationConverterDao) getApplicationContext().getBean(
    "organizationConverterDao");
	 
	public void testSaveConverterResearchStaff(){
		
		ConverterOrganization org = organizationConverterDao.getByName("New Site");
		ConverterResearchStaff convRs = new ConverterResearchStaff();
		convRs.setFirstName("firstName");
		convRs.setLastName("lastName");
		convRs.setEmailAddress("emailAddress@email.com");
		convRs.setPhoneNumber("201-876-1234");
		convRs.setType("LOCAL");
		convRs.setConverterOrganization(org);  
		getDao().save(convRs);
		interruptSession();
		
		convRs = null;
		convRs = getDao().getByEmailAddress("emailAddress@email.com");
		assertNotNull(convRs);
		assertNotNull(convRs.getId());
		assertEquals("firstName", convRs.getFirstName());
		
		
		convRs.setType("REMOTE");
		convRs.setExternalId("E_iD");
		getDao().save(convRs);
		interruptSession();
		convRs = getDao().getByEmailAddress("emailAddress@email.com");
		assertEquals("REMOTE", convRs.getType());
		
	}
}
