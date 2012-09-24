package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;


/**
* This class represents the Reconciliation Adverse Event Reporting Period domain object associated with 
* the Reconciliation Adverse event report.
*
* @author Ramakrishna
*
*/

@Entity
@Table(name = "reconciled_adverse_events")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_reconciled_adverse_even_id")})
public class ReconciledAdverseEvent extends AbstractMutableDomainObject{
	
	/** The reconciliation report adverseEvent reporting period. */
	private ReconciliationReport reconciliationReport;

    /** The system on which the reconciliation action should be taken */
    private ReconciliationSystem system;

    /** Action to be taken for reconciliation on the adverse event. */
    private ReconciliationAction action;

	private Integer itemId;
	private Grade grade;
	private Date startDate;
	private Date endDate;
	private String verbatim;
	private String whySerious;
    private Attribution attribution;
    private String externalId;
    private String termCode;
    private String termName;
    private String termOtherSpecify;
    private String errorMessage;
    


	@ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
	public ReconciliationReport getReconciliationReport() {
		return reconciliationReport;
	}

	public void setReconciliationReport(ReconciliationReport reconciliationReport) {
		this.reconciliationReport = reconciliationReport;
	}

    @Type(type = "grade")
    @Column(name = "grade_code")
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

    @Type(type = "attribution")
    @Column(name = "attribution_summary_code")
	public Attribution getAttribution() {
		return attribution;
	}

	public void setAttribution(Attribution attribution) {
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermOtherSpecify() {
        return termOtherSpecify;
    }

    public void setTermOtherSpecify(String termOtherSpecify) {
        this.termOtherSpecify = termOtherSpecify;
    }
}
