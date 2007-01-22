package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

/**
 * @author Rhett Sutphin
 */
public class ListAdverseEventsCommand {
    private Study study;
    private Participant participant;

    private StudyParticipantAssignmentDao assignmentDao;

    public ListAdverseEventsCommand(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
    }

    ////// LOGIC

    public StudyParticipantAssignment getAssignment() {
        if (getParticipant() != null && getStudy() != null) {
            return assignmentDao.getAssignment(getParticipant(), getStudy());
        } else {
            return null;
        }
    }

    ////// BEAN PROPERTIES

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
