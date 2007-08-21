package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Condition;
import gov.nih.nci.cabig.caaers.rules.brxml.FieldConstraint;
import gov.nih.nci.cabig.caaers.rules.brxml.LiteralRestriction;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.ReadableRule;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.BRXMLHelper;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import java.io.File;
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


public class RulesEngineServiceObjectGraphTest extends TestCase {

	private RulesEngineService rulesEngineService;
	
    private static Log log = LogFactory.getLog(RulesEngineService2Test.class);
    private static RuntimeException acLoadFailure = null;

    private static ApplicationContext applicationContext = null;

    protected Set<Object> mocks = new HashSet<Object>();

    private EvaluationService aees;
    
    private static String SERIOUS_ADVERSE_EVENT = "SERIOUS_ADVERSE_EVENT";

	protected void setUp() throws Exception {
		
		super.setUp();


		this.rulesEngineService = new RulesEngineServiceImpl();
		
		aees = (EvaluationServiceImpl)getDeployedApplicationContext().getBean("evaluationService");
	}


	public void testRulesEngineService() throws Exception {
		String sponsorName = "National Cancer Institute";
		String ruleSetType = RuleType.AE_ASSESMENT_RULES.getName();
		String studyName = "ctcstudy";
		
		//String type = "gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm";
		
		//Class cls = Class.forName(type);

	//	InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/Users/sakkala/caaers/rules/n1.xml");
		//Unmarshaller unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.rules.brxml").createUnmarshaller();
		//RuleSet rs = (RuleSet)unmarshaller.unmarshal(inputStream);

		
		//RuleSet rs = sponserAEAssesmentRuleFlow(sponsorName,ruleSetType,studyName);	
		createAdverseEvent2(studyName,sponsorName);
	}

	
	private RuleSet sponserAEAssesmentRuleFlow(String sponsorName,String ruleSetType, String studyName) throws Exception {

		RuleSet rs = this.createRulesForSponsor(1, sponsorName);
		RulesEngineService res = new RulesEngineServiceImpl();
		res.saveRulesForSponsor(rs, sponsorName);

		rs = res.getRuleSetForSponsor(rs.getDescription(),sponsorName);
		

		// deploy rules...
		res.deployRuleSet(rs);
		
		return rs;

	}

	
	private RuleSet createRulesForSponsor(int id, String sponsorName) {

		RuleSet rs = new RuleSet();
		rs.setDescription(RuleType.AE_ASSESMENT_RULES.getName());


		
		Rule r = makeRule(id,SERIOUS_ADVERSE_EVENT);
		// sponser based rules
		// need to add sponser name in the crieteria.
		r.getCondition().getColumn().add(this.createCriteriaForSponsor(sponsorName));
		r.getCondition().getColumn().add(this.createCriteriaForIND("NCI"));

		rs.getRule().add(r);
		
	
		return rs;
	}

