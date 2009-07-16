package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This concept relates the adverse events, that got reported in a Report.
 * @author Biju Joseph
 *
 */
@Entity
@Table(name = "reported_adverse_events")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_reported_adverse_events_id") })
public class ReportedAdverseEvent extends AbstractMutableDomainObject {
	
	private ReportVersion reportVersion;
	private AdverseEvent adverseEvent;
	
	//default for hibernate
	public ReportedAdverseEvent(){
		this(null, null);
	}
	
	public ReportedAdverseEvent(ReportVersion reportVersion, AdverseEvent adverseEvent){
		this.reportVersion = reportVersion;
		this.adverseEvent = adverseEvent;
	}
	
	@ManyToOne
	@JoinColumn(name = "report_version_id", nullable = false)
	public ReportVersion getReportVersion() {
		return reportVersion;
	}
	
	public void setReportVersion(ReportVersion reportVersion) {
		this.reportVersion = reportVersion;
	}
	
	@OneToOne
	@JoinColumn(name = "adverse_event_id", nullable = false)
	public AdverseEvent getAdverseEvent() {
		return adverseEvent;
	}
	public void setAdverseEvent(AdverseEvent adverseEvent) {
		this.adverseEvent = adverseEvent;
	}
	
	
}
