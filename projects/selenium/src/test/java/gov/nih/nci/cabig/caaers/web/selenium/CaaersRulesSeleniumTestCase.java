package gov.nih.nci.cabig.caaers.web.selenium;


public class CaaersRulesSeleniumTestCase extends CaaersSeleniumTestCase {
	
		public void testUploadRules() throws Exception {
			waitForCaaersStartup();
			uploadRules(); 	
	}
	
}
