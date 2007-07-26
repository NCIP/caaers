package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import junit.framework.TestCase;
import static gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode.*;

/**
 * @author Rhett Sutphin
 */
public class TreeNodeTest extends TestCase {
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
        TreeNode list = list("coll");
        assertEquals("coll[]", list.getPropertyPath());
    }

    public void testPropertyPathWithList() throws Exception {
        TreeNode end;
        property("a",
            list("c",
                end = property("e")
            )
        );
        assertEquals("a.c[].e", end.getPropertyPath());
    }
}
