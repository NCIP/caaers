package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_REPORT_FORMAT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_REPORT_FORMAT })
public class ExpeditedReportPersonTest extends CaaersTestCase {
    private ReportPerson person;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        person = new TestPerson();
    }

    public void testSaveableWhenSaveable() throws Exception {
        setSaveable();
        assertTrue(person.isSavable());
    }

    public void testSaveableWithoutFirstName() throws Exception {
        setSaveable();
        person.setFirstName(null);
        assertFalse(person.isSavable());
    }

    public void testSaveableWithoutLastName() throws Exception {
        setSaveable();
        person.setLastName(null);
        assertFalse(person.isSavable());
    }

    public void testSaveableWithoutEmailAddress() throws Exception {
        setSaveable();
        person.getContactMechanisms().remove(ReportPerson.EMAIL);
        assertFalse(person.isSavable());
    }

    private void setSaveable() {
        person.setFirstName("Mr.");
        person.setLastName("Anderson");
        person.getContactMechanisms().put(ReportPerson.EMAIL, "neo@example.com");
    }

    private static class TestPerson extends ReportPerson {
    }
}
