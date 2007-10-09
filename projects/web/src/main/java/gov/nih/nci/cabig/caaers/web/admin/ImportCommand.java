package gov.nih.nci.cabig.caaers.web.admin;

import java.util.List;
import java.util.ArrayList;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Participant;
import org.springframework.web.multipart.MultipartFile;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;


/**
 * @author Krikor Krumlian
 */
public class ImportCommand {
	
	private MultipartFile participantFile;
	private MultipartFile studyFile;
	private String type;
	
	private List<DomainObjectImportOutcome<Study>> nonImportableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
	private List<DomainObjectImportOutcome<Study>> importableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
	
	private List<DomainObjectImportOutcome<Participant>> nonImportableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
	private List<DomainObjectImportOutcome<Participant>> importableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MultipartFile getParticipantFile() {
		return participantFile;
	}

	public void setParticipantFile(MultipartFile participantFile) {
		this.participantFile = participantFile;
	}

	public MultipartFile getStudyFile() {
		return studyFile;
	}

	public void setStudyFile(MultipartFile studyFile) {
		this.studyFile = studyFile;
	}

	public List<DomainObjectImportOutcome<Study>> getNonImportableStudies() {
		return nonImportableStudies;
	}

	public void setNonImportableStudies(
			List<DomainObjectImportOutcome<Study>> nonImportableStudies) {
		this.nonImportableStudies = nonImportableStudies;
	}
	
	public void addNonImportableStudy(DomainObjectImportOutcome<Study> domainObjectImportOutcome){
		getNonImportableStudies().add(domainObjectImportOutcome);
	}

	public List<DomainObjectImportOutcome<Study>> getImportableStudies() {
		return importableStudies;
	}

	public void setImportableStudies(
			List<DomainObjectImportOutcome<Study>> importableStudies) {
		this.importableStudies = importableStudies;
	}
	
	public void addImportableStudy(DomainObjectImportOutcome<Study> domainObjectImportOutcome){
		getImportableStudies().add(domainObjectImportOutcome);
	}

	public List<DomainObjectImportOutcome<Participant>> getImportableParticipants() {
		return importableParticipants;
	}

	public void setImportableParticipants(
			List<DomainObjectImportOutcome<Participant>> importableParticipants) {
		this.importableParticipants = importableParticipants;
	}
	
	public void addImportableParticipant(DomainObjectImportOutcome<Participant> domainObjectImportOutcome){
		getImportableParticipants().add(domainObjectImportOutcome);
	}

	public List<DomainObjectImportOutcome<Participant>> getNonImportableParticipants() {
		return nonImportableParticipants;
	}

	public void setNonImportableParticipants(
			List<DomainObjectImportOutcome<Participant>> nonImportableParticipants) {
		this.nonImportableParticipants = nonImportableParticipants;
	}
	
	public void addNonImportableParticipant(DomainObjectImportOutcome<Participant> domainObjectImportOutcome){
		getNonImportableParticipants().add(domainObjectImportOutcome);
	}
	
	
	
	
	
}
