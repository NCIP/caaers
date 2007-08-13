package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import junit.framework.TestCase;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode.*;

/**
 * @author Rhett Sutphin
 */
public class TreeNodeTest extends TestCase {
    private TreeNode deepTree = section("container",
        property("R",
            property("a",
                property("A1"),
                property("A2")
            ),
            section("Land of Bees",
                property("b",
                     property("B1"),
                     property("B2")
                )
            ),
            list("l", "Lambda",
                property("L7",
                    property("L71"),
                    property("L72"),
                    property("L73")
                ),
                property("L9")
            ),
            property("duplicate", "Dup 0",
                property("duplicate", "Dup 1",
                    property("duplicate", "Dup 2",
                        property("duplicate", "Dup 3")
                    )
                )
            )
        )
    );

    public void testPropertyPathForSingleNode() throws Exception {
        assertEquals("prop", property("prop").getPropertyPath());
    }

    public void testPropertyPathThroughSection() throws Exception {
        TreeNode end;
        property("top", "Top",
            section("Four",
                end = property("bottom", "End")
            )
        );
        assertEquals("top.bottom", end.getPropertyPath());
    }

    public void testPropertyPathStartingAtSection() throws Exception {
        TreeNode end;
        section("DC",
            end = property("end", "DC")
        );
        assertEquals("end", end.getPropertyPath());
    }

    public void testPropertyPathForList() throws Exception {
        TreeNode list = list("coll", "Coll");
        assertEquals("coll[]", list.getPropertyPath());
    }

    public void testPropertyPathWithList() throws Exception {
        TreeNode end;
        property("a",
            list("c", "Coll",
                end = property("e")
            )
        );
        assertEquals("a.c[].e", end.getPropertyPath());
    }

    public void testDefaultDisplayNamesCreatedForList() throws Exception {
        TreeNode l = list("a", "Alphaville");
        assertEquals("Alphavilles", l.getDisplayName());
        assertEquals("Alphaville 3", l.getDisplayName(2));
    }

    public void testDefaultDisplayNamesCreatedForProperty() throws Exception {
        TreeNode p = property("a", "Alphaville");
        assertEquals("Alphaville", p.getDisplayName());
        assertEquals("Alphaville", p.getDisplayName(2));
    }

    public void testFindSimpleInternalProperty() throws Exception {
        assertFindInDeepTree("R.a", "a");
    }

    public void testFindSimpleLeafProperty() {
        assertFindInDeepTree("R.a.A2", "A2");
    }

    public void testFindInternalPropertyInSection() {
        assertFindInDeepTree("R.b", "b");
    }

    public void testFindLeafPropertyInSection() {
        assertFindInDeepTree("R.b.B1", "B1");
    }

    public void testFindInternalPropertyInList() {
        assertFindInDeepTree("R.l[].L7", "L7");
    }

    public void testFindLeafPropertyInList() {
        assertFindInDeepTree("R.l[].L7.L72", "L72");
    }

    public void testFindIsRelative() throws Exception {
        TreeNode a = deepTree.getChildren().get(0).getChildren().get(0);
        assertEquals("Test setup failure", "R.a", a.getPropertyPath());
        assertNull(a.find("a.A1"));
        assertNotNull(a.find("A1"));
    }

    public void testFindNodeWithSameNameAsParent() throws Exception {
        assertFindInDeepTree("R.duplicate", "duplicate", "Dup 0");
        assertFindInDeepTree("R.duplicate.duplicate", "duplicate", "Dup 1");
        assertFindInDeepTree("R.duplicate.duplicate.duplicate", "duplicate", "Dup 2");
        assertFindInDeepTree("R.duplicate.duplicate.duplicate.duplicate", "duplicate", "Dup 3");
    }

    private void assertFindInDeepTree(String path, String expectedProperty, String expectedDisplay) {
        assertNode(path, expectedProperty, expectedDisplay, deepTree.find(path));
    }

    private void assertFindInDeepTree(String path, String expectedProperty) {
        assertFindInDeepTree(path, expectedProperty, null);
    }

    private static void assertNode(String expectedPath, String expectedProperty, String expectedDisplay, TreeNode actual) {
        assertNotNull("Node is null", actual);
        assertEquals("Wrong path", expectedPath, actual.getPropertyPath());
        assertEquals("Wrong prop", expectedProperty, actual.getPropertyName());
        if (expectedDisplay != null) {
            assertEquals("Wrong display name", expectedDisplay, actual.getDisplayName());
        }
    }
}
