package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.same;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ReportPerson;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ReporterTabTest extends AeTabTestCase {
    private EvaluationService evaluationService;

    private AdverseEvent ae0;

    @Override
    protected void setUp() throws Exception {
        evaluationService = registerMockFor(EvaluationService.class);
        super.setUp();
        ae0 = command.getAeReport().getAdverseEvents().get(0);
        assertEquals("Test setup failure -- only expected 1 AE initially", 1, command.getAeReport()
                        .getAdverseEvents().size());
    }

    @Override
    protected AeTab createTab() {
        ReporterTab tab = new ReporterTab();
        tab.setEvaluationService(evaluationService);
        return tab;
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().getReporter().getContactMechanisms().put("phone", "foo");
        cmd.getAeReport().getReporter().getContactMechanisms().put("fax", "foo");
        cmd.getAeReport().getPhysician().getContactMechanisms().put("phone", "foo");
        cmd.getAeReport().getPhysician().getContactMechanisms().put("fax", "foo");
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
        assertFieldProperties("reporter", "aeReport.reporter.firstName",
                        "aeReport.reporter.middleName", "aeReport.reporter.lastName",
                        "aeReport.reporter.contactMechanisms[e-mail]",
                        "aeReport.reporter.contactMechanisms[phone]",
                        "aeReport.reporter.contactMechanisms[fax]");
    }

    public void testPhysicianFieldProperties() throws Exception {
        assertFieldProperties("physician", "aeReport.physician.firstName",
                        "aeReport.physician.middleName", "aeReport.physician.lastName",
                        "aeReport.physician.contactMechanisms[e-mail]",
                        "aeReport.physician.contactMechanisms[phone]",
                        "aeReport.physician.contactMechanisms[fax]");
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
        command.getAeReport().getReporter().getContactMechanisms().remove(ReportPerson.EMAIL);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.reporter.contactMechanisms[e-mail]",
                        "E-mail address");
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
        command.getAeReport().getPhysician().getContactMechanisms().remove(ReportPerson.EMAIL);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.physician.contactMechanisms[e-mail]",
                        "E-mail address");
    }

    public void testOnDisplayEvaluates() throws Exception {
        expect(evaluationService.isSevere(same(assignment), same(ae0))).andReturn(true);
        replayMocks();

        getTab().onDisplay(request, command);
        verifyMocks();

        assertEquals(Boolean.TRUE, request.getAttribute("oneOrMoreSevere"));
    }

    public void testOnDisplayWhenNotSevere() throws Exception {
        expect(evaluationService.isSevere(same(assignment), same(ae0))).andReturn(false);
        replayMocks();

        getTab().onDisplay(request, command);
        verifyMocks();

        assertEquals(Boolean.FALSE, request.getAttribute("oneOrMoreSevere"));
    }

    private AdverseEvent addAEToCommand() {
        AdverseEvent ae = new AdverseEvent();
        command.getAeReport().addAdverseEvent(ae);
        return ae;
    }

    public void testOnDisplayWithMultipleAEs() throws Exception {
        AdverseEvent ae1 = addAEToCommand();
        AdverseEvent ae2 = addAEToCommand();

        expect(evaluationService.isSevere(same(assignment), same(ae0))).andReturn(false);
        expect(evaluationService.isSevere(same(assignment), same(ae1))).andReturn(false);
        expect(evaluationService.isSevere(same(assignment), same(ae2))).andReturn(true);
        replayMocks();

        getTab().onDisplay(request, command);
        verifyMocks();

        assertEquals(Boolean.TRUE, request.getAttribute("oneOrMoreSevere"));
    }

}
