package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class HospitalizationTest extends CaaersTestCase {
    public void testToString() throws Exception {
        assertEquals("0: None", Hospitalization.NONE.toString());
        assertEquals("2: Prolonged hospitalization", Hospitalization.PROLONGED_HOSPITALIZATION.toString());
        assertEquals("1: Hospitalization", Hospitalization.HOSPITALIZATION.toString());
    }

    public void testFromCode() throws Exception {
        assertEquals(Hospitalization.NONE, Hospitalization.getByCode(0));
        assertEquals(Hospitalization.HOSPITALIZATION, Hospitalization.getByCode(1));
        assertEquals(Hospitalization.PROLONGED_HOSPITALIZATION, Hospitalization.getByCode(2));
    }
}
