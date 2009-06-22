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
		selenium.click("//input[@value='Search']");
		aw.waitForElementPresent("//td[@title='Sort By First Name']");

	}

	public void createSubjectDetails(String firstName, String lastName)
			throws InterruptedException {
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
				"mrn-pt-test-001");
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
		selenium.click("//input[@value='Search']");
		aw.waitForElementPresent("//td[@title='Sort By Primary ID']");
		selenium.click("study1");
		selenium.type("assignment.studySubjectIdentifier", "ssi-1234");
	}

	public void createSubjectMedHistory() throws InterruptedException {

		selenium.select("assignment.baselinePerformance",
				"label=2 = Symptomatic, in bed less than 50% of time");
		selenium.select("assignment.diseaseHistory.abstractStudyDisease",
				"label=Synovial sarcoma");
		//selenium.click("//option[@value='103']");
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
		aw.typeAutosuggest("metastaticDiseaseSite-input", "spleen",
				"metastaticDiseaseSite-choices");
		aw
				.addPanel(
						"metastatic-diseases-btn",
						"//span[@id='assignment.diseaseHistory.metastaticDiseaseSites[0].codedSite.name']");
		aw.typeAutosuggest("metastaticDiseaseSite-input", "bone marrow",
				"metastaticDiseaseSite-choices");
		aw
				.addPanel(
						"metastatic-diseases-btn",
						"//span[@id='assignment.diseaseHistory.metastaticDiseaseSites[1].codedSite.name']");

		selenium.select("preExistingCondition", "label=Autoimmune disorder");
		aw
				.addPanel("pre-cond-btn",
						"//span[@id='assignment.preExistingConditions[0].preExistingCondition.text']");
		selenium.select("preExistingCondition", "label=Bacterial infection");
		aw
				.addPanel("pre-cond-btn",
						"//span[@id='assignment.preExistingConditions[1].preExistingCondition.text']");

		selenium.type("concomitantMedication", "conmed 1");
		aw.addPanel("concomitantMedication-btn",
				"//div[@id='assignment.concomitantMedications[0]']");
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
		selenium.type(
				"assignment.concomitantMedications[0].endDate.monthString",
				"01");
		selenium.type("assignment.concomitantMedications[0].endDate.dayString",
				"01");
		selenium.type(
				"assignment.concomitantMedications[0].endDate.yearString",
				"2001");

		selenium.type("concomitantMedication", "conmed 2");
		aw.addPanel("concomitantMedication-btn",
				"//div[@id='assignment.concomitantMedications[1]']");

		selenium.select("priorTherapy", "label=Hormonal Therapy");
		aw.addPanel("priortherapy-btn",
				"//div[@id='assignment.priorTherapies[0]']");

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
		aw.typeAutosuggest("priorTherapyAgents[0]-input", "hexalen",
				"priorTherapyAgents[0]-choices");
		aw
				.addPanel("priortherapy[0].agent-btn",
						"//span[@id='assignment.priorTherapies[0].priorTherapyAgents[0].name']");

	}

	public void editSubjectMedHistoryAddPanels() throws Exception {

		selenium.select("assignment.baselinePerformance",
				"label=4 = 100% bedridden");
		selenium.select("//select[@id='assignment.diseaseHistory.abstractStudyDisease']", "index=1");
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
		aw.typeAutosuggest("metastaticDiseaseSite-input", "Appendix",
				"metastaticDiseaseSite-choices");
		aw
				.addLastPanel(
						"metastatic-diseases-btn",
						"//span[@id='assignment.diseaseHistory.metastaticDiseaseSites[?].codedSite.name']");

		selenium.select("preExistingCondition", "label=Coagulation disorder");
		aw
				.addLastPanel("pre-cond-btn",
						"//span[@id='assignment.preExistingConditions[?].preExistingCondition.text']");

		selenium.type("concomitantMedication", "conmed-random-"
				+ Calendar.getInstance().getTimeInMillis());
		aw.addLastPanel("concomitantMedication-btn",
				"//div[@id='assignment.concomitantMedications[?]']");

		selenium.select("priorTherapy", "label=Drug and/or Immunotherapy");
		aw.addLastPanel("priortherapy-btn",
				"//div[@id='assignment.priorTherapies[?]']");

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
		aw.typeAutosuggest("priorTherapyAgents[0]-input", "Hexalen",
				"priorTherapyAgents[0]-choices");
		aw
				.addLastPanel("priortherapy[0].agent-btn",
						"//span[@id='assignment.priorTherapies[0].priorTherapyAgents[?].name']");

	//	aw.clickNext("flow-next");


			}

	public void editSubjectMedHistoryRemovePanels() throws Exception {
		aw
				.removeLastPanel(
						"//span[@id='assignment.diseaseHistory.metastaticDiseaseSites[?].codedSite.name']/parent::td/parent::tr/descendant::a[@href='#anchorMetastaticDiseases']/img",
						"^Do you really want to delete[\\s\\S]$");
		aw
		.removeLastPanel(
				"//span[@id='assignment.preExistingConditions[?].preExistingCondition.text']/parent::td/parent::tr/descendant::a[@href='#anchorPreExistingCondition']/img",
				"^Do you really want to delete[\\s\\S]$");
		aw
		.removeLastPanel(
				"//div[@id='assignment.concomitantMedications[?]']/div/h3/table/tbody/tr/td[3]/a/img",
				"^Do you really want to delete[\\s\\S]$");
		
		aw
		.removeLastPanel(
				"//div[@id='assignment.priorTherapies[?]']/div/h3/table/tbody/tr/td[3]/a/img",
				"^Do you really want to delete[\\s\\S]$");
		
		
	}
}
