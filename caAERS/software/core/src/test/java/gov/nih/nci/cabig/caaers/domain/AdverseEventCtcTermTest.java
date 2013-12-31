/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventCtcTermTest extends AbstractNoSecurityTestCase {
    private AdverseEventCtcTerm term;

    private AdverseEvent adverseEvent;

    private CtcTerm coagOther;

    private CtcTerm cd4;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        adverseEvent = new AdverseEvent();
        term = new AdverseEventCtcTerm();
        term.setAdverseEvent(adverseEvent);

        coagOther = new CtcTerm();
        coagOther.setTerm("Coagulation - Other (Specify, __)");
        coagOther.setOtherRequired(true);

        cd4 = new CtcTerm();
        cd4.setTerm("CD4 count");
    }

//    public void testCopy() throws Exception {
//        term.setCtcTerm(cd4);
//        term.setId(1);
//        term.setGridId("grid id");
//        term.setVersion(2);
//        AdverseEventCtcTerm adverseEventCtcTerm = term.copy();
//        assertNull(adverseEventCtcTerm.getId());
//        assertNull(adverseEventCtcTerm.getGridId());
//        assertNull(adverseEventCtcTerm.getVersion());
//
//        assertNull("must not copy adverse event", adverseEventCtcTerm.getAdverseEvent());
//        assertSame("term must refer to same object", cd4, adverseEventCtcTerm.getTerm());
//    }

    public void testUniversalTermForSpecificTerm() throws Exception {
        term.setCtcTerm(cd4);
        assertEquals(cd4.getTerm(), term.getUniversalTerm());
    }

    public void testUniversalTermForOtherReqTermWithOtherDetails() throws Exception {
        adverseEvent.setDetailsForOther("Arbit");
        term.setCtcTerm(coagOther);
        String universalTerm = term.getUniversalTerm();
        assertEquals("Coagulation - Other: Arbit", universalTerm);
    }

    public void testUniversalTermForOtherReqTermWithoutOtherDetails() throws Exception {
        adverseEvent.setDetailsForOther(null);
        term.setCtcTerm(coagOther);
        assertEquals("Coagulation - Other (Specify, __)", term.getUniversalTerm());
    }

    public void testUniversalTermWithNoTerm() throws Exception {
        term.setCtcTerm(null);
        assertNull(term.getUniversalTerm());
    }
    
    public void testUniversalTermWithDetailsForOtherAndLowLevelTerm() throws Exception {
    	CtcTerm ctcterm = new CtcTerm();
    	ctcterm.setTerm("headache");
    	ctcterm.setSelect("select");
        term.setCtcTerm(ctcterm);
        term.getAdverseEvent().setDetailsForOther("other deails");
        assertFalse(term.getUniversalTerm().contains("null"));
    }
}
