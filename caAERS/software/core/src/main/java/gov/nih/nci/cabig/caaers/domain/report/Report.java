/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;

 
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

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4001323963140432224L;

	/** The required. */
	private boolean required;

    /** The ae report. */
    private ExpeditedAdverseEventReport aeReport;

    /** The report definition. */
    private ReportDefinition reportDefinition;

    /** The notifications. */
    private List<ScheduledNotification> notifications;

    /** The report versions. */
    private List<ReportVersion> reportVersions;

    /** The deliveries. */
    private List<ReportDelivery> deliveries;
    
    /** The manually selected. */
    private boolean manuallySelected;
    
    /** The external system. */
    private boolean externalSystem;
    
    /** The adeers report type indicator. */
    private String adeersReportTypeIndicator;
    
    /** The email addresses. */
    private List<String> emailAddresses;
    
    /** The review status. */
    private ReviewStatus reviewStatus;
    
    /** The workflow id. */
    private Integer workflowId;
    
    /** The review comments. */
    private List<ReportReviewComment> reviewComments;

    /** The mandatory fields. */
    private List<ReportMandatoryField> mandatoryFields;
    
    
    /** The _ regula r_ report. */
    private  String _REGULAR_REPORT = "Regular report";
    
    /** The _24 h r_ notification. */
    private  String _24HR_NOTIFICATION = "24-hr notification";
    
    /** The _24 h r_ notificatio n_ complete. */
    private  String _24HR_NOTIFICATION_COMPLETE = "24-hr notification complete";
    
    /** The _24 h r_ amendment. */
    private  String _24HR_AMENDMENT = "24-hr amendment";
    
    /** The _24 h r_ amendmen t_ complete. */
    private  String _24HR_AMENDMENT_COMPLETE = "24-hr amendment complete";
    
    /** The _ regula r_ amendment. */
    private  String _REGULAR_AMENDMENT = "Regular amendment";

    // dummy field to be used in serialized XML to check the status of the delivery for FDA
    /** The submitted to fda. */
    private String submittedToFDA = "No";

    /**
     * Instantiates a new report.
     */
    public Report(){
    	//for hibernate
    }
    
  
    // //// LOGIC

    /**
     * Adds the report version.
     *
     * @param reportVersion the report version
     */
    public void addReportVersion(ReportVersion reportVersion) {
        if (reportVersions == null) reportVersions = new ArrayList<ReportVersion>();
        reportVersion.setReport(this);
        reportVersions.add(reportVersion);
    }

    /**
     * Adds the scheduled notification.
     *
     * @param nf the nf
     */
    public void addScheduledNotification(ScheduledNotification nf) {
        if (notifications == null) notifications = new ArrayList<ScheduledNotification>();
        notifications.add(nf);
    }

    /**
     * Adds the report delivery.
     *
     * @param rd the rd
     */
    public void addReportDelivery(ReportDelivery rd) {
        if (this.deliveries == null) deliveries = new ArrayList<ReportDelivery>();
        deliveries.add(rd);
        rd.setReport(this);
    }

    /**
     * Checks for scheduled notifications.
     *
     * @return true, if successful
     */
    public boolean hasScheduledNotifications() {
        return (notifications != null) && (!notifications.isEmpty());
    }
    
    /**
     * This method will loop through the delivery defs,to see if there is an endpoint of type url.
     *
     * @return true, if successful
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
     *
     * @param nfId the nf id
     * @return the scheduled notification
     */
    public ScheduledNotification fetchScheduledNotification(Integer nfId) {
        if (notifications == null) return null;
        for (ScheduledNotification nf : notifications) {
            if (nf.getId().equals(nfId)) return nf;
        }
        return null;
    }

    /**
     * Gets the last version.
     *
     * @return the last version
     */
    @Transient
    public ReportVersion getLastVersion() {
    	if(reportVersions == null || reportVersions.isEmpty()){
    		ReportVersion defaultVersion = new ReportVersion();
    		addReportVersion(defaultVersion);
    	}
    	return reportVersions.get(reportVersions.size() -1);
    }

    // //// BEAN PROPERTIES
    /**
     * Gets the created on.
     *
     * @return the created on
     */
    @Transient
    public Date getCreatedOn() {
      return getLastVersion().getCreatedOn();
    }

    /**
     * Sets the created on.
     *
     * @param createdOn the new created on
     */
    public void setCreatedOn(Date createdOn) {
       getLastVersion().setCreatedOn(createdOn);
    }
    

    /**
     * Gets the due on.
     *
     * @return the due on
     */
    @Transient
    public Date getDueOn() {
        return getLastVersion().getDueOn();
    }

    /**
     * Sets the due on.
     *
     * @param dueOn the new due on
     */
    public void setDueOn(Date dueOn) {
        this.getLastVersion().setDueOn(dueOn);
    }

    /**
     * Gets the submitted on.
     *
     * @return the submitted on
     */
    @Transient
    public Date getSubmittedOn() {
        return getLastVersion().getSubmittedOn();
    }

    /**
     * Sets the submitted on.
     *
     * @param submittedOn the new submitted on
     */
    public void setSubmittedOn(Date submittedOn) {
        getLastVersion().setSubmittedOn(submittedOn);
    }
    
    /**
     * Gets the withdrawn on.
     *
     * @return the withdrawn on
     */
    @Transient
    public Date getWithdrawnOn(){
    	return getLastVersion().getWithdrawnOn();
    }
    
    /**
     * Sets the withdrawn on.
     *
     * @param withdrawnOn the new withdrawn on
     */
    public void setWithdrawnOn(Date withdrawnOn){
    	getLastVersion().setWithdrawnOn(withdrawnOn);
    }
    
    /**
     * Gets the status.
     *
     * @return the status
     */
    @Transient
    public ReportStatus getStatus() {
        return getLastVersion().getReportStatus();
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(ReportStatus status) {
        this.getLastVersion().setReportStatus(status);
    }
    
    /**
     * Gets the assigned identifer.
     *
     * @return the assigned identifer
     */
    @Transient
    public String getAssignedIdentifer() {
        return getLastVersion().getAssignedIdentifer();
    }

    /**
     * Sets the assigned identifer.
     *
     * @param assignedIdentifer the new assigned identifer
     */
    public void setAssignedIdentifer(String assignedIdentifer) {
        this.getLastVersion().setAssignedIdentifer(assignedIdentifer);
    }
    
    /**
     * Gets the submission message.
     *
     * @return the submission message
     */
    @Transient
    public String getSubmissionMessage() {
        return getLastVersion().getSubmissionMessage();
    }

    /**
     * Sets the submission message.
     *
     * @param submissionMessage the new submission message
     */
    public void setSubmissionMessage(String submissionMessage) {
        getLastVersion().setSubmissionMessage(submissionMessage);
    }
    
    /**
     * Gets the submission url.
     *
     * @return the submission url
     */
    @Transient
    public String getSubmissionUrl() {
        return getLastVersion().getSubmissionMessage();
    }

    /**
     * Sets the submission url.
     *
     * @param submissionUrl the new submission url
     */
    public void setSubmissionUrl(String submissionUrl) {
        this.getLastVersion().setSubmissionUrl(submissionUrl);
    }
    
    /**
     * Gets the amended on.
     *
     * @return the amended on
     */
    @Transient
    public Date getAmendedOn(){
    	return this.getLastVersion().getAmendedOn();
    }
    
    /**
     * Sets the amended on.
     *
     * @param amendedOn the new amended on
     */
    public void setAmendedOn(Date amendedOn){
    	this.getLastVersion().setAmendedOn(amendedOn);
    }


    /**
     * Gets the report definition.
     *
     * @return the report definition
     */
    @OneToOne
    @JoinColumn(name = "rct_id")
    @Cascade( { CascadeType.LOCK })
    public ReportDefinition getReportDefinition() {
        return reportDefinition;
    }

    /**
     * Sets the report definition.
     *
     * @param reportDefinition the new report definition
     */
    public void setReportDefinition(ReportDefinition reportDefinition) {
        this.reportDefinition = reportDefinition;
    }

    /**
     * Gets the scheduled notifications.
     *
     * @return the scheduled notifications
     */
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "rpsh_id", nullable = false)
    @Cascade(value = { CascadeType.ALL  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ScheduledNotification> getScheduledNotifications() {
        return notifications;
    }

    /**
     * Sets the scheduled notifications.
     *
     * @param notifications the new scheduled notifications
     */
    public void setScheduledNotifications(List<ScheduledNotification> notifications) {
        this.notifications = notifications;
    }

    /**
     * Gets the ae report.
     *
     * @return the ae report
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    public ExpeditedAdverseEventReport getAeReport() {
        return aeReport;
    }

    /**
     * Sets the ae report.
     *
     * @param aeReport the new ae report
     */
    public void setAeReport(ExpeditedAdverseEventReport aeReport) {
        this.aeReport = aeReport;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
        return reportDefinition.getName();
    }
    
    /**
     * Gets the label.
     *
     * @return the label
     */
    @Transient
    public String getLabel(){
    	return reportDefinition.getLabel();
    }

    /**
     * Checks if is required.
     *
     * @return true, if is required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Sets the required.
     *
     * @param required the new required
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Gets the report deliveries.
     *
     * @return the report deliveries
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "report", orphanRemoval = true)
    @Cascade(value = { CascadeType.ALL  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportDelivery> getReportDeliveries() {
        return deliveries;
    }

    /**
     * Sets the report deliveries.
     *
     * @param deliveries the new report deliveries
     */
    public void setReportDeliveries(List<ReportDelivery> deliveries) {
        this.deliveries = deliveries;
    }

    
    /**
     * Gets the physician signoff.
     *
     * @return the physician signoff
     */
    @Transient
    public Boolean getPhysicianSignoff() {
        return getLastVersion().getPhysicianSignoff();
    }

    /**
     * Sets the physician signoff.
     *
     * @param physicianSignoff the new physician signoff
     */
    public void setPhysicianSignoff(Boolean physicianSignoff) {
    	getLastVersion().setPhysicianSignoff(physicianSignoff);
    }

    /**
     * Find email address by role.
     *
     * @param roleName the role name
     * @return the list
     */
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
     * - Email recipients from deliveries
     * - CC Emails from ReportVersion (Last version)
     *
     * @return the email recipients
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
    
    /**
     * Gets the email addresses.
     *
     * @return the email addresses
     */
    @Transient
    public List<String> getEmailAddresses() {
		return emailAddresses;
	}


	/**
	 * Sets the email addresses.
	 *
	 * @param emailAddresses the new email addresses
	 */
	public void setEmailAddresses(List<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
    
    /**
     * Gets the external system deliveries.
     *
     * @return the external system deliveries
     */
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
    
    /**
     * Gets the report versions.
     *
     * @return the report versions
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = { CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportVersion> getReportVersions() {
        return reportVersions;
    }

    /**
     * Sets the report versions.
     *
     * @param reportVersions the new report versions
     */
    public void setReportVersions(List<ReportVersion> reportVersions) {
        this.reportVersions = reportVersions;
    }
    
    /**
     * Checks if is manually selected.
     *
     * @return true, if is manually selected
     */
    public boolean isManuallySelected() {
		return manuallySelected;
	}
    
    /**
     * Sets the manually selected.
     *
     * @param manuallySelected the new manually selected
     */
    public void setManuallySelected(boolean manuallySelected) {
		this.manuallySelected = manuallySelected;
	}

    // //// OBJECT METHODS

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
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

    /**
     * Gets the context variables.
     *
     * @return the context variables
     */
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

    /*
    *
    * This will return only the fields marked as mandatory 
    */
    /**
     * Gets the path of non self referenced mandatory fields.
     *
     * @return the path of non self referenced mandatory fields
     */
    @Transient
    public List<String> getPathOfNonSelfReferencedMandatoryFields() {
        List<String> fields = new LinkedList<String>();
        for(ReportMandatoryField mandatoryField : getFieldsByApplicability(Mandatory.MANDATORY)){
            if(!mandatoryField.isSelfReferenced())fields.add(mandatoryField.getFieldPath());
        }
        return fields;
    }


    /*
    *
    * This will return only the fields marked as mandatory
    */
    /**
     * Gets the path of self referenced mandatory fields.
     *
     * @return the path of self referenced mandatory fields
     */
    @Transient
    public List<String> getPathOfSelfReferencedMandatoryFields() {
        List<String> fields = new LinkedList<String>();
        for(ReportMandatoryField mandatoryField : getFieldsByApplicability(Mandatory.MANDATORY)){
            if(mandatoryField.isSelfReferenced()) fields.add(mandatoryField.getFieldPath());
        }
        return fields;
    }


    /**
     * Will list all the fields that are applicable (ie. Mandatory and Optional)
     *
     * @return the path of applicable fields
     */
    @Transient
    public List<String> getPathOfApplicableFields(){
       Set<String> fields = new LinkedHashSet<String>();
        for(ReportMandatoryField mandatoryField : getFieldsByApplicability(Mandatory.MANDATORY, Mandatory.OPTIONAL)){
            fields.add(mandatoryField.getFieldPath().replaceAll("(\\[\\d+\\])", ""));
        }
        return new ArrayList(fields);
    }

    /**
     * Will return the path of fields which are Not Applicable.
     *
     * @return the path of not applicable fields
     */
    @Transient
    public List<String> getPathOfNotApplicableFields(){
        List<String> fields = new LinkedList<String>();
        for(ReportMandatoryField mandatoryField : getFieldsByApplicability(Mandatory.NA)){
            fields.add(mandatoryField.getFieldPath());
        }
        return fields;
    }
    
    /*
    * Will find the list of mandatory fields, having the same Mandatory flag, mentioned
    * in mandatory types. 
    */
    /**
     * Gets the fields by applicability.
     *
     * @param mandatoryTypes the mandatory types
     * @return the fields by applicability
     */
    @Transient
    public List<ReportMandatoryField> getFieldsByApplicability(final Mandatory... mandatoryTypes) {
        ArrayList<ReportMandatoryField> reportMandatoryFields = new ArrayList<ReportMandatoryField>();
        for(ReportMandatoryField mf : getMandatoryFields()){
            if(ArrayUtils.contains(mandatoryTypes, mf.getMandatory())){
                reportMandatoryFields.add(mf);
            }
        }
        return reportMandatoryFields;
    }

    /**
     * Checks if is sponsor report.
     *
     * @param nciInstituteCode the nci institute code
     * @return the boolean
     */
    @Transient
    public  Boolean isSponsorReport(String nciInstituteCode){
    	if(reportDefinition.getOrganization().getNciInstituteCode().equals(nciInstituteCode))
    		return true;
    	else
    		return false;
    }
    
    
    /**
     * This method returns true if the last reportVersion is in Submitted state.
     *
     * @return the boolean
     */
    @Transient
    public Boolean isSubmitted(){
    	if(this.getLastVersion() != null && this.getLastVersion().getReportStatus() == ReportStatus.COMPLETED)
    		return true;
    	return false;
    }
    
    /**
     * This method returns true if the last reportVersion is in Amended state.
     *
     * @return the boolean
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
     *
     * @return true, if is active
     */
    @Transient
    public boolean isActive(){
    	return !isHavingStatus(ReportStatus.WITHDRAWN, ReportStatus.REPLACED, ReportStatus.AMENDED, ReportStatus.COMPLETED);
    }
    
    /**
     * Returns the attribution required flag, associated to the {@link ReportDefinition}.
     *
     * @return true, if is attribution required
     */
    @Transient
    public boolean isAttributionRequired(){
    	return reportDefinition.getAttributionRequired();
    }
   
    /**
     * Returns true, if the status of this report is any of the input reportStatus.
     *
     * @param reportStatus the report status
     * @return true, if is having status
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
     *
     * @return true, if is overdue
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
     *
     * @return true, if is amendable
     */
    @Transient
    public boolean isAmendable() {
    	return reportDefinition.getAmendable();
	}
   
    /**
     * Checks if is of same organization and type.
     *
     * @param rd the rd
     * @return true, if is of same organization and type
     */
    @Transient
    public boolean isOfSameOrganizationAndType(ReportDefinition rd){
    	return getReportDefinition().isOfSameReportTypeAndOrganization(rd);
    }
    
    /**
     * This method will return true, if the adverse event is reported in this report.
     *
     * @param ae the ae
     * @return true, if is reported
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
    
    /**
     * Derive adeers report type indicator.
     *
     * @return the string
     */
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

    /**
     * Reverse the comments so that it will return the reversed list.
     *
     * @return the review comments
     */
    @Transient
    public List<ReportReviewComment> getReviewComments() {
    	ArrayList<ReportReviewComment> comments = new ArrayList<ReportReviewComment>(getReviewCommentsInternal());
        Collections.reverse(comments);
		return comments;
	}
    
    /**
     * Sets the review comments.
     *
     * @param reviewComments the new review comments
     */
    public void setReviewComments(List<ReportReviewComment> reviewComments) {
		setReviewCommentsInternal(reviewComments);
	}

    //http://opensource.atlassian.com/projects/hibernate/browse/HHH-2802
    /**
     * Gets the review comments internal.
     *
     * @return the review comments internal
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = true )
    @IndexColumn(name = "list_index", nullable = false)
    @Cascade(value = { CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportReviewComment> getReviewCommentsInternal() {
    	if(reviewComments == null) reviewComments = new ArrayList<ReportReviewComment>();
		return reviewComments;
	}

    /**
     * Sets the review comments internal.
     *
     * @param reviewComments the new review comments internal
     */
    public void setReviewCommentsInternal(List<ReportReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}
    
    /**
     * This method will find the recently submitted report.
     *
     * @param reports the reports
     * @return the report
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
    
    /**
     * Gets the adeers report type indicator.
     *
     * @return the adeers report type indicator
     */
    @Transient
	public String getAdeersReportTypeIndicator() {
    	return adeersReportTypeIndicator;
	}
    
	/**
	 * Sets the adeers report type indicator.
	 *
	 * @param adeersReportTypeIndicator the new adeers report type indicator
	 */
	public void setAdeersReportTypeIndicator(String adeersReportTypeIndicator) {
		this.adeersReportTypeIndicator = adeersReportTypeIndicator;
	}
	

    
    /**
     * Gets the external system.
     *
     * @return the external system
     */
    @Transient
	public boolean getExternalSystem() {
    	externalSystem =  hasSystemDeliveries();
    	return externalSystem;
	}
    
    /**
     * Gets the reporter.
     *
     * @return the reporter
     */
    @Transient
    public Reporter getReporter(){
    	return aeReport.getReporter();
    }
    
    /**
     * Gets the submitter.
     *
     * @return the submitter
     */
    @Transient
    public Submitter getSubmitter(){
    	return getLastVersion().getSubmitter();
    }
    
    /**
     * Gets the physician.
     *
     * @return the physician
     */
    @Transient
    public Physician getPhysician(){
    	return aeReport.getPhysician();
    }
    
    /**
     * Sets the submitter.
     *
     * @param submitter the new submitter
     */
    public void setSubmitter(Submitter submitter){
    	getLastVersion().setSubmitter(submitter);
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
     * Gets the mandatory fields.
     *
     * @return the mandatory fields
     */
    @Transient
    public List<ReportMandatoryField> getMandatoryFields() {
        return mandatoryFields;
    }

    /**
     * Sets the mandatory fields.
     *
     * @param mandatoryFields the new mandatory fields
     */
    public void setMandatoryFields(List<ReportMandatoryField> mandatoryFields) {
        this.mandatoryFields = mandatoryFields;
    }

    /**
     * Checks if is workflow enabled.
     *
     * @return true, if is workflow enabled
     */
    @Transient
    public boolean isWorkflowEnabled(){
       if(getReportDefinition() != null){
          return (getReportDefinition().getWorkflowEnabled() != null && getReportDefinition().getWorkflowEnabled()) && getWorkflowId() != null;
       }
       return false;
    }

    /**
     * Gets the submitted to fda.
     *
     * @return the submitted to fda
     */
    @Transient
    public String getSubmittedToFDA() {
        return submittedToFDA;
    }

    /**
     * Sets the submitted to fda.
     *
     * @param submittedToFDA the new submitted to fda
     */
    public void setSubmittedToFDA(String submittedToFDA) {
        this.submittedToFDA = submittedToFDA;
    }
}
