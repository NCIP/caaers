package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Priyatam
 * @author Kulasekaran
 */
@Entity
@Table(name = "research_staffs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_research_staffs_id") })
public class ResearchStaff extends AbstractMutableDomainObject {

	private String firstName;

	private String middleName;

	private String lastName;

	private String emailAddress;// abc@email.com

	private String phoneNumber;

	private String faxNumber;

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
	@Transient
	public String getLastFirst() {
		StringBuilder name = new StringBuilder();
		boolean hasFirstName = getFirstName() != null;
		if (getLastName() != null) {
			name.append(getLastName());
			if (hasFirstName) {
				name.append(", ");
			}
		}
		if (hasFirstName) {
			name.append(getFirstName());
		}
		return name.toString();
	}

	@Transient
	public String getFullName() {
		StringBuilder name = new StringBuilder();
		boolean hasLastName = getLastName() != null;
		if (getFirstName() != null) {
			name.append(getFirstName());
			if (hasLastName) {
				name.append(' ');
			}
		}
		if (hasLastName) {
			name.append(getLastName());
		}
		return name.toString();
	}

	/*
	 * public void addSiteInvestigator(SiteInvestigator studyInvestigator) { getSiteInvestigators().add(studyInvestigator); }
	 */

	// bean methods
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

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

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + (firstName == null ? 0 : firstName.hashCode());
		result = PRIME * result + (lastName == null ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ResearchStaff other = (ResearchStaff) obj;
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		}
		else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		}
		else if (!lastName.equals(other.lastName)) {
			return false;
		}
		return true;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setFaxNumber(final String faxNumber) {
		this.faxNumber = faxNumber;
	}

}
