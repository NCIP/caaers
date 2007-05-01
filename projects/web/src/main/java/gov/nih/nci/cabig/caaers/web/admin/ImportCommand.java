package gov.nih.nci.cabig.caaers.web.admin;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Participant;


/**
 * @author Krikor Krumlian
 */
public class ImportCommand {
	
	private String studyFileName;
	private String participantFileName;
	private List<Study> studies = new ArrayList<Study>();
	private List<Participant> participants = new ArrayList<Participant>();
	private HashMap<Participant,List<String>> participantErrors = new HashMap<Participant,List<String>>();
	private HashMap<Study,List<String>> studyErrors = new HashMap<Study,List<String>>();
	

	public String getStudyFileName() {
		return studyFileName;
	}

	public void setStudyFileName(String studyFileName) {
		this.studyFileName = studyFileName;
	}

	public String getParticipantFileName() {
		return participantFileName;
	}

	public void setParticipantFileName(String participantFileName) {
		this.participantFileName = participantFileName;
	}

	public List<Study> getStudies() {
		return studies;
	}

	public void setStudies(List<Study> studies) {
		this.studies = studies;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}
	

	public HashMap<Participant, List<String>> getParticipantErrors() {
		return participantErrors;
	}

	public void setParticipantErrors(
			HashMap<Participant, List<String>> participantErrors) {
		this.participantErrors = participantErrors;
	}
	
	public void addParticipantErros(Participant participant, String errorMessage){
		if (participantErrors.containsKey(participant)) {
			participantErrors.get(participant).add(errorMessage);
		} 
		else{
			//ArrayList<MigrationError> migerr = new ArrayList<MigrationError>();
			//migerr.add(new MigrationError(errorMessage));
			ArrayList<String> errorMessages = new ArrayList<String>();
			errorMessages.add(errorMessage);
			participantErrors.put(participant, errorMessages);		
		}
	}

	public HashMap<Study, List<String>> getStudyErrors() {
		return studyErrors;
	}

	public void setStudyErrors(HashMap<Study, List<String>> studyErrors) {
		this.studyErrors = studyErrors;
	}
	
	public void addStudyErros(Study study, String errorMessage){
		if (studyErrors.containsKey(study)) {
			studyErrors.get(study).add(errorMessage);
		} 
		else{
			//ArrayList<MigrationError> migerr = new ArrayList<MigrationError>();
			//migerr.add(new MigrationError(errorMessage));
			ArrayList<String> errorMessages = new ArrayList<String>();
			errorMessages.add(errorMessage);
			studyErrors.put(study, errorMessages);		
		}
	}
	
	
	
}
