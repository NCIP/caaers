package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventCommand {
    private AdverseEvent adverseEvent;

    private Participant participant;
    private Study study;

    private StudyParticipantAssignmentDao assignmentDao;

    public CreateAdverseEventCommand(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
        adverseEvent = new AdverseEvent();
    }

    ////// LOGIC

    public StudyParticipantAssignment getAssignment() {
        if (getParticipant() != null && getStudy() != null) {
            return assignmentDao.getAssignment(getParticipant(), getStudy());
        } else {
            return null;
        }
    }

    ////// BOUND PROPERTIES

    public AdverseEvent getAe() {
        return adverseEvent;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }
}
