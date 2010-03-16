package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class ReportMandatoryFieldDefinitionTest extends CaaersTestCase {
    private ReportMandatoryFieldDefinition def;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        def = new ReportMandatoryFieldDefinition("", RequirednessIndicator.OPTIONAL);
        def.setRuleBindURL("some url");
        def.setRuleName("some rule");
    }

    public void testIsMandatoryWithNullMandatoryField() throws Exception {
        def.setMandatory(null);
        assertNull(def.getMandatory());
    }

    public void testIsMandatoryWithFalseMandatoryField() throws Exception {
        def.setMandatory(RequirednessIndicator.OPTIONAL);
        assertFalse(def.getMandatory().equals(Mandatory.MANDATORY));
    }

    public void testIsMandatoryWithTrueMandatoryField() throws Exception {
        def.setMandatory(RequirednessIndicator.MANDATORY);
        assertTrue(def.getMandatory().equals(Mandatory.MANDATORY));
    }

    public void testAssertCorrectPropertiesReturned(){
        assertEquals("some rule", def.getRuleName());
        assertEquals("some url", def.getRuleBindURL());
    }
    
}
