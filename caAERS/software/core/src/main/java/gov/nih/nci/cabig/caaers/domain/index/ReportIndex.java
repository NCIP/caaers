package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "report_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_report_index_id") })
public class ReportIndex extends AbstractMutableDomainObject{
	
	private String loginId;
	private Report report;

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
    @JoinColumn(name = "report_id")
	public Report Report() {
		return report;
	}
    
	public void setReport(Report report) {
		this.report = report;
	}

}