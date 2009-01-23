package gov.nih.nci.cabig.caaers.web.selenium;
import com.thoughtworks.selenium.*;

import java.util.regex.Pattern;

public class CaaersAETestCase extends CaaersSeleniumTestCase {
	AjaxWidgets aw;
	CaaersAEFlowFixtures aeFixtures;
	
	public void setUp() throws Exception {
		super.setUp();
		aw=new AjaxWidgets(selenium);
		aeFixtures = new CaaersAEFlowFixtures(selenium, aw);
	}

	public void testExecuteCAEFlow() throws Exception {
		checkLogin();
		aeFixtures.executeCAEFlow("mrn-pt-0002", "N027D");
		assertTrue("CAE flow. Could not reach 'Enter AEs' tab in expedited AE flow", selenium.isElementPresent("//h2[text()='Enter AEs']"));
	}
	
	public void testExecuteExpeditedAEFlow() throws Exception {
		checkLogin();
		aeFixtures.executeExpeditedAEFlow("caaers/pages/ae/edit?aeReport=21&study=1&participant=2");
		assertTrue("Expedited AE flow did not finish successfully", selenium.isElementPresent("//h2[text()='Submit']"));
	}
	public void testSubmitReport() throws Exception {
		checkLogin();
		aeFixtures.submitReport("/caaers/pages/ae/edit?aeReport=21&study=1&participant=2");
		assertTrue("Report was not submitted successfully",selenium.isTextPresent("Submitted on"));
	}
}