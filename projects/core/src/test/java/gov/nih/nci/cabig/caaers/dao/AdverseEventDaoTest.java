package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.DateUtils;
import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.*;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventDaoTest extends DaoTestCase<AdverseEventDao> {
    private CtcTermDao ctcTermDao = (CtcTermDao) getApplicationContext().getBean("ctcTermDao");
    private StudyParticipantAssignmentDao assignmentDao
        = (StudyParticipantAssignmentDao) getApplicationContext().getBean("studyParticipantAssignmentDao");

    public void testGet() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("AE not found", loaded);
        assertDayOfDate("Wrong day for AE time", 2007, Calendar.APRIL, 3, loaded.getDetectionDate());
        assertTimeOfDate("Wrong time for AE time", 21, 43, 56, 987, loaded.getDetectionDate());
        assertEquals("Wrong grade", Grade.DEATH, loaded.getGrade());
        assertEquals("Wrong attribution", Attribution.UNRELATED, loaded.getAttribution());
        assertEquals("Wrong term", 3007, (int) loaded.getCtcTerm().getId());
        assertEquals("Wrong hosp.", Boolean.FALSE, loaded.getHospitalization());
    }
    
    public void testSave() throws Exception {
        Integer savedId;
        {
            CtcTerm term = ctcTermDao.getById(3012);
            StudyParticipantAssignment assignment = assignmentDao.getById(-6);
            AdverseEvent newEvent = new AdverseEvent();
            newEvent.setAssignment(assignment);
            newEvent.setDetectionDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));
            newEvent.setGrade(Grade.MILD);
            newEvent.setAttribution(Attribution.PROBABLE);
            newEvent.setCtcTerm(term);
            newEvent.setHospitalization(true);

            getDao().save(newEvent);
            assertNotNull("No ID for newly saved event", newEvent.getId());
            savedId = newEvent.getId();
        }

        interruptSession();

        {
            AdverseEvent reloaded = getDao().getById(savedId);
            assertNotNull("Saved AE not found", reloaded);
            assertDayOfDate("Wrong day for loaded time", 2004, Calendar.APRIL, 25, reloaded.getDetectionDate());
            assertTimeOfDate("Wrong time for loaded time", 12, 10, 0, 0, reloaded.getDetectionDate());
            assertEquals("Wrong grade", Grade.MILD, reloaded.getGrade());
            assertEquals("Wrong attribution", Attribution.PROBABLE, reloaded.getAttribution());
            assertEquals("Wrong CTC term", 3012, (int) reloaded.getCtcTerm().getId());
            assertEquals("Wrong assignment", -6, (int) reloaded.getAssignment().getId());
            assertEquals("Wrong hospitalization flag", Boolean.TRUE, reloaded.getHospitalization());
        }
    }
}
