package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Notification;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.CategoryConfiguration;
import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import junit.framework.TestCase;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RulesEngineServiceTest extends TestCase{
	
	private RulesEngineService rulesEngineService;
	
	
	
	protected void setUp() throws Exception {
		super.setUp();
		
		
		Connection       db;        // A connection to the database
		  Statement        sql;       // Our statement to run queries with
	
		  
		  Class.forName("org.postgresql.Driver");
		  db = DriverManager.getConnection("jdbc:postgresql://localhost/rules_test","postgres","postgres");
		  
		  sql = db.createStatement();
		  String sqlText_1 = "drop table rep_binval";
		  String sqlText_2 = "drop table rep_fsentry";
		  String sqlText_3 = "drop table rep_node";
		  String sqlText_4 = "drop table rep_prop";
		  String sqlText_5 = "drop table rep_refs";
		    try{
		    sql.executeUpdate(sqlText_1);
		    System.out.println(sqlText_1+" successfully executed");
		    }catch(org.postgresql.util.PSQLException pe){
		    	if(pe.getErrorCode()!=0) throw pe;
		    }
		    try{
		    sql.executeUpdate(sqlText_2);
		    System.out.println(sqlText_2+" successfully executed");
			}catch(org.postgresql.util.PSQLException pe){
		    	if(pe.getErrorCode()!=0) throw pe;
		    }
			try{
		    sql.executeUpdate(sqlText_3);
		    System.out.println(sqlText_3+" successfully executed");
			}catch(org.postgresql.util.PSQLException pe){
		    	if(pe.getErrorCode()!=0) throw pe;
		    }
			try{
		    sql.executeUpdate(sqlText_4);
		    System.out.println(sqlText_4+" successfully executed");
			}catch(org.postgresql.util.PSQLException pe){
		    	if(pe.getErrorCode()!=0) throw pe;
		    }
			try{
		    sql.executeUpdate(sqlText_5);
		    System.out.println(sqlText_5+" successfully executed");
			}catch(org.postgresql.util.PSQLException pe){
		    	if(pe.getErrorCode()!=0) throw pe;
		    }
			
			db.close();
		  
		    deleteDir(new File("/vinaykumar/Documents/repo2"));
		    File f = new File("/vinaykumar/Documents/repo2");
		    f.mkdir();  
		    
		    this.rulesEngineService = new RulesEngineServiceImpl();
	}
	
	public void testRulesEngineService() throws Exception{
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
		
		this.getAllRuleSetsForInstitution();
		this.getAllRuleSetsForSponsor();
		this.getAllRuleSetsForStudy();
		
		this.getRule();
		this.getRulesByCategory();
		this.getAllRuleSets();
		
		this.deployRuleSet();
		
		
		
		
		
		
		//updateRuleSet(RuleSet ruleSet) 
		
		
		
		
		
		
		//deployRuleSet(RuleSet ruleSet) throws Exception;
		//unDeployRuleSet(RuleSet set) throws Exception;
			
		
	}
	
	
	
	private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
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
	 *  All the tests are to run in a particular order for that
	 *  first let us make all the corresponding methods private and
	 *  then call from one place
	 *  
	 */
	
	private void saveRulesForSponsor() throws Exception{
		RuleSet ruleSet = new RuleSet();
		ruleSet.setDescription(RuleType.AE_ASSESMENT_RULES.getName());
		
		
		List<Rule> rules = new ArrayList<Rule>();
		
		rules.add(makeRule(1));
		rules.add(makeRule(2));
		rules.add(makeRule(3));
		
		ruleSet.setRule(rules);
		rulesEngineService.saveRulesForSponsor(ruleSet,"National Cancer Institute");
		
		
		
	}
	
	
	
	private void createRuleSetForSponsor() throws Exception{
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String sponsorName = "National Cancer Institute";
		
		rulesEngineService.createRuleSetForSponsor(ruleSetName, sponsorName);
				
	}
	
	private void getRuleSetForSponsor() throws Exception{
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String sponsorName = "National Cancer Institute";
		RuleSet ruleSet = rulesEngineService.getRuleSetForSponsor(ruleSetName, sponsorName);
		System.out.println("***********************************************");
		System.out.println("***************Now some real test*****************");
		System.out.println("***********************************************");
		List<Rule> rules = ruleSet.getRule();
		for(Rule r:rules){
			System.out.println("the id:"+r.getId());
			System.out.println("the Name:"+r.getMetaData().getName());
			System.out.println("The category Path:"+r.getMetaData().getCategory().get(0).getPath());
			
		}
		System.out.println(ruleSet.getName());
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.SPONSOR_BASE.getPackagePrefix(), sponsorName, ruleSetName);
		System.out.println(packageName);
		assertEquals(packageName, ruleSet.getName());
		
		
	}
	
	
	
	
	
	
	
	private void createRuleSetForInstitution() throws Exception{
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String institutionName = "Duke Medical Center";
		rulesEngineService.createRuleSetForInstitution(ruleSetName, institutionName);
	}
	
	private void getRuleSetForInstitution() throws Exception{
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String institutionName = "Duke Medical Center";
		RuleSet ruleSet = rulesEngineService.getRuleSetForInstitution(ruleSetName, institutionName);
		String packageName = RuleUtil.getPackageName(CategoryConfiguration.INSTITUTION_BASE.getPackagePrefix(), institutionName, ruleSetName);
		assertEquals(packageName, ruleSet.getName());
	}
	
	private void saveRulesForInstitution() throws Exception{
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
		rulesEngineService.createRuleSetForStudy(ruleSetName, studyShortTitle, sponsorName);
	}
	private void getRuleSetForStudy() throws Exception{
		String ruleSetName = RuleType.REPORT_SCHEDULING_RULES.getName();
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		RuleSet ruleSet = rulesEngineService.getRuleSetForStudy(ruleSetName, studyShortTitle, sponsorName);
		String packageName = RuleUtil.getStudySponsorSpecificPackageName(CategoryConfiguration.STUDY_BASE.getPackagePrefix(), studyShortTitle, sponsorName, ruleSetName);
		assertEquals(packageName, ruleSet.getName());
	}
	private void saveRulesForStudy() throws Exception{
		RuleSet ruleSet = new RuleSet();
		ruleSet.setDescription(RuleType.REPORT_SCHEDULING_RULES.getName());
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		
		
		
		
		List<Rule> rules = new ArrayList<Rule>();
		
		rules.add(makeRule(1));
		rules.add(makeRule(2));
		rules.add(makeRule(3));
		ruleSet.setRule(rules);
		rulesEngineService.saveRulesForStudy(ruleSet, studyShortTitle, sponsorName);
	}
	
	private void createRuleForSponsor() throws Exception{
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String sponsorName = "National Cancer Institute";
		Rule rule = makeRule(71);
		rulesEngineService.createRuleForSponsor(rule, ruleSetName, sponsorName);
		
	}
	private void createRuleForStudy() throws Exception{
		String ruleSetName = RuleType.REPORT_SCHEDULING_RULES.getName();
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		Rule rule = makeRule(72);
		
		rulesEngineService.createRuleForStudy(rule, ruleSetName, studyShortTitle, sponsorName);
		}
	private void createRuleForInstitution() throws Exception{
		String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
		String institutionName = "Duke Medical Center";
		Rule rule = makeRule(73);
		rulesEngineService.createRuleForInstitution(rule, ruleSetName, institutionName);
	}
	
	private void getAllRuleSetsForSponsor() throws Exception{
		
		String sponsorName = "National Cancer Institute";
		List<RuleSet> ruleSets= rulesEngineService.getAllRuleSetsForSponsor(sponsorName);
		assertEquals(1,ruleSets.size());
		
	}
	
