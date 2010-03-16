package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.report.Report;

import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import org.apache.commons.io.IOUtils;
import org.easymock.classextension.EasyMock;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Biju Joseph
 *
 */
public class AdeersReportGeneratorTest extends CaaersTestCase {
	private AdeersReportGenerator generator;
	private AdverseEventReportSerializer mockSerializer;
    private AdverseEventReportSerializer orgSerializer;
    private EvaluationService evaluationService;
	@Override
	protected void setUp() throws Exception {
		super.setUp();

        generator = (AdeersReportGenerator)getDeployedApplicationContext().getBean("adeersReportGenerator");
		mockSerializer = registerMockFor(AdverseEventReportSerializer.class);
		evaluationService = registerMockFor(EvaluationService.class);
        orgSerializer = (AdverseEventReportSerializer)  getDeployedApplicationContext().getBean("adverseEventReportSerializer")  ;
	}

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        generator.setAdverseEventReportSerializer(orgSerializer);
    }

    public void testGenerateCaaersXml() throws Exception {

        generator.setAdverseEventReportSerializer(mockSerializer);
        generator.setEvaluationService(evaluationService);

		String retValue = "hello biju";
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        Report r = Fixtures.createReport("test");
		EasyMock.expect(mockSerializer.serialize(aeReport, r)).andReturn(retValue);
        evaluationService.evaluateMandatoryness(aeReport, r);
		replayMocks();
		String caAERSXML = generator.generateCaaersXml(aeReport, r);
		assertEquals(retValue, caAERSXML);
		verifyMocks();
	}

	public void testGenerateExternalReports() throws Exception {

        generator.setEvaluationService(evaluationService);

		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        Report r = Fixtures.createReport("abc");
        ReportDefinition rd = Fixtures.createReportDefinition("RD");
        rd.setMandatoryFields(createMandatoryFieldDefinitions());
        r.setReportDefinition(rd);
        r.getReportDefinition().setReportFormatType(ReportFormatType.CUSTOM_REPORT);

        evaluationService.evaluateMandatoryness(aeReport, r);

        replayMocks();
        String[] s = generator.generateExternalReports(r, "", 77);
        assertEquals(System.getProperty("java.io.tmpdir") + "/CustomReport-77.pdf", s[0]);
        verifyMocks();
	}
	public void testWithdrawXml() throws Exception {

        generator.setEvaluationService(evaluationService);

		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
		aeReport.setId(1);
		Study study = new LocalStudy() ;
		study.setShortTitle("test study");
		Identifier identifier= new Identifier();
		identifier.setPrimaryIndicator(true);
		identifier.setValue("001");
		identifier.setType("Protocol Authority Identifier");
		study.addIdentifier(identifier);
		Identifier identifier2= new Identifier();
		identifier2.setPrimaryIndicator(false);
		identifier2.setValue("002");
		identifier2.setType("Coordinating Center Identifier");	
		study.addIdentifier(identifier2);
		StudyParticipantAssignment spa = new StudyParticipantAssignment();
		StudySite studySite = new StudySite();
		studySite.setStudy(study);
		spa.setStudySite(studySite);
		
		AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
		reportingPeriod.setAssignment(spa);
		aeReport.setReportingPeriod(reportingPeriod);
		

		Report r = Fixtures.createReport("abc");
		r.setId(1);
        r.setReportDefinition(Fixtures.createReportDefinition("RD"));
        r.getReportDefinition().setReportFormatType(ReportFormatType.CUSTOM_REPORT);
        aeReport.addReport(r);

        evaluationService.evaluateMandatoryness(aeReport, r);

        replayMocks();
        String xml = generator.generateCaaersWithdrawXml(aeReport, r);

		String data =  IOUtils.toString(getClass().getResourceAsStream("withdraw_aereport.xml"));

        assertEquals(data.trim(),xml.trim());
		
	}


    private List<ReportMandatoryFieldDefinition> createManatoryFieldDefinitions(){
          return createMandatoryFieldDefinitions("adverseEvents[].participantAtRisk"
            ,"treatmentInformation"
            ,"treatmentInformation.firstCourseDate"
            ,"treatmentInformation.adverseEventCourse"
            ,"treatmentInformation.adverseEventCourse.date"
            ,"treatmentInformation.adverseEventCourse.number"
            ,"treatmentInformation.totalCourses"
            ,"treatmentInformation.courseAgents[]"
            ,"treatmentInformation.courseAgents[].studyAgent"
            ,"treatmentInformation.courseAgents[].formulation"
            ,"treatmentInformation.courseAgents[].lotNumber"
            ,"treatmentInformation.courseAgents[].dose.amount"
            ,"treatmentInformation.courseAgents[].dose.units"
            ,"treatmentInformation.courseAgents[].lastAdministeredDate"
            ,"treatmentInformation.courseAgents[].administrationDelayAmount"
            ,"treatmentInformation.courseAgents[].administrationDelayUnits"
            ,"treatmentInformation.courseAgents[].comments"
            ,"labs[].baseline"
            ,"labs[].baseline.value"
            ,"labs[].baseline.date"
            ,"labs[].nadir"
            ,"labs[].nadir.value"
            ,"labs[].nadir.date"
            ,"labs[].recovery"
            ,"labs[].recovery.value"
            ,"labs[].recovery.date"
            ,"labs[].site"
            ,"diseaseHistory.metastaticDiseaseSites[]"
            ,"reporter"
            ,"labs[].labDate"
            ,"labs[].infectiousAgent"
            ,"otherCauses[]"
            ,"otherCauses[].text"
            ,"saeReportPriorTherapies[]"
            ,"saeReportPriorTherapies[].priorTherapy"
            ,"saeReportPriorTherapies[].other"
            ,"saeReportPriorTherapies[].startDate"
            ,"saeReportPriorTherapies[].endDate"
            ,"saeReportPriorTherapies[].priorTherapyAgents[]"
            ,"saeReportPriorTherapies[].priorTherapyAgents[].chemoAgent"
            ,"saeReportPreExistingConditions[]"
            ,"saeReportPreExistingConditions[].preExistingCondition"
            ,"concomitantMedications[]"
            ,"concomitantMedications[].stillTakingMedications"
            ,"concomitantMedications[].startDate"
            ,"concomitantMedications[].agentName"
            ,"concomitantMedications[].endDate"
            ,"participantHistory.height"
            ,"participantHistory.height.quantity"
            ,"participantHistory.height.unit"
            ,"participantHistory.weight"
            ,"participantHistory.weight.quantity"
            ,"participantHistory.weight.unit"
            ,"participantHistory.baselinePerformanceStatus"
            ,"diseaseHistory"
            ,"diseaseHistory.codedPrimaryDiseaseSite"
            ,"diseaseHistory.diagnosisDate"
            ,"treatmentInformation.investigationalAgentAdministered"
            ,"treatmentInformation.courseAgents[].agentAdjustment"
            ,"additionalInformation"
            ,"additionalInformation.autopsyReport"
            ,"additionalInformation.pathologyReport"
            ,"additionalInformation.consults"
            ,"additionalInformation.progressNotes"
            ,"additionalInformation.dischargeSummary"
            ,"additionalInformation.radiologyReports"
            ,"additionalInformation.flowCharts"
            ,"additionalInformation.labReports"
            ,"additionalInformation.irbReport"
            ,"additionalInformation.obaForm"
            ,"additionalInformation.other"
            ,"additionalInformation.otherInformation"
            ,"adverseEvents[].eventApproximateTime.hourString"
            ,"responseDescription.primaryTreatmentApproximateTime.hourString"
            ,"diseaseHistory.abstractStudyDisease"
            ,"treatmentInformation.courseAgents[].dose"
            ,"labs[]"
            ,"radiationInterventions[].dosage"
            ,"labs[].labTerm"
            ,"physician"
            ,"diseaseHistory.metastaticDiseaseSites[].codedSite"
            ,"adverseEvents[]"
            ,"adverseEvents[].grade"
            ,"adverseEvents[].adverseEventCtcTerm"
            ,"adverseEvents[].adverseEventCtcTerm.term"
            ,"adverseEvents[].detailsForOther"
            ,"adverseEvents[].startDate"
            ,"adverseEvents[].endDate"
            ,"adverseEvents[].attributionSummary"
            ,"adverseEvents[].hospitalization"
            ,"adverseEvents[].expected"
            ,"adverseEvents[].eventLocation"
            ,"adverseEvents[].outcomes"
            ,"adverseEvents[].comments"
            ,"reporter.title"
            ,"reporter.firstName"
            ,"reporter.middleName"
            ,"reporter.lastName"
            ,"reporter.contactMechanisms[e-mail]"
            ,"reporter.contactMechanisms[phone]"
            ,"labs[].units"
            ,"reporter.contactMechanisms[fax]"
            ,"reporter.address"
            ,"reporter.address.street"
            ,"reporter.address.city"
            ,"reporter.address.state"
            ,"reporter.address.zip"
            ,"physician.title"
            ,"physician.firstName"
            ,"physician.middleName"
            ,"physician.lastName"
            ,"physician.contactMechanisms[e-mail]"
            ,"physician.contactMechanisms[phone]"
            ,"physician.contactMechanisms[fax]"
            ,"physician.address"
            ,"physician.address.street"
            ,"physician.address.city"
            ,"participantHistory"
            ,"physician.address.state"
            ,"physician.address.zip"
            ,"radiationInterventions[]"
            ,"radiationInterventions[].administration"
            ,"radiationInterventions[].dosageUnit"
            ,"radiationInterventions[].lastTreatmentDate"
            ,"radiationInterventions[].fractionNumber"
            ,"radiationInterventions[].daysElapsed"
            ,"radiationInterventions[].adjustment"
            ,"surgeryInterventions[]"
            ,"surgeryInterventions[].interventionSite"
            ,"surgeryInterventions[].interventionDate"
            ,"medicalDevices[]"
            ,"medicalDevices[].brandName"
            ,"medicalDevices[].commonName"
            ,"medicalDevices[].deviceType"
            ,"medicalDevices[].manufacturerName"
            ,"medicalDevices[].manufacturerCity"
            ,"medicalDevices[].manufacturerState"
            ,"medicalDevices[].modelNumber"
            ,"medicalDevices[].lotNumber"
            ,"medicalDevices[].catalogNumber"
            ,"medicalDevices[].expirationDate"
            ,"medicalDevices[].serialNumber"
            ,"medicalDevices[].otherNumber"
            ,"medicalDevices[].deviceOperator"
            ,"medicalDevices[].otherDeviceOperator"
            ,"medicalDevices[].implantedDate"
            ,"medicalDevices[].explantedDate"
            ,"medicalDevices[].deviceReprocessed"
            ,"medicalDevices[].reprocessorName"
            ,"medicalDevices[].reprocessorAddress"
            ,"medicalDevices[].evaluationAvailability"
            ,"medicalDevices[].returnedDate"
            ,"responseDescription"
            ,"responseDescription.eventDescription"
            ,"responseDescription.dateRemovedFromProtocol"
            ,"responseDescription.presentStatus"
            ,"responseDescription.recoveryDate"
            ,"responseDescription.retreated"
            ,"responseDescription.blindBroken"
            ,"responseDescription.studyDrugInterrupted"
            ,"responseDescription.reducedDose"
            ,"responseDescription.reducedDate"
            ,"responseDescription.daysNotGiven"
            ,"responseDescription.autopsyPerformed"
            ,"responseDescription.causeOfDeath"
            ,"responseDescription.eventAbate"
            ,"saeReportPriorTherapies[].startDate.year"
            ,"saeReportPriorTherapies[].startDate.month"
            ,"saeReportPriorTherapies[].startDate.day"
            ,"saeReportPriorTherapies[].endDate.year"
            ,"saeReportPriorTherapies[].endDate.month"
            ,"saeReportPriorTherapies[].endDate.day"
            ,"concomitantMedications[].startDate.year"
            ,"concomitantMedications[].startDate.month"
            ,"concomitantMedications[].startDate.day"
            ,"concomitantMedications[].endDate.year"
            ,"concomitantMedications[].endDate.month"
            ,"concomitantMedications[].endDate.day"
            ,"diseaseHistory.diagnosisDate.year"
            ,"diseaseHistory.diagnosisDate.month"
            ,"diseaseHistory.diagnosisDate.day"
            ,"responseDescription.eventReappear"
            ,"treatmentInformation.treatmentAssignment"
            ,"additionalInformation.referralLetters"
            );
    }

    private List<ReportMandatoryFieldDefinition> createMandatoryFieldDefinitions(String... paths){
        ArrayList<ReportMandatoryFieldDefinition> l = new ArrayList<ReportMandatoryFieldDefinition>();
        for(String s : paths) l.add(Fixtures.createMandatoryField(s, RequirednessIndicator.OPTIONAL));
        return l;
    }

}
