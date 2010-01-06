package gov.nih.nci.cabig.caaers.web.admin;

import org.easymock.EasyMock;

import gov.nih.nci.cabig.caaers.api.impl.StudyProcessorImpl;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Sameer Sawant
 */

public class StudyImporterTest extends WebTestCase {

	private StudyImporter importer;
	private DomainObjectValidator domainObjectValidator;
	private StudyProcessorImpl studyProcessorImpl;
	private StudyRepository studyRepository;
	private ImportCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		importer = new StudyImporter();
		command = new ImportCommand();
		domainObjectValidator = registerMockFor(DomainObjectValidator.class);
		studyProcessorImpl = registerMockFor(StudyProcessorImpl.class);
		studyRepository = registerMockFor(StudyRepository.class);
		
		importer.setDomainObjectValidator(domainObjectValidator);
		importer.setStudyProcessorImpl(studyProcessorImpl);
		importer.setStudyRepository(studyRepository);
	}
	
	public void setUpImportCommand(){
		DomainObjectImportOutcome<Study> importOutcome = new DomainObjectImportOutcome<Study>();
		Study study = Fixtures.createStudy("study");
		importOutcome.setImportedDomainObject(study);
		command.getImportableStudies().add(importOutcome);
	}
	
	public void testProcessEntities(){
		//TODO
	}
	
	public void testSave(){
		setUpImportCommand();
		studyRepository.save(command.getImportableStudies().get(0).getImportedDomainObject());
		replayMocks();
		importer.save(command, request);
		verifyMocks();
	}
}