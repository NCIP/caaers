package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.*;

/**
 * @author Ion C. Olaru
 *         Date: 1/5/12 -1:45 PM
 */
@Entity
@Table(name = "ta_other_interventions")
public class TreatmentAssignmentOtherIntervention extends TreatmentAssignmentStudyIntervention {

    private OtherIntervention otherIntervention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_other_intervention_id", nullable = false)
    public OtherIntervention getOtherIntervention() {
        return otherIntervention;
    }

    public void setOtherIntervention(OtherIntervention otherIntervention) {
        this.otherIntervention = otherIntervention;
    }

    @Override
    @Transient
    public StudyIntervention getStudyIntervention() {
        return this.getOtherIntervention();
    }
}
