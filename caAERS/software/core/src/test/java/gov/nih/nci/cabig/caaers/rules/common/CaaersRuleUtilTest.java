package gov.nih.nci.cabig.caaers.rules.common;

import com.semanticbits.rules.brxml.Category;
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


    public void testGetFieldRuleSpecificCategory() throws Exception {
        Category c = CaaersRuleUtil.getFieldRuleSpecificCategory(caaersRulesEngineService.getRuleAuthoringService(), RuleType.FIELD_LEVEL_RULES.getName());
        assertNotNull(c);
        assertEquals("CAAERS_BASE" , c.getPath());
        Category c2 = caaersRulesEngineService.getRepositoryService().getCategory("/CAAERS_BASE/field_rules");
        assertEquals(c.getPath(), c2.getPath());
    }


}
