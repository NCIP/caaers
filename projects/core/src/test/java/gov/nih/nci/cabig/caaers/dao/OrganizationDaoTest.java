/**
 * 
 */
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.List;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class OrganizationDaoTest extends DaoTestCase<OrganizationDao> {

    public void testGet() throws Exception {
        Organization loaded = getDao().getById(-1001);
        assertNotNull("Organization not found", loaded);
        assertEquals("New Site", loaded.getName());
    }
    public void testGetByGridId() throws Exception {
        Organization loaded = getDao().getByGridId("gridSite");
        assertNotNull("Organization not found", loaded);
        assertEquals("New Site", loaded.getName());
    }
    public void testGetByName() throws Exception {
        Organization loaded = getDao().getByName("New Site");
        assertNotNull("Organization not found", loaded);
        assertEquals("New Site", loaded.getName());
    }
    public void testGetAll() throws Exception {
        List<Organization> orgs = getDao().getAll();
        assertEquals(2, orgs.size());
    }

}
