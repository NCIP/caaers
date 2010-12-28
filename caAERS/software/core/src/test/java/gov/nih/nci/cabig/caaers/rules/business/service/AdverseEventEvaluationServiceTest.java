package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.utils.RepositoryCleaner;
import com.semanticbits.rules.utils.RulesPropertiesFileLoader;
import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportMandatoryFieldDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RequirednessIndicator;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdverseEventEvaluationServiceTest extends CaaersTestCase {
    AdverseEventEvaluationServiceImpl service;

    CaaersRulesEngineService caaersRulesEngineService;
    RulesPropertiesFileLoader propertiesBean;
    String bindURL =  "gov.nih.nci.cabig.caaers.rules.field_rules";

    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
        return testDataStream;
    }

    private File createTmpFileFromResource(String testDataFileName) throws FileNotFoundException {
        try{
                long l = System.currentTimeMillis();
                File f = File.createTempFile("t_" + l , "_import.xml");
                FileCopyUtils.copy(createInputStream(testDataFileName), new FileOutputStream(f));
                return f;
        }catch(IOException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

    }


    private void importRulesFile(String fileName) throws Exception{
        File f = createTmpFileFromResource(fileName);
        caaersRulesEngineService.importRules(f.getAbsolutePath());
    }

    protected void setUp() throws Exception {
        super.setUp();
        service = (AdverseEventEvaluationServiceImpl)getDeployedApplicationContext().getBean("adverseEventEvaluationService");
        caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
        service.setCaaersRulesEngineService(caaersRulesEngineService);
    }

    @Override
    protected void tearDown() throws Exception {
        cleanRulesRepo();//cleans the rules repository
        super.tearDown();
    }

    private void cleanRulesRepo(){
       try{

		propertiesBean = RulesPropertiesFileLoader.getLoadedInstance();
        String url = propertiesBean.getProperties().getProperty("rules.repository");
        new RepositoryCleaner(url);

       }catch(Exception ignore){
           System.out.println("Ignore below exception");
           ignore.printStackTrace();
       }
    }


	/** test field level rule on out come */
	public void testFieldRulesOnOutcome() throws Exception {

        importRulesFile("test_field_level_rules.xml");
        

        AdverseEventReportingPeriod aer = Fixtures.createReportingPeriod(1, "10/01/2009", "11/01/2009");
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        aer.addAeReport(aeReport);
        
        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.LIFE_THREATENING);
        ae1.addOutcome(Fixtures.createOutcome(1, OutcomeType.LIFE_THREATENING));
        AdverseEvent ae2 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae2.addOutcome(Fixtures.createOutcome(2, OutcomeType.DEATH));
        aeReport.getAdverseEvents().clear();
        aeReport.addAdverseEvent(ae1);
        aeReport.addAdverseEvent(ae2);
        aeReport.getAssignment().setStudySite(new StudySite());

        {
            String result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae1 , ae2
        }));

            System.out.println(result);
        }

        {
            String result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae2 , ae1
        }));

            System.out.println(result);
        }

        {
            String result = service.evaluateFieldLevelRules(bindURL, "Rule-2",  Arrays.asList(new Object[]{
                aeReport, ae1 , ae2
        }));

            System.out.println(result);
        }

        {
            String result = service.evaluateFieldLevelRules(bindURL, "Rule-2",  Arrays.asList(new Object[]{
                aeReport, ae2 , ae1
        }));

            System.out.println(result);
        }
