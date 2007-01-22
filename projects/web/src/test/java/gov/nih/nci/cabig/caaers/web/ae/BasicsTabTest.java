package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

/**
 * @author Rhett Sutphin
 */
public class BasicsTabTest extends AeTabTestCase {
    private BasicsTab tab = new BasicsTab();
    private AdverseEvent primaryAe;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        primaryAe = command.getAeReport().getPrimaryAdverseEvent();
    }

    public void testGradeRequired() throws Exception {
        primaryAe.setGrade(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.primaryAdverseEvent.grade", "Grade");
    }

    public void testAttributionRequired() throws Exception {
        primaryAe.setAttribution(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.primaryAdverseEvent.attribution", "Attribution");
    }

    public void testCtcTermRequired() throws Exception {
        primaryAe.setCtcTerm(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.primaryAdverseEvent.ctcTerm", "CTC term");
    }

    public void testOtherNotRequiredIfTermDoesNotRequireIt() throws Exception {
        doValidate();
        assertEquals(0, errors.getFieldErrorCount("aeReport.primaryAdverseEvent.detailsForOther"));
    }

    public void testOtherRequiredIfTermRequiresIt() throws Exception {
        primaryAe.getCtcTerm().setOtherRequired(true);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.primaryAdverseEvent.detailsForOther", "Other (specify)");
    }

    private void doValidate() {
        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
    }
}
