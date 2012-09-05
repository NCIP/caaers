package gov.nih.nci.cabig.caaers.domain.dto;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * This class represents the External Adverse Event Reporting Period associated to StudyParticipant Associations.
 *
 * @author Ramakrishna Gundala
 */
@Entity
@Table(name = "ext_ae_reporting_prds")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ext_ae_reporting_prds_id") })
public class ExternalAdverseEventReportingPeriodDTO extends AbstractMutableDomainObject{
	
	/** The Constant BASELINE_REPORTING_TYPE. */
	private static final String BASELINE_REPORTING_TYPE = "Baseline";
	
	/** The description. */
	private String description;
	
	/** The cycle number. */
	private Integer cycleNumber;
	
	/** The workflow id. */
	private Integer workflowId;
	
	/** The review status. */
	private ReviewStatus reviewStatus;
	
	/** The treatment assignment code. */
	private String treatmentAssignmentCode;
	
	/** The treatment assignment description. */
	private String treatmentAssignmentDescription;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The assignment. */
	private StudyParticipantAssignment assignment;
	
	/** The name. */
	private String name;
	
	/** The formatter. */
	private SimpleDateFormat formatter;
	
	// This gives the Report Status for the reporting Period
	/** The report status. */
	private String reportStatus;
	
	@Column(name = "treatment_assignment_code")
	public String getTreatmentAssignmentCode() {
		return treatmentAssignmentCode;
	}

	public void setTreatmentAssignmentCode(String treatmentAssignmentCode) {
		this.treatmentAssignmentCode = treatmentAssignmentCode;
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
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

	/**
	 * Instantiates a new adverse event reporting period.
	 */
	public ExternalAdverseEventReportingPeriodDTO() {
		formatter = new SimpleDateFormat("MM/dd/yy");
    }
	
	
	// BEAN PROPERTIES
    
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
    @Cascade(value={CascadeType.MERGE, CascadeType.LOCK})
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
    @Column(name = "review_status_code")
    @Type(type = "reviewStatus")
    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#setReviewStatus(gov.nih.nci.cabig.caaers.domain.ReviewStatus)
     */
    public void setReviewStatus(ReviewStatus reviewStatus){
    	this.reviewStatus = reviewStatus;
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
     * Gets the treatment assignment description.
     *
     * @return the treatment assignment description
     */
    @Column(name = "treatment_assignment_desc")
    public String getTreatmentAssignmentDescription() {
        return treatmentAssignmentDescription;
    }

    /**
     * Sets the treatment assignment description.
     *
     * @param treatmentAssignmentDescription the new treatment assignment description
     */
    public void setTreatmentAssignmentDescription(String treatmentAssignmentDescription) {
        this.treatmentAssignmentDescription = treatmentAssignmentDescription;
    }
}