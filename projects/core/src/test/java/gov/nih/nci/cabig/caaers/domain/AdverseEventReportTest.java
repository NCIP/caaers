package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventReportTest extends CaaersTestCase {
    private AdverseEventReport report;
    private CtcTerm ctcTerm;
    private AdverseEvent adverseEvent;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        report = new AdverseEventReport();
        adverseEvent = new AdverseEvent();
        report.addAdverseEvent(adverseEvent);
        adverseEvent.setGrade(Grade.MODERATE);
        ctcTerm = new CtcTerm();
        ctcTerm.setTerm("Term");
        ctcTerm.setSelect("Select");
        ctcTerm.setOtherRequired(false);
        adverseEvent.setCtcTerm(this.ctcTerm);
    }

    public void testNotificationMessage() throws Exception {
        assertEquals("Grade 2 adverse event with term Term - Select", report.getNotificationMessage());
    }

    public void testNotificationMessageWithOther() throws Exception {
        ctcTerm.setOtherRequired(true);
        adverseEvent.setDetailsForOther("other");
        assertEquals("Grade 2 adverse event with term Term - Select (other)", report.getNotificationMessage());
    }
    
    public void testNotificationMessageExceptionForNoAe() throws Exception {
        report.getAdverseEvents().clear();
        assertFalse(report.isNotificationMessagePossible());
        try {
            report.getNotificationMessage();
            fail("Exception not thrown");
        } catch (CaaersSystemException cse) {
            assertEquals("Cannot create notification message until primary AE is filled in", cse.getMessage());
        }
    }

    public void testNotificationMessageExceptionForNoGrade() throws Exception {
        adverseEvent.setGrade(null);
        assertFalse(report.isNotificationMessagePossible());
        try {
            report.getNotificationMessage();
            fail("Exception not thrown");
        } catch (CaaersSystemException cse) {
            assertEquals("Cannot create notification message until primary AE is filled in", cse.getMessage());
        }
    }
    
    public void testNotificationMessageExceptionForNoTerm() throws Exception {
        adverseEvent.setCtcTerm(null);
        assertFalse(report.isNotificationMessagePossible());
        try {
            report.getNotificationMessage();
            fail("Exception not thrown");
        } catch (CaaersSystemException cse) {
            assertEquals("Cannot create notification message until primary AE is filled in", cse.getMessage());
        }
    }
}
