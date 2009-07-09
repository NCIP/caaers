package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "REPORT_TRACKING")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_tracking_id") })
public class ReportTracking extends AbstractMutableDomainObject implements Serializable {
	private ReportTrackingStatus submissionInitiated;
	private ReportTrackingStatus caaersXMLGenerated;
	private ReportTrackingStatus attachmentGenerated;
	private ReportTrackingStatus emailNotification;
	private ReportTrackingStatus connectedToESB;
	private ReportTrackingStatus connectedToExternalSystem;
	private ReportTrackingStatus submissionToExternalSystem;
	private ReportTrackingStatus responseFromExternalSystem;
	private ReportTrackingStatus notificationToSubmitter;
	private ReportVersion reportVersion;
	private Integer attemptNumber;

	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getSubmissionInitiated() {
		return submissionInitiated;
	}
	public void setSubmissionInitiated(ReportTrackingStatus submissionInitiated) {
		this.submissionInitiated = submissionInitiated;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "xml_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getCaaersXMLGenerated() {
		return caaersXMLGenerated;
	}
	public void setCaaersXMLGenerated(ReportTrackingStatus caaersXMLGenerated) {
		this.caaersXMLGenerated = caaersXMLGenerated;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})	
	public ReportTrackingStatus getAttachmentGenerated() {
		return attachmentGenerated;
	}
	public void setAttachmentGenerated(ReportTrackingStatus attachmentGenerated) {
		this.attachmentGenerated = attachmentGenerated;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emailn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(ReportTrackingStatus emailNotification) {
		this.emailNotification = emailNotification;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "esbcn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})	
	public ReportTrackingStatus getConnectedToESB() {
		return connectedToESB;
	}
	public void setConnectedToESB(ReportTrackingStatus connectedToESB) {
		this.connectedToESB = connectedToESB;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemcn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})	
	public ReportTrackingStatus getConnectedToExternalSystem() {
		return connectedToExternalSystem;
	}
	public void setConnectedToExternalSystem(
			ReportTrackingStatus connectedToExternalSystem) {
		this.connectedToExternalSystem = connectedToExternalSystem;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "syssub_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getSubmissionToExternalSystem() {
		return submissionToExternalSystem;
	}
	public void setSubmissionToExternalSystem(
			ReportTrackingStatus submissionToExternalSystem) {
		this.submissionToExternalSystem = submissionToExternalSystem;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getResponseFromExternalSystem() {
		return responseFromExternalSystem;
	}
	public void setResponseFromExternalSystem(
			ReportTrackingStatus responseFromExternalSystem) {
		this.responseFromExternalSystem = responseFromExternalSystem;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emailsubn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getNotificationToSubmitter() {
		return notificationToSubmitter;
	}
	public void setNotificationToSubmitter(
			ReportTrackingStatus notificationToSubmitter) {
		this.notificationToSubmitter = notificationToSubmitter;
	}
	

	//@OneToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = false)
	public ReportVersion getReportVersion() {
		return reportVersion;
	}
	public void setReportVersion(ReportVersion reportVersion) {
		this.reportVersion = reportVersion;
	}
	public void setAttemptNumber(Integer attemptNumber) {
		this.attemptNumber = attemptNumber;
	}
	public Integer getAttemptNumber() {
		return attemptNumber;
	}

	
}
