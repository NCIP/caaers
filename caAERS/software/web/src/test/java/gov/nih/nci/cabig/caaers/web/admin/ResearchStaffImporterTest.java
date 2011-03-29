package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.util.ArrayList;

/**
 * @author Sameer Sawant
 * @author Ion C. Olaru
 */

public class ResearchStaffImporterTest extends WebTestCase {
	
	private ResearchStaffImporter importer;
	private ImportCommand command;
	private DomainObjectValidator domainObjectValidator;
	private DefaultResearchStaffMigratorService researchStaffMigratorService;
	private ResearchStaffRepository researchStaffRepository;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		importer = new ResearchStaffImporter();
        importer.setEventFactory(new EventFactory());
        importer.getEventFactory().setApplicationContext(new GenericApplicationContext() {
            @Override
            public void publishEvent(ApplicationEvent event) {}
        });

		command = new ImportCommand();
		domainObjectValidator = registerMockFor(DomainObjectValidator.class);
		researchStaffMigratorService = registerMockFor(DefaultResearchStaffMigratorService.class);
		researchStaffRepository = registerMockFor(ResearchStaffRepository.class);
		importer.setDomainObjectValidator(domainObjectValidator);
		importer.setResearchStaffMigratorService(researchStaffMigratorService);
		importer.setResearchStaffRepository(researchStaffRepository);
		
	}
	
	public void setUpImportCommand(){
		DomainObjectImportOutcome<ResearchStaff> importOutcome = new DomainObjectImportOutcome<ResearchStaff>();
		ResearchStaff researchStaff = Fixtures.createResearchStaff(Fixtures.createOrganization("test org"), new ArrayList<UserGroupType>(), "name");
		importOutcome.setImportedDomainObject(researchStaff);
		command.getImportableResearchStaff().add(importOutcome);
	}
	
	public void testProcessEntities(){
		//TODO
	}
	
	public void testSave(){
		setUpImportCommand();
		researchStaffRepository.save(command.getImportableResearchStaff().get(0).getImportedDomainObject(), ResetPasswordController.getURL(request
                .getScheme(), request.getServerName(), request.getServerPort(), request
                .getContextPath()));
		replayMocks();
		importer.save(command, request);
		verifyMocks();
	}
}