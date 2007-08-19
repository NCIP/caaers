package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
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
 * A report sending schedule for an adverse event.
 * The RuleExecutionService, evaluates pre-defined set of rules over the attributes of an AdverseEvent,
 * and creates a Report.
 *
 * @author Biju Joseph
 *
 */
@Entity
@Table(name = "REPORT_SCHEDULES")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_report_schedules_id")
    }
)
public class Report extends AbstractMutableDomainObject implements Serializable {
    private boolean required;

    private ExpeditedAdverseEventReport aeReport;

    private ReportDefinition reportDefinition;

    private List<ScheduledNotification> notifications;

    private Date createdOn;
    private Date dueOn;
    private Date submittedOn;

    private ReportStatus status = ReportStatus.PENDING;

	private List<ReportDelivery> deliveries;

    ////// LOGIC

    public void addScheduledNotification(ScheduledNotification nf) {
        if (notifications == null) notifications = new ArrayList<ScheduledNotification>();
        notifications.add(nf);
    }

	public void addReportDelivery(ReportDelivery rd){
		if(this.deliveries == null) deliveries = new ArrayList<ReportDelivery>();
		deliveries.add(rd);
		rd.setReport(this);
	}

	public boolean hasScheduledNotifications(){
		return (notifications != null) &&	(!notifications.isEmpty());
	}
    ////// BEAN PROPERTIES
	@Temporal(value=TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rct_id")
    @Cascade({ CascadeType.LOCK})
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

    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getDueOn() {
        return dueOn;
    }

    public void setDueOn(Date dueOn) {
        this.dueOn = dueOn;
    }

    @Temporal(value=TemporalType.TIMESTAMP)
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

    @OneToMany(fetch=FetchType.LAZY,mappedBy="report")
	@Cascade(value = { CascadeType.ALL , CascadeType.DELETE_ORPHAN})
	public List<ReportDelivery> getReportDeliveries() {
		return deliveries;
	}

	public void setReportDeliveries(List<ReportDelivery> deliveries) {
		this.deliveries = deliveries;
	}


	////// OBJECT METHODS

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report [").append("id : ").append(getId())
            .append(", createdOn :").append(String.valueOf(createdOn))
            .append(", submittedOn :").append(String.valueOf(submittedOn))
            .append(", dueOn :").append(String.valueOf(dueOn));
        sb.append("\r\n notifications :");
        if(notifications != null){
        	for (ScheduledNotification sn : notifications) {
        		sb.append("\r\n").append(String.valueOf(sn));
        	}
        }
        sb.append("]");
        return sb.toString();
    }
}

