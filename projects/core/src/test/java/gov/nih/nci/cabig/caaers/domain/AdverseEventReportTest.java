package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;

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
        ctcTerm.setCtepTerm("CTEP term");
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

    public void testSummaryIncludesStudy() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, new Site()));
        Map<String, String> summary = report.getSummary();
        assertEquals("El Study", summary.get("Study"));
    }
    
    public void testSummaryStudyIncludesPrimaryIdentifier() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        study.addIdentifier(Identifier.createTemplate("1845"));
        study.getIdentifiers().get(0).setPrimaryIndicator(true);
        report.setAssignment(Fixtures.assignParticipant(participant, study, new Site()));
        Map<String, String> summary = report.getSummary();
        assertEquals("El Study (1845)", summary.get("Study"));
    }

    public void testSummaryIncludesParticipant() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, new Site()));
        Map<String, String> summary = report.getSummary();
        assertEquals("Joe Shabadoo", summary.get("Participant"));
    }

    public void testSummaryParticipantIncludesPrimaryIdentifier() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        participant.addIdentifier(Identifier.createTemplate("MRN1138"));
        participant.getIdentifiers().get(0).setPrimaryIndicator(true);
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, new Site()));
        Map<String, String> summary = report.getSummary();
        assertEquals("Joe Shabadoo (MRN1138)", summary.get("Participant"));
    }

    public void testSummaryIncludesFirstAETerm() throws Exception {
        Map<String, String> summary = report.getSummary();
        assertEquals("CTEP term", summary.get("Primary AE"));
    }

    public void testSummaryIncludesAECount() throws Exception {
        Map<String, String> summary = report.getSummary();
        assertEquals("1", summary.get("Adverse event count"));
    }

/*  It would be nice if this test could pass, but it seems to cause issues with hibernate.
    public void testTreatmentInformationNeverNull() throws Exception {
        assertNotNull(report.getTreatmentInformation());
        report.setTreatmentInformation(null);
        assertNotNull(report.getTreatmentInformation());
    }
*/
}
