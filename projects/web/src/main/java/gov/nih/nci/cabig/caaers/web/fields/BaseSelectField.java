package gov.nih.nci.cabig.caaers.web.fields;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Map;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Base class for SELECT and LONGSELECT fields.
 *
 * @author Rhett Sutphin
 */
public abstract class BaseSelectField extends AbstractInputField {
    protected BaseSelectField() { }

    public BaseSelectField(
        String propertyName, String displayName, boolean required, Map<Object, Object> options
    ) {
        super(propertyName, displayName, required);
        setOptions(options);
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
     * @return an options map suitable for passing to the {@link #setOptions} or
     *  {@link gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField#DefaultSelectField(String, String, boolean, java.util.Map)}
     *
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

    @Override
    public abstract Category getCategory();

    @SuppressWarnings("unchecked")
    public Map<Object, Object> getOptions() {
        return (Map<Object, Object>) getAttributes().get(OPTIONS);
    }

    public void setOptions(Map<Object, Object> options) {
        getAttributes().put(OPTIONS, options);
    }
}
