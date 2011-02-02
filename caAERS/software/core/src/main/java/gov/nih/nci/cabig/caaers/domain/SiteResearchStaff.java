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

@Entity
@Table(name = "site_research_staffs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_site_research_staffs_id") })
public class SiteResearchStaff extends AbstractMutableRetireableDomainObject {

	private ResearchStaff researchStaff;
	private Organization organization;
    private List<StudyPersonnel> studyPersonnels;

    private String emailAddress;
    private String phoneNumber;
    private String faxNumber; 
    private Address address;
    private Date startDate;
    private Date endDate;

    @Deprecated
    private List<SiteResearchStaffRole> siteResearchStaffRoles;
    @Deprecated
    private Boolean associateAllStudies;
    
    
    public SiteResearchStaff() {
		super();
		this.setStartDate(DateUtils.today());
	}

	///LOGIC
    public SiteResearchStaffRole findSiteResearchStaffRole(SiteResearchStaffRole other){
    	for(SiteResearchStaffRole role : siteResearchStaffRoles){
    		if(StringUtils.equals(role.getRoleCode(), other.getRoleCode())) return role;
    	}
    	return null;
	}
    
    /**
     * Will return all the SiteResearchStaffRoles  which are currently active. 
     * @return
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
     * @return
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
    
    @OneToMany(mappedBy = "siteResearchStaff", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<SiteResearchStaffRole> getSiteResearchStaffRoles() {
		return siteResearchStaffRoles;
	}
    
	public void setSiteResearchStaffRoles(
			List<SiteResearchStaffRole> siteResearchStaffRoles) {
		this.siteResearchStaffRoles = siteResearchStaffRoles;
	}
	
	/**
	 * Utility method to add SiteResearchRole 
	 * @param siteResearchStaffRole
	 */
	public void addSiteResearchStaffRole(SiteResearchStaffRole siteResearchStaffRole){
		if(getSiteResearchStaffRoles() == null){
			siteResearchStaffRoles = new ArrayList<SiteResearchStaffRole>();
		}
		siteResearchStaffRole.setSiteResearchStaff(this);
		getSiteResearchStaffRoles().add(siteResearchStaffRole);
	}




    @OneToMany(mappedBy = "siteResearchStaff", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<StudyPersonnel> getStudyPersonnels() {
		return studyPersonnels;
	}
	public void setStudyPersonnels(List<StudyPersonnel> studyPersonnels) {
		this.studyPersonnels = studyPersonnels;
	}
	
	/**
	 * Utility method to add StudyPersonnel
	 * @param studyPersonnel
	 */
	public void addStudyPersonnel(StudyPersonnel studyPersonnel){
		if(getStudyPersonnels() == null){
			this.studyPersonnels = new ArrayList<StudyPersonnel>();
		}
		studyPersonnel.setSiteResearchStaff(this);
		getStudyPersonnels().add(studyPersonnel);
	}
    
    @ManyToOne
    @JoinColumn(name = "researchstaff_id")
	public ResearchStaff getResearchStaff() {
		return researchStaff;
	}
	public void setResearchStaff(ResearchStaff researchStaff) {
		this.researchStaff = researchStaff;
	}
	
    @ManyToOne
    @JoinColumn(name = "site_id")
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	@Column(name = "email_address")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "fax_number")
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}	
	
	@Embedded
	public Address getAddress() {
    	if(address == null) address = new Address();
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

    @Override
    public void retire() {
        super.retire();
        //cascade the retrie to Study Personnels associated to this SiteResearchstaff. 
        if(getStudyPersonnels() != null){
            for(StudyPersonnel sp :getStudyPersonnels()) sp.retire();
        }
    }

	@Transient
    public boolean isActive(){
    	return (startDate != null && DateUtils.between(new Date(), startDate, endDate));
    }

    @Transient
    public boolean isInActive(){
    	return (startDate == null || !DateUtils.between(new Date(), startDate, endDate));
    }
    

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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((researchStaff == null) ? 0 : researchStaff.hashCode());
        result = prime * result + ((organization == null) ? 0 : organization.hashCode());
        return result;
    }

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

    @Column(name = "associate_all_studies")
    public Boolean getAssociateAllStudies() {
        return associateAllStudies;
    }

    public void setAssociateAllStudies(Boolean associateAllStudies) {
        this.associateAllStudies = associateAllStudies;
    }

}
