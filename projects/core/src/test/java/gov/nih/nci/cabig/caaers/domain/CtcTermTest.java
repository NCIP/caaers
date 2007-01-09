package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class CtcTermTest extends CaaersTestCase {
    private CtcTerm term = new CtcTerm();

    public void testFullNameWithoutSelect() throws Exception {
        term.setTerm("This is the long term name");
        assertEquals("This is the long term name", term.getFullName());
    }

    public void testFullNameWithSelect() throws Exception {
        term.setTerm("This is the long term name");
        term.setSelect("exactly");
        assertEquals("This is the long term name - exactly", term.getFullName());
    }
}
