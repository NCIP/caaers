package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Rhett Sutphin
 */
public class InputFieldGroupMapTest extends AbstractTestCase {
    @SuppressWarnings( { "MismatchedQueryAndUpdateOfCollection" })
    private InputFieldGroupMap map = new InputFieldGroupMap();

    public void testAddRepeatingGroupFactory() throws Exception {
        map.addRepeatingFieldGroupFactory(new RepeatingFieldGroupFactory("foo", "dc"));
        assertEquals(0, map.size());
        assertNotNull(map.get("foo4"));
        assertEquals(1, map.size());
    }

    public void testAddRepeatingGroupFactoryWithCount() throws Exception {
        map.addRepeatingFieldGroupFactory(new RepeatingFieldGroupFactory("foo", "dc"), 3);
        assertEquals(3, map.size());
        assertNotNull(map.get("foo1"));
        assertEquals(3, map.size());
        assertNotNull(map.get("foo12"));
        assertEquals(4, map.size());
    }

    public void testAddInputFieldGroup() throws Exception {
        map.addInputFieldGroup(new DefaultInputFieldGroup("bar"));
        assertEquals(1, map.size());
        assertNotNull(map.get("bar"));
    }
}
