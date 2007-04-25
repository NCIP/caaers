package gov.nih.nci.cabig.caaers.web.fields;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class DefaultSelectFieldTest extends CaaersTestCase {
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
}
