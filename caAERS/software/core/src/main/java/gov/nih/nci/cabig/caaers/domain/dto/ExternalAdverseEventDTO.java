package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.OutcomeType;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.AdverseEventGroup;
import gov.nih.nci.cabig.caaers.validation.fields.validators.NotNullConstraint;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the External Adverse Event domain object associated with the Adverse event report.
 *
 * @author Ramakrishsna
 *
 */

@Entity
@Table(name = "ext_adverse_events")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ext_adverse_events_id")})
public class ExternalAdverseEventDTO extends AbstractMutableDomainObject{
   
	public Grade getGrade() {
		return grade;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getAdverseEventTerm() {
		return adverseEventTerm;
	}

	public void setAdverseEventTerm(String adverseEventTerm) {
		this.adverseEventTerm = adverseEventTerm;
	}

	public String getVerbatim() {
		return verbatim;
	}

	public void setVerbatim(String verbatim) {
		this.verbatim = verbatim;
	}

	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}

	/** The adverse event term. */
	private String adverseEventTerm;

    /** The grade. */
    private Grade grade;

    /** The verbatim. */
    private String verbatim;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;

    /** The serious. */
    private OutcomeType howSerious;
    
    /** The Attribution. */
    private String attribution;

    
    public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

    private String externalId;

    /**
     * Sets the grade.
     *
     * @param grade the new grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Gets the start date as string.
     *
     * @return the start date as string
     */
    @Transient
    public String getStartDateAsString(){
    	try {
			return DateUtils.formatDate(startDate);
		} catch (Exception e) {
			return null;
		}
    }
    
    /**
     * Sets the start date as string.
     *
     * @param startDate the new start date as string
     */
    @Transient
    public void setStartDateAsString(String startDate) {
    }
    

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].endDate")
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the new end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the display serious.
     *
     * @return the display serious
     */
    @Transient
    public String getDisplaySerious() {
        if (howSerious != null) return howSerious.getDisplayName();
        return "";
    }

    /**
     * Sets the display serious.
     *
     * @param igonre the new display serious
     */
    public void setDisplaySerious(String igonre) {

    }

	public OutcomeType getHowSerious() {
		return howSerious;
	}

	public void setHowSerious(OutcomeType howSerious) {
		this.howSerious = howSerious;
	}

}
