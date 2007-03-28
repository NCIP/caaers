package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;

import java.util.Calendar;
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
            event0.setDetectionDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));
            event0.setGrade(Grade.MILD);
            event0.setCtcTerm(term);
            event0.setExpected(Boolean.FALSE);
            event0.setHospitalization(Hospitalization.PROLONGED_HOSPITALIZATION);

            AdverseEvent event1 = new AdverseEvent();
            event1.setDetectionDate(new Timestamp(DateUtils.createDate(2003, Calendar.APRIL, 25).getTime() + 600000));
            event1.setGrade(Grade.SEVERE);
            event1.setCtcTerm(term);
            event1.setExpected(Boolean.FALSE);
            event1.setHospitalization(Hospitalization.HOSPITALIZATION);

            AdverseEventReport newReport = new AdverseEventReport();
            newReport.addAdverseEvent(event0);
            newReport.addAdverseEvent(event1);
            newReport.setAssignment(assignmentDao.getById(-14));

            getDao().save(newReport);
            assertNotNull("No ID for newly saved report", newReport.getId());
            savedId = newReport.getId();
        }

        interruptSession();

        {
            AdverseEventReport loaded = getDao().getById(savedId);
            assertNotNull("Saved report not found", loaded);
            assertEquals("Wrong assignment", -14, (int) loaded.getAssignment().getId());
            assertEquals("Wrong number of AEs", 2, loaded.getAdverseEvents().size());
            AdverseEvent loadedEvent0 = loaded.getAdverseEvents().get(0);
            assertNotNull("Cascaded AE not found", loadedEvent0);
            assertDayOfDate("Wrong day for loaded time", 2004, Calendar.APRIL, 25, loadedEvent0.getDetectionDate());
            assertTimeOfDate("Wrong time for loaded time", 12, 10, 0, 0, loadedEvent0.getDetectionDate());
            assertEquals("Wrong grade", Grade.MILD, loadedEvent0.getGrade());
            assertEquals("Wrong CTC term", 3012, (int) loadedEvent0.getCtcTerm().getId());
            assertNotNull("No report", loadedEvent0.getReport());
            assertEquals("Wrong hospitalization", Hospitalization.PROLONGED_HOSPITALIZATION, loadedEvent0.getHospitalization());
            assertEquals("Wrong expectedness", Boolean.FALSE, loadedEvent0.getExpected());
            assertNotNull("Second cascaded AE not found", loaded.getAdverseEvents().get(1));
        }
    }
}
