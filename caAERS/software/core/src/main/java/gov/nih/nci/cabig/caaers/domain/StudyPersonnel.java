package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the StudyPersonnel domain object associated with the Adverse event report.
 * 
 * @author Kulasekaran
 * @author Biju Joseph
 */
@Entity
@Table(name = "study_personnel")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_personnel_id") })
public class StudyPersonnel extends AbstractMutableRetireableDomainObject implements StudyOrganizationChild {

    private String roleCode;
    private SiteResearchStaff siteResearchStaff;
    private StudyOrganization studyOrganization;
    private Date startDate;
    private Date endDate;
    
    /**
     * This method will deactivate a user, by setting the termEndDate to a past date.
     */
    public void deactivate() {
    	this.endDate = DateUtils.yesterday();
    }
    
    /**
     *  This method will activate a {@link StudyPersonnel}
     */
    public void activate(){
    	this.startDate = DateUtils.yesterday();
    	this.endDate = null;
    }
    
    @ManyToOne
    @JoinColumn(name = "site_research_staffs_id")
	public SiteResearchStaff getSiteResearchStaff() {
		return siteResearchStaff;
	}

	public void setSiteResearchStaff(SiteResearchStaff siteResearchStaff) {
		this.siteResearchStaff = siteResearchStaff;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date")
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date termStartDate) {
		this.startDate = termStartDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date termEndDate) {
		this.endDate = termEndDate;
	}

	@Transient
    public boolean isActive(){
    	return (startDate != null && DateUtils.between(new Date(), startDate, endDate));
    }
    

    @Transient
    public boolean isInActive(){
    	return (startDate == null || !DateUtils.between(new Date(), startDate, endDate));
    }
    

    // /OBJECT METHODS
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((siteResearchStaff == null) ? 0 : siteResearchStaff.hashCode());
        result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((studyOrganization == null) ? 0 : studyOrganization.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final StudyPersonnel other = (StudyPersonnel) obj;
        
        if(this.isRetired() || other.isRetired()) return false;
        if (siteResearchStaff == null) {
            if (other.siteResearchStaff != null) return false;
        } else if (!siteResearchStaff.equals(other.siteResearchStaff)) return false;
        if (roleCode == null) {
            if (other.roleCode != null) return false;
        } else if (!roleCode.equals(other.roleCode)) return false;
        if (studyOrganization == null) {
            if (other.studyOrganization != null) return false;
        } else if (!studyOrganization.equals(other.studyOrganization)) return false;
        return true;
    }
}