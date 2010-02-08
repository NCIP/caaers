package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import org.easymock.classextension.EasyMock;
/**
 * 
 * @author Biju Joseph
 *
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class StudyInterventionsTabTest  extends AeTabTestCase {
	ConfigProperty configurationProperty;
	HashMap<String, List<Lov>> map;
	protected void setUp() throws Exception {
		super.setUp();
		
		
	}
	
	@Override
	protected AeTab createTab() {
		
		configurationProperty = registerMockFor(ConfigProperty.class);
		map = new HashMap<String, List<Lov>>();
		map.put("radiationDoseUMORefData", new ArrayList<Lov>());
		map.put("radiationAdjustmentRefData", new ArrayList<Lov>());
		map.put("agentDoseUMORefData", new ArrayList<Lov>());
		map.put("stateRefData", new ArrayList<Lov>());
		
		StudyInterventionsTab tab = new StudyInterventionsTab();
		tab.setExpeditedAdverseEventReportDao(expeditedReportDao);
		tab.setConfigurationProperty(configurationProperty);
		EasyMock.expect(configurationProperty.getMap()).andReturn(map).anyTimes();
		EasyMock.expect(evaluationService.validateReportingBusinessRules(command.getAeReport(), tab.section())).andReturn(new ValidationErrors()).anyTimes();
		return tab;
	}
	
	public void testValidate_NoErrors() {
		doValidate();
    	assertFalse(getErrors().hasErrors());
	}
	
	public void testValidate_CorrectTotalDoseAdministred() {
		CourseAgent agent = command.getAeReport().getTreatmentInformation().getCourseAgents().get(0);
		StudyAgent studyAgent = Fixtures.createStudyAgent("test");
		agent.setStudyAgent(studyAgent);
		agent.getDose().setAmount("3.2");
		agent.getDose().setUnits("BVP");
		doValidate();
		assertFalse(getErrors().hasErrors());
	}
	
	public void testValidate_WrongTotalDoseAdministred() {
		CourseAgent agent = command.getAeReport().getTreatmentInformation().getCourseAgents().get(0);
		StudyAgent studyAgent = Fixtures.createStudyAgent("test");
		agent.setStudyAgent(studyAgent);
		agent.getDose().setAmount("3.333333332");
		agent.getDose().setUnits("BVP");
		
		agent = command.getAeReport().getTreatmentInformation().getCourseAgents().get(1);
		studyAgent = Fixtures.createStudyAgent("test");
		agent.setStudyAgent(studyAgent);
		agent.getDose().setAmount("3.333333332");
		agent.getDose().setUnits("BVP");
		
		doValidate();
		assertFieldError("aeReport.treatmentInformation.courseAgents[0].dose.amount", "REQUIRED", "<b>Invalid:</b> &quot;Total dose administered this course&quot;");
		assertFieldError("aeReport.treatmentInformation.courseAgents[1].dose.amount", "REQUIRED", "<b>Invalid:</b> &quot;Total dose administered this course&quot;");
	}
	
	

	public void testValidate_WrongTotalDoseToDate() {
	
		RadiationIntervention radiationCorrect = command.getAeReport().getRadiationInterventions().get(0);
		RadiationIntervention radiationWrong1 = command.getAeReport().getRadiationInterventions().get(1);
		RadiationIntervention radiationWrong2 = command.getAeReport().getRadiationInterventions().get(2);
		
		radiationCorrect.setDosage("558");
		radiationWrong1.setDosage("-234.33");
		radiationWrong2.setDosage("444444444443444444444.33");
		
		doValidate();
		
		assertFieldError("aeReport.radiationInterventions[0].administration", "REQUIRED", "<b>Missing:</b> &quot;Type of radiation administration&quot;");
		assertFieldError("aeReport.radiationInterventions[1].administration", "REQUIRED", "<b>Missing:</b> &quot;Type of radiation administration&quot;");
		assertFieldError("aeReport.radiationInterventions[2].administration", "REQUIRED", "<b>Missing:</b> &quot;Type of radiation administration&quot;");
		assertFieldError("aeReport.radiationInterventions[1].dosage", "REQUIRED", "Invalid sign Total dose (to date)");
		assertFieldError("aeReport.radiationInterventions[2].dosage", "REQUIRED", "Invalid Total dose (to date)");
	}


    public void testDeviceFields(){
        replayMocks();
        assertFieldProperties("medicalDevice0", "aeReport.medicalDevices[0].brandName",
                "aeReport.medicalDevices[0].commonName",
                "aeReport.medicalDevices[0].deviceType",
                "aeReport.medicalDevices[0].manufacturerName",
                "aeReport.medicalDevices[0].manufacturerCity",
                "aeReport.medicalDevices[0].manufacturerState",
                "aeReport.medicalDevices[0].modelNumber",
                "aeReport.medicalDevices[0].lotNumber",
                "aeReport.medicalDevices[0].catalogNumber",
                "aeReport.medicalDevices[0].expirationDate",
                "aeReport.medicalDevices[0].serialNumber",
                "aeReport.medicalDevices[0].otherNumber",
                "aeReport.medicalDevices[0].deviceOperator",
                "aeReport.medicalDevices[0].otherDeviceOperator",
                "aeReport.medicalDevices[0].implantedDate",
                "aeReport.medicalDevices[0].explantedDate",
                "aeReport.medicalDevices[0].deviceReprocessed",
                "aeReport.medicalDevices[0].reprocessorName" ,
                "aeReport.medicalDevices[0].reprocessorAddress" ,
                "aeReport.medicalDevices[0].evaluationAvailability" ,
                "aeReport.medicalDevices[0].returnedDate" 



                );
    }
	

}
