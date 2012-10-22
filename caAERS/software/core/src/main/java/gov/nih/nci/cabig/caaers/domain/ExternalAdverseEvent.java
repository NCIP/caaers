package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.AdverseEventGroup;
import gov.nih.nci.cabig.caaers.validation.fields.validators.NotNullConstraint;
import gov.nih.nci.cabig.caaers.validation.fields.validators.NumberRangeConstraint;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
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
public class ExternalAdverseEvent extends AbstractMutableDomainObject{

	/** The adverse event term. */
	private String adverseEventTerm;
	
	/** The adverse event term. */
	private String adverseEventTermCode;
	
	/** The adverse event term. */
	private String adverseEventTermOtherValue;

    /** The grade. */
    private Grade grade;

    /** The verbatim. */
    private String verbatim;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;

    /** The serious. */
    private String howSerious;
    
    /** The Attribution. */
    private String attribution;
    
    private String otherSpecify;

    public String getOtherSpecify() {
		return otherSpecify;
	}

	public void setOtherSpecify(String otherSpecify) {
		this.otherSpecify = otherSpecify;
	}

	private Date creationDate = new Date();

    private ExternalAEReviewStatus status = ExternalAEReviewStatus.PENDING;


    private ExternalAdverseEventReportingPeriod externalAdverseEventReportingPeriod;

    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].adverseEventCtcTerm")
    public String getAdverseEventTermCode() {
        return adverseEventTermCode;
    }

    public void setAdverseEventTermCode(String adverseEventTermCode) {
        this.adverseEventTermCode = adverseEventTermCode;
    }

    public String getAdverseEventTermOtherValue() {
        return adverseEventTermOtherValue;
    }

    public void setAdverseEventTermOtherValue(String adverseEventTermOtherValue) {
        this.adverseEventTermOtherValue = adverseEventTermOtherValue;
    }

    
    @Enumerated(EnumType.STRING)
    public ExternalAEReviewStatus getStatus() {
		return status;
	}

	public void setStatus(ExternalAEReviewStatus status) {
		this.status = status;
	}

    public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

    @ManyToOne
    @JoinColumn(name = "ext_rep_prd_id", nullable = true)
    @Cascade(value = {CascadeType.LOCK, CascadeType.DETACH})
	public ExternalAdverseEventReportingPeriod getExternalAdverseEventReportingPeriod() {
		return externalAdverseEventReportingPeriod;
	}

	public void setExternalAdverseEventReportingPeriod(
			ExternalAdverseEventReportingPeriod externalAdverseEventReportingPeriod) {
		this.externalAdverseEventReportingPeriod = externalAdverseEventReportingPeriod;
	}
	
	
	 /**
     * This method will return the display name of this adverse event.
     *
     * @return the display name
     */
    @Transient
    public String getDisplayName(){
    	return this.getAdverseEventTerm();
    }

    @Enumerated(EnumType.ORDINAL)
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].grade")
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

	public String getHowSerious() {
		return howSerious;
	}

	public void setHowSerious(String howSerious) {
		this.howSerious = howSerious;
	}

}
