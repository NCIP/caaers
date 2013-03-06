/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.selenium;


public class CaaersRulesSeleniumTestCase extends CaaersSeleniumTestCase {
	
		public void testUploadRules() throws Exception {
			waitForCaaersStartup();
			uploadRules(); 	
	}
	
}
