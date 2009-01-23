package gov.nih.nci.cabig.caaers.web.selenium;
import com.thoughtworks.selenium.*;

import java.util.regex.Pattern;

public class CaaersAEFlowFixtures extends SeleneseTestCase{
	Selenium selenium;
	AjaxWidgets aw;
	public CaaersAEFlowFixtures(Selenium selenium, AjaxWidgets aw) {
		super();
		this.selenium = selenium;
		this.aw=aw;
	}
	public void executeCAEFlow(String participant, String studyId) throws InterruptedException{
		selenium.open("/caaers/pages/ae/list");
		selenium.click("firstlevelnav_listAdverseEventsController");
		aw.waitForElementPresent("//a[@id='secondlevelnav_captureAdverseEventController']");
		selenium.open("/caaers/pages/ae/captureRoutine");
		aw.waitForElementPresent("//input[@id='study-input']");
		aw.typeAutosuggest("study-input", studyId, "study-choices");
		aw.typeAutosuggest("participant-input", participant, "participant-choices");
		aw.clickNext("flow-next");
		selenium.select("adverseEventReportingPeriod", "label=01/01/09 - 01/02/09");
		aw.waitForElementPresent("//div[@id='solicitatedID']");
		selenium.select("adverseEvents[0].grade", "label=5: Death");
		selenium.select("adverseEvents[0].attributionSummary", "label=Definite");
		selenium.select("adverseEvents[0].hospitalization", "label=Yes");
		selenium.select("adverseEvents[0].expected", "label=No");
		aw.clickNext("flow-next");
		selenium.click("//img[@alt='Continue']");
		aw.waitForElementPresent("//div[@id='popupId']");
		aw.clickNext("edit-report");
		//selenium.click("edit-report");
		selenium.type("aeReport.reporter.firstName", "rs-fname");
		selenium.type("aeReport.reporter.lastName", "rs-lname");
		selenium.type("aeReport.reporter.contactMechanisms[e-mail]", "caaers.app@gmail.com");
		selenium.type("aeReport.reporter.contactMechanisms[phone]", "0000000000");
		selenium.type("aeReport.reporter.contactMechanisms[fax]", "0000000000");
		selenium.click("physician-same");
		aw.clickNext("flow-next");

	}
	public void executeExpeditedAEFlow(String relURL) throws InterruptedException {
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
		//selenium.open(relURL);
		selenium.open("caaers/pages/ae/submitReport?from=list&aeReport=21&reportId=21");
		//aw.clickNext("link=13. Submit");
		//aw.clickNext("//div[text()='NCI AdEERS Report [5 day CTEP]'] /parent::td/following-sibling::td/descendant::a[text()='Amend']");
		aw.waitForElementPresent("//select[@id='aeReport.reports[1].lastVersion.physicianSignoff']");
		selenium.select("aeReport.reports[1].lastVersion.physicianSignoff", "label=Yes");
		selenium.click("reporter_same_as_submitter");
		aw.clickNext("flow-next");
		aw.clickNext("flow-next");
		//aw.clickNext("link=Submit");
		//Thread.sleep(1500);
		selenium.open(relURL);
		aw.clickNext("link=13. Submit");
		
	}
	public void popExpAEsRadiation() {}
	public void popExpAEsSurgery() {}
	public void popExpAEsDevice() {}
	public void popExpAEsLabs() {}
	public void popExpAEsAttachments() {}
	public void popExpAEsAttribution() {
		selenium.select("attributionMap[disease][0][0]", "label=5: Definite");
		selenium.select("attributionMap[disease][1][0]", "label=5: Definite");
		selenium.select("attributionMap[disease][2][0]", "label=5: Definite");
		selenium.select("attributionMap[courseAgent][0][0]", "label=5: Definite");
		selenium.select("attributionMap[courseAgent][1][0]", "label=5: Definite");
		selenium.select("attributionMap[courseAgent][2][0]", "label=5: Definite");
		selenium.select("attributionMap[conMed][0][0]", "label=5: Definite");
		selenium.select("attributionMap[conMed][1][0]", "label=5: Definite");
		selenium.select("attributionMap[conMed][2][0]", "label=5: Definite");
		selenium.select("attributionMap[other][0][0]", "label=5: Definite");
		selenium.select("attributionMap[other][1][0]", "label=5: Definite");
		selenium.select("attributionMap[other][2][0]", "label=5: Definite");
	}
	public void popExpAEsOtherCauses() throws InterruptedException {
		Thread.sleep(4000);
		if(selenium.isElementPresent("//div[@id='aeReport.otherCauses[0].text-row']")){
			selenium.click("//div[@id='otherCause-0']/div/h3/div/a/img");
			aw.confirmOK("^Are you sure you want to delete this[\\s\\S]$");
			}
		Thread.sleep(1500);
		selenium.click("add-otherCause-button");
		Thread.sleep(2000);
		//aw.waitForElementPresent("//textarea[@id='aeReport.otherCauses[0].text']");
		selenium.type("aeReport.otherCauses[0].text", "other cause 1");
	}
	public void popExpAEsSubjectDetails() throws InterruptedException {
		selenium.type("aeReport.participantHistory.height.quantity", "100");
		selenium.type("aeReport.participantHistory.weight.quantity", "100");
		selenium.select("aeReport.diseaseHistory.ctepStudyDisease", "label=Glioblastoma multiforme");
		//aeReport.diseaseHistory.codedPrimaryDiseaseSite-choices
		//aw.typeAutosuggest("aeReport.diseaseHistory.codedPrimaryDiseaseSite-input", "Bone marrow", "aeReport.diseaseHistory.codedPrimaryDiseaseSite-choices");
		if(selenium.isElementPresent("//span[text()='Appendix']")){
			selenium.click("//div[@id='anchorMetastaticDiseases']/div/table/tbody/tr/td[2]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
			}
		aw.typeAutosuggest("metastaticDiseaseSite-input", "appendix", "metastaticDiseaseSite-choices");
//		selenium.type("metastaticDiseaseSite-input", "appendix");
//		selenium.click("//div[@id='metastaticDiseaseSite-choices']/ul/li");
		selenium.click("metastatic-diseases-btn");
		Thread.sleep(1000);
		if(selenium.isElementPresent("//span[@id='aeReport.saeReportPreExistingConditions[0].preExistingCondition.text']")){
			selenium.click("//div[@id='anchorPreExistingCondition']/div/table/tbody/tr/td[2]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
			}
		Thread.sleep(1500);
		selenium.select("preExistingCondition", "label=Anemia");
		selenium.click("pre-cond-btn");
		Thread.sleep(1000);
		if(selenium.isElementPresent("//div[@id='aeReport.concomitantMedications[0]']")){
			selenium.click("//div[@id='aeReport.concomitantMedications[0]']/div/h3/table/tbody/tr/td[3]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
			}
		Thread.sleep(4000);
		//aw.waitForElementNotPresent("//div[@id='aeReport.concomitantMedications[0]']");
		selenium.type("concomitantMedication", "med33");
		selenium.click("concomitantMedication-btn");
		Thread.sleep(1000);
		if(selenium.isElementPresent("//div[@id='aeReport.saeReportPriorTherapies[0]']")){
			selenium.click("//div[@id='aeReport.saeReportPriorTherapies[0]']/div/h3/table/tbody/tr/td[3]/a/img");
			aw.confirmOK("^Do you really want to delete[\\s\\S]$");
			}
		//div[@id='aeReport.saeReportPriorTherapies[0]']/div/h3/table/tbody/tr/td[3]/a/img
		//aw.waitForElementNotPresent("//div[@id='aeReport.saeReportPriorTherapies[0]']");
		Thread.sleep(1500);
		selenium.select("priorTherapy", "label=Bone Marrow Transplant");
		selenium.click("priortherapy-btn");
		Thread.sleep(3000);
		aw.typeAutosuggest("priorTherapyAgents[0]-input", "hexalen", "priorTherapyAgents[0]-choices");
		selenium.click("priortherapy[0].agent-btn");
		aw.waitForElementPresent("//span[@id='aeReport.saeReportPriorTherapies[0].priorTherapyAgents[0].name']");
		//selenium.type("priorTherapyAgents[0]-input", "hexalen");
	}
	public void popExpAEsEvent() throws InterruptedException {
		aw.waitForElementPresent("//input[@id='flow-next']");
		selenium.type("aeReport.responseDescription.eventDescription", "test description");
		selenium.select("aeReport.responseDescription.presentStatus", "label=Intervention for AE continues");
		selenium.select("aeReport.responseDescription.retreated", "label=Yes");
		selenium.click("//option[@value='true']");
	}
	public void popExpAEsCourseAgent() throws InterruptedException {
		Thread.sleep(5000);
		selenium.type("aeReport.treatmentInformation.firstCourseDate", "12/23/2008");
		selenium.type("aeReport.treatmentInformation.adverseEventCourse.date", "12/23/2008");
		selenium.type("aeReport.treatmentInformation.adverseEventCourse.number", "1");
		selenium.type("aeReport.treatmentInformation.totalCourses", "1");
		/*if(selenium.isElementPresent("//div[@id='courseAgent-0']")){
		selenium.click("//div[@id='courseAgent-0']/div[1]/h3/div/a/img");
		aw.confirmOK("^Are you sure you want to delete this[\\s\\S]$");
		}
		selenium.click("add-courseAgent-button");*/
		aw.waitForElementPresent("//label[@for='aeReport.treatmentInformation.courseAgents[0].agentAdjustment']");
		selenium.select("aeReport.treatmentInformation.courseAgents[0].studyAgent", "label=CCI-779 (temsirolimus, Torisel)");
		selenium.type("aeReport.treatmentInformation.courseAgents[0].dose.amount", "1");
		selenium.select("aeReport.treatmentInformation.courseAgents[0].dose.units", "label=Ci");
		selenium.type("aeReport.treatmentInformation.courseAgents[0].lastAdministeredDate", "12/23/2008");
	}
	public void popExpAEsEnterAEs() {
		selenium.type("aeReport.adverseEvents[0].startDate", "12/23/2008");
	}
}
