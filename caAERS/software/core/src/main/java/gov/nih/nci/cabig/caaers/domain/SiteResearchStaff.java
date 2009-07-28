package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "site_research_staffs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_site_research_staffs_id") })
public class SiteResearchStaff extends AbstractMutableDomainObject{

	private ResearchStaff researchStaff;
	private Organization organization;
    private List<StudyPersonnel> studyPersonnels;
    private List<SiteResearchStaffRole> siteResearchStaffRoles;
    private String emailAddress;
    private String phoneNumber;
    private String faxNumber; 
    private Address address;
    private Boolean associateAllStudies;
    
    
    ///LOGIC
    public SiteResearchStaffRole findSiteResearchStaffRole(SiteResearchStaffRole other){
    	for(SiteResearchStaffRole role : siteResearchStaffRoles){
    		if(StringUtils.equals(role.getRoleCode(), other.getRoleCode())) return role;
    	}
    	return null;
	}
    
    @OneToMany(mappedBy = "siteResearchStaff", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
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
	
	@Embedded
	public Address getAddress() {
    	if(address == null) address = new Address();
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Transient
    public boolean isActive(){
    	//return (startDate != null && DateUtils.between(new Date(), startDate, endDate));
		return true;
    }

    @Transient
    public boolean isInActive(){
    	//return (startDate == null || !DateUtils.between(new Date(), startDate, endDate));
    	return false;
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
}
