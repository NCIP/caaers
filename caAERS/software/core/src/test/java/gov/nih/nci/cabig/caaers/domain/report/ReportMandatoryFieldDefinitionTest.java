package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class ReportMandatoryFieldDefinitionTest extends CaaersTestCase {
    private ReportMandatoryFieldDefinition def;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        def = new ReportMandatoryFieldDefinition("", Mandatory.OPTIONAL);
    }

    public void testIsMandatoryWithNullMandatoryField() throws Exception {
        def.setMandatory(null);
        assertNull(def.getMandatory());
    }

    public void testIsMandatoryWithFalseMandatoryField() throws Exception {
        def.setMandatory(Mandatory.OPTIONAL);
        assertFalse(def.getMandatory().equals(Mandatory.MANDATORY));
    }

    public void testIsMandatoryWithTrueMandatoryField() throws Exception {
        def.setMandatory(Mandatory.MANDATORY);
        assertTrue(def.getMandatory().equals(Mandatory.MANDATORY));
    }
}
