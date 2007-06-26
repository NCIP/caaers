package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class CheckpointTabTest extends AeTabTestCase {
    private ReportDefinition r1, r2, r3;

    private EvaluationService evaluationService;

    @Override
    protected void setUp() throws Exception {
        evaluationService = registerMockFor(EvaluationService.class);
        super.setUp();

        r1 = setId(1, createReportDefinition("R1"));
        r2 = setId(2, createReportDefinition("R2"));
        r3 = setId(3, createReportDefinition("R3"));
    }

    @Override
    protected AeTab createTab() {
        CheckpointTab tab = new CheckpointTab();
        tab.setEvaluationService(evaluationService);
        return tab;
    }

    public void testPostProcessAddsOptionalReports() throws Exception {
        assertEquals(0, command.getAeReport().getReports().size());

        command.getOptionalReportDefinitionsMap().put(r1, Boolean.TRUE);
        command.getOptionalReportDefinitionsMap().put(r2, Boolean.FALSE);
        command.getOptionalReportDefinitionsMap().put(r3, Boolean.TRUE);

        // TODO: there will probably be a call to a service in here somewhere
        getTab().postProcess(request, command, errors);

        List<Report> actualReports = command.getAeReport().getReports();
        assertEquals(2, actualReports.size());
        assertEquals(r1, actualReports.get(0).getReportDefinition());
        assertEquals(r3, actualReports.get(1).getReportDefinition());
        assertFalse(actualReports.get(0).isRequired());
        assertFalse(actualReports.get(1).isRequired());
    }

    public void testPostProcessDoesNotInterfereWithExistingRequiredReports() throws Exception {
        Report reqd = r1.createReport();
        reqd.setRequired(true);
        command.getAeReport().getReports().add(reqd);

        command.getOptionalReportDefinitionsMap().put(r2, Boolean.TRUE);
        command.getOptionalReportDefinitionsMap().put(r3, Boolean.FALSE);

        // TODO: there will probably be a call to a service in here somewhere
        getTab().postProcess(request, command, errors);

        List<Report> actualReports = command.getAeReport().getReports();
        assertEquals(2, actualReports.size());
        assertEquals(r1, actualReports.get(0).getReportDefinition());
        assertEquals(r2, actualReports.get(1).getReportDefinition());
        assertTrue(actualReports.get(0).isRequired());
        assertFalse(actualReports.get(1).isRequired());
    }

    public void testPostProcessRemovesDeselectedOptionalReports() throws Exception {
        Report reqd = r1.createReport();
        reqd.setRequired(true);
        command.getAeReport().getReports().add(reqd);
        command.getAeReport().getReports().add(r2.createReport());

        command.getOptionalReportDefinitionsMap().put(r2, Boolean.FALSE);
        command.getOptionalReportDefinitionsMap().put(r3, Boolean.FALSE);

        // TODO: there will probably be a call to a service in here somewhere
        getTab().postProcess(request, command, errors);

        List<Report> actualReports = command.getAeReport().getReports();
        assertEquals(1, actualReports.size());
        assertEquals(r1, actualReports.get(0).getReportDefinition());
        assertTrue(actualReports.get(0).isRequired());
    }

    public void testPostProcessDoesNotRemoveRequiredReportsEver() throws Exception {
        Report reqd = r1.createReport();
        reqd.setRequired(true);
        command.getAeReport().getReports().add(reqd);

        // other code should prevent this situation from occurring, but just in case:
        command.getOptionalReportDefinitionsMap().put(r1, Boolean.FALSE);
        command.getOptionalReportDefinitionsMap().put(r2, Boolean.FALSE);
        command.getOptionalReportDefinitionsMap().put(r3, Boolean.FALSE);

        // TODO: there will probably be a call to a service in here somewhere
        getTab().postProcess(request, command, errors);

        List<Report> actualReports = command.getAeReport().getReports();
        assertEquals(1, actualReports.size());
        assertEquals(r1, actualReports.get(0).getReportDefinition());
        assertTrue(actualReports.get(0).isRequired());
    }

    public void testPostProcessSavesWhenThereAreAnyReports() throws Exception {
        command.getAeReport().getReports().add(r1.createReport());
        reportDao.save(command.getAeReport());

        replayMocks();
        getTab().postProcess(request, command, errors);
        verifyMocks();
    }

    public void testPostProcessDoesNotSaveWhenThereAreNoReports() throws Exception {
        command.getAeReport().getReports().clear();

        replayMocks();
        getTab().postProcess(request, command, errors);
        verifyMocks();
    }

    public void testFieldsPresentForOptionalReports() throws Exception {
        command.getAeReport().addReport(r2.createReport());
        command.setOptionalReportDefinitions(Arrays.asList(r1, r2, r3));
        assertFieldProperties("optionalReports",
            "optionalReportDefinitionsMap[1]",
            "optionalReportDefinitionsMap[2]",
            "optionalReportDefinitionsMap[3]"
        );
    }

    public void testNoReportsIsError() throws Exception {
        command.getAeReport().getReports().clear();

        replayMocks();
        getTab().validate(command, errors);
        verifyMocks();

        assertEquals(1, errors.getErrorCount());
        assertEquals(1, errors.getGlobalErrorCount());
        assertEquals("At least one expedited report must be selected to proceed",
            errors.getGlobalError().getDefaultMessage());
    }

    public void testAtLeastOneSelectedOptionalReportIsNotError() throws Exception {
        command.getAeReport().getReports().clear();
        command.getOptionalReportDefinitionsMap().put(r1, Boolean.FALSE);
        command.getOptionalReportDefinitionsMap().put(r2, Boolean.TRUE);
        command.getOptionalReportDefinitionsMap().put(r3, Boolean.FALSE);

        replayMocks();
        getTab().validate(command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
    }
}
