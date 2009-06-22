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
    private String agentName;

    private StudyParticipantAssignment assignment;

    private DateValue startDate;

    private DateValue endDate;

    private Boolean stillTakingMedications;

    // //// LOGIC

    @Transient
    public String getName() {
        return agentName;
    }

    // //// BOUND PROPERTIES

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.LOCK})
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

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

    public void setStartDate(DateValue startDate) {
        this.startDate = startDate;
    }

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

    public void setEndDate(DateValue endDate) {
        this.endDate = endDate;
    }

    public Boolean getStillTakingMedications() {
        return stillTakingMedications;
    }

    public void setStillTakingMedications(Boolean stillTakingMedications) {
        this.stillTakingMedications = stillTakingMedications;
    }


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
