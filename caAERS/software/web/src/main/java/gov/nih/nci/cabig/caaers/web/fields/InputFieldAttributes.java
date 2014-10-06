/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.fields;

import java.util.Map;

import static gov.nih.nci.cabig.caaers.web.fields.InputField.*;

/**
 * Helpers for accessing known attributes of InputFields without having to cast. (Also serves as
 * documentation of the expected types of various attributes.)
 * 
 * @author Rhett Sutphin
 */
public class InputFieldAttributes {
    @SuppressWarnings( { "unchecked" })
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

    public static Integer getRows(InputField field) {
        return (Integer) field.getAttributes().get(ROWS);
    }

    public static void setRows(InputField field, Integer rows) {
        field.getAttributes().put(ROWS, rows);
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

    public static void enableAutoCompleterClearButton(InputField field) {
        field.getAttributes().put(InputField.ENABLE_CLEAR, true);
    }


    public static void disableAutoCompleterClearButton(InputField field) {
        field.getAttributes().remove(InputField.ENABLE_CLEAR);
    }


    public static void setHelpProperty(InputField field, String helpKey){
    	field.getAttributes().put(InputField.HELP, helpKey);
    }

    private InputFieldAttributes() {
    }
    
    public static void setI18NLabelProperty(InputField field, String labelProperty) {
        field.getAttributes().put(InputField.LABEL_PROPERTY, labelProperty);
    }


    public static Integer getMaxLength(InputField field) {
        return (Integer) field.getAttributes().get(MAX_LENGTH);
    }

    public static void setMaxLength(InputField field, Integer size) {
        field.getAttributes().put(MAX_LENGTH, size);
    }
}
