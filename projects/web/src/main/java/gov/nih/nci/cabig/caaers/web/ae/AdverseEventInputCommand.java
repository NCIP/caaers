package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

/**
 * @author Rhett Sutphin
 */
public interface AdverseEventInputCommand {
    StudyParticipantAssignment getAssignment();

    Participant getParticipant();

    Study getStudy();

    AdverseEventReport getAeReport();
}
