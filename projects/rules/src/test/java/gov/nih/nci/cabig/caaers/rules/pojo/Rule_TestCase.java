package gov.nih.nci.cabig.caaers.rules.pojo;

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


public class Rule_TestCase extends TestCase {

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


    public void testCreate_AE_Grade_Rule_JiBX() throws Exception {

    	RuleSet ruleSet = new RuleSet();
		Rule rule = new Rule();

		ruleSet.setName("gov.nih.nci.cabig.caaers.rules");
		ruleSet.setStatus("DEV");
		ruleSet.getRule().add(rule);
    	ruleSet.getImport().add("gov.nih.nci.cabig.caaers.domain.*");

		MetaData ruleMetaData = new MetaData();
		ruleMetaData.setName("Decide AE Type");
		rule.setMetaData(ruleMetaData);

		Action action = new Action();
//		action.setContent("System.out.println(\"Found a SAE with Grade Greater Than Or Equal To Moderate\");");
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
    //TODO - Refactor the below test case, remove the reading values from input.
    public void xtestExecute_AE_Grade_Rule_JiBX() throws Exception {
		System.out.println("Please enter the RuleSet Id to Execute");
    	String ruleSetId = reader.readLine();
    	final Map properties1 = new HashMap();
/*		final StatelessRuleSession statelessSession = new RuleExecutionServiceImpl()
				.getStatelessRuleSession(ruleSetId, properties1,
						this.ruleAuthoringServiceImpl.getRuleServiceProvider());*/
		final List<AdverseEvent> inObjects = new ArrayList<AdverseEvent>();

		final AdverseEvent adverseEvent = getAdverseEvent();
		adverseEvent.setGrade(Grade.MODERATE);
		inObjects.add(adverseEvent);

		// execute the rules

/*
TO DOO

		final List outList = new RuleExecutionServiceImpl().fireRules(ruleSetId, inObjects);


*/    }



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
