/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Device;

/**
 * @author Ion C. Olaru
 *         Date: 4/18/12 -8:49 PM
 */
public class DeviceMigratorTest extends AbstractTestCase {

    private DeviceMigrator migrator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        migrator = new DeviceMigrator();
    }

    public void testMigrateOneDevice() {
        Device srcDevice = new Device();
        Device destDevice = new Device();

        assertNull(destDevice.getCommonName());
        assertNull(destDevice.getType());
        assertNull(destDevice.getBrandName());
        assertEquals("", destDevice.getDisplayName());

        srcDevice.setCommonName("C");
        srcDevice.setType("T");
        srcDevice.setBrandName("B");

        migrator.migrate(srcDevice, destDevice, null);

        assertEquals("C", destDevice.getCommonName());
        assertEquals("T", destDevice.getType());
        assertEquals("B", destDevice.getBrandName());
    }

}
