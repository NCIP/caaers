package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Reporter;

/**
 * @author Kulasekaran
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ReporterDaoTest extends DaoTestCase<ReporterDao> {

    public void testGetById() throws Exception {
        Reporter reporter = getDao().getById(-100);
        assertNotNull("Reporter not found", reporter);
        assertEquals("Wrong last name", "Scott", reporter.getLastName());
        assertEquals("Wrong first name", "Dilbert", reporter.getFirstName());
        assertEquals(2, reporter.getContactMechanisms().size());
    }

    public void testSaveNewReporter() throws Exception {
        Integer savedId;
        {
            Reporter reporter = new Reporter();
            reporter.setFirstName("Jeff");
            reporter.setLastName("Someone");
            reporter.getContactMechanisms().put("e-mail", "abc1@abc.com");

            getDao().save(reporter);
            savedId = reporter.getId();
            assertNotNull("The saved reporter has no id", savedId);
        }

        interruptSession();

        {
            Reporter loaded = getDao().getById(savedId);
            assertNotNull("Could not reload reporter " + savedId, loaded);
            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
            assertEquals("Wrong number of contact mechanisms", 1, loaded.getContactMechanisms()
                            .size());
            assertEquals("Wrong contact mechanism", "abc1@abc.com", loaded.getContactMechanisms()
                            .get("e-mail"));
        }
    }
}
