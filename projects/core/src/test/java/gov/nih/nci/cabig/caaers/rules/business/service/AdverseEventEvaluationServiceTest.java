package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;

import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.impl.RulesEngineServiceImpl;

public class AdverseEventEvaluationServiceTest extends CaaersTestCase {
    AdverseEventEvaluationServiceImpl service;

    RulesEngineService rulesEngineService;
    CaaersRulesEngineService caaersRulesEngineService;

    protected void setUp() throws Exception {
        super.setUp();
        service = new AdverseEventEvaluationServiceImpl();
        rulesEngineService = new RulesEngineServiceImpl();
        caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
        rulesEngineService = (RulesEngineService)getDeployedApplicationContext().getBean("ruleEngineService");
        service.setRulesEngineService(caaersRulesEngineService);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAssesAdverseEvent() throws Exception {
        Study study = Fixtures.createStudy("Test");
        Organization org = Fixtures.createOrganization("testOrg");
        study.setPrimaryFundingSponsorOrganization(org);
        AdverseEvent ae = new AdverseEvent();
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        ae.setReport(aeReport);
        String s = service.assesAdverseEvent(ae, study);

        System.out.println(s);
    }



}
