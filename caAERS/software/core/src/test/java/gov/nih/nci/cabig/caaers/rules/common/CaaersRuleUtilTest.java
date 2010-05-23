package gov.nih.nci.cabig.caaers.rules.common;

import com.semanticbits.rules.brxml.Category;
import com.semanticbits.rules.utils.RuleUtil;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * CaaersRuleUtil Tester.
 *
 * @author Biju Joseph
 * @since <pre>03/09/2010</pre>
 * 
 */
public class CaaersRuleUtilTest extends CaaersTestCase {
    CaaersRulesEngineService caaersRulesEngineService;

    public void setUp() throws Exception {
        super.setUp();
        caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testCreateCategory() throws Exception {
        CaaersRuleUtil.createCategory(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C/D");
        assertTrue(RuleUtil.categoryExist(caaersRulesEngineService.getRuleAuthoringService(), "/A"));
        assertTrue(RuleUtil.categoryExist(caaersRulesEngineService.getRuleAuthoringService(), "/A/B"));
        assertTrue(RuleUtil.categoryExist(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C"));
    }


    public void testCreateCategoryMultipleTimes() throws Exception {
       Category c1 =  CaaersRuleUtil.createCategory(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C/D/E");
       Category c2 =  CaaersRuleUtil.createCategory(caaersRulesEngineService.getRuleAuthoringService(), "/A/B/C/D/E");

       assertEquals(c1.getPath(), c2.getPath());
    }


}
