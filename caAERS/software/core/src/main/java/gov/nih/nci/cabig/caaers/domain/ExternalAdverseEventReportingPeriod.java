package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the External Adverse Event Reporting Period associated to StudyParticipant Associations.
 *
 * @author Ramakrishna Gundala
 */
@Entity
@Table(name = "ext_ae_reporting_prds")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ext_ae_reporting_prds_id") })
public class ExternalAdverseEventReportingPeriod extends AbstractMutableDomainObject{
	
	/** The Constant BASELINE_REPORTING_TYPE. */
	private static final String BASELINE_REPORTING_TYPE = "Baseline";
	
	/** The description. */
	private String description;
	
	/** The cycle number. */
	private Integer cycleNumber;
	
	/** The workflow id. */
	private Integer workflowId;
	
	/** The review status. */
	private ReviewStatus reviewStatus = ReviewStatus.DRAFT_INCOMPLETE;
	
	/** The treatment assignment code. */
	private String treatmentAssignmentCode;
	
	/** The treatment assignment description. */
	private String otherTreatmentAssignmentDescription;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The name. */
	private String name;
	
	private List<ExternalAdverseEvent> externalAdverseEvents = new ArrayList<ExternalAdverseEvent>();
	
	public Date getCreationDate() {
		return creationDate;
	}

	@OneToMany(mappedBy = "externalAdverseEventReportingPeriod", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<ExternalAdverseEvent> getExternalAdverseEvents() {
		return externalAdverseEvents;
	}

	public void setExternalAdverseEvents(
			List<ExternalAdverseEvent> externalAdverseEvents) {
		this.externalAdverseEvents = externalAdverseEvents;
	}
	
	public void addExternalAdverseEvent(ExternalAdverseEvent externalAdverseEvent){
		getExternalAdverseEvents().add(externalAdverseEvent);
		externalAdverseEvent.setExternalAdverseEventReportingPeriod(this);
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	private Date creationDate = new Date();
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name="reporting_period_id")
	public AdverseEventReportingPeriod getDomainReportingPeriod() {
		return domainReportingPeriod;
	}

	public void setDomainReportingPeriod(
			AdverseEventReportingPeriod domainReportingPeriod) {
		this.domainReportingPeriod = domainReportingPeriod;
	}

	private AdverseEventReportingPeriod domainReportingPeriod;
	
	@Column(name = "treatment_assignment_code")
	public String getTreatmentAssignmentCode() {
		return treatmentAssignmentCode;
	}

	public void setTreatmentAssignmentCode(String treatmentAssignmentCode) {
		this.treatmentAssignmentCode = treatmentAssignmentCode;
	}

	public static String getBaselineReportingType() {
		return BASELINE_REPORTING_TYPE;
	}

	public String getName() {
		return name;
	}

	private String externalId;
	
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	// BEAN PROPERTIES
    
    /**
	 * Gets the participant.
	 *
	 * @return the participant
	 */
	@Transient
    public Participant getParticipant() {
        return getDomainReportingPeriod().getAssignment() == null ? null : getDomainReportingPeriod().getAssignment().getParticipant();
    }

    /**
     * Gets the study.
     *
     * @return the study
     */
    @Transient
    public Study getStudy() {
        StudySite ss = getDomainReportingPeriod().getAssignment() == null ? null : getDomainReportingPeriod().getAssignment().getStudySite();
        return ss == null ? null : ss.getStudy();
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

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
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
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription(){
    	return description;
    }
    
    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description){
    	this.description = description;
    }

    /**
     * Gets the cycle number.
     *
     * @return the cycle number
     */
    public Integer getCycleNumber() {
		return cycleNumber;
	}
    
    /**
     * Sets the cycle number.
     *
     * @param cycleNumber the new cycle number
     */
    public void setCycleNumber(Integer cycleNumber) {
		this.cycleNumber = cycleNumber;
	}
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#getWorkflowId()
     */
    public Integer getWorkflowId() {
    	return workflowId;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#setWorkflowId(java.lang.Integer)
     */
    public void setWorkflowId(Integer workflowId){
    	this.workflowId = workflowId;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#getReviewStatus()
     */
    @Enumerated(EnumType.STRING)
    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#setReviewStatus(gov.nih.nci.cabig.caaers.domain.ReviewStatus)
     */
    public void setReviewStatus(ReviewStatus reviewStatus){
    	this.reviewStatus = reviewStatus;
    }
    
    @Column(name="other_description")
    public String getOtherTreatmentAssignmentDescription() {
		return otherTreatmentAssignmentDescription;
	}

	public void setOtherTreatmentAssignmentDescription(
			String otherTreatmentAssignmentDescription) {
		this.otherTreatmentAssignmentDescription = otherTreatmentAssignmentDescription;
	}

	/**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
		this.name = name;
	}

	@Transient
	public StudyParticipantAssignment getAssignment(){
		if(getDomainReportingPeriod() != null && getDomainReportingPeriod().getAssignment() != null){
			return getDomainReportingPeriod().getAssignment();
		}
		
		return null;
	}
}