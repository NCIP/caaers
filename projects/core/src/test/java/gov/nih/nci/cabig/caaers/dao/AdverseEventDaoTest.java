package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.DateUtils;
import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.*;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventDaoTest extends DaoTestCase<AdverseEventDao> {
    private CtcDao ctcDao = (CtcDao) getApplicationContext().getBean("ctcDao");
    private StudyParticipantAssignmentDao assignmentDao
        = (StudyParticipantAssignmentDao) getApplicationContext().getBean("studyParticipantAssignmentDao");

    public void testGet() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("AE not found", loaded);
        assertDayOfDate("Wrong day for AE time", 2007, Calendar.APRIL, 3, loaded.getDetectionDate());
        assertTimeOfDate("Wrong time for AE time", 21, 43, 56, 987, loaded.getDetectionDate());
        assertEquals("Wrong grade", Grade.DEATH, loaded.getGrade());
        assertEquals("Wrong attribution", Attribution.UNRELATED, loaded.getAttribution());
        assertEquals("Wrong CTC", 3, (int) loaded.getCtc().getId());
        assertEquals("Wrong category", 1, (int) loaded.getCtcCategory().getId());
        assertEquals("Wrong term", "Rhinitis", loaded.getTerm());
        assertEquals("Wrong hosp.", Boolean.FALSE, loaded.getHospitalization());
    }
    
    public void testSave() throws Exception {
        Integer savedId;
        {
            Ctc ctc = ctcDao.getCtcaeV3();
            StudyParticipantAssignment assignment = assignmentDao.getById(-6);
            AdverseEvent newEvent = new AdverseEvent();
            newEvent.setAssignment(assignment);
            newEvent.setDetectionDate(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));
            newEvent.setGrade(Grade.MILD);
            newEvent.setAttribution(Attribution.PROBABLE);
            newEvent.setCtc(ctc);
            newEvent.setCtcCategory(ctc.getCategories().get(1));
            newEvent.setTerm("Otitis, external");
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
            assertEquals("Wrong CTC", 3, (int) reloaded.getCtc().getId());
            assertEquals("Wrong CTC category", 2, (int) reloaded.getCtcCategory().getId());
            assertEquals("Wrong term", "Otitis, external", reloaded.getTerm());
            assertEquals("Wrong assignment", -6, (int) reloaded.getAssignment().getId());
            assertEquals("Wrong hospitalization flag", Boolean.TRUE, reloaded.getHospitalization());
        }
    }
}
