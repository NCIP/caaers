package gov.nih.nci.cabig.caaers.audit;

import java.util.ArrayList;
import java.util.List;

public class DecisionContext {
    private FiredRuleSetInfo firedRuleSetInfo;

    private List<String> firedRuleNames = new ArrayList<String>();

    private List<Object> assertedObjects;

    private List<RuleExecutionStatus> executionSummary;

    public FiredRuleSetInfo getFiredRuleSetInfo() {
        return firedRuleSetInfo;
    }

    public void setFiredRuleSetInfo(FiredRuleSetInfo firedRuleSetInfo) {
        this.firedRuleSetInfo = firedRuleSetInfo;
    }

    public List<String> getFiredRuleNames() {
        return firedRuleNames;
    }

    public void setFiredRuleNames(List<String> firedRuleNames) {
        this.firedRuleNames = firedRuleNames;
    }

    public List<Object> getAssertedObjects() {
        return assertedObjects;
    }

    public void setAssertedObjects(List<Object> assertedObjects) {
        this.assertedObjects = assertedObjects;
    }

    public void buildExecutionSummary() throws Exception {
        executionSummary = new ArrayList<RuleExecutionStatus>();
        List<String> ruleNames = this.getFiredRuleSetInfo().getRuleNames();
        for (int i = 0; i < ruleNames.size(); i++) {
            String name = ruleNames.get(i);
            RuleExecutionStatus res = new RuleExecutionStatus();
            res.setRuleName(name);
            if (firedRuleNames.contains(name)) {
                res.setConditionMet(true);
                res.setFired(true);
            } else {
                res.setConditionMet(false);
                res.setFired(false);
            }
            executionSummary.add(res);
        }

    }

    public List<RuleExecutionStatus> getExecutionSummary() {
        return executionSummary;
    }

    public void setExecutionSumary(List<RuleExecutionStatus> executionSumary) {
        this.executionSummary = executionSumary;
    }

}
