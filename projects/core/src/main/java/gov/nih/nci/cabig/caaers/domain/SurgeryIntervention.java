package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    private String treatmentArm;
    private String description;
    private InterventionSite interventionSite;
    private Date interventionDate;

    // //// LOGIC

    // //// BEAN PROPERTIES

    @Transient
    public String getTreatmentArm() {
        return treatmentArm;
    }

    public void setTreatmentArm(String treatmentArm) {
        this.treatmentArm = treatmentArm;
    }

    @Transient
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInterventionDate() {
        return interventionDate;
    }

    public void setInterventionDate(Date interventionDate) {
        this.interventionDate = interventionDate;
    }

    @ManyToOne
    public InterventionSite getInterventionSite() {
        return interventionSite;
    }

    public void setInterventionSite(InterventionSite interventionSite) {
        this.interventionSite = interventionSite;
    }

    public SurgeryIntervention copy() {
        SurgeryIntervention surgeryIntervention = new SurgeryIntervention();
        BeanUtils.copyProperties(this, surgeryIntervention, new String[]{"id", "gridId", "version", "report"});
        return surgeryIntervention;
    }
}
