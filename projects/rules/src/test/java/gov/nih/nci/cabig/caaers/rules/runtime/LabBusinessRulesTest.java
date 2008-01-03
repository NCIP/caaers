package gov.nih.nci.cabig.caaers.rules.runtime;

import java.util.Date;

import edu.nwu.bioinformatics.commons.DateUtils;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.LabValue;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class LabBusinessRulesTest extends BusinessRulesExecutionServiceTest {

	@Override
	public String getBindUri() {
		return "gov.nih.nci.cabig.caaers.rules.reporting_labs_section";
	}

	@Override
	public String getRuleFile() {
		// TODO Auto-generated method stub
		return "Labs_rule.xml";
	}
	
	/**
	 * RuleName : LAB_BR1_CHK
	Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab'  is provided and vice versa
	Error Code : LAB_BR1_ERR
	Error Message : Either and only LAB_CATEGORY and LAB_NAME or OTHER_LAB must be provided.
	
	RuleName : LAB_BR3_CHK
	Logic :"“Nadir/Worst Date” must not be greater “Baseline Date”."
	Error Code : LAB_BR3_ERR
	Error Message : WORST_DATE must not be greater BASELINE_DATE
	
	RuleName : LAB_UK_CHK
	Logic :Lab Results must be unique
	Error Code : LAB_UK_ERR
	Error Message :Lab Results must be unique
	
	 */
	public void testNoLab() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getLabs().clear();
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When there are no labs");
	}
	
	/**
	 * RuleName : LAB_BR1_CHK
	Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab'  is provided and vice versa
	Error Code : LAB_BR1_ERR
	Error Message : Either and only LAB_CATEGORY and LAB_NAME or OTHER_LAB must be provided.
	
	RuleName : LAB_UK_CHK
	Logic :Lab Results must be unique
	Error Code : LAB_UK_ERR
	Error Message :Lab Results must be unique
	 */
	public void testLabWithOnlyName() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When only names are there for labs");
	}
	/**
	 * RuleName : LAB_BR1_CHK
	Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab'  is provided and vice versa
	Error Code : LAB_BR1_ERR
	Error Message : Either and only LAB_CATEGORY and LAB_NAME or OTHER_LAB must be provided.
	
	RuleName : LAB_UK_CHK
	Logic :Lab Results must be unique
	Error Code : LAB_UK_ERR
	Error Message :Lab Results must be unique
	 */
	public void testLabWithOnlyOther() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getLabs().get(0).getLabTerm().setTerm(null);
		aeReport.getLabs().get(0).setOther("other1");
		aeReport.getLabs().get(1).getLabTerm().setTerm(null);
		aeReport.getLabs().get(1).setOther("other2");
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When only OtherName are there for labs");
	}
	/**
	 * RuleName : LAB_BR1_CHK
	Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab'  is provided and vice versa
	Error Code : LAB_BR1_ERR
	Error Message : Either and only LAB_CATEGORY and LAB_NAME or OTHER_LAB must be provided.
	 */
	public void testLabWithNameAndOther() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getLabs().get(0).setOther("other1");
		aeReport.getLabs().get(1).setOther("other2");
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_BR1_ERR");
		assertSameErrorCount(errors, 2);
		assertEquals("Replacement values incorrect", 1, errors.getErrorAt(0).getReplacementVariables()[0]);
		assertEquals("Replacement values incorrect", 2, errors.getErrorAt(1).getReplacementVariables()[0]);
	}
	
	/**
	 * RuleName : LAB_BR1_CHK
	Logic :'Lab Category' and 'Lab' must not be provided if 'Other Lab'  is provided and vice versa
	Error Code : LAB_BR1_ERR
	Error Message : Either and only LAB_CATEGORY and LAB_NAME or OTHER_LAB must be provided.
	 */
	public void testOneOutOfTwoHasBothNameAndOther() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getLabs().get(1).setOther("other2");
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_BR1_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Replacement values incorrect", 2, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
	
	
	/**
	 * RuleName : LAB_BR3_CHK
	Logic :"“Nadir/Worst Date” must not be greater “Baseline Date”."
	Error Code : LAB_BR3_ERR
	Error Message : WORST_DATE must not be greater BASELINE_DATE
	 */
	public void testLabsHavingOnlyBaselineDate() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		LabValue bv1 = new LabValue();
		bv1.setValue("33");
		bv1.setDate(new Date());
		
		aeReport.getLabs().get(0).setBaseline(bv1);
		aeReport.getLabs().get(1).setBaseline(bv1);
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When there is only baseline date");
	}
	
	/**
	 * RuleName : LAB_BR3_CHK
	Logic :"“Nadir/Worst Date” must not be greater “Baseline Date”."
	Error Code : LAB_BR3_ERR
	Error Message : WORST_DATE must not be greater BASELINE_DATE
	
	 * RuleName : LAB_BR4_CHK
	Logic :"“Recovery Date” must not be greater than “Nadir/Worst Date”.
	Error Code : LAB_BR4_ERR
	Error Message : RECOVERY_LAST_DATE must not be greater than WORST_DATE
	 */
	public void testLabsHavingOnlyWorstDate() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		LabValue wv1 = new LabValue();
		wv1.setValue("33");
		wv1.setDate(new Date());
		
		aeReport.getLabs().get(0).setNadir(wv1);
		aeReport.getLabs().get(1).setNadir(wv1);
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When there is only Worst date");
	}
	
	/**
	 * RuleName : LAB_BR3_CHK
	Logic :"“Nadir/Worst Date” must not be greater “Baseline Date”."
	Error Code : LAB_BR3_ERR
	Error Message : WORST_DATE must not be greater BASELINE_DATE
	 */
	public void testLabsHavingBaselineDateGTWorstDate() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		
		LabValue bv1 = new LabValue();
		bv1.setValue("33");
		bv1.setDate(DateUtils.createDate(2007, 12, 1));
		aeReport.getLabs().get(0).setBaseline(bv1);
		aeReport.getLabs().get(1).setBaseline(bv1);
		
		LabValue wv1 = new LabValue();
		wv1.setValue("33");
		wv1.setDate(DateUtils.createDate(2007, 1, 3));
		
		aeReport.getLabs().get(0).setNadir(wv1);
		aeReport.getLabs().get(1).setNadir(wv1);
		
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When Baseline date is greater than Worst date");
	}
	/**
	 * RuleName : LAB_BR3_CHK
	Logic :"“Nadir/Worst Date” must not be greater “Baseline Date”."
	Error Code : LAB_BR3_ERR
	Error Message : WORST_DATE must not be greater BASELINE_DATE
	 */
	public void testLabsHavingBaselineDateLTWorstDate() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		
		LabValue bv1 = new LabValue();
		bv1.setValue("33");
		bv1.setDate(DateUtils.createDate(2007, 1, 1));
		aeReport.getLabs().get(0).setBaseline(bv1);
		aeReport.getLabs().get(1).setBaseline(bv1);
		
		LabValue wv1 = new LabValue();
		wv1.setValue("33");
		wv1.setDate(DateUtils.createDate(2007, 1, 3));
		
		aeReport.getLabs().get(0).setNadir(wv1);
		aeReport.getLabs().get(1).setNadir(wv1);
		
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_BR3_ERR");
		assertSameErrorCount(errors, 2);
		assertEquals("Replcement incorrect", 1, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
	
	/**
	 * RuleName : LAB_BR3_CHK
	Logic :"“Nadir/Worst Date” must not be greater “Baseline Date”."
	Error Code : LAB_BR3_ERR
	Error Message : WORST_DATE must not be greater BASELINE_DATE
	 */
	public void testLabsOneOutOfTwoHavingBaselineDateLTWorstDate() throws Exception {
ExpeditedAdverseEventReport aeReport = createAEReport();
		
		LabValue bv1 = new LabValue();
		bv1.setValue("33");
		bv1.setDate(DateUtils.createDate(2007, 1, 1));
		aeReport.getLabs().get(0).setBaseline(bv1);
		aeReport.getLabs().get(1).setBaseline(bv1);
		
		LabValue wv1 = new LabValue();
		wv1.setValue("33");
		wv1.setDate(DateUtils.createDate(2006, 1, 3));
		LabValue wv2 = new LabValue();
		wv2.setValue("33");
		wv2.setDate(DateUtils.createDate(2007, 1, 3));
		aeReport.getLabs().get(0).setNadir(wv1);
		aeReport.getLabs().get(1).setNadir(wv2);
		
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_BR3_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Replcement incorrect", 2, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
	
	/**
	 * RuleName : LAB_BR4_CHK
	Logic :"“Recovery Date” must not be greater than “Nadir/Worst Date”.
	Error Code : LAB_BR4_ERR
	Error Message : RECOVERY_LAST_DATE must not be greater than WORST_DATE
	 */
	public void testRecoverDateOnly() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		LabValue rv1 = new LabValue();
		rv1.setValue("33");
		rv1.setDate(new Date());
		
		aeReport.getLabs().get(0).setRecovery(rv1);
		aeReport.getLabs().get(1).setRecovery(rv1);
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When there is only recovery date");
	}
	
	
	
	/**
	 * RuleName : LAB_BR4_CHK
	Logic :"“Recovery Date” must not be greater than “Nadir/Worst Date”.
	Error Code : LAB_BR4_ERR
	Error Message : RECOVERY_LAST_DATE must not be greater than WORST_DATE
	 */
	public void testWorstDateEqualToRecoveryDate() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		
		LabValue rv1 = new LabValue();
		rv1.setValue("33");
		rv1.setDate(DateUtils.createDate(2007, 1, 3));
		aeReport.getLabs().get(0).setRecovery(rv1);
		aeReport.getLabs().get(1).setRecovery(rv1);
		
		LabValue wv1 = new LabValue();
		wv1.setValue("33");
		wv1.setDate(DateUtils.createDate(2007, 1, 3));
		
		aeReport.getLabs().get(0).setNadir(wv1);
		aeReport.getLabs().get(1).setNadir(wv1);
		
		ValidationErrors errors = fireRules(aeReport);
		assertNoErrors(errors, "When recovery date is same as that of Worst date");
	}
	/**
	 * RuleName : LAB_BR4_CHK
	Logic :"“Recovery Date” must not be greater than “Nadir/Worst Date”.
	Error Code : LAB_BR4_ERR
	Error Message : RECOVERY_LAST_DATE must not be greater than WORST_DATE
	 */
	public void testWorstDateLTRecoveryDate() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		
		LabValue rv1 = new LabValue();
		rv1.setValue("33");
		rv1.setDate(DateUtils.createDate(2007, 1, 3));
		aeReport.getLabs().get(0).setRecovery(rv1);
		aeReport.getLabs().get(1).setRecovery(rv1);
		
		LabValue wv1 = new LabValue();
		wv1.setValue("33");
		wv1.setDate(DateUtils.createDate(2006, 1, 3));
		
		aeReport.getLabs().get(0).setNadir(wv1);
		aeReport.getLabs().get(1).setNadir(wv1);
		
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_BR4_ERR");
		assertSameErrorCount(errors, 2);
		assertEquals("Replcement incorrect", 1, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
	/**
	 * RuleName : LAB_BR4_CHK
	Logic :"“Recovery Date” must not be greater than “Nadir/Worst Date”.
	Error Code : LAB_BR4_ERR
	Error Message : RECOVERY_LAST_DATE must not be greater than WORST_DATE
	 */
	public void testOneOutOfTwoHavingWorstDateLTRecoveryDate() throws Exception {
		ExpeditedAdverseEventReport aeReport = createAEReport();
		
		LabValue rv1 = new LabValue();
		rv1.setValue("33");
		rv1.setDate(DateUtils.createDate(2007, 1, 3));
		aeReport.getLabs().get(0).setRecovery(rv1);
		aeReport.getLabs().get(1).setRecovery(rv1);
		
		LabValue wv1 = new LabValue();
		wv1.setValue("33");
		wv1.setDate(DateUtils.createDate(2006, 1, 3));
		LabValue wv2 = new LabValue();
		wv2.setValue("33");
		wv2.setDate(DateUtils.createDate(2007, 1, 3));
		aeReport.getLabs().get(0).setNadir(wv1);
		aeReport.getLabs().get(1).setNadir(wv2);
		
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_BR4_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Replcement incorrect", 1, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
	
	/**
	 * RuleName : LAB_UK_CHK
	Logic :Lab Results must be unique
	Error Code : LAB_UK_ERR
	Error Message :Lab Results must be unique
	 */
	public void testDuplicateLabs() throws Exception{
		ExpeditedAdverseEventReport aeReport = createAEReport();
		aeReport.getLabs().get(0).getLabTerm().setTerm("kk");
		aeReport.getLabs().get(1).getLabTerm().setTerm("kk");
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_UK_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Incorrect replacement", "kk", errors.getErrorAt(0).getReplacementVariables()[1]);
		assertEquals("Incorrect replacement", 2, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
	
	/**
	 * RuleName : LAB_UK_CHK
	Logic :Lab Results must be unique
	Error Code : LAB_UK_ERR
	Error Message :Lab Results must be unique
	 */
	public void testDuplicateLabsWithNameRecoveryAndBasline() throws Exception{
		ExpeditedAdverseEventReport aeReport = createAEReport();
		LabValue bl1 = new LabValue();
		bl1.setDate(DateUtils.createDate(2007, 11, 11));
		bl1.setValue("abcd1");
		
		LabValue bl2 = new LabValue();
		bl2.setDate(DateUtils.createDate(2007, 11, 11));
		bl2.setValue("abcd2");
		
		LabValue r1 = new LabValue();
		r1.setDate(DateUtils.createDate(2007, 11, 11));
		r1.setValue("abcd1");
		
		LabValue r21 = new LabValue();
		r21.setDate(DateUtils.createDate(2007, 11, 11));
		r21.setValue("abcd2");
		
		
		aeReport.getLabs().get(0).getLabTerm().setTerm("kk");
		aeReport.getLabs().get(0).setBaseline(bl1);
		aeReport.getLabs().get(0).setRecovery(r1);
		
		aeReport.getLabs().get(1).getLabTerm().setTerm("kk");
		aeReport.getLabs().get(0).setBaseline(bl2);
		aeReport.getLabs().get(0).setRecovery(r21);
		
		ValidationErrors errors = fireRules(aeReport);
		assertCorrectErrorCode(errors, "LAB_UK_ERR");
		assertSameErrorCount(errors, 1);
		assertEquals("Incorrect replacement", "kk", errors.getErrorAt(0).getReplacementVariables()[1]);
		assertEquals("Incorrect replacement", 2, errors.getErrorAt(0).getReplacementVariables()[0]);
	}
}
