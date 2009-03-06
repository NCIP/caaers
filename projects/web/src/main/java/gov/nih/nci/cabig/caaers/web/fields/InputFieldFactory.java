package gov.nih.nci.cabig.caaers.web.fields;

import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.AUTOCOMPLETER;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.DATE;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.INPLACE_TEXT;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.LABEL;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.LONGSELECT;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.SELECT;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.SPLIT_DATE;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.TEXT;
import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.TEXTAREA;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class InputFieldFactory {

    private static final boolean DEFAULT_REQUIREDNESS = false;
    private static final String DEFAULT_TRUE_DISPLAY = "Yes";
    private static final String DEFAULT_FALSE_DISPLAY = "No";
    private static final FieldValidator[] EMPTY_VALIDATORS = new FieldValidator[0];

    private InputFieldFactory() {
    }

    public static InputField createInputField(InputField.Category category, String propertyName, String displayName, FieldValidator... validators) {
        return new DefaultInputField(category, propertyName, displayName, validators);
    }

    public static InputField createInputField(InputField.Category category, String propertyName, String displayName, boolean required) {
        return new DefaultInputField(category, propertyName, displayName, required);
    }

    public static InputField createTextField(String propertyName, String displayName, FieldValidator... validators) {
        return createInputField(TEXT, propertyName, displayName, validators);
    }

    public static InputField createTextField(String propertyName, String displayName, boolean required) {
        return createInputField(TEXT, propertyName, displayName, required);
    }


    public static InputField createPastDateField(String propertyName, String displayName, boolean required) {
        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.PAST_DATE_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.PAST_DATE_VALIDATOR};
        }
        return createInputField(DATE, propertyName, displayName, validators);
    }

    public static InputField createFutureDateField(String propertyName, String displayName, boolean required) {
        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.FUTURE_DATE_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.FUTURE_DATE_VALIDATOR};
        }
        return createInputField(DATE, propertyName, displayName, validators);
    }

    public static InputField createDateField(String propertyName, String displayName, boolean required) {
        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.DATE_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.DATE_VALIDATOR};
        }
        return createInputField(DATE, propertyName, displayName, validators);
    }


    public static InputField createSplitDateField(String propertyName, String displayName, boolean dayRequired, boolean monthRequired, boolean yearRequired, boolean required) {

        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.DATE_VALUE_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.DATE_VALUE_VALIDATOR};
        }
        DefaultInputField field = new DefaultInputField(SPLIT_DATE, propertyName, displayName, validators);
        field.getAttributes().put(InputField.DAY_REQUIRED, dayRequired);
        field.getAttributes().put(InputField.MONTH_REQUIRED, monthRequired);
        field.getAttributes().put(InputField.YEAR_REQUIRED, yearRequired);
        return field;

    }


    public static InputField createTextArea(String propertyName, String displayName, FieldValidator... validators) {
        return createInputField(TEXTAREA, propertyName, displayName, validators);
    }

    public static InputField createTextArea(String propertyName, String displayName, boolean required) {
        return createInputField(TEXTAREA, propertyName, displayName, required);
    }

    public static InputField createAutocompleterField(String propertyName, String displayName, FieldValidator... validators) {
        return createInputField(AUTOCOMPLETER, propertyName, displayName, validators);
    }

    public static InputField createAutocompleterField(String propertyName, String displayName, boolean required) {
        return createInputField(AUTOCOMPLETER, propertyName, displayName, required);
    }

    public static InputField createInplaceTextEditorField(String propertyName, String displayName, boolean required) {
        return createInputField(INPLACE_TEXT, propertyName, displayName, required);
    }

    public static InputField createInplaceTextEditorField(String propertyName, String displayName, FieldValidator... validators) {
        return createInputField(INPLACE_TEXT, propertyName, displayName, validators);
    }

    public static InputField createLabelField(String propertyName, String displayName, boolean required) {
        return createInputField(LABEL, propertyName, displayName, required);
    }

    public static InputField createLabelField(String propertyName, String displayName, FieldValidator... validators) {
        return createInputField(LABEL, propertyName, displayName, validators);
    }

    public static InputField createSelectField(String propertyName, String displayName, Map<Object, Object> options, FieldValidator... validators) {
        DefaultInputField select = new DefaultInputField(SELECT, propertyName, displayName, validators);
        InputFieldAttributes.setOptions(select, options);
        return select;
    }

    public static InputField createSelectField(String propertyName, String displayName, boolean required, Map<Object, Object> options) {
        DefaultInputField select = new DefaultInputField(SELECT, propertyName, displayName, required);
        InputFieldAttributes.setOptions(select, options);
        return select;
    }

    public static InputField createBooleanSelectField(String propertyName, String displayName) {
        return createBooleanSelectField(propertyName, displayName, DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY);
    }

    public static InputField createBooleanSelectField(String propertyName, String displayName, boolean required) {
        if (required) {
            return createBooleanSelectField(propertyName, displayName, DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY, FieldValidator.NOT_NULL_VALIDATOR);
        } else {
            return createBooleanSelectField(propertyName, displayName, DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY, EMPTY_VALIDATORS);
        }
    }

    public static InputField createBooleanSelectField(String propertyName, String displayName, String trueDisplay, String falseDisplay, FieldValidator... validators) {
        DefaultInputField select = new DefaultInputField(SELECT, propertyName, displayName, validators);
        Map<Object, Object> opts = new LinkedHashMap<Object, Object>();
        opts.put("", "Please select");
        opts.put(Boolean.FALSE, falseDisplay);
        opts.put(Boolean.TRUE, trueDisplay);
        InputFieldAttributes.setOptions(select, opts);
        return select;
    }

    public static InputField createLongSelectField(String propertyName, String displayName, Map<Object, Object> options, FieldValidator... validators) {
        DefaultInputField longselect = new DefaultInputField(LONGSELECT, propertyName, displayName, validators);
        InputFieldAttributes.setOptions(longselect, options);
        return longselect;
    }

    public static InputField createLongSelectField(String propertyName, String displayName, boolean required, Map<Object, Object> options) {
        if (required) {
            return createLongSelectField(propertyName, displayName, options, FieldValidator.NOT_NULL_VALIDATOR);
        } else {
            return createLongSelectField(propertyName, displayName, options, EMPTY_VALIDATORS);
        }
    }

    public static InputField createCheckboxField(String propertyName, String displayName, FieldValidator... validators) {
        // it doesn't make sense for checkboxes to ever be "required"
        return new DefaultInputField(InputField.Category.CHECKBOX, propertyName, displayName, validators);
    }

    public static InputField createRadioButtonField(String propertyName, String displayName, String value, FieldValidator... validators) {
        DefaultInputField inputField = new DefaultInputField(InputField.Category.RADIO, propertyName, displayName, validators);
        inputField.getAttributes().put(InputField.DEFAULT_VALUE, value);
        return inputField;
    }

    /**
     * This field is used to display images on the screen. At present, this is more or less a kind of filler field,
     * which is used to act as a space holder.
     *
     * @return
     */
    public static InputField createImageField(String properyName, String displayName, String imageName) {
        return new DefaultInputField(InputField.Category.IMAGE, properyName, displayName);
    }

    /**
     * Intended mainly for testing.
     */
    public static InputField createInputField(InputField.Category category) {
        return new DefaultInputField(category, "dc", "Don't Care", DEFAULT_REQUIREDNESS);
    }

    public static InputField createNumberField(String propertyName, String displayName, boolean required) {
        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.NUMBER_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.NUMBER_VALIDATOR};
        }
        return createInputField(TEXT, propertyName, displayName, validators);
    }

    public static InputField createPhoneField(String propertyName, String displayName, boolean required) {
        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.PHONE_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.PHONE_VALIDATOR};
        }
        return createInputField(TEXT, propertyName, displayName, validators);
    }

    public static InputField createEmailField(String propertyName, String displayName, boolean required) {
        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.EMAIL_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.EMAIL_VALIDATOR};
        }
        return createInputField(TEXT, propertyName, displayName, validators);
    }

    public static InputField createZipCodeField(String propertyName, String displayName, boolean required) {
        FieldValidator validators[] = null;
        if (required) {
            validators = new FieldValidator[]{FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.ZIP_CODE_VALIDATOR};
        } else {
            validators = new FieldValidator[]{FieldValidator.ZIP_CODE_VALIDATOR};
        }
        return createInputField(TEXT, propertyName, displayName, validators);
    }

    public static class DefaultInputField extends AbstractInputField {
        private Category category;

        private DefaultInputField(Category category, String propertyName, String displayName, boolean required) {
            super(propertyName, displayName, required);
            this.category = category;
        }

        private DefaultInputField(Category category, String propertyName, String displayName, FieldValidator... fieldValidator) {
            super(propertyName, displayName, fieldValidator);
            this.category = category;
        }

        @Override
        public String getValidatorClassName() {
            return super.getValidatorClassName();    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public Category getCategory() {
            return category;
        }
    }
}
