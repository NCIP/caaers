package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Sameer Sawant
 */

public class SubjectImporterTest extends WebTestCase {
	
	private SubjectImporter importer;
	private DomainObjectValidator domainObjectValidator;
	private ParticipantServiceImpl participantServiceImpl;
	private ParticipantDao participantDao;
	private ImportCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		importer = new SubjectImporter();
		command = new ImportCommand();
		domainObjectValidator = registerMockFor(DomainObjectValidator.class);
		participantServiceImpl = registerMockFor(ParticipantServiceImpl.class);
		participantDao = registerDaoMockFor(ParticipantDao.class);
		importer.setDomainObjectValidator(domainObjectValidator);
		importer.setParticipantDao(participantDao);
		importer.setParticipantServiceImpl(participantServiceImpl);
	}
	
	public void setUpImportCommand(){
		DomainObjectImportOutcome<Participant> importOutcome = new DomainObjectImportOutcome<Participant>();
		Participant participant = Fixtures.createParticipant("firstName", "lastName");
		importOutcome.setImportedDomainObject(participant);
		command.getImportableParticipants().add(importOutcome);
	}
	
	public void testProcessEntities(){
		//TODO
	}
	
	public void testSave(){
		setUpImportCommand();
		participantDao.save(command.getImportableParticipants().get(0).getImportedDomainObject());
		replayMocks();
		importer.save(command, request);
		verifyMocks();
	}
}