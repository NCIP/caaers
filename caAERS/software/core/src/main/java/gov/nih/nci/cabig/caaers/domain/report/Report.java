package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Physician;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Submitter;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * A report sending schedule for an adverse event. The RuleExecutionService, evaluates pre-defined
 * set of rules over the attributes of an AdverseEvent, and creates a Report.
 * 
 * @author Biju Joseph
 * 
 */
@Entity
@Table(name = "REPORT_SCHEDULES")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_schedules_id") })
public class Report extends AbstractMutableDomainObject implements WorkflowAware, Serializable {

	private static final long serialVersionUID = 4001323963140432224L;

	private boolean required;

    private ExpeditedAdverseEventReport aeReport;

    private ReportDefinition reportDefinition;

    private List<ScheduledNotification> notifications;

    private List<ReportVersion> reportVersions;

    private List<ReportDelivery> deliveries;
    
    private boolean manuallySelected;
    private boolean externalSystem;
    
    private String adeersReportTypeIndicator;
    
    private List<String> emailAddresses;
    private ReviewStatus reviewStatus;
    private Integer workflowId;
    
    private List<ReportReviewComment> reviewComments;
    
    private  String _REGULAR_REPORT = "Regular report";
    private  String _24HR_NOTIFICATION = "24-hr notification";
    private  String _24HR_NOTIFICATION_COMPLETE = "24-hr notification complete";
    private  String _24HR_AMENDMENT = "24-hr amendment";
    private  String _24HR_AMENDMENT_COMPLETE = "24-hr amendment complete";
    private  String _REGULAR_AMENDMENT = "Regular amendment";
    
    public Report(){
    	//for hibernate
    }
    
  
    // //// LOGIC

    public void addReportVersion(ReportVersion reportVersion) {
        if (reportVersions == null) reportVersions = new ArrayList<ReportVersion>();
        reportVersion.setReport(this);
        reportVersions.add(reportVersion);
    }

    public void addScheduledNotification(ScheduledNotification nf) {
        if (notifications == null) notifications = new ArrayList<ScheduledNotification>();
        notifications.add(nf);
    }

    public void addReportDelivery(ReportDelivery rd) {
        if (this.deliveries == null) deliveries = new ArrayList<ReportDelivery>();
        deliveries.add(rd);
        rd.setReport(this);
    }

    public boolean hasScheduledNotifications() {
        return (notifications != null) && (!notifications.isEmpty());
    }
    
    /**
     * This method will loop through the delivery defs,to see if there is an endpoint of type url
     * @return
     */
    public boolean hasSystemDeliveries(){
    	if(deliveries == null) return false;
    	
    	for(ReportDelivery rDelivery : deliveries){
    		if(rDelivery.isSystemType()) return true;
    	}
    	
    	return false;
    }
    
    /**
     * Returns the notification having the supplied Id.
     */
    public ScheduledNotification fetchScheduledNotification(Integer nfId) {
        if (notifications == null) return null;
        for (ScheduledNotification nf : notifications) {
            if (nf.getId().equals(nfId)) return nf;
        }
        return null;
    }

    @Transient
    public ReportVersion getLastVersion() {
    	if(reportVersions == null || reportVersions.isEmpty()){
    		ReportVersion defaultVersion = new ReportVersion();
    		addReportVersion(defaultVersion);
    	}
    	return reportVersions.get(reportVersions.size() -1);
    }

    // //// BEAN PROPERTIES
    @Transient
    public Date getCreatedOn() {
      return getLastVersion().getCreatedOn();
    }

    public void setCreatedOn(Date createdOn) {
       getLastVersion().setCreatedOn(createdOn);
    }
    

    @Transient
    public Date getDueOn() {
        return getLastVersion().getDueOn();
    }

    public void setDueOn(Date dueOn) {
        this.getLastVersion().setDueOn(dueOn);
    }

    @Transient
    public Date getSubmittedOn() {
        return getLastVersion().getSubmittedOn();
    }

    public void setSubmittedOn(Date submittedOn) {
        getLastVersion().setSubmittedOn(submittedOn);
    }
    
