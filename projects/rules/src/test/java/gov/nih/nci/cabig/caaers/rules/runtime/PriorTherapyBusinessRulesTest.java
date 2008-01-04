package gov.nih.nci.cabig.caaers.rules.runtime;

import java.util.Date;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class PriorTherapyBusinessRulesTest extends
		BusinessRulesExecutionServiceTest {

	@Override
	public String getBindUri() {
		// TODO Auto-generated method stub
		return "gov.nih.nci.cabig.caaers.rules.reporting_prior_therapies_section";
	}

	@Override
	public String getRuleFile() {
		// TODO Auto-generated method stub
		return "prior_therapy_rule.xml";
	}
	
	
	/**
	 * RuleName : PTY_BR1_CHK
	Logic : "Comments (prior therapy) must be provided if 'Prior therapy' is 'No Prior Therapy'"
	Error Code : PTY_BR1_ERR
	Error Message : COMMENTS must be provided (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
	 */
	public void testCorrectPriorTherapy() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When other comments is not provided for a non 'No Prior Therapy'");
	}
	
	/**
	 * RuleName : PTY_BR1_CHK
	Logic : "Comments (prior therapy) must be provided if 'Prior therapy' is 'No Prior Therapy'"
	Error Code : PTY_BR1_ERR
	Error Message : COMMENTS must be provided (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
	 */
	public void testNoPriorTherapy_With_OtherComments() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getAdverseEventPriorTherapies().get(0).getPriorTherapy().setText("No Prior Therapy");
		aeReport.getAdverseEventPriorTherapies().get(0).setOther("Other");
		aeReport.getAdverseEventPriorTherapies().get(1).getPriorTherapy().setText("No Prior Therapy");
		aeReport.getAdverseEventPriorTherapies().get(1).setOther("Other1");
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When other comments is provided for a non 'No Prior Therapy'");
	}
	
	/**
	 * RuleName : PTY_BR1_CHK
	Logic : "Comments (prior therapy) must be provided if 'Prior therapy' is 'No Prior Therapy'"
	Error Code : PTY_BR1_ERR
	Error Message : COMMENTS must be provided (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
	 */
	public void testNoPriorTherapy_Without_OtherComments() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();	
		aeReport.getAdverseEventPriorTherapies().get(0).getPriorTherapy().setText("No Prior Therapy");
		aeReport.getAdverseEventPriorTherapies().get(0).setEndDate(new Date());
		System.out.println(aeReport.getAdverseEventPriorTherapies().get(0).getName());
		aeReport.getAdverseEventPriorTherapies().get(0).setOther(null);
		aeReport.getAdverseEventPriorTherapies().get(1).getPriorTherapy().setText("No Prior Therapy");
		aeReport.getAdverseEventPriorTherapies().get(1).setOther(null);
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "PTY_BR1_ERR");
		assertSameErrorCount(errors, 2);
		assertEquals("Correct replacement variable value", 2, errors.getErrorAt(1).getReplacementVariables()[0]);
	}
	

	/**
	 * RuleName : PTY_BR1_CHK
	Logic : "Comments (prior therapy) must be provided if 'Prior therapy' is 'No Prior Therapy'"
	Error Code : PTY_BR1_ERR
	Error Message : COMMENTS must be provided (including appropriate Prior Therapy) if PRIOR_THERAPY is "No Prior Therapy NOS"
	 */
	public void testOneOutOfTwoPriorTherapy_IsWithout_OtherComments() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();	
		aeReport.getAdverseEventPriorTherapies().get(0).getPriorTherapy().setText("No Prior Therapy");
		aeReport.getAdverseEventPriorTherapies().get(0).setEndDate(new Date());
		aeReport.getAdverseEventPriorTherapies().get(0).setOther("Other");
		aeReport.getAdverseEventPriorTherapies().get(1).getPriorTherapy().setText("No Prior Therapy");
		aeReport.getAdverseEventPriorTherapies().get(1).setOther(null);
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "PTY_BR1_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Correct replacement variable value", 2, errors.getErrorAt(0).getReplacementVariables()[0]);

	}
	
	
	/**
	RuleName : PTY_UK_CHK
	Logic : Prior Therapy must be unique
	Error Code : PTY_UK_ERR
	Error Message : PRIOR_THERAPY must be unique	 */
	public void testUniquePriorTherapy() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();	
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors,"When prior therapy is unique");

	}
	
	/**
	RuleName : PTY_UK_CHK
	Logic : Prior Therapy must be unique
	Error Code : PTY_UK_ERR
	Error Message : PRIOR_THERAPY must be unique	 */
	public void testDuplicatePriorTherapy() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getAdverseEventPriorTherapies().get(0).getPriorTherapy().setText("ll");
		aeReport.getAdverseEventPriorTherapies().get(1).getPriorTherapy().setText("ll");
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "PTY_UK_ERR");
		assertSameErrorCount(errors, 1);
	}
	
	/**
	RuleName : PTY_UK_CHK
	Logic : Prior Therapy must be unique
	Error Code : PTY_UK_ERR
	Error Message : PRIOR_THERAPY must be unique	 */
	public void testTwoOutOfThreeSamePriorTherapy() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.addAdverseEventPriorTherapies(aeReport.getAdverseEventPriorTherapies().get(1));
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "PTY_UK_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Replacement variable incorrect", 3, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
	
	
	/**
	 * RuleName : PTY_BR4A_CHK
	Logic : ‘Prior Therapy Agents’ must be provided if "Prior_Therapy" is 
			‘Bone Marrow Transplant’
			‘Chemotherapy (NOS)’
			‘Chemotherapy multiple agents systemic’
			‘Chemotherapy single agent systemic’
			‘Immunotherapy’
			‘Hormonal Therapy’
	Error Code : PTY_BR4B_ERR
	Error Message : CHEMO_AGENTS must be provided for the  provided PRIOR_THERAPY value.
	 */
	
	public void testBMTPriorTherapy_With_PriorTherapyAgents() throws Exception {
		fail("Not implemented");
	}
	
	/**
	 * RuleName : PTY_BR4A_CHK
	Logic : ‘Prior Therapy Agents’ must be provided if "Prior_Therapy" is 
			‘Bone Marrow Transplant’
			‘Chemotherapy (NOS)’
			‘Chemotherapy multiple agents systemic’
			‘Chemotherapy single agent systemic’
			‘Immunotherapy’
			‘Hormonal Therapy’
	Error Code : PTY_BR4B_ERR
	Error Message : CHEMO_AGENTS must be provided for the  provided PRIOR_THERAPY value.
	 */
	
	public void testBMTPriorTherapy_Without_PriorTherapyAgents() throws Exception {
		fail("Not implemented");
	}
	
}
