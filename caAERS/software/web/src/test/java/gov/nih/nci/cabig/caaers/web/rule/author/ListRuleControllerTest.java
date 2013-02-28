/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Biju Joseph
 */
public class ListRuleControllerTest extends WebTestCase {
    ListRuleController controller;
    CaaersRulesEngineService engine;
    
    public void setUp() throws Exception {
      super.setUp();
        controller = new ListRuleController();
        engine = registerMockFor(CaaersRulesEngineService.class);
        controller.setCaaersRulesEngineService(engine);
       
    }

    public void testHandleGetRequest() throws Exception {
        request.setMethod("GET");
        EasyMock.expect(engine.getAllRuleSets()).andReturn(new ArrayList<RuleSet>());
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("rule/author/list", mv.getViewName());
        verifyMocks();
        
    }


    public void testHandlePostRequestInvalid() throws Exception {
        request.setMethod("POST");
        request.setParameter("ruleSetFile1", "");
        EasyMock.expect(engine.getAllRuleSets()).andReturn(new ArrayList<RuleSet>()).anyTimes();
        replayMocks();

        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("rule/author/list", mv.getViewName());
        BindingResult result = (BindingResult)mv.getModel().get("org.springframework.validation.BindingResult.command");
        assertTrue(result.hasErrors());
        verifyMocks();

    }


    public void testOnSubmit() throws Exception {
        request.setMethod("POST");
        ArrayList<String> list = new ArrayList<String>();
        list.add("x");
        list.add("y");


        EasyMock.expect(engine.getAllRuleSets()).andReturn(new ArrayList<RuleSet>()).anyTimes();
        EasyMock.expect(engine.importRules((String)EasyMock.anyObject())).andReturn(list).anyTimes();

        replayMocks();
        ListRuleCommand cmd = new ListRuleCommand(engine) ;
        cmd.setRuleSetFile1(new MockMultipartFile("junk.xml", "<junk />".getBytes()));

        ModelAndView mv = controller.onSubmit(request, response, cmd, new BindException("",""));


        assertEquals("Rules imported successfully <br/>Following report definitions are created with basic information.<br/>Please update these report definitions<br/>y<br/>", cmd.getMessage());

        verifyMocks();

    }
}
