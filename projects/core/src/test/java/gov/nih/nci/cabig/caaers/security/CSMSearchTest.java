/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.GroupSearchCriteria;

import java.util.Iterator;
import java.util.List;

import org.dbunit.operation.DatabaseOperation;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class CSMSearchTest extends CaaersDbTestCase {

    public String getTestDataFileName() {
        return "testdata/CSMSearchTest.xml";
    }

    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.INSERT;
    }

    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE;
    }

    public void testGridGroupSearch() {

        String gridGroupNamePattern = "^\\{http(s?)\\:\\/\\/.*\\}.+";
        UserProvisioningManager mgr = (UserProvisioningManager) getApplicationContext().getBean(
                        "csmUserProvisioningManager");
        Application appProt = new Application();
        appProt.setApplicationName((String) getApplicationContext().getBean(
                        "csmApplicationContextName"));
        Group grpProt = new Group();
        grpProt.setApplication(appProt);
        GroupSearchCriteria sc = new GroupSearchCriteria(grpProt);
        List groups = mgr.getObjects(sc);

        int expectedNumGridGroups = 1;
        int numGridGroups = 0;
        for (Iterator i = groups.iterator(); i.hasNext();) {
            Group group = (Group) i.next();
            String groupName = group.getGroupName();
            if (groupName != null && groupName.matches(gridGroupNamePattern)) {
                numGridGroups++;
            }
        }

        assertTrue("Expected " + expectedNumGridGroups + " grid groups. Found " + numGridGroups,
                        expectedNumGridGroups == numGridGroups);
    }

}
