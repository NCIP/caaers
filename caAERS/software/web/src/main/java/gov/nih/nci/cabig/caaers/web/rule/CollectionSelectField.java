package gov.nih.nci.cabig.caaers.web.rule;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Rhett Sutphin
 */
public class CollectionSelectField extends DefaultSelectField {
    private Collection<?> items;

    private String itemValueProperty;

    private String itemLabelProperty;

    public CollectionSelectField(String propertyName, String displayName, boolean required,
                    Collection<?> items, String itemValueProperty, String itemDisplayProperty) {
        super(propertyName, displayName, required, new LinkedHashMap<Object, Object>());
        this.items = items;
        this.itemValueProperty = itemValueProperty;
        this.itemLabelProperty = itemDisplayProperty;
        buildOptions();
    }

    private void buildOptions() {
        for (Object item : getItems()) {
            BeanWrapper wrappedItem = new BeanWrapperImpl(item);
            Object value = extractProperty(wrappedItem, getItemValueProperty());
            Object label = extractProperty(wrappedItem, getItemLabelProperty());
            getOptions().put(value, label);
        }
    }

    private Object extractProperty(BeanWrapper wrappedItem, String propertyName) {
        if (wrappedItem.getWrappedInstance() == null) {
            return null;
        } else if (propertyName == null) {
            return wrappedItem.getWrappedInstance().toString();
        } else {
            return wrappedItem.getPropertyValue(propertyName);
        }
    }

    public Collection<?> getItems() {
        return items;
    }

    public String getItemValueProperty() {
        return itemValueProperty;
    }

    public String getItemLabelProperty() {
        return itemLabelProperty;
    }
}
