package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
public class BasePropertyInputFieldGroupTest extends CaaersTestCase {
    private BasePropertyInputFieldGroup group;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        group = new BasePropertyInputFieldGroup("test", "base.prop");
    }

    public void testAddedFieldsWrapped() throws Exception {
        group.addField(new DefaultTextField("name", "Name"));
        assertEquals("Wrong number of fields added", 1, group.getFields().size());
        assertEquals("Wrong wrapped property name", "base.prop.name",
            group.getFields().get(0).getPropertyName());
    }
    
    public void testFieldsNotDirectlyModifiable() throws Exception {
        try {
            group.getFields().add(new DefaultTextField("no", "No"));
            fail("Exception not thrown");
        } catch (UnsupportedOperationException uoe) {
            // expected
        }
    }
}
