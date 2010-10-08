package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
/**
 *
 * @author Ion C. Olaru
 *
 */
public class DeviceTest extends TestCase {
	Device device;

    protected void setUp() throws Exception {
		super.setUp();
		device = new Device();
        device.setBrandName("Init brand name");
        device.setCommonName("Init common name");
        device.setType("Init type");
	}

    public void testEqualsAll() {
        Device other = new Device();
        other.setBrandName("Init brand name");
        other.setCommonName("Init common name");
        other.setType("Init type");

        assertTrue(device.equals(other));
        assertTrue(device.hashCode() == other.hashCode());
    }

    public void testBrandNameDiff() {
        Device other = new Device();
        other.setBrandName("Other brand name");
        other.setCommonName("Init common name");
        other.setType("Init type");

        assertFalse(device.equals(other));
        assertFalse(device.hashCode() == other.hashCode());
    }

    public void testCommonNameDiff() {
        Device other = new Device();
        other.setBrandName("Init brand name");
        other.setCommonName("Other common name");
        other.setType("Init type");

        assertFalse(device.equals(other));
        assertFalse(device.hashCode() == other.hashCode());
    }
}
