/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import edu.internet2.middleware.grouper.GroupNotFoundException;
import edu.internet2.middleware.grouper.StemNotFoundException;
import edu.internet2.middleware.subject.Subject;
import gov.nih.nci.cagrid.gridgrouper.grouper.GroupI;
import gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI;
import gov.nih.nci.cagrid.gridgrouper.grouper.StemI;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class MockGridGrouperClient implements GrouperI {

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI#findGroup(java.lang.String)
     */
    public GroupI findGroup(String name) throws GroupNotFoundException {
        if (true) throw new UnsupportedOperationException();
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI#findStem(java.lang.String)
     */
    public StemI findStem(String name) throws StemNotFoundException {
        if (true) throw new UnsupportedOperationException();
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI#getName()
     */
    public String getName() {
        if (true) throw new UnsupportedOperationException();
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI#getRootStem()
     */
    public StemI getRootStem() throws StemNotFoundException {
        if (true) throw new UnsupportedOperationException();
        return null;
    }

    /*
     * Always returns true
     * 
     * @see gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI#isMemberOf(java.lang.String,
     *      java.lang.String)
     */
    public boolean isMemberOf(String subjectId, String groupName) throws GroupNotFoundException {
        return true;
    }

    /*
     * Always returns true
     * 
     * @see gov.nih.nci.cagrid.gridgrouper.grouper.GrouperI#isMemberOf(edu.internet2.middleware.subject.Subject,
     *      java.lang.String)
     */
    public boolean isMemberOf(Subject subject, String groupName) throws GroupNotFoundException {
        return true;
    }

}
