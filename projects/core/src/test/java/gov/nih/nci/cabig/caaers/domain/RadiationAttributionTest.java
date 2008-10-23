package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;

/**
 * @author Biju Joseph
 */
public class RadiationAttributionTest extends AbstractNoSecurityTestCase {

    private RadiationAttribution deviceAttribution;
    private AdverseEvent adverseEvent;
    private RadiationIntervention cause;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        adverseEvent.setId(1);
        cause = new RadiationIntervention();
        deviceAttribution = new RadiationAttribution();


        deviceAttribution.setId(1);
        deviceAttribution.setGridId("grid id");
        deviceAttribution.setCause(cause);
        deviceAttribution.setVersion(2);
        deviceAttribution.setAttribution(Attribution.POSSIBLE);
        deviceAttribution.setAdverseEvent(adverseEvent);
    }

//    public void testCopy() {
//        RadiationAttribution copiedRadiationAttribution = deviceAttribution.copy();
//        assertNull("id must be null", copiedRadiationAttribution.getId());
//        assertNull("grid id must be null", copiedRadiationAttribution.getGridId());
//        assertNull("version number must be null", copiedRadiationAttribution.getVersion());
//
//        assertSame("medical cause must refer to same object", cause, copiedRadiationAttribution.getCause());
//        assertEquals("attribution must be same", Attribution.POSSIBLE, copiedRadiationAttribution.getAttribution());
//        assertNotNull(deviceAttribution.getAdverseEvent());
//        assertNull("must not copy adverse event", copiedRadiationAttribution.getAdverseEvent());
//
//    	assertTrue(true);
//    }
    
    public void testAllTestCommented(){
    	assertTrue(true);
    }
}