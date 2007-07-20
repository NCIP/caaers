package gov.nih.nci.cabig.caaers.rules.business.service;


import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
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
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public class RulesEngineServiceTest extends TestCase {

	private RulesEngineService rulesEngineService;
	
    private static Log log = LogFactory.getLog(RulesEngineServiceTest.class);
    private static RuntimeException acLoadFailure = null;

    private static ApplicationContext applicationContext = null;

    protected Set<Object> mocks = new HashSet<Object>();

    private EvaluationService aees;
    
    

	protected void setUp() throws Exception {
		super.setUp();

		Connection db; // A connection to the database
		Statement sql; // Our statement to run queries with

		Class.forName("org.postgresql.Driver");
		db = DriverManager.getConnection(
				"jdbc:postgresql://localhost/rules_unit_test", "postgres",
				"postgres");

		sql = db.createStatement();
		String sqlText_1 = "drop table rep_binval";
		String sqlText_2 = "drop table rep_fsentry";
		String sqlText_3 = "drop table rep_node";
		String sqlText_4 = "drop table rep_prop";
		String sqlText_5 = "drop table rep_refs";
		try {
			sql.executeUpdate(sqlText_1);
			System.out.println(sqlText_1 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_2);
			System.out.println(sqlText_2 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_3);
			System.out.println(sqlText_3 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_4);
			System.out.println(sqlText_4 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_5);
			System.out.println(sqlText_5 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}

		db.close();

		deleteDir(new File("/tmp/testrepo"));
		File f = new File("/tmp/testrepo");
		f.mkdir();

		this.rulesEngineService = new RulesEngineServiceImpl();
		
		aees = (EvaluationServiceImpl)getDeployedApplicationContext().getBean("evaluationService");
	}

	public void testcreateRuleSet() throws Exception {
		RuleSet rs = this.createRulesForSponsor(1);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForSponsor(rs, "National Cancer Institute");

		rs = res.getRuleSetForSponsor(rs.getDescription(),	"National Cancer Institute");
		

		// deploy rules...
		res.deployRuleSet(rs);
		
		System.out.print(rs.getName());
	}
	
	public void atestInstitutionDefinedStudyLevelRuleFlow() throws Exception {
		RuleSet rs = this.createRuleForInstitutionDefinedStudy(101);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForInstitutionDefinedStudy(rs, "test study","National Cancer Institute");

		rs = res.getRuleSetForSponsor(rs.getDescription(),
				"National Cancer Institute");
		

		// deploy rules...
		res.deployRuleSet(rs);
		createAdverseEvent1();
		createAdverseEvent2();
	}
	
	public void atestSponserRuleFlow() throws Exception {
		RuleSet rs = this.createRulesForSponsor(1);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForSponsor(rs, "National Cancer Institute");

		rs = res.getRuleSetForSponsor(rs.getDescription(),
				"National Cancer Institute");
		

		// deploy rules...
		res.deployRuleSet(rs);
		createAdverseEvent1();
		createAdverseEvent2();
	}
	
	public void atestInstitutionRuleFlow() throws Exception {
		RuleSet rs = this.createRulesForInstitute(2);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForInstitution(rs, "Wake Forest Comprehensive Cancer Center");

		rs = res.getRuleSetForInstitution(rs.getDescription(),"Wake Forest Comprehensive Cancer Center");
		// deploy rules...
		res.deployRuleSet(rs);
		createAdverseEvent3();
		createAdverseEvent4();
	}
	
	public void atestInstitutionRuleFlowOnReportSchedulingRules() throws Exception {
		RuleSet rs = this.createRulesForInstitute(3,RuleType.REPORT_SCHEDULING_RULES.getName());
		RulesEngineService res = new RulesEngineServiceImpl();
		//RulesEngineService res = new RulesEngineServiceImpl();
		
		res.saveRulesForInstitution(rs, "Wake Forest Comprehensive Cancer Center");

		rs = res.getRuleSetForInstitution(rs.getDescription(),"Wake Forest Comprehensive Cancer Center");
		
		res.deployRuleSet(rs);
		//createAdverseEvent3();
		createAdverseEvent5();		
		
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
	
	private void createAdverseEvent3() throws Exception {

		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);
		ae1.setHospitalization(Hospitalization.NONE);

		Organization s = new Organization();
		s.setName("Wake Forest Comprehensive Cancer Center");

		AdverseEventEvaluationServiceImpl aees = new AdverseEventEvaluationServiceImpl();
		String msg = "";//aees.assesAdverseEvent(ae1, study);

		System.out.println(msg);
		assertEquals(msg, "CAN_NOT_DETERMINED");

	}

	private void createAdverseEvent4() throws Exception {

		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);

		ae1.setHospitalization(Hospitalization.HOSPITALIZATION);

		Organization s = new Organization();
		s.setName("Wake Forest Comprehensive Cancer Center");

		AdverseEventEvaluationServiceImpl aees = new AdverseEventEvaluationServiceImpl();
		String msg = "";//aees.assesAdverseEvent(ae1, s);

		System.out.println(msg);
		assertEquals(msg, "SERIOUS_ADVERSE_EVENT");

	}
	
	private void createAdverseEvent1() throws Exception {

		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);
		ae1.setHospitalization(Hospitalization.NONE);

		Study s = new Study();
		s.setPrimarySponsorCode("National Cancer Institute");
		s.setShortTitle("my study");

		AdverseEventEvaluationService aees = new AdverseEventEvaluationServiceImpl();
		String msg = aees.assesAdverseEvent(ae1, s);

		System.out.println(msg);
		assertEquals(msg, "CAN_NOT_DETERMINED");

	}

	private void createAdverseEvent2() throws Exception {
		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);

		ae1.setHospitalization(Hospitalization.HOSPITALIZATION);

		Study s = new Study();
		s.setPrimarySponsorCode("National Cancer Institute");
		s.setShortTitle("my study");

		AdverseEventEvaluationService aees = new AdverseEventEvaluationServiceImpl();
		String msg = aees.assesAdverseEvent(ae1, s);

		System.out.println(msg);
		assertEquals(msg, "SERIOUS_ADVERSE_EVENT");

	}

	private RuleSet createRulesForSponsor(int id) {

		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.AE_ASSESMENT_RULES.getName());

		Rule r = makeRule(id);
		// sponser based rules
		// need to add sponser name in the crieteria.
		r.getCondition().getColumn().add(
				this.createCriteriaForSponsor("National Cancer Institute"));

		rs.getRule().add(r);
		return rs;
	}

	private RuleSet createRulesForInstitute(int id) {

		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.AE_ASSESMENT_RULES.getName());

		Rule r = makeRule(id);
		// inst based rules
		// need to add inst name in the crieteria.
		r.getCondition().getColumn().add(
				this.createCriteriaForInstitute("Wake Forest Comprehensive Cancer Center"));

		rs.getRule().add(r);
		return rs;
	
	}
	
	/*
	 * THis method is used to create criteria for institute 
	 * name
	 */
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
	
	/*
	 * THis method is used to create criteria for sponsor based on the sponsor
	 * name
	 */
	private Column createCriteriaForSponsor(String criteriaValue) {
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.domain.Study");
		column.setIdentifier("studySDO");
		column.setExpression("getPrimarySponsorCode()");

		List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("primarySponsorCode");
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

	private void ztestRulesEngineService() throws Exception {
		createRuleSetForSponsor();

		saveRulesForSponsor();
		getRuleSetForSponsor();
		createRuleSetForInstitution();
		getRuleSetForInstitution();
		saveRulesForInstitution();

		this.createRuleSetForStudy();
		this.getRuleSetForStudy();
		this.saveRulesForStudy();

		this.createRuleForInstitution();
		this.createRuleForSponsor();
		this.createRuleForStudy();

		this.getAllRuleSetsForSponsor();
		this.getAllRuleSetsForStudy();

		this.getAllRuleSets();

		this.deployRuleSet();

		// updateRuleSet(RuleSet ruleSet)

		// deployRuleSet(RuleSet ruleSet) throws Exception;
		// unDeployRuleSet(RuleSet set) throws Exception;

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

	/**
	 * All the tests are to run in a particular order for that first let us make
	 * all the corresponding methods private and then call from one place
	 * 
	 */

	public void saveRulesForSponsor() throws Exception {
		RuleSet ruleSet = new RuleSet();
		ruleSet.setDescription(RuleType.AE_ASSESMENT_RULES.getName());

		List<Rule> rules = new ArrayList<Rule>();

		rules.add(makeRule(3));
		// rules.add(makeRule(2));
		// rules.add(makeRule(3));

		ruleSet.setRule(rules);
		rulesEngineService.saveRulesForSponsor(ruleSet,
				"National Cancer Institute");

		rulesEngineService.deployRuleSet(ruleSet);

	}

	private void createRuleSetForSponsor() throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String sponsorName = "National Cancer Institute";

		rulesEngineService.createRuleSetForSponsor(ruleSetName, sponsorName);

	}

	private void getRuleSetForSponsor() throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String sponsorName = "National Cancer Institute";
		RuleSet ruleSet = rulesEngineService.getRuleSetForSponsor(ruleSetName,
				sponsorName);
		System.out.println("***********************************************");
		System.out
				.println("***************Now some real test*****************");
		System.out.println("***********************************************");
		List<Rule> rules = ruleSet.getRule();
		for (Rule r : rules) {
			System.out.println("the id:" + r.getId());
			System.out.println("the Name:" + r.getMetaData().getName());
			System.out.println("The category Path:"
					+ r.getMetaData().getCategory().get(0).getPath());

		}
		System.out.println(ruleSet.getName());
		String packageName = RuleUtil.getPackageName(
				CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(),
				sponsorName, ruleSetName);
		System.out.println(packageName);
		assertEquals(packageName, ruleSet.getName());

	}

	private void createRuleSetForInstitution() throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String institutionName = "Duke Medical Center";
		rulesEngineService.createRuleSetForInstitution(ruleSetName,
				institutionName);
	}

	private void getRuleSetForInstitution() throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String institutionName = "Duke Medical Center";
		RuleSet ruleSet = rulesEngineService.getRuleSetForInstitution(
				ruleSetName, institutionName);
		String packageName = RuleUtil.getPackageName(
				CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(),
				institutionName, ruleSetName);
		assertEquals(packageName, ruleSet.getName());
	}

	private void saveRulesForInstitution() throws Exception {
		String institutionName = "Duke Medical Center";
		RuleSet ruleSet = new RuleSet();
		ruleSet.setDescription(RuleType.AE_ASSESMENT_RULES.getName());

		List<Rule> rules = new ArrayList<Rule>();

		rules.add(makeRule(1));
		rules.add(makeRule(2));
		rules.add(makeRule(3));
		ruleSet.setRule(rules);
		rulesEngineService.saveRulesForInstitution(ruleSet, institutionName);
	}

	private void createRuleSetForStudy() throws Exception {
		String ruleSetName = RuleType.REPORT_SCHEDULING_RULES.getName();
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		rulesEngineService.createRuleSetForSponsorDefinedStudy(ruleSetName, studyShortTitle,
				sponsorName);
	}

	private void getRuleSetForStudy() throws Exception {
		String ruleSetName = RuleType.REPORT_SCHEDULING_RULES.getName();
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		RuleSet ruleSet = rulesEngineService.createRuleSetForSponsorDefinedStudy(ruleSetName,
				studyShortTitle, sponsorName);
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(
				CategoryConfiguration.SPONSOR_DEFINED_STUDY_BASE.getPackagePrefix(),
				studyShortTitle, sponsorName, ruleSetName);
		assertEquals(packageName, ruleSet.getName());
	}

	private void saveRulesForStudy() throws Exception {
		RuleSet ruleSet = new RuleSet();
		ruleSet.setDescription(RuleType.REPORT_SCHEDULING_RULES.getName());
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";

		List<Rule> rules = new ArrayList<Rule>();

		rules.add(makeRule(1));
		rules.add(makeRule(2));
		rules.add(makeRule(3));
		ruleSet.setRule(rules);
		rulesEngineService.saveRulesForSponsorDefinedStudy(ruleSet, studyShortTitle,
				sponsorName);
	}

	private void createRuleForSponsor() throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String sponsorName = "National Cancer Institute";
		Rule rule = makeRule(71);
		rulesEngineService.createRuleForSponsor(rule, ruleSetName, sponsorName);

	}

	private void createRuleForStudy() throws Exception {
		String ruleSetName = RuleType.REPORT_SCHEDULING_RULES.getName();
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		Rule rule = makeRule(72);

		rulesEngineService.createRuleForSponsorDefinedStudy(rule, ruleSetName,
				studyShortTitle, sponsorName);
	}

	private void createRuleForInstitution() throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String institutionName = "Duke Medical Center";
		Rule rule = makeRule(73);
		rulesEngineService.createRuleForInstitution(rule, ruleSetName,
				institutionName);
	}

	private void getAllRuleSetsForSponsor() throws Exception {

		String sponsorName = "National Cancer Institute";
		List<RuleSet> ruleSets = rulesEngineService
				.getAllRuleSetsForSponsor(sponsorName);
		assertEquals(1, ruleSets.size());

	}

	private void getAllRuleSetsForStudy() throws Exception {

		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		List<RuleSet> ruleSets = rulesEngineService.getAllRuleSetsForSponsorDefinedStudy(
				studyShortTitle, sponsorName);
		assertEquals(1, ruleSets.size());
	}

	private void getAllRuleSetsForInstitution() throws Exception {

		String institutionName = "Duke Medical Center";
		List<RuleSet> ruleSets = rulesEngineService
				.getAllRuleSetsForInstitution(institutionName);
		assertEquals(1, ruleSets.size());

	}

	private void getRule() throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String institutionName = "Duke Medical Center";
		Rule rule = makeRule(99);
		String ruleId = rulesEngineService.createRuleForInstitution(rule,
				ruleSetName, institutionName);

		Rule persistedRule = rulesEngineService.getRule(ruleId);

		assertEquals(ruleId, persistedRule.getId());

	}

	private void getRulesByCategory() throws Exception {

		String catPath = "/CAAERS_BASE/SPONSOR/National_Cancer_Institute/AE_Assesment_Rules";

		List<Rule> rules = rulesEngineService.getRulesByCategory(catPath);
		assertEquals(4, rules.size());
	}

	private void getAllRuleSets() throws Exception {
		List<RuleSet> ruleSets = rulesEngineService.getAllRuleSets();
		for (RuleSet rs : ruleSets) {
			System.out.println("Rule Set Name:" + rs.getDescription());
		}
		assertEquals(4, ruleSets.size());
	}

	private void deployRuleSet() throws Exception {
		String ruleSetName = RuleType.REPORT_SCHEDULING_RULES.getName();
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		RuleSet ruleSet = rulesEngineService.getRuleSetForSponsorDefinedStudy(ruleSetName,
				studyShortTitle, sponsorName);

		rulesEngineService.deployRuleSet(ruleSet);
	}

	private Rule makeRule(int i) {
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
		action.setActionId("SERIOUS_ADVERSE_EVENT");

		rule1.setAction(action);

		return rule1;
	}
	

	
	private RuleSet createRuleForInstitutionDefinedStudy(int id) {

		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.AE_ASSESMENT_RULES.getName());

		Rule r = makeRule(id);
		// sponser based rules
		// need to add sponser name in the crieteria.
		r.getCondition().getColumn().add(this.createCriteriaForInstitute("National Cancer Institute"));
		//r.getCondition().getColumn().add(this.c)

		rs.getRule().add(r);
		return rs;
	}

	
	public static String[] getConfigLocations() {
        return new String[] {
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-configProperties.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-spring.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-db.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-dao.xml",
            "classpath*:gov/nih/nci/cabig/caaers/applicationContext-core-service.xml",
            "classpath*:config/spring/applicationContext-rules-services.xml"
        		
        };
    }
	
	public synchronized static ApplicationContext getDeployedApplicationContext() {
        if (acLoadFailure == null && applicationContext == null) {
            // This might not be the right place for this
            try {
                SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            } catch (NamingException e) {
                throw new RuntimeException("", e);
            }

            try {
            	log.debug("Initializing test version of deployed application context");
                applicationContext = new ClassPathXmlApplicationContext(getConfigLocations());
            } catch (RuntimeException e) {
                acLoadFailure = e;
                throw e;
            }
        } else if (acLoadFailure != null) {
            throw new CaaersSystemException(
                "Application context loading already failed.  Will not retry.  " +
                    "Original cause attached.", acLoadFailure);
        }
        return applicationContext;
    }
	

	
	public void atestNewRuleScheme() {

	}

}
