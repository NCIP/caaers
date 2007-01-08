package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * @author Rhett Sutphin
 */
public class StudyParticipantAssignmentDaoTest extends DaoTestCase<StudyParticipantAssignmentDao> {
    private ParticipantDao participantDao
        = (ParticipantDao) getApplicationContext().getBean("participantDao");
    private StudyDao studyDao
        = (StudyDao) getApplicationContext().getBean("studyDao");

    public void testGetById() throws Exception {
        StudyParticipantAssignment assignment = getDao().getById(-13);
        assertEquals("Wrong studySite", -10, (int) assignment.getStudySite().getId());
        assertEquals("Wrong participant", -5, (int) assignment.getParticipant().getId());
    }
    
    public void testGetFromParticipantAndStudy() throws Exception {
        Participant p = participantDao.getById(-5);
        Study s = studyDao.getById(-4);

        StudyParticipantAssignment actual = getDao().getAssignment(p, s);
        assertEquals("Wrong assignment found", -13, (int) actual.getId());
    }
}
