package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

/**
 * @author Rhett Sutphin
 */
public class BasicsTabTest extends AeTabTestCase {
    private AdverseEvent ae0;

    @Override
    protected BasicsTab createTab() {
        return new BasicsTab();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ae0 = command.getAeReport().getAdverseEvents().get(0);
        assertNotNull(ae0.getCtcTerm());
    }

    public void testGradeRequired() throws Exception {
        ae0.setGrade(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].grade", "Grade");
    }

    public void testHospitalizationRequired() throws Exception {
        ae0.setHospitalization(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].hospitalization", "Hospitalization");
    }

    public void testCtcTermRequired() throws Exception {
        ae0.setCtcTerm(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].ctcTerm", "CTC term");
    }

    public void testOtherNotRequiredIfTermDoesNotRequireIt() throws Exception {
        doValidate();
        assertEquals(0, getErrors().getFieldErrorCount("aeReport.adverseEvents[0].detailsForOther"));
    }

    public void testOtherRequiredIfTermRequiresIt() throws Exception {
        ae0.getCtcTerm().setOtherRequired(true);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].detailsForOther", "Other (specify)");
    }
}
