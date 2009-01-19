package gov.nih.nci.cabig.caaers.domain;

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
 * This class represents the ResearchStaff domain object associated with the Adverse event report.
 * 
 * @author Priyatam
 * @author Kulasekaran
 */
@Entity
@Table(name = "research_staffs")
public class ResearchStaff extends User {

    private String nciIdentifier;

    private List<StudyPersonnel> studyPersonnels = new ArrayList<StudyPersonnel>();

    private Organization organization;

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

        if (nciIdentifier != null ? !nciIdentifier.equals(that.nciIdentifier)
                        : that.nciIdentifier != null) return false;

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
