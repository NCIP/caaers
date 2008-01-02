package gov.nih.nci.cabig.caaers.rules.runtime;

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
		aeReport.getAdverseEventPriorTherapies().get(1).setOther("Other");
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
		aeReport.getAdverseEventPriorTherapies().get(0).setOther("Other");
		aeReport.getAdverseEventPriorTherapies().get(1).getPriorTherapy().setText("No Prior Therapy");
		aeReport.getAdverseEventPriorTherapies().get(1).setOther(null);
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "PTY_BR1_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Correct replacement variable value", 2, errors.getErrorAt(0).getReplacementVariables()[0]);

	}
	
	
}
