package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.web.WebTestCase;

/**
 * @author Sameer Sawant
 */
public class MeddraImporterTest extends WebTestCase{
	
	private MeddraImporter importer;
	private ImportCommand command;
	
	protected void setUp() throws Exception {
		super.setUp();
	
		importer = new MeddraImporter();
		command = new ImportCommand();
	}
	
	public void testProcessEntities(){
		//TODO
	}
	
	public void testSave(){
		//TODO
	}
}