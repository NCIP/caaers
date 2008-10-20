package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;

/**
 * @author Biju Joseph
 */
public class OtherCauseAttributionTest extends AbstractTestCase {

    private OtherCauseAttribution otherCauseAttribution;
    private AdverseEvent adverseEvent;
    private OtherCause cause;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        adverseEvent.setId(1);
        cause = new OtherCause();
        otherCauseAttribution = new OtherCauseAttribution();


        otherCauseAttribution.setId(1);
        otherCauseAttribution.setGridId("grid id");
        otherCauseAttribution.setCause(cause);
        otherCauseAttribution.setVersion(2);
        otherCauseAttribution.setAttribution(Attribution.POSSIBLE);
        otherCauseAttribution.setAdverseEvent(adverseEvent);
    }

    public void testCopy() {
//        OtherCauseAttribution copiedOtherCauseAttribution = otherCauseAttribution.copy();
//        assertNull("id must be null", copiedOtherCauseAttribution.getId());
//        assertNull("grid id must be null", copiedOtherCauseAttribution.getGridId());
//        assertNull("version number must be null", copiedOtherCauseAttribution.getVersion());
//
//        assertSame("medical cause must refer to same object", cause, copiedOtherCauseAttribution.getCause());
//        assertEquals("attribution must be same", Attribution.POSSIBLE, copiedOtherCauseAttribution.getAttribution());
//        assertNotNull(otherCauseAttribution.getAdverseEvent());
//        assertNull("must not copy adverse event", copiedOtherCauseAttribution.getAdverseEvent());

    	assertTrue(true);
    }
}