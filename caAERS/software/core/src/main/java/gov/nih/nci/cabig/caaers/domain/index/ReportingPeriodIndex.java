/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class ReportingPeriodIndex.
 */
@Entity
@Table(name = "reportingperiod_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_reportingperiod_index_id") })
public class ReportingPeriodIndex extends AbstractMutableDomainObject{
	
	/** The login id. */
	private String loginId;
	
	/** The reporting period. */
	private AdverseEventReportingPeriod reportingPeriod;

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
	 * Gets the reporting period.
	 *
	 * @return the reporting period
	 */
	@ManyToOne
    @JoinColumn(name = "reportingperiod_id")
	public AdverseEventReportingPeriod getReportingPeriod() {
		return reportingPeriod;
	}
	
	/**
	 * Sets the reporting period.
	 *
	 * @param reportingPeriod the new reporting period
	 */
	public void setReportingPeriod(AdverseEventReportingPeriod reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

}
