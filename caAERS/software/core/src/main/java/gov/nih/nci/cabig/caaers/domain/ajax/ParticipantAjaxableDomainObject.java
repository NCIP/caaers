/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.ajax;

import java.util.*;

 
/**
 * The Class ParticipantAjaxableDomainObject.
 */
public class ParticipantAjaxableDomainObject extends AbstractAjaxableDomainObject {


    /** The last name. */
    private String lastName;
    
    /** The first name. */
    private String firstName;
    
    /** The gender. */
    private String gender;
    
    /** The race. */
    private String race;
    
    /** The ethnicity. */
    private String ethnicity;
    
    /** The primary identifier value. */
    private String primaryIdentifierValue;
    
    /** The study subject identifiers. */
    private Set<String> studySubjectIdentifiers;
    
    /** The study subject identifiers string. */
    private String studySubjectIdentifiersString;

    private String studyPrimaryIdentifier;

    private Integer assignmentId;
    private Integer studyId;

  //  List<StudySiteAjaxableDomainObject> studySites = new ArrayList<StudySiteAjaxableDomainObject>();
    /** The studies. */
  List<StudySearchableAjaxableDomainObject> studies = new ArrayList<StudySearchableAjaxableDomainObject>();

    //FIXME : this logic belongs to participant
    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {

        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" : " ( " + this.getPrimaryIdentifierValue() + " ) ";
        StringBuilder name = new StringBuilder(primaryIdentifier);
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
        if (getStudySubjectIdentifiersString() != null)
            name.append(" - (" + getStudySubjectIdentifiersString() + ")");
        return name.toString() ;

    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {

        return lastName;
    }

    /**
     * Gets the primary identifier value.
     *
     * @return the primary identifier value
     */
    public String getPrimaryIdentifierValue() {
        return primaryIdentifierValue;
    }

    /**
     * Sets the primary identifier value.
     *
     * @param primaryIdentifierValue the new primary identifier value
     */
    public void setPrimaryIdentifierValue(String primaryIdentifierValue) {
        this.primaryIdentifierValue = primaryIdentifierValue;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the studies.
     *
     * @return the studies
     */
    public List<StudySearchableAjaxableDomainObject> getStudies() {
        return studies;
    }

	/**
	 * Adds the study.
	 *
	 * @param studySearchableAjaxableDomainObject the study searchable ajaxable domain object
	 */
	public void addStudy(StudySearchableAjaxableDomainObject studySearchableAjaxableDomainObject) {
        if (getObjectById(this.getStudies(), studySearchableAjaxableDomainObject.getId()) == null) {
        	getStudies().add(studySearchableAjaxableDomainObject);
        } else {
        	getStudies().remove(studySearchableAjaxableDomainObject);
        	getStudies().add(studySearchableAjaxableDomainObject);
        }

    }
	/*
    public List<StudySiteAjaxableDomainObject> getStudySites() {
        return studySites;
    }

	public void addStudySite(StudySiteAjaxableDomainObject studySiteAjaxableDomainObject) {
        if (getObjectById(this.getStudySites(), studySiteAjaxableDomainObject.getId()) == null) {
            getStudySites().add(studySiteAjaxableDomainObject);
        }

    }   
    */
    /**
	 * Gets the object by id.
	 *
	 * @param ajaxableDomainObjects the ajaxable domain objects
	 * @param id the id
	 * @return the object by id
	 */
	protected AbstractAjaxableDomainObject getObjectById(List<? extends AbstractAjaxableDomainObject> ajaxableDomainObjects, Integer id) {
        for (AbstractAjaxableDomainObject object : ajaxableDomainObjects) {
            if (object.getId().equals(id)) {
                return object;
            }
        }
        return null;
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
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
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
	public void setGender(String gender) {
		this.gender = gender;
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
	public void setRace(String race) {
		this.race = race;
	}

    /**
     * Gets the study subject identifiers.
     *
     * @return the study subject identifiers
     */
    public Set getStudySubjectIdentifiers() {
        if (studySubjectIdentifiers == null) studySubjectIdentifiers = new HashSet<String>();
        return studySubjectIdentifiers;
    }

    /**
     * Sets the study subject identifiers.
     *
     * @param studySubjectIdentifiers the new study subject identifiers
     */
    public void setStudySubjectIdentifiers(Set studySubjectIdentifiers) {
        this.studySubjectIdentifiers = studySubjectIdentifiers;
    }

    /**
     * Collect study subject identifier.
     *
     * @param studySubjectIdentifier the study subject identifier
     */
    public void collectStudySubjectIdentifier(String studySubjectIdentifier) {
        if (getStudySubjectIdentifiers() == null) setStudySubjectIdentifiers(new HashSet<String>());
        getStudySubjectIdentifiers().add(studySubjectIdentifier);
    }

    /**
     * Gets the study subject identifiers csv.
     *
     * @return the study subject identifiers csv
     */
    public String getStudySubjectIdentifiersCSV() {
        Iterator it = getStudySubjectIdentifiers().iterator();
        StringBuffer sb = new StringBuffer("");
        while (it.hasNext()) {
            sb.append(it.next().toString() + ", ");
        }
        if (sb.length() > 0)
            return sb.substring(0, sb.length() - 2);
        else return "";
    }

    /**
     * Gets the study subject identifiers string.
     *
     * @return the study subject identifiers string
     */
    public String getStudySubjectIdentifiersString() {
        return studySubjectIdentifiersString;
    }

    /**
     * Sets the study subject identifiers string.
     *
     * @param studySubjectIdentifiersString the new study subject identifiers string
     */
    public void setStudySubjectIdentifiersString(String studySubjectIdentifiersString) {
        this.studySubjectIdentifiersString = studySubjectIdentifiersString;
    }

    public String getStudyPrimaryIdentifier() {
        return studyPrimaryIdentifier;
    }

    public void setStudyPrimaryIdentifier(String studyPrimaryIdentifier) {
        this.studyPrimaryIdentifier = studyPrimaryIdentifier;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getStudyId() {
        return studyId;
    }

    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
    }
}
