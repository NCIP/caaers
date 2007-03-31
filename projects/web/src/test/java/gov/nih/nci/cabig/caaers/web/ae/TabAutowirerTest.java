package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.tabbedflow.Flow;

/**
 * @author Rhett Sutphin
 */
public class TabAutowirerTest extends AdverseEventControllerTestCase {
    public void testInjectIntoBasicsTab() throws Exception {
        BasicsTab injected = injectInto(new BasicsTab());
        assertNotNull(injected.getCtcDao());
    }

    private <T extends AeTab> T injectInto(T tab) {
        Flow<AdverseEventInputCommand> flow = new Flow<AdverseEventInputCommand>("test flow");
        flow.addTab(tab);
        autowirer.injectDependencies(flow);
        return tab;
    }
}
