package gov.nih.nci.cabig.caaers.web.selenium;

import java.util.Calendar;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class CaaersSubjectFlowFixtures extends SeleneseTestCase {
	Selenium selenium;
	AjaxWidgets aw;

	public CaaersSubjectFlowFixtures(Selenium selenium, AjaxWidgets aw) {
		super();
		this.selenium = selenium;
		this.aw = aw;
	}

	public void searchSubject(String firstName) throws InterruptedException {
		selenium.open("/caaers/pages/participant/search");
		selenium.click("firstlevelnav_searchParticipantController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.click("secondlevelnav_searchParticipantController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.type("prop1", "catherine");
		selenium.click("//button/descendant::td[text()[contains(.,'Search')]]");
		// selenium.click("//input[@value='Search']");
		aw.waitForElementPresent("//td[@title='Sort By First Name']");

	}

	public void createSubjectDetails(String firstName, String lastName,
			String participantIdentifier) throws InterruptedException {
		selenium.open("/caaers/pages/participant/search");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.click("firstlevelnav_searchParticipantController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium
				.click("//a[@id='secondlevelnav_createParticipantController']/span");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.select("organization",
				"label=University of Alabama at Birmingham ( AL002 )");
		selenium.type("participant.firstName", firstName);
		selenium.type("participant.lastName", lastName);
		selenium.type("participant.dateOfBirth.monthString", "01");
		selenium.type("participant.dateOfBirth.dayString", "01");
		selenium.type("participant.dateOfBirth.yearString", "2001");
		// aw.clickCalendar("participant.dateOfBirth-calbutton");
		selenium.select("participant.gender", "label=Male");
		selenium.select("participant.ethnicity", "label=Hispanic or Latino");
		selenium.select("participant.race", "label=Asian");
		selenium.type("participant.organizationIdentifiers[0].value",
				participantIdentifier);
		aw.typeAutosuggest(
				"participant.organizationIdentifiers[0].organization-input",
				"ctep",
				"participant.organizationIdentifiers[0].organization-choices");
		selenium
				.click("participant.organizationIdentifiers[0].primaryIndicator");
		selenium
				.click("participant.organizationIdentifiers[0].primaryIndicator");
		/*
		 * aw .addPanel("//input[@id='organization-button']",
		 * "//input[@id='participant.organizationIdentifiers[1].primaryIndicator']"
		 * ); aw .removePanel(
		 * "//input[@id='participant.organizationIdentifiers[1].primaryIndicator']/parent::td/parent::tr/descendant::img[@alt='delete']"
		 * , null);
		 */
	}

	public void createSubjectChooseStudy() throws InterruptedException {
		selenium.type("searchText", "n027d");
		selenium.select("searchType", "label=Identifier");
		// selenium.click("//input[@value='Search']");
		selenium.click("//button/descendant::td[text()[contains(.,'Search')]]");
		aw.waitForElementPresent("//td[@title='Sort By Primary ID']");
		selenium.click("study1");
		selenium.type("assignment.studySubjectIdentifier", "ssi-1234");
	}

	public void createSubjectMedHistory() throws InterruptedException {

		selenium.select("assignment.baselinePerformance",
				"label=2 = Symptomatic, in bed less than 50% of time");
		selenium.select("assignment.diseaseHistory.abstractStudyDisease",
				"label=Glioblastoma multiforme");
		// selenium.click("//option[@value='103']");
		aw.typeAutosuggest(
				"assignment.diseaseHistory.codedPrimaryDiseaseSite-input",
				"hands",
				"assignment.diseaseHistory.codedPrimaryDiseaseSite-choices");
		selenium.type("assignment.diseaseHistory.diagnosisDate.monthString",
				"01");
		selenium
				.type("assignment.diseaseHistory.diagnosisDate.dayString", "01");
		selenium.type("assignment.diseaseHistory.diagnosisDate.yearString",
				"2001");

		aw
				.addPanel(
						"metastatic-diseases-btn",
						"//input[@id='assignment.diseaseHistory.metastaticDiseaseSites[0].codedSite-input']");
		aw
				.typeAutosuggest(
						"assignment.diseaseHistory.metastaticDiseaseSites[0].codedSite-input",
						"spleen",
						"assignment.diseaseHistory.metastaticDiseaseSites[0].codedSite-choices");

		aw
				.addPanel(
						"metastatic-diseases-btn",
						"//input[@id='assignment.diseaseHistory.metastaticDiseaseSites[1].codedSite-input']");
		aw
				.typeAutosuggest(
						"assignment.diseaseHistory.metastaticDiseaseSites[1].codedSite-input",
						"bone marrow",
						"assignment.diseaseHistory.metastaticDiseaseSites[1].codedSite-choices");

		aw.addPanel("pre-cond-btn",
				"//div[@id='assignment.preExistingConditions[0]-row']");
		selenium.select(
				"assignment.preExistingConditions[0].preExistingCondition",
				"label=Autoimmune disorder");

		aw.addPanel("pre-cond-btn",
				"//div[@id='assignment.preExistingConditions[1]-row']");
		selenium.select(
				"assignment.preExistingConditions[1].preExistingCondition",
				"label=Bacterial infection");

		aw
				.addPanel("concomitantMedication-btn",
						"//div[@id='assignment.concomitantMedications[0].agentName-row']");
		selenium.type("assignment.concomitantMedications[0].agentName",
				"conmed 1");
		selenium
				.click("assignment.concomitantMedications[0].stillTakingMedications");
		selenium.type(
				"assignment.concomitantMedications[0].startDate.monthString",
				"01");
		selenium.type(
				"assignment.concomitantMedications[0].startDate.dayString",
				"01");
		selenium.type(
				"assignment.concomitantMedications[0].startDate.yearString",
				"2001");
		/*
		 * selenium.type(
		 * "assignment.concomitantMedications[0].endDate.monthString", "01");
		 * selenium
		 * .type("assignment.concomitantMedications[0].endDate.dayString",
		 * "01"); selenium.type(
		 * "assignment.concomitantMedications[0].endDate.yearString", "2001");
		 */
		// ------------------------
		aw
				.addPanel("concomitantMedication-btn",
						"//div[@id='assignment.concomitantMedications[1].agentName-row']");
		selenium.type("assignment.concomitantMedications[1].agentName",
				"conmed 2");

		aw.addPanel("priortherapy-btn",
				"//div[@id='assignment.priorTherapies[0].priorTherapy-row']");
		selenium.select("assignment.priorTherapies[0].priorTherapy",
				"label=Hormonal Therapy");
		selenium.type("assignment.priorTherapies[0].other", "test comments");
		selenium.type("assignment.priorTherapies[0].startDate.monthString",
				"01");
		selenium.type("assignment.priorTherapies[0].startDate.dayString", "01");
		selenium.type("assignment.priorTherapies[0].startDate.yearString",
				"2001");
		selenium.type("assignment.priorTherapies[0].endDate.monthString", "01");
		selenium.type("assignment.priorTherapies[0].endDate.dayString", "01");
		selenium
				.type("assignment.priorTherapies[0].endDate.yearString", "2001");
		/*
		 * aw.typeAutosuggest("priorTherapyAgents[0]-input", "hexalen",
		 * "priorTherapyAgents[0]-choices"); aw
		 * .addPanel("priortherapy[0].agent-btn",
		 * "//span[@id='assignment.priorTherapies[0].priorTherapyAgents[0].name']"
		 * );
		 */
	}

	public void editSubjectMedHistoryAddPanels() throws Exception {

		selenium.select("assignment.baselinePerformance",
				"label=4 = 100% bedridden");
		selenium
				.select(
						"//select[@id='assignment.diseaseHistory.abstractStudyDisease']",
						"index=1");
		aw.typeAutosuggest(
				"assignment.diseaseHistory.codedPrimaryDiseaseSite-input",
				"Cerebellum",
				"assignment.diseaseHistory.codedPrimaryDiseaseSite-choices");
		selenium.type("assignment.diseaseHistory.diagnosisDate.monthString",
				"04");
		selenium
				.type("assignment.diseaseHistory.diagnosisDate.dayString", "04");
		selenium.type("assignment.diseaseHistory.diagnosisDate.yearString",
				"2004");
		selenium.click("//button[@id='metastatic-diseases-btn']");
		aw
				.waitForElementPresent("assignment.diseaseHistory.metastaticDiseaseSites[2].codedSite-input");
		aw
				.typeAutosuggest(
						"assignment.diseaseHistory.metastaticDiseaseSites[2].codedSite-input",
						"Appendix",
						"assignment.diseaseHistory.metastaticDiseaseSites[2].codedSite-choices");

		/*
		 * aw .addLastPanel( "metastatic-diseases-btn",
		 * "//span[@id='assignment.diseaseHistory.metastaticDiseaseSites[?].codedSite.name']"
		 * );
		 */
		selenium.click("//button[@id='pre-cond-btn']");
		aw
				.waitForElementPresent("assignment.preExistingConditions[2].preExistingCondition");
		selenium.select(
				"assignment.preExistingConditions[2].preExistingCondition",
				"label=Coagulation disorder");

		/*
		 * aw .addLastPanel("pre-cond-btn",
		 * "//span[@id='assignment.preExistingConditions[?].preExistingCondition.text']"
		 * );
		 */

		selenium.click("//button[@id='concomitantMedication-btn']");
		aw
				.waitForElementPresent("assignment.concomitantMedications[2].agentName");
		selenium.type("assignment.concomitantMedications[2].agentName",
				"conmed-random-" + Calendar.getInstance().getTimeInMillis());
		/*
		 * aw.addLastPanel("concomitantMedication-btn",
		 * "//div[@id='assignment.concomitantMedications[?]']");
		 */
		selenium.click("//button[@id='priortherapy-btn']");
		aw.waitForElementPresent("assignment.priorTherapies[1].priorTherapy");
		selenium.select("assignment.priorTherapies[1].priorTherapy",
				"label=Drug and/or Immunotherapy");
		/*
		 * aw.addLastPanel("priortherapy-btn",
		 * "//div[@id='assignment.priorTherapies[?]']");
		 */
		selenium.type("assignment.priorTherapies[1].other", "test comments");
		selenium.type("assignment.priorTherapies[1].startDate.monthString",
				"01");
		selenium.type("assignment.priorTherapies[1].startDate.dayString", "01");
		selenium.type("assignment.priorTherapies[1].startDate.yearString",
				"2001");
		selenium.type("assignment.priorTherapies[1].endDate.monthString", "01");
		selenium.type("assignment.priorTherapies[1].endDate.dayString", "01");
		selenium
				.type("assignment.priorTherapies[1].endDate.yearString", "2001");
		/*
		 * aw.typeAutosuggest("priorTherapyAgents[0]-input", "Hexalen",
		 * "priorTherapyAgents[0]-choices"); aw
		 * .addLastPanel("priortherapy[0].agent-btn",
		 * "//span[@id='assignment.priorTherapies[0].priorTherapyAgents[?].name']"
		 * );
		 */// aw.clickNext("flow-next");
	}

	public void editSubjectMedHistoryRemovePanels() throws Exception {
		/*
		 * aw .removeLastPanel(
		 * "//span[@id='assignment.diseaseHistory.metastaticDiseaseSites[?].codedSite.name']/parent::td/parent::tr/descendant::a[@href='#anchorMetastaticDiseases']/img"
		 * , "^Do you really want to delete[\\s\\S]$");
		 */
		aw
				.removeLastPanel(
						"//span[@id='titleOf_assignment.diseaseHistory.metastaticDiseaseSites[?]']/parent::td/parent::tr/descendant::img",
						"^Do you really want to delete[\\s\\S]$");

		/*
		 * aw .removeLastPanel(
		 * "//span[@id='assignment.preExistingConditions[?].preExistingCondition.text']/parent::td/parent::tr/descendant::a[@href='#anchorPreExistingCondition']/img"
		 * , "^Do you really want to delete[\\s\\S]$");
		 */

		aw
				.removeLastPanel(
						"//span[@id='titleOf_assignment.preExistingConditions[?]']/parent::td/parent::tr/descendant::img",
						"^Do you really want to delete[\\s\\S]$");
		/*
		 * aw .removeLastPanel(
		 * "//div[@id='assignment.concomitantMedications[?]']/div/h3/table/tbody/tr/td[3]/a/img"
		 * , "^Do you really want to delete[\\s\\S]$");
		 */aw
				.removeLastPanel(
						"//span[@id='titleOf_assignment.concomitantMedications[?]']/parent::td/parent::tr/descendant::img[@alt='Delete']",
						"^Do you really want to delete[\\s\\S]$");

		/*
		 * aw .removeLastPanel(
		 * "//div[@id='assignment.priorTherapies[?]']/div/h3/table/tbody/tr/td[3]/a/img"
		 * , "^Do you really want to delete[\\s\\S]$");
		 */
		aw
		.removeLastPanel(
				"//div[@id='assignment.priorTherapies[?]']/descendant::img[@alt='Delete']",
				"^Do you really want to delete[\\s\\S]$");

	}
}
