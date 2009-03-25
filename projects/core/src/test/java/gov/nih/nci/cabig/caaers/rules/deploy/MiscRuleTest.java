package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

public class MiscRuleTest extends AbstractBusinessRulesExecutionTestCase {

    @Override
    public String getBindUri() {
        // TODO Auto-generated method stub
        return "gov.nih.nci.cabig.caaers.rules.reporting_misc_rule";
    }

    @Override
    public String getRuleFile() {
        // TODO Auto-generated method stub
        return "misc_rule.xml";
    }

    public void testMicsRule() throws Exception {
        ExpeditedAdverseEventReport aeReport = super.createAEReport();
        System.out.println(fireRules(aeReport));
    }
}
