package gov.nih.nci.cabig.caaers.web.participant;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Identifier;

/**
 * @author Krikor Krumlian
 */

public class NewParticipantCommand {
	protected final Log log = LogFactory.getLog(getClass());

	private String instituitionalPatientNumber;

	private String institution;

	private String firstName;

	private String middleName;

	private String maidenName;

	private String lastName;

	private Date dateOfBirth;

	private String gender;

	private String race;

	private String ethnicity;

	private String[] studySiteArray;

	private String searchTypeText;

	private String searchType;

	private String searchText;

	// NEWLY ADDED
	private StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment();

	private List<Study> studies = new ArrayList<Study>();

	private final List<Identifier> identifiers = new ArrayList<Identifier>();

	private List<StudySite> studySites = new ArrayList<StudySite>();

	private Organization organization;

	/*
	 * public Participant createParticipant() { Participant participant = new Participant();
	 * participant.setInstitutionalPatientNumber(getInstituitionalPatientNumber()); participant.setInstitution(getInstitution());
	 * participant.setFirstName(getFirstName()); participant.setLastName(getLastName()); participant.setDateOfBirth(getDateOfBirth());
	 * participant.setGender(getGender()); participant.setRace(getRace()); participant.setEthnicity(getEthnicity());
	 * participant.setIdentifiers(getIdentifiers()); return participant; }
	 */

	public Participant createParticipant() {
		Participant participant = new Participant();
		participant.setInstitutionalPatientNumber(getInstituitionalPatientNumber());
		participant.setInstitution(getInstitution());
		participant.setFirstName(getFirstName());
		participant.setMaidenName(getMaidenName());
		participant.setMiddleName(getMiddleName());
		participant.setLastName(getLastName());
		participant.setDateOfBirth(getDateOfBirth());
		participant.setGender(getGender());
		participant.setRace(getRace());
		participant.setEthnicity(getEthnicity());
		participant.setIdentifiers(getIdentifiers());

		for (int i = 0; i < studySites.size(); i++) {
			participant.getAssignments().add(new StudyParticipantAssignment(participant, studySites.get(i)));
		}
		return participant;
	}

	public String getInstituitionalPatientNumber() {
		return instituitionalPatientNumber;
	}

	public void setInstituitionalPatientNumber(final String instituitionalPatientNumber) {
		this.instituitionalPatientNumber = instituitionalPatientNumber;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(final String institution) {
		this.institution = institution;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(final String maidenName) {
		this.maidenName = maidenName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(final Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String[] getStudySiteArray() {
		return studySiteArray;
	}

	public void setStudySiteArray(final String[] studySiteArray) {
		this.studySiteArray = studySiteArray;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(final String searchType) {
		this.searchType = searchType;
	}

	public String getSearchTypeText() {
		return searchTypeText;
	}

	public void setSearchTypeText(final String searchTypeText) {
		this.searchTypeText = searchTypeText;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(final String searchText) {
		this.searchText = searchText;
	}

	public StudyParticipantAssignment getStudyParticipantAssignment() {
		return studyParticipantAssignment;
	}

	public void setStudyParticipantAssignment(final StudyParticipantAssignment studyParticipantAssignment) {
		this.studyParticipantAssignment = studyParticipantAssignment;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(final List<Study> studies) {
		this.studies = studies;
	}

	public List<StudySite> getStudySites() {
		return studySites;
	}

	public void setStudySites(final List<StudySite> studySites) {
		this.studySites = studySites;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(final List<? extends Identifier> identifiers) {
		this.identifiers.addAll(identifiers);
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
