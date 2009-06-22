package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

/**
 * @author Rhett Sutphin
 */
public class ViewReportTabTest extends AeTabTestCase {
    private EvaluationService evaluationService;

    private Report report17;

    private Report report23;

    @Override
    protected void setUp() throws Exception {
        evaluationService = registerMockFor(EvaluationService.class);

        report17 = Fixtures.setId(17, new Report());
        report23 = Fixtures.setId(23, new Report());

        super.setUp();

        command.getAeReport().addReport(report17);
        command.getAeReport().addReport(report23);
    }

    @Override
    protected AeTab createTab() {
        ViewReportTab tab = new ViewReportTab();
        tab.setEvaluationService(evaluationService);
        return tab;
    }

//    @SuppressWarnings( { "unchecked" })
//    public void testRefdataIncludesPerReportErrors() throws Exception {
//        ReportSubmittability r17Messages = new ReportSubmittability();
//        ReportSubmittability r23Messages = new ReportSubmittability();
//        r23Messages.addMissingField(ExpeditedReportSection.BASICS_SECTION, "Terrible, terrible",
//                        "75");
//        expect(
//                        evaluationService.validateReportingBusinessRules(command.getAeReport(),
//                                        ExpeditedReportSection.SUBMIT_REPORT_SECTION)).andReturn(
//                        new ValidationErrors()).anyTimes();
//        expect(evaluationService.isSubmittable(report17)).andReturn(r17Messages);
//        expect(evaluationService.isSubmittable(report23)).andReturn(r23Messages);
//
//        replayMocks();
//        Map<String, Object> refdata = getTab().referenceData(command);
//        verifyMocks();
//
//        assertTrue("Report messages map not present", refdata.containsKey("reportMessages"));
//        assertTrue("Report messages map not a Map", refdata.get("reportMessages") instanceof Map);
//        Map<Integer, ReportSubmittability> actual = (Map<Integer, ReportSubmittability>) refdata
//                        .get("reportMessages");
//        assertEquals("Should be one message collection per report", command.getAeReport()
//                        .getReports().size(), actual.size());
//        assertSame(actual.get(17), r17Messages);
//        assertSame(actual.get(23), r23Messages);
//    }

}
