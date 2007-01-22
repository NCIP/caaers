package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.Grade;

import java.util.Collection;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

/**
 * @author Rhett Sutphin
 */
public class CollectionSelectFieldTest extends CaaersTestCase {
    public void testCreatedOptions() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        CollectionSelectField field = new CollectionSelectField("DC", "DC", false, items, "code", "name");
        Map<Object, Object> actual = field.getOptions();

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade.getName());
        }
    }

    public void testCreatedOptionsMaintainsOrder() throws Exception {
        List<Grade> items = Arrays.asList(Grade.values());
        CollectionSelectField field = new CollectionSelectField("DC", "DC", false, items, "code", "name");
        Map<Object, Object> actual = field.getOptions();

        List<Object> actualKeys = new LinkedList<Object>(actual.keySet());
        assertEquals("Wrong number of options", items.size(), actualKeys.size());
        for (int i = 0; i < items.size(); i++) {
            assertEquals("Wrong key at " + i, items.get(i).getCode(), actualKeys.get(i));
        }
    }

    public void testNullValuePropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        CollectionSelectField field = new CollectionSelectField("DC", "DC", false, items, null, "name");
        Map<Object, Object> actual = field.getOptions();

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.toString()), grade.getName());
        }
    }

    public void testNullLabelPropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        CollectionSelectField field = new CollectionSelectField("DC", "DC", false, items, "code", null);
        Map<Object, Object> actual = field.getOptions();

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade.toString());
        }
    }
}
