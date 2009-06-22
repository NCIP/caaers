package gov.nih.nci.cabig.caaers.web.selenium;

import java.util.Calendar;

public class CaaersSubjectTestCase extends CaaersSeleniumTestCase {
	String firstName = "catherine";
	String lastName = "jones";
	CaaersSubjectFlowFixtures subFixtures;

	public void setUp() throws Exception {
		super.setUp();
		subFixtures = new CaaersSubjectFlowFixtures(selenium, aw);
	}

	public void testSearchSubject() throws Exception {
		aw.login();
		searchSubjectDetails();
		subFixtures.editSubjectMedHistoryAddPanels();
		searchSubjectDetails();
		subFixtures.editSubjectMedHistoryRemovePanels();

	}

	public void searchSubjectDetails() throws InterruptedException {
		subFixtures.searchSubject(firstName);
		assertTrue("Search Subject failed for: " + firstName + " " + lastName,
				selenium.isTextPresent("regexpi:" + firstName)
						&& selenium.isTextPresent("regexpi:" + lastName));
		selenium.open(selenium
				.getAttribute("//a[text()='mrn-pt-test-001']@href"));
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		aw.clickNext("flow-next");
		selenium
				.click("//td[text()='University of Alabama at Birmingham']/parent::tr/descendant::input");
		aw.clickNext("flow-next");
	}

	public void testCreateSubject() throws Exception {
		checkLogin();
		subFixtures.createSubjectDetails(firstName, lastName);
		aw.clickNext("flow-next");
		subFixtures.createSubjectChooseStudy();
		aw.clickNext("flow-next");
		subFixtures.createSubjectMedHistory();
		aw.clickNext("flow-next");
		aw.clickNext("flow-next");
		assertTrue(
				"Create Subject failed for: " + firstName + " " + lastName,
				selenium
						.isTextPresent("You have successfully created a new subject."));
	}

	public void testAssignSubject() throws Exception {
		checkLogin();
		String fundingSponsorIdentifier = "N027D-test1-"
				+ Calendar.getInstance().getTimeInMillis();
		createCTCStudy(fundingSponsorIdentifier);
		// ---------------------------
		selenium.open("/caaers/pages/participant/search");
		selenium.click("firstlevelnav_searchParticipantController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium
				.click("//a[@id='secondlevelnav_assignParticipantController']/span");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.type("searchText", "mrn-pt-test-001");
		selenium.select("searchType", "label=Identifier");
		selenium.click("//input[@value='Search']");
		aw.waitForElementPresent("//td[text()='mrn-pt-test-001']/input");
		selenium.click("//td[text()='mrn-pt-test-001']/input");
		selenium.click("flow-next");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.type("searchText_", "n027d");
		selenium.select("searchType", "label=Identifier");
		selenium.click("//input[@value='Search']");
		aw.waitForElementPresent("//td[text()='" + fundingSponsorIdentifier
				+ "']/following-sibling::td/descendant::input[@type='radio']");
		selenium.click("//td[text()='" + fundingSponsorIdentifier
				+ "']/following-sibling::td/descendant::input[@type='radio']");
		// selenium.click("studySite246");
		selenium.type("studySubjectIdentifierInput", "ssi-4567");
		aw.clickNext("flow-next");
		// do subject medical details here.
		subFixtures.editSubjectMedHistoryAddPanels();
		aw.clickNext("flow-next");
		aw.clickNext("flow-prev");
		subFixtures.editSubjectMedHistoryRemovePanels();
		aw.clickNext("flow-next");
		aw.clickNext("flow-next");
		// ---------------------------
		assertTrue(
				"Assign Subject failed for: " + firstName + " " + lastName,
				selenium
						.isTextPresent("You have successfully updated the subject."));
	}

}
