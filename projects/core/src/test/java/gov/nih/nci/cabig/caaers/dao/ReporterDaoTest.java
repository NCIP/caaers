package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ContactMechanism;
import gov.nih.nci.cabig.caaers.domain.Reporter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulasekaran
 */
public class ReporterDaoTest extends DaoTestCase<ReporterDao> {

    public void testGetById() throws Exception {
        Reporter reporter = getDao().getById(-100);
        assertNotNull("Reporter not found", reporter);
        assertEquals("Wrong last name", "Scott", reporter.getLastName());
        assertEquals("Wrong first name", "Dilbert", reporter.getFirstName());
    }

    public void testSaveNewReporter() throws Exception {
        Integer savedId;
        {
            Reporter reporter = new Reporter();
            reporter.setFirstName("Jeff");
            reporter.setLastName("Someone");
            ContactMechanism cm = new ContactMechanism();
            cm.setType("email");
            cm.setValue("abc1@abc.com");
            List<ContactMechanism> arr = new ArrayList<ContactMechanism>();
            arr.add(cm);
            reporter.setContactMechanisms(arr);

            getDao().save(reporter);
            savedId = reporter.getId();
            assertNotNull("The saved repoter id", savedId);
        }

        interruptSession();

        {
            Reporter loaded = getDao().getById(savedId);
            assertNotNull("Could not reload reporter " + savedId, loaded);
            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
            assertEquals("Wrong number of contact mechanisms", 1,
                loaded.getContactMechanisms().size());
            assertEquals("Wrong type for cm[0]", "email",
                loaded.getContactMechanisms().get(0).getType());
            assertEquals("Wrong value for cm[0]", "abc1@abc.com",
                loaded.getContactMechanisms().get(0).getValue());
        }
    }
}
