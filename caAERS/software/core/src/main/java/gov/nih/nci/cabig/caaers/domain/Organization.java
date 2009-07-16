package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.validation.annotation.UniqueNciIdentifierForOrganization;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
public abstract class Organization extends AbstractMutableDomainObject {
    public static final String DEFAULT_SITE_NAME = "default";

    protected String name;

    protected String nciInstituteCode;

    protected String descriptionText;

    protected List<SiteInvestigator> siteInvestigators = new ArrayList<SiteInvestigator>();
    
    protected List<SiteResearchStaff> siteResearchStaffs = new ArrayList<SiteResearchStaff>();

    protected List<ResearchStaff> researchStaffs = new ArrayList<ResearchStaff>();

    protected List<StudyOrganization> studyOrganizations = new ArrayList<StudyOrganization>();

    protected List<ReportDefinition> reportDefinitions;
    
    protected List<Organization> externalOrganizations;

    protected String city;

    protected String state;

    protected String country;
    
    protected String externalId;
	
    // //// LOGIC

    /*
     * @See study_details.jsp , study_identifiers.jsp
     */
    @Transient
    public String getFullName() {
        return getName()
                        + (getNciInstituteCode() == null ? "" : " ( " + getNciInstituteCode()
                                        + " ) ");
    }

    public void addStudyOrganization(StudyOrganization studyOrg) {
        this.getStudyOrganizations().add(studyOrg);
        studyOrg.setOrganization(this);

    }

    public void addSiteInvestigator(SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setOrganization(this);
    }
    
    
    public void addSiteResearchStaff(SiteResearchStaff siteResearchStaff) {
        getSiteResearchStaffs().add(siteResearchStaff);
        siteResearchStaff.setOrganization(this);
    }


    public void addReportDefinition(ReportDefinition reportDefinition) {
        if (reportDefinitions == null) reportDefinitions = new ArrayList<ReportDefinition>();
        reportDefinitions.add(reportDefinition);
        reportDefinition.setOrganization(this);
    }

    // //// BEAN PROPERTIES
    @Transient
    public String getExternalId() {
		return externalId;
	}
    
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

    @Transient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Transient
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Transient
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Cascade limited to DELETE, Fix for #11452
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    @OrderBy
    // order by ID for testing consistency
    @Cascade(value = { CascadeType.DELETE, CascadeType.DELETE_ORPHAN })
    public List<StudyOrganization> getStudyOrganizations() {
        return studyOrganizations;
    }

    public void setStudyOrganizations(List<StudyOrganization> studyOrganizations) {
        this.studyOrganizations = studyOrganizations;
    }

    public void addStudySite(StudySite studySite) {
        addStudyOrganization(studySite);
    }

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

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.DELETE, CascadeType.DELETE_ORPHAN })
    public List<SiteInvestigator> getSiteInvestigators() {
        return siteInvestigators;
    }

    public void setSiteInvestigators(List<SiteInvestigator> siteInvestigators) {
        this.siteInvestigators = siteInvestigators;
    }
    
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.DELETE, CascadeType.DELETE_ORPHAN })
	public List<SiteResearchStaff> getSiteResearchStaffs() {
		return siteResearchStaffs;
	}

	public void setSiteResearchStaffs(List<SiteResearchStaff> siteResearchStaffs) {
		this.siteResearchStaffs = siteResearchStaffs;
	}
    
    // Cascade limited to DELETE, Fix for #11452
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.DELETE, CascadeType.DELETE_ORPHAN })
    public List<ReportDefinition> getReportDefinitions() {
        return reportDefinitions;
    }

    public void setReportDefinitions(List<ReportDefinition> reportDefinitions) {
        this.reportDefinitions = reportDefinitions;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String description) {
        this.descriptionText = description;
    }

    @UniqueNciIdentifierForOrganization(message = "Nci  Identifier already exits in the datbase...!")
    
    public String getNciInstituteCode() {
        return nciInstituteCode;
    }

    public void setNciInstituteCode(String nciInstituteCode) {
        this.nciInstituteCode = nciInstituteCode;
    }
    
    @Transient
	public List<Organization> getExternalOrganizations() {
		return externalOrganizations;
	}

	public void setExternalOrganizations(List<Organization> externalOrganizations) {
		this.externalOrganizations = externalOrganizations;
	}
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Organization)) return false;
        Organization other = (Organization) o;
        
        //id if same they are same, they are equal
        if(getId() != null && other.getId() != null){
        	if(getId().equals(other.getId())) return true;
        }
        
        //nci code same, they are same, they are equal
        if(getNciInstituteCode() != null && other.getNciInstituteCode() != null){
    	   if(getNciInstituteCode().equals(other.getNciInstituteCode())) return true;
        }
    	   
        
        //otherwise check name
        if (!ComparisonTools.nullSafeEquals(getName(), other.getName())) return false;

        return true;
    }
    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((descriptionText == null) ? 0 : descriptionText.hashCode());
		result = prime * result
				+ ((externalId == null) ? 0 : externalId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((nciInstituteCode == null) ? 0 : nciInstituteCode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}


    @Override
    public String toString() {
        return name;
    }

}
