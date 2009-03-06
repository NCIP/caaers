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
		aw.typeAutosuggest("participant-input", participant,
				"participant-choices");
		// selenium.type("participant-input", participant);
		// selenium.click("//div[@id='participant-choices']/ul/li[1]");
		aw.typeAutosuggest("study-input", studyId, "study-choices");
		// selenium.type("study-input", "n0");
		// selenium.click("//div[@id='study-choices']/ul/li");
		aw.clickNext("flow-next");
		selenium.select("adverseEventReportingPeriod", "label=Create New");
		selenium.click("//option[@value='-1']");
		aw
				.waitForElementPresent("//label[@for='reportingPeriod.treatmentAssignment.description']");
		// selenium.selectFrame("window_1233345345579_content");
		selenium.type("reportingPeriod.startDate", rpFrom);
		selenium.type("reportingPeriod.endDate", rpTo);
		selenium.select("reportingPeriod.epoch", "label=Treatment");
		selenium.type("reportingPeriod.description", "test description");
		selenium.type("reportingPeriod.cycleNumber", "1");
		selenium.select("reportingPeriod.treatmentAssignment", "label=A3");
		aw
				.clickNext("//form[@action[contains(.,'/caaers/pages/ae/createReportingPeriod?')]]/descendant::input[@value='Save']");
		assertTrue(
				"Evaluation period was not created successfully",
				selenium
						.isElementPresent("//b[text()='Evaluation period details saved successfully']"));
		selenium.click("ok-id");
		Thread.sleep(5000);

		// selenium.selectWindow("name=null");
		selenium
				.select(
						"adverseEvents[0].grade",
						"label=2: Symptomatic and medical intervention or minor cauterization indicated");
		selenium.type("adverseEvents[0].detailsForOther", "test-verbatim");
		selenium.select("adverseEvents[0].attributionSummary",
				"label=Unrelated");
		selenium.select("adverseEvents[0].hospitalization", "label=Yes");
		selenium.select("adverseEvents[0].expected", "label=No");
		selenium
				.select("adverseEvents[0].attributionSummary", "label=Definite");
		aw
				.typeAutosuggest("termCode-input", "Pneumothorax",
						"termCode-choices");
		// selenium.type("termCode-input", "hypothermia");
		// selenium.click("//div[@id='termCode-choices']/ul/li/strong");
		aw
				.addLastPanel("addSingleTermBtn",
						"//span[@id='adverseEvents[?].adverseEventTerm.universalTerm']");
		// selenium.click("addSingleTermBtn");
		selenium.select("adverseEvents[2].grade", "label=5: Death");
		selenium
				.select("adverseEvents[2].attributionSummary", "label=Unlikely");
		selenium
				.select("adverseEvents[2].attributionSummary", "label=Definite");
		selenium.select("adverseEvents[2].hospitalization", "label=Yes");
		selenium.select("adverseEvents[2].expected", "label=No");
		aw.clickNext("flow-next");
		// selenium.click("flow-next");
		selenium.click("//img[@alt='Continue']");
		aw.waitForElementPresent("//input[@id='create-new-report']");
		aw.clickNext("create-new-report");
		selenium.type("aeReport.reporter.firstName", "rs-fname");
		selenium.type("aeReport.reporter.lastName", "rs-lname");
		selenium.type("aeReport.reporter.contactMechanisms[e-mail]",
				"caaers.app@gmail.com");
		selenium.type("aeReport.reporter.contactMechanisms[phone]",
				"0000000000");
		selenium.type("aeReport.reporter.contactMechanisms[fax]", "0000000000");
		selenium.click("physician-same");
		aw.clickNext("flow-next");
		selenium
				.click("//a[@id='secondlevelnav_listAdverseEventsController']/span");
		selenium.waitForPageToLoad(CaaersSeleniumTestCase.waitTime);
		aw.typeAutosuggest("participant-input", participant,
				"participant-choices");
		aw.typeAutosuggest("study-input", studyId, "study-choices");
		aw.clickNext("flow-next");
		String truncRPFrom = truncateReportingPeriod(rpFrom);
		selenium.click("//a[contains(.,'" + truncRPFrom
				+ "')]/parent::td/parent::tr/descendant::img");
		// selenium.click("//img[@onclick=\"javascript: if ($('table1').style.display == 'none') { $('table1').show(); this.src = this.src.replace('right','down');} else { $('table1').hide(); this.src = this.src.replace('down','right');}\"]");
		aw
				.waitForElementPresent("//a[contains(.,'"
						+ truncRPFrom
						+ "')]/parent::td/parent::tr/following-sibling::*/descendant::span[text()='All Adverse Events for this Reporting Period']");
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
			String rpFrom, String rpTo) throws InterruptedException {
		selenium.open("/caaers/pages/ae/list");
		selenium.click("firstlevelnav_listAdverseEventsController");
		aw
				.waitForElementPresent("//a[@id='secondlevelnav_captureAdverseEventController']");
		selenium.open("/caaers/pages/ae/captureRoutine");
		aw.waitForElementPresent("//input[@id='study-input']");
		aw.typeAutosuggest("participant-input", participant,
				"participant-choices");
		aw.typeAutosuggest("study-input", studyId, "study-choices");
		aw.clickNext("flow-next");
		selenium.select("adverseEventReportingPeriod", "label="
				+ truncateReportingPeriod(rpFrom) + " - "
				+ truncateReportingPeriod(rpTo));
		aw.waitForElementPresent("//div[@id='solicitatedID']");
		selenium.select("adverseEvents[0].grade", "label=5: Death");
		selenium
				.select("adverseEvents[0].attributionSummary", "label=Definite");
		selenium.select("adverseEvents[0].hospitalization", "label=Yes");
		selenium.select("adverseEvents[0].expected", "label=No");
		aw.clickNext("flow-next");
		selenium.click("//img[@alt='Continue']");
		aw.waitForElementPresent("//div[@id='popupId']");
		aw.clickNext("edit-report");
		// selenium.click("edit-report");
		selenium.type("aeReport.reporter.firstName", "rs-fname");
		selenium.type("aeReport.reporter.lastName", "rs-lname");
		selenium.type("aeReport.reporter.contactMechanisms[e-mail]",
				"caaers.app@gmail.com");
		selenium.type("aeReport.reporter.contactMechanisms[phone]",
				"0000000000");
		selenium.type("aeReport.reporter.contactMechanisms[fax]", "0000000000");
		selenium.click("physician-same");
		aw.clickNext("flow-next");

	}

	public void executeExpeditedAEFlow(String relURL)
			throws InterruptedException {
		selenium.open(relURL);
		aw.clickNext("flow-next");
		popExpAEsEnterAEs();
		aw.clickNext("flow-next");
		popExpAEsCourseAgent();
		aw.clickNext("flow-next");
		popExpAEsEvent();
		aw.clickNext("flow-next");
		popExpAEsSubjectDetails();
		aw.clickNext("flow-next");
		popExpAEsOtherCauses();
		aw.clickNext("flow-next");
		popExpAEsRadiation();
		aw.clickNext("flow-next");
		popExpAEsSurgery();
		aw.clickNext("flow-next");
		popExpAEsDevice();
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
		// selenium.open("caaers/pages/ae/submitReport?from=list&aeReport=21&reportId=21");
		// aw.clickNext("link=13. Submit");
		// aw.clickNext("//div[text()='NCI AdEERS Report [5 day CTEP]'] /parent::td/following-sibling::td/descendant::a[text()='Amend']");
		aw
				.waitForElementPresent("//select[@id='aeReport.reports[1].lastVersion.physicianSignoff']");
		selenium.select("aeReport.reports[1].lastVersion.physicianSignoff",
				"label=Yes");
		selenium.click("reporter_same_as_submitter");
		aw.clickNext("flow-next");
		aw.clickNext("flow-next");
		// aw.clickNext("link=Submit");
		// Thread.sleep(1500);
		//selenium.open(relURL);
		aw.clickNext("link=13. Submit");

	}

	public void popExpAEsRadiation() {
	}

	public void popExpAEsSurgery() {
	}

	public void popExpAEsDevice() {
	}

	public void popExpAEsLabs() {
	}

	public void popExpAEsAttachments() {
	}

	public void popExpAEsAttribution() {
		
		select("disease","label=5: Definite");
		
		selenium.select("attributionMap[disease][0][0]", "label=5: Definite");
		selenium.select("attributionMap[disease][1][0]", "label=5: Definite");
		selenium.select("attributionMap[disease][2][0]", "label=5: Definite");
		
		selenium.select("attributionMap[courseAgent][0][0]",
				"label=5: Definite");
		selenium.select("attributionMap[courseAgent][1][0]",
				"label=5: Definite");
		selenium.select("attributionMap[courseAgent][2][0]",
		"label=5: Definite");
		
		selenium.select("attributionMap[conMed][0][0]", "label=5: Definite");
		selenium.select("attributionMap[conMed][1][0]", "label=5: Definite");
		selenium.select("attributionMap[conMed][2][0]", "label=5: Definite");
		selenium.select("attributionMap[other][0][0]", "label=5: Definite");
		selenium.select("attributionMap[other][1][0]", "label=5: Definite");
		selenium.select("attributionMap[other][2][0]", "label=5: Definite");
	
	}

	public void select(String cause, String option) {
		
		
	}

	public void popExpAEsOtherCauses() throws InterruptedException {
		Thread.sleep(4000);
		if (selenium
				.isElementPresent("//div[@id='aeReport.otherCauses[0].text-row']")) {
			selenium.click("//div[@id='otherCause-0']/div/h3/div/a/img");
			aw.confirmOK("^Are you sure you want to delete this[\\s\\S]$");
		}
		Thread.sleep(1500);
		selenium.click("add-otherCause-button");
		Thread.sleep(2000);
		// aw.waitForElementPresent("//textarea[@id='aeReport.otherCauses[0].text']");
		selenium.type("aeReport.otherCauses[0].text", "other cause 1");
	}

	public void popExpAEsSubjectDetails() throws InterruptedException {
		selenium.type("aeReport.participantHistory.height.quantity", "100");
		selenium.type("aeReport.participantHistory.weight.quantity", "100");
		selenium.select("aeReport.diseaseHistory.ctepStudyDisease",
				"label=Glioblastoma multiforme");
		// aeReport.diseaseHistory.codedPrimaryDiseaseSite-choices
		aw.typeAutosuggest("aeReport.diseaseHistory.codedPrimaryDiseaseSite-input","Bone marrow", "aeReport.diseaseHistory.codedPrimaryDiseaseSite-choices");
		if (selenium.isElementPresent("//span[text()='Appendix']")) {
			selenium
					.click("//div[@id='anchorMetastaticDiseases']/div/table/tbody/tr/td[2]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
		}
		aw.typeAutosuggest("metastaticDiseaseSite-input", "appendix",
				"metastaticDiseaseSite-choices");
		// selenium.type("metastaticDiseaseSite-input", "appendix");
		// selenium.click("//div[@id='metastaticDiseaseSite-choices']/ul/li");
		selenium.click("metastatic-diseases-btn");
		Thread.sleep(1000);
		if (selenium
				.isElementPresent("//span[@id='aeReport.saeReportPreExistingConditions[0].preExistingCondition.text']")) {
			selenium
					.click("//div[@id='anchorPreExistingCondition']/div/table/tbody/tr/td[2]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
		}
		Thread.sleep(1500);
		selenium.select("preExistingCondition", "label=Anemia");
		selenium.click("pre-cond-btn");
		Thread.sleep(1000);
		if (selenium
				.isElementPresent("//div[@id='aeReport.concomitantMedications[0]']")) {
			selenium
					.click("//div[@id='aeReport.concomitantMedications[0]']/div/h3/table/tbody/tr/td[3]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
		}
		Thread.sleep(4000);
		// aw.waitForElementNotPresent("//div[@id='aeReport.concomitantMedications[0]']");
		selenium.type("concomitantMedication", "med33");
		selenium.click("concomitantMedication-btn");
		Thread.sleep(1000);
		if (selenium
				.isElementPresent("//div[@id='aeReport.saeReportPriorTherapies[0]']")) {
			selenium
					.click("//div[@id='aeReport.saeReportPriorTherapies[0]']/div/h3/table/tbody/tr/td[3]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
		}
		// div[@id='aeReport.saeReportPriorTherapies[0]']/div/h3/table/tbody/tr/td[3]/a/img
		// aw.waitForElementNotPresent("//div[@id='aeReport.saeReportPriorTherapies[0]']");
		Thread.sleep(1500);
		selenium.select("priorTherapy", "label=Bone Marrow Transplant");
		selenium.click("priortherapy-btn");
		Thread.sleep(3000);
		aw.typeAutosuggest("priorTherapyAgents[0]-input", "hexalen",
				"priorTherapyAgents[0]-choices");
		selenium.click("priortherapy[0].agent-btn");
		aw
				.waitForElementPresent("//span[@id='aeReport.saeReportPriorTherapies[0].priorTherapyAgents[0].name']");
		// selenium.type("priorTherapyAgents[0]-input", "hexalen");
	}

	public void popExpAEsEvent() throws InterruptedException {
		aw.waitForElementPresent("//input[@id='flow-next']");
		selenium.type("aeReport.responseDescription.eventDescription",
				"test description");
		selenium.select("aeReport.responseDescription.presentStatus",
				"label=Intervention for AE continues");
		selenium.select("aeReport.responseDescription.retreated", "label=Yes");
		selenium.click("//option[@value='true']");
	}

	public void popExpAEsCourseAgent() throws InterruptedException {
		Thread.sleep(5000);
		selenium.type("aeReport.treatmentInformation.firstCourseDate",
				"12/23/2008");
		selenium.type("aeReport.treatmentInformation.adverseEventCourse.date",
				"12/23/2008");
		selenium.type(
				"aeReport.treatmentInformation.adverseEventCourse.number", "1");
		selenium.type("aeReport.treatmentInformation.totalCourses", "1");
		if (selenium.isElementPresent("//div[@id='courseAgent-0']")) {
			selenium.click("//div[@id='courseAgent-0']/div[1]/h3/div/a/img");
			aw.confirmOK("^Are you sure you want to delete this[\\s\\S]$");
		}
		selenium.click("add-courseAgent-button");

		aw
				.waitForElementPresent("//label[@for='aeReport.treatmentInformation.courseAgents[0].agentAdjustment']");
		selenium.select(
				"aeReport.treatmentInformation.courseAgents[0].studyAgent",
				"label=CCI-779 (temsirolimus, Torisel)");
		selenium.type(
				"aeReport.treatmentInformation.courseAgents[0].dose.amount",
				"1");
		selenium.select(
				"aeReport.treatmentInformation.courseAgents[0].dose.units",
				"label=Ci");
		selenium
				.type(
						"aeReport.treatmentInformation.courseAgents[0].lastAdministeredDate",
						"12/23/2008");
	}

	public void popExpAEsEnterAEs() {
		selenium.type("aeReport.adverseEvents[0].startDate", "12/23/2008");
	}
}
