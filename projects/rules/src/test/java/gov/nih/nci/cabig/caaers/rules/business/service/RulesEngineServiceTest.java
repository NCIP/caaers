package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Notification;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import junit.framework.TestCase;

public class RulesEngineServiceTest extends TestCase{
	
	private RulesEngineService rulesEngineService;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.rulesEngineService = new RulesEngineServiceImpl();
	}
	
	public void testCreateRuleSetForSponsor() throws Exception{
		String ruleSetName = "Asses AE Rules";
		String sponsorName = "Our Dream Sponsor";
		
		rulesEngineService.createRuleSetForSponsor(ruleSetName, sponsorName);
				
	}
	
	public void testCreateAnotherRuleSetForSponsor() throws Exception{
		String ruleSetName = "Report Scheduling Period Rules";
		String sponsorName = "Our Dream Sponsor";
		
		rulesEngineService.createRuleSetForSponsor(ruleSetName, sponsorName);
	}
	
	public void testGetRuleSetForSponsor() throws Exception{
		String ruleSetName = "Asses AE Rules";
		String sponsorName = "Our Dream Sponsor";
		RuleSet ruleSet = rulesEngineService.getRuleSetForSponsor(ruleSetName, sponsorName);
		System.out.println(ruleSet.getName());
		String packageName = RuleUtil.getPackageName("gov.nih.nci.cabig.caaers.rules.sponsor", sponsorName, ruleSetName);
		System.out.println(packageName);
		assertEquals(packageName, ruleSet.getName());
		
		
	}
	
	public void testCreateRuleForSponsor() throws Exception{
		String ruleSetName = "Asses AE Rules";
		String sponsorName = "Our Dream Sponsor";
		
		Rule rule1 = new Rule();
		rule1.setMetaData(new MetaData());
		rule1.getMetaData().setName("Rule1");
		rule1.getMetaData().setDescription("Our test rule");
		
		
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
		
		rulesEngineService.createRuleForSponsor(rule1, ruleSetName, sponsorName);
		
		
	}
	
	
	
	public void testGetAllRuleSetsForSponsor() throws Exception{
		String sponsorName = "Our Dream Sponsor";
		rulesEngineService.getAllRuleSetsForSponsor(sponsorName);
		
	}
	
	

}
