package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import static gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField.collectOptions;
import gov.nih.nci.cabig.caaers.domain.Grade;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
 * @author Rhett Sutphin
 */
public class BaseSelectFieldTest extends CaaersTestCase {
    public void testOptionsStoredAsAttribute() throws Exception {
        Map<Object, Object> expectedOptions = new HashMap<Object, Object>();
        DefaultSelectField field = new DefaultSelectField("pn", "P N", false, expectedOptions);
        assertSame(expectedOptions, field.getAttributes().get(InputField.OPTIONS));
    }

    public void testOptionsAvailableAsProperty() throws Exception {
        Map<Object, Object> expectedOptions = new HashMap<Object, Object>();
        DefaultSelectField field = new DefaultSelectField("pn", "P N", false, expectedOptions);
        assertSame(expectedOptions, field.getOptions());
    }

    public void testOptionsSetAsAttributeVisibleAsProperty() throws Exception {
        Map<Object, Object> expectedOptions = new HashMap<Object, Object>();
        DefaultSelectField field = new DefaultSelectField();
        field.getAttributes().put(InputField.OPTIONS, expectedOptions);
        assertSame(expectedOptions, field.getOptions());
    }

    public void testBuiltOptions() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = collectOptions(items, "code", "name");

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade.getName());
        }
    }

    public void testBuiltOptionsMaintainsOrder() throws Exception {
        List<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = collectOptions(items, "code", "name");

        List<Object> actualKeys = new LinkedList<Object>(actual.keySet());
        assertEquals("Wrong number of options", items.size(), actualKeys.size());
        for (int i = 0; i < items.size(); i++) {
            assertEquals("Wrong key at " + i, items.get(i).getCode(), actualKeys.get(i));
        }
    }

    public void testBuiltOptionsNullValuePropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = collectOptions(items, null, "name");

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.toString()), grade.getName());
        }
    }

    public void testBuiltOptionsNullLabelPropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = collectOptions(items, "code", null);

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade.toString());
        }
    }
}
