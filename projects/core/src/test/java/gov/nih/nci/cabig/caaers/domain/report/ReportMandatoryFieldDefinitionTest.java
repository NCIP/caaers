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
        def = new ReportMandatoryFieldDefinition();
    }

    public void testIsMandatoryWithNullMandatoryField() throws Exception {
        def.setMandatory(null);
        assertFalse(def.getMandatory());
    }

    public void testIsMandatoryWithFalseMandatoryField() throws Exception {
        def.setMandatory(false);
        assertFalse(def.getMandatory());
    }

    public void testIsMandatoryWithTrueMandatoryField() throws Exception {
        def.setMandatory(true);
        assertTrue(def.getMandatory());
    }
}
