package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the ResearchStaff domain object associated with the Adverse event report.
 * 
 * @author Priyatam
 * @author Kulasekaran
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "research_staffs")
@DiscriminatorColumn(name="type")
@GenericGenerator(name = "id-generator", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "seq_users_id") })
public abstract class ResearchStaff extends User {
	
	protected Integer id;
	
    protected String nciIdentifier;

    protected List<StudyPersonnel> studyPersonnels = new ArrayList<StudyPersonnel>();
    	
    protected List<ResearchStaff> externalResearchStaff = new ArrayList<ResearchStaff>();

    protected Organization organization;
    
    protected String externalId;
    
    protected String status;
    
  @Id 
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id-generator")
  public Integer getId() {
      return id;
  }

  public void setId(Integer id) {
      this.id = id;
  }

    // LOGIC
    @Transient
    public boolean isAssociatedToUserGroup(UserGroupType groupType) {
        return getUserGroupTypes().contains(groupType);
    }

    public void addStudyPersonnel(final StudyPersonnel studyPersonnel) {
        getStudyPersonnels().add(studyPersonnel);
    }

    // bean methods

    @OneToMany(mappedBy = "researchStaff", fetch = FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyPersonnel> getStudyPersonnels() {
        return studyPersonnels;
    }

    public void setStudyPersonnels(final List<StudyPersonnel> studyPersonnels) {
        this.studyPersonnels = studyPersonnels;
    }

    @ManyToOne
    @JoinColumn(name = "site_id")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ResearchStaff that = (ResearchStaff) o;

        if (emailAddress != null ? !emailAddress.equals(that.emailAddress)
                        : that.emailAddress != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }
    
    @Transient
    public String getNciIdentifier() {
        return nciIdentifier;
    }

    public void setNciIdentifier(final String nciIdentifier) {
        this.nciIdentifier = nciIdentifier;
    }
    
    @Transient
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
	@Transient
	public List<ResearchStaff> getExternalResearchStaff() {
		return externalResearchStaff;
	}

	public void setExternalResearchStaff(List<ResearchStaff> externalResearchStaff) {
		this.externalResearchStaff = externalResearchStaff;
	}

}
