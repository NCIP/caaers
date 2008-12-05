package gov.nih.nci.cabig.caaers.domain.ajax;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class ParticipantAjaxableDomainObject extends AbstractAjaxableDomainObject {


    private String lastName;
    private String firstName;
    private String gender;
    private String race;
    private String ethnicity;
    
    private String primaryIdentifierValue;
    
  //  List<StudySiteAjaxableDomainObject> studySites = new ArrayList<StudySiteAjaxableDomainObject>();
    List<StudySearchableAjaxableDomainObject> studies = new ArrayList<StudySearchableAjaxableDomainObject>();

    //FIXME : this logic belongs to participant
    public String getDisplayName() {

        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" :
                " ( " + this.getPrimaryIdentifierValue() + " ) ";
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
        return name.toString() + primaryIdentifier;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getPrimaryIdentifierValue() {
        return primaryIdentifierValue;
    }

    public void setPrimaryIdentifierValue(String primaryIdentifierValue) {
        this.primaryIdentifierValue = primaryIdentifierValue;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<StudySearchableAjaxableDomainObject> getStudies() {
        return studies;
    }

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
    protected AbstractAjaxableDomainObject getObjectById(List<? extends AbstractAjaxableDomainObject> ajaxableDomainObjects, Integer id) {
        for (AbstractAjaxableDomainObject object : ajaxableDomainObjects) {
            if (object.getId().equals(id)) {
                return object;
            }
        }
        return null;
    }

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
    
}