//
//        //for ae1
//        String result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
//                aeReport, ae1
//        }));
//        assertEquals("MANDATORY", result );
//
//        result  = service.evaluateFieldLevelRules(bindURL, "Rule-2",  Arrays.asList(new Object[]{
//                aeReport, ae1
//        }));
//        assertEquals("OPTIONAL", result);
//
//
//        result = service.evaluateFieldLevelRules(bindURL, "Rule-1,Rule-2",  Arrays.asList(new Object[]{
//                aeReport, ae1
//        }));
//        assertEquals("MANDATORY", result);
//
//        //for ae2
//        result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
//                aeReport, ae2
//        }));
//        assertEquals("OPTIONAL", result );
//
//        result  = service.evaluateFieldLevelRules(bindURL, "Rule-2",  Arrays.asList(new Object[]{
//                aeReport, ae2
//        }));
//        assertEquals("NA", result);
//
//
//        result = service.evaluateFieldLevelRules(bindURL, "Rule-1,Rule-2",  Arrays.asList(new Object[]{
//                aeReport, ae2
//        }));
//        assertEquals("NA", result);


	}

    //Rule-1 "IND holder is CTEP then Mandatory"
    public void testEvaluateFieldRulesOnINDHolder() throws Exception{

        AdverseEventReportingPeriod aer = Fixtures.createReportingPeriod(1, "10/01/2009", "11/01/2009");
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        aer.addAeReport(aeReport);

        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.LIFE_THREATENING);
        ae1.addOutcome(Fixtures.createOutcome(1, OutcomeType.LIFE_THREATENING));
        AdverseEvent ae2 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae2.addOutcome(Fixtures.createOutcome(2, OutcomeType.DEATH));
        aeReport.getAdverseEvents().clear();
        aeReport.addAdverseEvent(ae1);
        aeReport.addAdverseEvent(ae2);

        Study s = Fixtures.createStudy("s");
        StudySite ss = Fixtures.createStudySite(Fixtures.createOrganization("x"), 1) ;
        s.addStudySite(ss);
        aeReport.getAssignment().setStudySite(ss);

        Organization ctep = Fixtures.createOrganization("Cancer Therapy Evaluation Program");
        InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(Fixtures.createOrganizationINDHolder(ctep), "-111");
        StudyAgent sa = Fixtures.createStudyAgent("my agent", ind, INDType.CTEP_IND);
        aeReport.getStudy().addStudyAgent(sa);
        Report report = Fixtures.createReport("x");


        importRulesFile("test_intervention_field_level_rules.xml");
        String result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae1, s
        }));
        assertEquals("MANDATORY", result );
        result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae2, s
        }));
        assertEquals("MANDATORY", result );
    }


    //Rule-1 "IND holder is CTEP then Mandatory otherwise OPTIONAL"
    public void testEvaluateFieldRulesOnInvalidINDHolder() throws Exception{


        AdverseEventReportingPeriod aer = Fixtures.createReportingPeriod(1, "10/01/2009", "11/01/2009");
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        aer.addAeReport(aeReport);

        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.LIFE_THREATENING);
        ae1.addOutcome(Fixtures.createOutcome(1, OutcomeType.LIFE_THREATENING));
        AdverseEvent ae2 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae2.addOutcome(Fixtures.createOutcome(2, OutcomeType.DEATH));
        aeReport.getAdverseEvents().clear();
        aeReport.addAdverseEvent(ae1);
        aeReport.addAdverseEvent(ae2);

        Study s = Fixtures.createStudy("s");
        StudySite ss = Fixtures.createStudySite(Fixtures.createOrganization("x"), 1) ;
        s.addStudySite(ss);
        aeReport.getAssignment().setStudySite(ss);

        Organization ctep = Fixtures.createOrganization("xxxxx");
        InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(Fixtures.createOrganizationINDHolder(ctep), "-111");
        StudyAgent sa = Fixtures.createStudyAgent("my agent", ind, INDType.CTEP_IND);
        aeReport.getStudy().addStudyAgent(sa);
        Report report = Fixtures.createReport("x");


        importRulesFile("test_intervention_field_level_rules.xml");
        String result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae1, s
        }));
        assertEquals("OPTIONAL", result );
        result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae2, s
        }));
        assertEquals("OPTIONAL", result );
    }


    //Rule-1 "IND holder is CTEP then Mandatory, but the Study Agent is retired"
    public void testEvaluateFieldRulesOnINDHolderWhenObjectInRuleConditionIsRetired() throws Exception{



        ReportMandatoryFieldDefinition mfd = Fixtures.createMandatoryField("x", RequirednessIndicator.MANDATORY);
        mfd.setRuleBindURL("gov.nih.nci.cabig.caaers.rules.field_rules");
        mfd.setRuleName("Rule-1");

        AdverseEventReportingPeriod aer = Fixtures.createReportingPeriod(1, "10/01/2009", "11/01/2009");
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        aer.addAeReport(aeReport);

        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.LIFE_THREATENING);
        ae1.addOutcome(Fixtures.createOutcome(1, OutcomeType.LIFE_THREATENING));
        AdverseEvent ae2 = Fixtures.createAdverseEvent(1, Grade.DEATH);
        ae2.addOutcome(Fixtures.createOutcome(2, OutcomeType.DEATH));
        aeReport.getAdverseEvents().clear();
        aeReport.addAdverseEvent(ae1);
        aeReport.addAdverseEvent(ae2);

        Study s = Fixtures.createStudy("s");
        StudySite ss = Fixtures.createStudySite(Fixtures.createOrganization("x"), 1) ;
        s.addStudySite(ss);
        aeReport.getAssignment().setStudySite(ss);

        Organization ctep = Fixtures.createOrganization("Cancer Therapy Evaluation Program");
        InvestigationalNewDrug ind = Fixtures.createInvestigationalNewDrug(Fixtures.createOrganizationINDHolder(ctep), "-111");
        StudyAgent sa = Fixtures.createStudyAgent("my agent", ind, INDType.CTEP_IND);
        aeReport.getStudy().addStudyAgent(sa);
        sa.retire();
        Report report = Fixtures.createReport("x");


        importRulesFile("test_intervention_field_level_rules.xml");
        String result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae1, s
        }));
        assertEquals("OPTIONAL", result );
        result = service.evaluateFieldLevelRules(bindURL, "Rule-1",  Arrays.asList(new Object[]{
                aeReport, ae2, s
        }));
        assertEquals("OPTIONAL", result );
    }




}
