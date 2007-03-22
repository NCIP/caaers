package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Krikor Krumlian
 */
@Entity
@Table
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_participants_id")
    }
)
public class Participant extends AbstractDomainObject {
    private String institutionalPatientNumber;
    private String institution;
    private String firstName;
    private String middleName;
    private String maidenName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String race;
    private String ethnicity;
    private List<StudyParticipantAssignment> assignments = new ArrayList<StudyParticipantAssignment>();
    private List<Identifier> identifiers = new ArrayList<Identifier>();

    ////// LOGIC

    public void addAssignment(StudyParticipantAssignment studyParticipantAssignment){
        getAssignments().add(studyParticipantAssignment);
        studyParticipantAssignment.setParticipant(this);
    }

    @Transient
    public String getLastFirst() {
        StringBuilder name = new StringBuilder();
        boolean hasFirstName = getFirstName() != null;
        if (getLastName() != null) {
            name.append(getLastName());
            if (hasFirstName) name.append(", ");
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
            if (hasLastName) name.append(' ');
        }
        if (hasLastName) {
            name.append(getLastName());
        }
        return name.toString();
    }

    @Transient
    public List<Study> getStudies() {
        List<Study> collected = new ArrayList<Study>(getAssignments().size());
        for (StudyParticipantAssignment assignment : getAssignments()) {
            collected.add(assignment.getStudySite().getStudy());
        }
        return collected;
    }

    ////// BEAN PROPERTIES

    @Column(name= "instituitional_patient_number") // TODO: correct the column name's spelling
    public String getInstitutionalPatientNumber() {
        return institutionalPatientNumber;
    }

    public void setInstitutionalPatientNumber(String instituitionalPatientNumber) {
        this.institutionalPatientNumber = instituitionalPatientNumber;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "birth_date")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @OneToMany (mappedBy = "participant", fetch=FetchType.LAZY)
    @OrderBy // order by ID for testing consistency
    @Cascade (value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyParticipantAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<StudyParticipantAssignment> assignments) {
        this.assignments = assignments;
    }

    @OneToMany
    @Cascade({CascadeType.ALL,CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "participant_id")
    public List<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public void addIdentifier(Identifier identifier) {
        identifiers.add(identifier);
    }

    public void removeIdentifier(Identifier identifier) {
        identifiers.remove(identifier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Participant that = (Participant) o;

        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
            return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (assignments != null ? !assignments.equals(that.assignments) : that.assignments != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (firstName != null ? firstName.hashCode() : 0);
        result = 29 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 29 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 29 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
