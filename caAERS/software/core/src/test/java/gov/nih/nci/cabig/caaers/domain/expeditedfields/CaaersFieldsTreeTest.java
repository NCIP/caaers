package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersContextLoader;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * This class tests CaaersFieldTree class.
 * @author Sameer Sawant
 */
public class CaaersFieldsTreeTest extends TestCase{
	private ExpeditedReportTree expeditedTree = new ExpeditedReportTree();
	private CaaersFieldsTree tree = new CaaersFieldsTree();
	
	public void testRecurcivelyCollectListNodes(){
        List<TreeNode> nodes = new ArrayList();
        tree.recursivelyCollectListNodes(nodes);
        assertEquals("adverseEvents", nodes.get(0).getPropertyName());
    }
}
