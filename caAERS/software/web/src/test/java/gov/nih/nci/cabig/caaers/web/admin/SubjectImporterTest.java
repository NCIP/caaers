/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author Sameer Sawant
 * @author Ion C. Olaru
 */

public class SubjectImporterTest extends WebTestCase {
	
	private SubjectImporter importer;
	private DomainObjectValidator domainObjectValidator;
	private ParticipantServiceImpl participantServiceImpl;
	private ParticipantDao participantDao;
	private ImportCommand command;
    private ApplicationContext ctx;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		importer = new SubjectImporter();
        importer.setEventFactory(new EventFactory());
        importer.getEventFactory().setApplicationContext(new GenericApplicationContext() {
            @Override
            public void publishEvent(ApplicationEvent event) {}
        });
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
