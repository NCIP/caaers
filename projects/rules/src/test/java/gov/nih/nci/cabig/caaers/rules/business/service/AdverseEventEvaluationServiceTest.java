package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import junit.framework.TestCase;

public class AdverseEventEvaluationServiceTest extends TestCase {
    AdverseEventEvaluationServiceImpl service;

    RulesEngineService rulesEngineService;

    protected void setUp() throws Exception {
        super.setUp();
        service = new AdverseEventEvaluationServiceImpl();
        rulesEngineService = new RulesEngineServiceImpl();
        service.setRulesEngineService(rulesEngineService);
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
