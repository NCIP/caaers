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
import org.hibernate.annotations.*;

 
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
    
    /** The institutional patient number. */
    private String institutionalPatientNumber;

    /** The institution. */
    private String institution;

    /** The maiden name. */
    private String maidenName;

    /** The date of birth. */
    private DateValue dateOfBirth;

    /** The gender. */
    private String gender;

    /** The race. */
    private String race;

    /** The ethnicity. */
    private String ethnicity;

    /** The load status. */
    private Integer loadStatus = LoadStatus.COMPLETE.getCode();

    /** The lazy list helper. */
    private final LazyListHelper lazyListHelper;

    /** The assignments. */
    private List<StudyParticipantAssignment> assignments = new ArrayList<StudyParticipantAssignment>();

    /**
     * Instantiates a new participant.
     */
    public Participant() {

        lazyListHelper = new LazyListHelper();

        // register with lazy list helper study site.
        lazyListHelper.add(Identifier.class, new InstantiateFactory<Identifier>(Identifier.class));
        dateOfBirth = new DateValue();

    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getFirstName()
     */
    @Override
    public String getFirstName() {
        return firstName;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getLastName()
     */
    @Override
    public String getLastName() {
        return lastName;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getMiddleName()
     */
    @Override
    public String getMiddleName() {
        return middleName;
    }

    // //// LOGIC
    /**
     * Adds the assignment.
     *
     * @param studyParticipantAssignment the study participant assignment
     */
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

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getLastFirst()
     */
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

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.Person#getFullName()
     */
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

    /**
     * Gets the studies.
     *
     * @return the studies
     */
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
     * @param site the site
     * @return true, if is assigned to study site
     */
    public boolean isAssignedToStudySite(StudySite site) {
        return getStudyParticipantAssignment(site) != null;
    }

    /**
     * Gets the study participant assignment.
     *
     * @param studySite the study site
     * @return the study participant assignment
     */
    public StudyParticipantAssignment getStudyParticipantAssignment(StudySite studySite) {
        if (studySite != null) {
            for (StudyParticipantAssignment assignment : getAssignments()) {
                if (assignment.getStudySite().getId().equals(studySite.getId())) return assignment;
            }
        }
        return null;
    }

    // //// BEAN PROPERTIES

    /**
     * Gets the institutional patient number.
     *
     * @return the institutional patient number
     */
    @Column(name = "instituitional_patient_number")
    // TODO: correct the column name's spelling
    public String getInstitutionalPatientNumber() {
        return institutionalPatientNumber;
    }

    /**
     * Sets the institutional patient number.
     *
     * @param instituitionalPatientNumber the new institutional patient number
     */
    public void setInstitutionalPatientNumber(final String instituitionalPatientNumber) {
        institutionalPatientNumber = instituitionalPatientNumber;
    }

    /**
     * Gets the institution.
     *
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Sets the institution.
     *
     * @param institution the new institution
     */
    public void setInstitution(final String institution) {
        this.institution = institution;
    }

    
    /**
     * Gets the maiden name.
     *
     * @return the maiden name
     */
    public String getMaidenName() {
        return maidenName;
    }

    /**
     * Sets the maiden name.
     *
     * @param maidenName the new maiden name
     */
    public void setMaidenName(final String maidenName) {
        this.maidenName = maidenName;
    }


    /**
     * Gets the date of birth.
     *
     * @return the date of birth
     */
    @Embedded
    @AttributeOverrides( { @AttributeOverride(name = "day", column = @Column(name = "birth_day")),
            @AttributeOverride(name = "month", column = @Column(name = "birth_month")),
            @AttributeOverride(name = "year", column = @Column(name = "birth_year")),
            @AttributeOverride(name = "zone", column = @Column(name = "birth_zone")) })
    public DateValue getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     *
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(final DateValue dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /*
     * KK - used as a utility method for data import
     */
    /**
     * Gets the birth date.
     *
     * @return the birth date
     */
    @Transient
    public Date getBirthDate() {
        return this.dateOfBirth != null ? this.dateOfBirth.toDate() : null;
    }

    /**
     * Sets the birth date.
     *
     * @param date the new birth date
     */
    public void setBirthDate(Date date) {
        this.dateOfBirth = new DateValue(date);
    }

    /*
     * KK - used as a utility method for data import
     */
    /**
     * Gets the birth year.
     *
     * @return the birth year
     */
    @Transient
    public Date getBirthYear() {
        return this.dateOfBirth != null ? this.dateOfBirth.toDate() : null;
    }

    /**
     * Sets the birth year.
     *
     * @param date the new birth year
     */
    public void setBirthYear(Date date) {
        this.dateOfBirth = new DateValue(date);
        this.dateOfBirth.setDay(0);
        this.dateOfBirth.setMonth(0);
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * Gets the ethnicity.
     *
     * @return the ethnicity
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * Sets the ethnicity.
     *
     * @param ethnicity the new ethnicity
     */
    public void setEthnicity(final String ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * Gets the race.
     *
     * @return the race
     */
    public String getRace() {
        return race;
    }

    /**
     * Sets the race.
     *
     * @param race the new race
     */
    public void setRace(final String race) {
        this.race = race;
    }

    /**
     * Gets the assignments.
     *
     * @return the assignments
     */
    @OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    @OrderBy
    // order by ID for testing consistency
    @Cascade(value = {CascadeType.DELETE, CascadeType.MERGE, CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DELETE_ORPHAN})
    @UniqueObjectInCollection(message = "Duplicate Assignement found in Assignments list")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyParticipantAssignment> getAssignments() {
        return assignments;
    }

    /**
     * Sets the assignments.
     *
     * @param assignments the new assignments
     */
    public void setAssignments(final List<StudyParticipantAssignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * Gets the load status.
     *
     * @return the load status
     */
    public Integer getLoadStatus() {
        return loadStatus;
    }

    /**
     * Sets the load status.
     *
     * @param loadStatus the new load status
     */
    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }
    

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 29 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 29 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 29 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject#getIdentifiers()
     */
    @Override
    @OneToMany
    @Cascade({CascadeType.DELETE, CascadeType.MERGE, CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "participant_id")
    @UniqueObjectInCollection(message = "Duplicate Identifier found in Identifiers list")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<Identifier> getIdentifiers() {
        return lazyListHelper.getInternalList(Identifier.class);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject#setIdentifiers(java.util.List)
     */
    @Override
    public void setIdentifiers(final List<Identifier> identifiers) {
        lazyListHelper.setInternalList(Identifier.class, identifiers);
    }

    /**
     * Gets the identifiers lazy.
     *
     * @return the identifiers lazy
     */
    @Transient
    public List<Identifier> getIdentifiersLazy() {
        return lazyListHelper.getLazyList(Identifier.class);
    }

    /**
     * Sets the identifiers lazy.
     *
     * @param identifiers the new identifiers lazy
     */
    @Transient
    public void setIdentifiersLazy(final List<Identifier> identifiers) {
        setIdentifiers(identifiers);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject#getPrimaryIdentifier()
     */
    @Transient
    public Identifier getPrimaryIdentifier() {
        try {
			for (Identifier id : getIdentifiersLazy()) {
			    if (id.isPrimary()) return id;
			}
		} catch (Exception ignore) {
		}
        return null;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject#getPrimaryIdentifierValue()
     */
    @Transient
    public String getPrimaryIdentifierValue() {
    	return getPrimaryIdentifier().getValue();
    }

    /**
     * Gets the system assigned identifiers.
     *
     * @return the system assigned identifiers
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate SystemAssignedIdentifier found in Identifiers list")
    public List<SystemAssignedIdentifier> getSystemAssignedIdentifiers() {
        return new ProjectedList(this.getIdentifiers(), SystemAssignedIdentifier.class);
    }                                

    /**
     * Gets the organization identifiers.
     *
     * @return the organization identifiers
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate OrganizationAssignedIdentifer found in Identifiers list")
    public List<OrganizationAssignedIdentifier> getOrganizationIdentifiers() {
        return new ProjectedList(this.getIdentifiers(), OrganizationAssignedIdentifier.class);  
    }
}
