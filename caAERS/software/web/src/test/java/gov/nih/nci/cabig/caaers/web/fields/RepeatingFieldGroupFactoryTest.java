package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class RepeatingFieldGroupFactoryTest extends AbstractTestCase {
    private RepeatingFieldGroupFactory factory = new RepeatingFieldGroupFactory("start",
                    "root.list");

    public void testRegularFieldsWrapped() throws Exception {
        factory.addField(InputFieldFactory.createTextField("textField", "DC", false));
        InputFieldGroup actualGroup = factory.createGroup(3);
        assertEquals("Wrong number of fields", 1, actualGroup.getFields().size());
        InputField actualField = actualGroup.getFields().get(0);
        assertEquals("Field property name not qualified", "root.list[3].textField", actualField
                        .getPropertyName());
        assertEquals("Category not preserved", InputField.Category.TEXT, actualField.getCategory());
    }

    @SuppressWarnings("unchecked")
    public void testSelectFieldsWrapped() throws Exception {
        factory.addField(InputFieldFactory.createSelectField("selectField", "DC", false,
                        Collections.<Object, Object> singletonMap("k", "V")));
        InputFieldGroup actualGroup = factory.createGroup(7);
        assertEquals("Wrong number of fields", 1, actualGroup.getFields().size());
        InputField actualField = actualGroup.getFields().get(0);
        assertEquals("Field property name not qualified", "root.list[7].selectField", actualField
                        .getPropertyName());
        assertEquals("Category not preserved", InputField.Category.SELECT, actualField
                        .getCategory());
        Map<Object, Object> actualOptions = InputFieldAttributes.getOptions(actualField);
        assertNotNull(actualOptions);
        assertEquals("Wrong number of options", 1, actualOptions.size());
        assertEquals("Wrong option", "V", actualOptions.get("k"));
    }

    @SuppressWarnings("unchecked")
    public void testCompositeFieldsWrapped() throws Exception {
        DefaultInputFieldGroup group = new DefaultInputFieldGroup("dc");
        group.setFields(Collections.<InputField> singletonList(InputFieldFactory.createTextField(
                        "subfield", "DC", true)));
        factory.addField(new CompositeField("compositeField", group));

        // create other groups to make sure shared state isn't being mutated
        for (int i = 0; i <= 3; i++)
            factory.createGroup(i);

        InputFieldGroup actualGroup = factory.createGroup(4);
        assertEquals("Wrong number of fields", 1, actualGroup.getFields().size());
        InputField actualField = actualGroup.getFields().get(0);
        assertEquals("Field property name not qualified", "root.list[4].compositeField",
                        actualField.getPropertyName());
        List<InputField> fields = (List<InputField>) actualField.getAttributes().get(
                        InputField.SUBFIELDS);
        assertNotNull("Subfields not present", fields);
        assertEquals("Wrong number of subfields", 1, fields.size());
        assertEquals("Subfield name not correctly qualified",
                        "root.list[4].compositeField.subfield", fields.get(0).getPropertyName());
    }
}
