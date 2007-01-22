package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.util.Collections;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class RepeatingFieldGroupFactoryTest extends CaaersTestCase {
    private RepeatingFieldGroupFactory factory = new RepeatingFieldGroupFactory("start", "root.list");

    public void testRegularFieldsWrapped() throws Exception {
        factory.addField(new DefaultTextField("textField", "DC", false));
        InputFieldGroup actualGroup = factory.createGroup(3);
        assertEquals("Wrong number of fields", 1, actualGroup.getFields().size());
        InputField actualField = actualGroup.getFields().get(0);
        assertEquals("Field property name not qualified", "root.list[3].textField", actualField.getPropertyName());
        assertEquals("Category not preserved", InputField.Category.TEXT, actualField.getCategory());
    }

    public void testSelectFieldsWrapped() throws Exception {
        factory.addField(new DefaultSelectField("selectField", "DC", false,
            Collections.<Object, Object>singletonMap("k", "V")));
        InputFieldGroup actualGroup = factory.createGroup(7);
        assertEquals("Wrong number of fields", 1, actualGroup.getFields().size());
        InputField actualField = actualGroup.getFields().get(0);
        assertEquals("Field property name not qualified", "root.list[7].selectField", actualField.getPropertyName());
        assertEquals("Category not preserved", InputField.Category.SELECT, actualField.getCategory());
        assertTrue("SelectField type not preserved", actualField instanceof SelectField);
        Map<Object,Object> actualOptions = ((SelectField) actualField).getOptions();
        assertEquals("Wrong number of options", 1, actualOptions.size());
        assertEquals("Wrong option", "V", actualOptions.get("k"));
    }
}
