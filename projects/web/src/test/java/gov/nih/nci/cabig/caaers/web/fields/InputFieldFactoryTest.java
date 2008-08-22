package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class InputFieldFactoryTest extends AbstractTestCase {
    public void testOptionsStoredAsAttribute() throws Exception {
        Map<Object, Object> expectedOptions = new HashMap<Object, Object>();
        InputField field = InputFieldFactory.createSelectField("pn", "P N", false, expectedOptions);
        assertSame(expectedOptions, InputFieldAttributes.getOptions(field));
    }

    public void testCollectedOptions() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, "code", "name");

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade
                            .getName());
        }
    }

    public void testCollectedOptionsMaintainsOrder() throws Exception {
        List<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, "code", "name");

        List<Object> actualKeys = new LinkedList<Object>(actual.keySet());
        assertEquals("Wrong number of options", items.size(), actualKeys.size());
        for (int i = 0; i < items.size(); i++) {
            assertEquals("Wrong key at " + i, items.get(i).getCode(), actualKeys.get(i));
        }
    }

    public void testCollectedOptionsNullValuePropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, null, "name");

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.toString()), grade
                            .getName());
        }
    }

    public void testCollectedOptionsNullLabelPropNameUsesToString() throws Exception {
        Collection<Grade> items = Arrays.asList(Grade.values());
        Map<Object, Object> actual = WebUtils.collectOptions(items, "code", null);

        assertEquals("Wrong number of options", items.size(), actual.size());
        for (Grade grade : items) {
            assertEquals("Mismatch at expected item " + grade, actual.get(grade.getCode()), grade
                            .toString());
        }
    }
}
