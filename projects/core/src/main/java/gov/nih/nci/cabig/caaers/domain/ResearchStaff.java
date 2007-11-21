package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Priyatam
 * @author Kulasekaran
 */
@Entity
@Table(name = "research_staffs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_research_staffs_id") })
public class ResearchStaff extends User {


	private String nciIdentifier;

	private List<StudyPersonnel> studyPersonnels = new ArrayList<StudyPersonnel>();

	private Organization organization;

	public void addStudyPersonnel(final StudyPersonnel studyPersonnel) {
		getStudyPersonnels().add(studyPersonnel);
	}

	/*
	 * // This doesn't work (there's no 'staff_id' column in the assoc. table and there never was). // Fortunately, it's also never used, so
	 * there's no problem. Presumably it will need to be // used at some point -- at that point, it should be fixed, possibly by refactoring
	 * this class // to descend from Person. RMS20070621. @OneToMany @JoinColumn(name="staff_id") @Cascade(value = { CascadeType.ALL,
	 * CascadeType.DELETE_ORPHAN}) public List<ContactMechanism> getContactMechanims() { return contactMechanims; } public void
	 * setContactMechanims(List<ContactMechanism> contactMechanims) { this.contactMechanims = contactMechanims; } //
	 */

	// business methods

    /*
      * public void addSiteInvestigator(SiteInvestigator studyInvestigator) { getSiteInvestigators().add(studyInvestigator); }
      */

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

        if (nciIdentifier != null ? !nciIdentifier.equals(that.nciIdentifier) : that.nciIdentifier != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (nciIdentifier != null ? nciIdentifier.hashCode() : 0);
        return result;
    }

   
    public String getNciIdentifier() {
		return nciIdentifier;
	}

	public void setNciIdentifier(final String nciIdentifier) {
		this.nciIdentifier = nciIdentifier;
	}

}
