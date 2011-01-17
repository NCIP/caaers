package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyParticipantAssignmentQuery;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author Ion C. Olaru
 *
 * */
public class StudyParticipantAssignmentRepository {

    private static Log log = LogFactory.getLog(StudyParticipantAssignmentRepository.class);
    StudyParticipantAssignmentDao spaDao;

    public List<StudyParticipantAssignment> getAllAssignments() {
        StudyParticipantAssignmentQuery q = new StudyParticipantAssignmentQuery();
        q.joinParticipant();
        q.joinStudySite();
        List<StudyParticipantAssignment> assignments = (List<StudyParticipantAssignment>)spaDao.search(q);
        log.debug(String.format(">>> Found %d Study Participant Assignments", assignments.size()));
        return assignments;
    }

    public StudyParticipantAssignmentDao getStudyParticipantAssignmentDao() {
        return spaDao;
    }

    public void setStudyParticipantAssignmentDao(StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
        this.spaDao = studyParticipantAssignmentDao;
    }
}
