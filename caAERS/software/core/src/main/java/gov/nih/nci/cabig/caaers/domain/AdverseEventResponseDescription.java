/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the AdverseEventResponseDescription domain object associated with the
 * Adverse event report.
 *
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_report_descriptions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_report_descriptions_id")})
public class AdverseEventResponseDescription extends AbstractExpeditedReportSingleChild {
    
    /** The event description. */
    private String eventDescription;

    /** The present status. */
    private PostAdverseEventStatus presentStatus;

    /** The recovery date. */
    private Date recoveryDate;

    /** The retreated. */
    private Boolean retreated;

    /** The date removed from protocol. */
    private Date dateRemovedFromProtocol;

    /** The blind broken. */
    private Boolean blindBroken;

    /** The study drug interrupted. */
    private Boolean studyDrugInterrupted;

    /** The reduced dose. */
    private String reducedDose;

    /** The reduced date. */
    private Date reducedDate;

    /** The days not given. */
    private Integer daysNotGiven;

    /** The event abate. */
    private EventStatus eventAbate;

    /** The event reappear. */
    private EventStatus eventReappear;

    /** The autopsy performed. */
    private Boolean autopsyPerformed;

    /** The cause of death. */
    private String causeOfDeath;
    
    /** The primary treatment. */
    private String primaryTreatment;

    /** The primary treatment approximate time. */
    private TimeValue primaryTreatmentApproximateTime;



    // //// LOGIC

    /**
     * Checks if is removed from protocol.
     *
     * @return true, if is removed from protocol
     */
    @Transient
    public boolean isRemovedFromProtocol() {
        return getDateRemovedFromProtocol() != null;
    }

    // //// BEAN PROPERTIES

    /**
     * Gets the event description.
     *
     * @return the event description
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Sets the event description.
     *
     * @param eventDescription the new event description
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Gets the present status.
     *
     * @return the present status
     */
    @Column(name = "present_status_code")
    @Type(type = "postAdverseEventStatus")
    public PostAdverseEventStatus getPresentStatus() {
        return presentStatus;
    }

    /**
     * Sets the present status.
     *
     * @param presentStatus the new present status
     */
    public void setPresentStatus(PostAdverseEventStatus presentStatus) {
        this.presentStatus = presentStatus;
    }

    /**
     * Gets the recovery date.
     *
     * @return the recovery date
     */
    public Date getRecoveryDate() {
        return recoveryDate;
    }

    /**
     * Sets the recovery date.
     *
     * @param recoveryDate the new recovery date
     */
    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    /**
     * Gets the retreated.
     *
     * @return the retreated
     */
    public Boolean getRetreated() {
        return retreated;
    }

    /**
     * Sets the retreated.
     *
     * @param retreated the new retreated
     */
    public void setRetreated(Boolean retreated) {
        this.retreated = retreated;
    }

    /**
     * Gets the date removed from protocol.
     *
     * @return the date removed from protocol
     */
    public Date getDateRemovedFromProtocol() {
        return dateRemovedFromProtocol;
    }

    /**
     * Sets the date removed from protocol.
     *
     * @param dateRemovedFromProtocol the new date removed from protocol
     */
    public void setDateRemovedFromProtocol(Date dateRemovedFromProtocol) {
        this.dateRemovedFromProtocol = dateRemovedFromProtocol;
    }

    /**
     * Gets the blind broken.
     *
     * @return the blind broken
     */
    public Boolean getBlindBroken() {
        return blindBroken;
    }

    /**
     * Sets the blind broken.
     *
     * @param blindBroken the new blind broken
     */
    public void setBlindBroken(Boolean blindBroken) {
        this.blindBroken = blindBroken;
    }

    /**
     * Gets the event abate.
     *
     * @return the event abate
     */
    @Column(name = "event_abate")
    public EventStatus getEventAbate() {
        return eventAbate;
    }

    /**
     * Sets the event abate.
     *
     * @param eventAbate the new event abate
     */
    public void setEventAbate(EventStatus eventAbate) {
        this.eventAbate = eventAbate;
    }

    /**
     * Gets the event reappear.
     *
     * @return the event reappear
     */
    @Column(name = "event_reappear")
    public EventStatus getEventReappear() {
        return eventReappear;
    }

