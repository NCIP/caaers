package gov.nih.nci.cabig.caaers.web.selenium;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class CaaersAEFlowFixtures extends SeleneseTestCase {
	Selenium selenium;
	AjaxWidgets aw;

	public CaaersAEFlowFixtures(Selenium selenium, AjaxWidgets aw) {
		super();
		this.selenium = selenium;
		this.aw = aw;

	}

	public void createRPFlow(String participant, String studyId, String rpFrom,
			String rpTo) throws Exception {
		selenium.open("/caaers/pages/task");
		selenium.click("firstlevelnav_listAdverseEventsController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.click("secondlevelnav_captureAdverseEventController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		createCourse(participant, studyId, rpFrom, rpTo);
		aw.clickNext("flow-next");
		aw.typeAutosuggest("termCode-input", "vasculitis", "termCode-choices");
		int i = aw.computeLatestElementIndex(
				"//div[@id='adverseEvents[?].grade-row']", false);
		selenium.click("addSingleTermBtn");
		aw.waitForElementPresent("//div[@id='adverseEvents[" + i
				+ "].grade-row']");

		// ===============
		selenium.click("adverseEvents[" + i + "].grade-radio-3");
		selenium.type("adverseEvents[" + i + "].startDate", "01/01/2009");
		selenium.select("adverseEvents[" + i + "].attributionSummary",
				"label=Possible");
		selenium
				.select("adverseEvents[" + i + "].hospitalization", "label=Yes");
		selenium.select("adverseEvents[" + i + "].expected", "label=No");

		// ===============

		aw.clickNext("flow-next");
		selenium.click("//a[@id='manualselect2']");
		aw
				.confirmOK("Are you sure you want to bypass the caAERS-based report selection above and instead manually select from the list of all reports defined for this study[\\s\\S]$");
		selenium
				.click("//label[text()[contains(.,'5 day')]]/parent::td/input[@type='checkbox']");
		selenium.click("//tr[@id='ae-section-0']/descendant::input");
		aw
				.clickNext("//button/descendant::*[text()[contains(.,'Continue Expedited Reporting')]]");
		selenium.select("staff", "label=John Fiveash");
		selenium.select("physician", "label=John Fiveash");
		aw.clickNext("flow-next");

	}

	public void createCourse(String participant, String studyId, String rpFrom,
			String rpTo) throws InterruptedException {
		aw.typeAutosuggest("participant-input", participant,
				"participant-choices");
		aw.typeAutosuggest("study-input", studyId, "study-choices");
		aw.waitForElementPresent("//option[@value='-1']");
		selenium.select("course-input", "label=Create New");
		aw.waitForElementPresent("//h2[text()='Course/Cycle Information']");
		selenium.type("assignment.startDateOfFirstCourse", "01/01/1999");
		selenium.type("reportingPeriod.startDate", rpFrom);
		selenium.type("reportingPeriod.endDate", rpTo);
		selenium.select("reportingPeriod.epoch", "label=Treatment");
		selenium.type("reportingPeriod.cycleNumber", "1");
		selenium.click("//td[text()[contains(.,'A4')]]/input");
		selenium.click("flow-update");
		aw.waitForElementNotPresent("//h2[text()='Course/Cycle Information']");
		assertTrue("Evaluation period was not created successfully", selenium
				.isTextPresent("Course/Cycle created successfully"));
	}

	/**
	 * @param reportingPeriod
	 * @return truncates the mm/dd/yyyy to mm/dd/yy format
	 */
	public String truncateReportingPeriod(String reportingPeriod) {
		return reportingPeriod.substring(0, 6)
				+ reportingPeriod.substring(8, 10);
	}

	public void executeCAEFlow(String participant, String studyId,
			String rpFrom, String rpTo) throws Exception {
		selenium.open("/caaers/pages/task");
		selenium.click("firstlevelnav_listAdverseEventsController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		selenium.click("secondlevelnav_listAdverseEventsController");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		aw.typeAutosuggest("participant-input", participant,
				"participant-choices");
		aw.typeAutosuggest("study-input", studyId, "study-choices");
		aw.clickNext("flow-next");
		/*
		 * selenium.select("adverseEventReportingPeriod", "label=" +
		 * truncateReportingPeriod(rpFrom) + " - " +
		 * truncateReportingPeriod(rpTo));
		 */
		aw.waitForElementPresent("//td[text()[contains(.,'" + rpFrom + "')]]");
		/*
		 * selenium .click( "//td[text()[contains(.,'" + rpFrom +
		 * "')]]/following-sibling::td/select/option[@value='editReportingPeriod']"
		 * );
		 */
		selenium.select("actions-1", "label=Edit Adverse Events");
		aw
				.confirmOK("^Are you sure you want to take the action - Edit Adverse Events$");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		aw.waitForElementPresent("//span[text()[contains(.,'Vasculitis')]]");
		selenium
				.click("//span[text()[contains(.,'Vasculitis')]]/parent::td/following-sibling::td/a/img[@alt='Delete']");
		aw.confirmOK("^Are you sure you want to delete this[\\s\\S]$");
		selenium.click("addMultiTermBtn");
		aw
				.waitForElementPresent("//h2[text()[contains(.,'Select Adverse Event Terms')]]");
		selenium.click("//a[@title='GASTROINTESTINAL']");
		aw.waitForElementPresent("//a[text()[contains(.,'Constipation')]]");
		selenium.click("//a[text()[contains(.,'Constipation')]]");
		selenium.click("//a[text()[contains(.,'Esophagitis')]]");
		selenium
				.click("//button/descendant::*[text()[contains(.,'Add Terms')]]");
		aw.waitForElementNotPresent("//a[text()[contains(.,'Constipation')]]");
		// ---------------------------------------------------------
		int i = getAEIndex("Constipation");
		// ---------------------------------------------------------
		selenium.click("adverseEvents[" + i + "].grade-radio-3");
		selenium.type("adverseEvents[" + i + "].startDate", "01/01/2009");
		selenium.select("adverseEvents[" + i + "].attributionSummary",
				"label=Possible");
		selenium
				.select("adverseEvents[" + i + "].hospitalization", "label=Yes");
		selenium.select("adverseEvents[" + i + "].expected", "label=No");
		i = getAEIndex("Esophagitis");
		selenium.click("adverseEvents[" + i + "].grade-radio-1");
		aw.clickNext("flow-next");
		selenium
				.click("//tr[@id='ae-section-0']/descendant::input[@type='checkbox']");
		selenium
				.click("//tr[@id='ae-section-1']/descendant::input[@type='checkbox']");
		selenium
				.click("//tr[@id='ae-section-0']/descendant::input[@type='radio']");
		aw
				.clickNext("//button/descendant::*[text()[contains(.,'Continue Expedited Reporting')]]");
		aw.clickNext("flow-next");
	}

	public int getAEIndex(String aeName) {
		// span[text()[contains(.,'Vasculitis')]]/ancestor::div[@class='header']/following-sibling::div/descendant::*[@id[contains(.,'detailsForOther-row')]]
		String divId = selenium
				.getAttribute("//span[text()[contains(.,'"
						+ aeName
						+ "')]]/ancestor::div[@class='header']/following-sibling::div/descendant::*[@id[contains(.,'detailsForOther-row')]]@id");
		String parts[] = divId.split("\\[");
		return Integer.parseInt(parts[1].substring(0, 1));
	}

	public void executeExpeditedAEFlow(String relURL)
			throws InterruptedException {
		selenium.open(relURL);
		aw.clickNext("flow-next");
		popExpAEsEnterAEs();
		aw.clickNext("flow-next");
		popExpAEsEvent();
		aw.clickNext("flow-next");
		popExpAEsCourseAgent();
		aw.clickNext("flow-next");
		popExpAEsStudyInterventions();
		aw.clickNext("flow-next");
		popExpAEsSubjectDetails();
		aw.clickNext("flow-next");
		popExpAEsOtherCauses();
		aw.clickNext("flow-next");
		popExpAEsLabs();
		aw.clickNext("flow-next");
		popExpAEsAttribution();
		aw.clickNext("flow-next");
		popExpAEsAttachments();
		aw.clickNext("flow-next");

	}

	public void submitReport(String relURL) throws InterruptedException {
		selenium.open(relURL);
		aw
				.waitForElementPresent("//label[@for='aeReport.reports[0].lastVersion.submitter.firstName']");
		aw.clickNext("flow-next");

	}

	public void popExpAEsLabs() {
	}

	public void popExpAEsAttachments() {
	}

	public void popExpAEsAttribution() {

		select("disease", "5: Definite");
		select("courseAgent", "5: Definite");
		select("conMed", "5: Definite");
		select("other", "5: Definite");
		// selenium.select("attributionMap[disease][0][0]","label=5: Definite");
	}

	public void select(String cause, String option) {
		// select[@id='attributionMap[courseAgent][0][0]']
		boolean elemExists = true;
		int i = 0;
		String s1 = "attributionMap[";
		String s2 = "][";
		String s3 = "][0]";
		String s4 = "label=";
		while (elemExists) {
			if (selenium.isElementPresent("//select[@id='" + s1 + cause + s2
					+ i + s3 + "']")) {
				selenium.select(s1 + cause + s2 + i + s3, s4 + option);
				i++;
			} else
				elemExists = false;
		}
	}

	public void popExpAEsOtherCauses() throws InterruptedException {
		selenium.click("add-otherCause-button");
		aw.waitForElementPresent("aeReport.otherCauses[0].text");
		selenium.type("aeReport.otherCauses[0].text", "other cause 1");

	}

	public void popExpAEsSubjectDetails() throws InterruptedException {
		selenium.type("aeReport.participantHistory.height.quantity", "100");
		selenium.type("aeReport.participantHistory.weight.quantity", "100");
		selenium.select("aeReport.diseaseHistory.ctepStudyDisease",
				"label=Glioblastoma multiforme");
		// aeReport.diseaseHistory.codedPrimaryDiseaseSite-choices
		aw.typeAutosuggest(
				"aeReport.diseaseHistory.codedPrimaryDiseaseSite-input",
				"Bone marrow",
				"aeReport.diseaseHistory.codedPrimaryDiseaseSite-choices");
		selenium.click("metastatic-diseases-btn");
		aw
				.waitForElementPresent("aeReport.diseaseHistory.metastaticDiseaseSites[0].codedSite-input");
		aw
				.typeAutosuggest(
						"aeReport.diseaseHistory.metastaticDiseaseSites[0].codedSite-input",
						"back",
						"aeReport.diseaseHistory.metastaticDiseaseSites[0].codedSite-choices");

		selenium.click("pre-cond-btn");
		aw
				.waitForElementPresent("aeReport.saeReportPreExistingConditions[0].preExistingCondition");
		selenium
				.select(
						"aeReport.saeReportPreExistingConditions[0].preExistingCondition",
						"label=Arrhythmia");
		selenium.click("concomitantMedication-btn");
		aw
				.waitForElementPresent("aeReport.concomitantMedications[0].agentName");
		selenium.type("aeReport.concomitantMedications[0].agentName",
				"conmed 1");
		selenium.click("priortherapy-btn");
		aw
				.waitForElementPresent("aeReport.saeReportPriorTherapies[0].priorTherapy");
		selenium.select("aeReport.saeReportPriorTherapies[0].priorTherapy",
				"label=Gene Transfer");
		selenium.type("aeReport.saeReportPriorTherapies[0].other",
				"Prior therapy comments");
		selenium.type(
				"aeReport.saeReportPriorTherapies[0].startDate.monthString",
				"01");
		selenium
				.type(
						"aeReport.saeReportPriorTherapies[0].startDate.dayString",
						"01");
		selenium.type(
				"aeReport.saeReportPriorTherapies[0].startDate.yearString",
				"2009");
		selenium
				.type(
						"aeReport.saeReportPriorTherapies[0].endDate.monthString",
						"01");
		selenium.type("aeReport.saeReportPriorTherapies[0].endDate.dayString",
				"02");
		selenium.type("aeReport.saeReportPriorTherapies[0].endDate.yearString",
				"2009");

	}

	public void popExpAEsEvent() throws InterruptedException {
		selenium.type("aeReport.responseDescription.eventDescription",
				"test description");
		selenium.select("aeReport.responseDescription.presentStatus",
				"label=Intervention for AE continues");
		selenium.select("aeReport.responseDescription.retreated", "label=Yes");
	}

	public void popExpAEsCourseAgent() throws InterruptedException {
		// Thread.sleep(5000);
		selenium.select("aeReport.treatmentInformation.treatmentAssignment",
				"label=A3");
		selenium.type("aeReport.treatmentInformation.firstCourseDate",
				"01/01/1999");
		selenium.type("aeReport.treatmentInformation.adverseEventCourse.date",
				"12/23/2008");
		selenium.type(
				"aeReport.treatmentInformation.adverseEventCourse.number", "1");
		selenium.type("aeReport.treatmentInformation.totalCourses", "1");
	}

	public void popExpAEsStudyInterventions() throws InterruptedException {
		selenium
				.select(
						"aeReport.treatmentInformation.investigationalAgentAdministered",
						"label=Yes");
		selenium.click("btn-add-agent");
		aw
				.waitForElementPresent("aeReport.treatmentInformation.courseAgents[0].dose.amount");
		selenium.select(
				"aeReport.treatmentInformation.courseAgents[0].studyAgent",
				"label=CCI-779 (temsirolimus, Torisel)");
		selenium.type(
				"aeReport.treatmentInformation.courseAgents[0].dose.amount",
				"10");
		selenium.select(
				"aeReport.treatmentInformation.courseAgents[0].dose.units",
				"label=billion pfu");
		selenium
				.type(
						"aeReport.treatmentInformation.courseAgents[0].lastAdministeredDate",
						"01/01/2009");
		selenium.type("aeReport.treatmentInformation.courseAgents[0].comments",
				"test comments");
	}

	public void popExpAEsEnterAEs() {
		selenium.type("aeReport.adverseEvents[0].startDate", "12/23/2008");
	}
}
