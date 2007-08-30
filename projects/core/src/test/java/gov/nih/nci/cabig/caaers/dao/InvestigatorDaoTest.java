package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Investigator;

/**
 * @author Kulasekaran
 */
@CaaersUseCases( { STUDY_ABSTRACTION })
public class InvestigatorDaoTest extends DaoTestCase<InvestigatorDao> {

	public void testGetById() throws Exception {
		Investigator investigator = getDao().getById(-100);
		assertNotNull("Investigator not found", investigator);
		assertEquals("Wrong last name", "Scott", investigator.getLastName());
		assertEquals("Wrong first name", "Dilbert", investigator.getFirstName());
	}

	public void testSaveNewInvestigator() throws Exception {
		Integer savedId;
		{
			Investigator investigator = new Investigator();
			investigator.setFirstName("Jeff");
			investigator.setLastName("Someone");

			investigator.setEmailAddress("abc@def.com");
			investigator.setPhoneNumber("123-456-789");

			getDao().save(investigator);
			savedId = investigator.getId();
			assertNotNull("The saved investigator id", savedId);
		}

		interruptSession();

		{
			Investigator loaded = getDao().getById(savedId);
			assertNotNull("Could not reload investigator id " + savedId, loaded);
			assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
			assertEquals("Wrong lastname", "Someone", loaded.getLastName());
		}
	}
}
