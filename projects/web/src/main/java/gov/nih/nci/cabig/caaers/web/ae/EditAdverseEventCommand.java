package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * @author Rhett Sutphin
 */
public class EditAdverseEventCommand implements AdverseEventInputCommand {
    private AdverseEventReport aeReport;

    ////// LOGIC

    public StudyParticipantAssignment getAssignment() {
        return aeReport.getAssignment();
    }

    public Participant getParticipant() {
        return getAssignment().getParticipant();
    }

    public Study getStudy() {
        return getAssignment().getStudySite().getStudy();
    }

    ////// BEAN PROPERTIES

    public AdverseEventReport getAeReport() {
        return aeReport;
    }

    public void setAeReport(AdverseEventReport aeReport) {
        this.aeReport = aeReport;
    }
}
