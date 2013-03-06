/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
