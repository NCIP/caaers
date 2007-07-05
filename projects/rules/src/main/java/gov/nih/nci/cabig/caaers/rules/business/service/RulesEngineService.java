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
	/*
	 * REVISIT: Make it private
	 */
	public RuleSet createRuleSetForSponsor( String ruleSetName,String sponsorName) throws Exception;
	
	/*
	 * REVISIT: Make it private
	 */
	public RuleSet createRuleSetForInstitution(String ruleSetName, String institutionName) throws Exception;
	
	/*
	 * REVISIT: Make it private
	 */
	public RuleSet createRuleSetForSponsorDefinedStudy(String ruleSetName,String studyShortTitle, String sponsorName) throws Exception;
	
	/*
	 * REVISIT: Make it private
	 */
	public RuleSet createRuleSetForInstitutionDefinedStudy(String ruleSetName,String studyShortTitle, String institutionName) throws Exception;
	
	
	/**
	 * getRuleSetForSponsor( String ruleSetName, String sponsorName)
	 * 
	 * getRuleSetForInstitution( String ruleSetName String institutionName)
	 * 
	 * getRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName)
	 *
	 */
	public RuleSet getRuleSetForSponsor( String ruleSetName, String sponsorName) throws Exception;
	
	public RuleSet  getRuleSetForInstitution( String ruleSetName, String institutionName) throws Exception;
	
	public RuleSet getRuleSetForSponsorDefinedStudy(String ruleSetName, String studyShortTitle, String sponsorName) throws Exception;
	
	public RuleSet getRuleSetForInstitutionDefinedStudy(String ruleSetName, String studyShortTitle, String institutionName) throws Exception;
	
	
	
	
	
	public List<RuleSet> getAllRuleSetsForSponsor(String sponsorName) throws Exception;
	
	public List<RuleSet> getAllRuleSetsForInstitution(String institutionName) throws Exception;
	
	public List<RuleSet> getAllRuleSetsForSponsorDefinedStudy(String studyShortTitle, String sponsorName) throws Exception;
	
	public List<RuleSet> getAllRuleSetsForInstitutionDefinedStudy(String studyShortTitle, String institutionName) throws Exception;
	
	
	/**
	 * This will be common for all the entities
	 *
	 */
	
	public void updateRuleSet(RuleSet ruleSet) throws Exception;
	
	/**
	 * createRuleForSponsor(Rule rule, String ruleSetName,String sponsorName)
	 * 
	 *  createRuleForInstitution(Rule rule, String ruleSetName, String institutionName)
	 *  
	 *  createRuleForStudy(Rule rule, String ruleSetName, String studyShortTitle, String sponsorName)
	 *  
	 *
	 */
	
	public String createRuleForSponsor(Rule rule, String ruleSetName,String sponsorName) throws Exception;
	
	public String createRuleForInstitution(Rule rule, String ruleSetName, String institutionName) throws Exception;
	
	public String createRuleForSponsorDefinedStudy(Rule rule, String ruleSetName, String studyShortTitle, String sponsorName) throws Exception;
	
	public String createRuleForInstitutionDefinedStudy(Rule rule, String ruleSetName, String studyShortTitle, String institutionName) throws Exception;
	
	/*
	 * REVISIT: Add 3 more methods similar to the above methods that take RuleSet as the input parameter
	 */
	//public String createRulesForXYZ(RuleSet ruleSet, String entityName) throws Exception;
	
	public void saveRulesForSponsor(RuleSet ruleSet, String sponsorName) throws Exception;
	
	public void saveRulesForInstitution(RuleSet ruleSet, String institutionName) throws Exception;
	
	public void saveRulesForSponsorDefinedStudy(RuleSet ruleSet, String studyShortTitle, String sponsorName) throws Exception;
	
	public void saveRulesForInstitutionDefinedStudy(RuleSet ruleSet, String studyShortTitle, String institutionName) throws Exception;
	
	/**
	 * Just pass ruleId
	 *
	 */
	
	public Rule getRule(String uuID) throws Exception;
	
	/**
	 * Same
	 *
	 */
	
	public void updateRule(Rule rule) throws Exception;
	
	/**
	 * By Category path
	 *
	 */
	
	public List<Rule> getRulesByCategory(String categoryPath) throws Exception;
	
	public List<RuleSet> getAllRuleSets() throws Exception;
	
	
	public void deployRuleSet(RuleSet ruleSet) throws Exception;
	
	public void unDeployRuleSet(RuleSet set) throws Exception;
	
	public boolean isDeployed(RuleSet ruleSet);
	
	

}
