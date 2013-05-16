/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	private int role;
	
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
