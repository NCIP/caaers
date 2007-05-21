package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceImpl;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;

import java.rmi.RemoteException;
import java.util.List;

public class RulesEngineServiceImpl implements RulesEngineService{
	
	private RuleAuthoringService ruleAuthoringService;
	private RuleDeploymentService ruleDeploymentService;
	
	private static final String INSTITUTION_PACKAGE_NAME_PREFIX ="gov.nih.nci.cabig.caaers.rules.institution";
	
	private static final String STUDY_PACKAGE_NAME_PREFIX ="gov.nih.nci.cabig.caaers.rules.study";
	
	private static final String SPONSOR_PACKAGE_NAME_PREFIX ="gov.nih.nci.cabig.caaers.rules.sponsor";
	
	
	public RulesEngineServiceImpl(){
		ruleAuthoringService = new RuleAuthoringServiceImpl();
		ruleDeploymentService = new RuleDeploymentServiceImpl();
	}
	

	public String createRuleForInstitution(Rule rule, String ruleSetName, String institutionName) {
		// TODO Auto-generated method stub
		
		String uuid = null;
		String packageName = RuleUtil.getPackageName(INSTITUTION_PACKAGE_NAME_PREFIX, institutionName, ruleSetName);
		Category category = RuleUtil.getInstitutionSpecificCategory(ruleAuthoringService, institutionName, ruleSetName);
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
		
		try {
			uuid=ruleAuthoringService.createRule(rule);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uuid;
	}

	public String createRuleForSponsor(Rule rule, String ruleSetName, String sponsorName) {
		// TODO Auto-generated method stub
		String uuid= null;
		String packageName = RuleUtil.getPackageName(SPONSOR_PACKAGE_NAME_PREFIX, sponsorName, ruleSetName);
		Category category = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName, ruleSetName);
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
		
		try {
			uuid = ruleAuthoringService.createRule(rule);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uuid;
	}

	public String createRuleForStudy(Rule rule, String ruleSetName, String studyShortTitle, String sponsorName) {
		// TODO Auto-generated method stub
		String uuid = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(STUDY_PACKAGE_NAME_PREFIX, studyShortTitle, sponsorName, ruleSetName);
		Category category = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName, ruleSetName);
		
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
		
		try {
			uuid = ruleAuthoringService.createRule(rule);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uuid;
		
		
	}

	public void createRuleSetForInstitution(String ruleSetName, String institutionName) {
		// TODO Auto-generated method stub
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);
		
		String packageName = RuleUtil.getPackageName(INSTITUTION_PACKAGE_NAME_PREFIX, institutionName, ruleSetName);
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
		
		try {
			ruleAuthoringService.createRuleSet(ruleSet);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createRuleSetForSponsor(String ruleSetName, String sponsorName) {
		// TODO Auto-generated method stub
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getPackageName(SPONSOR_PACKAGE_NAME_PREFIX, sponsorName, ruleSetName);
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
		
		try {
			ruleAuthoringService.createRuleSet(ruleSet);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName) {
		// TODO Auto-generated method stub
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getStudySponsorSpecificPackageName(STUDY_PACKAGE_NAME_PREFIX, studyShortTitle, sponsorName, ruleSetName);
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
		
		try {
			ruleAuthoringService.createRuleSet(ruleSet);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void deployRuleSet(RuleSet ruleSet) {
		// TODO Auto-generated method stub
		
	}

	public List<RuleSet> getAllRuleSets() {
		// TODO Auto-generated method stub
		return null;
	}

	public Rule getRule(String uuID) {
		// TODO Auto-generated method stub
		return null;
	}

	public RuleSet getRuleSetForInstitution(String ruleSetName, String institutionName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RuleSet> getRuleSetForInstitution(String institutionName) {
		// TODO Auto-generated method stub
		return null;
	}

	public RuleSet getRuleSetForSponsor(String ruleSetName, String sponsorName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RuleSet> getRuleSetForSponsor(String sponsorName) {
		// TODO Auto-generated method stub
		return null;
	}

	public RuleSet getRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RuleSet> getRuleSetForStudy(String studyShortTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Rule> getRulesByCategory(String categoryPath) {
		// TODO Auto-generated method stub
		return null;
	}

	public void unDeployRuleSet(RuleSet set) {
		// TODO Auto-generated method stub
		
	}

	public void updateRule(Rule rule) {
		// TODO Auto-generated method stub
		
	}

	public void updateRuleSet(RuleSet ruleSet) {
		// TODO Auto-generated method stub
		
	}

}
