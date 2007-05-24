package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Notification;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbServiceFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.service.invoker.ObjectInvoker;
import org.codehaus.xfire.soap.SoapConstants;
import org.codehaus.xfire.test.AbstractXFireTest;

/**
 * 
 * @author vinaykumar
 * This class is for testing the persistence of rules at different levels
 * under certain categories.
 * There are three highest level categories namely:
 * 	1. Institution
 * 	2. Sponsor
 * 	3. Study
 * These categories will be created right under the root node
 *                     / (Root Node)
 *      /Institution      /Sponsor           /Study
 *     
 *     Since there will be number of sponsors,for every sponsor there
 *     will be a different category
 *     
 *                    / (Root Node)
 *      /Institution      /Sponsor           /Study
 *                               /SP1
 *   The same scheme will apply for the Study as well. A sponsor may
 *   have different rule sets (collectio of rules). These rule sets 
 *   will have the package name and will be persisted in the following
 *   way by making categories
 *   
 *   					/ (Root Node)
 *      /Institution      /Sponsor           /Study
 *                               /SP1
 *   								/RS1 (category path = /Sponsor/SP1/RS1)
 *                                  /RS2 (category path = /Sponsor/SP1/RS2)
 */

public class RuleAuthoringServiceTestWithNewScheme extends AbstractXFireTest //TestCase
{
	
	private String caaers_rulebase_category = "CAAERS_RULEBASE";
	
	private String institution_category ="INSTITUTION";
	private String sponsor_category="SPONSOR";
	private String study_category="STUDY";
	
	private String our_dream_Sponsor = "OUR_DREAM_SPONSOR";
	private String our_worst_Sponsor = "OUR_WORST_SPONSOR";
	
	private String our_dream_Sponsor_category_path= "/SPONSOR/OUR_DREAM_SPONSOR";
	private String our_worst_Sponsor_category_path= "/SPONSOR/OUR_WORST_SPONSOR";
	
	private String rule_set_1_name_for_dream_sponsor = "Asses AE Rule";
	private String rule_set_2_name_for_dream_sponsor= "SAE Report Schedule Rule";

	//private RuleAuthoringServiceImpl ruleAuthoringServiceImpl;
	
    private Service service;
    private ObjectServiceFactory factory;
	private RuleAuthoringService ruleAuthoringServiceImpl;
    

	protected void setUp() throws Exception {
		super.setUp();
		//this.ruleAuthoringServiceImpl = new RuleAuthoringServiceImpl();
		//super.setUp();

        factory = new JaxbServiceFactory(getXFire().getTransportManager());
        factory.setStyle(SoapConstants.STYLE_DOCUMENT);
        
        // Set the schemas
        ArrayList<String> schemas = new ArrayList<String>();
        //TODO
        schemas.add(getTestFile("bin/schema/rules.xsd").getAbsolutePath());
        
        Map<String,Object> props = new HashMap<String,Object>();
        props.put(ObjectServiceFactory.SCHEMAS, schemas);
        
        service = factory.create(RuleAuthoringService.class,
                                  "RuleAuthoringService",
                                  "urn:RuleAuthoringService",
                                  props);

		factory = new JaxbServiceFactory();
		service = factory.create(RuleAuthoringService.class);        
        service.setProperty(ObjectInvoker.SERVICE_IMPL_CLASS, RuleAuthoringServiceImpl.class);
		getServiceRegistry().register(service);
		ruleAuthoringServiceImpl = (RuleAuthoringService) new XFireProxyFactory(getXFire())
        .create(service, "http://localhost:8080/rules/services/RuleAuthoringService");

	}
	
	public void testCreateBaseCategory() throws Exception{
		
		/**
		 * This is the rule base for the all the caAERS rules
		 * In future we should create this rule base one more
		 * level deep so that we can make the rule base for the 
		 * number of applications
		 */
		
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/");
		metaData.setName(this.caaers_rulebase_category);
		metaData.setDescription("caAERS Base Rule Level");
		category.setMetaData(metaData);
		this.ruleAuthoringServiceImpl.createCategory(category);
		
	}
	
	public void testCreateInstitutionCategory() throws Exception{
		
		Category category1 = new Category();
		MetaData metaData1 = new MetaData();
		category1.setPath(this.caaers_rulebase_category);
		metaData1.setName(this.institution_category);
		metaData1.setDescription("Institutional Level Triggers are registered under this category");
		category1.setMetaData(metaData1);
		this.ruleAuthoringServiceImpl.createCategory(category1);
		
	}
	
