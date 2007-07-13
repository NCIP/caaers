package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.util.List;

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

    public void testCompositeFieldSubfieldsWrapped() throws Exception {
        group.addField(new CompositeField("amount",
            new DefaultInputFieldGroup().addField(new DefaultTextField("currency", ""))));
        assertEquals("Wrong number of fields added", 1, group.getFields().size());
        InputField actual = group.getFields().get(0);
        assertEquals("Wrong wrapped property name", "base.prop.amount", actual.getPropertyName());
        List<InputField> subfields = CompositeField.getSubfields(actual);
        assertNotNull("Added field is not composite", subfields);
        assertEquals("Wrong number of subfields", 1, subfields.size());
        assertEquals("Wrong subfield property name", "base.prop.amount.currency",
            subfields.get(0).getPropertyName());
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