	private Study createStudy(String studyName, String orgName) {
		Study study = new Study ();
		
		
		Organization org = new Organization();
		org.setName(orgName);
		study.setPrimaryFundingSponsorOrganization(org);
		study.setShortTitle(studyName);
		
		StudyAgent sa1 = new StudyAgent();
		sa1.setAgentAsString("agent1");
		
		InvestigationalNewDrug investigationalNewDrug = new InvestigationalNewDrug();
		investigationalNewDrug.setHolderName("Wake Forest Comprehensive Cancer Center");
		
		StudyAgentINDAssociation studyAgentINDAssociation = new StudyAgentINDAssociation();
		studyAgentINDAssociation.setInvestigationalNewDrug(investigationalNewDrug);
		
		List<StudyAgentINDAssociation> studyAgentINDAssociations = new ArrayList<StudyAgentINDAssociation>();
		studyAgentINDAssociations.add(studyAgentINDAssociation);
		
		sa1.setStudyAgentINDAssociations(studyAgentINDAssociations);
		
		List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
		studyAgents.add(sa1);
		/*
		StudyAgent sa2 = new StudyAgent();
		sa2.setAgentAsString("agent2");
		
		
		
		studyAgents.add(sa2);
		*/
		study.setStudyAgents(studyAgents);
		
		
		StudyTherapy st = new StudyTherapy();
		st.setStudyTherapyType(StudyTherapyType.RADIATION);
		
		StudyTherapy st1 = new StudyTherapy();
		st1.setStudyTherapyType(StudyTherapyType.DEVICE);
		
		List<StudyTherapy> stList = new ArrayList<StudyTherapy>();
		stList.add(st);
		stList.add(st1);
		study.setStudyTherapies(stList);
		
		return study;
	}
	private void createAdverseEvent2(String studyName, String orgName) throws Exception {
		// execute rules...
		AdverseEvent ae1 = new AdverseEvent();
		ae1.setGrade(Grade.DEATH);
		ae1.setExpected(true);

		ae1.setHospitalization(Hospitalization.HOSPITALIZATION);
		
		AdverseEventMeddraLowLevelTerm x = new AdverseEventMeddraLowLevelTerm();
		LowLevelTerm ll = new LowLevelTerm();
		ll.setMeddraCode("1");
		ll.setMeddraTerm("Not Specified");
		
		System.out.println(ll.getFullName());
		x.setLowLevelTerm(ll);

		ae1.setAdverseEventMeddraLowLevelTerm(x);
		
		
		CtcCategory ctcCategory = new CtcCategory();
		ctcCategory.setId(new Integer("301"));
		
		CtcTerm ctcTerm = new CtcTerm();
		ctcTerm.setId(new Integer("3002"));
		
		ctcTerm.setCategory(ctcCategory);
		
		
		AdverseEventCtcTerm  adverseEventCtcTerm = new AdverseEventCtcTerm();
		adverseEventCtcTerm.setCtcTerm(ctcTerm);
		
		ae1.setAdverseEventCtcTerm(adverseEventCtcTerm);
		
/*
		Study s = new Study();
		Organization org = new Organization();
		org.setName(orgName);
		s.setPrimaryFundingSponsorOrganization(org);
		s.setShortTitle(studyName);
*/
		
		ReportDefinition rd = new ReportDefinition();
		rd.setName("R-3");

		ReportDefinition rd1 = new ReportDefinition();
		rd1.setName("R-1");
		
		Report report = new Report();
		report.setReportDefinition(rd);

		Report report1 = new Report();
		report1.setReportDefinition(rd1);
		
		ExpeditedAdverseEventReport ex = new ExpeditedAdverseEventReport();
		ex.addAdverseEvent(ae1);
		ex.addReport(report);
		ex.addReport(report1);
		
		Study study = createStudy(studyName, orgName);
		StudyParticipantAssignment spa = new StudyParticipantAssignment();
		StudySite ss = new StudySite();
		ss.setStudy(study);
		spa.setStudySite(ss);
		ex.setAssignment(spa);
		
		
		AdverseEventEvaluationService aees = new AdverseEventEvaluationServiceImpl();
		//String msg = aees.assesAdverseEvent(ae1, this.createStudy(studyName, orgName));
		List<String> sections = aees.mandatorySections(ex);

		//System.out.println(msg);
		
		for (String section : sections) {
			System.out.println(section);
		}
		
		
		//assertEquals(msg, "SERIOUS_ADVERSE_EVENT");

	}

	private Column createCriteriaForIND(String criteriaValue) {
		Column column = BRXMLHelper.newColumn();
		column.setObjectType("gov.nih.nci.cabig.caaers.rules.objectgraph.FactResolver");
		column.setIdentifier("factResolver");
		String expression = "assertFact(studySDO,"+"\"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug"+"\"," + "\"holderName" + "\"," + "\"" + criteriaValue+ "\""+")";
		
		System.out.println(expression);
		column.setExpression(expression);
		
		

		List<FieldConstraint> fieldConstraints = new ArrayList<FieldConstraint>();

		FieldConstraint fieldConstraint = new FieldConstraint();
		fieldConstraint.setFieldName("sss");
		fieldConstraints.add(fieldConstraint);
		ArrayList<LiteralRestriction> literalRestrictions = new ArrayList<LiteralRestriction>();
		LiteralRestriction literalRestriction = new LiteralRestriction();
		literalRestriction.setEvaluator("==");
		

		
		literalRestriction.getValue().add("true");
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
		
//		 1
		column1.setDisplayUri("Adverse Event");

		FieldConstraint fieldConstraint1 = new FieldConstraint();
		fieldConstraint1.setFieldName("grade");
//		 2 
		fieldConstraint1.setGrammerPrefix(" has a ");
		
//		 3
		fieldConstraint1.setDisplayUri("Grade");

		LiteralRestriction literalRestriction1 = new LiteralRestriction();
		literalRestriction1.setEvaluator(">=");
		
//		 4 
		literalRestriction1.setDisplayUri("Greater Than or Equal To");
		
		List<String> values1 = new ArrayList<String>();
		

		

		values1.add("3");

		literalRestriction1.setValue(values1);

		List<LiteralRestriction> lr1 = new ArrayList<LiteralRestriction>();
		lr1.add(literalRestriction1);

		fieldConstraint1.setLiteralRestriction(lr1);

		ArrayList<FieldConstraint> fields1 = new ArrayList<FieldConstraint>();
		fields1.add(fieldConstraint1);

		column1.setFieldConstraint(fields1);

		condition.getColumn().add(column1);


		/**
		 * Make it or break it
		 */

		rule1.setCondition(condition);



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
