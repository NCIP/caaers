package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
* This class represents the Reconciliation Adverse Event Reporting Period domain object associated with 
* the Reconciliation Adverse event report.
*
* @author Ramakrishna
*
*/

@Entity
@Table(name = "recon_rpt_adv_events")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_recon_rpt_adv_events_id")})
public class ReconciledAdverseEvent extends AbstractMutableDomainObject{
	
	/** The reconciliation report adverseEvent reporting period. */
	private ReconciliationReport reconciliationReportReportingPeriod;
	
	private Integer itemId;
	
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	private Grade grade;
	
	private Date startDate;
	
	private Date endDate;
	
	private String verbatim;
	
	private String whySerious;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String attribution;
	
	private String errorMessage;
	
	@ManyToOne
    @JoinColumn(name = "recon_rep_prd_id", nullable = true)
    @Cascade(value = {CascadeType.LOCK, CascadeType.DETACH})
	public ReconciliationReport getReconciliationReportReportingPeriod() {
		return reconciliationReportReportingPeriod;
	}

	public void setReconciliationReportReportingPeriod(
			ReconciliationReport reconciliationReportReportingPeriod) {
		this.reconciliationReportReportingPeriod = reconciliationReportReportingPeriod;
	}

	@Enumerated(EnumType.ORDINAL)
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getVerbatim() {
		return verbatim;
	}

	public void setVerbatim(String verbatim) {
		this.verbatim = verbatim;
	}

	public String getWhySerious() {
		return whySerious;
	}

	public void setWhySerious(String whySerious) {
		this.whySerious = whySerious;
	}

	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}

	@Enumerated(EnumType.ORDINAL)
	public ReconciliationSystem getSystem() {
		return system;
	}

	public void setSystem(ReconciliationSystem system) {
		this.system = system;
	}

	public ReconciliationAction getAction() {
		return action;
	}

	public void setAction(ReconciliationAction action) {
		this.action = action;
	}

	/** The system on which the reconciliation action should be taken */
	private ReconciliationSystem system; 
	
	/** Action to be taken for reconciliation on the adverse event. */
	private ReconciliationAction action;

}
