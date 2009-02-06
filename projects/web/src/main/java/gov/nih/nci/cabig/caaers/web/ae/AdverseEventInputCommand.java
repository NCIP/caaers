package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * @author Krikor Krumlian
 */
public interface AdverseEventInputCommand {

    StudyParticipantAssignment getAssignment();

    Participant getParticipant();

    Study getStudy();

    void save();

    public String getTreatmentDescriptionType();

    public void setTreatmentDescriptionType(String type);

    public boolean getIgnoreCompletedStudy();
    
    public boolean getWorkflowEnabled();
}