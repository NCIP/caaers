/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    /** The role code. */
    private String roleCode;
    
    /** The site investigator. */
    private SiteInvestigator siteInvestigator;
    
    /** The study organization. */
    private StudyOrganization studyOrganization;
    
    /** The start date. */
    private Date startDate;
    
    /** The end date. */
    private Date endDate;
    
    /**
     * This method will deactivate a {@link StudyInvestigator}, by setting the EndDate to a past date.
     */
    public void deactivate(){
    	this.endDate = DateUtils.yesterday();
    }
    
    /**
     * This method will activate, by setting the termEndDate to a past date.
     */
    public void activate(){
        if (getSiteInvestigator().getStartDate().compareTo(DateUtils.today()) > 0) {
            this.setStartDate(getSiteInvestigator().getStartDate());
        } else {
            this.setStartDate(DateUtils.today());
        }
        this.setEndDate(getSiteInvestigator().getEndDate());
        this.endDate = null;
    }
    
    /**
     * This method will take care of synchronizing the start and end dates.
     *  By copying it from {@link SiteInvestigator}
     */
    public void syncDates(){
    	if(siteInvestigator == null) return;
    	
    	startDate = siteInvestigator.getStartDate();
    	endDate = siteInvestigator.getEndDate();
    }
    

    /**
     * Gets the site investigator.
     *
     * @return the site investigator
     */
    @ManyToOne
    @JoinColumn(name = "site_investigators_id")
    public SiteInvestigator getSiteInvestigator() {
        return siteInvestigator;
    }

    /**
     * Sets the site investigator.
     *
     * @param siteInvestigator the new site investigator
     */
    public void setSiteInvestigator(SiteInvestigator siteInvestigator) {
        this.siteInvestigator = siteInvestigator;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyOrganizationChild#getStudyOrganization()
     */
    @ManyToOne
    @JoinColumn(name = "study_sites_id")
    public StudyOrganization getStudyOrganization() {
        return studyOrganization;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyOrganizationChild#setStudyOrganization(gov.nih.nci.cabig.caaers.domain.StudyOrganization)
     */
    public void setStudyOrganization(StudyOrganization studyOrganization) {
        this.studyOrganization = studyOrganization;
    }

    /**
     * Gets the role code.
     *
     * @return the role code
     */
    @Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * Sets the role code.
     *
     * @param roleCode the new role code
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date")
    public Date getStartDate() {
		return startDate;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.StudyOrganizationChild#setStartDate(java.util.Date)
	 */
	public void setStartDate(Date termStartDate) {
		this.startDate = termStartDate;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.StudyOrganizationChild#setEndDate(java.util.Date)
	 */
	public void setEndDate(Date termEndDate) {
		this.endDate = termEndDate;
	}

    
    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    @Transient
    public boolean isActive(){
    	return (startDate != null && DateUtils.between(new Date(), startDate, endDate));
    }
    
   
    /**
     * Checks if is in active.
     *
     * @return true, if is in active
     */
    @Transient
    public boolean isInActive(){
    	return (startDate == null || !DateUtils.between(new Date(), startDate, endDate));
    }
    
    /**
     * Returns the email address associtated to {@link SiteResearchStaff}.
     * If email addres not found in {@link SiteResearchStaff}, {@link ResearchStaff}'s one is returned.
     *
     * @return the email address
     */
    @Transient
    public String getEmailAddress(){
    	String email = null;
    	if(getSiteInvestigator() != null){
    		email = siteInvestigator.getEmailAddress();
    		if(email == null) {
    			email = siteInvestigator.getInvestigator().getEmailAddress();
    		}
    	}
    	return email;
    }

    // /OBJECT METHODS

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        StudyInvestigator that = (StudyInvestigator) o;
        if(this.isRetired() || that.isRetired()) return false;

        if (roleCode != null ? !roleCode.equals(that.roleCode) : that.roleCode != null) return false;
        if (siteInvestigator != null ? !siteInvestigator.equals(that.siteInvestigator) : that.siteInvestigator != null) return false;
        if (studyOrganization != null ? !studyOrganization.equals(that.studyOrganization) : that.studyOrganization != null) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((siteInvestigator == null) ? 0 : siteInvestigator.hashCode());
        result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
        result = prime * result + ((studyOrganization == null) ? 0 : studyOrganization.hashCode());
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return String.valueOf(siteInvestigator);
    }
}
