
package gov.nih.nci.cabig.caaers.rules.xml;


import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.brxml.Action;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.rules.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.rules.domain.Grade;
import gov.nih.nci.cabig.caaers.rules.domain.Site;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySite;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionServiceImpl;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;


public class Sponsor_Level_Rule_TestCase extends TestCase {

	private RuleExecutionService ruleExecutionService;
	
	private RuleAuthoringServiceImpl ruleAuthoringServiceImpl;

	private static BufferedReader reader = new BufferedReader(new
			java.io.InputStreamReader(System.in));

	protected void setUp() throws Exception {
		super.setUp();
		this.ruleAuthoringServiceImpl = new RuleAuthoringServiceImpl();
		this.ruleExecutionService = new RuleExecutionServiceImpl();
	}
	    
    protected void assertContains(final List expected, final Object object) {
		if (expected.contains(object)) {
			return;
		}
		fail(object + " not in " + expected);
	}    
    
    
    public void testCreate_AE_Grade_Rule_Eval_JiBX() throws Exception {

    	RuleSet ruleSet = new RuleSet();
		Rule rule = new Rule();
		MetaData metaData = new MetaData();
		rule.setMetaData(metaData);

		ruleSet.setName("gov.nih.nci.cabig.caaers.rules");
		ruleSet.setStatus("DEV");
		ruleSet.getArrayOfRule().getRule().add(rule);
    	ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");
    	
		rule.getMetaData().setName("Decide AE Type");
		
		Action action = new Action();
//		action.setContent("System.out.println(\"Found a SEVERE ADVERSE EVENT with Grade Greater Than Or Equal To Moderate. \nPlease send a mail to" +
//				" regulatory groups immediately.. \");");
//		action.setType("AE");
		rule.setAction(action);
		
		
		Condition condition = new Condition();
		condition.getEval().add("adverseEvent.getGrade().getCode() >= Grade.MODERATE.getCode()");
		
		
		Column column = new Column();
		column.setObjectType("AdverseEvent");
		column.setIdentifier("adverseEvent");
		condition.getColumn().add(column);
		rule.setCondition(condition);    	
    	this.ruleAuthoringServiceImpl.createRuleSet(ruleSet);
    	
    	if(ruleSet.getId() != null) {
    		System.out.println("RuleSet created successfully : " + ruleSet.getId());
    	}

    
    }

	public void testExecute_AE_Grade_Rule_JiBX() throws Exception {
		System.out.println("Please enter the RuleSet Id to Execute");
    	String ruleSetId = reader.readLine();
    	final Map properties1 = new HashMap();

		final List<AdverseEvent> inObjects = new ArrayList<AdverseEvent>();

		final AdverseEvent adverseEvent = getAdverseEvent();
		adverseEvent.setGrade(Grade.MODERATE);
		inObjects.add(adverseEvent);
		
		// execute the rules
/*
TO DOO
		final List outList = this.ruleExecutionService.fireRules( ruleSetId, inObjects );
		assertEquals("incorrect size", 1, outList.size());
		assertContains(outList, adverseEvent);

*/	}

    public void testCreate_AE_Grade_Rule_JiBX() throws Exception {

    	RuleSet ruleSet = new RuleSet();
		Rule rule = new Rule();

		ruleSet.setName("gov.nih.nci.cabig.caaers.rules");
		ruleSet.setStatus("DEV");
		ruleSet.getArrayOfRule().getRule().add(rule);
    	ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");

    	rule.getMetaData().setName("Decide AE Type");
		
		Action action = new Action();
//		action.setContent("System.out.println(\"Found a SEVERE ADVERSE EVENT with Grade Greater Than Or Equal To Moderate. \nPlease send a mail to" +
//				" regulatory groups immediately.. \");");
//		action.setType("AE");
		rule.setAction(action);
		
		
		Condition condition = new Condition();
		//condition.addEval("adverseEvent.getGrade().getCode() >= Grade.MODERATE.getCode()");
		
		
		Column column = new Column();
		column.setObjectType("AdverseEvent");
		column.setIdentifier("adverseEvent");
		condition.getColumn().add(column);
		rule.setCondition(condition);    	
    	this.ruleAuthoringServiceImpl.createRuleSet(ruleSet);
    	
    	if(ruleSet.getId() != null) {
    		System.out.println("RuleSet created successfully : " + ruleSet.getId());
    	}

    
    }
	
	
    private StudySDO getStudy() {
        final StudySDO study = new StudySDO();
        StudySite studySite = new StudySite();
        Site site = new Site();
        studySite.setSite(site);
//        study.getStudySite().add(studySite);
    	return study;
    }

    private AdverseEvent getAdverseEvent() {
    	AdverseEvent adverseEvent = new AdverseEvent();
    	CtcTerm ctcTerm = new CtcTerm();
    	adverseEvent.setCtcTerm(ctcTerm);
    	return adverseEvent;
    }
	
    
}
