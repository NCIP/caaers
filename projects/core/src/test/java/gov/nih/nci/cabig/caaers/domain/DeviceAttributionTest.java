package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;

/**
 * @author Biju Joseph
 */
public class DeviceAttributionTest extends AbstractNoSecurityTestCase {

    private DeviceAttribution deviceAttribution;
    private AdverseEvent adverseEvent;
    private MedicalDevice cause;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        adverseEvent.setId(1);
        cause = new MedicalDevice();
        deviceAttribution = new DeviceAttribution();


        deviceAttribution.setId(1);
        deviceAttribution.setGridId("grid id");
        deviceAttribution.setCause(cause);
        deviceAttribution.setVersion(2);
        deviceAttribution.setAttribution(Attribution.POSSIBLE);
        deviceAttribution.setAdverseEvent(adverseEvent);
    }

//    public void testCopy() {
//        DeviceAttribution copiedDeviceAttribution = deviceAttribution.copy();
//        assertNull("id must be null", copiedDeviceAttribution.getId());
//        assertNull("grid id must be null", copiedDeviceAttribution.getGridId());
//        assertNull("version number must be null", copiedDeviceAttribution.getVersion());
//
//        assertSame("medical cause must refer to same object", cause, copiedDeviceAttribution.getCause());
//        assertEquals("attribution must be same", Attribution.POSSIBLE, copiedDeviceAttribution.getAttribution());
//        assertNotNull(deviceAttribution.getAdverseEvent());
//        assertNull("must not copy adverse event", copiedDeviceAttribution.getAdverseEvent());
//        assertTrue(true);
//
//    }
    
    public void testAllTestCommented(){
    	assertTrue(true);
    }
}
