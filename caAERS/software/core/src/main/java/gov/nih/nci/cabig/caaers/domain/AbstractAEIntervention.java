package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * This class represents the Intervention domain object associated with the Adverse event
 * report.
 *
 * @author Ion C. Olaru
 */
@MappedSuperclass
public abstract class AbstractAEIntervention extends AbstractExpeditedReportCollectionElementChild {

    /** The description. */
    private String description;

    /** The study behavioral. */
    private OtherIntervention studyIntervention;
    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "study_intervention_id")
    public OtherIntervention getStudyIntervention() {
        return studyIntervention;
    }

    public void setStudyIntervention(OtherIntervention studyIntervention) {
        this.studyIntervention = studyIntervention;
    }

    public void copy(AbstractAEIntervention to){
       to.setDescription(this.getDescription());
       to.setId(this.getId());
       OtherIntervention oi = new OtherIntervention();
       to.setStudyIntervention(oi);
       oi.setDescription(this.getStudyIntervention().getDescription());
       oi.setName(this.getStudyIntervention().getName());
    }
}
