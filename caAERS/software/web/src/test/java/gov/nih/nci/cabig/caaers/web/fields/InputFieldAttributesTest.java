package gov.nih.nci.cabig.caaers.web.fields;

import static gov.nih.nci.cabig.caaers.web.fields.InputField.COLS;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.DETAILS;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.OPTIONS;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.SIZE;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.web.fields.InputField.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Rhett Sutphin
 */
public class InputFieldAttributesTest extends AbstractTestCase {
    private InputField field = InputFieldFactory.createInputField(Category.TEXT);

    public void testSetOptions() throws Exception {
        Map<Object, Object> expectedMap = new HashMap<Object, Object>();
        InputFieldAttributes.setOptions(field, expectedMap);
        assertSame(expectedMap, field.getAttributes().get(OPTIONS));
    }

    public void testGetOptions() throws Exception {
        Map<Object, Object> expectedMap = new TreeMap<Object, Object>();
        field.getAttributes().put(OPTIONS, expectedMap);
        assertSame(expectedMap, InputFieldAttributes.getOptions(field));
    }

    public void testSetDetails() throws Exception {
        String expectedDetails = "Anything you like";
        InputFieldAttributes.setDetails(field, expectedDetails);
        assertEquals(expectedDetails, field.getAttributes().get(DETAILS));
    }

    public void testGetDetails() throws Exception {
        String expectedDetails = "Here's the thing";
        field.getAttributes().put(DETAILS, expectedDetails);
        assertSame(expectedDetails, InputFieldAttributes.getDetails(field));
    }

    @SuppressWarnings( { "UnnecessaryBoxing" })
    public void testSetSize() throws Exception {
        InputFieldAttributes.setSize(field, 88);
        assertEquals(Integer.valueOf(88), field.getAttributes().get(SIZE));
    }

    public void testGetSize() throws Exception {
        field.getAttributes().put(SIZE, 56);
        assertEquals(56, (int) InputFieldAttributes.getSize(field));
    }

    @SuppressWarnings( { "UnnecessaryBoxing" })
    public void testSetColumns() throws Exception {
        InputFieldAttributes.setColumns(field, 45);
        assertEquals(Integer.valueOf(45), field.getAttributes().get(COLS));
    }

    public void testGetColumns() throws Exception {
        field.getAttributes().put(COLS, 97);
        assertEquals(97, (int) InputFieldAttributes.getColumns(field));
    }
}
