package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.ctms.lang.DateTools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_ROUTINE_REPORT})
public class RoutineAdverseEventReportTest extends CaaersNoSecurityTestCase {
    private static final Timestamp START_DATE = DateTools.createTimestamp(2006, Calendar.MAY, 8, 9, 8, 7);
    private static final Timestamp END_DATE = DateTools.createTimestamp(2006, Calendar.JULY, 8, 9, 8, 7);
    
    private RoutineAdverseEventReport report;
    private BeanWrapper wrappedReport;
    private CtcTerm ctcTerm;
    private AdverseEvent adverseEvent;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        report = new RoutineAdverseEventReport();
        report.setStartDate(START_DATE);
        report.setEndDate(END_DATE);
        adverseEvent = new AdverseEvent();
        report.addAdverseEvent(adverseEvent);
        adverseEvent.setGrade(Grade.MODERATE);
        ctcTerm = new CtcTerm();
        ctcTerm.setTerm("Term");
        ctcTerm.setCtepTerm("CTEP term");
        ctcTerm.setSelect("Select");
        ctcTerm.setOtherRequired(false);
        adverseEvent.getAdverseEventCtcTerm().setCtcTerm(ctcTerm);

        wrappedReport = new BeanWrapperImpl(report);
    }

    public void testGetAdverseEventNeverThrowsIndexOutOfBounds() throws Exception {
        AdverseEvent e4 = report.getAdverseEvents().get(4);
        assertNotNull(e4);
	System.out.println("testGetAdverseEventNeverThrowsIndexOutOfBounds");
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
	System.out.println("testSetAdverseEvents...");
    }

    public void testDynamicallyCreatedAdverseEventsInInternal() throws Exception {
        AdverseEvent e4 = report.getAdverseEvents().get(4);
        assertSame(e4, report.getAdverseEventsInternal().get(4));
	System.out.println("testDynamically...");
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
        adverseEvent.getAdverseEventCtcTerm().setCtcTerm(null);
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
        report.setAssignment(Fixtures.assignParticipant(participant, study, new Organization()));
        Map<String, String> summary = report.getSummary();
        assertEquals("El Study", summary.get("Study"));
    }
    
    public void testSummaryStudyIncludesPrimaryIdentifier() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        study.addIdentifier(Identifier.createTemplate("1845"));
        study.getIdentifiers().get(0).setPrimaryIndicator(true);
        report.setAssignment(Fixtures.assignParticipant(participant, study, new Organization()));
        Map<String, String> summary = report.getSummary();
        assertEquals("El Study (1845)", summary.get("Study"));
    }

    public void testSummaryIncludesParticipant() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));
        Map<String, String> summary = report.getSummary();
        assertEquals("Joe Shabadoo", summary.get("Participant"));
    }

    public void testSummaryParticipantIncludesPrimaryIdentifier() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        participant.addIdentifier(Identifier.createTemplate("MRN1138"));
        participant.getIdentifiers().get(0).setPrimaryIndicator(true);
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));
        Map<String, String> summary = report.getSummary();
        assertEquals("Joe Shabadoo (MRN1138)", summary.get("Participant"));
    }

}
