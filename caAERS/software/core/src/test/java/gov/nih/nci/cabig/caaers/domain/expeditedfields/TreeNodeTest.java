package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import static gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode.codedOrOther;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode.list;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode.property;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class TreeNodeTest extends TestCase {
    private TreeNode deepTree;

    private TestTree instance;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        deepTree = section(ExpeditedReportSection.BASICS_SECTION,
                            property("r",
                                       property("a",
                                                property("a1"),
                                                property("a2")
                                       ),
                                       section(ExpeditedReportSection.MEDICAL_INFO_SECTION,
                                               property("b",
                                                       property("b1"),
                                                       property("b2")
                                               )
                                       ),
                                       list("l", "Lambda",
                                             property("l7",
                                                property("l71"),
                                                property("l72"),
                                                property("l73")
                                             ),
                                             property("L9")
                                       ),
                                       property("duplicate", "Dup 0",
                                               property("duplicate", "Dup 1",
                                                       property("duplicate", "Dup 2",
                                                               property("duplicate", "Dup 3")
                                                       )
                                               )
                                       ),
                                       codedOrOther("z", "Z", "oz", "Other")
                            )
                );

        instance = new TestTree();
    }

    public void testPropertyPathForSingleNode() throws Exception {
        assertEquals("prop", property("prop").getPropertyPath());
    }

    public void testPropertyPathThroughSection() throws Exception {
        TreeNode end;
        property("top", "Top", section(ExpeditedReportSection.BASICS_SECTION, end = property(
                        "bottom", "End")));
        assertEquals("top.bottom", end.getPropertyPath());
    }

    public void testPropertyPathStartingAtSection() throws Exception {
        TreeNode end;
        section(ExpeditedReportSection.BASICS_SECTION, end = property("end", "DC"));
        assertEquals("end", end.getPropertyPath());
    }

    public void testPropertyPathForList() throws Exception {
        TreeNode list = list("coll", "Coll");
        assertEquals("coll[]", list.getPropertyPath());
    }

    public void testPropertyPathWithList() throws Exception {
        TreeNode end;
        property("a", list("c", "Coll", end = property("e")));
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
        assertFindInDeepTree("r.a", "a");
    }

    public void testFindSimpleLeafProperty() {
        assertFindInDeepTree("r.a.a2", "a2");
    }

    public void testFindInternalPropertyInSection() {
        assertFindInDeepTree("r.b", "b");
    }

    public void testFindLeafPropertyInSection() {
        assertFindInDeepTree("r.b.b1", "b1");
    }

    public void testFindInternalPropertyInList() {
        assertFindInDeepTree("r.l[].l7", "l7");
    }

    public void testFindLeafPropertyInList() {
        assertFindInDeepTree("r.l[].l7.l72", "l72");
    }

    public void testFindOtherInCoded() throws Exception {
        assertNode("r.z", "z", null, deepTree.find("r.oz"));
    }

    public void testFindIsRelative() throws Exception {
        TreeNode a = deepTree.getChildren().get(0).getChildren().get(0);
        assertEquals("Test setup failure", "r.a", a.getPropertyPath());
        assertNull(a.find("a.a1"));
        assertNotNull(a.find("a1"));
    }

    public void testFindNodeWithSameNameAsParent() throws Exception {
        assertFindInDeepTree("r.duplicate", "duplicate", "Dup 0");
        assertFindInDeepTree("r.duplicate.duplicate", "duplicate", "Dup 1");
        assertFindInDeepTree("r.duplicate.duplicate.duplicate", "duplicate", "Dup 2");
        assertFindInDeepTree("r.duplicate.duplicate.duplicate.duplicate", "duplicate", "Dup 3");
    }

    private void assertFindInDeepTree(String path, String expectedProperty, String expectedDisplay) {
        assertNode(path, expectedProperty, expectedDisplay, deepTree.find(path));
    }

    private void assertFindInDeepTree(String path, String expectedProperty) {
        assertFindInDeepTree(path, expectedProperty, null);
    }

    private static void assertNode(String expectedPath, String expectedProperty,
                    String expectedDisplay, TreeNode actual) {
        assertNotNull("Node is null", actual);
        assertEquals("Wrong path", expectedPath, actual.getPropertyPath());
        assertEquals("Wrong prop", expectedProperty, actual.getPropertyName());
        if (expectedDisplay != null) {
            assertEquals("Wrong display name", expectedDisplay, actual.getDisplayName());
        }
    }

    public void testFindSimplePropertyFrom() throws Exception {
        assertSinglePropertyValue("Wrong item found", "r.a", instance.getR().getA(), deepTree.find(
                        "r.a").getPropertyValuesFrom(instance));
        assertSinglePropertyValue("Should be no match", "r.a.a2", null, deepTree.find("r.a.a2")
                        .getPropertyValuesFrom(instance));
    }

    public void testFindSimplePropertyFromWithInterveningNull() throws Exception {
        instance.getR().setB(null);
        assertSinglePropertyValue("Should be null match", "r.b", null, deepTree.find("r.b")
                        .getPropertyValuesFrom(instance));
        assertSinglePropertyValue("Should be null match", "r.b.b1", null, deepTree.find("r.b.b1")
                        .getPropertyValuesFrom(instance));
    }

    public void testFindListPropertyFrom() throws Exception {
        PropertyValue[] pvWhenEmpty = deepTree.find("r.l[]").getPropertyValuesFrom(instance)
                        .getPropertyValues();
        assertEquals("Should have no values for empty list: " + Arrays.asList(pvWhenEmpty), 0,
                        pvWhenEmpty.length);
        instance.getR().getL().add(new L());
        instance.getR().getL().add(new L());
        assertEquals("Wrong number of values for populated list", 2, deepTree.find("r.l[]")
                        .getPropertyValuesFrom(instance).getPropertyValues().length);
    }

    public void testFindPropertyOfListPropertyFrom() throws Exception {
        L l0 = new L();
        l0.getL7().setL72("Frob");
        L l2 = new L();
        l2.getL7().setL72("Baz");
        instance.getR().getL().add(l0);
        instance.getR().getL().add(new L());
        instance.getR().getL().add(l2);
        PropertyValue[] actualPVs = deepTree.find("r.l[].l7.l72").getPropertyValuesFrom(instance)
                        .getPropertyValues();
        assertEquals("Wrong number of values for populated list", 3, actualPVs.length);
        assertPropertyValue("Wrong 0th PV", "r.l[0].l7.l72", "Frob", actualPVs[0]);
        assertPropertyValue("Wrong 1st PV", "r.l[1].l7.l72", null, actualPVs[1]);
        assertPropertyValue("Wrong 2nd PV", "r.l[2].l7.l72", "Baz", actualPVs[2]);
    }

    public void testFindCodedPropertyFindsCode() throws Exception {
        instance.getR().setZ(8);

        assertSinglePropertyValue("Coded value not found", "r.z", 8, deepTree.find("r.z")
                        .getPropertyValuesFrom(instance));
    }

    public void testFindCodedPropertyFindsOther() throws Exception {
        instance.getR().setOz("eightq");

        assertSinglePropertyValue("Coded value not found", "r.oz", "eightq", deepTree.find("r.z")
                        .getPropertyValuesFrom(instance));
    }

    public void testFindCodedPropertyPrefersCode() throws Exception {
        instance.getR().setZ(8);
        instance.getR().setOz("eightq");

        assertSinglePropertyValue("Coded value not found", "r.z", 8, deepTree.find("r.z")
                        .getPropertyValuesFrom(instance));
    }

    public void testNullCodedPropertyPrefersCodedPropertyName() throws Exception {
        instance.getR().setZ(null);
        instance.getR().setOz(null);

        assertSinglePropertyValue("Coded value not found", "r.z", null, deepTree.find("r.z")
                        .getPropertyValuesFrom(instance));
    }

    public void testIsAncestorOf() throws Exception {
        TreeNode r = deepTree.find("r");
        TreeNode a = deepTree.find("r.a");
        assertTrue(deepTree.isAncestorOf(r));
        assertTrue(deepTree.isAncestorOf(a));
        assertTrue(r.isAncestorOf(a));
        assertFalse(a.isAncestorOf(r));
        assertFalse(a.isAncestorOf(a));
        assertFalse(r.isAncestorOf(r));
    }

    public void testRecurcivelyCollectListNode(){
        List<TreeNode> listNodes = new ArrayList<TreeNode>();
        deepTree.recursivelyCollectListNodes(listNodes);
        assertEquals(1, listNodes.size());
        assertEquals("l", listNodes.get(0).getPropertyName());
    }

    private void assertSinglePropertyValue(String msg, String expectedName, Object expectedValue,
                    PropertyValues pvs) {
        assertEquals(msg + ": Expected only one PV", 1, pvs.getPropertyValues().length);
        assertPropertyValue(msg, expectedName, expectedValue, pvs.getPropertyValues()[0]);
    }

    private void assertPropertyValue(String msg, String expectedName, Object expectedValue,
                    PropertyValue actualPV) {
        assertEquals(msg + ": bad prop", expectedName, actualPV.getName());
        assertEquals(msg + ": bad value", expectedValue, actualPV.getValue());
    }

    private static class TestTree {
        private R r = new R();

        public R getR() {
            return r;
        }

        public void setR(R r) {
            this.r = r;
        }
    }

    private static class R {
        private A a = new A();

        private B b = new B();

        private List<L> l = new ArrayList<L>();

        private Duplicate duplicate = new Duplicate();

        private Integer z;

        private String oz;

        public A getA() {
            return a;
        }

        public void setA(A a) {
            this.a = a;
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }

        public List<L> getL() {
            return l;
        }

        public void setL(List<L> l) {
            this.l = l;
        }

        public Duplicate getDuplicate() {
            return duplicate;
        }

        public void setDuplicate(Duplicate duplicate) {
            this.duplicate = duplicate;
        }

        public Integer getZ() {
            return z;
        }

        public void setZ(Integer z) {
            this.z = z;
        }

        public String getOz() {
            return oz;
        }

        public void setOz(String oz) {
            this.oz = oz;
        }
    }

    private static class A {
        private String a1, a2;

        public String getA1() {
            return a1;
        }

        public void setA1(String a1) {
            this.a1 = a1;
        }

        public String getA2() {
            return a2;
        }

        public void setA2(String a2) {
            this.a2 = a2;
        }
    }

    private static class B {
        private String b1, b2;

        public String getB1() {
            return b1;
        }

        public void setB1(String b1) {
            this.b1 = b1;
        }

        public String getB2() {
            return b2;
        }

        public void setB2(String b2) {
            this.b2 = b2;
        }
    }

    private static class L {
        private L7 l7 = new L7();

        private String l9;

        public L7 getL7() {
            return l7;
        }

        public void setL7(L7 l7) {
            this.l7 = l7;
        }

        public String getL9() {
            return l9;
        }

        public void setL9(String l9) {
            this.l9 = l9;
        }
    }

    private static class L7 {
        private String l71, l72, l73;

        public String getL71() {
            return l71;
        }

        public void setL71(String l71) {
            this.l71 = l71;
        }

        public String getL72() {
            return l72;
        }

        public void setL72(String l72) {
            this.l72 = l72;
        }

        public String getL73() {
            return l73;
        }

        public void setL73(String l73) {
            this.l73 = l73;
        }
    }

    private static class Duplicate {
        private Duplicate duplicate;

        public Duplicate getDuplicate() {
            return duplicate;
        }

        public void setDuplicate(Duplicate duplicate) {
            this.duplicate = duplicate;
        }
    }
}
