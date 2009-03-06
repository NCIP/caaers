package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the StudyPersonnel domain object associated with the Adverse event report.
 * 
 * @author Kulasekaran
 */
@Entity
@Table(name = "study_personnel")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_personnel_id") })
public class StudyPersonnel extends AbstractMutableDomainObject implements StudyOrganizationChild {

    private String roleCode;

    private String statusCode;

    private ResearchStaff researchStaff;

    private StudyOrganization studyOrganization;

    @ManyToOne
    @JoinColumn(name = "research_staffs_id")
    public ResearchStaff getResearchStaff() {
        return researchStaff;
    }

    public void setResearchStaff(ResearchStaff researchStaff) {
        this.researchStaff = researchStaff;
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

    // /OBJECT METHODS
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((researchStaff == null) ? 0 : researchStaff.hashCode());
        result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
        result = prime * result + ((studyOrganization == null) ? 0 : studyOrganization.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final StudyPersonnel other = (StudyPersonnel) obj;
        if (researchStaff == null) {
            if (other.researchStaff != null) return false;
        } else if (!researchStaff.equals(other.researchStaff)) return false;
        if (roleCode == null) {
            if (other.roleCode != null) return false;
        } else if (!roleCode.equals(other.roleCode)) return false;
        if (studyOrganization == null) {
            if (other.studyOrganization != null) return false;
        } else if (!studyOrganization.equals(other.studyOrganization)) return false;
        return true;
    }

}
