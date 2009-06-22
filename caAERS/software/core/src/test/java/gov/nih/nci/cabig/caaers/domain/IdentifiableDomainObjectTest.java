package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
// Maven skips test classes named "Abstract" (lame)
public class IdentifiableDomainObjectTest extends CaaersTestCase {
    private static final String MRN = "12442";

    private static final String DRIVERS_LICENSE = "42001242";

    private static final String SSN = "124-42-4200";

    private TestIdentifiable identifiable;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        identifiable = new TestIdentifiable();
        identifiable.addIdentifier(Identifier.createTemplate("MRN", MRN));
        identifiable.getIdentifiers().get(0).setPrimaryIndicator(true);
        identifiable.addIdentifier(Identifier.createTemplate("SSN", SSN));
        identifiable.addIdentifier(Identifier.createTemplate("DLN", DRIVERS_LICENSE));
    }

    public void testGetPrimary() throws Exception {
        assertEquals(MRN, identifiable.getPrimaryIdentifier().getValue());
    }

    public void testGetPrimaryWithNone() throws Exception {
        identifiable.getIdentifiers().get(0).setPrimaryIndicator(false);
        // TODO: we might want this to throw an exception in the future
        assertNull(identifiable.getPrimaryIdentifier());
    }

    public void testGetPrimaryWithNullIndicator() throws Exception {
        identifiable.getIdentifiers().get(0).setPrimaryIndicator(null);
        // TODO: we might want this to throw an exception in the future
        assertNull(identifiable.getPrimaryIdentifier());
    }

    public void testGetSecondary() throws Exception {
        List<Identifier> actual = identifiable.getSecondaryIdentifiers();
        assertEquals("Wrong number of secondaries", 2, actual.size());
        assertEquals("Wrong secondary 0", SSN, actual.get(0).getValue());
        assertEquals("Wrong secondary 1", DRIVERS_LICENSE, actual.get(1).getValue());
    }

    public void testGetSecondaryWhenNoPrimary() throws Exception {
        identifiable.getIdentifiers().get(0).setPrimaryIndicator(false);
        List<Identifier> actual = identifiable.getSecondaryIdentifiers();
        assertEquals("Wrong number of secondaries", 3, actual.size());
    }

    public void testGetPrimaryWhenNoIdentifiers() throws Exception {
        identifiable.getIdentifiers().clear();
        assertNull(identifiable.getPrimaryIdentifier());
    }

    public void testGetSecondaryWhenNoIdentifiers() throws Exception {
        identifiable.getIdentifiers().clear();
        assertEquals(0, identifiable.getSecondaryIdentifiers().size());
    }

    private static class TestIdentifiable extends AbstractIdentifiableDomainObject {
    }
}