	public void testCreateSponsorCategory() throws Exception{
		Category category2 = new Category();
		MetaData metaData2 = new MetaData();
		category2.setPath(this.caaers_rulebase_category);
		metaData2.setName(this.sponsor_category);
		metaData2.setDescription("Sponsor Level Triggers are registered under this category");
		category2.setMetaData(metaData2);
		this.ruleAuthoringServiceImpl.createCategory(category2);
	}
	
	public void testCreateStudyCategory() throws Exception{
		Category category3 = new Category();
		MetaData metaData3 = new MetaData();
		category3.setPath(this.caaers_rulebase_category);
		metaData3.setName(this.study_category);
		metaData3.setDescription("Study Level Triggers are registered under this category");
		category3.setMetaData(metaData3);
		this.ruleAuthoringServiceImpl.createCategory(category3);
	}
	
	/**
	 * 
	 * @throws Exception
	 * 
	 * Creates category per sponsor name under Sponsor category
	 */
	
	public void testSponsorSpecificCategory_DREAM_SPONSOR() throws Exception{
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/CAAERS_RULEBASE/SPONSOR");
		metaData.setName(this.our_dream_Sponsor);
		metaData.setDescription("Dream Sponsor Rule Base category");
		category.setMetaData(metaData);
		this.ruleAuthoringServiceImpl.createCategory(category);
	}
	
	public void testSponsorSpecificCategory_WORST_SPONSOR() throws Exception{
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/CAAERS_RULEBASE/SPONSOR");
		metaData.setName(this.our_worst_Sponsor);
		metaData.setDescription("Worst Sponsor Rule Base category");
		category.setMetaData(metaData);
		this.ruleAuthoringServiceImpl.createCategory(category);
	}
	
