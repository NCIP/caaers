package gov.nih.nci.cabig.caaers.rules.runtime;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

public class ReportLevelBusinessRulesTest extends
		BusinessRulesExecutionServiceTest {

	@Override
	public String getBindUri() {
		// TODO Auto-generated method stub
		return "gov.nih.nci.cabig.caaers.rules.reporting_submit_report_section";
	}

	@Override
	public String getRuleFile() {
		// TODO Auto-generated method stub
		return "submit_section_rule.xml";
	}
	
	/**
	 * RuleName : SEC_BR1_CHK
	Rule : Either Course Information and/or Radiation Intervention must be provided for AGENTS + RADIATION pathways
	Error Code : SEC_BR1_ERR
	Error Message :  Either COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
	 */
	public void testChemo_RadiationStudy_HavingRadiation() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
	}
	

	/**
	 * RuleName : SEC_BR1_CHK
	Rule : Either Course Information and/or Radiation Intervention must be provided for AGENTS + RADIATION pathways
	Error Code : SEC_BR1_ERR
	Error Message :  Either COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
	 */
	public void testChemo_RadiationStudy_HavingCourse() throws Exception {
		
	}
	

	/**
	 * RuleName : SEC_BR1_CHK
	Rule : Either Course Information and/or Radiation Intervention must be provided for AGENTS + RADIATION pathways
	Error Code : SEC_BR1_ERR
	Error Message :  Either COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
	 */
	public void testChemo_RadiationStudy_HavingRadiationAndCourse() throws Exception {
		
	}
	
	

	/**
	 * RuleName : SEC_BR1_CHK
	Rule : Either Course Information and/or Radiation Intervention must be provided for AGENTS + RADIATION pathways
	Error Code : SEC_BR1_ERR
	Error Message :  Either COURSE_INFORMATION and/or RADIATION_INTERVENTION must be provided.
	 */
	public void testChemo_RadiationStudy_NotHavingRadiationOrCourse() throws Exception {
		
	}
	
}
