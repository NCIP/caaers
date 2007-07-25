package gov.nih.nci.cabig.caaers.web.fields;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

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

    protected AbstractInputField() {
        this.attributes = new LinkedHashMap<String, Object>();
    }

    protected AbstractInputField(String propertyName, String displayName, boolean required) {
        this();
        this.displayName = displayName;
        this.propertyName = propertyName;
        this.required = required;
    }

    /** This base implementation does a simple not-null check if the field is required. */ 
    public void validate(BeanWrapper commandBean, Errors errors) {
        validateRequired(this, commandBean, errors);
    }

    /**
     * Helper so that other InputField implementations can easily implement requiredness
     * validation just like this class.
     */
    public static void validateRequired(InputField field, BeanWrapper commandBean, Errors errors) {
        if (field.isRequired()) {
            Object propValue = commandBean.getPropertyValue(field.getPropertyName());
            if (propValue == null) {
                errors.rejectValue(field.getPropertyName(),
                    "REQUIRED", "Missing " + field.getDisplayName());
            }
        }
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

    ////// OBJECT METHODS

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
            .append("[propertyName=").append(getPropertyName())
            .append("; category=").append(getCategoryName()).append(']')
            .toString();
    }
}
