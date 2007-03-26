package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventTest extends CaaersTestCase {
    public void testDefaultExpectedness() throws Exception {
        AdverseEvent ae = new AdverseEvent();
        assertNotNull(ae.getExpected());
        assertFalse(ae.getExpected());
    }
}
