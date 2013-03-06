/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.rules.common;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * RuleType Tester.
 *
 * @author Biju Joseph
 * @since <pre>06/06/2010</pre>
 * 
 */
public class RuleTypeTest extends TestCase {
    

    public void testGetName() throws Exception {
        assertEquals("Field Rules", RuleType.FIELD_LEVEL_RULES.getName());
        assertEquals("Mandatory Sections Rules", RuleType.MANDATORY_SECTIONS_RULES.getName());
        assertEquals("SAE Reporting Rules", RuleType.REPORT_SCHEDULING_RULES.getName());
    }

    public void testGetDescription() throws Exception {
        assertEquals("The rules regarding requiredness of UI fields", RuleType.FIELD_LEVEL_RULES.getDescription());
        assertEquals("The rules regarding identifying the mandatory sections", RuleType.MANDATORY_SECTIONS_RULES.getDescription());
        assertEquals("The rules regarding identifying the reporting periods", RuleType.REPORT_SCHEDULING_RULES.getDescription());
    }

}
