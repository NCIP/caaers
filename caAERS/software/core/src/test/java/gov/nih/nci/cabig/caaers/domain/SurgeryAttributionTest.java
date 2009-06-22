package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;

/**
 * @author Biju Joseph
 */
public class SurgeryAttributionTest extends AbstractNoSecurityTestCase {

    private SurgeryAttribution surgeryAttribution;
    private AdverseEvent adverseEvent;
    private SurgeryIntervention cause;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        adverseEvent.setId(1);
        cause = new SurgeryIntervention();
        surgeryAttribution = new SurgeryAttribution();


        surgeryAttribution.setId(1);
        surgeryAttribution.setGridId("grid id");
        surgeryAttribution.setCause(cause);
        surgeryAttribution.setVersion(2);
        surgeryAttribution.setAttribution(Attribution.POSSIBLE);
        surgeryAttribution.setAdverseEvent(adverseEvent);
    }

//    public void testCopy() {
//        SurgeryAttribution copiedSurgeryAttribution = surgeryAttribution.copy();
//        assertNull("id must be null", copiedSurgeryAttribution.getId());
//        assertNull("grid id must be null", copiedSurgeryAttribution.getGridId());
//        assertNull("version number must be null", copiedSurgeryAttribution.getVersion());
//
//        assertSame("medical cause must refer to same object", cause, copiedSurgeryAttribution.getCause());
//        assertEquals("attribution must be same", Attribution.POSSIBLE, copiedSurgeryAttribution.getAttribution());
//        assertNotNull(surgeryAttribution.getAdverseEvent());
//        assertNull("must not copy adverse event", copiedSurgeryAttribution.getAdverseEvent());
//    	assertTrue(true);
//
//    }
    
    public void testAllTestCommented(){
    	assertTrue(true);
    }
}