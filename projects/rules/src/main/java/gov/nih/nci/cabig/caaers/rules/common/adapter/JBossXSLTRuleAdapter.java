package gov.nih.nci.cabig.caaers.rules.common.adapter;

import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.rule.Package;

public class JBossXSLTRuleAdapter implements RuleAdapter {
    private static final Log log = LogFactory.getLog(JBossXSLTRuleAdapter.class);

    public Package adapt(RuleSet ruleSet) {
        if (true) {
            throw new RuntimeException(
                            "Deprecated, dont use JBossXSLTRuleAdapter, instead use CaAERSJBossXSLTRuleAdapter");
        }
        return null;
    }

}