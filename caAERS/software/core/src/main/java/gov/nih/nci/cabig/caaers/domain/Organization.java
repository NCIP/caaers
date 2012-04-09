package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.validation.annotation.UniqueNciIdentifierForOrganization;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.*;

 
/**
 * This class represents the Organization domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "organizations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_organizations_id") })
public abstract class Organization extends AbstractMutableRetireableDomainObject implements Comparable<Organization>{
    
    /** The Constant DEFAULT_SITE_NAME. */
    public static final String DEFAULT_SITE_NAME = "default";

    /** The name. */
    protected String name;

    /** The nci institute code. */
    protected String nciInstituteCode;

    /** The description text. */
    protected String descriptionText;

    /** The site investigators. */
    protected List<SiteInvestigator> siteInvestigators = new ArrayList<SiteInvestigator>();
    
    /** The site research staffs. */
    protected List<SiteResearchStaff> siteResearchStaffs = new ArrayList<SiteResearchStaff>();

    /** The research staffs. */
    protected List<ResearchStaff> researchStaffs = new ArrayList<ResearchStaff>();

    /** The study organizations. */
    protected List<StudyOrganization> studyOrganizations = new ArrayList<StudyOrganization>();

    /** The report definitions. */
    protected List<ReportDefinition> reportDefinitions;
    
    /** The external organizations. */
    protected List<Organization> externalOrganizations;

    /** The city. */
    protected String city;

    /** The state. */
    protected String state;

    /** The country. */
    protected String country;
    
    /** The external id. */
    protected String externalId;
    
   /* *//** The status code. *//*
    protected ActiveInactiveStatus status = ActiveInactiveStatus.AC;*/
    
    /** The merged organization. */
    protected Organization mergedOrganization;
	
    // //// LOGIC

    @ManyToOne
    @JoinColumn(name="merged_org_id", nullable=true)
    public Organization getMergedOrganization() {
		return mergedOrganization;
	}

	public void setMergedOrganization(Organization mergedOrganization) {
		this.mergedOrganization = mergedOrganization;
	}

	/**
     * Gets the status code.
     * 
     * @return the status code
     *//*
    @Enumerated(EnumType.STRING)
    public ActiveInactiveStatus getStatus() {
		return status;
	}

    *//**
	 * Sets the status code.
	 * 
	 * @param statusCode the new status code
	 *//*
	public void setStatus(ActiveInactiveStatus status) {
		this.status = status;
	}
*/
	/*
     * @See study_details.jsp , study_identifiers.jsp
     */
    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public String getFullName() {
        return getName()
                        + (getNciInstituteCode() == null ? "" : " ( " + getNciInstituteCode()
                                        + " ) ");
    }

    /**
     * Adds the study organization.
     *
     * @param studyOrg the study org
     */
    public void addStudyOrganization(StudyOrganization studyOrg) {
        this.getStudyOrganizations().add(studyOrg);
        studyOrg.setOrganization(this);

    }

    /**
     * Adds the site investigator.
     *
     * @param siteInvestigator the site investigator
     */
    public void addSiteInvestigator(SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setOrganization(this);
    }
    
    
    /**
     * Adds the site research staff.
     *
     * @param siteResearchStaff the site research staff
     */
    public void addSiteResearchStaff(SiteResearchStaff siteResearchStaff) {
        getSiteResearchStaffs().add(siteResearchStaff);
        siteResearchStaff.setOrganization(this);
    }


    /**
     * Adds the report definition.
     *
     * @param reportDefinition the report definition
     */
    public void addReportDefinition(ReportDefinition reportDefinition) {
        if (reportDefinitions == null) reportDefinitions = new ArrayList<ReportDefinition>();
        reportDefinitions.add(reportDefinition);
        reportDefinition.setOrganization(this);
    }

    // //// BEAN PROPERTIES
    /**
     * Gets the external id.
     *
     * @return the external id
     */
    @Transient
    public String getExternalId() {
		return externalId;
	}
    
	/**
	 * Sets the external id.
	 *
	 * @param externalId the new external id
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    @Transient
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the country.
     *
     * @return the country
     */
    @Transient
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    @Transient
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    // Cascade limited to DELETE, Fix for #11452
    /**
     * Gets the study organizations.
     *
     * @return the study organizations
     */
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy
    // order by ID for testing consistency
    @Cascade(value = { CascadeType.DELETE  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyOrganization> getStudyOrganizations() {
        return studyOrganizations;
    }

    /**
     * Sets the study organizations.
     *
     * @param studyOrganizations the new study organizations
     */
    public void setStudyOrganizations(List<StudyOrganization> studyOrganizations) {
        this.studyOrganizations = studyOrganizations;
    }

    /**
     * Adds the study site.
     *
     * @param studySite the study site
     */
    public void addStudySite(StudySite studySite) {
        addStudyOrganization(studySite);
    }

    /**
     * Gets the study sites.
     *
     * @return the study sites
     */
    @Transient
    public List<StudySite> getStudySites() {
        List<StudySite> sites = new ArrayList<StudySite>();
        for (StudyOrganization studyOrg : this.getStudyOrganizations()) {
            if (studyOrg instanceof StudySite) {
                sites.add((StudySite) studyOrg);
            }
        }
        return sites;
    }

    /**
     * Gets the site investigators.
     *
     * @return the site investigators
     */
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = { CascadeType.DELETE })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<SiteInvestigator> getSiteInvestigators() {
        return siteInvestigators;
    }

    /**
     * Sets the site investigators.
     *
     * @param siteInvestigators the new site investigators
     */
    public void setSiteInvestigators(List<SiteInvestigator> siteInvestigators) {
        this.siteInvestigators = siteInvestigators;
    }
    
    /**
     * Gets the site research staffs.
     *
     * @return the site research staffs
     */
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = { CascadeType.DELETE  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<SiteResearchStaff> getSiteResearchStaffs() {
		return siteResearchStaffs;
	}

	/**
	 * Sets the site research staffs.
	 *
	 * @param siteResearchStaffs the new site research staffs
	 */
	public void setSiteResearchStaffs(List<SiteResearchStaff> siteResearchStaffs) {
		this.siteResearchStaffs = siteResearchStaffs;
	}
    
    // Cascade limited to DELETE, Fix for #11452
    /**
     * Gets the report definitions.
     *
     * @return the report definitions
     */
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = { CascadeType.DELETE  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportDefinition> getReportDefinitions() {
        return reportDefinitions;
    }

    /**
     * Sets the report definitions.
     *
     * @param reportDefinitions the new report definitions
     */
    public void setReportDefinitions(List<ReportDefinition> reportDefinitions) {
        this.reportDefinitions = reportDefinitions;
    }

    /**
     * Gets the description text.
     *
     * @return the description text
     */
    public String getDescriptionText() {
        return descriptionText;
    }

    /**
     * Sets the description text.
     *
     * @param description the new description text
     */
    public void setDescriptionText(String description) {
        this.descriptionText = description;
    }

    /**
     * Gets the nci institute code.
     *
     * @return the nci institute code
     */
    @UniqueNciIdentifierForOrganization(message = "Nci  Identifier already exits in the datbase...!")
    
    public String getNciInstituteCode() {
        return nciInstituteCode;
    }

    /**
     * Sets the nci institute code.
     *
     * @param nciInstituteCode the new nci institute code
     */
    public void setNciInstituteCode(String nciInstituteCode) {
        this.nciInstituteCode = nciInstituteCode;
    }
    
    /**
     * Gets the external organizations.
     *
     * @return the external organizations
     */
    @Transient
	public List<Organization> getExternalOrganizations() {
		return externalOrganizations;
	}

	/**
	 * Sets the external organizations.
	 *
	 * @param externalOrganizations the new external organizations
	 */
	public void setExternalOrganizations(List<Organization> externalOrganizations) {
		this.externalOrganizations = externalOrganizations;
	}
    

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Organization)) return false;
        Organization other = (Organization) o;
        
        //id if same they are same, they are equal
        if(getId() != null && other.getId() != null){
        	if(getId().equals(other.getId())){
                return true;
            } else {
                return false;
            }
        }
        
        //nci code same, they are same, they are equal
        if(getNciInstituteCode() != null && other.getNciInstituteCode() != null){

    	   if(getNciInstituteCode().equals(other.getNciInstituteCode())){
               return true;
           }else{
               return false;
           }
        }
    	   
        
        //otherwise check name
        if (!ComparisonTools.nullSafeEquals(getName(), other.getName())) return false;

        return true;
    }
    

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nciInstituteCode == null) ? 0 : nciInstituteCode.hashCode());
		return result;
	}


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }
    
    public int compareTo(Organization o) {
    	if( this.getName().compareTo(o.getName()) != 0){
    		return this.getName().compareTo(o.getName());
    	}
    	
    	if( this.getDescriptionText().compareTo(o.getDescriptionText()) != 0){
    		return this.getDescriptionText().compareTo(o.getDescriptionText());
    	}
    	
    	if( this.getRetiredIndicator().compareTo(o.getRetiredIndicator()) != 0){
    		return this.getRetiredIndicator().compareTo(o.getRetiredIndicator());
    	}
    	
    	if( this.getCity().compareTo(o.getCity()) != 0){
    		return this.getCity().compareTo(o.getCity());
    	}
    	
    	if( this.getState().compareTo(o.getState()) != 0){
    		return this.getState().compareTo(o.getState());
    	}
    	
    	if( this.getCountry().compareTo(o.getCountry()) != 0){
    		return this.getCountry().compareTo(o.getCountry());
    	}
    	
    	if( this.getMergedOrganization() == null && o.getMergedOrganization() != null ){
    		return -1;
    	}
    	
    	if( this.getMergedOrganization() !=null && o.getMergedOrganization() != null ){
    		return (this.getMergedOrganization().getNciInstituteCode().compareTo(o.getMergedOrganization().getNciInstituteCode()));
    	}
    	    	
    	return 0;
    }

}
