package gov.nih.nci.cabig.caaers.rules.repository;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;

import java.rmi.RemoteException;
import java.util.List;

import org.drools.repository.RulesRepository;

/**
 * 
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public interface RepositoryService {

    /**
     * This will create a new category at the specified path.
     */
    public Boolean createCategory(Category category);

    public void deleteRuleSet(String ruleSet) throws Exception;

    public void deleteRule(String ruleSetName, String ruleName);

    /**
     * Returns the Category associated with the category path passed in as argument
     */
    public Category getCategory(String categoryPath);

    /**
     * Creates a brand new rule with the initial category. Return the UUID of the item created.
     * String ruleName, String description, String initialCategory, String initialPackage, String
     * format
     */
    public String createRule(Rule rule);

    public void updateRule(Rule rule);

    /**
     * Move the rule from one Ruleset/package to another
     */
    public void moveRule(String newRuleSetName, String ruleId);

    /**
     * 
     */
    public String createRuleSet(RuleSet ruleSet);

    /**
     * 
     * This returns a list of packages where rules may be added.
     */
    public List<RuleSet> listRuleSets();

    /**
     * Loads a package by its name (NOT UUID !).
     * 
     * @param name
     *                The name of the package (NOT THE UUID !).
     * @return Well, its pretty obvious if you think about it for a minute. Really.
     * @throws RemoteException
     */
    public RuleSet getRuleSet(String name, boolean cached);

    /**
     * This loads up all the stuff for a rule asset based on the UUID (always latest and editable
     * version).
     * 
     * @param ruleId
     *                UUID
     */
    public Rule getRule(String ruleId);

    /**
     * This checks in a new version of an asset.
     * 
     * @return the UUID of the asset you are checking in, null if there was some problem (and an
     *         exception was not thrown).
     */
    public String checkinVersion(Rule rule);

    public String registerRuleSet(String name, RuleSetInfo ruleSetInfo);

    public RuleSetInfo[] listRegistrations();

    public RuleSetInfo getRegisteredRuleset(String bindUri);

    public void deregisterRuleExecutionSet(String bindUri);

    public List<Rule> getRulesByCategory(String string);

    public List<String> getAllImmediateChildren(String categoryPath);

    // REVISIT! Remove once the testing is done
    public RulesRepository getRulesRepository();

    public boolean containsRuleSet(String ruleSetName);

}