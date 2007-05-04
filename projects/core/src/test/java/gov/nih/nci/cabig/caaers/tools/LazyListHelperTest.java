package gov.nih.nci.cabig.caaers.tools;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import org.apache.commons.collections15.functors.InstantiateFactory;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * @author Rhett Sutphin
 */
public class LazyListHelperTest extends CaaersTestCase {
    private LazyListHelper helper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        helper = new LazyListHelper();
        helper.add(Object.class, new InstantiateFactory<Object>(Object.class));
        helper.add(String.class, new InstantiateFactory<String>(String.class));
    }

    public void testInternalListInitializedToEmpty() throws Exception {
        assertNotNull(helper.getInternalList(Object.class));
        assertEquals(0, helper.getInternalList(Object.class).size());
    }

    public void testLazyListInitializedToEmpty() throws Exception {
        assertNotNull(helper.getLazyList(Object.class));
        assertEquals(0, helper.getLazyList(Object.class).size());
    }

    public void testChangesToInternalShowInLazy() throws Exception {
        helper.getInternalList(String.class).add("Foom");
        assertEquals(1, helper.getLazyList(String.class).size());
        assertEquals("Foom", helper.getLazyList(String.class).get(0));
    }

    public void testLazyInitializes() throws Exception {
        assertNotNull(helper.getLazyList(Object.class).get(37));
        assertEquals(38, helper.getLazyList(Object.class).size());
    }

    public void testInternalListObjectIdentityMaintained() throws Exception {
        List<String> expectedList = new LinkedList<String>();
        helper.setInternalList(String.class, expectedList);
        assertSame(expectedList, helper.getInternalList(String.class));
    }

    public void testSetInternalReflectedInLazy() throws Exception {
        helper.getLazyList(String.class).add("Eleven");
        helper.getLazyList(String.class).add("Sieben");
        assertEquals(2, helper.getLazyList(String.class).size());
        helper.setInternalList(String.class, Arrays.asList("Floatsam"));
        assertEquals(1, helper.getLazyList(String.class).size());
        assertEquals("Floatsam", helper.getLazyList(String.class).get(0));
    }
}
