package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the StudyParticipantConcomitantMedication domain object associated with the StudyParticipantAssignment
 * report.
 *
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_concomitant_medications")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_spa_concomitant_medicat_id")})
public class StudyParticipantConcomitantMedication extends AbstractMutableDomainObject {
    
    /** The agent name. */
    private String agentName;

    /** The assignment. */
    private StudyParticipantAssignment assignment;

    /** The start date. */
    private DateValue startDate;

    /** The end date. */
    private DateValue endDate;

    /** The still taking medications. */
    private Boolean stillTakingMedications;

    // //// LOGIC

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
        return agentName;
    }

    // //// BOUND PROPERTIES

    /**
     * Gets the agent name.
     *
     * @return the agent name
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * Sets the agent name.
     *
     * @param agentName the new agent name
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    /**
     * Gets the assignment.
     *
     * @return the assignment
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.LOCK})
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    /**
     * Sets the assignment.
     *
     * @param assignment the new assignment
     */
    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "start_date_day")),
            @AttributeOverride(name = "month", column = @Column(name = "start_date_month")),
            @AttributeOverride(name = "year", column = @Column(name = "start_date_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "start_date_zone"))
    })
    public DateValue getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(DateValue startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "day", column = @Column(name = "end_date_day")),
            @AttributeOverride(name = "month", column = @Column(name = "end_date_month")),
            @AttributeOverride(name = "year", column = @Column(name = "end_date_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "end_date_zone"))
    })
    public DateValue getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the new end date
     */
    public void setEndDate(DateValue endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the still taking medications.
     *
     * @return the still taking medications
     */
    public Boolean getStillTakingMedications() {
        return stillTakingMedications;
    }

    /**
     * Sets the still taking medications.
     *
     * @param stillTakingMedications the new still taking medications
     */
    public void setStillTakingMedications(Boolean stillTakingMedications) {
        this.stillTakingMedications = stillTakingMedications;
    }


    /**
     * Creates the assignment concomitant medication.
     *
     * @param saeReportConcomitantMedication the sae report concomitant medication
     * @return the study participant concomitant medication
     */
    public static StudyParticipantConcomitantMedication createAssignmentConcomitantMedication(ConcomitantMedication saeReportConcomitantMedication) {

        if (saeReportConcomitantMedication != null) {
            StudyParticipantConcomitantMedication studyParticipantConcomitantMedication = new StudyParticipantConcomitantMedication();
            BeanUtils.copyProperties(saeReportConcomitantMedication, studyParticipantConcomitantMedication, new String[]{"id", "gridId",
                    "version", "assignment"});


            return studyParticipantConcomitantMedication;

        }
        return null;

    }
}
