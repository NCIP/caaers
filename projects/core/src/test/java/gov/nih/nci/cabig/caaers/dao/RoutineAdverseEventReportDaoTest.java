package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersTestCase.*;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Fixtures;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import edu.nwu.bioinformatics.commons.testing.CoreTestCase;
import edu.nwu.bioinformatics.commons.DateUtils;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({CREATE_ROUTINE_REPORT })
public class RoutineAdverseEventReportDaoTest extends DaoTestCase<RoutineAdverseEventReportDao> {
    private CtcTermDao ctcTermDao = (CtcTermDao) getApplicationContext().getBean("ctcTermDao");
    private StudyParticipantAssignmentDao assignmentDao
        = (StudyParticipantAssignmentDao) getApplicationContext().getBean("studyParticipantAssignmentDao");

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

    public void testSearchRoutineReportByCtcTermPartial() throws Exception {
    	List<RoutineAdverseEventReport> results;
    	Map<String,String> m = new HashMap<String,String>();
    	m.put("ctcTerm", "Auditory/Ear");
    	results = getDao().searchRoutineReports(m);
    	assertEquals("Wrong number of results", 1, results.size());
    }
    public void testSearchRoutineReportByStartOrEndDate() throws Exception {
    	List<RoutineAdverseEventReport> results;
    	Map<String,String> m = new HashMap<String,String>();
    	m.put("date", "05/12/2004");
    	results = getDao().searchRoutineReports(m);
    	assertEquals("Wrong number of results", 1, results.size());
    }
    
    public void testSearchRoutineReportByParticipantFirstName() throws Exception {
    	List<RoutineAdverseEventReport> results;
    	Map<String,String> m = new HashMap<String,String>();
    	m.put("participantFirstName", "Michael");
    	results = getDao().searchRoutineReports(m);
    	assertEquals("Wrong number of results", 1, results.size());
    }
    
    public void testSearchRoutineReportByStudyShortTitle() throws Exception {
    	List<RoutineAdverseEventReport> results;
    	Map<String,String> m = new HashMap<String,String>();
    	m.put("studyShortTitle", "That");
    	results = getDao().searchRoutineReports(m);
    	assertEquals("Wrong number of results", 1, results.size());
    }

    public void testSave() throws Exception {
        doSaveTest(new SaveTester() {
            public void setupReport(RoutineAdverseEventReport report) {
                CtcTerm term = ctcTermDao.getById(3012);
                AdverseEvent event0 = new AdverseEvent();
                event0.setGrade(Grade.MILD);
                event0.setAdverseEventCtcTerm(Fixtures.createAdverseEventCtcTerm(event0, term));
                event0.setExpected(Boolean.FALSE);
                event0.setHospitalization(Hospitalization.PROLONGED_HOSPITALIZATION);

                AdverseEvent event1 = new AdverseEvent();
                event1.setGrade(Grade.SEVERE);
                event1.setAdverseEventCtcTerm(Fixtures.createAdverseEventCtcTerm(event0, term));
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
                assertEquals("Wrong CTC term", 3012, (int) loadedEvent0.getAdverseEventCtcTerm().getCtcTerm().getId());
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
        report.getAdverseEvents().get(0).setAdverseEventCtcTerm(Fixtures.createAdverseEventCtcTerm(report.getAdverseEvents().get(0), ctcTermDao.getById(3012)));
        return report;
    }


    private static interface SaveTester {
        void setupReport(RoutineAdverseEventReport report);
        void assertCorrect(RoutineAdverseEventReport loaded);
    }
    
}
