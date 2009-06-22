package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class DefaultListDisplayNameCreatorTest extends TestCase {
    public void testIndexedName() throws Exception {
        DefaultListDisplayNameCreator creator = new DefaultListDisplayNameCreator("Ear");
        assertEquals("Ear 1", creator.createIndexedName(0));
        assertEquals("Ear 5", creator.createIndexedName(4));
    }

    public void testGenericNameIsPluralized() throws Exception {
        DefaultListDisplayNameCreator creator = new DefaultListDisplayNameCreator("Ear");
        assertEquals("Ears", creator.createGenericName());
    }
}
