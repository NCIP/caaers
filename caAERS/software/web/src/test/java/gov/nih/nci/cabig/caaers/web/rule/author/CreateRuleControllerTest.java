package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * CreateRuleController Tester.
 *
 * @author Biju Joseph
 * @since <pre>05/10/2010</pre>
 * 
 */
public class CreateRuleControllerTest extends WebTestCase {

    CreateRuleController controller;
    private StudyDao studyDao;

    private ReportDefinitionDao reportDefinitionDao;

    private OrganizationDao organizationDao;

    private CaaersRulesEngineService caaersRulesEngineService;
    public void setUp() throws Exception {
        super.setUp();
        controller = new CreateRuleController();
        studyDao = registerDaoMockFor(StudyDao.class);
        reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class);
        organizationDao = registerDaoMockFor(OrganizationDao.class);
        caaersRulesEngineService = registerMockFor(CaaersRulesEngineService.class);

        controller.setStudyDao(studyDao);
        controller.setOrganizationDao(organizationDao);
        controller.setReportDefinitionDao(reportDefinitionDao);
        controller.setCaaersRulesEngineService(caaersRulesEngineService);
        

    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

   public void testFormBackingObject(){
       Object cmd = controller.formBackingObject(request);
       assertTrue(cmd instanceof CreateRuleCommand);
   }

}
