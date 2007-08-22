package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceImpl;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.jbossrules.RepositoryServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
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
		
		/**
		 * First check if the RuleSet has been created or not
		 * If not then first create the ruleset
		 */
		RuleSet ruleSet = this.getRuleSetForInstitution(ruleSetName, institutionName);
		if(ruleSet==null){
			this.createRuleSetForInstitution(ruleSetName, institutionName);
		}
		
		String uuid = null;
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), institutionName, ruleSetName);
		Category category = RuleUtil.getInstitutionSpecificCategory(ruleAuthoringService, institutionName, ruleSetName);
		
		if(rule.getMetaData()==null){
			rule.setMetaData(new MetaData());
		}
		
		rule.getMetaData().setPackageName(packageName);
		rule.getMetaData().getCategory().clear();
		rule.getMetaData().getCategory().add(category);
		
		
		
		
		
			uuid=ruleAuthoringService.createRule(rule);
	
		
		return uuid;
	}

	public String createRuleForSponsor(Rule rule, String ruleSetName, String sponsorName) throws Exception {
		// TODO Auto-generated method stub
		
		/**
		 * First check if the RuleSet has been created or not
		 * If not then first create the ruleset
		 */
		RuleSet ruleSet = this.getRuleSetForSponsor(ruleSetName, sponsorName);
		if(ruleSet==null){
			this.createRuleSetForSponsor(ruleSetName, sponsorName);
		}
		String uuid= null;
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
		Category category = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName, ruleSetName);
		
		System.out.println("Path of category For Sponsor Rules:"+category.getPath());
		System.out.println("Name of category For Sponsor Rules:"+category.getMetaData().getName());
		
		System.out.println("PackageName:"+packageName);
		if(rule.getMetaData()==null){
			rule.setMetaData(new MetaData());
		}
		
		rule.getMetaData().setPackageName(packageName);
		rule.getMetaData().getCategory().clear();
		rule.getMetaData().getCategory().add(category);
		
		
		
		
			uuid = ruleAuthoringService.createRule(rule);
		
		
		return uuid;
	}

	public String createRuleForSponsorDefinedStudy(Rule rule, String ruleSetName, String studyShortTitle, String sponsorName) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * First check if the RuleSet has been created or not
		 * If not then first create the ruleset
		 */
		RuleSet ruleSet = this.getRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle, sponsorName);
		
		if(ruleSet==null){
			this.createRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle, sponsorName);
		}
		
		String uuid = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(), studyShortTitle, sponsorName, ruleSetName);
		Category category = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName, ruleSetName);
		
		if(rule.getMetaData()==null){
			rule.setMetaData(new MetaData());
		}
		
		rule.getMetaData().setPackageName(packageName);
		rule.getMetaData().getCategory().clear();
		rule.getMetaData().getCategory().add(category);
		
		
		
			uuid = ruleAuthoringService.createRule(rule);
		
		
		return uuid;
		
		
	}

	public String createRuleForInstitutionDefinedStudy(Rule rule, String ruleSetName, String studyShortTitle, String institutionName) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * First check if the RuleSet has been created or not
		 * If not then first create the ruleset
		 */
		RuleSet ruleSet = this.getRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle, institutionName);
		
		if(ruleSet==null){
			this.createRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle, institutionName);
		}
		
		String uuid = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix(), studyShortTitle, institutionName, ruleSetName);
		Category category = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, institutionName, ruleSetName);
		
		if(rule.getMetaData()==null){
			rule.setMetaData(new MetaData());
		}
		
		rule.getMetaData().setPackageName(packageName);
		rule.getMetaData().getCategory().clear();
		rule.getMetaData().getCategory().add(category);
		
		
		
			uuid = ruleAuthoringService.createRule(rule);
		
		
		return uuid;
		
		
	}
	
	public RuleSet createRuleSetForInstitution(String ruleSetName, String institutionName) throws Exception {
		// TODO Auto-generated method stub
		
		Category cat = RuleUtil.getInstitutionSpecificCategory(ruleAuthoringService, institutionName, ruleSetName);
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);
		
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), institutionName, ruleSetName);
    	//System.out.println(packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription(ruleSetName);
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		if(ruleSet.getImport().size()==0){
		 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
		}
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
		
		Category cat = RuleUtil.getSponsorSpecificCategory(ruleAuthoringService, sponsorName, ruleSetName);
		
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
    	System.out.println("PackageName:"+packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription(ruleSetName);
		
		
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		if(ruleSet.getImport().size()==0){
			 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
			}
		//List<String> _imports = new ArrayList<String>();
		//_imports.add("gov.nih.nci.cabig.caaers.rules.domain.*");
		//_imports.add("gov.nih.nci.cabig.caaers.domain.*");
		//ruleSet.setImport(_imports);
		
		
			ruleAuthoringService.createRuleSet(ruleSet);
		
		return ruleSet;
	}

	public RuleSet createRuleSetForSponsorDefinedStudy(String ruleSetName, String studyShortTitle, String sponsorName) throws Exception{
		// TODO Auto-generated method stub
		
		Category cat = RuleUtil.getStudySponsorSpecificCategory(ruleAuthoringService, sponsorName, studyShortTitle, ruleSetName);
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(), studyShortTitle, sponsorName, ruleSetName);
    	//System.out.println(packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription(ruleSetName);
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		if(ruleSet.getImport().size()==0){
			 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
			}
		//List<String> _imports = new ArrayList<String>();
		//_imports.add("gov.nih.nci.cabig.caaers.rules.domain.*");
		//_imports.add("gov.nih.nci.cabig.caaers.domain.*");
		//ruleSet.setImport(_imports);
		
		
			ruleAuthoringService.createRuleSet(ruleSet);
		
		return ruleSet;
	}

	public RuleSet createRuleSetForInstitutionDefinedStudy(String ruleSetName, String studyShortTitle, String institutionName) throws Exception{
		// TODO Auto-generated method stub
		
		Category cat = RuleUtil.getStudySponsorSpecificCategory(ruleAuthoringService, institutionName, studyShortTitle, ruleSetName);
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		//String packageName = "gov.nih.nci.cabig.caaers.rules"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);

		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix(), studyShortTitle, institutionName, ruleSetName);
    	//System.out.println(packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription(ruleSetName);
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		if(ruleSet.getImport().size()==0){
			 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
			}
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
		   try{
			ruleDeploymentService.deregisterRuleSet(bindUri);
		   }catch(Exception ex){
			   System.out.println("This is first time registration");
		   }
		   
		   
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
		if(ruleSetNames.size()<1) {
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

	public RuleSet getRuleSetForSponsorDefinedStudy(String ruleSetName, String studyShortTitle, String sponsorName) throws Exception{
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(), studyShortTitle, sponsorName, ruleSetName);
		
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		
		return ruleSet;
		
	}

	public RuleSet getRuleSetForInstitutionDefinedStudy(String ruleSetName, String studyShortTitle, String institutionName) throws Exception{
		// TODO Auto-generated method stub
		RuleSet ruleSet = null;
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.INSTITUTION_DEFINED_STUDY_BASE.getPackagePrefix(), studyShortTitle, institutionName, ruleSetName);
		
			ruleSet = ruleAuthoringService.getRuleSet(packageName);
		
		return ruleSet;
		
	}
	
	/**
	 * I really have to investigate into this
	 * We may have to have handle to repositoyr service and do something from there
	 */

	public List<RuleSet> getAllRuleSetsForSponsorDefinedStudy(String studyShortTitle,String sponsorName) throws Exception{
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
		if(ruleSetNames.size()<1) {
			return ruleSets;
		}
		
		for(String ruleSetName: ruleSetNames){
			
			RuleSet ruleSet = this.getRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle, sponsorName);
			ruleSets.add(ruleSet);
		}
		
		return ruleSets;
	}

	public List<RuleSet> getAllRuleSetsForInstitutionDefinedStudy(String studyShortTitle,String institutionName) throws Exception{
		List<RuleSet> ruleSets = new ArrayList<RuleSet>();
		String studySponsorSpecificCategoryPath = RuleUtil.getStudySponsorSpecificPath(studyShortTitle, institutionName);
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
		if(ruleSetNames.size()<1) {
			return ruleSets;
		}
		
		for(String ruleSetName: ruleSetNames){
			
			RuleSet ruleSet = this.getRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle, institutionName);
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
		String ruleSetName = ruleSet.getDescription();
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
		
		String ruleSetName = ruleSet.getDescription();
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


	public void saveRulesForSponsorDefinedStudy(RuleSet ruleSet, String studyShortTitle, String sponsorName) throws Exception {
		// TODO Auto-generated method stub
		String ruleSetName = ruleSet.getDescription();
		if(ruleSetName==null){
			throw new Exception("Rule name should be set to some  valid value");
		}
		RuleSet rs = this.getRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle, sponsorName);
		if(rs==null){
			//create the rule set
			RuleSet ruleSetTemp = this.createRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle, sponsorName);
		}
		List<Rule> rules = ruleSet.getRule();
		for(Rule rule: rules){
			if(rule.getId()==null){
			this.createRuleForSponsorDefinedStudy(rule, ruleSetName, studyShortTitle, sponsorName);
			}else{
				this.updateRule(rule);
			}
		}
		
	}

	public void saveRulesForInstitutionDefinedStudy(RuleSet ruleSet, String studyShortTitle, String institutionName) throws Exception {
		// TODO Auto-generated method stub
		String ruleSetName = ruleSet.getDescription();
		//System.out.println("package name before savinf : " + ruleSet.getMetaData().getPackageName());
		if(ruleSetName==null){
			throw new Exception("Rule name should be set to some  valid value");
		}
		RuleSet rs = this.getRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle, institutionName);
		if(rs==null){
			//create the rule set
			RuleSet ruleSetTemp = this.createRuleSetForInstitutionDefinedStudy(ruleSetName, studyShortTitle, institutionName);
		}
		List<Rule> rules = ruleSet.getRule();
		for(Rule rule: rules){
			if(rule.getId()==null){
			this.createRuleForInstitutionDefinedStudy(rule, ruleSetName, studyShortTitle, institutionName);
			}else{
				this.updateRule(rule);
			}
		}
		
	}
	

	public boolean isDeployed(RuleSet ruleSet) {
		// TODO Auto-generated method stub
		boolean found = false;
		String bindURI = ruleSet.getName();
		RuleSetInfo[] info = repositoryService.listRegistrations();
		
		for(int i=0;i<info.length;i++){
			RuleSetInfo rsi = (RuleSetInfo)info[i];
			String bindUri= rsi.getBindUri();
			if(bindURI.equalsIgnoreCase(bindUri)) {
				found = true;
				break;
			}
		}
		return found;
	}


	public RuleSet getRuleSet(String packageName) throws Exception {
		// TODO Auto-generated method stub
		return ruleAuthoringService.getRuleSet(packageName);
	}


	public void exportRules(String locationToExport) throws Exception {
		StringBuilder sbr = new StringBuilder();
		List<RuleSet> list = this.getAllRuleSets();
		Iterator<RuleSet> it = list.iterator();
		while(it.hasNext()){
			RuleSet rs = it.next();
			rs=repositoryService.getRuleSet(rs.getName());
			String str = XMLUtil.marshal(rs);
			File outFile = new File(locationToExport+File.separator+RuleUtil.getStringWithoutSpaces(rs.getName())+".xml");
			FileWriter out = new FileWriter(outFile);
			out.write(str);
			out.flush();
			out.close();
		}
		/*
		Document doc = new Document();
		Element rootElement = new Element("RuleSets");
		doc.setRootElement(rootElement);
		rootElement.addContent(sbr.toString());
		XMLOutputter outputter = new XMLOutputter();
		FileOutputStream out = new FileOutputStream("");
		outputter.output(doc,out);
		out.flush();
	    out.close();
	    */
	}


	public void importRules(String fileName) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public boolean validateRules(String exportedRuleFileName, String domainJarFileName) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
