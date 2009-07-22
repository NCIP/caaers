package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Submitter;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.DurationUtils;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * Every report has a report version
 * 
 * @author Krikor Krumlian
 * 
 */
@Entity
@Table(name = "report_versions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_versions_id") })
public class ReportVersion extends AbstractMutableDomainObject implements Serializable {

	private static final long serialVersionUID = 446860490773763452L;

	private Date createdOn;

    private Date dueOn;

    private Date submittedOn;

    private Date withdrawnOn;
    
    private Date amendedOn;

    private Submitter submitter;

    private Boolean physicianSignoff;

    private String ccEmails;

    private String reportVersionId;

    private ReportStatus reportStatus;

    private String assignedIdentifer;

    // private String submissionStatus;
    private String submissionUrl;

    private String submissionMessage;

    private Report report;
    
    List<ReportContent> contents;
    
    //adverse events, that got added/reported in this report.
    List<ReportedAdverseEvent> reportedAdversEvents;
    
    private List<ReportTracking> reportTrackings;
    

    // ////Logic
    public void addReportedAdverseEvent(AdverseEvent ae){
    	if(reportedAdversEvents == null) reportedAdversEvents = new ArrayList<ReportedAdverseEvent>();
    	ReportedAdverseEvent reportedAE = new ReportedAdverseEvent(this, ae);
    	reportedAdversEvents.add(reportedAE);
    }
    
    public void addReportContent(ReportContent content){
    	if(contents == null) contents = new ArrayList<ReportContent>();
    	contents.add(content);
    }
    
    /**
     * This method will clear off the previous submission debris if-any
     */
    public void clear(){
    	if(contents != null) contents.clear();
    	if(reportedAdversEvents != null) reportedAdversEvents.clear();
    	setSubmissionMessage("");
    	setSubmissionUrl("");
    }
    
    /**
     * Will initialize the reportTrackings list if need be, and adds the reportTracking after updating the attemptnumber. 
     * @param reportTracking
     */
    public void addReportTracking(ReportTracking reportTracking){
		if (reportTrackings == null) reportTrackings = new ArrayList<ReportTracking>();
		if(reportTracking.getAttemptNumber() == null) reportTracking.setAttemptNumber(reportTrackings.size() + 1);
		reportTracking.setReportVersion(this);
        reportTrackings.add(reportTracking);
	}
    
    @Transient
    public String getStatusAsString(){
    	if(reportStatus == ReportStatus.PENDING){
    		if(dueOn != null)	return DurationUtils.formatDuration(dueOn.getTime() - new Date().getTime(), report.getReportDefinition().getTimeScaleUnitType().getFormat());
    		return "Pending";
    	}
    		
    	if(reportStatus == ReportStatus.INPROCESS) return "In-progress";
    	if(reportStatus == ReportStatus.COMPLETED) return "Submitted on " + DateUtils.formatDate(submittedOn);
    	if(reportStatus == ReportStatus.WITHDRAWN) return "Withdrawn on " + DateUtils.formatDate(withdrawnOn);
    	if(reportStatus == ReportStatus.FAILED) return "Submission Failed";
    	if(reportStatus == ReportStatus.AMENDED) return "Amended on " + DateUtils.formatDate(amendedOn);
    	return "";
    }
    
    ///Object properties
    @Column(name = "status_code")
    @Type(type = "reportStatus")
    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", insertable = false, updatable = false, nullable = false)
    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getWithdrawnOn() {
        return withdrawnOn;
    }

