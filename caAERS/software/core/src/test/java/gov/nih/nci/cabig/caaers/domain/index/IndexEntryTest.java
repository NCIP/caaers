/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    public void testEquals(){
        assertTrue(new IndexEntry(0).equals(new IndexEntry(0)));
        assertFalse(new IndexEntry(0).equals(new IndexEntry(10)));

        IndexEntry i1 = new IndexEntry(0) ;
        i1.addRole(UserGroupType.ae_reporter, UserGroupType.ae_expedited_report_reviewer);

        IndexEntry i2 = new IndexEntry(0) ;
        i2.addRole(UserGroupType.ae_expedited_report_reviewer,UserGroupType.ae_reporter);


        IndexEntry i3 = new IndexEntry(10) ;
        i3.addRole(UserGroupType.ae_reporter, UserGroupType.ae_expedited_report_reviewer, UserGroupType.ae_rule_and_report_manager);


        IndexEntry i4 = new IndexEntry(0) ;
        i4.addRole(UserGroupType.ae_reporter, UserGroupType.ae_expedited_report_reviewer, UserGroupType.ae_rule_and_report_manager);

        assertTrue(i1.equals(i2));
        assertTrue(i2.equals(i1));
        assertFalse(i2.equals(i3));
        assertFalse(i2.equals(i4));
        assertFalse(i3.equals(i4));
    }
}
