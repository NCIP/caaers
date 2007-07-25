package gov.nih.nci.cabig.caaers.web.fields;

import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.*;

import java.util.Map;
import java.util.Collection;
import java.util.LinkedHashMap;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 */
public class InputFieldFactory {
    private static final boolean DEFAULT_REQUIREDNESS = false;
    private static final String DEFAULT_TRUE_DISPLAY = "Yes";
    private static final String DEFAULT_FALSE_DISPLAY = "No";

    private InputFieldFactory() { }

    public static InputField createTextField(String propertyName, String displayName) {
        return createTextField(propertyName, displayName, DEFAULT_REQUIREDNESS);
    }

    public static InputField createTextField(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(TEXT, propertyName, displayName, required);
    }

    public static InputField createDateField(String propertyName, String displayName) {
        return createDateField(propertyName, displayName, DEFAULT_REQUIREDNESS);
    }

    public static InputField createDateField(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(DATE, propertyName, displayName, required);
    }

    public static InputField createTextArea(String propertyName, String displayName) {
        return createTextArea(propertyName, displayName, DEFAULT_REQUIREDNESS);
    }

    public static InputField createTextArea(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(TEXTAREA, propertyName, displayName, required);
    }

    public static InputField createAutocompleterField(String propertyName, String displayName) {
        return createAutocompleterField(propertyName, displayName, DEFAULT_REQUIREDNESS);
    }

    public static InputField createAutocompleterField(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(AUTOCOMPLETER, propertyName, displayName, required);
    }

    public static InputField createSelectField(String propertyName, String displayName, Map<Object, Object> options) {
        return createSelectField(propertyName, displayName, DEFAULT_REQUIREDNESS, options);
    }

    public static InputField createSelectField(String propertyName, String displayName, boolean required, Map<Object, Object> options) {
        DefaultInputField select = new DefaultInputField(SELECT, propertyName, displayName, required);
        InputFieldAttributes.setOptions(select, options);
        return select;
    }

    public static InputField createBooleanSelectField(String propertyName, String displayName) {
        return createBooleanSelectField(propertyName, displayName, DEFAULT_REQUIREDNESS, DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY);
    }

    public static InputField createBooleanSelectField(String propertyName, String displayName, boolean required) {
        return createBooleanSelectField(propertyName, displayName, required, DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY);
    }

    public static InputField createBooleanSelectField(
        String propertyName, String displayName, boolean required,
        String trueDisplay, String falseDisplay
    ) {
        DefaultInputField select = new DefaultInputField(SELECT, propertyName, displayName, required);
        Map<Object, Object> opts = new LinkedHashMap<Object, Object>();
        opts.put(Boolean.TRUE, trueDisplay);
        opts.put(Boolean.FALSE, falseDisplay);
        InputFieldAttributes.setOptions(select, opts);
        return select;
    }

    public static InputField createLongSelectField(String propertyName, String displayName, Map<Object, Object> options) {
        return createLongSelectField(propertyName, displayName, DEFAULT_REQUIREDNESS, options);
    }

    public static InputField createLongSelectField(String propertyName, String displayName, boolean required, Map<Object, Object> options) {
        DefaultInputField longselect = new DefaultInputField(LONGSELECT, propertyName, displayName, required);
        InputFieldAttributes.setOptions(longselect, options);
        return longselect;
    }

    public static InputField createCheckboxField(String propertyName, String displayName) {
        // it doesn't make sense for checkboxes to ever be "required"
        return new DefaultInputField(InputField.Category.CHECKBOX, propertyName, displayName, false);
    }

    /**
     * Intended mainly for testing.
     */
    public static InputField createInputField(InputField.Category category) {
        return new DefaultInputField(category, "dc", "Don't Care", DEFAULT_REQUIREDNESS);
    }

    /**
     * Creates and options map using the same principles as spring's
     * <code>form:options</code> tag.
     *
     * @param items A collection of items that should make up the options.  The options will be
     *          in the same iteration order as this collection.
     * @param itemValueProperty The property of the collection's elements which should be used as
     *          as the submitted value for each item.  If null, the result of <code>item.toString()</code>
     *          will be used instead.
     * @param itemLabelProperty The property of the collection's elements which should be used as
     *          as the displayed label for each item.  If null, the result of <code>item.toString()</code>
     *          will be used instead.
     * @return an options map suitable for use as the {@link InputField#OPTIONS} attribute
     */
    public static Map<Object, Object> collectOptions(
        Collection<?> items, String itemValueProperty, String itemLabelProperty, String blankValue
    ) {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        if (blankValue != null) options.put("", blankValue);
        for (Object item : items) {
            BeanWrapper wrappedItem = new BeanWrapperImpl(item);
            Object value = extractProperty(wrappedItem, itemValueProperty);
            Object label = extractProperty(wrappedItem, itemLabelProperty);
            options.put(value, label);
        }
        return options;
    }

    public static Map<Object, Object> collectOptions(
        Collection<?> items, String itemValueProperty, String itemLabelProperty
    ) {
        return collectOptions(items, itemValueProperty, itemLabelProperty, null);
    }

    private static Object extractProperty(BeanWrapper wrappedItem, String propertyName) {
        if (wrappedItem.getWrappedInstance() == null) {
            return null;
        } else if (propertyName == null) {
            return wrappedItem.getWrappedInstance().toString();
        } else {
            return wrappedItem.getPropertyValue(propertyName);
        }
    }

    public static class DefaultInputField extends AbstractInputField {
        private Category category;

        private DefaultInputField(Category category, String propertyName, String displayName, boolean required) {
            super(propertyName, displayName, required);
            this.category = category;
        }

        @Override
        public Category getCategory() {
            return category;
        }
    }
}
