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
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;

import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.*;
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

    private static void assertLabValue(
        String message, String expectedValue, int expectedYear, int expectedMonth, int expectedDay,
        LabValue actual
    ) {
        assertDayOfDate(prependMessage(message) + "wrong date",
            expectedYear, expectedMonth, expectedDay, actual.getDate());
        assertEquals(prependMessage(message) + "wrong value", expectedValue, actual.getValue());
    }

    public void testSave() throws Exception {
        Integer savedId;
        {
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

            AdverseEventReport newReport = new AdverseEventReport();
            newReport.addAdverseEvent(event0);
            newReport.addAdverseEvent(event1);
            newReport.setAssignment(assignmentDao.getById(-14));
            newReport.setDetectionDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));

            getDao().save(newReport);
            assertNotNull("No ID for newly saved report", newReport.getId());
            savedId = newReport.getId();
        }

        interruptSession();

        {
            AdverseEventReport loaded = getDao().getById(savedId);
            assertNotNull("Saved report not found", loaded);
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
    }
    
    public void testSaveNewReportWithConMedWithAttribution() throws Exception {
        Integer savedId;
        {
            AdverseEventReport report = new AdverseEventReport();
            report.setAssignment(assignmentDao.getById(-14));
            report.setDetectionDate(new Date());

            AdverseEvent ae0 = report.getAdverseEvents().get(0);
            ae0.setCtcTerm(ctcTermDao.getById(3012));

            ConcomitantMedication conMed = report.getConcomitantMedications().get(0);
            conMed.setAgent(agentDao.getById(-101));

            ae0.getConcomitantMedicationAttributions().add(new ConcomitantMedicationAttribution());
            ConcomitantMedicationAttribution conMedAttrib = ae0.getConcomitantMedicationAttributions().get(0);
            conMedAttrib.setCause(conMed);
            conMedAttrib.setAttribution(Attribution.PROBABLE);

            getDao().save(report);
            assertNotNull("No ID for new report", report.getId());
            savedId = report.getId();
        }

        interruptSession();

        {
            AdverseEventReport loaded = getDao().getById(savedId);
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
    }
}
