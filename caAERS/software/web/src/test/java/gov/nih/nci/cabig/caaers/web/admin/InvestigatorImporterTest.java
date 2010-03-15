package gov.nih.nci.cabig.caaers.web.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.easymock.classextension.EasyMock;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import edu.nwu.bioinformatics.commons.ResourceRetriever;

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
	private File investigatorFile;
	
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

		//investigatorFile = getResources("classpath*:gov/nih/nci/cabig/caaers/web/admin/testdata/ImportInvestigator.xml")[0].getFile();
	}
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }
	
	public void setUpImportCommand(){
		DomainObjectImportOutcome<Investigator> importOutcome = new DomainObjectImportOutcome<Investigator>();
		Investigator investigator = Fixtures.createInvestigator("test inv");
		importOutcome.setImportedDomainObject(investigator);
		command.getImportableInvestigators().add(importOutcome);
	}
	
	public void testProcessEntities(){
		//TODO
		//assertTrue(investigatorFile.exists());
		//importer.processEntities(investigatorFile, command);
	}
	
	public void testSave(){
		//setUpImportCommand();
		//investigatorRepository.save(command.getImportableInvestigators().get(0).getImportedDomainObject(), ResetPasswordController.getURL(request
        //       .getScheme(), request.getServerName(), request.getServerPort(), request
        //        .getContextPath()));
		//replayMocks();
		//importer.save(command, request);
		//verifyMocks();
	}
	
	private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
        if (testDataStream == null) {
                throw new NullPointerException(
                        "Test data resource " + ResourceRetriever.getResourceName(getClass().getPackage(), testDataFileName)
                                + " not found and fallback call to handleTestDataFileNotFound() did not provide a substitute.");
        }
        return testDataStream;
    }
}