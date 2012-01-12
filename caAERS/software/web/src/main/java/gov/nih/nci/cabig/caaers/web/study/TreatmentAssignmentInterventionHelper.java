package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.StudyIntervention;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

/**
 * @author Ion C. Olaru
 *         Date: 1/11/12 -4:33 PM
 */
public class TreatmentAssignmentInterventionHelper {
    private TreatmentAssignment treatmentAssignment;
    private StudyIntervention studyIntervention;
    private boolean selected;

    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

    public StudyIntervention getStudyIntervention() {
        return studyIntervention;
    }

    public void setStudyIntervention(StudyIntervention studyIntervention) {
        this.studyIntervention = studyIntervention;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return ">>> TASI: " + getStudyIntervention().getInterventionName() + ", " + getTreatmentAssignment().getCode();
    }
}
