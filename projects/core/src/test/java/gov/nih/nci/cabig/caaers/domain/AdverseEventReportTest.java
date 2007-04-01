package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.Arrays;
import java.util.ArrayList;

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

    public void testGetAdverseEventNeverThrowsIndexOutOfBounds() throws Exception {
        AdverseEvent e4 = report.getAdverseEvents().get(4);
        assertNotNull(e4);
        assertSame(report, e4.getReport());
    }

    public void testSetAdverseEventsInternalReflectedInAdverseEvents() throws Exception {
        report.setAdverseEventsInternal(new ArrayList<AdverseEvent>(Arrays.asList(
            Fixtures.setId(10, new AdverseEvent()),
            Fixtures.setId(12, new AdverseEvent()),
            Fixtures.setId(14, new AdverseEvent())
        )));
        assertEquals(10, (int) report.getAdverseEvents().get(0).getId());
        assertEquals(12, (int) report.getAdverseEvents().get(1).getId());
        assertEquals(14, (int) report.getAdverseEvents().get(2).getId());
    }

    public void testDynamicallyCreatedAdverseEventsInInternal() throws Exception {
        AdverseEvent e4 = report.getAdverseEvents().get(4);
        assertSame(e4, report.getAdverseEventsInternal().get(4));
    }

    public void testGetLabNeverThrowsIndexOutOfBounds() throws Exception {
        Lab l4 = report.getLabs().get(4);
        assertNotNull(l4);
        assertSame(report, l4.getReport());
    }

    public void testSetLabsInternalReflectedInLabs() throws Exception {
        report.setLabsInternal(new ArrayList<Lab>(Arrays.asList(
            Fixtures.setId(10, new Lab()),
            Fixtures.setId(12, new Lab()),
            Fixtures.setId(14, new Lab())
        )));
        assertEquals(10, (int) report.getLabs().get(0).getId());
        assertEquals(12, (int) report.getLabs().get(1).getId());
        assertEquals(14, (int) report.getLabs().get(2).getId());
    }

    public void testDynamicallyCreatedLabsInInternal() throws Exception {
        Lab l4 = report.getLabs().get(4);
        assertSame(l4, report.getLabsInternal().get(4));
    }

    public void testGetConMedNeverThrowsIndexOutOfBounds() throws Exception {
        ConcomitantMedication cm4 = report.getConcomitantMedications().get(4);
        assertNotNull(cm4);
        assertSame(report, cm4.getReport());
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
        report.getAdverseEventsInternal().clear();
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
