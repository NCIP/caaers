package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Entity
@Table (name = "organizations")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_organizations_id")
    }
)
public class Organization extends AbstractMutableDomainObject {
    public static final String DEFAULT_SITE_NAME = "default";

    private String name;
    private String nciInstituteCode;
    private String descriptionText;
    private List<SiteInvestigator> siteInvestigators = new ArrayList<SiteInvestigator>();
    private List<ResearchStaff> researchStaffs = new ArrayList<ResearchStaff>();
    private List<StudyOrganization> studyOrganizations = new ArrayList<StudyOrganization>();
    
    
    ////// LOGIC

    public String getDescriptionText() {
		return descriptionText;
	}


	public void setDescriptionText(String description) {
		this.descriptionText = description;
	}


	public String getNciInstituteCode() {
		return nciInstituteCode;
	}


	public void setNciInstituteCode(String nciInstituteCode) {
		this.nciInstituteCode = nciInstituteCode;
	}


	public void addStudySite(StudyOrganization studyOrg) {
    	this.getStudyOrganizations().add(studyOrg);
    	studyOrg.setOrganization(this);
        
    }
    
   
    public void addSiteInvestigator(SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setOrganization(this);
    }
   
    public void addResearchStaff(ResearchStaff staff) {
    	getResearchStaffs().add(staff);
    	staff.setOrganization(this);
    }

    ////// BEAN PROPERTIES

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    @OrderBy // order by ID for testing consistency
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyOrganization> getStudyOrganizations() {
		return studyOrganizations;
	}

	public void setStudyOrganizations(List<StudyOrganization> studyOrganizations) {
		this.studyOrganizations = studyOrganizations;
	}
	
	public void addStudySite(StudySite studySite) {
		studyOrganizations.add(studySite);
		studySite.setOrganization(this);
	}
	
	@Transient
	public List<StudySite> getStudySites() {
		List<StudySite> sites = new ArrayList<StudySite>();
		for(StudyOrganization studyOrg: this.getStudyOrganizations()){
			if (studyOrg instanceof StudySite){
				sites.add((StudySite)studyOrg);
			}
		}
		return sites;
	}


    @OneToMany (mappedBy = "organization", fetch = FetchType.LAZY)    
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<SiteInvestigator> getSiteInvestigators() {
		return siteInvestigators;
	}

	public void setSiteInvestigators(List<SiteInvestigator> siteInvestigators) {
		this.siteInvestigators = siteInvestigators;
	}

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<ResearchStaff> getResearchStaffs() {
		return researchStaffs;
	}

	public void setResearchStaffs(List<ResearchStaff> researchStaffs) {
		this.researchStaffs = researchStaffs;
	}
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization organization = (Organization) o;

        if (name != null ? !name.equals(organization.name) : organization.name != null) return false;

        return true;
    }

    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }
}

