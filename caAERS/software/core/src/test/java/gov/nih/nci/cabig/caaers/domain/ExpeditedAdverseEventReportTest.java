package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.lang.DateTools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT})
public class ExpeditedAdverseEventReportTest extends AbstractNoSecurityTestCase {
    private static final Timestamp CREATED_AT = DateTools.createTimestamp(2006, Calendar.MAY, 8, 9,
            8, 7);

    private ExpeditedAdverseEventReport report;

    private BeanWrapper wrappedReport;

    private CtcTerm ctcTerm;

    private AdverseEvent adverseEvent;
    private StudyParticipantAssignment assignment;
    private AdverseEventReportingPeriod reportingPeriod;
    private String publicId;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        report = new ExpeditedAdverseEventReport();
        report.setCreatedAt(CREATED_AT);
        publicId = "str id";
        report.setPublicIdentifier(publicId);
        adverseEvent = new AdverseEvent();
        report.addAdverseEvent(adverseEvent);
        adverseEvent.setGrade(Grade.MODERATE);
        ctcTerm = new CtcTerm();
        ctcTerm.setTerm("Term");
        ctcTerm.setCtepTerm("CTEP term");
        ctcTerm.setSelect("Select");
        ctcTerm.setOtherRequired(false);
        adverseEvent.getAdverseEventCtcTerm().setCtcTerm(this.ctcTerm);
        report.setReportingPeriod(Fixtures.createReportingPeriod());
        wrappedReport = new BeanWrapperImpl(report);
        assignment = new StudyParticipantAssignment();
        assignment.addConcomitantMedication(new StudyParticipantConcomitantMedication());
        assignment.addPriorTherapy(new StudyParticipantPriorTherapy());
        assignment.addPreExistingCondition(new StudyParticipantPreExistingCondition());
        StudyParticipantDiseaseHistory studyParticipantDiseaseHistory = new StudyParticipantDiseaseHistory();
        studyParticipantDiseaseHistory.addMetastaticDiseaseSite(new StudyParticipantMetastaticDiseaseSite());
        assignment.setDiseaseHistory(studyParticipantDiseaseHistory);

        reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
    }
//
//    public void testCopyLab() {
//        report.addLab(new Lab());
//        assertFalse(report.getLabs().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(1, copiedReport.getLabs().size());
//        for (Lab object : copiedReport.getLabs()) {
//            assertSame("must change reference", copiedReport, object.getReport());
//        }
//    }
//
//    public void testAdverseEvents() {
//        assertFalse(report.getAdverseEvents().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(report.getAdverseEvents().size(), copiedReport.getAdverseEvents().size());
//        for (AdverseEvent event : copiedReport.getAdverseEvents()) {
//            assertSame("must change reference", copiedReport, event.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testPriorTherapies() {
//        report.addSaeReportPriorTherapies(new SAEReportPriorTherapy());
//        assertFalse(report.getSaeReportPriorTherapies().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(report.getSaeReportPriorTherapies().size(), copiedReport.getSaeReportPriorTherapies().size());
//        for (SAEReportPriorTherapy priorTherapy : copiedReport.getSaeReportPriorTherapies()) {
//            assertSame("must change reference", copiedReport, priorTherapy.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testTreatmentInformation() {
//
//        report.setTreatmentInformation(new TreatmentInformation());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNotNull(copiedReport.getTreatmentInformation());
//        assertSame("must change reference", copiedReport, copiedReport.getTreatmentInformation().getReport());
//    	assertTrue(true);
//
//    }
//
//    public void testCopyPhysician() {
//
//        report.setPhysician(new Physician());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNotNull(copiedReport.getPhysician());
//        assertSame("must change reference", copiedReport, copiedReport.getPhysician().getExpeditedReport());
//    	assertTrue(true);
//    }
//
//    public void testCopyReporter() {
//
//        report.setReporter(new Reporter());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNotNull(copiedReport.getReporter());
//        assertSame("must change reference", copiedReport, copiedReport.getReporter().getExpeditedReport());
//    	assertTrue(true);
//    }
//
//    public void testCopyAdditionalInformation() {
//
//        report.setAdditionalInformation(new AdditionalInformation());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNotNull(copiedReport.getAdditionalInformation());
//        assertSame("must change reference", copiedReport, copiedReport.getAdditionalInformation().getReport());
//    	assertTrue(true);
//    }
//
//    public void testCopyResponseDescription() {
//
//        report.setResponseDescription(new AdverseEventResponseDescription());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNotNull(copiedReport.getResponseDescription());
//        assertSame("must change reference", copiedReport, copiedReport.getResponseDescription().getReport());
//    	assertTrue(true);
//    }
//
//    public void testCopyDiseaseHistory() {
//
//        report.setDiseaseHistory(new DiseaseHistory());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNotNull(copiedReport.getDiseaseHistory());
//        assertSame("must change reference", copiedReport, copiedReport.getDiseaseHistory().getReport());
//    	assertTrue(true);
//    }
//
//    public void testCopyParticipantHistory() {
//
//        report.setParticipantHistory(new ParticipantHistory());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNotNull(copiedReport.getParticipantHistory());
//        assertSame("must change reference", copiedReport, copiedReport.getParticipantHistory().getReport());
//    	assertTrue(true);
//    }
//
//    public void testCopyMedicalDevices() {
//        report.addMedicalDevice(new MedicalDevice());
//        assertFalse(report.getMedicalDevices().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(1, copiedReport.getMedicalDevices().size());
//        for (MedicalDevice object : copiedReport.getMedicalDevices()) {
//            assertSame("must change reference", copiedReport, object.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testCopyPreExistingConditions() {
//        report.addSaeReportPreExistingCondition(new SAEReportPreExistingCondition());
//        assertFalse(report.getSaeReportPreExistingConditions().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(1, copiedReport.getSaeReportPreExistingConditions().size());
//        for (SAEReportPreExistingCondition object : copiedReport.getSaeReportPreExistingConditions()) {
//            assertSame("must change reference", copiedReport, object.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testCopyOtherCauses() {
//        report.addOtherCause(new OtherCause());
//        assertFalse(report.getOtherCauses().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(1, copiedReport.getOtherCauses().size());
//        for (OtherCause object : copiedReport.getOtherCauses()) {
//            assertSame("must change reference", copiedReport, object.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testCopyRadiationIntervention() {
//        report.addRadiationIntervention(new RadiationIntervention());
//        assertFalse(report.getRadiationInterventions().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(1, copiedReport.getRadiationInterventions().size());
//        for (RadiationIntervention object : copiedReport.getRadiationInterventions()) {
//            assertSame("must change reference", copiedReport, object.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testCopySurgeryIntervention() {
//        report.addSurgeryIntervention(new SurgeryIntervention());
//        assertFalse(report.getSurgeryInterventions().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(report.getSurgeryInterventions().size(), copiedReport.getSurgeryInterventions().size());
//        for (SurgeryIntervention object : copiedReport.getSurgeryInterventions()) {
//            assertSame("must change reference", copiedReport, object.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testCopyConMed() {
//        report.addConcomitantMedication(new ConcomitantMedication());
//        assertFalse(report.getConcomitantMedications().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertEquals(1, copiedReport.getConcomitantMedications().size());
//        for (ConcomitantMedication concomitantMedication : copiedReport.getConcomitantMedications()) {
//            assertSame("must change reference", copiedReport, concomitantMedication.getReport());
//        }
//    	assertTrue(true);
//    }
//
//    public void testMustNotCopyReports() {
//        report.addReport(new Report());
//        assertFalse(report.getReports().isEmpty());
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertTrue("must not copy report", copiedReport.getReports().isEmpty());
//
//    }

