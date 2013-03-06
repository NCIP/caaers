/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Biju Joseph
 */
public class RuleUtilControllerTest extends WebTestCase  {
    RuleUtilController controller;
    CaaersRulesEngineService facade;

    public void setUp() throws Exception {
        super.setUp();
       controller = new RuleUtilController();
       request.setMethod("GET");
       request.setParameter("ruleSetName", "x");
       facade = registerMockFor(CaaersRulesEngineService.class);
       controller.setCaaersRulesEngineService(facade);

    }

    public void testHandleRequestInternal() throws Exception {
           
           facade.deleteRuleSet("x");
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("redirect:/pages/rule/list", mv.getViewName());
        verifyMocks();
    }
}
