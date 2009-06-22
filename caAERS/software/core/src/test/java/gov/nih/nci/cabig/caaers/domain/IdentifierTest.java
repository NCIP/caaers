package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */

public class IdentifierTest extends CaaersTestCase {
    private Identifier identifier;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        identifier = new Identifier();
    }

    public void testIsPrimaryWhenIndicatorNull() throws Exception {
        identifier.setPrimaryIndicator(null);
        assertFalse(identifier.isPrimary());
    }

    public void testIsPrimaryWhenIndicatorFalse() throws Exception {
        identifier.setPrimaryIndicator(false);
        assertFalse(identifier.isPrimary());
    }

    public void testIsPrimaryWhenIndicatorTrue() throws Exception {
        identifier.setPrimaryIndicator(true);
        assertTrue(identifier.isPrimary());
    }
}
