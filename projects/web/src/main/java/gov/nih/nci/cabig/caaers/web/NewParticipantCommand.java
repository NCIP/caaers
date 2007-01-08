package gov.nih.nci.cabig.caaers.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author Krikor Krumlian
 */

public class NewParticipantCommand {
	protected final Log log = LogFactory.getLog(getClass());
	private String instituitionalPatientNumber;
	private String institution;
	private String studyParticipantName;
	private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String race;
    private String ethnicity;
    private String[] studySiteArray;
    	
	public Participant createParticipant() {
		Participant participant = new Participant();
		participant.setInstitutionalPatientNumber(getInstituitionalPatientNumber());
		participant.setInstitution(getInstitution());
		participant.setStudyParticipantName(getStudyParticipantName());
		participant.setFirstName(getFirstName());
		participant.setLastName(getLastName());
		participant.setDateOfBirth(getDateOfBirth());
		participant.setGender(getGender());
		participant.setRace(getRace());
		participant.setEthnicity(getEthnicity());
        return participant;
    }
	
	public Participant createParticipant(List<StudySite> studySites) {
		Participant participant = new Participant();
		participant.setInstitutionalPatientNumber(getInstituitionalPatientNumber());
		participant.setInstitution(getInstitution());
		participant.setStudyParticipantName(getStudyParticipantName());
		participant.setFirstName(getFirstName());
		participant.setLastName(getLastName());
		participant.setDateOfBirth(getDateOfBirth());
		participant.setGender(getGender());
		participant.setRace(getRace());
		participant.setEthnicity(getEthnicity());
		
		for (int i = 0; i < studySiteArray.length ; i++) {
			log.debug("the selected indexes : " + studySiteArray[i]);
			participant.getAssignments().add(
					new StudyParticipantAssignment(participant,
							studySites.get(Integer.parseInt(studySiteArray[i]))));
		}
		
        return participant;
    }
	
	 public String getInstituitionalPatientNumber() {
			return instituitionalPatientNumber;
		}

	 public void setInstituitionalPatientNumber(String instituitionalPatientNumber) {
			this.instituitionalPatientNumber = instituitionalPatientNumber;
		}
	
	 public String getInstitution() {
			return institution;
		}

	public void setInstitution(String institution) {
			this.institution = institution;
		}
	
	 public String getStudyParticipantName() {
			return studyParticipantName;
		}

	public void setStudyParticipantName(String studyParticipantName) {
			this.studyParticipantName = studyParticipantName;
		}
	 
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String[] getStudySiteArray() {
		return studySiteArray;
	}

	public void setStudySiteArray(String[] studySiteArray) {
		this.studySiteArray = studySiteArray;
		log.debug("The list size is : " + studySiteArray == null ? "null" : studySiteArray.length);
	}
	

}
