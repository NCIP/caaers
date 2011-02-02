package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class ReportIndex.
 */
@Entity
@Table(name = "report_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_report_index_id") })
public class ReportIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The report. */
	private Report report;

    /** The role code. */
    private Integer roleCode;

    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public Integer getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the role code.
     *
     * @param roleCode the new role code
     */
    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }
	
	/**
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLoginId() {
		return loginId;
	}

    /**
     * Sets the login id.
     *
     * @param loginId the new login id
     */
    public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	@ManyToOne
    @JoinColumn(name = "report_id")
	public Report getReport() {
		return report;
	}
    
	/**
	 * Sets the report.
	 *
	 * @param report the new report
	 */
	public void setReport(Report report) {
		this.report = report;
	}

}