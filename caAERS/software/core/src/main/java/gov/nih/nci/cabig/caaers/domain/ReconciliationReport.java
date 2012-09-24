package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
* This class represents the Reconciliation Report Adverse Event domain object.
*
* @author Ramakrishna
*
*/

@Entity
@Table(name = "recon_rprt_rep_periods")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_recon_rprt_rep_periods_id")})
public class ReconciliationReport extends AbstractMutableDomainObject{
	
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	
	private List<ReconciledAdverseEvent> reconciliationReportAdverseEvents = new ArrayList<ReconciledAdverseEvent>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="reporting_period_id")
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod() {
		return adverseEventReportingPeriod;
	}

	public void setAdverseEventReportingPeriod(
			AdverseEventReportingPeriod adverseEventReportingPeriod) {
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}

	@OneToMany(mappedBy = "reconciliationReportReportingPeriod", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<ReconciledAdverseEvent> getReconciliationReportAdverseEvents() {
		return reconciliationReportAdverseEvents;
	}

	public void setReconciliationReportAdverseEvents(
			List<ReconciledAdverseEvent> reconciliationReportAdverseEvents) {
		this.reconciliationReportAdverseEvents = reconciliationReportAdverseEvents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	/** Created date of the reconciliation report. */
	private Date createdDate = new Date();
	
	/** The date the reconciliation report is updated. */
	private Date updatedDate;
	
	/** The user who reviewed the reconciliation report. */
	private String reviewedBy;

}
