package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.math.BigDecimal;

/**
 * @author Rhett Sutphin
 */

public class DoseTest extends CaaersTestCase {
    private Dose dose;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dose = new Dose();
    }

    public void testDisplayNameWithAllFields() throws Exception {
        dose.setAmount("2373.4");
        dose.setUnits("kg");
        dose.setRoute("aural");

        assertEquals("2373.4kg aural", dose.getDisplayName());
    }

    public void testDisplayNameWithoutRoute() throws Exception {
        dose.setAmount("2373.4");
        dose.setUnits("kg");
        dose.setRoute(null);

        assertEquals("2373.4kg", dose.getDisplayName());
    }

    public void testDisplayNameAmountOnly() throws Exception {
        dose.setAmount("2373.4");

        assertEquals("2373.4", dose.getDisplayName());
    }
}
