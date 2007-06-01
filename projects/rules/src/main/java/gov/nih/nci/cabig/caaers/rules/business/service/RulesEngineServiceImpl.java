package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceImpl;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RulesRepositoryEx;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.drools.repository.RulesRepository;
/**
 * 
 * @author vinaykumar
 *
 */

public class RulesEngineServiceImpl implements RulesEngineService{
	
	private RuleAuthoringService ruleAuthoringService;
	private RuleDeploymentService ruleDeploymentService;
	private RepositoryService repositoryService;
	
	
	
	
	public RulesEngineServiceImpl(){
		ruleAuthoringService = new RuleAuthoringServiceImpl();
		ruleDeploymentService = new RuleDeploymentServiceImpl();
		
		this.repositoryService = (RepositoryServiceImpl)RuleServiceContext.getInstance().repositoryService;
	}
	

	public String createRuleForInstitution(Rule rule, String ruleSetName, String institutionName) throws Exception {
		// TODO Auto-generated method stub
		
		String uuid = null;
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), institutionName, ruleSetName);
		Category category = RuleUtil.getInstitutionSpecificCategory(ruleAuthoringService, institutionName, ruleSetName);
		
		if(rule.getMetaData()==null){
			rule.setMetaData(new MetaData());
		}
		
		rule.getMetaData().setPackageName(packageName);
		rule.getMetaData().getCategory().clear();
		rule.getMetaData().getCategory().add(category);
		
		/**
		 * This need to be added after discussion 
		 */
		Condition condition = rule.getCondition();
		Column column_fixed = new Column();
		column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
		column_fixed.setIdentifier("adverseEventEvaluationResult");
		
		condition.getColumn().add(column_fixed);
		
		
			uuid=ruleAuthoringService.createRule(rule);
	
		
		return uuid;
	}

	public String createRuleForSponsor(Rule rule, String ruleSetName, String sponsorName) throws Exception {
		// TODO Auto-generated method stub
		String uuid= null;
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
		Category category = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName, ruleSetName);
		
		System.out.println("Path of category:"+category.getPath());
		System.out.println("Name of category:"+category.getMetaData().getName());
		
		System.out.println("PackageName:"+packageName);
		if(rule.getMetaData()==null){
			rule.setMetaData(new MetaData());
		}
		
		rule.getMetaData().setPackageName(packageName);
		rule.getMetaData().getCategory().clear();
		rule.getMetaData().getCategory().add(category);
		
		/**
		 * This need to be added after discussion 
		 */
		Condition condition = rule.getCondition();
		Column column_fixed = new Column();
		column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
		column_fixed.setIdentifier("adverseEventEvaluationResult");
		
		condition.getColumn().add(column_fixed);
		
		
			uuid = ruleAuthoringService.createRule(rule);
		
		
		return uuid;
	}

	public String createRuleForStudy(Rule rule, String ruleSetName, String studyShortTitle, String sponsorName) throws Exception {
		// TODO Auto-generated method stub
		String uuid = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.STUDY_BASE.getPackagePrefix(), studyShortTitle, sponsorName, ruleSetName);
		Category category = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName, ruleSetName);
		
		if(rule.getMetaData()==null){
			rule.setMetaData(new MetaData());
		}
		
		rule.getMetaData().setPackageName(packageName);
		rule.getMetaData().getCategory().clear();
		rule.getMetaData().getCategory().add(category);
		
		/**
		 * This need to be added after discussion 
		 */
		Condition condition = rule.getCondition();
		Column column_fixed = new Column();
		column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
		column_fixed.setIdentifier("adverseEventEvaluationResult");
		
		condition.getColumn().add(column_fixed);
		
		
			uuid = ruleAuthoringService.createRule(rule);
		
		
		return uuid;
		
		
	}

	public RuleSet createRuleSetForInstitution(String ruleSetName, String institutionName) throws Exception {
		// TODO Auto-generated method stub
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);
		
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), institutionName, ruleSetName);
    	//System.out.println(packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription("package for"+ruleSetName+" rules");
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
		//List<String> _imports = new ArrayList<String>();
		//_imports.add("gov.nih.nci.cabig.caaers.rules.domain.*");
		//_imports.add("gov.nih.nci.cabig.caaers.domain.*");
		//ruleSet.setImport(_imports);
		
		
			ruleAuthoringService.createRuleSet(ruleSet);
		
		
		return  ruleSet;
		
	}
	/**
	 * 
	 */

	public RuleSet createRuleSetForSponsor(String ruleSetName, String sponsorName) throws Exception {
		// TODO Also create the category for the same rule set as well. 
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
    	System.out.println("PackageName:"+packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription("package for"+ruleSetName+" rules");
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
		//List<String> _imports = new ArrayList<String>();
		//_imports.add("gov.nih.nci.cabig.caaers.rules.domain.*");
		//_imports.add("gov.nih.nci.cabig.caaers.domain.*");
		//ruleSet.setImport(_imports);
		
		
			ruleAuthoringService.createRuleSet(ruleSet);
		
		return ruleSet;
	}

	public RuleSet createRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName) throws Exception{
		// TODO Auto-generated method stub
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.STUDY_BASE.getPackagePrefix(), studyShortTitle, sponsorName, ruleSetName);
    	//System.out.println(packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription("package for"+ruleSetName+" rules");
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
		//List<String> _imports = new ArrayList<String>();
		//_imports.add("gov.nih.nci.cabig.caaers.rules.domain.*");
		//_imports.add("gov.nih.nci.cabig.caaers.domain.*");
		//ruleSet.setImport(_imports);
		
		
			ruleAuthoringService.createRuleSet(ruleSet);
		
		return ruleSet;
	}

	public void deployRuleSet(RuleSet ruleSet) throws Exception {
		// TODO Auto-generated method stub
		
		String bindUri = ruleSet.getName();
		
			ruleDeploymentService.deregisterRuleSet(bindUri);
			ruleDeploymentService.registerRuleSet(bindUri, bindUri);
		
		
	}

	public List<RuleSet> getAllRuleSets() throws Exception {
		// TODO Auto-generated method stub
		List<RuleSet> ruleSets = null;
		
			ruleSets = ruleAuthoringService.getAllRuleSets();
		
		return ruleSets;
	}

	public Rule getRule(String uuID) throws Exception {
		// TODO Auto-generated method stub
		Rule rule = null;
		
			rule= ruleAuthoringService.getRule(uuID);
		
		return rule;
	}

	public RuleSet getRuleSetForInstitution(String ruleSetName, String institutionName) throws Exception{
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), institutionName, ruleSetName);
		
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		
		return ruleSet;
	}
	/**
	 * I really have to investigate into this
	 * We may have to have handle to repositoyr service and do something from there
	 * @throws Exception 
	 */

	public List<RuleSet> getAllRuleSetsForInstitution(String institutionName) throws Exception {
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		String institutionSpecificCategoryPath = RuleUtil.getInstitutionSpecificPath(institutionName);
		/**
		 * First check if there is a entry for this sponsor in the repository 
		 * If there is no entry then return empty list implying that there are no rules
		 * for this sponsor
		 */
		boolean exist = RuleUtil.categoryExist(ruleAuthoringService, institutionSpecificCategoryPath);
		if(!exist) {
			return ruleSets;
		}
		
		/**
		 * Now for this catgeory we can go ahead and pull all children
		 */
		List<String> ruleSetNames = this.repositoryService.getAllImmediateChildren(institutionSpecificCategoryPath);
		if(ruleSetNames.size()>0) {
			return ruleSets;
		}
		
		for(String ruleSetName: ruleSetNames){
			
			RuleSet ruleSet = this.getRuleSetForInstitution(ruleSetName, institutionName);
			ruleSets.add(ruleSet);
		}
		
		return ruleSets;
	}

	public RuleSet getRuleSetForSponsor(String ruleSetName, String sponsorName) throws Exception{
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
		
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		
		return ruleSet;
	}
	
	/**
	 * I really have to investigate into this
	 * We may have to have handle to repositoyr service and do something from there
	 * @throws Exception 
	 */

	public List<RuleSet> getAllRuleSetsForSponsor(String sponsorName) throws Exception {
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		String sponsorSpecificCategoryPath = RuleUtil.getSponsorSpecificPath(sponsorName);
		/**
		 * First check if there is a entry for this sponsor in the repository 
		 * If there is no entry then return empty list implying that there are no rules
		 * for this sponsor
		 */
		boolean exist = RuleUtil.categoryExist(ruleAuthoringService, sponsorSpecificCategoryPath);
		if(!exist) {
			return ruleSets;
		}
		
		/**
		 * Now for this catgeory we can go ahead and pull all children
		 */
		List<String> ruleSetNames = this.repositoryService.getAllImmediateChildren(sponsorSpecificCategoryPath);
		if(ruleSetNames.size()<1) {
			return ruleSets;
		}
		
		for(String ruleSetName: ruleSetNames){
			
			RuleSet ruleSet = this.getRuleSetForSponsor(ruleSetName, sponsorName);
			System.out.println(ruleSet.getName());
			ruleSets.add(ruleSet);
		}
		
		return ruleSets;
	}

	public RuleSet getRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName) throws Exception{
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.STUDY_BASE.getPackagePrefix(), studyShortTitle, sponsorName, ruleSetName);
		
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		
		return ruleSet;
		
	}
	
	/**
	 * I really have to investigate into this
	 * We may have to have handle to repositoyr service and do something from there
	 */

	public List<RuleSet> getAllRuleSetsForStudy(String studyShortTitle,String sponsorName) throws Exception{
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		String studySponsorSpecificCategoryPath = RuleUtil.getStudySponsorSpecificPath(studyShortTitle, sponsorName);
		/**
		 * First check if there is a entry for this sponsor in the repository 
		 * If there is no entry then return empty list implying that there are no rules
		 * for this sponsor
		 */
		boolean exist = RuleUtil.categoryExist(ruleAuthoringService, studySponsorSpecificCategoryPath);
		if(!exist) {
			return ruleSets;
		}
		
		/**
		 * Now for this catgeory we can go ahead and pull all children
		 */
		List<String> ruleSetNames = this.repositoryService.getAllImmediateChildren(studySponsorSpecificCategoryPath);
		if(ruleSetNames.size()>0) {
			return ruleSets;
		}
		
		for(String ruleSetName: ruleSetNames){
			
			RuleSet ruleSet = this.getRuleSetForStudy(ruleSetName, studyShortTitle, sponsorName);
			ruleSets.add(ruleSet);
		}
		
		return ruleSets;
	}

	public List<Rule> getRulesByCategory(String categoryPath) throws Exception {
		// TODO Auto-generated method stub
		List<Rule> rules =null;
		
			rules = ruleAuthoringService.getRulesByCategory(categoryPath);
		
		return rules;
	}

	public void unDeployRuleSet(RuleSet ruleSet) throws Exception {
		// TODO Auto-generated method stub
		String bindUri = ruleSet.getName();
		
			ruleDeploymentService.deregisterRuleSet(bindUri);
		
	}

	public void updateRule(Rule rule) throws Exception{
		// TODO Auto-generated method stub
		
			ruleAuthoringService.updateRule(rule);
		
	}

	public void updateRuleSet(RuleSet ruleSet) throws Exception {
		// TODO Auto-generated method stub
		
			ruleAuthoringService.updateRuleSet(ruleSet);
		
	}


	public void saveRulesForInstitution(RuleSet ruleSet, String institutionName) throws Exception {
		// TODO Auto-generated method stub
		String ruleSetName = ruleSet.getName();
		if(ruleSetName==null){
			throw new Exception("Rule name should be set to some  valid value");
		}
		RuleSet rs = this.getRuleSetForInstitution(ruleSetName, institutionName);
		if(rs==null){
			//create the rule set
			RuleSet ruleSetTemp = this.createRuleSetForInstitution(ruleSetName, institutionName);
		}
		List<Rule> rules = ruleSet.getRule();
		for(Rule rule: rules){
			if(rule.getId()==null){
			this.createRuleForInstitution(rule, ruleSetName, institutionName);
			}else{
				this.updateRule(rule);
			}
		}
		
		
	}


	public void saveRulesForSponsor(RuleSet ruleSet, String sponsorName) throws Exception {
		// TODO Auto-generated method stub
		
		String ruleSetName = ruleSet.getName();
		if(ruleSetName==null){
			throw new Exception("Rule name should be set to some  valid value");
		}
		RuleSet rs = this.getRuleSetForSponsor(ruleSetName, sponsorName);
		if(rs==null){
			//create the rule set
			RuleSet ruleSetTemp = this.createRuleSetForSponsor(ruleSetName, sponsorName);
		}
		List<Rule> rules = ruleSet.getRule();
		for(Rule rule: rules){
			if(rule.getId()==null){
			this.createRuleForSponsor(rule, ruleSetName, sponsorName);
			}else{
				this.updateRule(rule);
			}
		}
		
	}


	public void saveRulesForStudy(RuleSet ruleSet, String studyShortTitle, String sponsorName) throws Exception {
		// TODO Auto-generated method stub
		String ruleSetName = ruleSet.getName();
		if(ruleSetName==null){
			throw new Exception("Rule name should be set to some  valid value");
		}
		RuleSet rs = this.getRuleSetForStudy(ruleSetName, studyShortTitle, sponsorName);
		if(rs==null){
			//create the rule set
			RuleSet ruleSetTemp = this.createRuleSetForStudy(ruleSetName, studyShortTitle, sponsorName);
		}
		List<Rule> rules = ruleSet.getRule();
		for(Rule rule: rules){
			if(rule.getId()==null){
			this.createRuleForStudy(rule, ruleSetName, studyShortTitle, sponsorName);
			}else{
				this.updateRule(rule);
			}
		}
		
	}
	
	
	
	

}
