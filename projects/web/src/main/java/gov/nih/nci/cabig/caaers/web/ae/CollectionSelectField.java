package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Collection;

/**
 * @author Rhett Sutphin
 */
public class CollectionSelectField extends SelectField {
    private Collection<?> items;
    private String itemValueProperty;
    private String itemLabelProperty;

    public CollectionSelectField(
        String propertyName, String displayName, boolean required,
        Collection<?> items, String itemValueProperty, String itemDisplayProperty
    ) {
        super(propertyName, displayName, required);
        this.items = items;
        this.itemValueProperty = itemValueProperty;
        this.itemLabelProperty = itemDisplayProperty;
    }

    public String getType() {
        return "collection-select";
    }

    public Collection<?> getItems() {
        return items;
    }

    public void setItems(Collection<?> items) {
        this.items = items;
    }

    public String getItemValueProperty() {
        return itemValueProperty;
    }

    public void setItemValueProperty(String itemValueProperty) {
        this.itemValueProperty = itemValueProperty;
    }

    public String getItemLabelProperty() {
        return itemLabelProperty;
    }

    public void setItemLabelProperty(String itemLabelProperty) {
        this.itemLabelProperty = itemLabelProperty;
    }
}
