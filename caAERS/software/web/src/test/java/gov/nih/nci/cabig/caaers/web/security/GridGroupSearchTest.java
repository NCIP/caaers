/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.grid.authorization.CSMGridGroupSearch;
import gov.nih.nci.security.acegi.grid.authorization.GridGrouperClientFactory;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class GridGroupSearchTest extends TestCase {

    public GridGroupSearchTest() {

    }

    public GridGroupSearchTest(String name) {
        super(name);
    }

    public void testGroupSearch() {

        UserProvisioningManager mgr = new MockUserProvisioningManagerAdapter() {
            public List getObjects(SearchCriteria sc) {
                List objects = new ArrayList();
                Group group = new Group();
                group.setGroupName("{http://some.host/SomeGridGrouper}someStem:someGroup");
                objects.add(group);
                return objects;
            }
        };
        GridGrouperClientFactory fact = new GridGrouperClientFactory() {
            public GrouperI newGridGrouperClient(String url) {
                return new MockGridGrouperClient();
            }

        };
        CSMGridGroupSearch search = new CSMGridGroupSearch();
        search.setCsmApplicationContextName("ignored");
        search.setUserProvisioningManager(mgr);
        search.setGridGrouperClientFactory(fact);
        List groupNames = search.getGridGroupNames("ignored");
        assertTrue(groupNames.size() == 1);
    }
}
