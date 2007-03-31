package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class LabsTabTest extends AeTabTestCase {
    private LabsTab tab;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tab = new LabsTab();
    }

    public void testDisplayNames() throws Exception {
        assertEquals("Lab A", displayNameForGroup(tab, "lab0"));
        assertEquals("Lab G", displayNameForGroup(tab, "lab6"));
    }
    
    public void testFieldProperties() throws Exception {
        InputFieldGroup group3 = tab.createFieldGroups(command).get("lab3");
        List<InputField> actualFields = group3.getFields();
        assertEquals("aeReport.labs[3].name", actualFields.get(0).getPropertyName());
        assertEquals("aeReport.labs[3].units", actualFields.get(1).getPropertyName());
        assertEquals("aeReport.labs[3].baseline.value", actualFields.get(2).getPropertyName());
        assertEquals("aeReport.labs[3].baseline.date", actualFields.get(3).getPropertyName());
        assertEquals("aeReport.labs[3].nadir.value", actualFields.get(4).getPropertyName());
        assertEquals("aeReport.labs[3].nadir.date", actualFields.get(5).getPropertyName());
        assertEquals("aeReport.labs[3].recovery.value", actualFields.get(6).getPropertyName());
        assertEquals("aeReport.labs[3].recovery.date", actualFields.get(7).getPropertyName());
        assertEquals("Wrong number of fields", 8, actualFields.size());
    }

    private String displayNameForGroup(AeTab t, String key) {
        return t.createFieldGroups(command).get(key).getDisplayName();
    }
}
