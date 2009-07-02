package gov.nih.nci.cabig.caaers.web.selenium;

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
		assertTrue("Could not create new reporting period in CAE flow: ",
				selenium.isElementPresent("//h2[text()='Adverse Events']"));

	}

	public void testExecuteCAEFlow() throws Exception {
		checkLogin();
		String rpFrom = "01/20/09";
		String rpTo = "01/21/09";
		aeFixtures.executeCAEFlow("mrn-pt-0002", "N027D", rpFrom, rpTo);
		assertTrue("Could not edit AEs in reporting period in CAE flow: ",
				selenium.isElementPresent("//h2[text()='Adverse Events']"));

	}

	public void testExecuteExpeditedAEFlow() throws Exception {
		checkLogin();
		aeFixtures.executeExpeditedAEFlow("caaers/pages/ae/edit?aeReport=1");
		assertTrue("Expedited AE flow did not finish successfully", selenium
				.getText("//td[@class='completion-messages']").contains("Yes"));
	}

	public void testSubmitReport() throws Exception {
		checkLogin();
		aeFixtures
				.submitReport("caaers/pages/ae/submitReport?from=list&aeReport=1&reportId=1");

		assertTrue(
				"Report was not submitted successfully",
				selenium
						.isElementPresent("//div[text()='NCI AdEERS Report [5 day CTEP]']/parent::td/following-sibling::td/descendant::a[text()='Amend']"));
	}
}