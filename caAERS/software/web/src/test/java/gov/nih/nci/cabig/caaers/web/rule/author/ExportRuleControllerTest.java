package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.TestCase;
import org.easymock.EasyMock;

/**
 * @author: Biju Joseph
 */
public class ExportRuleControllerTest extends WebTestCase {
    ExportRuleController controller;
    RuleSetDao ruleSetDao;
    CaaersRulesEngineService caaersRulesEngineService;

    public void setUp() throws Exception {
        super.setUp();
       controller = new ExportRuleController();
       ruleSetDao = registerDaoMockFor(RuleSetDao.class);
       caaersRulesEngineService = registerMockFor(CaaersRulesEngineService.class);
       controller.setCaaersRulesEngineService(caaersRulesEngineService);
       controller.setRuleSetDao(ruleSetDao);
    }

    public void testHandleEmptyRuleSetName() throws Exception {
        EasyMock.expect(ruleSetDao.getByBindURI(null)).andReturn(null);
        replayMocks();
        try{
            controller.handle(request, response, null, null);
           fail("Must throw error");
        }catch (CaaersSystemException e){

        }
        verifyMocks();
    }


    public void testHandle() throws Exception {
        assertSame(ruleSetDao, controller.getRuleSetDao());
        assertSame(caaersRulesEngineService, controller.getCaaersRulesEngineService());
        RuleSet ruleSet = Fixtures.createRuleSet();
        ruleSet.setRuleBindURI("x");
        request.setParameter("ruleSetName", ruleSet.getRuleBindURI());
        EasyMock.expect(ruleSetDao.getByBindURI("x")).andReturn(ruleSet);
        EasyMock.expect(caaersRulesEngineService.exportRules("x")).andReturn("<y>a</y>");
        replayMocks();
        controller.handle(request, response, null, null);
        verifyMocks();
        assertEquals("<y>a</y>", response.getContentAsString());
    }


    public void testHandleRequest() throws Exception {
        assertSame(ruleSetDao, controller.getRuleSetDao());
        assertSame(caaersRulesEngineService, controller.getCaaersRulesEngineService());
        RuleSet ruleSet = Fixtures.createRuleSet();
        ruleSet.setRuleBindURI("x");
        request.setParameter("ruleSetName", ruleSet.getRuleBindURI());
        EasyMock.expect(ruleSetDao.getByBindURI("x")).andReturn(ruleSet);
        EasyMock.expect(caaersRulesEngineService.exportRules("x")).andReturn("<y>a</y>");
        replayMocks();
        controller.handleRequest(request,response);
        verifyMocks();
        assertEquals("<y>a</y>", response.getContentAsString());
    }

    public void testCommand()throws Exception{
        ExportRuleCommand cmd = new ExportRuleCommand();
        cmd.setFolder("f");
        cmd.setMessage("m");
        cmd.setUpdated(true);
        assertEquals("f", cmd.getFolder());
        assertEquals("m", cmd.getMessage());
        assertTrue(cmd.isUpdated());
    }
}
