package gov.nih.nci.cabig.caaers.rules.repository;

import java.util.List;

import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetRegisterException;

public interface RuleExecutionSetRepository {

    public List getRegistrations();

    public RuleExecutionSet getRuleExecutionSet(final String bindUri);

    public void registerRuleExecutionSet(final String bindUri, final RuleExecutionSet ruleSet)
                    throws RuleExecutionSetRegisterException;

    public void unregisterRuleExecutionSet(final String bindUri);
}
