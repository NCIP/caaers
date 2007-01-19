package gov.nih.nci.cabig.caaers.web.tabbedflow;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class FlowTest extends CaaersTestCase {
    private Flow<?> flow = new Flow("Test flow");

    public void testAddTabSetsNumber() throws Exception {
        flow.addTab(new Tab("Zero", "0", null));
        flow.addTab(new Tab("One",  "1", null));
        flow.addTab(new Tab("Two",  "2", null));
        assertEquals(0, (int) flow.getTab(0).getNumber());
        assertEquals(1, (int) flow.getTab(1).getNumber());
        assertEquals(2, (int) flow.getTab(2).getNumber());
    }
}
