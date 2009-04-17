package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ConverterInvestigator;

public class InvestigatorConverterDaoTest extends DaoNoSecurityTestCase<InvestigatorConverterDao>{
	 
	public void testSaveConverterResearchStaff(){
		
		ConverterInvestigator convInv = new ConverterInvestigator();
		convInv.setFirstName("firstName");
		convInv.setLastName("lastName");
		convInv.setEmailAddress("emailAddress@email.com");
		convInv.setPhoneNumber("201-876-1234");
		convInv.setType("LOCAL");
		getDao().save(convInv);
		interruptSession();
		
		convInv = null;
		convInv = getDao().getByEmailAddress("emailAddress@email.com");
		assertNotNull(convInv);
		assertNotNull(convInv.getId());
		assertEquals("firstName", convInv.getFirstName());
		
		convInv.setType("REMOTE");
		convInv.setExternalId("E_iD");
		getDao().save(convInv);
		interruptSession();
		convInv = getDao().getByEmailAddress("emailAddress@email.com");
		assertEquals("REMOTE", convInv.getType());
	}
}
