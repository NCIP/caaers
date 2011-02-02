package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the SurgeryIntervention domain object associated with the Adverse event
 * report.
 *
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "ae_surgery_interventions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_surgery_intervention_id")})
public class SurgeryIntervention extends AbstractExpeditedReportCollectionElementChild {

    /** The treatment arm. */
    private String treatmentArm;
    
    /** The description. */
    private String description;
    
    /** The intervention site. */
    private InterventionSite interventionSite;
    
    /** The intervention date. */
    private Date interventionDate;

    /** The study surgery. */
    private OtherIntervention studySurgery;

    // //// LOGIC

    // //// BEAN PROPERTIES

    /**
     * Gets the treatment arm.
     *
     * @return the treatment arm
     */
    @Transient
    public String getTreatmentArm() {
        return treatmentArm;
    }

    /**
     * Sets the treatment arm.
     *
     * @param treatmentArm the new treatment arm
     */
    public void setTreatmentArm(String treatmentArm) {
        this.treatmentArm = treatmentArm;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Transient
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the intervention date.
     *
     * @return the intervention date
     */
    public Date getInterventionDate() {
        return interventionDate;
    }

    /**
     * Sets the intervention date.
     *
     * @param interventionDate the new intervention date
     */
    public void setInterventionDate(Date interventionDate) {
        this.interventionDate = interventionDate;
    }

    /**
     * Gets the intervention site.
     *
     * @return the intervention site
     */
    @ManyToOne
    public InterventionSite getInterventionSite() {
        return interventionSite;
    }

    /**
     * Sets the intervention site.
     *
     * @param interventionSite the new intervention site
     */
    public void setInterventionSite(InterventionSite interventionSite) {
        this.interventionSite = interventionSite;
    }

    /**
     * Gets the study surgery.
     *
     * @return the study surgery
     */
    @ManyToOne
    @JoinColumn(name = "study_intervention_id")
    public OtherIntervention getStudySurgery() {
        return studySurgery;
    }

    /**
     * Sets the study surgery.
     *
     * @param studySurgery the new study surgery
     */
    public void setStudySurgery(OtherIntervention studySurgery) {
        this.studySurgery = studySurgery;
    }

}
