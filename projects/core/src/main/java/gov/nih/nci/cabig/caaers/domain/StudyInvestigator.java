package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * This class represents the StudyInvestigator domain object associated with the Adverse event
 * report.
 * 
 * @author Kulasekaran
 */
@Entity
@Table(name = "study_investigators")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_investigators_id") })
public class StudyInvestigator extends AbstractMutableDomainObject implements
                StudyOrganizationChild {

    private String roleCode;

    private String statusCode;

    private SiteInvestigator siteInvestigator;

    private StudyOrganization studyOrganization;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "site_investigators_id")
    public SiteInvestigator getSiteInvestigator() {
        return siteInvestigator;
    }

    public void setSiteInvestigator(SiteInvestigator siteInvestigator) {
        this.siteInvestigator = siteInvestigator;
    }

    @ManyToOne(fetch=FetchType.LAZY)
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

    // /OBJECT METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyInvestigator that = (StudyInvestigator) o;

        if (roleCode != null ? !roleCode.equals(that.roleCode) : that.roleCode != null) return false;
        if (siteInvestigator != null ? !siteInvestigator.equals(that.siteInvestigator) : that.siteInvestigator != null)
            return false;
        if (statusCode != null ? !statusCode.equals(that.statusCode) : that.statusCode != null) return false;
        if (studyOrganization != null ? !studyOrganization.equals(that.studyOrganization) : that.studyOrganization != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleCode != null ? roleCode.hashCode() : 0;
        result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
        result = 31 * result + (siteInvestigator != null ? siteInvestigator.hashCode() : 0);
        result = 31 * result + (studyOrganization != null ? studyOrganization.hashCode() : 0);
        return result;
    }
}
