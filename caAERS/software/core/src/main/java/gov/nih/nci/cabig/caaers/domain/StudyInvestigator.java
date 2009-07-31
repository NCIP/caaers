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
 * This class represents the StudyInvestigator domain object associated with the Adverse event
 * report.
 * 
 * @author Kulasekaran
 * @author Biju Joseph
 */
@Entity
@Table(name = "study_investigators")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_investigators_id") })
public class StudyInvestigator extends AbstractMutableRetireableDomainObject implements StudyOrganizationChild {

    private String roleCode;
    private SiteInvestigator siteInvestigator;
    private StudyOrganization studyOrganization;
    
    private Date startDate;
    private Date endDate;
    
    /**
     * This method will deactivate a {@link StudyInvestigator}, by setting the termEndDate to a past date.
     */
    public void deactivate(){
    	this.endDate = DateUtils.yesterday();
    }
    
    /**
     * This method will activate, by setting the termEndDate to a past date.
     */
    public void activate(){
    	this.startDate = DateUtils.yesterday();
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyInvestigator that = (StudyInvestigator) o;
        if(this.isRetired() || that.isRetired()) return false;
        
        if (roleCode != null ? !roleCode.equals(that.roleCode) : that.roleCode != null) return false;
        if (siteInvestigator != null ? !siteInvestigator.equals(that.siteInvestigator) : that.siteInvestigator != null)
            return false;
        if (studyOrganization != null ? !studyOrganization.equals(that.studyOrganization) : that.studyOrganization != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleCode != null ? roleCode.hashCode() : 0;
        result = 31 * result + (siteInvestigator != null ? siteInvestigator.hashCode() : 0);
        result = 31 * result + (studyOrganization != null ? studyOrganization.hashCode() : 0);
        result = 31 * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = 31 * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
    	return String.valueOf(siteInvestigator);
    }
}
