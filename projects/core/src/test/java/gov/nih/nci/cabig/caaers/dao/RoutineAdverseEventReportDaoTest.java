package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Fixtures;

import java.util.Calendar;
import java.sql.Timestamp;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.*;
import edu.nwu.bioinformatics.commons.testing.CoreTestCase;
import edu.nwu.bioinformatics.commons.DateUtils;

/**
 * @author Krikor Krumlian
 */
public class RoutineAdverseEventReportDaoTest extends DaoTestCase<RoutineAdverseEventReportDao> {
    private CtcTermDao ctcTermDao = (CtcTermDao) getApplicationContext().getBean("ctcTermDao");
    private StudyParticipantAssignmentDao assignmentDao
        = (StudyParticipantAssignmentDao) getApplicationContext().getBean("studyParticipantAssignmentDao");
    private AgentDao agentDao = (AgentDao) getApplicationContext().getBean("agentDao");
   // private ReportDefinitionDao reportDefinitionDao
   //     = (ReportDefinitionDao) getApplicationContext().getBean("reportDefinitionDao");

    public void testGet() throws Exception {
        RoutineAdverseEventReport loaded = getDao().getById(-1);
        assertEquals("Wrong AE 0", -70, (int) loaded.getAdverseEvents().get(0).getId());
        assertEquals("Wrong AE 1", -11, (int) loaded.getAdverseEvents().get(1).getId());
        assertEquals("Wrong assignment", -14, (int) loaded.getAssignment().getId());
        CoreTestCase.assertDayOfDate("Wrong start date", 2004, Calendar.MAY, 12,
            loaded.getStartDate());
        CoreTestCase.assertDayOfDate("Wrong end date", 2004, Calendar.AUGUST, 12,
                loaded.getEndDate());
    }

