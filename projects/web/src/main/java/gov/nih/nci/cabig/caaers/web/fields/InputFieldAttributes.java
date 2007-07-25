package gov.nih.nci.cabig.caaers.web.fields;

import static gov.nih.nci.cabig.caaers.web.fields.InputField.*;

import java.util.Map;

/**
 * Helpers for accessing known attributes of InputFields without having to cast.
 * (Also serves as documentation of the expected types of various attributes.)
 *
 * @author Rhett Sutphin
 */
public class InputFieldAttributes {
    @SuppressWarnings({ "unchecked" })
    public static Map<Object, Object> getOptions(InputField field) {
        return (Map<Object, Object>) field.getAttributes().get(OPTIONS);
    }

    public static void setOptions(InputField field, Map<Object, Object> options) {
        field.getAttributes().put(OPTIONS, options);
    }

    public static Integer getColumns(InputField field) {
        return (Integer) field.getAttributes().get(COLS);
    }

    public static void setColumns(InputField field, Integer cols) {
        field.getAttributes().put(COLS, cols);
    }

    public static Integer getSize(InputField field) {
        return (Integer) field.getAttributes().get(SIZE);
    }

    public static void setSize(InputField field, Integer size) {
        field.getAttributes().put(SIZE, size);
    }

    public static String getDetails(InputField field) {
        return (String) field.getAttributes().get(DETAILS);
    }

    public static void setDetails(InputField field, String details) {
        field.getAttributes().put(DETAILS, details);
    }

    private InputFieldAttributes() { }
}
