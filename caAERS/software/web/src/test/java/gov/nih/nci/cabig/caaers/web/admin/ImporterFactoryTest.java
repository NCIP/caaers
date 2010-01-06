package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Sameer Work
 */

public class ImporterFactoryTest extends WebTestCase {

	private ImporterFactory factory;
	protected void setUp() throws Exception {
		super.setUp();
		
		factory = new ImporterFactory();
	}
	
	public void testCreateImporterInstance(){
		Importer importer = factory.createImporterInstance("study");
		assertTrue("Expected importer instance of type StudyImporter", importer instanceof StudyImporter);
		
		importer = factory.createImporterInstance("participant");
		assertTrue("Expected importer instance of type SubjectImporter", importer instanceof SubjectImporter);
		
		importer = factory.createImporterInstance("researchStaff");
		assertTrue("Expected importer instance of type ResearchStaffImporter", importer instanceof ResearchStaffImporter);
		
		importer = factory.createImporterInstance("investigator");
		assertTrue("Expected importer instance of type InvestigatorImporter", importer instanceof InvestigatorImporter);
	}
}