package gov.nih.nci.cabig.caaers.rules.runtime.cache;

import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;

import java.util.Hashtable;

public class RulesCache implements RuleSetModificationListener,
                RuleExecutionSetModificationListener {
    private static RulesCache instance = null;

    private Hashtable<String, RuleSet> ruleSetCache = null;

    // private Hashtable<String,RuleExecutionSet> ruleExecutionSetCache = null;

    public static synchronized RulesCache getInstance() {
        if (instance == null) {
            instance = new RulesCache();
        }
        return instance;
    }

    private RulesCache() {
        ruleSetCache = new Hashtable<String, RuleSet>();
        // ruleExecutionSetCache = new Hashtable<String,RuleExecutionSet>();
    }

    public void ruleSetModified(String uri) {
        // TODO Auto-generated method stub
        this.removeRuleSet(uri);
    }

    public void putRuleSet(String uri, RuleSet ruleSet) {
        if (!ruleSetCache.containsKey(uri)) {
            System.out.println("put in map " + uri);
            ruleSetCache.put(uri, ruleSet);
        }
    }

    public RuleSet getRuleSet(String uri) {
        return ruleSetCache.get(uri);
    }

    private void removeRuleSet(String uri) {
        if (ruleSetCache.containsKey(uri)) {
            ruleSetCache.remove(uri);
        }
    }

    // public void putRuleExecutionSet(String uri, RuleExecutionSet ruleExecutionSet){
    // System.out.println ("put in exec map "+uri);
    // ruleExecutionSetCache.put(uri, ruleExecutionSet);
    // }

    // public RuleExecutionSet getRuleExecutionSet(String uri){
    // return ruleExecutionSetCache.get(uri);
    // }
    public void ruleSetDeployed(String uri) {
        // TODO Auto-generated method stub
        // if(ruleExecutionSetCache.containsKey(uri)){
        // / ruleExecutionSetCache.remove(uri);
        // }

    }

}
