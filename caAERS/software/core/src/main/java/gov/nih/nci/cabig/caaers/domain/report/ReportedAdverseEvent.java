/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
	
	/** The report version. */
	private ReportVersion reportVersion;
	
	/** The adverse event. */
	private AdverseEvent adverseEvent;
	
	//default for hibernate
	/**
	 * Instantiates a new reported adverse event.
	 */
	public ReportedAdverseEvent(){
		this(null, null);
	}
	
	/**
	 * Instantiates a new reported adverse event.
	 *
	 * @param reportVersion the report version
	 * @param adverseEvent the adverse event
	 */
	public ReportedAdverseEvent(ReportVersion reportVersion, AdverseEvent adverseEvent){
		this.reportVersion = reportVersion;
		this.adverseEvent = adverseEvent;
	}
	
	/**
	 * Gets the report version.
	 *
	 * @return the report version
	 */
	@ManyToOne
	@JoinColumn(name = "report_version_id", nullable = false)
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
	 * Gets the adverse event.
	 *
	 * @return the adverse event
	 */
	@OneToOne
	@JoinColumn(name = "adverse_event_id", nullable = false)
	public AdverseEvent getAdverseEvent() {
		return adverseEvent;
	}
	
	/**
	 * Sets the adverse event.
	 *
	 * @param adverseEvent the new adverse event
	 */
	public void setAdverseEvent(AdverseEvent adverseEvent) {
		this.adverseEvent = adverseEvent;
	}
	
	
}
