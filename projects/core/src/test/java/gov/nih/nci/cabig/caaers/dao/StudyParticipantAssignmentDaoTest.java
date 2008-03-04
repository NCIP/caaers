package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.ASSIGN_PARTICIPANT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { ASSIGN_PARTICIPANT })
public class StudyParticipantAssignmentDaoTest extends DaoTestCase<StudyParticipantAssignmentDao> {
    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean(
                    "participantDao");

    private StudyDao studyDao = (StudyDao) getApplicationContext().getBean("studyDao");

    public void testGetById() throws Exception {
        StudyParticipantAssignment assignment = getDao().getById(-13);
        assertEquals("Wrong studySite", -10, (int) assignment.getStudySite().getId());
        assertEquals("Wrong participant", -5, (int) assignment.getParticipant().getId());
        assertEquals("Wrong number of AE reports", 2, assignment.getAeReports().size());
    }

    public void testGetFromParticipantAndStudy() throws Exception {
        Participant p = participantDao.getById(-5);
        Study s = studyDao.getById(-4);

        StudyParticipantAssignment actual = getDao().getAssignment(p, s);
        assertEquals("Wrong assignment found", -13, (int) actual.getId());
    }

    public void testIsAssignmentExistTest() throws Exception {
        Study s = studyDao.getById(-4);
        Participant p = new Participant();
        p.setId(-9);
        boolean exist = getDao().isAssignmentExist(p, s.getStudySites().get(0));
        assertTrue("Assignment should exist", exist);

        p = participantDao.getById(-5);
        exist = getDao().isAssignmentExist(p, s.getStudySites().get(0));
        assertTrue("Assignment should exist", exist);

        p = participantDao.getById(-99);
        exist = getDao().isAssignmentExist(p, s.getStudySites().get(0));
        assertFalse("Assignment should not exist", exist);

    }
}
