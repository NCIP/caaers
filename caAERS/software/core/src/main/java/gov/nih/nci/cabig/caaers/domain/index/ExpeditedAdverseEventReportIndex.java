package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class ExpeditedAdverseEventReportIndex.
 */
@Entity
@Table(name = "expedited_ae_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_expedited_ae_index_id") })
public class ExpeditedAdverseEventReportIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The expedited adverse event report. */
	private ExpeditedAdverseEventReport expeditedAdverseEventReport;

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
	 * Gets the expedited adverse event report.
	 *
	 * @return the expedited adverse event report
	 */
	@ManyToOne
    @JoinColumn(name = "expedited_ae_id")
	public ExpeditedAdverseEventReport getExpeditedAdverseEventReport() {
		return expeditedAdverseEventReport;
	}
    
	/**
	 * Sets the expedited adverse event report.
	 *
	 * @param expeditedAdverseEventReport the new expedited adverse event report
	 */
	public void setExpeditedAdverseEventReport(ExpeditedAdverseEventReport expeditedAdverseEventReport) {
		this.expeditedAdverseEventReport = expeditedAdverseEventReport;
	}

}