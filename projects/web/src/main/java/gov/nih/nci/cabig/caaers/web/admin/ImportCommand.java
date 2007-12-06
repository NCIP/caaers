package gov.nih.nci.cabig.caaers.web.admin;

import java.util.List;
import java.util.ArrayList;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import org.springframework.web.multipart.MultipartFile;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;


/**
 * @author Krikor Krumlian
 */
public class ImportCommand {
	
	private MultipartFile participantFile;
	private MultipartFile studyFile;
	private MultipartFile routineAdverseEventReportFile;
	private String schemaValidationResult;
	private String type;
	
	private List<DomainObjectImportOutcome<Study>> nonImportableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
	private List<DomainObjectImportOutcome<Study>> importableStudies = new ArrayList<DomainObjectImportOutcome<Study>>();
	
	private List<DomainObjectImportOutcome<Participant>> nonImportableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
	private List<DomainObjectImportOutcome<Participant>> importableParticipants = new ArrayList<DomainObjectImportOutcome<Participant>>();
	
	private List<DomainObjectImportOutcome<RoutineAdverseEventReport>> nonImportableRoutineAdverseEventReports = new ArrayList<DomainObjectImportOutcome<RoutineAdverseEventReport>>();
	private List<DomainObjectImportOutcome<RoutineAdverseEventReport>> importableRoutineAdverseEventReports = new ArrayList<DomainObjectImportOutcome<RoutineAdverseEventReport>>();
	
	
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
	
	public MultipartFile getRoutineAdverseEventReportFile() {
		return routineAdverseEventReportFile;
	}

	public void setRoutineAdverseEventReportFile(MultipartFile routineAeFile) {
		this.routineAdverseEventReportFile = routineAeFile;
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

	public List<DomainObjectImportOutcome<RoutineAdverseEventReport>> getImportableRoutineAdverseEventReports() {
		return importableRoutineAdverseEventReports;
	}

	public void setImportableRoutineAdverseEventReports(
			List<DomainObjectImportOutcome<RoutineAdverseEventReport>> importableRoutineAes) {
		this.importableRoutineAdverseEventReports = importableRoutineAes;
	}
	
	public void addImportableRoutineAdverseEventReport(DomainObjectImportOutcome<RoutineAdverseEventReport> domainObjectImportOutcome){
		getImportableRoutineAdverseEventReports().add(domainObjectImportOutcome);
	}

	public List<DomainObjectImportOutcome<RoutineAdverseEventReport>> getNonImportableRoutineAdverseEventReports() {
		return nonImportableRoutineAdverseEventReports;
	}

	public void setNonImportableRoutineAdverseEventReports(
			List<DomainObjectImportOutcome<RoutineAdverseEventReport>> nonImportableRoutineAes) {
		this.nonImportableRoutineAdverseEventReports = nonImportableRoutineAes;
	}
	
	public void addNonImportableRoutineAdverseEventReport(DomainObjectImportOutcome<RoutineAdverseEventReport> domainObjectImportOutcome){
		getNonImportableRoutineAdverseEventReports().add(domainObjectImportOutcome);
	}

	public String getSchemaValidationResult() {
		return schemaValidationResult;
	}

	public void setSchemaValidationResult(String schemaValidationResult) {
		this.schemaValidationResult = schemaValidationResult;
	}
}
