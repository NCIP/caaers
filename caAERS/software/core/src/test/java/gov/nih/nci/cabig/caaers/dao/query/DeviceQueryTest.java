package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 *@author Biju Joseph
 */
public class DeviceQueryTest extends TestCase {
    
    public void testFilterByCtepDbIdentifier() throws Exception {
        DeviceQuery dq = new DeviceQuery();
        dq.filterByCtepDbIdentifier("134");
        assertEquals("SELECT d FROM Device d WHERE d.ctepDbIdentifier = :dbId", dq.getQueryString());
    }
}