private void getAllRuleSetsForStudy() throws Exception{
		
	String studyShortTitle = "Our test Study";
	String sponsorName = "Loudoun Medical Center";
		List<RuleSet> ruleSets= rulesEngineService.getAllRuleSetsForStudy(studyShortTitle, sponsorName);
		assertEquals(1,ruleSets.size());
	}

		private void getAllRuleSetsForInstitution() throws Exception{
			
			String institutionName = "Duke Medical Center";
			List<RuleSet> ruleSets= rulesEngineService.getAllRuleSetsForInstitution(institutionName);
			assertEquals(1,ruleSets.size());
			
		}
		
		
		private void getRule() throws Exception{
			String ruleSetName = RuleType.AE_ASSESMENT_RULES.getName();
			String institutionName = "Duke Medical Center";
			Rule rule = makeRule(99);
			String ruleId = rulesEngineService.createRuleForInstitution(rule, ruleSetName, institutionName);
			
			Rule persistedRule = rulesEngineService.getRule(ruleId);
			
			assertEquals(ruleId,persistedRule.getId());
			
		}
	
	private void getRulesByCategory() throws Exception{
		String catPath = "/CAAERS_BASE/SPONSOR/National_Cancer_Institute/AE_Assesment_Rules";
		
		List<Rule> rules = rulesEngineService.getRulesByCategory(catPath);
		assertEquals(4,rules.size());
	}
	
	private void getAllRuleSets() throws Exception{
		List<RuleSet> ruleSets = rulesEngineService.getAllRuleSets();
		for(RuleSet rs:ruleSets){
			System.out.println("Rule Set Name:"+rs.getDescription());
		}
		assertEquals(4,ruleSets.size());
	}
	
	private void deployRuleSet() throws Exception{
		String ruleSetName = RuleType.REPORT_SCHEDULING_RULES.getName();
		String studyShortTitle = "Our test Study";
		String sponsorName = "Loudoun Medical Center";
		RuleSet ruleSet = rulesEngineService.getRuleSetForStudy(ruleSetName, studyShortTitle, sponsorName);
		
		rulesEngineService.deployRuleSet(ruleSet);
	}
	
	private Rule makeRule(int i){
		Rule rule1 = new Rule();
		rule1.setMetaData(new MetaData());
		rule1.getMetaData().setName("Rule"+i);
		rule1.getMetaData().setDescription("Our test rule"+i);
		
		
		Condition condition = new Condition();
		condition.getEval().add("adverseEvent.getGrade().getCode() <= Grade.MODERATE.getCode()");

		Column column = new Column();
		column.setObjectType("AdverseEvent");
		column.setIdentifier("adverseEvent");	
		
		condition.getColumn().add(column);	
		/**
		 *  Make it or break it
		 */
		
		//Column column_fixed = new Column();
		//column_fixed.setObjectType("gov.nih.nci.cabig.caaers.rules.domain.AdverseEventEvaluationResult");
		//column_fixed.setIdentifier("adverseEventEvaluationResult");
		
		//condition.getColumn().add(column_fixed);
		
		rule1.setCondition(condition);
		
		Notification action = new Notification();
		action.setActionId("ROUTINE_AE");
		rule1.setAction(action);
		return rule1;
	}
	
	
	
	
	

}
