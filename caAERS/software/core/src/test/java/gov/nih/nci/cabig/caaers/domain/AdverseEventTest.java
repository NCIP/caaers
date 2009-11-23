package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.AE_DATA_COLLECTION;
import edu.nwu.bioinformatics.commons.CollectionUtils;
import edu.nwu.bioinformatics.commons.DateUtils;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Biju Joseph
 */
@CaaersUseCases({AE_DATA_COLLECTION})
public class AdverseEventTest extends AbstractTestCase {
    private AdverseEvent adverseEvent;
    private String comments;
    private String detailsForOther;
    private Boolean expected;
    private Boolean requiresReporting;
    private Grade grade;
    private Attribution attributionSummary;
    private TimeValue eventApproximateTime;
    private String eventLocation;
    private Date startDate;
    private Date endDate;
    private Boolean solicited;
    private OutcomeType serious;
    private Hospitalization hospitalization;
    private LowLevelTerm lowLevelTerm;
    private Outcome outcome1, outcome2;
    private AdverseEventReportingPeriod reportingPeriod;
    private ExpeditedAdverseEventReport report;

    private RoutineAdverseEventReport routineReport;
    private AbstractAdverseEventTerm adverseEventTerm;


    private DeviceAttribution deviceAttribution;
    private ConcomitantMedicationAttribution concomitantMedicationAttribution;
    private DiseaseAttribution diseaseAttribution;
    private CourseAgentAttribution courseAgentAttribution;
    private OtherCauseAttribution otherCauseAttribution;
    private SurgeryAttribution surgeryAttribution;
    private RadiationAttribution radiationAttribution;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        routineReport = new RoutineAdverseEventReport();
        routineReport.setId(1);
        report = new ExpeditedAdverseEventReport();
        outcome1 = new Outcome();
        outcome1.setOutcomeType(OutcomeType.DISABILITY);
        outcome2 = new Outcome();
        outcome2.setOutcomeType(OutcomeType.DEATH);

        adverseEventTerm = new AdverseEventCtcTerm();
        adverseEventTerm.setId(1);
        deviceAttribution = new DeviceAttribution();
        deviceAttribution.setAttribution(Attribution.DEFINITE);

        radiationAttribution = new RadiationAttribution();
        radiationAttribution.setAttribution(Attribution.PROBABLE);

        surgeryAttribution = new SurgeryAttribution();
        surgeryAttribution.setAttribution(Attribution.DEFINITE);

        concomitantMedicationAttribution = new ConcomitantMedicationAttribution();
        concomitantMedicationAttribution.setAttribution(Attribution.PROBABLE);

        diseaseAttribution = new DiseaseAttribution();
        diseaseAttribution.setAttribution(Attribution.DEFINITE);

        otherCauseAttribution = new OtherCauseAttribution();
        otherCauseAttribution.setAttribution(Attribution.PROBABLE);

        courseAgentAttribution = new CourseAgentAttribution();
        courseAgentAttribution.setAttribution(Attribution.DEFINITE);


        reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setId(1);
        serious = OutcomeType.DEATH;
        comments = "comments";
        expected = true;
        solicited = true;
        eventLocation = "eventLocation";
        requiresReporting = true;
        attributionSummary = Attribution.POSSIBLE;
        detailsForOther = "detailsForOther";
        grade = Grade.DEATH;
        hospitalization = Hospitalization.YES;
        eventApproximateTime = new TimeValue();
        eventApproximateTime.setHour(3);
        eventApproximateTime.setMinute(2);
        lowLevelTerm = new LowLevelTerm();
        lowLevelTerm.setIcd9Code("icd code");
        adverseEvent = new AdverseEvent();
        adverseEvent.setHospitalization(hospitalization);
        adverseEvent.setSerious(serious);
        adverseEvent.setGrade(grade);
        adverseEvent.addOutcome(outcome1);
        adverseEvent.addOutcome(outcome2);
        adverseEvent.setId(1);
        adverseEvent.setVersion(2);
        adverseEvent.setLowLevelTerm(lowLevelTerm);
        adverseEvent.setEventApproximateTime(eventApproximateTime);
        adverseEvent.setAttributionSummary(attributionSummary);
        adverseEvent.setGridId("grid id");
        adverseEvent.setComments(comments);
        adverseEvent.setDetailsForOther(detailsForOther);
        adverseEvent.setExpected(expected);
        adverseEvent.setSolicited(solicited);
        adverseEvent.setEventLocation(eventLocation);
        adverseEvent.setRequiresReporting(requiresReporting);
        startDate = DateUtils.createDate(2008, Calendar.NOVEMBER, 2);
        endDate = DateUtils.createDate(2008, Calendar.NOVEMBER, 3);
        adverseEvent.setStartDate(startDate);
        adverseEvent.setEndDate(endDate);
        adverseEvent.setReport(report);
        adverseEvent.setReportingPeriod(reportingPeriod);
        adverseEvent.addAdverseEventAttribution(deviceAttribution);
        adverseEvent.addAdverseEventAttribution(radiationAttribution);
        adverseEvent.addAdverseEventAttribution(concomitantMedicationAttribution);
        adverseEvent.addAdverseEventAttribution(otherCauseAttribution);
        adverseEvent.addAdverseEventAttribution(diseaseAttribution);
        adverseEvent.addAdverseEventAttribution(surgeryAttribution);
        adverseEvent.addAdverseEventAttribution(courseAgentAttribution);


    }
    
    public void testDefaultExpectedness() throws Exception {
        adverseEvent = new AdverseEvent();
        assertNull(adverseEvent.getExpected());
    }
    
    public void testGetStartDateAsString(){
    	assertEquals("11/02/2008", adverseEvent.getStartDateAsString());
    }
    
    public void testSetDateAsString(){
    	adverseEvent.setStartDateAsString("x");
    	assertEquals("11/02/2008", adverseEvent.getStartDateAsString());
    }
    
    public void testGetStartDateAsStringWhenDateIsNull(){
    	adverseEvent.setStartDate(null);
    	adverseEvent.getStartDateAsString();
    }
    
    public void testToString(){
    	assertEquals("{id :null, Grade : null, Hospitalization: null, attribution : null, expected : null}",new AdverseEvent().toString());
    	AdverseEvent e = new AdverseEvent();
    	e.setId(5);
    	assertEquals("{id :5, Grade : null, Hospitalization: null, attribution : null, expected : null}",e.toString());
    	
    }
    
    
    public void testGetCurrentSignature(){
    	AdverseEvent ae = new AdverseEvent();
    	assertEquals("$$$$$$$$$$$$$$$$$$$$$$", ae.getCurrentSignature());
    	assertEquals("detailsForOther$$DEATH$$POSSIBLE$$YES$$true$$$$11/02/2008$$11/03/2008$$03$$02$$eventLocation$$",adverseEvent.getCurrentSignature());
    }
    
    public void testInitializeGradedDate(){
    	AdverseEvent ae = new AdverseEvent();
    	assertNull(ae.getGradedDate());
    	ae.initailzeGradedDate();
    	assertNull(ae.getGradedDate());
    }
    
    public void testInitializeGradedDate_WhenGraded(){
    	assertNull(adverseEvent.getGradedDate());
    	adverseEvent.initailzeGradedDate();
    	assertNotNull(adverseEvent.getGradedDate());
    }
    
    public void testInitializeGradedDate_WhenGradedNotEvaluated(){
    	assertNull(adverseEvent.getGradedDate());
    	adverseEvent.setGrade(Grade.NOT_EVALUATED);
    	adverseEvent.initailzeGradedDate();
    	assertNull(adverseEvent.getGradedDate());
    }
    
    public void testGetAssocitatedReportNames(){
    	assertTrue(adverseEvent.getAssociatedReportNames().isEmpty());
    	Report r = Fixtures.createReport("test");
    	r.setStatus(ReportStatus.PENDING);
    	report.addReport(r);
    	assertFalse(adverseEvent.getAssociatedReportNames().isEmpty());
    	assertEquals(1, adverseEvent.getAssociatedReportNames().size());
    	assertEquals("test", adverseEvent.getAssociatedReportNames().get(0));
    }
    
    
    public void testInitializePostSubmissionUpdatedDate(){
    	AdverseEvent ae = new AdverseEvent();
    	assertNull(ae.getPostSubmissionUpdatedDate());
    	ae.initializePostSubmissionUpdatedDate();
    	assertNull(ae.getPostSubmissionUpdatedDate());
    }
    
    public void testInitializePostSubmissionUpdatedDate_WhenAEModified(){
    	AdverseEvent ae = new AdverseEvent();
    	ae.setGrade(Grade.NORMAL);
    	assertNull(ae.getPostSubmissionUpdatedDate());
    	assertTrue(ae.isModified());
    	ae.initializePostSubmissionUpdatedDate();
    	assertNull(ae.getPostSubmissionUpdatedDate());
    }
    
    public void testInitializePostSubmissionUpdatedDate_WhenGraded(){
    	AdverseEvent ae = new AdverseEvent();
    	ae.setGrade(Grade.LIFE_THREATENING);
    	assertNull(ae.getPostSubmissionUpdatedDate());
    	assertTrue(ae.isModified());
    	ae.initializePostSubmissionUpdatedDate();
    	assertNotNull(ae.getPostSubmissionUpdatedDate());
    }
    
    
    public void testInitializePostSubmissionUpdatedDate_Same(){
    	AdverseEvent ae = new AdverseEvent();
    	Date d = new Date();
    	ae.setPostSubmissionUpdatedDate(d);
    	ae.setGrade(Grade.LIFE_THREATENING);
    	assertTrue(ae.isModified());
    	ae.initializePostSubmissionUpdatedDate();
    	assertNotNull(ae.getPostSubmissionUpdatedDate());
    	assertSame(d, ae.getPostSubmissionUpdatedDate());
    }
    
