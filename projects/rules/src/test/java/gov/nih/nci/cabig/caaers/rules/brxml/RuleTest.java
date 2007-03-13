package gov.nih.nci.cabig.caaers.rules.brxml;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import junit.framework.TestCase;

public class RuleTest extends TestCase{

	private static final String INSTITUTIONAL_LEVEL = "Institution";
	private static final String SPONSOR_LEVEL = "Sponsor";
	private static final String PROTOCOL_LEVEL = "Protocol";

	
	private RuleAuthoringServiceImpl ruleAuthoringServiceImpl;

	protected void setUp() throws Exception {
		super.setUp();
		this.ruleAuthoringServiceImpl = new RuleAuthoringServiceImpl();
	}
	
	public void testAddConditions() throws Exception {
		Rule rule = new Rule();
		MetaData metaData = new MetaData();
		metaData.setName("Decide AE Type 1");
		metaData.setPackageName("gov.nih.nci.cabig.caaers.rules");
		metaData.setDescription("Description 1");
		rule.setMetaData(metaData);
		Category category = new Category();
		category.setMetaData(new MetaData());
		category.setPath(INSTITUTIONAL_LEVEL);
		category.getMetaData().setName(INSTITUTIONAL_LEVEL);
		metaData.getCategory().add(category);
		
		RuleAttribute ruleAttribute = new RuleAttribute();
		ruleAttribute.setName("salience");
		ruleAttribute.setValue("10");
		
		Condition condition = new Condition();
		//condition.getEval().add("adverseEvent.getGrade().getCode() >= Grade.MODERATE.getCode()");

		Column column = new Column();
		column.setObjectType("Study");
		column.setIdentifier("study1");
		
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("primarySponsorCode");
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.setValue("SC_1");
		fieldConstraint.getLiteralRestriction().add(literalRestriction);
		column.getFieldConstraint().add(fieldConstraint);
		
		condition.getColumn().add(column);		
		
		rule.setCondition(condition);
		
		Action action = new Action();
		rule.setAction(action);
		
		this.ruleAuthoringServiceImpl = new RuleAuthoringServiceImpl();
		
		String ruleId = this.ruleAuthoringServiceImpl.createRule(rule);
		
		Rule ruleReloaded = this.ruleAuthoringServiceImpl.getRule(ruleId);
		
		System.out.print(ruleReloaded.getId());
	}
	
	public void testGetRule() throws Exception {
		Rule ruleReloaded = this.ruleAuthoringServiceImpl.getRule("46f7f483-c8b1-4ded-9ab8-9aec3dcb7ff4");
		System.out.print(ruleReloaded);
	}
	
}