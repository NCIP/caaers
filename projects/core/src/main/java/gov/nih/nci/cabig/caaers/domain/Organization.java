package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.lang.ComparisonTools;

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
    private List<ReportDefinition> reportDefinitions;

    ////// LOGIC

    /*
     * @See study_details.jsp , study_identifiers.jsp 
     */
    @Transient
    public String getFullName(){
    	return getName() + ( getNciInstituteCode() == null ? "" : " ( " + getNciInstituteCode() + " ) ");
    }
    
	public void addStudyOrganization(StudyOrganization studyOrg) {
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

    public void addReportDefinition(ReportDefinition  reportDefinition){
    	if(reportDefinitions == null) reportDefinitions = new ArrayList<ReportDefinition>();
    	reportDefinitions.add(reportDefinition);
    	reportDefinition.setOrganization(this);
    }

    ////// BEAN PROPERTIES

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    @OrderBy // order by ID for testing consistency
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
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

    @OneToMany(mappedBy="organization", fetch=FetchType.LAZY)
    @Cascade(value={CascadeType.ALL ,CascadeType.DELETE_ORPHAN})
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


	public String getNciInstituteCode() {
		return nciInstituteCode;
	}


	public void setNciInstituteCode(String nciInstituteCode) {
		this.nciInstituteCode = nciInstituteCode;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Organization)) return false;

        Organization organization = (Organization) o;

        if (!ComparisonTools.nullSafeEquals(getName(), organization.getName())) return false;

        return true;
    }

    @Override
	public int hashCode() {
        return (getName() != null ? getName().hashCode() : 0);
    }

    @Override
    public String toString() {
    	return name;
    }
}

