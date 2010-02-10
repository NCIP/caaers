package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersContextLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import junit.framework.TestCase;

/**
 * This class tests CaaersFieldTree class.
 * @author Sameer Sawant
 * @author Ion C. Olaru
 */
public class CaaersFieldsTreeTest extends CaaersTestCase {
	private CaaersFieldsTree tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tree = (CaaersFieldsTree)getDeployedApplicationContext().getBean("caaersFieldsTree");
    }

    public void testRecurcivelyCollectListNodes(){
        List<TreeNode> nodes = new ArrayList();
        tree.recursivelyCollectListNodes(nodes);
        assertEquals("adverseEvents", nodes.get(0).getPropertyName());
    }

}
