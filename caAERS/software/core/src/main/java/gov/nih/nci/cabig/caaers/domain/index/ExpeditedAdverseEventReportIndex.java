/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "expedited_ae_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_expedited_ae_index_id") })
public class ExpeditedAdverseEventReportIndex extends AbstractMutableDomainObject{
	
	private String loginId;
	private ExpeditedAdverseEventReport expeditedAdverseEventReport;

    private Integer roleCode;

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }
	public String getLoginId() {
		return loginId;
	}

    public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@ManyToOne
    @JoinColumn(name = "expedited_ae_id")
	public ExpeditedAdverseEventReport getExpeditedAdverseEventReport() {
		return expeditedAdverseEventReport;
	}
    
	public void setExpeditedAdverseEventReport(ExpeditedAdverseEventReport expeditedAdverseEventReport) {
		this.expeditedAdverseEventReport = expeditedAdverseEventReport;
	}

}