	public void testRuleSetCategory_ASSESS_AE_RULES_For_DREAM_SPONSOR() throws Exception{
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/CAAERS_RULEBASE/SPONSOR/OUR_DREAM_SPONSOR");
		metaData.setName(this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor));
		metaData.setDescription(this.rule_set_1_name_for_dream_sponsor);
		category.setMetaData(metaData);
		this.ruleAuthoringServiceImpl.createCategory(category);
	}
	
	/**
	 * 
	 * @throws Exception
	 * 
	 * This is just a logical collection of rules and does not mean
	 * anything from category prespective. 
	 */
	
	public void testCreateRuleSet_Asses_AE_Rules() throws Exception{
		RuleSet ruleSet = new RuleSet();
		//This name should be unique
		String packageName = "gov.nih.nci.cabig.caaers.rules.sponsor"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);
    	System.out.println(packageName);
		ruleSet.setName(packageName);
		ruleSet.setStatus("Draft");
		ruleSet.setDescription("package for"+this.rule_set_1_name_for_dream_sponsor+" rules");
		
		//ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		 ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
		//List<String> _imports = new ArrayList<String>();
		//_imports.add("gov.nih.nci.cabig.caaers.rules.domain.*");
		//_imports.add("gov.nih.nci.cabig.caaers.domain.*");
		//ruleSet.setImport(_imports);
		
		this.ruleAuthoringServiceImpl.createRuleSet(ruleSet);
	}
	
	public void testCreateRulesForAssesAERulesRuleSet() throws Exception{
		
		String packageName = "gov.nih.nci.cabig.caaers.rules.sponsor"+"."+this.getStringWithoutSpaces(this.our_dream_Sponsor)+"."+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor);
		Category asses_ae_rule_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/SPONSOR/OUR_DREAM_SPONSOR"+"/"+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor));
		
		Rule rule1 = new Rule();
		MetaData metaData1 = new MetaData();
		metaData1.setName("Decide Ruotine AE");
		metaData1.setPackageName(packageName);
		metaData1.setDescription("Deciding Routine AE");
		metaData1.getCategory().add(asses_ae_rule_cat);
		rule1.setMetaData(metaData1);
		
		Condition condition = new Condition();
		condition.getEval().add("adverseEvent.getGrade().getCode() <= Grade.MODERATE.getCode()");

		Column column = new Column();
		column.setObjectType("AdverseEvent");
		column.setIdentifier("adverseEvent");
		
		
		
		
		condition.getColumn().add(column);	
		/**
		 *  Make it or break it
		 */
		
		Column column_fixed = new Column();
		column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
		column_fixed.setIdentifier("adverseEventEvaluationResult");
		
		condition.getColumn().add(column_fixed);
		
		rule1.setCondition(condition);
		
		Notification action = new Notification();
		action.setActionId("ROUTINE_AE");
		rule1.setAction(action);
		
		this.ruleAuthoringServiceImpl.createRule(rule1);
		
		Rule rule2 = new Rule();
		MetaData metaData2 = new MetaData();
		metaData2.setName("Decide SAE");
		metaData2.setPackageName(packageName);
		metaData2.setDescription("Deciding SAE");
		metaData2.getCategory().add(asses_ae_rule_cat);
		rule2.setMetaData(metaData2);
		

		Condition condition2 = new Condition();
		condition2.getEval().add("adverseEvent.getGrade().getCode() > Grade.MODERATE.getCode()");

		Column column2 = new Column();
		column2.setObjectType("AdverseEvent");
		column2.setIdentifier("adverseEvent");
		
		
		
		
		condition2.getColumn().add(column2);
		
		condition2.getColumn().add(column_fixed);
		
		rule2.setCondition(condition2);
		
		Notification action2 = new Notification();
		action2.setActionId("SAE");
		rule2.setAction(action2);
		
		this.ruleAuthoringServiceImpl.createRule(rule2);
		
		
		
		
		
		
		
	}
	

	
	public void testRetrieveCategories() throws Exception{
		
		Category base_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE");
		if (base_cat !=null){
			System.out.println("Got the object");
		}
		System.out.println("Name:"+base_cat.getMetaData().getName());
		System.out.println("Desc:"+base_cat.getMetaData().getDescription());
		System.out.println("Path:"+base_cat.getPath());
		
		System.out.println("*************************************");
		
		Category inst_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/INSTITUTION");
		if (inst_cat !=null){
			System.out.println("Got the object");
		}
		System.out.println("Name:"+inst_cat.getMetaData().getName());
		System.out.println("Desc:"+inst_cat.getMetaData().getDescription());
		System.out.println("Path:"+inst_cat.getPath());
		
		System.out.println("*************************************");
		
		Category spns_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/SPONSOR");
		if (spns_cat !=null){
			System.out.println("Got the object");
		}
		System.out.println("Name:"+spns_cat.getMetaData().getName());
		System.out.println("Desc:"+spns_cat.getMetaData().getDescription());
		System.out.println("Path:"+spns_cat.getPath());
		
		System.out.println("*************************************");
		
		Category stud_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/STUDY");
		if (spns_cat !=null){
			System.out.println("Got the object");
		}
		System.out.println("Name:"+stud_cat.getMetaData().getName());
		System.out.println("Desc:"+stud_cat.getMetaData().getDescription());
		System.out.println("Path:"+stud_cat.getPath());
		
		
		
	}
	
	public void toTree() throws Exception{
		Category base_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE");
		Category inst_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/INSTITUTION");
		Category spns_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/SPONSOR");
		Category stud_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/STUDY");
		
		Category dream_sponsor_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/SPONSOR/OUR_DREAM_SPONSOR");
		Category worst_sponsor_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/SPONSOR/OUR_WORST_SPONSOR");
		Category asses_ae_rule_cat = this.ruleAuthoringServiceImpl.getCategory("/CAAERS_RULEBASE/SPONSOR/OUR_DREAM_SPONSOR"+"/"+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor));
		
		System.out.println("/");
		System.out.println(tab()+base_cat.getMetaData().getName());
		System.out.println(tab()+tab()+inst_cat.getMetaData().getName());
		System.out.println(tab()+tab()+spns_cat.getMetaData().getName());
		System.out.println(tab()+tab()+tab()+dream_sponsor_cat.getMetaData().getName());
		System.out.println(tab()+tab()+tab()+tab()+asses_ae_rule_cat.getMetaData().getName());
		System.out.println(tab()+tab()+tab()+worst_sponsor_cat.getMetaData().getName());
		
		System.out.println(tab()+tab()+stud_cat.getMetaData().getName());
		
		
	}
	
	public void testRulesByCategory() throws Exception{
		List<Rule> rules = this.ruleAuthoringServiceImpl.getRulesByCategory("/CAAERS_RULEBASE/SPONSOR/OUR_DREAM_SPONSOR"+"/"+this.getStringWithoutSpaces(this.rule_set_1_name_for_dream_sponsor));
		
		System.out.println(rules.size());
	}
	

	
	public void testAll() throws Exception{
		this.testCreateBaseCategory();
		this.testCreateInstitutionCategory();
		this.testCreateSponsorCategory();
		this.testCreateStudyCategory();
		this.testSponsorSpecificCategory_DREAM_SPONSOR();
		this.testSponsorSpecificCategory_WORST_SPONSOR();
		this.testCreateRuleSet_Asses_AE_Rules();
		this.testCreateRulesForAssesAERulesRuleSet();
		this.toTree();
	}

	private String tab(){
		return "\t";
	}
	
	
	
	private String getStringWithoutSpaces(String str){
		
		String _str= str.trim();
		return _str.replace(" ", "_");
		
		
	}
	
	private Rule getRule1(){
		return null;
	}
	
	
}
