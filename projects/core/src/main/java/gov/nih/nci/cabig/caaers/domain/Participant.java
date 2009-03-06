package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.ProjectedList;
import gov.nih.nci.cabig.caaers.validation.annotation.UniqueIdentifierForParticipant;
import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections15.functors.InstantiateFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

/**
 * This class represents the Participant domain object associated with the Adverse event report.
 *
 * @author Krikor Krumlian
 * @author Rhett Sutphin
 */
@Entity
@Table
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_participants_id")})
@Where(clause = "load_status > 0")
public class Participant extends Person {
    private String institutionalPatientNumber;

    private String institution;

    private String maidenName;

    private DateValue dateOfBirth;

    private String gender;

    private String race;

    private String ethnicity;

    private Integer loadStatus = LoadStatus.COMPLETE.getCode();

    private final LazyListHelper lazyListHelper;

    private List<StudyParticipantAssignment> assignments = new ArrayList<StudyParticipantAssignment>();

    public Participant() {

        lazyListHelper = new LazyListHelper();

        // register with lazy list helper study site.
        lazyListHelper.add(Identifier.class, new InstantiateFactory<Identifier>(Identifier.class));
        dateOfBirth = new DateValue();

    }

    // //// LOGIC
    @Transient
    public void addAssignment(final StudyParticipantAssignment studyParticipantAssignment) {

        // make sure user can not add same assignment again
        if (studyParticipantAssignment != null && studyParticipantAssignment.getStudySite() != null
                && studyParticipantAssignment.getStudySite().getId() != null) {

            //first check if assignment already exists or not for a given studySite

            StudyParticipantAssignment existingParticipantAssignment = this.getStudyParticipantAssignment(studyParticipantAssignment.getStudySite());

            if (existingParticipantAssignment == null) {
                studyParticipantAssignment.setParticipant(this);
                this.getAssignments().add(studyParticipantAssignment);
                return;
            }


        }
    }

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

    @Transient
    public List<Study> getStudies() {
        List<Study> collected = new ArrayList<Study>(getAssignments().size());
        for (StudyParticipantAssignment assignment : getAssignments()) {
            collected.add(assignment.getStudySite().getStudy());
        }
        return collected;
    }

    /**
     * Will tell whether this participant is assigned to the give site.
     *
     * @param site
     * @return
     */
    public boolean isAssignedToStudySite(StudySite site) {
        return getStudyParticipantAssignment(site) != null;
    }

    public StudyParticipantAssignment getStudyParticipantAssignment(StudySite studySite) {
        if (studySite != null) {
            for (StudyParticipantAssignment assignment : getAssignments()) {
                if (assignment.getStudySite().getId().equals(studySite.getId())) return assignment;
            }
        }
        return null;
    }

    // //// BEAN PROPERTIES

    @Column(name = "instituitional_patient_number")
    // TODO: correct the column name's spelling
    public String getInstitutionalPatientNumber() {
        return institutionalPatientNumber;
    }

    public void setInstitutionalPatientNumber(final String instituitionalPatientNumber) {
        institutionalPatientNumber = instituitionalPatientNumber;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(final String institution) {
        this.institution = institution;
    }

    
    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(final String maidenName) {
        this.maidenName = maidenName;
    }


    @Embedded
    @AttributeOverrides( { @AttributeOverride(name = "day", column = @Column(name = "birth_day")),
            @AttributeOverride(name = "month", column = @Column(name = "birth_month")),
            @AttributeOverride(name = "year", column = @Column(name = "birth_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "birth_zone")) })
    public DateValue getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final DateValue dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /*
     * KK - used as a utility method for data import
     */
    @Transient
    public Date getBirthDate() {
        return this.dateOfBirth != null ? this.dateOfBirth.toDate() : null;
    }

    public void setBirthDate(Date date) {
        this.dateOfBirth = new DateValue(date);
    }

    /*
     * KK - used as a utility method for data import
     */
    @Transient
    public Date getBirthYear() {
        return this.dateOfBirth != null ? this.dateOfBirth.toDate() : null;
    }

    public void setBirthYear(Date date) {
        this.dateOfBirth = new DateValue(date);
        this.dateOfBirth.setDay(0);
        this.dateOfBirth.setMonth(0);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(final String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getRace() {
        return race;
    }

    public void setRace(final String race) {
        this.race = race;
    }

    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    @OrderBy
    // order by ID for testing consistency
    @Cascade(value = {CascadeType.DELETE, CascadeType.MERGE, CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DELETE_ORPHAN})
    @UniqueObjectInCollection(message = "Duplicate Assignement found in Assignments list")
    public List<StudyParticipantAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(final List<StudyParticipantAssignment> assignments) {
        this.assignments = assignments;
    }

    public Integer getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }
    

    @Override
    public boolean equals(Object obj) {
    	boolean found = false;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Participant other = (Participant) obj;
        if (getIdentifiers() == null) {
            if (other.getIdentifiers() != null) {
                return false;
            }
        } else {
        	for(Identifier identifier : getIdentifiers()){
        		for(Identifier otherIdentifier : other.getIdentifiers()){
        			if(identifier.equals(otherIdentifier)){
        				found = true;
        				break;
        			}
        		}
        	}
        	return found;
        }
        	
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 29 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 29 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 29 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    @OneToMany
    @Cascade({CascadeType.DELETE, CascadeType.MERGE, CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "participant_id")
    @UniqueIdentifierForParticipant
    @UniqueObjectInCollection(message = "Duplicate Identifier found in Identifiers list")
    public List<Identifier> getIdentifiers() {
        return lazyListHelper.getInternalList(Identifier.class);
    }

    @Override
    public void setIdentifiers(final List<Identifier> identifiers) {
        lazyListHelper.setInternalList(Identifier.class, identifiers);
    }

    @Transient
    @UniqueIdentifierForParticipant
    public List<Identifier> getIdentifiersLazy() {
        return lazyListHelper.getLazyList(Identifier.class);
    }

    @Transient
    public void setIdentifiersLazy(final List<Identifier> identifiers) {
        setIdentifiers(identifiers);
    }

    @Transient
    public Identifier getPrimaryIdentifier() {
        for (Identifier id : getIdentifiersLazy()) {
            if (id.isPrimary()) return id;
        }
        return null;
    }

    @Transient
    @UniqueIdentifierForParticipant
    @UniqueObjectInCollection(message = "Duplicate SystemAssignedIdentifier found in Identifiers list")
    public List<SystemAssignedIdentifier> getSystemAssignedIdentifiers() {
        return new ProjectedList(this.getIdentifiers(), SystemAssignedIdentifier.class);
    }                                

    @Transient
    @UniqueIdentifierForParticipant
    @UniqueObjectInCollection(message = "Duplicate OrganizationAssignedIdentifer found in Identifiers list")
    public List<OrganizationAssignedIdentifier> getOrganizationIdentifiers() {
        return new ProjectedList(this.getIdentifiers(), OrganizationAssignedIdentifier.class);  
    }
}
