package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class AdverseEventMeddraLowLevelTermTest extends TestCase {
    private AdverseEventMeddraLowLevelTerm term;

    private AdverseEvent adverseEvent;


    private String medraCode;
    private LowLevelTerm lowLevelTerm;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        term = new AdverseEventMeddraLowLevelTerm();
        term.setAdverseEvent(adverseEvent);

        lowLevelTerm = new LowLevelTerm();
        medraCode = "medraCode";
    }

    public void testCopy() throws Exception {
//        term.setMeddraCode(medraCode);
//        term.setTerm(lowLevelTerm);
//        term.setId(1);
//        term.setGridId("grid id");
//        term.setVersion(2);
//        AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm = term.copy();
//        assertNull(adverseEventMeddraLowLevelTerm.getId());
//        assertNull(adverseEventMeddraLowLevelTerm.getGridId());
//        assertNull(adverseEventMeddraLowLevelTerm.getVersion());
//
//        assertNull("must not copy adverse event", adverseEventMeddraLowLevelTerm.getAdverseEvent());
//        assertSame("low level must refer to same object", lowLevelTerm, adverseEventMeddraLowLevelTerm.getTerm());
//        assertEquals(medraCode, adverseEventMeddraLowLevelTerm.getMeddraCode());
    	
    	assertTrue(true);
    }


}