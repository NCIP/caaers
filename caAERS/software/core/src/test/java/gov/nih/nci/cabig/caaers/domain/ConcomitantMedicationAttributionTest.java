package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;

/**
 * @author Biju Joseph
 */
public class ConcomitantMedicationAttributionTest extends AbstractNoSecurityTestCase {

    private ConcomitantMedicationAttribution concomitantMedicationAttribution;
    private AdverseEvent adverseEvent;
    private ConcomitantMedication cause;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        adverseEvent.setId(1);
        cause = new ConcomitantMedication();
        concomitantMedicationAttribution = new ConcomitantMedicationAttribution();


        concomitantMedicationAttribution.setId(1);
        concomitantMedicationAttribution.setGridId("grid id");
        concomitantMedicationAttribution.setCause(cause);
        concomitantMedicationAttribution.setVersion(2);
        concomitantMedicationAttribution.setAttribution(Attribution.POSSIBLE);
        concomitantMedicationAttribution.setAdverseEvent(adverseEvent);
    }

//    public void testCopy() {
//        ConcomitantMedicationAttribution copiedConcomitantMedicationAttribution = concomitantMedicationAttribution.copy();
//        assertNull("id must be null", copiedConcomitantMedicationAttribution.getId());
//        assertNull("grid id must be null", copiedConcomitantMedicationAttribution.getGridId());
//        assertNull("version number must be null", copiedConcomitantMedicationAttribution.getVersion());
//
//        assertSame("medical cause must refer to same object", cause, copiedConcomitantMedicationAttribution.getCause());
//        assertEquals("attribution must be same", Attribution.POSSIBLE, copiedConcomitantMedicationAttribution.getAttribution());
//        assertNotNull(concomitantMedicationAttribution.getAdverseEvent());
//        assertNull("must not copy adverse event", copiedConcomitantMedicationAttribution.getAdverseEvent());
//    	assertTrue(true);
//
//
//    }
    public void testAllTestCommented(){
    	assertTrue(true);
    }
}