    public void setWithdrawnOn(Date withdrawnOn) {
        this.withdrawnOn = withdrawnOn;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDueOn() {
        return dueOn;
    }
    public void setDueOn(Date dueOn) {
        this.dueOn = dueOn;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getAmendedOn() {
		return amendedOn;
	}
    public void setAmendedOn(Date amendedOn) {
		this.amendedOn = amendedOn;
	}

    @Transient
    public void addSubmitter() {
        if (submitter == null) setSubmitter(new Submitter());
    }

    // non-total cascade allows us to skip saving if the reporter hasn't been filled in yet
    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public Submitter getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
        if (submitter != null) submitter.setReport(this);
    }

    @Column(name = "physician_signoff")
    public Boolean getPhysicianSignoff() {
        return physicianSignoff;
    }

    public void setPhysicianSignoff(Boolean physicianSignoff) {
        this.physicianSignoff = physicianSignoff;
    }

    @Column(name = "email")
    public String getCcEmails() {
        return ccEmails;
    }

    public void setCcEmails(String email) {
        this.ccEmails = email;
    }

    @Transient
    public String[] getEmailAsArray() {
        if (this.ccEmails == null) {
            return null;
        }
        String[] emails = this.ccEmails.split(",");
        return emails;
    }

    public String getReportVersionId() {
        return reportVersionId;
    }

    public void setReportVersionId(String reportVersionId) {
        this.reportVersionId = reportVersionId;
    }

    public String getAssignedIdentifer() {
        return assignedIdentifer;
    }

    public void setAssignedIdentifer(String assignedIdentifer) {
        this.assignedIdentifer = assignedIdentifer;
    }

    public String getSubmissionMessage() {
        return submissionMessage;
    }

    public void setSubmissionMessage(String submissionMessage) {
        this.submissionMessage = submissionMessage;
    }

    public String getSubmissionUrl() {
        return submissionUrl;
    }

    public void setSubmissionUrl(String submissionUrl) {
        this.submissionUrl = submissionUrl;
    }
    
    @OneToMany
    @JoinColumn(name="report_version_id" ,nullable=false)
    @Cascade(value={CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<ReportContent> getContents() {
		return contents;
	}
    public void setContents(List<ReportContent> contents) {
		this.contents = contents;
	}
    
    @OneToMany(mappedBy="reportVersion")
    @Cascade(value={CascadeType.SAVE_UPDATE, CascadeType.REMOVE, CascadeType.EVICT, CascadeType.DELETE_ORPHAN})
    public List<ReportedAdverseEvent> getReportedAdversEvents() {
		return reportedAdversEvents;
	}
    public void setReportedAdversEvents(
			List<ReportedAdverseEvent> reportedAdversEvents) {
		this.reportedAdversEvents = reportedAdversEvents;
	}
    
    /**
     * Returns the {@link ReportContent} of type text/xml
     * @return
     */
    @Transient
    public ReportContent getXmlContent(){
    	if(contents != null){
    		for(ReportContent rc : contents){
        		if(rc.getContentType().equals("text/xml")) return rc;
        	}
    	}
    	
    	return null;
    }
    
    public void copySubmissionDetails(ReportVersion rv){
    	this.setAssignedIdentifer(rv.getAssignedIdentifer());
    }
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_version_id", nullable = false)
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})        
	public List<ReportTracking> getReportTrackings() {
		return reportTrackings;
	}
    public void setReportTrackings(List<ReportTracking> reportTrackings) {
		this.reportTrackings = reportTrackings;
	}
    
    
    @Transient
    public ReportTracking getLastReportTracking() {
        return reportTrackings != null && reportTrackings.size() > 0 ? reportTrackings.get(reportTrackings.size()-1) : null;
    }
    
   
    /*
     * get backward list
     */
    @Transient
	public List<ReportTracking> getReportTrackingsForDisplay() {
    	List<ReportTracking> reverseList = new ArrayList<ReportTracking>();
    	for (int i=reportTrackings.size()-1; i>=0 ;i--) {
    		reverseList.add(reportTrackings.get(i));
    	}
		return reverseList;
	}
	
    /**
     * This method returns true, if the report version can be submitted.
     * The following statuses cannot be submitted : REPLACED, WITDRAWN, COMPLETED, AMENDED. 
     * @return
     */
    @Transient
    public boolean isActive(){
    	if(reportStatus == null) return true;
    	return !(reportStatus.equals(ReportStatus.COMPLETED) || 
    			reportStatus.equals(ReportStatus.AMENDED)    || 
    			reportStatus.equals(ReportStatus.REPLACED)   || 
    			reportStatus.equals(ReportStatus.WITHDRAWN));
    }
    
}
