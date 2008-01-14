package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * @author Kulasekaran
 */
@Entity
@Table (name="study_investigators")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_study_investigators_id")
    }
)
public class StudyInvestigator extends AbstractMutableDomainObject implements StudyOrganizationChild{

	private String roleCode;
	private String statusCode;
	private SiteInvestigator siteInvestigator;
	private StudyOrganization studyOrganization;

	@ManyToOne
    @JoinColumn(name = "site_investigators_id")
	public SiteInvestigator getSiteInvestigator() {
		return siteInvestigator;
	}

	public void setSiteInvestigator(SiteInvestigator siteInvestigator) {
		this.siteInvestigator = siteInvestigator;
	}

	@ManyToOne
    @JoinColumn(name = "study_sites_id")
	public StudyOrganization getStudyOrganization() {
		return studyOrganization;
	}

	public void setStudyOrganization(StudyOrganization studyOrganization) {
		this.studyOrganization = studyOrganization;
	}

	@Column(name = "role_code")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "status_code")
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	///OBJECT METHODS
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roleCode == null) ? 0 : roleCode.hashCode());
		result = prime
				* result
				+ ((siteInvestigator == null) ? 0 : siteInvestigator.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final StudyInvestigator other = (StudyInvestigator) obj;
		if (roleCode == null) {
			if (other.roleCode != null)
				return false;
		} else if (!roleCode.equals(other.roleCode))
			return false;
		if (siteInvestigator == null) {
			if (other.siteInvestigator != null)
				return false;
		} else if (!siteInvestigator.equals(other.siteInvestigator))
			return false;
		
		return true;
	}

	
	
	
}
