package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;

/**
 * @author Rhett Sutphin
 */
public class ReporterTabTest extends AeTabTestCase {
    @Override
    protected AeTab createTab() {
        return new ReporterTab();
    }

    public void testGroupsIncludesReporter() throws Exception {
        InputFieldGroup actual = getTab().createFieldGroups(command).get("reporter");
        assertNotNull("No reporter group", actual);
        assertEquals("Wrong group name", "reporter", actual.getName());
        assertEquals("Wrong display name", "Reporter details", actual.getDisplayName());
    }

    public void testGroupsIncludesPhysician() throws Exception {
        InputFieldGroup actual = getTab().createFieldGroups(command).get("physician");
        assertNotNull("No physician group", actual);
        assertEquals("Wrong group name", "physician", actual.getName());
        assertEquals("Wrong display name", "Physician details", actual.getDisplayName());
    }

    public void testReporterFieldProperties() throws Exception {
        assertFieldProperties("reporter",
            "aeReport.reporter.firstName",
            "aeReport.reporter.middleName",
            "aeReport.reporter.lastName",
            "aeReport.reporter.contactMechanisms[e-mail]",
            "aeReport.reporter.contactMechanisms[phone]",
            "aeReport.reporter.contactMechanisms[fax]"
        );
    }

    public void testPhysicianFieldProperties() throws Exception {
        assertFieldProperties("physician",
            "aeReport.physician.firstName",
            "aeReport.physician.middleName",
            "aeReport.physician.lastName",
            "aeReport.physician.contactMechanisms[e-mail]",
            "aeReport.physician.contactMechanisms[phone]",
            "aeReport.physician.contactMechanisms[fax]"
        );
    }

    public void testReporterFirstNameRequired() throws Exception {
        command.getAeReport().getReporter().setFirstName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.reporter.firstName", "First name");
    }

    public void testReporterLastNameRequired() throws Exception {
        command.getAeReport().getReporter().setLastName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.reporter.lastName", "Last name");
    }

    public void testReporterEmailAddressRequired() throws Exception {
        command.getAeReport().getReporter().getContactMechanisms().remove(ExpeditedReportPerson.EMAIL);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.reporter.contactMechanisms[e-mail]", "E-mail address");
    }

    public void testPhysicianFirstNameRequired() throws Exception {
        command.getAeReport().getPhysician().setFirstName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.physician.firstName", "First name");
    }

    public void testPhysicianLastNameRequired() throws Exception {
        command.getAeReport().getPhysician().setLastName(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.physician.lastName", "Last name");
    }

    public void testPhysicianEmailAddressRequired() throws Exception {
        command.getAeReport().getPhysician().getContactMechanisms().remove(ExpeditedReportPerson.EMAIL);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.physician.contactMechanisms[e-mail]", "E-mail address");
    }

    public void testPreProcessAddsReporterIfNotPresent() throws Exception {
        command.getAeReport().setReporter(null);
        assertNull("Test setup failure: existing reporter", command.getAeReport().getReporter());
        getTab().preProcess(request, command);

        assertNotNull("Reporter not added",command.getAeReport().getReporter());
    }
    
    public void testPreProcessAddsPhysicianIfNotPresent() throws Exception {
        command.getAeReport().setPhysician(null);
        assertNull("Test setup failure: existing physician", command.getAeReport().getPhysician());
        getTab().preProcess(request, command);

        assertNotNull("Physician not added",command.getAeReport().getPhysician());
    }

    public void testDirtyMoves() throws Exception {
        assertFalse(getTab().isAllowDirtyBack());
        assertFalse(getTab().isAllowDirtyForward());
    }
}
