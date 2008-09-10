package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

/**
 * This class represents the ConcomitantMedication domain object associated with the Adverse event
 * report.
 *
 * @author Rhett Sutphin
 */
@Entity
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_concomitant_medications_id")})
public class ConcomitantMedication extends AbstractExpeditedReportCollectionElementChild {
    private String agentName;

    private DateValue startDate;

    private DateValue endDate;

    private Boolean stillTakingMedications;

    public ConcomitantMedication() {
        startDate = new DateValue();
        endDate = new DateValue();
        stillTakingMedications = false;
    }

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


    public static ConcomitantMedication createConcomitantMedication(StudyParticipantConcomitantMedication studyParticipantConcomitantMedication) {

        if (studyParticipantConcomitantMedication != null) {
            ConcomitantMedication saeReportConcomitantMedication = copy(studyParticipantConcomitantMedication);
            return saeReportConcomitantMedication;

        }
        return null;

    }

    private static ConcomitantMedication copy(Object source) {
        ConcomitantMedication saeReportConcomitantMedication = new ConcomitantMedication();
        BeanUtils.copyProperties(source, saeReportConcomitantMedication, new String[]{"id", "gridId",
                "version", "report"});

        return saeReportConcomitantMedication;
    }

    public ConcomitantMedication copy() {
        return copy(this);

    }
}
