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
public class AbstractAEIntervention extends AbstractExpeditedReportCollectionElementChild {

    /** The description. */
    private String description;

    /** The study behavioral. */
    private OtherIntervention studyBehavioral;
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

    /**
     * Gets the study behavioral
     * @return the study radiation
     */
    @ManyToOne
    @JoinColumn(name = "study_intervention_id")
    public OtherIntervention getStudyBehavioral() {
        return studyBehavioral;
    }

    /**
     * Sets the study behavioral
     * @param studyBehavioral the new study behavioral
     */
    public void setStudyBehavioral(OtherIntervention studyBehavioral) {
        this.studyBehavioral = studyBehavioral;
    }

}
