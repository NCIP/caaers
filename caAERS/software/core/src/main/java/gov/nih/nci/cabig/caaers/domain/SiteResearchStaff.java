package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

 
/**
 * The Class SiteResearchStaff.
 */
@Entity
@Table(name = "site_research_staffs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_site_research_staffs_id") })
public class SiteResearchStaff extends AbstractMutableRetireableDomainObject {

	/** The research staff. */
	private ResearchStaff researchStaff;
	
	/** The organization. */
	private Organization organization;
    
    /** The study personnels. */
    private List<StudyPersonnel> studyPersonnels;

    /** The email address. */
    private String emailAddress;
    
    /** The phone number. */
    private String phoneNumber;
    
    /** The fax number. */
    private String faxNumber; 
    
    /** The address. */
    private Address address;
    
    /** The start date. */
    private Date startDate;
    
    /** The end date. */
    private Date endDate;

    /** The site research staff roles. */
    @Deprecated
    private List<SiteResearchStaffRole> siteResearchStaffRoles;
    
    /** The associate all studies. */
    @Deprecated
    private Boolean associateAllStudies;
    
    
    /**
     * Instantiates a new site research staff.
     */
    public SiteResearchStaff() {
		super();
		this.setStartDate(DateUtils.today());
	}

	///LOGIC
    /**
	 * Find site research staff role.
	 *
	 * @param other the other
	 * @return the site research staff role
	 */
	public SiteResearchStaffRole findSiteResearchStaffRole(SiteResearchStaffRole other){
    	for(SiteResearchStaffRole role : siteResearchStaffRoles){
    		if(StringUtils.equals(role.getRoleCode(), other.getRoleCode())) return role;
    	}
    	return null;
	}
    
    /**
     * Will return all the SiteResearchStaffRoles  which are currently active.
     *
     * @return the active site research staff roles
     */
    @Transient
    public List<SiteResearchStaffRole> getActiveSiteResearchStaffRoles(){
        List<SiteResearchStaffRole> srsRoleList = new ArrayList<SiteResearchStaffRole>();
        List<SiteResearchStaffRole> roles = getSiteResearchStaffRoles();
        if (roles == null) return  srsRoleList;
        for(SiteResearchStaffRole  srsRole : roles){
            if(srsRole.isActive()) srsRoleList.add(srsRole);
        }
        return srsRoleList;
    }
    
    /**
     * Will return all the SiteResearchStaffRoles  which are currently Inactive.
     *
     * @return the in active site research staff roles
     */
    @Transient
    public List<SiteResearchStaffRole> getInActiveSiteResearchStaffRoles(){
        List<SiteResearchStaffRole> srsRoleList = new ArrayList<SiteResearchStaffRole>();
        List<SiteResearchStaffRole> roles = getSiteResearchStaffRoles();
        if (roles == null) return  srsRoleList;
        for(SiteResearchStaffRole  srsRole : roles){
            if(srsRole.isInActive()) srsRoleList.add(srsRole);
        }
        return srsRoleList;
    }
    
