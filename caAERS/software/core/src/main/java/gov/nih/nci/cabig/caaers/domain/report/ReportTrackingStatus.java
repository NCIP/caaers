package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "REPORT_TRACKING_STATUS")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_report_tracking_status_id") })

public class ReportTrackingStatus extends AbstractMutableDomainObject implements Serializable {
	private Boolean status;
	private String statusMessage = "";
	private Date recordedTime;
	//private ReportTracking reportTracking;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getRecordedTime() {
		return recordedTime;
	}
	public void setRecordedTime(Date recordedTime) {
		this.recordedTime = recordedTime;
	}
	
}
