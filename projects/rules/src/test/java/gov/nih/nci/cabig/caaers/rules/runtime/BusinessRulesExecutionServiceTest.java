package gov.nih.nci.cabig.caaers.rules.runtime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceImpl;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import junit.framework.TestCase;

public class BusinessRulesExecutionServiceTest extends TestCase {
	
	RuleDeploymentServiceImpl deploymetService;
	BusinessRulesExecutionServiceImpl executionService;
	
	protected void setUp() throws Exception {
		super.setUp();
		deploymetService = new RuleDeploymentServiceImpl();
		executionService = new BusinessRulesExecutionServiceImpl();
		try {
			File file = new File("C:\\rules_repo\\repo\\.lock");
			file.delete();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	/**
	 * RuleName : DSC_BR1A_CHK
	Rule : Date of Recovery or Death” must be provided if “Present Status” has one of following values:
			Fatal/Died
			Recovered/Resolved without Sequelae
			Recovered/Resolved with Sequelae"
	Error Code : DSC_BR1A_ERR
	Error Message : DATE_OF_RECOVERY_DEATH must be provided for the provided PRESENT_STATUS value.
	 */
	public void testEventDescriptionRule1() throws Exception{
		String bindUri = "gov.nih.nci.cabig.caaers.rules.sponsor.cancer_therapy_evaluation_program.description_section";
		try{
			registerRule(bindUri, "event_description_rule_1.xml");
			
			//----------------
			ExpeditedAdverseEventReport aeReport = createAEReport();
			// update event description
			AdverseEventResponseDescription aeResponseDesc = new AdverseEventResponseDescription();
			aeResponseDesc.setEventDescription("This is a sample description");
			aeResponseDesc.setRecoveryDate(new Date());
			aeResponseDesc.setDateRemovedFromProtocol(new Date());
			aeResponseDesc.setPresentStatus(PostAdverseEventStatus.DEAD);
			aeReport.setResponseDescription(aeResponseDesc);
			//----------------
			List<Object> input = new ArrayList<Object>();
			input.add(aeReport);
			ValidationErrors errors = new ValidationErrors();
			input.add(errors);
			
			List<Object> output = executionService.fireRules(bindUri, input);
			errors = retrieveValidationErrors(output);
			
			assertEquals("There should not be any validation error", 0, errors.getErrorCount());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			unregisterRule(bindUri);
		}
	}
	/**
	 * RuleName : DSC_BR1A_CHK
	Rule : Date of Recovery or Death” must be provided if “Present Status” has one of following values:
			Fatal/Died
			Recovered/Resolved without Sequelae
			Recovered/Resolved with Sequelae"
	Error Code : DSC_BR1A_ERR
	Error Message : DATE_OF_RECOVERY_DEATH must be provided for the provided PRESENT_STATUS value.
	 */
	public void testEventDescriptionRule1_WithoutRecoveryDate() throws Exception{
		String bindUri = "gov.nih.nci.cabig.caaers.rules.sponsor.cancer_therapy_evaluation_program.description_section";
		try{
			registerRule(bindUri, "event_description_rule_1.xml");
			
			//----------------
			ExpeditedAdverseEventReport aeReport = createAEReport();
			// update event description
			AdverseEventResponseDescription aeResponseDesc = new AdverseEventResponseDescription();
			aeResponseDesc.setEventDescription("This is a sample description");
			aeResponseDesc.setPresentStatus(PostAdverseEventStatus.DEAD);
			aeResponseDesc.setDateRemovedFromProtocol(new Date());
			aeReport.setResponseDescription(aeResponseDesc);
			//----------------
			List<Object> input = new ArrayList<Object>();
			input.add(aeReport);
			ValidationErrors errors = new ValidationErrors();
			input.add(errors);
			
			List<Object> output = executionService.fireRules(bindUri, input);
			errors = retrieveValidationErrors(output);
			System.out.println(errors);
			assertEquals("There should be 1 validation error", 1, errors.getErrorCount());
			errors.getErrorAt(0).getMessage().equals("'Date of Recovery or Death' must be provided if 'Present Status' has " +
					"one of following values:'Fatal/Died'," +
					"'Recovered/Resolved without Sequelae','Recovered/Resolved with Sequelae'");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			unregisterRule(bindUri);
		}
	}
	/**
	 * RuleName : DSC_BR1A_CHK
	Rule : Date of Recovery or Death” must be provided if “Present Status” has one of following values:
			Fatal/Died
			Recovered/Resolved without Sequelae
			Recovered/Resolved with Sequelae"
	Error Code : DSC_BR1A_ERR
	Error Message : DATE_OF_RECOVERY_DEATH must be provided for the provided PRESENT_STATUS value.
	 */
	public void testEventDescriptionRule1_WithoutRecoveryDateAndNotRecoverdPresentStatus() throws Exception{
		String bindUri = "gov.nih.nci.cabig.caaers.rules.sponsor.cancer_therapy_evaluation_program.description_section";
		try{
			registerRule(bindUri, "event_description_rule_1.xml");
			
			//----------------
			ExpeditedAdverseEventReport aeReport = createAEReport();
			// update event description
			AdverseEventResponseDescription aeResponseDesc = new AdverseEventResponseDescription();
			aeResponseDesc.setEventDescription("This is a sample description");
			aeResponseDesc.setPresentStatus(PostAdverseEventStatus.NOT_RECOVERED);
			aeResponseDesc.setDateRemovedFromProtocol(new Date());
			aeReport.setResponseDescription(aeResponseDesc);
			//----------------
			List<Object> input = new ArrayList<Object>();
			input.add(aeReport);
			ValidationErrors errors = new ValidationErrors();
			input.add(errors);
			
			List<Object> output = executionService.fireRules(bindUri, input);
			errors = retrieveValidationErrors(output);
			System.out.println(errors);
			assertEquals("There should not be validation error", 0, errors.getErrorCount());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			unregisterRule(bindUri);
		}
	}
	
	public String getFileContext(String fileName) throws Exception{
		File testFile = new ClassPathResource(fileName,BusinessRulesExecutionServiceTest.class).getFile();
		BufferedReader ds = new BufferedReader(new FileReader(testFile));
		String line = null;
		StringBuffer xml = new StringBuffer();
		while((line = ds.readLine()) != null){
			xml.append(line);
		}
		assertTrue("Content of the xml should not be null", xml.toString().length() > 0);
		return xml.toString();
	}
	
	
	public ExpeditedAdverseEventReport createAEReport(){
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		return aeReport;
	}
	
	public ValidationErrors retrieveValidationErrors(List<Object> list){
		for(Object o : list)
			if(o instanceof ValidationErrors) return (ValidationErrors)o;
		return null;
	}
	
	public void registerRule(String bindUri, String ruleFile) throws Exception{
		String ruleXml = getFileContext(ruleFile);
		deploymetService.registerRuleXml(bindUri, ruleXml);
		assertTrue("Rule deployed", true);
	}
	
	public void unregisterRule(String bindUri) throws Exception{
		deploymetService.deregisterRuleSet(bindUri);
		assertTrue("Rule undeployed", true);
	}
}
