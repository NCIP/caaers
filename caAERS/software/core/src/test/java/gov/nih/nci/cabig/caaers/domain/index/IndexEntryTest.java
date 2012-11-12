package gov.nih.nci.cabig.caaers.domain.index;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class IndexEntryTest extends TestCase {
    public void testHasRoles() throws Exception {
        IndexEntry e = new IndexEntry(0);
        assertFalse(e.hasRoles());
        e.addRole(UserGroupType.ae_reporter);
        assertTrue(e.hasRoles());
    }
}