//
//    public void testCopyAdverseEventTerm() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//        assertNull("must not create ctc term  and medra term by default", copiedAdverseEvent.getAdverseEventTerm());
//
//        adverseEvent.setAdverseEventTerm(adverseEventTerm);
//        copiedAdverseEvent = adverseEvent.copy();
//        assertNotNull(copiedAdverseEvent.getAdverseEventTerm());
//        assertNotSame("adverse event term object must not be same", adverseEventTerm, copiedAdverseEvent.getAdverseEventTerm());
//
//        assertNotEquals("adverse event term object must not be same", adverseEventTerm, copiedAdverseEvent.getAdverseEventTerm());
//
//        assertSame("adverse must be same", copiedAdverseEvent, copiedAdverseEvent.getAdverseEventTerm().getAdverseEvent());
//    }
//
//    public void testCopyAdverseEventAttribution() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//        assertEquals("number of adverse events attributions must be same", 7, adverseEvent.getAdverseEventAttributions().size());
//
//        assertEquals("number of adverse events attributions must be same", 7, copiedAdverseEvent.getAdverseEventAttributions().size());
//
//        assertEquals("number of deviceAttribution1 must be same", adverseEvent.getDeviceAttributions().size(), copiedAdverseEvent.getDeviceAttributions().size());
//        assertEquals("attribute of deviceAttribution1 must have same value", deviceAttribution.getAttribution(), copiedAdverseEvent.getDeviceAttributions().get(0).getAttribution());
//
//        assertNotSame("deviceAttribution object must not refer to same object", deviceAttribution, copiedAdverseEvent.getDeviceAttributions().get(0));
//        assertNotEquals("deviceAttribution object must not refer to same object", deviceAttribution, copiedAdverseEvent.getDeviceAttributions().get(0));
//        assertSame("adverse must be same", copiedAdverseEvent, copiedAdverseEvent.getDeviceAttributions().get(0).getAdverseEvent());
//
//        assertNotSame("radiationAttribution object must not refer to same object", radiationAttribution, copiedAdverseEvent.getRadiationAttributions().get(0));
//        assertNotEquals("radiationAttribution object must not refer to same object", radiationAttribution, copiedAdverseEvent.getRadiationAttributions().get(0));
//        assertSame("adverse must be same", copiedAdverseEvent, copiedAdverseEvent.getRadiationAttributions().get(0).getAdverseEvent());
//
//        assertNotSame("surgeryAttribution object must not refer to same object", surgeryAttribution, copiedAdverseEvent.getSurgeryAttributions().get(0));
//        assertNotEquals("surgeryAttribution object must not refer to same object", surgeryAttribution, copiedAdverseEvent.getSurgeryAttributions().get(0));
//        assertSame("adverse must be same", copiedAdverseEvent, copiedAdverseEvent.getSurgeryAttributions().get(0).getAdverseEvent());
//
//        assertNotSame("diseaseAttribution object must not refer to same object", diseaseAttribution, copiedAdverseEvent.getDiseaseAttributions().get(0));
//        assertNotEquals("diseaseAttribution object must not refer to same object", diseaseAttribution, copiedAdverseEvent.getDiseaseAttributions().get(0));
//        assertSame("adverse must be same", copiedAdverseEvent, copiedAdverseEvent.getDiseaseAttributions().get(0).getAdverseEvent());
//
//        assertNotSame("courseAgentAttribution object must not refer to same object", courseAgentAttribution, copiedAdverseEvent.getCourseAgentAttributions().get(0));
//        assertNotEquals("courseAgentAttribution object must not refer to same object", radiationAttribution, copiedAdverseEvent.getCourseAgentAttributions().get(0));
//        assertSame("adverse must be same", copiedAdverseEvent, copiedAdverseEvent.getCourseAgentAttributions().get(0).getAdverseEvent());
//
//
//        assertNotSame("concomitantMedicationAttribution object must not refer to same object", concomitantMedicationAttribution, copiedAdverseEvent.getConcomitantMedicationAttributions().get(0));
//        assertNotEquals("concomitantMedicationAttribution object must not refer to same object", concomitantMedicationAttribution, copiedAdverseEvent.getConcomitantMedicationAttributions().get(0));
//        assertSame("adverse must be same", copiedAdverseEvent, copiedAdverseEvent.getConcomitantMedicationAttributions().get(0).getAdverseEvent());
//
//
//    }
//
//    public void testCopyForRoutineReport() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//        assertSame("routineReport must refer to same object", routineReport, copiedAdverseEvent.getRoutineReport());
//        assertEquals("routineReport must refer to same object", routineReport, copiedAdverseEvent.getRoutineReport());
//
//    }
//
//    public void testCopyForReportingPeriod() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//        assertSame("reportingPeriod must refer to same object", reportingPeriod, copiedAdverseEvent.getReportingPeriod());
//        assertEquals("reportingPeriod must refer to same object", reportingPeriod, copiedAdverseEvent.getReportingPeriod());
//
//    }
//
//    public void testCopyForReport() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//        assertNotNull(adverseEvent.getReport());
//        assertNull("must not copy the report", copiedAdverseEvent.getReport());
//
//    }
//
//    public void testCopyForOutCome() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//
//        assertEquals("number of outcomes must be same", adverseEvent.getOutcomes().size(), copiedAdverseEvent.getOutcomes().size());
//        assertEquals("attribute of outcomes must have same value", outcome1.getOutcomeType(), copiedAdverseEvent.getOutcomes().get(0).getOutcomeType());
//
//        assertNotSame("outcome object must not refer to same object", outcome1, copiedAdverseEvent.getOutcomes().get(0));
//        assertNotEquals("outcome object must not refer to same object", outcome1, copiedAdverseEvent.getOutcomes().get(0));
//
//    }
//
//    public void testMustNotCopyIdGridIdAndVersionNumber() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//
//        assertNotNull(adverseEvent.getId());
//
//        assertNotNull(adverseEvent.getGridId());
//        assertNotNull(adverseEvent.getVersion());
//
//        assertNull(copiedAdverseEvent.getId());
//        assertNull(copiedAdverseEvent.getGridId());
//        assertNull("version number must be null", copiedAdverseEvent.getVersion());
//    }
//
//    public void testCopyForBasicProperties() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//
//
//        assertEquals("hospitalization must be same", hospitalization, copiedAdverseEvent.getHospitalization());
//
//        assertEquals("comments must be same", comments, copiedAdverseEvent.getComments());
//        assertEquals("details must be same", detailsForOther, copiedAdverseEvent.getDetailsForOther());
//        assertEquals("expected must be same", expected, copiedAdverseEvent.getExpected());
//        assertEquals("start date must be same", startDate, copiedAdverseEvent.getStartDate());
//        assertEquals("end date must be same", endDate, copiedAdverseEvent.getEndDate());
//        assertEquals("serious must be same", serious, copiedAdverseEvent.getSerious());
//
//        assertEquals("solicited must be same", solicited, copiedAdverseEvent.getSolicited());
//        assertEquals("eventLocation must be same", eventLocation, copiedAdverseEvent.getEventLocation());
//        assertEquals("requiresReporting must be same", requiresReporting, copiedAdverseEvent.getRequiresReporting());
//
//        assertEquals("attributionSummary must be same", attributionSummary, copiedAdverseEvent.getAttributionSummary());
//
//
//        assertEquals("grade must be same", grade, copiedAdverseEvent.getGrade());
//
//        assertEquals("low level term must be equal", adverseEvent.getLowLevelTerm(), copiedAdverseEvent.getLowLevelTerm());
//        assertSame("low level term must refer to same object", lowLevelTerm, copiedAdverseEvent.getLowLevelTerm());
//
//        assertNotSame("both adverse event must not refer to same object", copiedAdverseEvent, adverseEvent);
//        assertNotEquals("both adverse event must not refer to same object", copiedAdverseEvent, adverseEvent);
//
//    }
//
//    public void testCopyForEventApproximationtime() {
//
//        AdverseEvent copiedAdverseEvent = adverseEvent.copy();
//
//
//        assertSame("eventApproximateTime must   refer same objects", eventApproximateTime, copiedAdverseEvent.getEventApproximateTime());
//
//        assertEquals("eventApproximateTime must  refer same object ", eventApproximateTime, copiedAdverseEvent.getEventApproximateTime());
//
//
//    }
    public void testCollectionUtils(){
    	List<Object> l1 = null;
    	List<Object> l2 = new ArrayList<Object>();
    	List<Object> l3 = new ArrayList<Object>();
    	l3.add(2);
    	assertTrue(CollectionUtils.isEmpty(l1));
    	assertTrue(CollectionUtils.isEmpty(l2));
    	assertFalse(CollectionUtils.isEmpty(l3));
    }
    
    public void testGetDisplayName(){
    	
    	AdverseEvent ae = Fixtures.createAdverseEvent(1, Grade.DEATH);
    	ae.getAdverseEventCtcTerm().getTerm().setOtherRequired(true);
    	assertEquals("abcd", ae.getDisplayName());
    	ae.setDetailsForOther("hello");
    	assertEquals("abcd, hello", ae.getDisplayName());
    	
    }

}
