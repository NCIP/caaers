package gov.nih.nci.cabig.caaers.rules.repository;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.CompiledPackageItem;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Sujith Vellat Thayyilthodi 
 * */
public interface RepositoryService extends Remote {

	/**
     * This will create a new category at the specified path.
     */
    public Boolean createCategory(Category category) throws RemoteException;
    
    /**
     * Creates a brand new rule with the initial category.
     * Return the UUID of the item created.
     * String ruleName, String description, String initialCategory, String initialPackage, String format
     */
    public String createRule(Rule rule) throws RemoteException;
    
    
    public void updateRule(Rule rule) throws RemoteException;
    
    /**
     * Move the rule from one Ruleset/package to another
     * @throws RemoteException 
     *  
     * */
    public void moveRule(String newRuleSetName, String ruleId) throws RemoteException;
    
    /**
     * 
     * */
    public String createRuleSet(RuleSet ruleSet) throws RemoteException;
    
    /**
     * 
     * This returns a list of packages where rules may be added.
     */
    public RuleSet[] listRuleSets() throws RemoteException;

    
    /**
     * Loads a package by its name (NOT UUID !).
     * @param name The name of the package (NOT THE UUID !).
     * @return Well, its pretty obvious if you think about it for a minute. Really.
     */
    public RuleSet getRuleSet(String name);
    
    /**
     * This loads up all the stuff for a 
     * rule asset based on the UUID (always latest and editable version).
     * @param ruleId UUID
     */
    public Rule getRule(String ruleId) throws RemoteException;    
    
    /**
     * This checks in a new version of an asset. 
     * @return the UUID of the asset you are checking in, 
     * null if there was some problem (and an exception was not thrown).
     */
    public String checkinVersion(Rule rule) throws RemoteException;
    
    
    public String registerRuleSet(String name, RuleSetInfo ruleSetInfo) throws RemoteException;

	
    public RuleSetInfo[] listRegistrations();

    
	public RuleSetInfo getRegisteredRuleset(String bindUri);

	
	public void deregisterRuleExecutionSet(String bindUri);



}