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
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;

import java.rmi.RemoteException;
import java.util.List;
/**
 * 
 * @author vinaykumar
 *
 */

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
		
		try {
			uuid = ruleAuthoringService.createRule(rule);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uuid;
		
		
	}

	public RuleSet createRuleSetForInstitution(String ruleSetName, String institutionName) {
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
		
		return  ruleSet;
		
	}

	public RuleSet createRuleSetForSponsor(String ruleSetName, String sponsorName) {
		// TODO Auto-generated method stub
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getPackageName(SPONSOR_PACKAGE_NAME_PREFIX, sponsorName, ruleSetName);
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
		
		try {
			ruleAuthoringService.createRuleSet(ruleSet);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruleSet;
	}

	public RuleSet createRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName) {
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
		return ruleSet;
	}

	public void deployRuleSet(RuleSet ruleSet) {
		// TODO Auto-generated method stub
		
		String bindUri = ruleSet.getName();
		try {
			ruleDeploymentService.deregisterRuleSet(bindUri);
			ruleDeploymentService.registerRuleSet(bindUri, bindUri);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<RuleSet> getAllRuleSets() {
		// TODO Auto-generated method stub
		List<RuleSet> ruleSets = null;
		try {
			ruleSets = ruleAuthoringService.getAllRuleSets();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruleSets;
	}

	public Rule getRule(String uuID) {
		// TODO Auto-generated method stub
		Rule rule = null;
		try {
			rule= ruleAuthoringService.getRule(uuID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rule;
	}

	public RuleSet getRuleSetForInstitution(String ruleSetName, String institutionName) {
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getPackageName(INSTITUTION_PACKAGE_NAME_PREFIX, institutionName, ruleSetName);
		try {
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruleSet;
	}
	/**
	 * I really have to investigate into this
	 * We may have to have handle to repositoyr service and do something from there
	 */

	public List<RuleSet> getAllRuleSetForInstitution(String institutionName) {
		// TODO Auto-generated method stub
		return null;
	}

	public RuleSet getRuleSetForSponsor(String ruleSetName, String sponsorName) {
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getPackageName(SPONSOR_PACKAGE_NAME_PREFIX, sponsorName, ruleSetName);
		try {
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruleSet;
	}
	
	/**
	 * I really have to investigate into this
	 * We may have to have handle to repositoyr service and do something from there
	 */

	public List<RuleSet> getAllRuleSetForSponsor(String sponsorName) {
		// TODO Auto-generated method stub
		return null;
	}

	public RuleSet getRuleSetForStudy(String ruleSetName, String studyShortTitle, String sponsorName) {
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(STUDY_PACKAGE_NAME_PREFIX, studyShortTitle, sponsorName, ruleSetName);
		try {
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruleSet;
		
	}
	
	/**
	 * I really have to investigate into this
	 * We may have to have handle to repositoyr service and do something from there
	 */

	public List<RuleSet> getAllRuleSetForStudy(String studyShortTitle,String sponsorName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Rule> getRulesByCategory(String categoryPath) {
		// TODO Auto-generated method stub
		List<Rule> rules =null;
		try {
			rules = ruleAuthoringService.getRulesByCategory(categoryPath);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rules;
	}

	public void unDeployRuleSet(RuleSet ruleSet) {
		// TODO Auto-generated method stub
		String bindUri = ruleSet.getName();
		try {
			ruleDeploymentService.deregisterRuleSet(bindUri);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateRule(Rule rule) {
		// TODO Auto-generated method stub
		try {
			ruleAuthoringService.updateRule(rule);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateRuleSet(RuleSet ruleSet) {
		// TODO Auto-generated method stub
		try {
			ruleAuthoringService.updateRuleSet(ruleSet);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