//    public void testCopyBasicProperties() {
//
//
//        ExpeditedAdverseEventReport copiedReport = report.copy();
//        assertNull("must not copy id", copiedReport.getId());
//        assertNull("must not copy version ", copiedReport.getGridId());
//        assertNull("must not copy grid id", copiedReport.getVersion());
//        assertEquals(report.getReportingPeriod(), copiedReport.getReportingPeriod());
//        assertSame(report.getReportingPeriod(), copiedReport.getReportingPeriod());
//
//        assertEquals(report.getReportingPeriod().getAssignment(), copiedReport.getAssignment());
//        assertSame(report.getReportingPeriod().getAssignment(), copiedReport.getAssignment());
//
//        assertEquals(CREATED_AT, copiedReport.getCreatedAt());
//        assertEquals(report.getPublicIdentifier(), copiedReport.getPublicIdentifier());
//
//    }

    public void testWrongUsesOfSyncrhonizeMethod() {
        report.setAssignment(null);
        String message = String.format("Must set assignment before calling synchronizeMedicalHistoryFromAssignmentToReport");
        try {
            report.synchronizeMedicalHistoryFromAssignmentToReport();
            fail();

        } catch (CaaersSystemException e) {
            assertEquals(message, e.getMessage());
        }

    }

    public void testSyncrhonizePriorTherapies() {
        report.setReportingPeriod(reportingPeriod);
        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertEquals("must  copy the prior therapy", 1, report.getSaeReportPriorTherapies().size());

        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertEquals("must not copy  prior therapy twice", 1, report.getSaeReportPriorTherapies().size());


    }

    public void testSyncrhonizeDiseaseHistory() {
        report.setReportingPeriod(reportingPeriod);
        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertNotNull("must  copy the diseaseHistory", report.getDiseaseHistory());
        assertEquals("must  copy the MetastaticDiseaseSite", 1, report.getDiseaseHistory().getMetastaticDiseaseSites().size());

        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertNotNull("must not copy the diseaseHistory twice", report.getDiseaseHistory());
        assertEquals("must  copy the MetastaticDiseaseSite twice", 1, report.getDiseaseHistory().getMetastaticDiseaseSites().size());

    }

    public void testSyncrhonizePreExistingCondition() {
        report.setReportingPeriod(reportingPeriod);
        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertEquals("must copy the pre existing condition", 1, report.getSaeReportPreExistingConditions().size());

        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertEquals("must not copy the pre existing condition twice", 1, report.getSaeReportPreExistingConditions().size());

    }

    public void testSyncrhonizeConcomitantMedication() {
        report.setReportingPeriod(reportingPeriod);
        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertEquals("must copy the concomitantMedication", 1, report.getConcomitantMedications().size());
        report.synchronizeMedicalHistoryFromAssignmentToReport();
        assertEquals("must not copy the concomitantMedication twice", 1, report.getConcomitantMedications().size());


    }

    public void testGetAdverseEventNeverThrowsIndexOutOfBounds() throws Exception {
        AdverseEvent e4 = report.getAdverseEvents().get(4);
        assertNotNull(e4);
        assertSame(report, e4.getReport());
    }

    public void testSetAdverseEventsInternalReflectedInAdverseEvents() throws Exception {
        report.setAdverseEventsInternal(new ArrayList<AdverseEvent>(Arrays.asList(Fixtures.setId(
                10, new AdverseEvent()), Fixtures.setId(12, new AdverseEvent()), Fixtures
                .setId(14, new AdverseEvent()))));
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
        report.setLabsInternal(new ArrayList<Lab>(Arrays.asList(Fixtures.setId(10, new Lab()),
                Fixtures.setId(12, new Lab()), Fixtures.setId(14, new Lab()))));
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
        assertEquals("Grade 2 adverse event with term Term - Select", report
                .getNotificationMessage());
    }

    public void testNotificationMessageWithOther() throws Exception {
        ctcTerm.setOtherRequired(true);
        adverseEvent.setDetailsForOther("other");
        assertEquals("Grade 2 adverse event with term Term - Select (other)", report
                .getNotificationMessage());
    }

    public void testNotificationMessageExceptionForNoAe() throws Exception {
        report.getAdverseEventsInternal().clear();
        assertFalse(report.isNotificationMessagePossible());
        try {
            report.getNotificationMessage();
            fail("Exception not thrown");
        } catch (CaaersSystemException cse) {
            assertEquals("Cannot create notification message until primary AE is filled in", cse
                    .getMessage());
        }
    }

    public void testNotificationMessageExceptionForNoGrade() throws Exception {
        adverseEvent.setGrade(null);
        assertFalse(report.isNotificationMessagePossible());
        try {
            report.getNotificationMessage();
            fail("Exception not thrown");
        } catch (CaaersSystemException cse) {
            assertEquals("Cannot create notification message until primary AE is filled in", cse
                    .getMessage());
        }
    }

    public void testNotificationMessageExceptionForNoTerm() throws Exception {
        adverseEvent.getAdverseEventCtcTerm().setCtcTerm(null);
        assertFalse(report.isNotificationMessagePossible());
        try {
            report.getNotificationMessage();
            fail("Exception not thrown");
        } catch (CaaersSystemException cse) {
            assertEquals("Cannot create notification message until primary AE is filled in", cse
                    .getMessage());
        }
    }

    public void testSummaryIncludesStudy() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));
        Map<String, String> summary = report.getSummary();
        assertEquals(" El Study", summary.get("Study"));
    }

    public void testSummaryStudyIncludesPrimaryIdentifier() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        study.addIdentifier(Identifier.createTemplate("1845"));
        study.getIdentifiers().get(0).setPrimaryIndicator(true);
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));
        Map<String, String> summary = report.getSummary();
        assertEquals(" (1845) El Study", summary.get("Study"));
    }

    public void testSummaryIncludesParticipant() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));
        Map<String, String> summary = report.getSummary();
        assertEquals(" Joe Shabadoo", summary.get("Participant"));
    }

    public void testSummaryParticipantIncludesPrimaryIdentifier() throws Exception {
        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        participant.addIdentifier(Identifier.createTemplate("MRN1138"));
        participant.getIdentifiers().get(0).setPrimaryIndicator(true);
        Study study = Fixtures.createStudy("El Study");
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));
        Map<String, String> summary = report.getSummary();
        assertEquals(" (MRN1138) Joe Shabadoo", summary.get("Participant"));
    }

    public void testSummaryIncludesFirstAETerm() throws Exception {
        Map<String, String> summary = report.getSummary();
        assertEquals("Term - Select", summary.get("Primary AE"));
    }

    public void testSummaryIncludesAECount() throws Exception {
        Map<String, String> summary = report.getSummary();
        assertEquals("1", summary.get("AE count"));
    }

    public void testSummaryIncludesCreatedAt() throws Exception {
        Map<String, String> summary = report.getSummary();
        assertEquals("2006-05-08 09:08:07.0", summary.get("Report created at"));
    }

    public void testTreatmentInformationNeverNull() throws Exception {
        assertChildNeverNull("treatmentInformation");
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
        ExpeditedAdverseEventReportChild actual = (ExpeditedAdverseEventReportChild) wrappedReport
                .getPropertyValue(childProp);
        assertNotNull(childProp + " not reinited after set null", actual);
        assertSame("Reverse link not set", report, actual.getReport());
    }



    public void testGetSponsorDefinedReports() throws Exception {
        Report rep = new Report();
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        rep = new Report();
        reportDefinition = Fixtures.createReportDefinition("defn2", "NCI-CODE2");
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        rep = new Report();
        reportDefinition = Fixtures.createReportDefinition("defn3", "NCI-CODE1");
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        Organization org = Fixtures.createOrganization("test Org");
        org.setNciInstituteCode("NCI-CODE1");
        StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(org);
        sponsor.setPrimary(true);
        study.addStudyFundingSponsor(sponsor);
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));

        assertEquals(2, report.getSponsorDefinedReports().size());

    }
    
    public void testGetAllSponsorReportsCompletedWithReplacedReports() throws Exception{
    	Report rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.REPLACED);
        reportDefinition = Fixtures.createReportDefinition("defn3", "NCI-CODE1");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        Organization org = Fixtures.createOrganization("test Org");
        org.setNciInstituteCode("NCI-CODE1");
        StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(org);
        sponsor.setPrimary(true);
        study.addStudyFundingSponsor(sponsor);
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));

        assertTrue(report.getAllSponsorReportsCompleted());
    }

    public void testGetAllSponsorReportsCompleted() throws Exception {
        Report rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        reportDefinition = Fixtures.createReportDefinition("defn2", "NCI-CODE2");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.WITHDRAWN);
        reportDefinition = Fixtures.createReportDefinition("defn3", "NCI-CODE1");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        Organization org = Fixtures.createOrganization("test Org");
        org.setNciInstituteCode("NCI-CODE1");
        StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(org);
        sponsor.setPrimary(true);
        study.addStudyFundingSponsor(sponsor);
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));

        assertFalse(report.getAllSponsorReportsCompleted());

    }

    public void testGetEarliestPendingSponsorReport() throws Exception {

        Report rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        rep.setDueOn(new Timestamp(300));
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        reportDefinition = Fixtures.createReportDefinition("defn2", "NCI-CODE2");
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        rep = new Report();
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        rep.setDueOn(new Timestamp(200));
        reportDefinition = Fixtures.createReportDefinition("defn3", "NCI-CODE1");
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);

        Participant participant = Fixtures.createParticipant("Joe", "Shabadoo");
        Study study = Fixtures.createStudy("El Study");
        Organization org = Fixtures.createOrganization("test Org");
        org.setNciInstituteCode("NCI-CODE1");
        StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(org);
        sponsor.setPrimary(true);
        study.addStudyFundingSponsor(sponsor);
        report.setAssignment(Fixtures.assignParticipant(participant, study, Fixtures.SITE));

        assertEquals(report.getReports().get(2), report.getEarliestPendingSponsorReport());
    }
    /**
     * This method tests {@link ExpeditedAdverseEventReport#isAttributionRequired()}
     */
    public void testIsAttributionRequired_WhenNoReports(){
    	assertTrue(report.getReports().isEmpty());
    	assertFalse(report.isAttributionRequired());
    }
    
    /**
     * This method tests {@link ExpeditedAdverseEventReport#isAttributionRequired()}
     */
    public void testIsAttributionRequired_WhenAllReportsInactive(){
    	Report rep = Fixtures.createReport("test1");
    	rep.getReportDefinition().setAttributionRequired(true);
    	rep.setStatus(ReportStatus.WITHDRAWN);
    	report.addReport(rep);
    	
    	assertFalse(report.getReports().isEmpty());
    	assertFalse(report.isAttributionRequired());
    }
    
    /**
     * This method tests {@link ExpeditedAdverseEventReport#isAttributionRequired()}
     */
    public void testIsAttributionRequired_WhenAllReportsActiveAndAttribution_NO(){
    	Report rep = Fixtures.createReport("test1");
    	rep.getReportDefinition().setAttributionRequired(false);
    	rep.setStatus(ReportStatus.PENDING);
    	report.addReport(rep);
    	
    	assertFalse(report.getReports().isEmpty());
    	assertFalse(report.isAttributionRequired());
    }
    
    /**
     * This method tests {@link ExpeditedAdverseEventReport#isAttributionRequired()}
     */
    public void testIsAttributionRequired_WhenAllReportsActiveAndAttribution_YES(){
    	Report rep = Fixtures.createReport("test1");
    	rep.getReportDefinition().setAttributionRequired(true);
    	rep.setStatus(ReportStatus.PENDING);
    	report.addReport(rep);
    	
    	rep = Fixtures.createReport("test2");
    	rep.getReportDefinition().setAttributionRequired(true);
    	rep.setStatus(ReportStatus.COMPLETED);
    	report.addReport(rep);
    	
    	assertFalse(report.getReports().isEmpty());
    	assertTrue(report.isAttributionRequired());
    }
    
    /**
     * This method tests {@link ExpeditedAdverseEventReport#isAttributionRequired()}
     */
    public void testIsAttributionRequired_WhenAllActiveReportsHaveAttribution_YES(){
    	Report rep = Fixtures.createReport("test1");
    	rep.getReportDefinition().setAttributionRequired(true);
    	rep.setStatus(ReportStatus.PENDING);
    	report.addReport(rep);
    	
    	rep = Fixtures.createReport("test2");
    	rep.getReportDefinition().setAttributionRequired(true);
    	rep.setStatus(ReportStatus.COMPLETED);
    	report.addReport(rep);
    	
    	rep = Fixtures.createReport("test3");
    	rep.getReportDefinition().setAttributionRequired(false);
    	rep.setStatus(ReportStatus.REPLACED);
    	report.addReport(rep);
    	
    	assertFalse(report.getReports().isEmpty());
    	assertTrue(report.isAttributionRequired());
    }
    
    /**
     * This method tests {@link ExpeditedAdverseEventReport#isAttributionRequired()}
     */
    public void testIsAttributionRequired_WhenSomeActiveReportsHaveAttribution_YES(){
    	Report rep = Fixtures.createReport("test1");
    	rep.getReportDefinition().setAttributionRequired(true);
    	rep.setStatus(ReportStatus.PENDING);
    	report.addReport(rep);
    	
    	rep = Fixtures.createReport("test2");
    	rep.getReportDefinition().setAttributionRequired(false);
    	rep.setStatus(ReportStatus.FAILED);
    	report.addReport(rep);
    	
    	rep = Fixtures.createReport("test3");
    	rep.getReportDefinition().setAttributionRequired(false);
    	rep.setStatus(ReportStatus.REPLACED);
    	report.addReport(rep);
    	
    	assertFalse(report.getReports().isEmpty());
    	assertFalse(report.isAttributionRequired());
    }
    
    public void testGetActiveReports(){
    	Report rep = Fixtures.createReport("test1");
    	rep.getReportDefinition().setAttributionRequired(true);
    	rep.setStatus(ReportStatus.PENDING);
    	report.addReport(rep);
    	
    	Report rep2 = Fixtures.createReport("test2");
    	rep2.getReportDefinition().setAttributionRequired(false);
    	rep2.setStatus(ReportStatus.FAILED);
    	report.addReport(rep2);
    	assertNotNull(report.getActiveReports());
    	assertEquals(2,report.getActiveReports().size());
    	assertSame(rep, report.getActiveReports().get(0));
    	assertSame(rep2, report.getActiveReports().get(1));
    	
    }
    
    public void testUpdateSignatureOfAdverseEvents(){
    	assertNull(report.getAdverseEvents().get(0).getSignature());
    	report.updateSignatureOfAdverseEvents();
    	assertNotNull(report.getAdverseEvents().get(0).getSignature());
    	
    }
   
    public void testGetModifiedAdverseEvents(){
    	assertNotNull(report.getModifiedAdverseEvents());
    	assertEquals(1, report.getModifiedAdverseEvents().size());
    }
    
    public void testGetModifiedAdverseEvents_WhenSignatureNotModified(){
    	AdverseEvent ae = report.getAdverseEvents().get(0);
    	ae.setSignature(ae.getCurrentSignature());
    	
    	assertNotNull(report.getModifiedAdverseEvents());
    	assertEquals(0, report.getModifiedAdverseEvents().size());
    }
    
    public void testGetEarliestAdverseEventGradedDate(){
    	assertNull(report.getEarliestAdverseEventGradedDate());
    	Date d = new Date();
    	report.getAdverseEvents().get(0).setGradedDate(d);
    	assertNotNull(report.getEarliestAdverseEventGradedDate());
    }
    
    public void testGetPendingReports(){
    	assertTrue(report.getPendingReports().isEmpty());
    	Report rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.COMPLETED);
        rep.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        rep = new Report();
        rep.setId(2);
        rep.setStatus(ReportStatus.PENDING);
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        assertEquals(1, report.getPendingReports().size());
        assertSame(rep, report.getPendingReports().get(0));
        
    }
    
    public void testUpdateAdverseEventGradedDate(){
    	for(AdverseEvent ae : report.getAdverseEvents()){
    		assertNull(ae.getGradedDate());
    	}
    	Date now = new Date();
    	String strDate = DateUtils.formatDate(now);
    	report.updateAdverseEventGradedDate();
    	for(AdverseEvent ae : report.getAdverseEvents()){
    		assertNotNull(ae.getGradedDate());
    		assertEquals(strDate, DateUtils.formatDate(ae.getGradedDate()));
    	}
    	
    }

    
    public void testFindCompletedAmendableReports(){
    	Report rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.COMPLETED);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        Report rep1 = new Report();
    	rep1.setId(2);
        rep1.setStatus(ReportStatus.COMPLETED);
        ReportDefinition reportDefinition1 = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition1.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition1.setAmendable(false);
        rep1.setReportDefinition(reportDefinition1);
        report.addReport(rep1);
        
        List<Report> reports = report.findCompletedAmendableReports();
        assertEquals(1, reports.size());
        
        
    }
    
    public void testIsAnActiveReportPresent(){
    	Report rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.PENDING);
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setId(55);
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        boolean result = report.isAnActiveReportPresent(reportDefinition);
        assertTrue(result);
    }
    
    
    public void testIsAnActiveReportPresentWhenThereAreReplacedReportsOnly(){
    	Report rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.REPLACED);
        rep.getLastVersion().setReportStatus(ReportStatus.REPLACED);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setId(55);
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        boolean result = report.isAnActiveReportPresent(reportDefinition);
        assertFalse(result);
    }
    
    
    public void testIsAnActiveReportPresentWhenThereAreReplacedWithdrawnReportsOnly(){
    	Report rep =new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.REPLACED);
        rep.getLastVersion().setReportStatus(ReportStatus.REPLACED);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setId(55);
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.WITHDRAWN);
        rep.getLastVersion().setReportStatus(ReportStatus.WITHDRAWN);
        reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setId(55);
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        boolean result = report.isAnActiveReportPresent(reportDefinition);
        assertFalse(result);
    }
    
 
    
    public void testIsAnActiveReportPresentWhenThereAreReplacedWithdrawnAndPendingReportsOnly(){
    	Report rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.REPLACED);
        rep.getLastVersion().setReportStatus(ReportStatus.REPLACED);
        ReportDefinition reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setId(55);
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.WITHDRAWN);
        rep.getLastVersion().setReportStatus(ReportStatus.WITHDRAWN);
        reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setId(55);
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        rep = new Report();
    	rep.setId(1);
        rep.setStatus(ReportStatus.PENDING);
        rep.getLastVersion().setReportStatus(ReportStatus.PENDING);
        reportDefinition = Fixtures.createReportDefinition("defn1", "NCI-CODE1");
        reportDefinition.setId(55);
        reportDefinition.setGroup(Fixtures.createConfigProperty("RT_AdEERS"));
        reportDefinition.setAmendable(true);
        rep.setReportDefinition(reportDefinition);
        report.addReport(rep);
        
        boolean result = report.isAnActiveReportPresent(reportDefinition);
        assertTrue(result);
    }
    
    public void testGetPhysicianSignOff() throws Exception{
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Report report1 = Fixtures.createReport("report 1");
    	report1.getLastVersion().setPhysicianSignoff(true);
    	report1.getReportDefinition().setPhysicianSignOff(true);
    	aeReport.addReport(report1);
    	Report report2 = Fixtures.createReport("report 2");
    	report2.getLastVersion().setPhysicianSignoff(true);
    	report2.getReportDefinition().setPhysicianSignOff(true);
    	aeReport.addReport(report2);
    	assertTrue("Physician-signOff not evaluated correctly", aeReport.getPhysicianSignOff());
    }
    
    public void testIsPhysicianSignOffRequired() throws Exception{
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Report report1 = Fixtures.createReport("report 1");
    	report1.getReportDefinition().setPhysicianSignOff(false);
    	aeReport.addReport(report1);
    	Report report2 = Fixtures.createReport("report 2");
    	report2.getReportDefinition().setPhysicianSignOff(false);
    	aeReport.addReport(report2);
    	assertFalse("isPhysicianSignOffRequired not evaluated correctly", aeReport.isPhysicianSignOffRequired());
    	
    }
    
    public void testSetPhysicianSignOff() throws Exception{
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Report report1 = Fixtures.createReport("report 1");
    	Report report2 = Fixtures.createReport("report 2");
    	aeReport.addReport(report1);
    	aeReport.addReport(report2);
    	aeReport.setPhysicianSignOff(true);
    	assertTrue("Physician sign-off not set correctly", aeReport.getReports().get(0).getLastVersion().getPhysicianSignoff());
    	assertTrue("Physician sign-off not set correctly", aeReport.getReports().get(1).getLastVersion().getPhysicianSignoff());
    }
    
    public void testGetManuallySelectedReports(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	
    	Report report1 = Fixtures.createReport("report 1");
    	report1.setStatus(ReportStatus.PENDING);
    	
    	Report report2 = Fixtures.createReport("report 2");
    	report2.setStatus(ReportStatus.REPLACED);
    	
    	
    	Report report3= Fixtures.createReport("report 3");
    	report3.setStatus(ReportStatus.WITHDRAWN);
    	
    	Report report4= Fixtures.createReport("report 4");
    	report4.setStatus(ReportStatus.COMPLETED);
    	
    	Report report5= Fixtures.createReport("report 5");
    	report5.setStatus(ReportStatus.AMENDED);
    	
    	
    	aeReport.addReport(report1);
    	aeReport.addReport(report2);
    	aeReport.addReport(report3);
    	aeReport.addReport(report4);
    	aeReport.addReport(report5);
    	
    	assertTrue(aeReport.getManuallySelectedReports().isEmpty());
    	
    	report1.setManuallySelected(true);
    	assertTrue(1 == aeReport.getManuallySelectedReports().size());
    	report5.setManuallySelected(true);
    	
    	assertTrue(2 == aeReport.getManuallySelectedReports().size());
    	assertSame(report5, aeReport.getManuallySelectedReports().get(1));
    }
    
    
    public void testGetHasAmendableSubmittedReport(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	
    	ReportDefinition rd = Fixtures.createReportDefinition("test");
    	rd.setAmendable(true);
    	
    	Report report1 = Fixtures.createReport("report 1");
    	report1.setStatus(ReportStatus.REPLACED);
    	report1.getLastVersion().setReportStatus(ReportStatus.REPLACED);
    	report1.setReportDefinition(rd);
    	
    	Report report2 = Fixtures.createReport("report 1");
    	report2.setStatus(ReportStatus.REPLACED);
    	report2.getLastVersion().setReportStatus(ReportStatus.REPLACED);
    	report2.setReportDefinition(rd);
    	
    	
    	Report report3= Fixtures.createReport("report 1");
    	report3.setStatus(ReportStatus.REPLACED);
    	report3.getLastVersion().setReportStatus(ReportStatus.REPLACED);
    	report3.setReportDefinition(rd);
    	
    	aeReport.addReport(report1);
    	aeReport.addReport(report2);
    	aeReport.addReport(report3);
    	assertFalse(aeReport.getHasSubmittedAmendableReport());
    	
    	
    }
    
    
    public void testGetHasAmendableSubmittedReport_WhenNoAmendableReport(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	
    	ReportDefinition rd = Fixtures.createReportDefinition("test");
    	rd.setAmendable(false);
    	
    	Report report1 = Fixtures.createReport("report 1");
    	report1.setStatus(ReportStatus.COMPLETED);
    	report1.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	report1.setReportDefinition(rd);
    	
    	Report report2 = Fixtures.createReport("report 1");
    	report2.setStatus(ReportStatus.COMPLETED);
    	report2.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	report2.setReportDefinition(rd);
    	
    	
    	Report report3= Fixtures.createReport("report 1");
    	report3.setStatus(ReportStatus.COMPLETED);
    	report3.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	report3.setReportDefinition(rd);
    	
    	aeReport.addReport(report1);
    	aeReport.addReport(report2);
    	aeReport.addReport(report3);
    	assertFalse(aeReport.getHasSubmittedAmendableReport());
    	
    	
    }
    

    public void testGetHasAmendableSubmittedReport_WhenAmendableReport(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	
    	ReportDefinition rd = Fixtures.createReportDefinition("test");
    	rd.setAmendable(true);
    	
    	Report report1 = Fixtures.createReport("report 1");
    	report1.setStatus(ReportStatus.REPLACED);
    	report1.getLastVersion().setReportStatus(ReportStatus.REPLACED);
    	report1.setReportDefinition(rd);
    	
    	ReportDefinition rd2 = Fixtures.createReportDefinition("test");
    	rd2.setAmendable(false);
    	Report report2 = Fixtures.createReport("report 1");
    	report2.setStatus(ReportStatus.COMPLETED);
    	report2.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	report2.setReportDefinition(rd2);
    	
    	
    	Report report3= Fixtures.createReport("report 1");
    	report3.setStatus(ReportStatus.COMPLETED);
    	report3.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
    	report3.setReportDefinition(rd);
    	
    	aeReport.addReport(report1);
    	aeReport.addReport(report2);
    	aeReport.addReport(report3);
    	assertTrue(aeReport.getHasSubmittedAmendableReport());
    	
    	
    }
    
    /**
     * RD1 - 10 day 
     * Expedited Data collection having Report-1 associated to RD1
     * 
     * RD2 - 2 Day report
     * 
     * RD3 a 11 day report
     * RD4 a 10 day report
     * 
     * 
     * -----
     * For RD1 - reports to withdraw should return RD1 
     * For RD2 - reports to withdraw should be empty
     * For RD3 - reports to withdraw should be empty
     * For RD4 - reports to withdraw should be empty
     * 
     */
    public void testFindReportsToEdit(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Organization org = Fixtures.createOrganization("test",  1);
    	ConfigProperty type = Fixtures.createConfigProperty("abcd");
    	type.setId(1);
    	
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1");
    	rd1.setId(1);
    	rd1.setOrganization(org);
    	rd1.setDuration(10);
    	rd1.setGroup(type);
    	rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	
    	ReportDefinition rd2 = Fixtures.createReportDefinition("rd2");
    	rd2.setId(2);
    	rd2.setOrganization(org);
    	rd2.setDuration(2);
    	rd2.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd2.setGroup(type);
    	
    	ReportDefinition rd3 = Fixtures.createReportDefinition("rd3");
    	rd3.setId(3);
    	rd3.setOrganization(org);
    	rd3.setDuration(11);
    	rd3.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd3.setGroup(type);
    	
    	ReportDefinition rd4 = Fixtures.createReportDefinition("rd4");
    	rd4.setId(4);
    	rd4.setOrganization(org);
    	rd4.setDuration(10);
    	rd4.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd4.setGroup(type);
    	
    	
    	Report r1 = Fixtures.createReport("r1");
    	r1.setId(1);
    	r1.setReportDefinition(rd1);
    	r1.setStatus(ReportStatus.INPROCESS);
    	r1.getLastVersion().setDueOn(new Date());
    	r1.getLastVersion().setReportStatus(ReportStatus.INPROCESS);
    	
    	aeReport.addReport(r1);
    	
    	List<Report> reportsToWithdraw = aeReport.findReportsToEdit(rd1);
    	assertFalse(reportsToWithdraw.isEmpty());
    	assertEquals(1, reportsToWithdraw.size());
    	assertEquals(rd1, reportsToWithdraw.get(0).getReportDefinition());
    	
    	reportsToWithdraw = aeReport.findReportsToEdit(rd2);
    	assertTrue(reportsToWithdraw.isEmpty());

    	reportsToWithdraw = aeReport.findReportsToEdit(rd3);
    	assertTrue(reportsToWithdraw.isEmpty());

    	reportsToWithdraw = aeReport.findReportsToEdit(rd4);
    	assertTrue(reportsToWithdraw.isEmpty());
    	
    	
    }
    
    /**
     * RD1 - 10 day 
     * Expedited Data collection having Report-1 associated to RD1
     * 
     * RD2 - 2 Day report
     * 
     * RD3 a 11 day report
     * RD4 a 10 day report
     * 
     * RDX - 1 Day report ( belonging to different group)
     * 
     * -----
     * For RD2 - reports to withdraw should return RD1 
     * For RD1 - reports to withdraw should be empty
     * For RD3 - reports to withdraw should be RD1
     * For RD4 - reports to withdraw should be RD1
     * For RDX - reports to withdraw should be empty
     * 
     */
    public void testFindReportsToWithdraw(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Organization org = Fixtures.createOrganization("test",  1);
    	ConfigProperty type = Fixtures.createConfigProperty("abcd");
    	type.setId(1);
    	
    	ConfigProperty typeX = Fixtures.createConfigProperty("x");
    	typeX.setId(3);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1");
    	rd1.setId(1);
    	rd1.setOrganization(org);
    	rd1.setDuration(10);
    	rd1.setGroup(type);
    	rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	
    	ReportDefinition rd2 = Fixtures.createReportDefinition("rd2");
    	rd2.setId(2);
    	rd2.setOrganization(org);
    	rd2.setDuration(2);
    	rd2.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd2.setGroup(type);
    	
    	ReportDefinition rd3 = Fixtures.createReportDefinition("rd3");
    	rd3.setId(3);
    	rd3.setOrganization(org);
    	rd3.setDuration(11);
    	rd3.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd3.setGroup(type);
    	
    	ReportDefinition rd4 = Fixtures.createReportDefinition("rd4");
    	rd4.setId(4);
    	rd4.setOrganization(org);
    	rd4.setDuration(10);
    	rd4.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd4.setGroup(type);
    	
    	ReportDefinition rdx = Fixtures.createReportDefinition("rdx");
    	rdx.setId(22);
    	rdx.setOrganization(org);
    	rdx.setDuration(1);
    	rdx.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rdx.setGroup(typeX);
    	
    	Report r1 = Fixtures.createReport("r1");
    	r1.setId(1);
    	r1.setReportDefinition(rd1);
    	r1.setStatus(ReportStatus.INPROCESS);
    	r1.getLastVersion().setDueOn(new Date());
    	r1.getLastVersion().setReportStatus(ReportStatus.INPROCESS);
    	
    	aeReport.addReport(r1);
    	
    	List<Report> reportsToWithdraw = aeReport.findReportsToWitdraw(rd2);
    	assertFalse(reportsToWithdraw.isEmpty());
    	assertEquals(1, reportsToWithdraw.size());
    	assertEquals(rd1, reportsToWithdraw.get(0).getReportDefinition());
    	
    	reportsToWithdraw = aeReport.findReportsToWitdraw(rd1);
    	assertTrue(reportsToWithdraw.isEmpty());

    	reportsToWithdraw = aeReport.findReportsToWitdraw(rd3);
    	assertFalse(reportsToWithdraw.isEmpty());
    	assertEquals(1, reportsToWithdraw.size());
    	assertEquals(rd1, reportsToWithdraw.get(0).getReportDefinition());
    	
    	reportsToWithdraw = aeReport.findReportsToWitdraw(rd4);
    	assertFalse(reportsToWithdraw.isEmpty());
    	assertEquals(1, reportsToWithdraw.size());
    	assertEquals(rd1, reportsToWithdraw.get(0).getReportDefinition());
    	
    	reportsToWithdraw = aeReport.findReportsToWitdraw(rdx);
    	assertTrue(reportsToWithdraw.isEmpty());
    	
    	
    	
    }
    
    public void testHasExistingReportsOfSameOrganizationAndType(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Organization org = Fixtures.createOrganization("test",  1);
    	ConfigProperty type = Fixtures.createConfigProperty("abcd");
    	type.setId(1);
    	
    	ConfigProperty typeX = Fixtures.createConfigProperty("x");
    	typeX.setId(3);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1");
    	rd1.setId(1);
    	rd1.setOrganization(org);
    	rd1.setDuration(10);
    	rd1.setGroup(type);
    	rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	assertFalse(aeReport.hasExistingReportsOfSameOrganizationAndType(rd1));
    }
    

    public void testHasExistingReportsOfSameOrganizationAndType_WhenThereIsOne(){
    	ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
    	Organization org = Fixtures.createOrganization("test",  1);
    	ConfigProperty type = Fixtures.createConfigProperty("abcd");
    	type.setId(1);
    	
    	ConfigProperty typeX = Fixtures.createConfigProperty("x");
    	typeX.setId(3);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1");
    	rd1.setId(1);
    	rd1.setOrganization(org);
    	rd1.setDuration(10);
    	rd1.setGroup(type);
    	rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	
    	ReportDefinition rd2 = Fixtures.createReportDefinition("rd2");
    	rd2.setId(2);
    	rd2.setOrganization(org);
    	rd2.setDuration(2);
    	rd2.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd2.setGroup(type);
    	
    	ReportDefinition rd3 = Fixtures.createReportDefinition("rd3");
    	rd3.setId(3);
    	rd3.setOrganization(org);
    	rd3.setDuration(11);
    	rd3.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd3.setGroup(type);
    	
    	ReportDefinition rd4 = Fixtures.createReportDefinition("rd4");
    	rd4.setId(4);
    	rd4.setOrganization(org);
    	rd4.setDuration(10);
    	rd4.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rd4.setGroup(type);
    	
    	ReportDefinition rdx = Fixtures.createReportDefinition("rdx");
    	rdx.setId(22);
    	rdx.setOrganization(org);
    	rdx.setDuration(1);
    	rdx.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rdx.setGroup(typeX);
    	
    	Report r1 = Fixtures.createReport("r1");
    	r1.setId(1);
    	r1.setReportDefinition(rd1);
    	r1.setStatus(ReportStatus.INPROCESS);
    	r1.getLastVersion().setDueOn(new Date());
    	r1.getLastVersion().setReportStatus(ReportStatus.INPROCESS);
    	
    	aeReport.addReport(r1);
    	
    	assertTrue(aeReport.hasExistingReportsOfSameOrganizationAndType(rd1));
    	assertTrue(aeReport.hasExistingReportsOfSameOrganizationAndType(rd2));
    	assertTrue(aeReport.hasExistingReportsOfSameOrganizationAndType(rd3));
    	assertTrue(aeReport.hasExistingReportsOfSameOrganizationAndType(rd4));
    	assertFalse(aeReport.hasExistingReportsOfSameOrganizationAndType(rdx));
    	
    }
}
