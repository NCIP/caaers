package gov.nih.nci.cabig.caaers.web.fields;

import static gov.nih.nci.cabig.caaers.web.fields.InputField.Category.*;

import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;

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
    private static final FieldValidator[] EMPTY_VALIDATORS = new FieldValidator[0];
    private InputFieldFactory() { }

    public static InputField createTextField(String propertyName, String displayName, FieldValidator... validators) {
    	return new DefaultInputField(TEXT, propertyName, displayName, validators);
    }

    public static InputField createTextField(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(TEXT, propertyName, displayName, required);
    }

    public static InputField createDateField(String propertyName, String displayName, FieldValidator... validators) {
    	return new DefaultInputField(DATE, propertyName, displayName, validators);
    }

    public static InputField createDateField(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(DATE, propertyName, displayName, required);
    }

    public static InputField createTextArea(String propertyName, String displayName, FieldValidator... validators) {
    	 return new DefaultInputField(TEXTAREA, propertyName, displayName, validators);
    }

    public static InputField createTextArea(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(TEXTAREA, propertyName, displayName, required);
    }

    public static InputField createAutocompleterField(String propertyName, String displayName, FieldValidator... validators) {
    	return new DefaultInputField(AUTOCOMPLETER, propertyName, displayName, validators);
    }

    public static InputField createAutocompleterField(String propertyName, String displayName, boolean required) {
        return new DefaultInputField(AUTOCOMPLETER, propertyName, displayName, required);
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
        if(required){
        	return createBooleanSelectField(propertyName, displayName, 
        			DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY,
        			FieldValidator.NOT_NULL_VALIDATOR);
        }else{
        	return createBooleanSelectField(propertyName, displayName, 
        			DEFAULT_TRUE_DISPLAY, DEFAULT_FALSE_DISPLAY,
        			EMPTY_VALIDATORS);
        }
    }

    public static InputField createBooleanSelectField(
        String propertyName, String displayName,
        String trueDisplay, String falseDisplay, FieldValidator... validators
    ) {
        DefaultInputField select = new DefaultInputField(SELECT, propertyName, displayName, validators);
        Map<Object, Object> opts = new LinkedHashMap<Object, Object>();
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
    	if(required){
    		return createLongSelectField(propertyName, displayName, options, FieldValidator.NOT_NULL_VALIDATOR);
    	}else{
    		return createLongSelectField(propertyName, displayName, options, EMPTY_VALIDATORS);
    	}
    }

    public static InputField createCheckboxField(String propertyName, String displayName, FieldValidator... validators) {
        // it doesn't make sense for checkboxes to ever be "required"
        return new DefaultInputField(InputField.Category.CHECKBOX, propertyName, displayName, validators);
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
        
        private DefaultInputField(Category category, String propertyName, String displayName, FieldValidator... fieldValidator) {
            super(propertyName, displayName, fieldValidator);
            this.category = category;
        }
        

        @Override
        public Category getCategory() {
            return category;
        }
    }
}