    @Transient
    public Date getWithdrawnOn(){
    	return getLastVersion().getWithdrawnOn();
    }
    public void setWithdrawnOn(Date withdrawnOn){
    	getLastVersion().setWithdrawnOn(withdrawnOn);
    }
    
    @Transient
    public ReportStatus getStatus() {
        return getLastVersion().getReportStatus();
    }

    public void setStatus(ReportStatus status) {
        this.getLastVersion().setReportStatus(status);
    }
    
    @Transient
    public String getAssignedIdentifer() {
        return getLastVersion().getAssignedIdentifer();
    }

    public void setAssignedIdentifer(String assignedIdentifer) {
        this.getLastVersion().setAssignedIdentifer(assignedIdentifer);
    }
    
    @Transient
    public String getSubmissionMessage() {
        return getLastVersion().getSubmissionMessage();
    }

    public void setSubmissionMessage(String submissionMessage) {
        getLastVersion().setSubmissionMessage(submissionMessage);
    }
    
    @Transient
    public String getSubmissionUrl() {
        return getLastVersion().getSubmissionMessage();
    }

    public void setSubmissionUrl(String submissionUrl) {
        this.getLastVersion().setSubmissionUrl(submissionUrl);
    }
    
    @Transient
    public Date getAmendedOn(){
    	return this.getLastVersion().getAmendedOn();
    }
    
    public void setAmendedOn(Date amendedOn){
    	this.getLastVersion().setAmendedOn(amendedOn);
    }


    @OneToOne
    @JoinColumn(name = "rct_id")
    @Cascade( { CascadeType.LOCK })
    public ReportDefinition getReportDefinition() {
        return reportDefinition;
    }

    public void setReportDefinition(ReportDefinition reportDefinition) {
        this.reportDefinition = reportDefinition;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rpsh_id", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<ScheduledNotification> getScheduledNotifications() {
        return notifications;
    }

