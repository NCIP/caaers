package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the LabLoad domain object associated with the Adverse event report.
 * 
 * @author Srini Akkala
 */
@Entity
@Table(name = "labs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_labs_id") })
public class LabLoad extends AbstractMutableDomainObject {
 
	/** The name. */
	private String name;

    /** The units. */
    private String units; 

    /** The result. */
    private String result;

    /** The dismissed. */
    private boolean  dismissed;

    /** The lab date. */
    private Date labDate;
    
    /** The assignment. */
    private StudyParticipantAssignment assignment;
    
    private Date lastSynchedDate;

    // //// BEAN PROPERTIES

    public Date getLastSynchedDate() {
		return lastSynchedDate;
	}

	public void setLastSynchedDate(Date lastSynchedDate) {
		this.lastSynchedDate = lastSynchedDate;
	}

	/**
     * Gets the units.
     *
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * Sets the units.
     *
     * @param units the new units
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * Gets the lab date.
     *
     * @return the lab date
     */
    public Date getLabDate() {
        return labDate;
    }

    /**
     * Sets the lab date.
     *
     * @param labDate the new lab date
     */
    public void setLabDate(Date labDate) {
        this.labDate = labDate;
    }

	/**
	 * Checks if is dismissed.
	 *
	 * @return true, if is dismissed
	 */
	public boolean isDismissed() {
		return dismissed;
	}

	/**
	 * Sets the dismissed.
	 *
	 * @param dismissed the new dismissed
	 */
	public void setDismissed(boolean dismissed) {
		this.dismissed = dismissed;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}
    
    /**
     * Gets the participant.
     *
     * @return the participant
     */
    @Transient
    public Participant getParticipant() {
        return getAssignment() == null ? null : getAssignment().getParticipant();
    }

    /**
     * Gets the study.
     *
     * @return the study
     */
    @Transient
    public Study getStudy() {
        StudySite ss = getAssignment() == null ? null : getAssignment().getStudySite();
        return ss == null ? null : ss.getStudy();
    }
    
    /**
     * Gets the assignment.
     *
     * @return the assignment
     */
    @ManyToOne(fetch = FetchType.LAZY)
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
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
    public int hashCode() {
        return ("" + getName() ).hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null) return false;

        if (!(obj instanceof Lab)) return false;

        LabLoad l = (LabLoad) obj;
        if (!StringUtils.equals(name, l.name)) return false;

        if (l.units != null && !units.equals(l.units)) return false;
        if (l.result != null && !result.equals(l.result)) return false;

        return true;
    }
}
