package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventsDisplayNameCreatorTest extends TestCase {
    private AdverseEventsDisplayNameCreator creator = new AdverseEventsDisplayNameCreator();

    public void testIndexedPrimary() throws Exception {
        assertEquals("Primary adverse event", creator.createIndexedName(0));
    }

    public void testIndexedOther() throws Exception {
        assertEquals("Adverse event 2", creator.createIndexedName(1));
        assertEquals("Adverse event 8", creator.createIndexedName(7));
    }

    public void testGeneric() throws Exception {
        assertEquals("Adverse events", creator.createGenericName());
    }
}
