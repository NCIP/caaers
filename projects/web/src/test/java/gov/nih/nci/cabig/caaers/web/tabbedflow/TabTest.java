package gov.nih.nci.cabig.caaers.web.tabbedflow;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class TabTest extends CaaersTestCase {
    private Tab<?> tab = new Tab("Long title", "Short title", "View");

    public void testDefaultRefData() throws Exception {
        assertNotNull(tab.referenceData());
        assertEquals("Default refdata not empty", 0, tab.referenceData().size());
    }

    public void testDefaultTarget() throws Exception {
        tab.setNumber(5);
        assertEquals(6, tab.getTargetNumber());
    }
}
