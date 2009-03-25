package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class PreExistingConditionBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {

    @Override
    public String getBindUri() {
        return "gov.nih.nci.cabig.caaers.rules.reporting_pre_existing_condition_section";
    }

    @Override
    public String getRuleFile() {
        return "rules_reporting_pre_existing_conditions.xml";
    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testNoPreConditionAndNoOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().clear();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when there are no pre-conditions and other");
    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testPreConditionOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().get(0).setOther(null);
        aeReport.getSaeReportPreExistingConditions().get(1).setOther(null);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when there are  other");

    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testOtherOnly() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().get(0).setPreExistingCondition(null);
        aeReport.getSaeReportPreExistingConditions().get(1).setPreExistingCondition(null);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "when there are  pre conditions only");

    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testBothPreConditionAndOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 2);
        assertCorrectErrorCode(errors, "PEC_BR1_ERR");

    }

    /**
     * RuleName : PEC_BR1_CHK Logic : 'Pre-Existing Condition' must not be provided if 'Other
     * Pre-Existing Condition' is provided and vice-versa Error Code : PEC_BR1_ERR Error Message :
     * Either and only CONDITION_NAME or OTHER_CONDITION_NAME must be provided
     */
    public void testOneOutOfTwoHasBothPreConditionAndOther() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        aeReport.getSaeReportPreExistingConditions().get(0).setPreExistingCondition(null);
        ValidationErrors errors = fireRules(aeReport);
        assertSameErrorCount(errors, 1);
        assertCorrectErrorCode(errors, "PEC_BR1_ERR");

    }

}
