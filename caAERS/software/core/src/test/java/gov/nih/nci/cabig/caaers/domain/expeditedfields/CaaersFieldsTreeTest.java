package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersContextLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import junit.framework.TestCase;
import org.easymock.classextension.EasyMock;

/**
 * This class tests CaaersFieldTree class.
 * @author Sameer Sawant
 * @author Ion C. Olaru
 * @author Biju Joseph
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

    public void testSections() {
        assertEquals(2, tree.getChildren().size());
    }
    
    //checks whether the call is properly getting delegated to expedited tree. 
    public void testInitialize(){
        ExpeditedReportTree reportTree = registerMockFor(ExpeditedReportTree.class);
        tree.setExpeditedReportTree(reportTree);

        reportTree.initialize();
        
        replayMocks();
        tree.initialize();
        verifyMocks();
    }

    //checks that if expedited report tree is null, the initialize will not throw NPE.
    public void testInitializeWillNotThrowNPE(){
        try{
            tree.setExpeditedReportTree(null);
            tree.initialize();
        }catch(NullPointerException npe){
            fail("Must not throw null pointer exception, when ExpeditedReportTree is null");
        }
    }

}
