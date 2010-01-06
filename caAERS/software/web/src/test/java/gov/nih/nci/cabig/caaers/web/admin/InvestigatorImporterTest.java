package gov.nih.nci.cabig.caaers.web.admin;

import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.api.impl.DefaultInvestigatorMigratorService;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;

/**
 * @author Sameer Sawant
 */

public class InvestigatorImporterTest extends WebTestCase {
	
	private InvestigatorImporter importer;
	private ImportCommand command;
	private DomainObjectValidator domainObjectValidator;
	private DefaultInvestigatorMigratorService investigatorMigratorService;
	private InvestigatorRepository investigatorRepository;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		importer = new InvestigatorImporter();
		command = new ImportCommand();
		domainObjectValidator = registerMockFor(DomainObjectValidator.class);
		investigatorMigratorService = registerMockFor(DefaultInvestigatorMigratorService.class);
		investigatorRepository = registerMockFor(InvestigatorRepository.class);
		
		importer.setDomainObjectValidator(domainObjectValidator);
		importer.setInvestigatorMigratorService(investigatorMigratorService);
		importer.setInvestigatorRepository(investigatorRepository);
	}
	
	public void setUpImportCommand(){
		DomainObjectImportOutcome<Investigator> importOutcome = new DomainObjectImportOutcome<Investigator>();
		Investigator investigator = Fixtures.createInvestigator("test inv");
		importOutcome.setImportedDomainObject(investigator);
		command.getImportableInvestigators().add(importOutcome);
	}
	
	public void testProcessEntities(){
		//TODO
	}
	
	public void testSave(){
		setUpImportCommand();
		investigatorRepository.save(command.getImportableInvestigators().get(0).getImportedDomainObject(), ResetPasswordController.getURL(request
                .getScheme(), request.getServerName(), request.getServerPort(), request
                .getContextPath()));
		replayMocks();
		importer.save(command, request);
		verifyMocks();
	}
}