package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.ReadableRule;
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
    
    private static String SERIOUS_ADVERSE_EVENT = "SERIOUS_ADVERSE_EVENT";

	protected void setUp() throws Exception {
		
		super.setUp();

		Connection db; // A connection to the database
		Statement sql; // Our statement to run queries with

		Class.forName("org.postgresql.Driver");
		db = DriverManager.getConnection(
				"jdbc:postgresql://localhost/caaers", "postgres",
				"postgres");

		sql = db.createStatement();
		String sqlText_1 = "drop table rep_binval";
		String sqlText_2 = "drop table rep_fsentry";
		String sqlText_3 = "drop table rep_node";
		String sqlText_4 = "drop table rep_prop";
		String sqlText_5 = "drop table rep_refs";

		String sqlText_6 = "drop table rep_ver_binval";
		String sqlText_7 = "drop table rep_ver_fsentry";
		String sqlText_8 = "drop table rep_ver_node";
		String sqlText_9 = "drop table rep_ver_prop";
		String sqlText_0 = "drop table rep_ver_refs";
		
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

		try {
			sql.executeUpdate(sqlText_6);
			System.out.println(sqlText_6 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_7);
			System.out.println(sqlText_7 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_8);
			System.out.println(sqlText_8 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_9);
			System.out.println(sqlText_9 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		try {
			sql.executeUpdate(sqlText_0);
			System.out.println(sqlText_0 + " successfully executed");
		} catch (org.postgresql.util.PSQLException pe) {
			if (pe.getErrorCode() != 0)
				throw pe;
		}
		db.close();

		deleteDir(new File("/tmp/rules_repo/repo"));
		File f = new File("/tmp/rules_repo/repo");
		f.mkdir();

		this.rulesEngineService = new RulesEngineServiceImpl();
		
		aees = (EvaluationServiceImpl)getDeployedApplicationContext().getBean("evaluationService");
	}


	public void testRulesEngineService() throws Exception {
		String sponsorName = "National Cancer Institute";
		String ruleSetType = RuleType.AE_ASSESMENT_RULES.getName();
		String studyName = "cgems";
		

		   //rulePreview(sponsorName,ruleSetType,studyName);	
		
		   sponserAEAssesmentRuleFlow(sponsorName,ruleSetType,studyName);	
		/*
		sponsorDefinedStudyAEAssesmentRuleFlow(sponsorName,ruleSetType,studyName);
		
		ruleSetType = RuleType.REPORT_SCHEDULING_RULES.getName();
		institutionSAEReportingRuleFlow(sponsorName,ruleSetType,studyName);
		
		createAdverseEvents(studyName,sponsorName);
		//Create and deploy sponsor defined study level AE assesment Rules.
		
		
		
		/*
		getRuleSetForSponsor();
		
		createRuleSetForInstitution();
		getRuleSetForInstitution();
		saveRulesForInstitution();

		createRuleSetForStudy();
		getRuleSetForStudy();
		saveRulesForStudy();

		createRuleForInstitution();
		createRuleForSponsor();
		createRuleForStudy();

		getAllRuleSetsForSponsor();
		getAllRuleSetsForStudy();

		getAllRuleSets();

		deployRuleSet();
		*/

		// updateRuleSet(RuleSet ruleSet)

		// deployRuleSet(RuleSet ruleSet) throws Exception;
		// unDeployRuleSet(RuleSet set) throws Exception;

	}

	
	
	
	private void sponserAEAssesmentRuleFlow(String sponsorName,String ruleSetType, String studyName) throws Exception {

		RuleSet rs = this.createRulesForSponsor(1, sponsorName);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForSponsor(rs, sponsorName);

		rs = res.getRuleSetForSponsor(rs.getDescription(),sponsorName);
		
		
		// display readable info 
		
		ReadableRule readable = rs.getRule().get(0).getReadableRule();
		System.out.println("    ");
		for (String line:readable.getLine()) {
			System.out.println(line);			
		}	
		
		

		// deploy rules...
		res.deployRuleSet(rs);

	}

	
	private void sponsorDefinedStudyAEAssesmentRuleFlow(String sponsorName,String ruleSetType, String studyName) throws Exception {

		RuleSet rs = this.createRulesForSponsorDefinedStudy(3, sponsorName, studyName);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForSponsorDefinedStudy(rs, studyName, sponsorName);

		rs = res.getRuleSetForSponsorDefinedStudy(rs.getDescription(),studyName,sponsorName);
		

		// deploy rules...
		res.deployRuleSet(rs);

	}
	

	private void institutionSAEReportingRuleFlow(String institutionName,String ruleSetType, String studyName) throws Exception {

		RuleSet rs = this.createRulesForInstitution(2, institutionName);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForInstitution(rs, institutionName);

		rs = res.getRuleSetForInstitution(rs.getDescription(),institutionName);
		

		// deploy rules...
		res.deployRuleSet(rs);

	}
	
	private void createRuleSetForSponsor(String sponsorName, String ruleSetName) throws Exception {
		rulesEngineService.createRuleSetForSponsor(ruleSetName, sponsorName);
	}
	
	private RuleSet createRulesForSponsor(int id, String sponsorName) {

		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.AE_ASSESMENT_RULES.getName());
		
		


		Rule r = makeRule(id,SERIOUS_ADVERSE_EVENT);
		// sponser based rules
		// need to add sponser name in the crieteria.
		r.getCondition().getColumn().add(this.createCriteriaForSponsor(sponsorName));

		rs.getRule().add(r);
		return rs;
	}
	
	private RuleSet createRulesForInstitution(int id, String institutionName) throws Exception {
		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.REPORT_SCHEDULING_RULES.getName());

		Rule r = makeRule(id,"10 Day Report");
		// sponser based rules
		// need to add sponser name in the crieteria.
		r.getCondition().getColumn().add(this.createCriteriaForInstitute(institutionName));

		rs.getRule().add(r);
		return rs;
	}
	
	private RuleSet createRulesForSponsorDefinedStudy(int id, String sponsorName,String studyName) {

		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.AE_ASSESMENT_RULES.getName());

		Rule r = makeRule(id,SERIOUS_ADVERSE_EVENT);
		// sponser based rules
		// need to add sponser name in the crieteria.
		r.getCondition().getColumn().add(this.createCriteriaForSponsor(sponsorName));
		r.getCondition().getColumn().add(this.createCriteriaForStudy(studyName));

		rs.getRule().add(r);
		return rs;
	}
	
	private void createAdverseEvents(String studyName, String orgName) throws Exception {
		createAdverseEvent1( studyName,  orgName);
		createAdverseEvent2( studyName,  orgName);
	
	}
	
	private void createAdverseEvent1(String studyName, String orgName) throws Exception {

		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);
		ae1.setHospitalization(Hospitalization.NONE);

		Study s = new Study();
		Organization org = new Organization();
		org.setName(orgName);
		s.setPrimaryFundingSponsorOrganization(org);
		s.setShortTitle(studyName);

		AdverseEventEvaluationService aees = new AdverseEventEvaluationServiceImpl();
		String msg = aees.assesAdverseEvent(ae1, s);

		System.out.println(msg);
		assertEquals(msg, "CAN_NOT_DETERMINED");

	}
	private void createAdverseEvent2(String studyName, String orgName) throws Exception {
		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.MILD);

		ae1.setHospitalization(Hospitalization.HOSPITALIZATION);

		Study s = new Study();
		Organization org = new Organization();
		org.setName(orgName);
		s.setPrimaryFundingSponsorOrganization(org);
		s.setShortTitle(studyName);

		AdverseEventEvaluationService aees = new AdverseEventEvaluationServiceImpl();
		String msg = aees.assesAdverseEvent(ae1, s);

		System.out.println(msg);
		assertEquals(msg, "SERIOUS_ADVERSE_EVENT");

	}


	
	
	private RuleSet createRulesForInstitute(int id) {

		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.AE_ASSESMENT_RULES.getName());

		Rule r = makeRule(id,"");
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
		column.setExpression("getPrimaryFundingSponsorOrganization().getName()");

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

	private Column createCriteriaForStudy(String criteriaValue) {
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.domain.Study");
		column.setIdentifier("studySDO");
		column.setExpression("getShortTitle()");

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





	private RuleSet getRuleSetForSponsor(String sponsorName) throws Exception {
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		RuleSet ruleSet = rulesEngineService.getRuleSetForSponsor(ruleSetName,
				sponsorName);
		return ruleSet;
		
		/*
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
		*/

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

	private Rule makeRule(int i, String actionStr) {
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
		// 1
		column1.setDisplayUri("Adverse Event");

		FieldConstraint fieldConstraint1 = new FieldConstraint();
		fieldConstraint1.setFieldName("grade");
		// 2 
		fieldConstraint1.setGrammerPrefix(" has a ");
		
		// 3
		fieldConstraint1.setDisplayUri("Grade");

		LiteralRestriction literalRestriction1 = new LiteralRestriction();
		literalRestriction1.setEvaluator("==");
		
		// 4 
		literalRestriction1.setDisplayUri("Equal To");
		
		// 5 
		
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
		column2.setDisplayUri("Adverse Event");

		FieldConstraint fieldConstraint2 = new FieldConstraint();
		fieldConstraint2.setFieldName("hospitalization");
		fieldConstraint2.setGrammerPrefix(" has a ");
		
		fieldConstraint2.setDisplayUri("Hospitalization");

		LiteralRestriction literalRestriction2 = new LiteralRestriction();
		literalRestriction2.setEvaluator("==");
		literalRestriction2.setDisplayUri("Equal To");
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

		List<String> action = new ArrayList<String>();
		action.add(actionStr);

		rule1.setAction(action);
		
		

			ReadableRule readable = new ReadableRule();
			List<String> line = new ArrayList<String>();
			
			// add lines..
			line.add("If");
			for (Column column:rule1.getCondition().getColumn()) {
				
				// skip rule type filters
				if (!column.getExpression().equals("getPrimaryFundingSponsorOrganization().getName()")) {
					line.add(RuleUtil.readableColumn(column));
					line.add("AND");					
				}
				

			}
			line.remove(line.size()-1);
			readable.setLine(line);
			rule1.setReadableRule(readable);			


		return rule1;
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
