package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author vinaykumar
 * 
 * This is a facacde for authoring and deployment
 *
 */

public interface RulesEngineService {
	
	/**
	 *  For creating RuleSets
	 *
	 */
	public void createRuleSetForSponsor( String ruleSetName,String sponsorName);
	
	public void createRuleSetForInstitution(String ruleSetName, String institutionName);
	
	public void createRuleSetForStudy(String ruleSetName,String studyShortTitle, String sponsorName);
	
	
	
	/**
	 * getRuleSetForSponsor( String ruleSetName, String sponsorName)
	 * 
	 * getRuleSetForInstitution( String ruleSetName String institutionName)
	 * 
	 * getRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName)
	 *
	 */
	public RuleSet getRuleSetForSponsor( String ruleSetName, String sponsorName);
	
	public RuleSet  getRuleSetForInstitution( String ruleSetName, String institutionName);
	
	public RuleSet getRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName);
	
	
	/**
	 * This will be common for all the entities
	 *
	 */
	
	public void updateRuleSet();
	
	/**
	 * createRuleForSponsor(Rule rule, String ruleSetName,String sponsorName)
	 * 
	 *  createRuleForInstitution(Rule rule, String ruleSetName, String institutionName)
	 *  
	 *  createRuleForStudy(Rule rule, String ruleSetName, String studyShortTitle, String sponsorName)
	 *  
	 *
	 */
	
	public String createRuleForSponsor(Rule rule, String ruleSetName,String sponsorName);
	
	public String createRuleForInstitution(Rule rule, String ruleSetName, String institutionName);
	
	public String createRuleForStudy(Rule rule, String ruleSetName, String studyShortTitle, String sponsorName);
	
	
	
	/**
	 * Just pass ruleId
	 *
	 */
	
	public Rule getRule(String uuID);
	
	/**
	 * Same
	 *
	 */
	
	public void updateRule(Rule rule);
	
	/**
	 * By Category path
	 *
	 */
	
	public List<Rule> getRulesByCategory(String categoryPath);
	
	public List<RuleSet> getAllRuleSets();
	
	public void addRuleExecutionSet(final String bindUri,
			final InputStream resourceAsStream, final Map properties);
	

}
