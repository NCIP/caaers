package gov.nih.nci.cabig.caaers.web.fields;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public abstract class QualifiedPropertyNameInputField implements InputField {
    private InputField src;

    public QualifiedPropertyNameInputField(InputField src) {
        this.src = src;
    }

    protected InputField getSourceField() {
        return src;
    }

    public void validate(BeanWrapper commandBean, Errors errors) {
        AbstractInputField.validateRequired(this, commandBean, errors);
    }

    public Category getCategory() {
        return getSourceField().getCategory();
    }

    public String getCategoryName() {
        return getSourceField().getCategoryName();
    }

    public String getDisplayName() {
        return getSourceField().getDisplayName();
    }

    public boolean isRequired() {
        return getSourceField().isRequired();
    }

    public String getPropertyName() {
        return qualifyPropertyName(getSourceField().getPropertyName());
    }

    public Map<String, Object> getAttributes() {
        return getSourceField().getAttributes();
    }

    protected abstract String qualifyPropertyName(String propertyName);


    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
            .append("[propertyName=").append(getPropertyName())
            .append("; source propertyName=").append(getSourceField().getPropertyName())
            .append(']').toString();
    }
}
