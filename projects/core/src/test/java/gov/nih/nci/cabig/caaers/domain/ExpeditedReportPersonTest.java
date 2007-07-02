package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class ExpeditedReportPersonTest extends CaaersTestCase {
    private ExpeditedReportPerson person;

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
        person.getContactMechanisms().remove(ExpeditedReportPerson.EMAIL);
        assertFalse(person.isSavable());
    }
    
    private void setSaveable() {
        person.setFirstName("Mr.");
        person.setLastName("Anderson");
        person.getContactMechanisms().put(ExpeditedReportPerson.EMAIL, "neo@example.com");
    }

    private static class TestPerson extends ExpeditedReportPerson {
    }
}