    /**
     * Gets the site research staff roles.
     *
     * @return the site research staff roles
     */
    @OneToMany(mappedBy = "siteResearchStaff", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = { CascadeType.ALL  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<SiteResearchStaffRole> getSiteResearchStaffRoles() {
		return siteResearchStaffRoles;
	}
    
	/**
	 * Sets the site research staff roles.
	 *
	 * @param siteResearchStaffRoles the new site research staff roles
	 */
	public void setSiteResearchStaffRoles(
			List<SiteResearchStaffRole> siteResearchStaffRoles) {
		this.siteResearchStaffRoles = siteResearchStaffRoles;
	}
	
	/**
	 * Utility method to add SiteResearchRole.
	 *
	 * @param siteResearchStaffRole the site research staff role
	 */
	public void addSiteResearchStaffRole(SiteResearchStaffRole siteResearchStaffRole){
		if(getSiteResearchStaffRoles() == null){
			siteResearchStaffRoles = new ArrayList<SiteResearchStaffRole>();
		}
		siteResearchStaffRole.setSiteResearchStaff(this);
		getSiteResearchStaffRoles().add(siteResearchStaffRole);
	}




    /**
     * Gets the study personnels.
     *
     * @return the study personnels
     */
    @OneToMany(mappedBy = "siteResearchStaff", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = { CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<StudyPersonnel> getStudyPersonnels() {
		return studyPersonnels;
	}
	
	/**
	 * Sets the study personnels.
	 *
	 * @param studyPersonnels the new study personnels
	 */
	public void setStudyPersonnels(List<StudyPersonnel> studyPersonnels) {
		this.studyPersonnels = studyPersonnels;
	}
	
	/**
	 * Utility method to add StudyPersonnel.
	 *
	 * @param studyPersonnel the study personnel
	 */
	public void addStudyPersonnel(StudyPersonnel studyPersonnel){
		if(getStudyPersonnels() == null){
			this.studyPersonnels = new ArrayList<StudyPersonnel>();
		}
		studyPersonnel.setSiteResearchStaff(this);
		getStudyPersonnels().add(studyPersonnel);
	}
    
    /**
     * Gets the research staff.
     *
     * @return the research staff
     */
    @ManyToOne
    @JoinColumn(name = "researchstaff_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}
	
	/**
	 * Sets the research staff.
	 *
	 * @param researchStaff the new research staff
	 */
	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}
	
    /**
     * Gets the organization.
     *
     * @return the organization
     */
    @ManyToOne
    @JoinColumn(name = "site_id")
	public Organization getOrganization() {
		return organization;
	}
	
	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	@Column(name = "email_address")
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the fax number.
	 *
	 * @return the fax number
	 */
	@Column(name = "fax_number")
	public String getFaxNumber() {
		return faxNumber;
	}
	
	/**
	 * Sets the fax number.
	 *
	 * @param faxNumber the new fax number
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
    /**
     * Gets the end date.
     *
     * @return the end date
     */
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}	
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	@Embedded
	public Address getAddress() {
    	if(address == null) address = new Address();
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractMutableRetireableDomainObject#retire()
     */
    @Override
    public void retire() {
        super.retire();
        //cascade the retrie to Study Personnels associated to this SiteResearchstaff. 
        if(getStudyPersonnels() != null){
            for(StudyPersonnel sp :getStudyPersonnels()) sp.retire();
        }
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
     * Gets the active date.
     *
     * @return the active date
     */
    @Transient
    public Date getActiveDate() {
        SortedSet<Date> dates = new TreeSet<Date>();
        if (this.getSiteResearchStaffRoles() == null) return new Date(System.currentTimeMillis());
        
        for (SiteResearchStaffRole srsr : this.getSiteResearchStaffRoles()) {
            if (srsr.getStartDate() == null) srsr.setStartDate(new Date(System.currentTimeMillis()));
            dates.add(srsr.getStartDate());
        }

        if (dates.size() > 0) return dates.first(); else return null;
    }


    /**
     * Will return true if the SiteResearchStaff  is having an active role identified by the roleCodes.
     * @param roleCodes  - A list of role codes
     * @return true, if the SiteResearchStaff is active on any of the roleCodes. Otherwise false. 
     */
    public boolean hasActiveRolesOfType(String... roleCodes){
        for(SiteResearchStaffRole srr : getSiteResearchStaffRoles()){
            if(srr.isActive()){
               for(String roleCode : roleCodes){
                   if(srr.getRoleCode().equals(roleCode)) return true;
               }
            }
        }
        return false;
    }


    /**
     * Sync.
     *
     * @param srs the srs
     */
    public void sync(SiteResearchStaff srs){
        if(srs == null){
            retire();
        }else{
            setEmailAddress(srs.getEmailAddress());
            setPhoneNumber(srs.getPhoneNumber());
            setFaxNumber(srs.getFaxNumber());
            if(getAddress() == null) {
                setAddress(srs.getAddress());
            } else {
                getAddress().sync(srs.getAddress());
            }

        }

    }
    
    
    // /OBJECT METHODS
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((researchStaff == null) ? 0 : researchStaff.hashCode());
        result = prime * result + ((organization == null) ? 0 : organization.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final SiteResearchStaff other = (SiteResearchStaff) obj;
        if (researchStaff == null) {
            if (other.researchStaff != null) return false;
        } else if (!researchStaff.equals(other.researchStaff)) return false;
        if (organization == null) {
            if (other.organization != null) return false;
        } else if (!organization.equals(other.organization)) return false;
        return true;
    }

    /**
     * Gets the associate all studies.
     *
     * @return the associate all studies
     */
    @Column(name = "associate_all_studies")
    public Boolean getAssociateAllStudies() {
        return associateAllStudies;
    }

    /**
     * Sets the associate all studies.
     *
     * @param associateAllStudies the new associate all studies
     */
    public void setAssociateAllStudies(Boolean associateAllStudies) {
        this.associateAllStudies = associateAllStudies;
    }

}
