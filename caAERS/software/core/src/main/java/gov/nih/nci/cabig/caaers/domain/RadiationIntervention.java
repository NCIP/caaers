/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.*;

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

    /** The treatment arm. */
    private String treatmentArm;

    /** The description. */
    private String description;

    /** The administration. */
    private RadiationAdministration administration;

    /** The dosage. */
    private String dosage;

    /** The dosage unit. */
    private String dosageUnit; // TODO : figure out what the units are ?

    /** The last treatment date. */
    private Date lastTreatmentDate;

    // schedule
    /** The fraction number. */
    private String fractionNumber;

    /** The days elapsed. */
    private String daysElapsed;

    /** The adjustment. */
    private String adjustment; // TODO : figure out if this is a drop down or not ?

    /** The study radiation. */
    private OtherIntervention studyRadiation;

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
     * Gets the adjustment.
     *
     * @return the adjustment
     */
    public String getAdjustment() {
        return adjustment;
    }

    /**
     * Sets the adjustment.
     *
     * @param adjustment the new adjustment
     */
    public void setAdjustment(String adjustment) {
        this.adjustment = adjustment;
    }

    /**
     * Gets the days elapsed.
     *
     * @return the days elapsed
     */
    public String getDaysElapsed() {
        return daysElapsed;
    }

    /**
     * Sets the days elapsed.
     *
     * @param daysElapsed the new days elapsed
     */
    public void setDaysElapsed(String daysElapsed) {
        this.daysElapsed = daysElapsed;
    }

    /**
     * Gets the dosage.
     *
     * @return the dosage
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage.
     *
     * @param dosage the new dosage
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * Gets the dosage unit.
     *
     * @return the dosage unit
     */
    public String getDosageUnit() {
        return dosageUnit;
    }

    /**
     * Sets the dosage unit.
     *
     * @param dosageUnit the new dosage unit
     */
    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    /**
     * Gets the fraction number.
     *
     * @return the fraction number
     */
    public String getFractionNumber() {
        return fractionNumber;
    }

    /**
     * Sets the fraction number.
     *
     * @param fractionNumber the new fraction number
     */
    public void setFractionNumber(String fractionNumber) {
        this.fractionNumber = fractionNumber;
    }

    /**
     * Gets the last treatment date.
     *
     * @return the last treatment date
     */
    public Date getLastTreatmentDate() {
        return lastTreatmentDate;
    }

    /**
     * Sets the last treatment date.
     *
     * @param lastTreatmentDate the new last treatment date
     */
    public void setLastTreatmentDate(Date lastTreatmentDate) {
        this.lastTreatmentDate = lastTreatmentDate;
    }

    /**
     * Gets the administration.
     *
     * @return the administration
     */
    @Column(name = "radiation_administration_code")
    @Type(type = "radiationAdministration")
    public RadiationAdministration getAdministration() {
        return administration;
    }

    /**
     * Sets the administration.
     *
     * @param administration the new administration
     */
    public void setAdministration(RadiationAdministration administration) {
        this.administration = administration;
    }
    
    /**
     * Gets the study radiation.
     *
     * @return the study radiation
     */
    @ManyToOne
    @JoinColumn(name = "study_intervention_id")
    public OtherIntervention getStudyRadiation() {
        return studyRadiation;
    }

    /**
     * Sets the study radiation.
     *
     * @param studyRadiation the new study radiation
     */
    public void setStudyRadiation(OtherIntervention studyRadiation) {
        this.studyRadiation = studyRadiation;
    }



}
