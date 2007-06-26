package gov.nih.nci.cabig.caaers.rules;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Action;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldBinding;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Notification;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleAttribute;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.runtime.action.DefaultEmailNotificationHandler;
import junit.framework.TestCase;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleAuthoringServiceTest extends TestCase {

	private static final String INSTITUTIONAL_LEVEL = "Institution";
	private static final String SPONSOR_LEVEL = "Sponsor";
	private static final String PROTOCOL_LEVEL = "Protocol";
	
	private RuleAuthoringServiceImpl ruleAuthoringServiceImpl;

	protected void setUp() throws Exception {
		super.setUp();
		this.ruleAuthoringServiceImpl = new RuleAuthoringServiceImpl();
	}
	
	public void testCreateCategory() throws Exception {
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/");
		metaData.setName(INSTITUTIONAL_LEVEL);
		metaData.setDescription("Institutiona Level Triggers are registered under this category");
		category.setMetaData(metaData);
		this.ruleAuthoringServiceImpl.createCategory(category);
	}

	public void testCreateSubCategory() throws Exception {		
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(INSTITUTIONAL_LEVEL);
		metaData.setName(SPONSOR_LEVEL);
		metaData.setDescription("Institutiona Level Triggers are registered under this category");
		category.setMetaData(metaData);
		this.ruleAuthoringServiceImpl.createCategory(category);
	}
	
	public void testCreateProtocolCategory() throws Exception {		
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath(SPONSOR_LEVEL);
		metaData.setName(PROTOCOL_LEVEL);
		metaData.setDescription("Institutiona Level Triggers are registered under this category");
		this.ruleAuthoringServiceImpl.createCategory(category);
	}
	
    public void testCreateRuleSet() throws Exception {
    	RuleSet ruleSet = new RuleSet();
		//This name should be unique
    	ruleSet.setName("gov.nih.nci.cabig.caaers.rules");
		ruleSet.setStatus("DEV");
		ruleSet.setDescription("Generic package for all rules");
		ruleSet.getImport().add("gov.nih.nci.cabig.caaers.rules.domain.*");
		this.ruleAuthoringServiceImpl.createRuleSet(ruleSet);
    }
    
    public void testGetAllRuleSets() throws Exception {
		List<RuleSet> ruleSets = this.ruleAuthoringServiceImpl.getAllRuleSets();
		System.out.println(ruleSets);
    }
    
    public void testCreateRule() throws Exception {
		Rule rule = new Rule();
		MetaData metaData = new MetaData();
		metaData.setName("Decide AE Type 1");
		metaData.setPackageName("gov.nih.nci.cabig.caaers.rules");
		metaData.setDescription("Description 1");
		rule.setMetaData(metaData);
		Category category = new Category();
		category.setMetaData(new MetaData());
		category.setPath("");
		category.getMetaData().setName(INSTITUTIONAL_LEVEL);
		metaData.getCategory().add(category);
		
		RuleAttribute ruleAttribute = new RuleAttribute();
		ruleAttribute.setName("salience");
		ruleAttribute.setValue("10");
		
		Condition condition = new Condition();
		//condition.getEval().add("adverseEvent.getGrade().getCode() >= Grade.MODERATE.getCode()");

		Column column = new Column();
		column.setObjectType("StudySDO");
		column.setIdentifier("study1");
		
/*		FieldBinding fieldBinding = new FieldBinding();
		fieldBinding.setFieldName("primarySponsorCode");
		fieldBinding.setIdentifier("primarySponsorCode");
		column.getFieldBinding().add(fieldBinding);
*/		
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("primarySponsorCode");
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		List<String> values = new ArrayList<String>();
		values.add("SC_1");
		literalRestriction.setValue(values);
		fieldConstraint.getLiteralRestriction().add(literalRestriction);
		column.getFieldConstraint().add(fieldConstraint);
		
		condition.getColumn().add(column);		
		
		rule.setCondition(condition);
		
		Notification action = new Notification();
		action.setActionId("actionId_1");
		//action.setActionHandler(new DefaultEmailNotificationHandler());
		//action.setContent("System.out.println(\"Found a SAE with Grade Greater Than Or Equal To Moderate\");");
		rule.setAction(action);
		
		this.ruleAuthoringServiceImpl = new RuleAuthoringServiceImpl();
		
		String ruleId = this.ruleAuthoringServiceImpl.createRule(rule);
		
		Rule ruleReloaded = this.ruleAuthoringServiceImpl.getRule(ruleId);
		
		System.out.print(ruleReloaded.getId());
		ruleReloaded.setId(ruleId);
		addRuleAttributes(ruleReloaded);
    }
    
/*    public void testUpdateRule() throws Exception {
    	Rule rule = this.ruleAuthoringServiceImpl.getRule("");
    	
    	this.ruleAuthoringServiceImpl.updateRule(rule);
    }*/
    
    private void addRuleAttributes(Rule rule) throws Exception {
        RuleAttribute at = new RuleAttribute();
        at.setName("salience"); at.setValue("42");
        rule.getRuleAttribute().add(at);
        assertEquals(1, rule.getRuleAttribute().size());
        assertEquals(at, rule.getRuleAttribute().get(0));
        
        RuleAttribute at2 = new RuleAttribute();
        at.setName("agenda-group"); at.setValue("groupOne");
        rule.getRuleAttribute().add( at2 );
        assertEquals(2, rule.getRuleAttribute().size());
        assertEquals(at2, rule.getRuleAttribute().get(1));
    	this.ruleAuthoringServiceImpl.updateRule(rule);

    }
    
}