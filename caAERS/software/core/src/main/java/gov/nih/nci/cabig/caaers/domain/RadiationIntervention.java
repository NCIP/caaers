package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the RadiationIntervention domain object associated with the Adverse event
 * report.
 *
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "ae_radiation_interventions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_radiation_interventi_id")})
public class RadiationIntervention extends AbstractExpeditedReportCollectionElementChild {

    private String treatmentArm;

    private String description;

    private RadiationAdministration administration;

    private String dosage;

    private String dosageUnit; // TODO : figure out what the units are ?

    private Date lastTreatmentDate;

    // schedule
    private String fractionNumber;

    private String daysElapsed;

    private String adjustment; // TODO : figure out if this is a drop down or not ?

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

    public String getAdjustment() {
        return adjustment;
    }

    public void setAdjustment(String adjustment) {
        this.adjustment = adjustment;
    }

    public String getDaysElapsed() {
        return daysElapsed;
    }

    public void setDaysElapsed(String daysElapsed) {
        this.daysElapsed = daysElapsed;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getFractionNumber() {
        return fractionNumber;
    }

    public void setFractionNumber(String fractionNumber) {
        this.fractionNumber = fractionNumber;
    }

    public Date getLastTreatmentDate() {
        return lastTreatmentDate;
    }

    public void setLastTreatmentDate(Date lastTreatmentDate) {
        this.lastTreatmentDate = lastTreatmentDate;
    }

    @Column(name = "radiation_administration_code")
    @Type(type = "radiationAdministration")
    public RadiationAdministration getAdministration() {
        return administration;
    }

    public void setAdministration(RadiationAdministration administration) {
        this.administration = administration;
    }


    public RadiationIntervention copy() {
        RadiationIntervention radiationIntervention = new RadiationIntervention();
        BeanUtils.copyProperties(this, radiationIntervention, new String[]{"id", "gridId",
                "version", "report"});

        return radiationIntervention;

    }


}
