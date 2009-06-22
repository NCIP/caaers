package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

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
        BeanUtils.copyProperties(source, saeReportConcomitantMedication, new String[]{"id", "gridId","version", "report"});

        return saeReportConcomitantMedication;
    }

    public ConcomitantMedication copy() {
        return copy(this);

    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agentName == null) ? 0 : agentName.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime
				* result
				+ ((stillTakingMedications == null) ? 0
						: stillTakingMedications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcomitantMedication other = (ConcomitantMedication) obj;
		if (agentName == null) {
			if (other.agentName != null)
				return false;
		} else if (!agentName.equals(other.agentName))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (stillTakingMedications == null) {
			if (other.stillTakingMedications != null)
				return false;
		} else if (!stillTakingMedications.equals(other.stillTakingMedications))
			return false;
		return true;
	}
    
    ///OBJECT METHODS
    
}
