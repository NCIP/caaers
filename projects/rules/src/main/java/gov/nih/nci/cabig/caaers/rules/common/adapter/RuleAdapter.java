package gov.nih.nci.cabig.caaers.rules.common.adapter;

import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;

import org.drools.rule.Package;

public interface RuleAdapter {
    public Package adapt(RuleSet ruleSet);
}
