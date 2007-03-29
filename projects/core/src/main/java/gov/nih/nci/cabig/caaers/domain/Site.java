package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OrderBy;

/**
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Entity
@Table (name = "sites")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_sites_id")
    }
)
public class Site extends AbstractDomainObject {
    public static final String DEFAULT_SITE_NAME = "default";

    private String name;
    private List<StudySite> studySites = new ArrayList<StudySite>();
    private List<SiteInvestigator> siteInvestigators = new ArrayList<SiteInvestigator>();
    
    
    ////// LOGIC

    public void addStudySite(StudySite studySite) {
        getStudySites().add(studySite);
        studySite.setSite(this);
    }
    
   
    public void addSiteInvestigator(SiteInvestigator siteInvestigator) {
        getSiteInvestigators().add(siteInvestigator);
        siteInvestigator.setSite(this);
    }
   

    ////// BEAN PROPERTIES

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudySites(List<StudySite> studySites) {
        this.studySites = studySites;
    }
    
    @OneToMany (mappedBy = "site",fetch = FetchType.EAGER)
    @OrderBy // order by ID for testing consistency
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudySite> getStudySites() {
        return studySites;
    }

    @OneToMany (mappedBy = "site", fetch = FetchType.LAZY)    
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<SiteInvestigator> getSiteInvestigators() {
		return siteInvestigators;
	}

	public void setSiteInvestigators(List<SiteInvestigator> siteInvestigators) {
		this.siteInvestigators = siteInvestigators;
	}

    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Site site = (Site) o;

        if (name != null ? !name.equals(site.name) : site.name != null) return false;

        return true;
    }

    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }
}

