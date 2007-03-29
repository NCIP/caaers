package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractInputField implements InputField {
    private String displayName;
    private String propertyName;
    private boolean required;
    private Map<String, Object> attributes;

    protected AbstractInputField() { }

    protected AbstractInputField(String propertyName, String displayName, boolean required) {
        this.displayName = displayName;
        this.propertyName = propertyName;
        this.required = required;
        this.attributes = new LinkedHashMap<String, Object>();
    }

    public abstract Category getCategory();

    public String getCategoryName() {
        return getCategory().name().toLowerCase();
    }

    public String getDisplayName() {
        return displayName == null ? propertyName : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
