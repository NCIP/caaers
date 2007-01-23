/**
 * 
 */
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Site;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class SiteDaoTest extends DaoTestCase<SiteDao> {

    public void testGet() throws Exception {
        Site loaded = getDao().getById(-1001);
        assertNotNull("Site not found", loaded);
        assertEquals("New Site", loaded.getName());
    }
    public void testGetByGridId() throws Exception {
        Site loaded = getDao().getByGridId("gridSite");
        assertNotNull("Site not found", loaded);
        assertEquals("New Site", loaded.getName());
    }

}
