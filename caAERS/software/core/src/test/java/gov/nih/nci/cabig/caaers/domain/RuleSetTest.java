package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 * @date 3/22/12
 */
public class RuleSetTest extends TestCase {
    
    public void testValidate() throws Exception {
        RuleSet r1 = Fixtures.createRuleSet();
        r1.setRuleType(RuleType.SAFETY_SIGNALLING_RULES);
        r1.setStudy(null);
        
        assertTrue(r1.validate().containsErrorWithCode("RUL_012"));
        r1.setStudy( Fixtures.createStudy("test"));
        assertFalse(r1.validate().containsErrorWithCode("RUL_012"));
        
        r1 = Fixtures.createRuleSet();
        r1.setRuleBindURI("x");
        r1.setRuleType(RuleType.REPORT_SCHEDULING_RULES);
        r1.setRuleLevel(null);
        assertTrue(r1.validate().containsErrorWithCode("RUL_014"));

    }
}
