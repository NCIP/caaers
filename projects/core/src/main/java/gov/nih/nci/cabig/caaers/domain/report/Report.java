package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Submitter;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
public class Report extends AbstractMutableDomainObject implements Serializable {
    private boolean required;

    private ExpeditedAdverseEventReport aeReport;

    private ReportDefinition reportDefinition;

    private List<ScheduledNotification> notifications;

    private Date createdOn;

    private Date dueOn;

    private Date submittedOn;

    private Submitter submitter;

    private Boolean physicianSignoff;

    private String assignedIdentifer;

    private String submissionUrl;

    private String submissionMessage;

    private ReportStatus status = ReportStatus.PENDING;

    private List<ReportVersion> reportVersions;


    private List<ReportDelivery> deliveries;
    
  
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

        return reportVersions != null && reportVersions.size() > 0 ? reportVersions
                        .get(reportVersions.size() - 1) : null;
    }

    // //// BEAN PROPERTIES
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDueOn() {
        return dueOn;
    }

    public void setDueOn(Date dueOn) {
        this.dueOn = dueOn;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }

    @Transient
    public String getName() {
        return reportDefinition.getName();
    }

    @Column(name = "status_code")
    @Type(type = "reportStatus")
    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
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
    public void addSubmitter() {
        if (submitter == null) setSubmitter(new Submitter());
    }

    @Transient
    public Submitter getSubmitter() {
        // if (submitter == null) setSubmitter(new Submitter());
        return submitter;
    }

    @Transient
    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }
    
    @Transient
    public Boolean getPhysicianSignoff() {
        return getLastVersion().getPhysicianSignoff();
    }

    public void setPhysicianSignoff(Boolean physicianSignoff) {
    	getLastVersion().setPhysicianSignoff(physicianSignoff);
    }

    
    
    /**
     * This method will return the list of email recipients associated with this report. 
     *  - Email recipients from deliveries
     *  - CC Emails from ReportVersion (Last version) 
     */
    @Transient
    public List<String> getEmailRecipients(){
    	List<String> emailAddresses = new ArrayList<String>();
    	if(deliveries != null){
    		for(ReportDelivery rd : deliveries){
    			if(rd.isEmailType()) emailAddresses.add(rd.getEndPoint());
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
    				if(StringUtils.isNotEmpty(email))	emailAddresses.add(email);
    			}
    		}
    	}
    	
    	return emailAddresses;
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

    // //// OBJECT METHODS

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report [").append("id : ").append(getId()).append(", createdOn :").append(
                        String.valueOf(createdOn)).append(", submittedOn :").append(
                        String.valueOf(submittedOn)).append(", dueOn :").append(
                        String.valueOf(dueOn));
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

    @Transient
    public Map<Object, Object> getContextVariables() {
        //TODO : properly populate the following....
        //TODO: add appropriate null-checks
        Map<Object, Object> map = new HashMap<Object, Object>();

        String primaryIdentifier = getAeReport().getAssignment().getParticipant().getPrimaryIdentifierValue();
        map.put("patientId", primaryIdentifier == null ? "xxxx" : primaryIdentifier);//Patient ID
        map.put("reportId", getAeReport().getId());//Report ID
        map.put("reportURL", "caaers/pages/ae/edit?aeReport=" + getAeReport().getId());//URL To Report
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
    
    @Transient
    public String getCurrentVersion(){
    	return getLastVersion().getReportVersionId();
    }
    
    @Transient
    public Boolean getIsLatestVersion(){
    	String nciInstituteCode = reportDefinition.getOrganization().getNciInstituteCode();
    	if(getCurrentVersion().equals(aeReport.getCurrentVersionForSponsorReport(nciInstituteCode)))
    		return true;
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
   
    public void copySubmissionDetails(Report r){
    	this.assignedIdentifer = r.getAssignedIdentifer();
    	//copy the last version also.
    	getLastVersion().copySubmissionDetails(r.getLastVersion());
    }
    
    /**
     * Returns true, if the status of this report is any of the input reportStatus
     * @param reportStatus
     * @return
     */
    @Transient
    public boolean isHavingStatus(ReportStatus...reportStatus){
    	boolean retVal = false;
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
    	return (dueOn != null && new Date().getTime() > dueOn.getTime());
    }
   
}
