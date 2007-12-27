package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.CtepStudyDisease;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.rules.RulesTestCase;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceImpl;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BusinessRulesExecutionServiceTest extends RulesTestCase {
	
	
	protected RuleDeploymentServiceImpl deploymetService;
	protected BusinessRulesExecutionServiceImpl executionService;
	
	public abstract String getBindUri();
	public abstract String getRuleFile();
	
	@Override
	public Class<BusinessRulesExecutionServiceTest> getTestClass() {
		return BusinessRulesExecutionServiceTest.class;
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		deploymetService = new RuleDeploymentServiceImpl();
		executionService = new BusinessRulesExecutionServiceImpl();
		try{unregisterRule();}catch(Exception e){}
		registerRule();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		unregisterRule();
	}


	public ExpeditedAdverseEventReport createAEReport(){
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		// update event description
		AdverseEventResponseDescription aeResponseDesc = new AdverseEventResponseDescription();
		aeResponseDesc.setEventDescription("This is a sample description");
		aeResponseDesc.setRecoveryDate(new Date());
		aeResponseDesc.setDateRemovedFromProtocol(new Date());
		aeResponseDesc.setPresentStatus(PostAdverseEventStatus.DEAD);
		aeResponseDesc.setRetreated(Boolean.FALSE);
		aeReport.setResponseDescription(aeResponseDesc);
		
		//patient details section
		DiseaseHistory aeDiseaseHistory = new DiseaseHistory();
		CtepStudyDisease studyDisease = new CtepStudyDisease();
		DiseaseTerm diseaseTerm = new DiseaseTerm();
		diseaseTerm.setTerm("Solid tumor, NOS");
		studyDisease.setDiseaseTerm(diseaseTerm);
		aeDiseaseHistory.setAbstractStudyDisease(studyDisease);
		aeDiseaseHistory.setOtherPrimaryDisease("MyOtherDisease");
		aeReport.setDiseaseHistory(aeDiseaseHistory);
		
		return aeReport;
	}
	
	public ValidationErrors retrieveValidationErrors(List<Object> list){
		for(Object o : list)
			if(o instanceof ValidationErrors) return (ValidationErrors)o;
		return null;
	}
	public ValidationErrors fireRules(ExpeditedAdverseEventReport aeReport) throws Exception{
		List<Object> input = new ArrayList<Object>();
		input.add(aeReport);
		ValidationErrors errors = new ValidationErrors();
		input.add(errors);
		
		List<Object> output = executionService.fireRules(getBindUri(), input);
		return retrieveValidationErrors(output);
	}
	public void registerRule() throws Exception{
		String ruleXml = getFileContext(getRuleFile());
		deploymetService.registerRuleXml(getBindUri(), ruleXml);
		assertTrue("Rule deployed", true);
	}
	
	public void unregisterRule() throws Exception{
		deploymetService.deregisterRuleSet(getBindUri());
		assertTrue("Rule undeployed", true);
	}
}
