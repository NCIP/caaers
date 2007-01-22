package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Lab;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventCommand {
    private AdverseEventReport aeReport;

    private Participant participant;
    private Study study;

    private StudyParticipantAssignmentDao assignmentDao;

    public CreateAdverseEventCommand(StudyParticipantAssignmentDao assignmentDao) {
        this.assignmentDao = assignmentDao;
        aeReport = new AdverseEventReport();
        aeReport.setPrimaryAdverseEvent(new AdverseEvent());

        // TODO temporary
        aeReport.getLabs().add(new Lab());
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

    public AdverseEventReport getAeReport() {
        return aeReport;
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