    /*
    public void testGetLabs() throws Exception {
        ExpeditedAdverseEventReport loaded = getDao().getById(-1);
        assertEquals("Wrong number of labs", 3, loaded.getLabs().size());
        assertEquals("Wrong lab 0", -21, (int) loaded.getLabs().get(0).getId());
        assertEquals("Wrong lab 1", -20, (int) loaded.getLabs().get(1).getId());
        assertEquals("Wrong lab 2", -22, (int) loaded.getLabs().get(2).getId());

        Lab l1 = loaded.getLabs().get(1);
        assertSame("Wrong report", loaded, l1.getReport());
        assertEquals("Wrong name", "Extent", l1.getName());
        assertEquals("Wrong units", "hectares/liter", l1.getUnits());
        assertLabValue("Wrong baseline", "3.66", 2003, Calendar.APRIL, 17, l1.getBaseline());
        assertLabValue("Wrong nadir", "0.4", 2007, Calendar.MARCH, 14, l1.getNadir());
        assertLabValue("Wrong nadir", "3.54", 2007, Calendar.MARCH, 19, l1.getRecovery());
    }

    private static void assertLabValue(
        String message, String expectedValue, int expectedYear, int expectedMonth, int expectedDay,
        LabValue actual
    ) {
        assertDayOfDate(prependMessage(message) + "wrong date",
            expectedYear, expectedMonth, expectedDay, actual.getDate());
        assertEquals(prependMessage(message) + "wrong value", expectedValue, actual.getValue());
    }

    public void testGetConcomitantMedications() throws Exception {
        ExpeditedAdverseEventReport loaded = getDao().getById(-1);
        assertEquals("Wrong number of concomitant meds", 2, loaded.getConcomitantMedications().size());
        assertEquals("Wrong con med 0", -31, (int) loaded.getConcomitantMedications().get(0).getId());
        assertEquals("Wrong con med 1", -30, (int) loaded.getConcomitantMedications().get(1).getId());

        ConcomitantMedication cm1 = loaded.getConcomitantMedications().get(1);
        assertSame("Wrong report", loaded, cm1.getReport());
        assertEquals("Wrong agent", -101, (int) cm1.getAgent().getId());
        assertNull("Wrong other", cm1.getOther());
    }

    public void testGetTreatmentInformation() throws Exception {
        ExpeditedAdverseEventReport loaded = getDao().getById(-1);
        assertNotNull("No treatment info", loaded.getTreatmentInformation());

        TreatmentInformation actual = loaded.getTreatmentInformation();
        assertEquals("Wrong treatment information", -10, (int) actual.getId());
        assertDayOfDate("Wrong first course date", 2005, Calendar.JUNE, 4, actual.getFirstCourseDate());
        assertDayOfDate("Wrong adverse event course date", 2006, Calendar.JULY, 9, actual.getAdverseEventCourse().getDate());
        assertEquals("Wrong adverse event course number", 8, (int) actual.getAdverseEventCourse().getNumber());

        assertEquals("Wrong number of course agents", 2, actual.getCourseAgents().size());
        assertEquals("Wrong course agent 0", -19, (int) actual.getCourseAgents().get(0).getId());

        CourseAgent agent1 = actual.getCourseAgents().get(1);
        assertEquals("Wrong course agent 1", -20, (int) agent1.getId());
        assertEquals("Wrong delay in minutes", new BigDecimal(240), agent1.getAdministrationDelay());
        assertEquals("Wrong delay amount", new BigDecimal(4), agent1.getAdministrationDelayAmount());
        assertEquals("Wrong delay units", DelayUnits.HOURS, agent1.getAdministrationDelayUnits());
        assertEquals("Wrong dose amount", new BigDecimal("17.4"), agent1.getDose().getAmount());
        assertEquals("Wrong dose units", "mg", agent1.getDose().getUnits());
        assertEquals("Wrong dose route", "aural", agent1.getDose().getRoute());
        assertEquals("Wrong duration", "8 times every third hour", agent1.getDurationAndSchedule());

        assertEquals("Wrong modified dose amount", new BigDecimal("10"), agent1.getModifiedDose().getAmount());
        assertEquals("Wrong modified dose units", "mg", agent1.getModifiedDose().getUnits());
        assertEquals("Wrong modified dose route", "aural", agent1.getModifiedDose().getRoute());

        assertEquals("Wrong total dose", new BigDecimal("7"), agent1.getTotalDoseAdministeredThisCourse());
        assertDayOfDate("Wrong last administered date", 2006, Calendar.JULY, 10, agent1.getLastAdministeredDate());
    }

    public void testGetOtherCauses() throws Exception {
        ExpeditedAdverseEventReport loaded = getDao().getById(-1);

        assertEquals("Wrong number of causes", 3, loaded.getOtherCauses().size());
        assertEquals("Wrong cause 0", -81, (int) loaded.getOtherCauses().get(0).getId());
        assertEquals("Wrong cause 1", -80, (int) loaded.getOtherCauses().get(1).getId());
        assertEquals("Wrong cause 2", -82, (int) loaded.getOtherCauses().get(2).getId());

        assertEquals("Wrong text for cause 1", "Crossed against light",
            loaded.getOtherCauses().get(1).getText());
    }

    public void testGetResponseDescription() throws Exception {
        AdverseEventResponseDescription actual = getDao().getById(-1).getResponseDescription();
        assertEquals("Wrong present status", PostAdverseEventStatus.RECOVERED_WITH_SEQUELAE,
            actual.getPresentStatus());
        assertEquals("Wrong event description", "It was real bad", actual.getEventDescription());
        assertEquals("Wrong retreated flag", Boolean.FALSE, actual.getRetreated());
        assertDayOfDate("Wrong date removed", 2012, Calendar.MARCH, 4, actual.getRecoveryDate());
    }

    public void testGetReports() throws Exception {
        List<Report> actual = getDao().getById(-1).getReports();
        assertNotNull(actual);
        assertEquals("Wrong number of reports", 2, actual.size());
        assertEquals("Wrong report 0", -40, (int) actual.get(0).getId());

        Report actualReport1 = actual.get(1);
        assertNotNull(actualReport1);
        assertEquals("Wrong report 1", -41, (int) actualReport1.getId());
        assertEquals("Wrong def for report 1", -30,
            (int) actualReport1.getReportDefinition().getId());
        assertDayOfDate("Wrong due date for report 1", 2007, Calendar.MAY, 5, actualReport1.getDueOn());
    }

    public void testGetReporter() throws Exception {
        Reporter actual = getDao().getById(-1).getReporter();
        assertNotNull("No reporter", actual);
        assertEquals("Wrong reporter", -100, (int) actual.getId());
        assertEquals("DiMaggio", actual.getLastName());
        assertEquals("Wrong number of contact mechanisms", 2, actual.getContactMechanisms().size());
        assertEquals("joltin@joe.com", actual.getContactMechanisms().get(ExpeditedReportPerson.EMAIL));
        assertEquals("212 555-1212", actual.getContactMechanisms().get(ExpeditedReportPerson.PHONE));
    }

    public void testGetPhysician() throws Exception {
        Physician actual = getDao().getById(-1).getPhysician();
        assertNotNull("No physician", actual);
        assertEquals("Wrong reporter", -101, (int) actual.getId());
        assertEquals("Sandpiper", actual.getLastName());
        assertEquals("Wrong number of contact mechanisms", 0, actual.getContactMechanisms().size());
    }
    */