    /**
     * Sets the event reappear.
     *
     * @param eventReappear the new event reappear
     */
    public void setEventReappear(EventStatus eventReappear) {
        this.eventReappear = eventReappear;
    }

    /**
     * Gets the reduced date.
     *
     * @return the reduced date
     */
    public Date getReducedDate() {
        return reducedDate;
    }

    /**
     * Sets the reduced date.
     *
     * @param reducedDate the new reduced date
     */
    public void setReducedDate(Date reducedDate) {
        this.reducedDate = reducedDate;
    }

    /**
     * Gets the reduced dose.
     *
     * @return the reduced dose
     */
    public String getReducedDose() {
        return reducedDose;
    }

    /**
     * Sets the reduced dose.
     *
     * @param reducedDose the new reduced dose
     */
    public void setReducedDose(String reducedDose) {
        this.reducedDose = reducedDose;
    }

    /**
     * Gets the study drug interrupted.
     *
     * @return the study drug interrupted
     */
    public Boolean getStudyDrugInterrupted() {
        return studyDrugInterrupted;
    }

    /**
     * Sets the study drug interrupted.
     *
     * @param studyDrugInterrupted the new study drug interrupted
     */
    public void setStudyDrugInterrupted(Boolean studyDrugInterrupted) {
        this.studyDrugInterrupted = studyDrugInterrupted;
    }

    /**
     * Gets the days not given.
     *
     * @return the days not given
     */
    public Integer getDaysNotGiven() {
        return daysNotGiven;
    }

    /**
     * Sets the days not given.
     *
     * @param daysNotGiven the new days not given
     */
    public void setDaysNotGiven(Integer daysNotGiven) {
        this.daysNotGiven = daysNotGiven;
    }


    /**
     * Gets the autopsy performed.
     *
     * @return the autopsy performed
     */
    public Boolean getAutopsyPerformed() {
        return autopsyPerformed;
    }

    /**
     * Sets the autopsy performed.
     *
     * @param autopsyPerformed the new autopsy performed
     */
    public void setAutopsyPerformed(Boolean autopsyPerformed) {
        this.autopsyPerformed = autopsyPerformed;
    }

    /**
     * Gets the cause of death.
     *
     * @return the cause of death
     */
    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    /**
     * Sets the cause of death.
     *
     * @param causeOfDeath the new cause of death
     */
    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    /**
     * Gets the primary treatment.
     *
     * @return the primary treatment
     */
    public String getPrimaryTreatment() {
        return primaryTreatment;
    }

    /**
     * Sets the primary treatment.
     *
     * @param primaryTreatment the new primary treatment
     */
    public void setPrimaryTreatment(String primaryTreatment) {
        this.primaryTreatment = primaryTreatment;
    }

    /**
     * Gets the primary treatment approximate time.
     *
     * @return the primary treatment approximate time
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "hour", column = @Column(name = "treatment_time_hour")),
            @AttributeOverride(name = "minute", column = @Column(name = "treatment_time_minute")),
            @AttributeOverride(name = "type", column = @Column(name = "treatment_time_zone"))
    })
    public TimeValue getPrimaryTreatmentApproximateTime() {
        if (primaryTreatmentApproximateTime == null) primaryTreatmentApproximateTime = new TimeValue();
        return primaryTreatmentApproximateTime;
    }

    /**
     * Sets the primary treatment approximate time.
     *
     * @param primaryTreatmentApproximateTime the new primary treatment approximate time
     */
    public void setPrimaryTreatmentApproximateTime(
            TimeValue primaryTreatmentApproximateTime) {
        this.primaryTreatmentApproximateTime = primaryTreatmentApproximateTime;
    }

    /**
     * Copy.
     *
     * @return the adverse event response description
     */
    public AdverseEventResponseDescription copy() {
        AdverseEventResponseDescription adverseEventResponseDescription = new AdverseEventResponseDescription();
        BeanUtils.copyProperties(this, adverseEventResponseDescription, new String[]{"id", "gridId",
                "version", "report"});

        return adverseEventResponseDescription;

    }


}
