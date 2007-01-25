package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventReportTest extends CaaersTestCase {
    private AdverseEventReport report;
    private CtcTerm ctcTerm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        report = new AdverseEventReport();
        report.setPrimaryAdverseEvent(new AdverseEvent());
        report.getPrimaryAdverseEvent().setGrade(Grade.MODERATE);
        ctcTerm = new CtcTerm();
        ctcTerm.setTerm("Term");
        ctcTerm.setSelect("Select");
        ctcTerm.setOtherRequired(false);
        report.getPrimaryAdverseEvent().setCtcTerm(this.ctcTerm);
    }

    public void testNotificationMessage() throws Exception {
        assertEquals("Grade 2 adverse event with term Term - Select", report.getNotificationMessage());
    }

    public void testNotificationMessageWithOther() throws Exception {
        ctcTerm.setOtherRequired(true);
        report.getPrimaryAdverseEvent().setDetailsForOther("other");
        assertEquals("Grade 2 adverse event with term Term - Select (other)", report.getNotificationMessage());
    }
}