    public void testSave() throws Exception {
        doSaveTest(new SaveTester() {
            public void setupReport(RoutineAdverseEventReport report) {
                CtcTerm term = ctcTermDao.getById(3012);
                AdverseEvent event0 = new AdverseEvent();
                event0.setGrade(Grade.MILD);
                event0.setCtcTerm(term);
                event0.setExpected(Boolean.FALSE);
                event0.setHospitalization(Hospitalization.PROLONGED_HOSPITALIZATION);

                AdverseEvent event1 = new AdverseEvent();
                event1.setGrade(Grade.SEVERE);
                event1.setCtcTerm(term);
                event1.setExpected(Boolean.FALSE);
                event1.setHospitalization(Hospitalization.HOSPITALIZATION);

                report.getAdverseEvents().clear();
                report.addAdverseEvent(event0);
                report.addAdverseEvent(event1);
                report.setAssignment(assignmentDao.getById(-14));
                report.setStartDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));
                report.setEndDate(new Timestamp(DateUtils.createDate(2005, Calendar.APRIL, 25).getTime() + 600000));
            }

            public void assertCorrect(RoutineAdverseEventReport loaded) {
                assertEquals("Wrong assignment", -14, (int) loaded.getAssignment().getId());
                assertDayOfDate("Wrong day for loaded date", 2004, Calendar.APRIL, 25, loaded.getStartDate());
                assertDayOfDate("Wrong day for loaded date", 2005, Calendar.APRIL, 25, loaded.getEndDate());
                
                assertEquals("Wrong number of AEs", 2, loaded.getAdverseEvents().size());
                AdverseEvent loadedEvent0 = loaded.getAdverseEvents().get(0);
                assertNotNull("Cascaded AE not found", loadedEvent0);
                assertEquals("Wrong grade", Grade.MILD, loadedEvent0.getGrade());
                assertEquals("Wrong CTC term", 3012, (int) loadedEvent0.getCtcTerm().getId());
                assertEquals("Wrong hospitalization", Hospitalization.PROLONGED_HOSPITALIZATION, loadedEvent0.getHospitalization());
                assertEquals("Wrong expectedness", Boolean.FALSE, loadedEvent0.getExpected());
                assertNotNull("Second cascaded AE not found", loaded.getAdverseEvents().get(1));
            }
        });
    }
    
    private void doSaveTest(SaveTester tester) {
        Integer savedId;
        {
            RoutineAdverseEventReport report = createMinimalAeReport();

            tester.setupReport(report);

            getDao().save(report);
            assertNotNull("No ID for new report", report.getId());
            savedId = report.getId();
        }

        interruptSession();

        {
        	RoutineAdverseEventReport loaded = getDao().getById(savedId);
            assertNotNull("Saved report not found", loaded);
            tester.assertCorrect(loaded);
        }
    }

    private RoutineAdverseEventReport createMinimalAeReport() {
    	RoutineAdverseEventReport report = Fixtures.createSavableRoutineReport();
        report.setAssignment(assignmentDao.getById(-14));
        report.getAdverseEvents().get(0).setCtcTerm(ctcTermDao.getById(3012));
        return report;
    }


    private static interface SaveTester {
        void setupReport(RoutineAdverseEventReport report);
        void assertCorrect(RoutineAdverseEventReport loaded);
    }
    
}
