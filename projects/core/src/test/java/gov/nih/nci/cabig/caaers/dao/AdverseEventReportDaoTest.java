package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;

import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.*;
import edu.nwu.bioinformatics.commons.testing.CoreTestCase;
import edu.nwu.bioinformatics.commons.DateUtils;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventReportDaoTest extends DaoTestCase<AdverseEventReportDao> {
    private CtcTermDao ctcTermDao = (CtcTermDao) getApplicationContext().getBean("ctcTermDao");
    private StudyParticipantAssignmentDao assignmentDao
        = (StudyParticipantAssignmentDao) getApplicationContext().getBean("studyParticipantAssignmentDao");
    private AgentDao agentDao = (AgentDao) getApplicationContext().getBean("agentDao");

    public void testGet() throws Exception {
        AdverseEventReport loaded = getDao().getById(-1);
        assertEquals("Wrong AE 0", -70, (int) loaded.getAdverseEvents().get(0).getId());
        assertEquals("Wrong AE 1", -11, (int) loaded.getAdverseEvents().get(1).getId());
        assertEquals("Wrong assignment", -14, (int) loaded.getAssignment().getId());
        CoreTestCase.assertDayOfDate("Wrong created at (date)", 2004, Calendar.SEPTEMBER, 4,
            loaded.getCreatedAt());
        CoreTestCase.assertTimeOfDate("Wrong created at (time)", 13, 15, 30, 0,
            loaded.getCreatedAt());
    }

    public void testGetLabs() throws Exception {
        AdverseEventReport loaded = getDao().getById(-1);
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

    public void testGetConcomitantMedications() throws Exception {
        AdverseEventReport loaded = getDao().getById(-1);
        assertEquals("Wrong number of concomitant meds", 2, loaded.getConcomitantMedications().size());
        assertEquals("Wrong con med 0", -31, (int) loaded.getConcomitantMedications().get(0).getId());
        assertEquals("Wrong con med 1", -30, (int) loaded.getConcomitantMedications().get(1).getId());

        ConcomitantMedication cm1 = loaded.getConcomitantMedications().get(1);
        assertSame("Wrong report", loaded, cm1.getReport());
        assertEquals("Wrong agent", -101, (int) cm1.getAgent().getId());
        assertNull("Wrong other", cm1.getOther());
    }

    public void testGetTreatmentInformation() throws Exception {
        AdverseEventReport loaded = getDao().getById(-1);
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
        AdverseEventReport loaded = getDao().getById(-1);

        assertEquals("Wrong number of causes", 3, loaded.getOtherCauses().size());
        assertEquals("Wrong cause 0", -81, (int) loaded.getOtherCauses().get(0).getId());
        assertEquals("Wrong cause 1", -80, (int) loaded.getOtherCauses().get(1).getId());
        assertEquals("Wrong cause 2", -82, (int) loaded.getOtherCauses().get(2).getId());

        assertEquals("Wrong text for cause 1", "Crossed against light",
            loaded.getOtherCauses().get(1).getText());
    }

    private static void assertLabValue(
        String message, String expectedValue, int expectedYear, int expectedMonth, int expectedDay,
        LabValue actual
    ) {
        assertDayOfDate(prependMessage(message) + "wrong date",
            expectedYear, expectedMonth, expectedDay, actual.getDate());
        assertEquals(prependMessage(message) + "wrong value", expectedValue, actual.getValue());
    }

    public void testSave() throws Exception {
        doSaveTest(new SaveTester() {
            public void setupReport(AdverseEventReport report) {
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
                report.setDetectionDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));
            }

            public void assertCorrect(AdverseEventReport loaded) {
                assertEquals("Wrong assignment", -14, (int) loaded.getAssignment().getId());
                assertDayOfDate("Wrong day for loaded date", 2004, Calendar.APRIL, 25, loaded.getDetectionDate());

                assertEquals("Wrong number of AEs", 2, loaded.getAdverseEvents().size());
                AdverseEvent loadedEvent0 = loaded.getAdverseEvents().get(0);
                assertNotNull("Cascaded AE not found", loadedEvent0);
                assertEquals("Wrong grade", Grade.MILD, loadedEvent0.getGrade());
                assertEquals("Wrong CTC term", 3012, (int) loadedEvent0.getCtcTerm().getId());
                assertNotNull("No report", loadedEvent0.getReport());
                assertEquals("Wrong hospitalization", Hospitalization.PROLONGED_HOSPITALIZATION, loadedEvent0.getHospitalization());
                assertEquals("Wrong expectedness", Boolean.FALSE, loadedEvent0.getExpected());
                assertNotNull("Second cascaded AE not found", loaded.getAdverseEvents().get(1));
            }
        });
    }
    
    public void testSaveNewReportWithConMedWithAttribution() throws Exception {
        doSaveTest(new SaveTester() {
            public void setupReport(AdverseEventReport report) {
                ConcomitantMedication conMed = report.getConcomitantMedications().get(0);
                conMed.setAgent(agentDao.getById(-101));

                AdverseEvent ae0 = report.getAdverseEvents().get(0);
                report.getAdverseEvents().get(0).getConcomitantMedicationAttributions()
                    .add(new ConcomitantMedicationAttribution());
                ConcomitantMedicationAttribution conMedAttrib
                    = ae0.getConcomitantMedicationAttributions().get(0);
                conMedAttrib.setCause(conMed);
                conMedAttrib.setAttribution(Attribution.PROBABLE);
            }

            public void assertCorrect(AdverseEventReport loaded) {
                assertNotNull("Report not found", loaded);

                assertEquals(1, loaded.getConcomitantMedications().size());
                assertEquals("Wrong concomitant med", -101,
                    (int) loaded.getConcomitantMedications().get(0).getAgent().getId());

                List<ConcomitantMedicationAttribution> attribs
                    = loaded.getAdverseEvents().get(0).getConcomitantMedicationAttributions();
                assertEquals(1, attribs.size());
                assertEquals("Wrong number of con med attribs", -101,
                    (int) attribs.get(0).getCause().getAgent().getId());
            }
        });
    }

    public void testSaveNewReportWithTreatment() throws Exception {
        doSaveTest(new SaveTester() {
            public void setupReport(AdverseEventReport report) {
                TreatmentInformation ti = new TreatmentInformation();
                ti.getAdverseEventCourse().setDate(DateUtils.createDate(2006, Calendar.MAY, 4));
                ti.getAdverseEventCourse().setNumber(4);
                ti.setFirstCourseDate(DateUtils.createDate(2005, Calendar.JULY, 30));
                ti.getCourseAgents().get(0).setAdministrationDelay(new BigDecimal(480));
                ti.getCourseAgents().get(0).getDose().setAmount(new BigDecimal("45.2"));
                report.setTreatmentInformation(ti);
            }

            public void assertCorrect(AdverseEventReport loaded) {
                TreatmentInformation ti = loaded.getTreatmentInformation();
                assertNotNull("Should have treatment info", ti);
                assertDayOfDate("Wrong first course date", 2005, Calendar.JULY, 30,
                    ti.getFirstCourseDate());
                assertEquals("Wrong AE course number", 4,
                    (int) ti.getAdverseEventCourse().getNumber());
                assertDayOfDate("Wrong AE course date", 2006, Calendar.MAY, 4,
                    ti.getAdverseEventCourse().getDate());

                assertEquals("Wrong number of course agents", 1, ti.getCourseAgents().size());
                CourseAgent ca = ti.getCourseAgents().get(0);
                assertEquals(new BigDecimal(480), ca.getAdministrationDelay());
                assertEquals(new BigDecimal("45.2"), ca.getDose().getAmount());
            }
        });
    }

    public void testSaveOtherCauses() throws Exception {
        doSaveTest(new SaveTester() {
            public void setupReport(AdverseEventReport report) {
                report.getOtherCauses().get(0).setText("Insomnia");
                report.getOtherCauses().get(1).setText("Bus");
            }

            public void assertCorrect(AdverseEventReport loaded) {
                assertEquals("Wrong number of other causes", 2, loaded.getOtherCauses().size());
                assertEquals("Wrong cause 0", "Insomnia", loaded.getOtherCauses().get(0).getText());
                assertEquals("Wrong cause 1", "Bus", loaded.getOtherCauses().get(1).getText());
            }
        });
    }

    private void doSaveTest(SaveTester tester) {
        Integer savedId;
        {
            AdverseEventReport report = createMinimalAeReport();

            tester.setupReport(report);

            getDao().save(report);
            assertNotNull("No ID for new report", report.getId());
            savedId = report.getId();
        }

        interruptSession();

        {
            AdverseEventReport loaded = getDao().getById(savedId);
            assertNotNull("Saved report not found", loaded);
            tester.assertCorrect(loaded);
        }
    }

    private AdverseEventReport createMinimalAeReport() {
        AdverseEventReport report = new AdverseEventReport();
        report.setAssignment(assignmentDao.getById(-14));
        report.setDetectionDate(new Date());
        report.getAdverseEvents().get(0).setCtcTerm(ctcTermDao.getById(3012));
        report.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return report;
    }

    private static interface SaveTester {
        void setupReport(AdverseEventReport report);
        void assertCorrect(AdverseEventReport loaded);
    }
}
