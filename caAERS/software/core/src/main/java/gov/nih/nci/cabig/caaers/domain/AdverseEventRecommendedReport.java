package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name = "ae_recom_reports")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_recom_reports_id")})
public class AdverseEventRecommendedReport extends AbstractMutableDomainObject{
	
	private AdverseEvent adverseEvent;
	
	private Boolean aeReported;
	
	private Date dueDate;
	
	private Date createdDate = new Date();

	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Boolean getAeReported() {
		return aeReported;
	}


	public void setAeReported(Boolean aeReported) {
		this.aeReported = aeReported;
	}


	@ManyToOne
	@JoinColumn(name = "adverse_event_id",nullable = false)
	public AdverseEvent getAdverseEvent() {
		return adverseEvent;
	}


	public void setAdverseEvent(AdverseEvent adverseEvent) {
		this.adverseEvent = adverseEvent;
	}

	@ManyToOne
	@JoinColumn(name = "report_definition_id",nullable = false)
	public ReportDefinition getReportDefinition() {
		return reportDefinition;
	}


	public void setReportDefinition(ReportDefinition reportDefinition) {
		this.reportDefinition = reportDefinition;
	}


	private ReportDefinition reportDefinition;
}
