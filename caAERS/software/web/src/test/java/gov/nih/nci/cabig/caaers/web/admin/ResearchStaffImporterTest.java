package gov.nih.nci.cabig.caaers.web.admin;

import java.util.ArrayList;

import org.easymock.classextension.EasyMock;

import gov.nih.nci.cabig.caaers.api.ResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.api.impl.DefaultResearchStaffMigratorService;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;

/**
 * @author Sameer Sawant
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