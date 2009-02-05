package gov.nih.nci.cabig.caaers.web.selenium;

import junit.framework.TestCase;

public class CaaersAETestCase extends CaaersSeleniumTestCase {

	AjaxWidgets aw;
	CaaersAEFlowFixtures aeFixtures;

	public void setUp() throws Exception {
		super.setUp();
		aw = new AjaxWidgets(selenium);
		aeFixtures = new CaaersAEFlowFixtures(selenium, aw);
	}

	public void testCreateRPFlow() throws Exception {
		checkLogin();
		String rpFrom = "01/20/2009";
		String rpTo = "01/21/2009";
		aeFixtures.createRPFlow("mrn-pt-0002", "N027D", rpFrom, rpTo);
		assertTrue(
				"Could not create new reporting period in CAE flow: ",
				selenium
						.isElementPresent("//a[contains(.,'"
								+ aeFixtures.truncateReportingPeriod(rpFrom)
								+ "')]/parent::*/parent::*/following-sibling::*/descendant::a[contains(.,'Report')]"));

	}

	public void testExecuteCAEFlow() throws Exception {
		checkLogin();
		String rpFrom = "01/20/2009";
		String rpTo = "01/21/2009";
		aeFixtures.executeCAEFlow("mrn-pt-0002", "N027D", rpFrom, rpTo);
		assertTrue(
				"CAE flow. Could not reach 'Enter AEs' tab in expedited AE flow",
				selenium.isElementPresent("//h2[text()='Enter AEs']"));
	}

	public void testExecuteExpeditedAEFlow() throws Exception {
		checkLogin();
		aeFixtures
				.executeExpeditedAEFlow("caaers/pages/ae/edit?aeReport=1");
		/*aeFixtures
		.executeExpeditedAEFlow("caaers/pages/ae/edit?aeReport=21&study=1&participant=2");*/
		assertTrue("Expedited AE flow did not finish successfully", selenium
				.isElementPresent("//h2[text()='Submit']"));
	}

	public void testSubmitReport() throws Exception {
		checkLogin();
		aeFixtures
				.submitReport("caaers/pages/ae/submitReport?aeReport=21&reportId=21");

		assertTrue(
				"Report was not submitted successfully",
				selenium
						.isElementPresent("//div[text()='NCI AdEERS Report [5 day CTEP]']/parent::td/following-sibling::td/descendant::a[text()='Amend']"));
	}
}