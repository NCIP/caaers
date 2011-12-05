package gov.nih.nci.cabig.caaers.domain.report;

import java.util.Collections;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

 
/**
 * Every report has a report version.
 *
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "report_versions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_versions_id") })
public class ReportVersion extends AbstractMutableDomainObject implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 446860490773763452L;

	/** The created on. */
	private Date createdOn;

    /** The due on. */
    private Date dueOn;

    /** The submitted on. */
    private Date submittedOn;

    /** The withdrawn on. */
    private Date withdrawnOn;
    
    /** The amended on. */
    private Date amendedOn;

    /** The submitter. */
    private Submitter submitter;

    /** The physician signoff. */
    private Boolean physicianSignoff;

    /** The cc emails. */
    private String ccEmails;
    
    /** The amendment number. */
    private Integer amendmentNumber; //the amendment #, which is shown on UI
    
    /** The report version id. */
    private String reportVersionId; //amendment # (that is getting incremented), used by AdEERS.

    /** The report status. */
    private ReportStatus reportStatus;

    /** The assigned identifer. */
    private String assignedIdentifer;

    // private String submissionStatus;
    /** The submission url. */
    private String submissionUrl;

    /** The submission message. */
    private String submissionMessage;

    /** The report. */
    private Report report;
    
    /** The contents. */
    List<ReportContent> contents;
    
    //adverse events, that got added/reported in this report.
    /** The reported advers events. */
    List<ReportedAdverseEvent> reportedAdversEvents;
    
    /** The report trackings. */
    private List<ReportTracking> reportTrackings;
    

    // ////Logic
    /**
     * This method will increment the report version by 1. 
     */
    public void incrementReportVersion(){
    	if(StringUtils.isEmpty(reportVersionId)){
    		reportVersionId = "0";
    	}else{
    		reportVersionId = (Integer.parseInt(reportVersionId) + 1) + "";
    	}
    }
    
    /**
     * Increment amendment number.
     */
    public void incrementAmendmentNumber(){
    	if(amendmentNumber == null){
    		amendmentNumber = 0;
    	}else{
    		amendmentNumber = (amendmentNumber + 1);
    	}
    	
    }
    
    /**
     * Adds the reported adverse event.
     *
     * @param ae the ae
     */
    public void addReportedAdverseEvent(AdverseEvent ae){
    	if(reportedAdversEvents == null) reportedAdversEvents = new ArrayList<ReportedAdverseEvent>();
    	ReportedAdverseEvent reportedAE = new ReportedAdverseEvent(this, ae);
    	reportedAdversEvents.add(reportedAE);
    }
    
    /**
     * Adds the report content.
     *
     * @param content the content
     */
    public void addReportContent(ReportContent content){
    	if(contents == null) contents = new ArrayList<ReportContent>();
    	contents.add(content);
    }
    
    /**
     * This method will clear off the previous submission debris if-any.
     */
    public void clear(){
    	if(contents != null) contents.clear();
    	if(reportedAdversEvents != null) reportedAdversEvents.clear();
    	setSubmissionMessage("");
    	setSubmissionUrl("");
    }
    
    /**
     * Will initialize the reportTrackings list if need be, and adds the reportTracking after updating the attemptnumber.
     *
     * @param reportTracking the report tracking
     */
    public void addReportTracking(ReportTracking reportTracking){
		if (reportTrackings == null) reportTrackings = new ArrayList<ReportTracking>();
		if(reportTracking.getAttemptNumber() == null) reportTracking.setAttemptNumber(reportTrackings.size() + 1);
		reportTracking.setReportVersion(this);
        reportTrackings.add(reportTracking);
	}
    
    /**
     * Gets the status as string.
     *
     * @return the status as string
     */
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
    /**
     * Gets the report status.
     *
     * @return the report status
     */
    @Column(name = "status_code")
    @Type(type = "reportStatus")
    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    /**
     * Sets the report status.
     *
     * @param reportStatus the new report status
     */
    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    /**
     * Gets the report.
     *
     * @return the report
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", insertable = false, updatable = false, nullable = false)
    public Report getReport() {
        return report;
    }

    /**
     * Sets the report.
     *
     * @param report the new report
     */
    public void setReport(Report report) {
        this.report = report;
    }

    /**
     * Gets the created on.
     *
     * @return the created on
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the created on.
     *
     * @param createdOn the new created on
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the submitted on.
     *
     * @return the submitted on
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getSubmittedOn() {
        return submittedOn;
    }

    /**
     * Sets the submitted on.
     *
     * @param submittedOn the new submitted on
     */
    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }
    
    /**
     * Gets the withdrawn on.
     *
     * @return the withdrawn on
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getWithdrawnOn() {
        return withdrawnOn;
    }

    /**
     * Sets the withdrawn on.
     *
     * @param withdrawnOn the new withdrawn on
     */
    public void setWithdrawnOn(Date withdrawnOn) {
        this.withdrawnOn = withdrawnOn;
    }
    
    /**
     * Gets the due on.
     *
     * @return the due on
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDueOn() {
        return dueOn;
    }
    
    /**
     * Sets the due on.
     *
     * @param dueOn the new due on
     */
    public void setDueOn(Date dueOn) {
        this.dueOn = dueOn;
    }
    
    /**
     * Gets the amended on.
     *
     * @return the amended on
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getAmendedOn() {
		return amendedOn;
	}
    
    /**
     * Sets the amended on.
     *
     * @param amendedOn the new amended on
     */
    public void setAmendedOn(Date amendedOn) {
		this.amendedOn = amendedOn;
	}

    /**
     * Adds the submitter.
     */
    @Transient
    public void addSubmitter() {
        if (submitter == null) setSubmitter(new Submitter());
    }

    // non-total cascade allows us to skip saving if the reporter hasn't been filled in yet
    /**
     * Gets the submitter.
     *
     * @return the submitter
     */
    @OneToOne(mappedBy = "reportVersion", orphanRemoval = true)
    @Cascade(value = { CascadeType.ALL  })
    public Submitter getSubmitter() {
        return submitter;
    }

    /**
     * Sets the submitter.
     *
     * @param submitter the new submitter
     */
    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
        if (submitter != null) submitter.setReportVersion(this);
    }

    /**
     * Gets the physician signoff.
     *
     * @return the physician signoff
     */
    @Column(name = "physician_signoff")
    public Boolean getPhysicianSignoff() {
        return physicianSignoff;
    }

    /**
     * Sets the physician signoff.
     *
     * @param physicianSignoff the new physician signoff
     */
    public void setPhysicianSignoff(Boolean physicianSignoff) {
        this.physicianSignoff = physicianSignoff;
    }

    /**
     * Gets the cc emails.
     *
     * @return the cc emails
     */
    @Column(name = "email")
    public String getCcEmails() {
        return ccEmails;
    }

    /**
     * Sets the cc emails.
     *
     * @param email the new cc emails
     */
    public void setCcEmails(String email) {
        this.ccEmails = email;
    }

    /**
     * Gets the email as array.
     *
     * @return the email as array
     */
    @Transient
    public String[] getEmailAsArray() {
        if (this.ccEmails == null) {
            return null;
        }
        String[] emails = this.ccEmails.split(",");
        return emails;
    }

    /**
     * Gets the report version id.
     *
     * @return the report version id
     */
    public String getReportVersionId() {
        return reportVersionId;
    }

    /**
     * Sets the report version id.
     *
     * @param reportVersionId the new report version id
     */
    public void setReportVersionId(String reportVersionId) {
        this.reportVersionId = reportVersionId;
    }

    /**
     * Gets the assigned identifer.
     *
     * @return the assigned identifer
     */
    public String getAssignedIdentifer() {
        return assignedIdentifer;
    }

    /**
     * Sets the assigned identifer.
     *
     * @param assignedIdentifer the new assigned identifer
     */
    public void setAssignedIdentifer(String assignedIdentifer) {
        this.assignedIdentifer = assignedIdentifer;
    }

    /**
     * Gets the submission message.
     *
     * @return the submission message
     */
    public String getSubmissionMessage() {
        return submissionMessage;
    }

    /**
     * Sets the submission message.
     *
     * @param submissionMessage the new submission message
     */
    public void setSubmissionMessage(String submissionMessage) {
        this.submissionMessage = submissionMessage;
    }

    /**
     * Gets the submission url.
     *
     * @return the submission url
     */
    public String getSubmissionUrl() {
        return submissionUrl;
    }

    /**
     * Sets the submission url.
     *
     * @param submissionUrl the new submission url
     */
    public void setSubmissionUrl(String submissionUrl) {
        this.submissionUrl = submissionUrl;
    }
    
    /**
     * Gets the contents.
     *
     * @return the contents
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name="report_version_id" ,nullable=false)
    @Cascade(value={CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportContent> getContents() {
		return contents;
	}
    
    /**
     * Sets the contents.
     *
     * @param contents the new contents
     */
    public void setContents(List<ReportContent> contents) {
		this.contents = contents;
	}
    
    /**
     * Gets the reported advers events.
     *
     * @return the reported advers events
     */
    @OneToMany(mappedBy="reportVersion", orphanRemoval = true)
    @Cascade(value={CascadeType.SAVE_UPDATE, CascadeType.REMOVE, CascadeType.DETACH})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportedAdverseEvent> getReportedAdversEvents() {
		return reportedAdversEvents;
	}
    
    /**
     * Sets the reported advers events.
     *
     * @param reportedAdversEvents the new reported advers events
     */
    public void setReportedAdversEvents(
			List<ReportedAdverseEvent> reportedAdversEvents) {
		this.reportedAdversEvents = reportedAdversEvents;
	}
    
    /**
     * Returns the {@link ReportContent} of type text/xml.
     *
     * @return the xml content
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
    
    /**
     * Copy submission details.
     *
     * @param rv the rv
     */
    public void copySubmissionDetails(ReportVersion rv){
    	this.setAssignedIdentifer(rv.getAssignedIdentifer());
    }
    
    
    
    /**
     * Gets the report trackings internal.
     *
     * @return the report trackings internal
     */
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "report_version_id", nullable = false)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<ReportTracking> getReportTrackingsInternal() {
		return reportTrackings;
	}
    
    /**
     * Sets the report trackings internal.
     *
     * @param reportTrackings the new report trackings internal
     */
    public void setReportTrackingsInternal (List<ReportTracking> reportTrackings) {
		this.reportTrackings = reportTrackings;
	}
    
    /**
     * Gets the report trackings.
     *
     * @return the report trackings
     */
    @SuppressWarnings("unchecked")
	@Transient 
	public List<ReportTracking> getReportTrackings() {
    	ArrayList<ReportTracking> nonSortedList = new ArrayList<ReportTracking>();
    	nonSortedList.addAll(getReportTrackingsInternal());
    	Collections.sort(nonSortedList, new AttemptNumberReportTrackingComparator());
    	return nonSortedList;
	}
    
    /**
     * Sets the report trackings.
     *
     * @param reportTrackings the new report trackings
     */
    public void setReportTrackings(List<ReportTracking> reportTrackings) {
		this.reportTrackings = reportTrackings;
	}
    
    /**
     * Gets the amendment number.
     *
     * @return the amendment number
     */
    public Integer getAmendmentNumber() {
		return amendmentNumber;
	}

    /**
     * Sets the amendment number.
     *
     * @param amendmentNumber the new amendment number
     */
    public void setAmendmentNumber(Integer amendmentNumber) {
		this.amendmentNumber = amendmentNumber;
	}
    
    /**
     * Gets the last report tracking.
     *
     * @return the last report tracking
     */
    @Transient
    public ReportTracking getLastReportTracking() {
        return getReportTrackings() != null && getReportTrackings().size() > 0 ? getReportTrackings().get(getReportTrackings().size()-1) : null;
    }
    
   
    /*
     * get backward list
     */
    /**
     * Gets the report trackings for display.
     *
     * @return the report trackings for display
     */
    @Transient
	public List<ReportTracking> getReportTrackingsForDisplay() {
    	List<ReportTracking> reverseList = new ArrayList<ReportTracking>();
    	for (int i=getReportTrackings().size()-1; i>=0 ;i--) {
    		reverseList.add(getReportTrackings().get(i));
    	}
		return reverseList;
	}
	
    /**
     * This method returns true, if the report version can be submitted.
     * The following statuses cannot be submitted : REPLACED, WITDRAWN, COMPLETED, AMENDED.
     *
     * @return true, if is active
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
