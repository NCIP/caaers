package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.ctms.lang.DateTools;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.Calendar;
import java.sql.Timestamp;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 */
public class ExpeditedAdverseEventReportTest extends CaaersTestCase {
    private static final Timestamp CREATED_AT = DateTools.createTimestamp(2006, Calendar.MAY, 8, 9, 8, 7);

    private ExpeditedAdverseEventReport report;
    private BeanWrapper wrappedReport;
    private CtcTerm ctcTerm;
    private AdverseEvent adverseEvent;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        report = new ExpeditedAdverseEventReport();
        report.setCreatedAt(CREATED_AT);
        adverseEvent = new AdverseEvent();
        report.addAdverseEvent(adverseEvent);
        adverseEvent.setGrade(Grade.MODERATE);
        ctcTerm = new CtcTerm();
        ctcTerm.setTerm("Term");
        ctcTerm.setCtepTerm("CTEP term");
        ctcTerm.setSelect("Select");
        ctcTerm.setOtherRequired(false);
        adverseEvent.setCtcTerm(this.ctcTerm);

        wrappedReport = new BeanWrapperImpl(report);
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

    public void testSummaryIncludesCreatedAt() throws Exception {
        Map<String, String> summary = report.getSummary();
        assertEquals("2006-05-08 09:08:07.0", summary.get("Report created at"));
    }

    public void testTreatmentInformationNeverNull() throws Exception {
        assertChildNeverNull("treatmentInformation");
    }

    public void testReporterNeverNull() throws Exception {
        assertChildNeverNull("reporter");
    }

    public void testPhysicianNeverNull() throws Exception {
        assertChildNeverNull("physician");
    }

    public void testResponseNeverNull() throws Exception {
        assertChildNeverNull("responseDescription");
    }

    public void testParticipantHistoryNeverNull() throws Exception {
        assertChildNeverNull("participantHistory");
    }

    public void testDiseaseHistoryNeverNull() throws Exception {
        assertChildNeverNull("diseaseHistory");
    }

    private void assertChildNeverNull(String childProp) {
        assertNotNull(childProp + " null initially", wrappedReport.getPropertyValue(childProp));
        wrappedReport.setPropertyValue(childProp, null);
        ExpeditedAdverseEventReportChild actual = (ExpeditedAdverseEventReportChild) wrappedReport.getPropertyValue(childProp);
        assertNotNull(childProp + " not reinited after set null", actual);
        assertSame("Reverse link not set", report, actual.getReport());
    }
}