    public void setScheduledNotifications(List<ScheduledNotification> notifications) {
        this.notifications = notifications;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }

    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        this.aeReport = aeReport;
    }

    @Transient
    public String getName() {
        return reportDefinition.getName();
    }
    
    @Transient
    public String getLabel(){
    	return reportDefinition.getLabel();
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "report")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<ReportDelivery> getReportDeliveries() {
        return deliveries;
    }

    public void setReportDeliveries(List<ReportDelivery> deliveries) {
        this.deliveries = deliveries;
    }

    
    @Transient
    public Boolean getPhysicianSignoff() {
        return getLastVersion().getPhysicianSignoff();
    }

    public void setPhysicianSignoff(Boolean physicianSignoff) {
    	getLastVersion().setPhysicianSignoff(physicianSignoff);
    }

    public List<String> findEmailAddressByRole(String roleName){
    	List<String> emails = new ArrayList<String>();
    	if(StringUtils.equals(roleName, "REP")){
    		if(getReporter() != null){
    			String email = getReporter().getEmailAddress();
        		if(StringUtils.isNotEmpty(email)) emails.add(email);
    		}
    	}else if(StringUtils.equals(roleName, "SUB")){
    		if(getSubmitter() != null){
    			String email = getSubmitter().getEmailAddress();
        		if(StringUtils.isNotEmpty(email)) emails.add(email);
    		}
    	}else if(StringUtils.equals(roleName, "PHY")){
    		if(getPhysician() != null){
    			String email = getPhysician().getEmailAddress();
    			if(StringUtils.isNotEmpty(email)) emails.add(email);
    		}
    	}
    	
    	return emails;
    }
    
    /**
     * This method will return the list of email recipients associated with this report. 
     *  - Email recipients from deliveries
     *  - CC Emails from ReportVersion (Last version) 
     */
    @Transient
    public List<String> getEmailRecipients(){
    	List<String> emailAddressesTemp = new ArrayList<String>();
    	if(deliveries != null){
    		for(ReportDelivery rd : deliveries){
    			if(rd.isEmailType()) emailAddressesTemp.add(rd.getEndPoint());
    		}
    	}
    	//now include the CC emails.
    	ReportVersion lastVersion = getLastVersion();
    	if(lastVersion != null){
    		lastVersion.getCcEmails();
    		String[] ccEmails = lastVersion.getEmailAsArray();
    		if(ccEmails != null){
    			for(String ccEmail : ccEmails){
    				String email = ccEmail.trim();
    				if(StringUtils.isNotEmpty(email))	emailAddressesTemp.add(email);
    			}
    		}
    	}
    	
    	return emailAddressesTemp;
    }
    @Transient
    public List<String> getEmailAddresses() {
		return emailAddresses;
	}


	public void setEmailAddresses(List<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
    
    @Transient
    public List<ReportDelivery> getExternalSystemDeliveries(){
    	List<ReportDelivery> externalDeliveries = new ArrayList<ReportDelivery>();
    	if(deliveries != null){
    		for(ReportDelivery rd : deliveries){
    			if(rd.isSystemType()) externalDeliveries.add(rd);
    		}
    	}
    	return externalDeliveries;
    }
    
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<ReportVersion> getReportVersions() {
        return reportVersions;
    }

    public void setReportVersions(List<ReportVersion> reportVersions) {
        this.reportVersions = reportVersions;
    }
    
    public boolean isManuallySelected() {
		return manuallySelected;
	}
    
    public void setManuallySelected(boolean manuallySelected) {
		this.manuallySelected = manuallySelected;
	}

    // //// OBJECT METHODS

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report [").append("id : ").append(getId()).append(", createdOn :").append(
                        String.valueOf(getCreatedOn())).append(", submittedOn :").append(
                        String.valueOf(getSubmittedOn())).append(", dueOn :").append(
                        String.valueOf(getDueOn()));
        sb.append("\r\n notifications :");
        if (notifications != null) {
            for (ScheduledNotification sn : notifications) {
                sb.append("\r\n").append(String.valueOf(sn));
            }
        }
        if (deliveries != null) {
            for (ReportDelivery delivery : deliveries) {
                sb.append("\r\n").append(String.valueOf(delivery));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Transient
    public Map<Object, Object> getContextVariables() {
        //TODO : properly populate the following....
        //TODO: add appropriate null-checks
        Map<Object, Object> map = new HashMap<Object, Object>();

        String primaryIdentifier = getAeReport().getAssignment().getParticipant().getPrimaryIdentifierValue();
        map.put("patientId", primaryIdentifier == null ? "xxxx" : primaryIdentifier);//Patient ID
        map.put("reportId", getAeReport().getId());//Report ID
        map.put("reportURL", "/pages/ae/edit?aeReport=" + getAeReport().getId() + "&report=" + getId());//URL To Report
        map.put("report", this);
        map.put("study", getAeReport().getStudy());
        return map;
    }

    @Transient
    public List<String> getMandatoryFieldList() {
        List<String> fields = new LinkedList<String>();
        if (getReportDefinition().getMandatoryFields() != null) {
            for (ReportMandatoryFieldDefinition field : getReportDefinition().getMandatoryFields()) {
                if (field.getMandatory().equals(Mandatory.MANDATORY)) fields.add(field.getFieldPath());
            }
        }
        return fields;
    }
    
    @Transient
    public  Boolean isSponsorReport(String nciInstituteCode){
    	if(reportDefinition.getOrganization().getNciInstituteCode().equals(nciInstituteCode))
    		return true;
    	else
    		return false;
    }
    
    
    /**
     * This method returns true if the last reportVersion is in Submitted state.
     * @return
     */
    @Transient
    public Boolean isSubmitted(){
    	if(this.getLastVersion() != null && this.getLastVersion().getReportStatus() == ReportStatus.COMPLETED)
    		return true;
    	return false;
    }
    
    /**
     * This method returns true if the last reportVersion is in Amended state.
     * @return
     */
    @Transient
    public Boolean isAmended(){
    	if(this.getLastVersion() != null && this.getLastVersion().getReportStatus() == ReportStatus.AMENDED)
    		return true;
    	return false;
    }
    
    /**
     * All the reports, which can be submitted is considered active. 
     * The reports in {@link ReportStatus#WITHDRAWN} or {@link ReportStatus#REPLACED} or {@link ReportStatus#COMPLETED} or {@link ReportStatus#AMENDED}are considered inactive. 
     * @return
     */
    @Transient
    public boolean isActive(){
    	return !isHavingStatus(ReportStatus.WITHDRAWN, ReportStatus.REPLACED, ReportStatus.AMENDED, ReportStatus.COMPLETED);
    }
    
    /**
     * Returns the attribution required flag, associated to the {@link ReportDefinition}
     * @return
     */
    @Transient
    public boolean isAttributionRequired(){
    	return reportDefinition.getAttributionRequired();
    }
   
    /**
     * Returns true, if the status of this report is any of the input reportStatus
     * @param reportStatus
     * @return
     */
    @Transient
    public boolean isHavingStatus(ReportStatus...reportStatus){
    	boolean retVal = false;
    	ReportStatus status = getStatus();
    	for(ReportStatus rpStatus : reportStatus){
    		retVal = retVal || rpStatus.equals(status);
    	}
    	return retVal;
    }
    
    /**
     * True, if the report is overdue,ie. the dueOn is passed. 
     * @return
     */
    @Transient
    public boolean isOverdue(){
    	ReportStatus status = getStatus();
    	Date dueOn = getDueOn();
    	
    	if(status != ReportStatus.PENDING || status != ReportStatus.INPROCESS ) return false;
    	return (dueOn != null && new Date().getTime() > dueOn.getTime());
    }
    
    /**
     * Will return true the report definition says that it can be amended.
     */
    @Transient
    public boolean isAmendable() {
    	return reportDefinition.getAmendable();
	}
   
    @Transient
    public boolean isOfSameOrganizationAndType(ReportDefinition rd){
    	return getReportDefinition().isOfSameReportTypeAndOrganization(rd);
    }
    
    /**
     * This method will return true, if the adverse event is reported in this report. 
     * @param ae
     * @return
     */
    @Transient
    public boolean isReported(AdverseEvent ae){
    	if(BooleanUtils.isTrue(ae.getReported())){
    		List<ReportedAdverseEvent> reportedAdverseEvents = getLastVersion().getReportedAdversEvents();
    		if(reportedAdverseEvents != null){
    			for(ReportedAdverseEvent reportedAe : reportedAdverseEvents){
    				if(ae.getId().equals(reportedAe.getAdverseEvent().getId())){
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    public String deriveAdeersReportTypeIndicator() {
    	//return this._REGULAR_REPORT;
    	
    	ReportType reportType = getReportDefinition().getReportType();
    	//Get Reports that are completed and amended.
		List<Report> reportList = getAeReport().listReportsHavingStatus(ReportStatus.AMENDED,ReportStatus.COMPLETED);
		//Filter above reports to get reports of same Group(example:AdEERS ) and Organization of the report.
		List<Report> reportListOfSameGroupAndOrg = new ArrayList<Report>();
		//Notification reports Count.
		int ReportsWithTypeNotificationCnt = 0;
		for (Report rep:reportList) {
			if (isOfSameOrganizationAndType(rep.getReportDefinition())) {
				reportListOfSameGroupAndOrg.add(rep);
				if (rep.getReportDefinition().getReportType().equals(ReportType.NOTIFICATION)){
					ReportsWithTypeNotificationCnt++;
				}
			}
		}
		//find last submitted report from above list 
		Report lastSubmittedReport = findLastSubmittedReport(reportListOfSameGroupAndOrg);
		// if type is notification 
		if (reportType.equals(ReportType.NOTIFICATION)) {
			if (reportListOfSameGroupAndOrg.size() == 0) {
				return _24HR_NOTIFICATION;
			} else {
				setAssignedIdentifer(lastSubmittedReport.getAssignedIdentifer());
				return _24HR_AMENDMENT;
			}
		}
		// if type is report 
		if (reportType.equals(ReportType.REPORT)) {
			if (reportListOfSameGroupAndOrg.size() == 0) {
				return _REGULAR_REPORT;
			} 
			// if last submitted report is REPORT 
			if (lastSubmittedReport != null && lastSubmittedReport.getReportDefinition().getReportType().equals(ReportType.REPORT)) {
				setAssignedIdentifer(lastSubmittedReport.getAssignedIdentifer());
				return _REGULAR_AMENDMENT;
			// if last submitted report is NOTIFICATION
			} else if (lastSubmittedReport != null && lastSubmittedReport.getReportDefinition().getReportType().equals(ReportType.NOTIFICATION)) {
				String reportVersionIdStr = lastSubmittedReport.getReportVersions().get(0).getReportVersionId();
				int reportVersionId = Integer.parseInt(reportVersionIdStr);
				//  COMPLETE the notofication if the only NOTIFICATION is 24-HR Notification (not AMEND of a Regular Report).
				if (ReportsWithTypeNotificationCnt == 1 && reportVersionId == 0) {
					setAssignedIdentifer(lastSubmittedReport.getAssignedIdentifer());
					return _24HR_NOTIFICATION_COMPLETE;
				}
				// amend  the notofication if the only NOTIFICATION  is AMEND of a Regular Report (24-HR amend)
				if (ReportsWithTypeNotificationCnt == 1 && reportVersionId > 0) {
					setAssignedIdentifer(lastSubmittedReport.getAssignedIdentifer());
					return _24HR_AMENDMENT_COMPLETE;
				}
				
				//  AMEND the COMPLETEd report 
				if (ReportsWithTypeNotificationCnt > 1) {
					setAssignedIdentifer(lastSubmittedReport.getAssignedIdentifer());
					return _24HR_AMENDMENT_COMPLETE;
				}
			}
		}    	
		return null;
    }
    
    @OneToMany
    @JoinColumn(name = "report_id", nullable = true)
    @IndexColumn(name = "list_index", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @OrderBy(value = "createdDate DESC")
    public List<ReportReviewComment> getReviewComments() {
    	if(reviewComments == null) reviewComments = new ArrayList<ReportReviewComment>();
		return reviewComments;
	}
    
    public void setReviewComments(
			List<ReportReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}

    /**
     * This method will find the recently submitted report
     */
    private Report findLastSubmittedReport(List<Report> reports){
    	Report theReport = null;
		for(Report report : reports){
    			if(theReport == null){
    				theReport = report;
    			}else{
    				if(DateUtils.compateDateAndTime(theReport.getSubmittedOn(), report.getSubmittedOn()) < 0){
    					theReport = report;
    				}
    			}
    	}
    	return theReport;
    }
    
    @Transient
	public String getAdeersReportTypeIndicator() {
    	return adeersReportTypeIndicator;
	}
    
	public void setAdeersReportTypeIndicator(String adeersReportTypeIndicator) {
		this.adeersReportTypeIndicator = adeersReportTypeIndicator;
	}
	

    
    @Transient
	public boolean getExternalSystem() {
    	externalSystem =  hasSystemDeliveries();
    	return externalSystem;
	}
    
    @Transient
    public Reporter getReporter(){
    	return aeReport.getReporter();
    }
    
    @Transient
    public Submitter getSubmitter(){
    	return getLastVersion().getSubmitter();
    }
    
    @Transient
    public Physician getPhysician(){
    	return aeReport.getPhysician();
    }
    
    public void setSubmitter(Submitter submitter){
    	getLastVersion().setSubmitter(submitter);
    }
    
    public Integer getWorkflowId() {
    	return workflowId;
    }
    
    public void setWorkflowId(Integer workflowId){
    	this.workflowId = workflowId;
    }
 
    @Column(name = "review_status_code")
    @Type(type = "reviewStatus")
    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }
    
    public void setReviewStatus(ReviewStatus reviewStatus){
    	this.reviewStatus = reviewStatus;
    }
}
