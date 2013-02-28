/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 *@author Biju Joseph
 *@author Ion C. Olaru
 */
public class DeviceQueryTest extends TestCase {
    
    public void testFilterByCtepDbIdentifier() throws Exception {
        DeviceQuery dq = new DeviceQuery();
        dq.filterByCtepDbIdentifier("134");
        assertEquals("SELECT d FROM Device d WHERE d.ctepDbIdentifier = :dbId", dq.getQueryString());
    }

    public void testFilterByCommonNameAndBrandName() throws Exception {
        DeviceQuery dq = new DeviceQuery();
        dq.filterByCommonName("abc");
        dq.filterByBrandName("xyz");
        assertEquals("SELECT d FROM Device d WHERE lower(d.commonName) = :common_name AND lower(d.brandName) = :brand_name", dq.getQueryString());
    }
}
