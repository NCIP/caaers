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
    
    /** The agent name. */
    private String agentName;

    /** The start date. */
    private DateValue startDate;

    /** The end date. */
    private DateValue endDate;

    /** The still taking medications. */
    private Boolean stillTakingMedications;

    /**
     * Instantiates a new concomitant medication.
     */
    public ConcomitantMedication() {
        startDate = new DateValue();
        endDate = new DateValue();
        stillTakingMedications = false;
    }

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
     * Creates the concomitant medication.
     *
     * @param studyParticipantConcomitantMedication the study participant concomitant medication
     * @return the concomitant medication
     */
    public static ConcomitantMedication createConcomitantMedication(StudyParticipantConcomitantMedication studyParticipantConcomitantMedication) {
        if (studyParticipantConcomitantMedication != null) {
            ConcomitantMedication saeReportConcomitantMedication = copy(studyParticipantConcomitantMedication);
            return saeReportConcomitantMedication;
        }
        return null;

    }

    /**
     * Copy.
     *
     * @param source the source
     * @return the concomitant medication
     */
    private static ConcomitantMedication copy(Object source) {
        ConcomitantMedication saeReportConcomitantMedication = new ConcomitantMedication();
        BeanUtils.copyProperties(source, saeReportConcomitantMedication, new String[]{"id", "gridId","version", "report"});

        return saeReportConcomitantMedication;
    }

    /**
     * Copy.
     *
     * @return the concomitant medication
     */
    public ConcomitantMedication copy() {
        return copy(this);

    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
