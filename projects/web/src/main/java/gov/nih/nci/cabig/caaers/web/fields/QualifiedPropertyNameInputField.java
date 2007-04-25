package gov.nih.nci.cabig.caaers.web.fields;

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
        return qualifyPropertyName(src.getPropertyName());
    }

    public Map<String, Object> getAttributes() {
        return getSourceField().getAttributes();
    }

    protected abstract String qualifyPropertyName(String propertyName);
}
