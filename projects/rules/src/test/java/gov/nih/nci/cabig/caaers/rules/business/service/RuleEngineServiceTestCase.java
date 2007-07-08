package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.rules.brxml.Action;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.BRXMLHelper;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RuleEngineServiceTestCase extends CaaersDbTestCase {

	private RulesEngineService rulesEngineService;
	
    

    protected Set<Object> mocks = new HashSet<Object>();

    private AdverseEventEvaluationServiceImpl aees;
    
	public static ApplicationContext getApplicationContext(){
		String[] locations = new String[] {
	            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml",
		 		"classpath*:config/spring/applicationContext-rules-services.xml"   
	           };
		return new ClassPathXmlApplicationContext(locations);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		deleteDir(new File("/tmp/testrepo"));
		File f = new File("/tmp/testrepo");
		f.mkdir();
		rulesEngineService = new RulesEngineServiceImpl();
		
		aees = (AdverseEventEvaluationServiceImpl)getApplicationContext().getBean("adverseEventEvaluationService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInstitutionRuleFlowOnReportSchedulingRules() throws Exception {
		RuleSet rs = this.createRulesForInstitute(3,RuleType.REPORT_SCHEDULING_RULES.getName());
		
		//RulesEngineService res = new RulesEngineServiceImpl();
		
		rulesEngineService.saveRulesForInstitution(rs, "Wake Forest Comprehensive Cancer Center");

		rs = rulesEngineService.getRuleSetForInstitution(rs.getDescription(),"Wake Forest Comprehensive Cancer Center");
		
		rulesEngineService.deployRuleSet(rs);
		//createAdverseEvent3();
		createAdverseEvent5();		
		
	}
	
	private RuleSet createRulesForInstitute(int id,String ruleTypeDesc) {

		RuleSet rs = new RuleSet();
		rs.setDescription(ruleTypeDesc);

		Rule r = makeRule(id,"24 Hour, 5 Calendar Days");
		// inst based rules
		// need to add inst name in the crieteria.
		r.getCondition().getColumn().add(
				this.createCriteriaForInstitute("Wake Forest Comprehensive Cancer Center"));

		rs.getRule().add(r);
		return rs;
	
	}
	
	private Rule makeRule(int i,String actionStr) {
		Rule rule1 = new Rule();
		rule1.setMetaData(new MetaData());
		rule1.getMetaData().setName("Rule" + i);
		rule1.getMetaData().setDescription("Our test rule" + i);

		Condition condition = new Condition();
		// condition.getEval().add("adverseEvent.getGrade().getCode() <=
		// Grade.MODERATE.getCode()");

		Column column1 = new Column();
		column1.setObjectType("AdverseEvent");
		column1.setIdentifier("adverseEvent");
		column1.setExpression("getGrade().getCode().intValue()");

		FieldConstraint fieldConstraint1 = new FieldConstraint();
		fieldConstraint1.setFieldName("grade");

		LiteralRestriction literalRestriction1 = new LiteralRestriction();
		literalRestriction1.setEvaluator("==");
		List<String> values1 = new ArrayList<String>();

		values1.add("1");

		literalRestriction1.setValue(values1);

		List<LiteralRestriction> lr1 = new ArrayList<LiteralRestriction>();
		lr1.add(literalRestriction1);

		fieldConstraint1.setLiteralRestriction(lr1);

		ArrayList<FieldConstraint> fields1 = new ArrayList<FieldConstraint>();
		fields1.add(fieldConstraint1);

		column1.setFieldConstraint(fields1);

		condition.getColumn().add(column1);

		Column column2 = new Column();
		column2.setObjectType("AdverseEvent");
		column2.setIdentifier("adverseEvent");
		column2.setExpression("getHospitalization().getCode().intValue()");

		FieldConstraint fieldConstraint2 = new FieldConstraint();
		fieldConstraint2.setFieldName("hospitalization");

		LiteralRestriction literalRestriction2 = new LiteralRestriction();
		literalRestriction2.setEvaluator("==");
		List<String> values2 = new ArrayList<String>();

		values2.add("1");
		values2.add("2");

		literalRestriction2.setValue(values2);

		List<LiteralRestriction> lr2 = new ArrayList<LiteralRestriction>();
		lr2.add(literalRestriction2);

		fieldConstraint2.setLiteralRestriction(lr2);

		ArrayList<FieldConstraint> fields2 = new ArrayList<FieldConstraint>();
		fields2.add(fieldConstraint2);

		column2.setFieldConstraint(fields2);

		condition.getColumn().add(column2);

		/**
		 * Make it or break it
		 */

		// Column column_fixed = new Column();
		// column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
		// column_fixed.setIdentifier("adverseEventEvaluationResult");
		// condition.getColumn().add(column_fixed);
		rule1.setCondition(condition);

		// Notification action = new Notification();
		// action.setActionId("ROUTINE_AE");

		Action action = new Action();
		action.setActionId(actionStr);

		rule1.setAction(action);

		return rule1;
	}
	
	private Column createCriteriaForInstitute(String criteriaValue) {
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.domain.Organization");
		column.setIdentifier("siteSDO");
		column.setExpression("getName()");
		
		List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();
		
		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("name");
		fieldConstraints.add(fieldConstraint);
		ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		literalRestriction.getValue().add(criteriaValue);
		literalRestrictions.add(literalRestriction);
		fieldConstraint.setLiteralRestriction(literalRestrictions);

		column.setFieldConstraint(fieldConstraints);
		
		return column;

	}
	
	private void createAdverseEvent5() throws Exception {

		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);

		ae1.setHospitalization(Hospitalization.HOSPITALIZATION);

		Organization s = new Organization();
		s.setName("Wake Forest Comprehensive Cancer Center");
		
		ExpeditedAdverseEventReport exaer = new ExpeditedAdverseEventReport();
		exaer.addAdverseEvent(ae1);
		StudyParticipantAssignment spa = new StudyParticipantAssignment();
		StudySite ss = new StudySite();
		ss.setOrganization(s);
		spa.setStudySite(ss);
		
		exaer.setAssignment(spa);
		
		//System.out.println(exaer.get);
		

		
		//String msg = aees.evaluateSAEReportSchedule(exaer);

		//System.out.println(msg);
		//assertEquals(msg, "SERIOUS_ADVERSE_EVENT");

	}

	private boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}


	
}
