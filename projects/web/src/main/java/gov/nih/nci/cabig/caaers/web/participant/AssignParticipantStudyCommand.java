package gov.nih.nci.cabig.caaers.web.participant;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Identifier;

/**
 * @author Krikor Krumlian
 */

public class AssignParticipantStudyCommand {
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

	// ADDED

	// DEAD Pool

	private String studyType;

	private String studyText;

	private String participantType;

	private String participantText;

	private String studyId;

	private String participantId;

	// NEWLY ADDED
	private StudyParticipantAssignment studyParticipantAssignment = new StudyParticipantAssignment();

	private List<Study> studies = new ArrayList<Study>();

	private List<Participant> participants = new ArrayList<Participant>();

	private List<Participant> participantSearchResults = new ArrayList<Participant>();

	private List<Identifier> identifiers = new ArrayList<Identifier>();

	private List<StudySite> studySites = new ArrayList<StudySite>();

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

	public String getStudyText() {
		return studyText;
	}

	public void setStudyText(final String studyText) {
		this.studyText = studyText;
	}

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(final String studyType) {
		this.studyType = studyType;
	}

	/*
	 * public String getStudySiteId() { return studySiteId; } public void setStudySiteId(String studySiteId) { this.studySiteId =
	 * studySiteId; }
	 */
	public String getParticipantId() {
		return participantId;
	}

	public void setStudyId(final String studyId) {
		this.studyId = studyId;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setParticipantId(final String participantId) {
		this.participantId = participantId;
	}

	public String getParticipantText() {
		return participantText;
	}

	public void setParticipantText(final String participantText) {
		this.participantText = participantText;
	}

	public String getParticipantType() {
		return participantType;
	}

	public void setParticipantType(final String participantType) {
		this.participantType = participantType;
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

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(final List<Participant> participants) {
		this.participants = participants;
	}

	public List<Participant> getParticipantSearchResults() {
		return participantSearchResults;
	}

	public void setParticipantSearchResults(final List<Participant> participantSearchResults) {
		this.participantSearchResults = participantSearchResults;
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
}
