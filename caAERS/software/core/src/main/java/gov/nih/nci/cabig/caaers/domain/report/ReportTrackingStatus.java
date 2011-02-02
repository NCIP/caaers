package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class ReportTrackingStatus.
 */
@Entity
@Table(name = "REPORT_TRACKING_STATUS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_tracking_status_id") })

public class ReportTrackingStatus extends AbstractMutableDomainObject implements Serializable {
	
	/** The status. */
	private Boolean status;
	
	/** The status message. */
	private String statusMessage = "";
	
	/** The recorded time. */
	private Date recordedTime;
	//private ReportTracking reportTracking;
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	/**
	 * Gets the status message.
	 *
	 * @return the status message
	 */
	public String getStatusMessage() {
		return statusMessage;
	}
	
	/**
	 * Sets the status message.
	 *
	 * @param statusMessage the new status message
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Gets the recorded time.
	 *
	 * @return the recorded time
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getRecordedTime() {
		return recordedTime;
	}
	
	/**
	 * Sets the recorded time.
	 *
	 * @param recordedTime the new recorded time
	 */
	public void setRecordedTime(Date recordedTime) {
		this.recordedTime = recordedTime;
	}
	
}
