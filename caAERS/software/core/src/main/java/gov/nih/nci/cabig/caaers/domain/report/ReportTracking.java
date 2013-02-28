/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

 
/**
 * The Class ReportTracking.
 */
@Entity
@Table(name = "REPORT_TRACKING")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_tracking_id") })
public class ReportTracking extends AbstractMutableDomainObject implements Serializable {
	
	/** The submission initiated. */
	private ReportTrackingStatus submissionInitiated;
	
	/** The caaers xml generated. */
	private ReportTrackingStatus caaersXMLGenerated;
	
	/** The attachment generated. */
	private ReportTrackingStatus attachmentGenerated;
	
	/** The email notification. */
	private ReportTrackingStatus emailNotification;
	
	/** The connected to esb. */
	private ReportTrackingStatus connectedToESB;
	
	/** The connected to external system. */
	private ReportTrackingStatus connectedToExternalSystem;
	
	/** The submission to external system. */
	private ReportTrackingStatus submissionToExternalSystem;
	
	/** The response from external system. */
	private ReportTrackingStatus responseFromExternalSystem;
	
	/** The notification to submitter. */
	private ReportTrackingStatus notificationToSubmitter;
	
	/** The report version. */
	private ReportVersion reportVersion;
	
	/** The attempt number. */
	private Integer attemptNumber;

	
	/**
	 * Gets the submission initiated.
	 *
	 * @return the submission initiated
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getSubmissionInitiated() {
		return submissionInitiated;
	}
	
	/**
	 * Sets the submission initiated.
	 *
	 * @param submissionInitiated the new submission initiated
	 */
	public void setSubmissionInitiated(ReportTrackingStatus submissionInitiated) {
		this.submissionInitiated = submissionInitiated;
	}
	
	/**
	 * Gets the caaers xml generated.
	 *
	 * @return the caaers xml generated
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "xml_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getCaaersXMLGenerated() {
		return caaersXMLGenerated;
	}
	
	/**
	 * Sets the caaers xml generated.
	 *
	 * @param caaersXMLGenerated the new caaers xml generated
	 */
	public void setCaaersXMLGenerated(ReportTrackingStatus caaersXMLGenerated) {
		this.caaersXMLGenerated = caaersXMLGenerated;
	}
	
	/**
	 * Gets the attachment generated.
	 *
	 * @return the attachment generated
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})	
	public ReportTrackingStatus getAttachmentGenerated() {
		return attachmentGenerated;
	}
	
	/**
	 * Sets the attachment generated.
	 *
	 * @param attachmentGenerated the new attachment generated
	 */
	public void setAttachmentGenerated(ReportTrackingStatus attachmentGenerated) {
		this.attachmentGenerated = attachmentGenerated;
	}
	
	/**
	 * Gets the email notification.
	 *
	 * @return the email notification
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emailn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getEmailNotification() {
		return emailNotification;
	}
	
	/**
	 * Sets the email notification.
	 *
	 * @param emailNotification the new email notification
	 */
	public void setEmailNotification(ReportTrackingStatus emailNotification) {
		this.emailNotification = emailNotification;
	}

	/**
	 * Gets the connected to esb.
	 *
	 * @return the connected to esb
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "esbcn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})	
	public ReportTrackingStatus getConnectedToESB() {
		return connectedToESB;
	}
	
	/**
	 * Sets the connected to esb.
	 *
	 * @param connectedToESB the new connected to esb
	 */
	public void setConnectedToESB(ReportTrackingStatus connectedToESB) {
		this.connectedToESB = connectedToESB;
	}
	
	/**
	 * Gets the connected to external system.
	 *
	 * @return the connected to external system
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemcn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})	
	public ReportTrackingStatus getConnectedToExternalSystem() {
		return connectedToExternalSystem;
	}
	
	/**
	 * Sets the connected to external system.
	 *
	 * @param connectedToExternalSystem the new connected to external system
	 */
	public void setConnectedToExternalSystem(
			ReportTrackingStatus connectedToExternalSystem) {
		this.connectedToExternalSystem = connectedToExternalSystem;
	}

	/**
	 * Gets the submission to external system.
	 *
	 * @return the submission to external system
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "syssub_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getSubmissionToExternalSystem() {
		return submissionToExternalSystem;
	}
	
	/**
	 * Sets the submission to external system.
	 *
	 * @param submissionToExternalSystem the new submission to external system
	 */
	public void setSubmissionToExternalSystem(
			ReportTrackingStatus submissionToExternalSystem) {
		this.submissionToExternalSystem = submissionToExternalSystem;
	}
	
	/**
	 * Gets the response from external system.
	 *
	 * @return the response from external system
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getResponseFromExternalSystem() {
		return responseFromExternalSystem;
	}
	
	/**
	 * Sets the response from external system.
	 *
	 * @param responseFromExternalSystem the new response from external system
	 */
	public void setResponseFromExternalSystem(
			ReportTrackingStatus responseFromExternalSystem) {
		this.responseFromExternalSystem = responseFromExternalSystem;
	}
	
	/**
	 * Gets the notification to submitter.
	 *
	 * @return the notification to submitter
	 */
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emailsubn_trk_id" , nullable = true)
    @Cascade(value = {CascadeType.ALL})
	public ReportTrackingStatus getNotificationToSubmitter() {
		return notificationToSubmitter;
	}
	
	/**
	 * Sets the notification to submitter.
	 *
	 * @param notificationToSubmitter the new notification to submitter
	 */
	public void setNotificationToSubmitter(
			ReportTrackingStatus notificationToSubmitter) {
		this.notificationToSubmitter = notificationToSubmitter;
	}
	

	//@OneToOne(fetch = FetchType.LAZY)
	/**
	 * Gets the report version.
	 *
	 * @return the report version
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = false)
	public ReportVersion getReportVersion() {
		return reportVersion;
	}
	
	/**
	 * Sets the report version.
	 *
	 * @param reportVersion the new report version
	 */
	public void setReportVersion(ReportVersion reportVersion) {
		this.reportVersion = reportVersion;
	}
	
	/**
	 * Sets the attempt number.
	 *
	 * @param attemptNumber the new attempt number
	 */
	public void setAttemptNumber(Integer attemptNumber) {
		this.attemptNumber = attemptNumber;
	}
	
	/**
	 * Gets the attempt number.
	 *
	 * @return the attempt number
	 */
	public Integer getAttemptNumber() {
		return attemptNumber;
	}

	
}
