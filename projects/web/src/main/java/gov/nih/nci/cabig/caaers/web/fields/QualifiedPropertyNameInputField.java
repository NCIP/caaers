package gov.nih.nci.cabig.caaers.web.fields;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public abstract class QualifiedPropertyNameInputField implements InputField {
    private InputField src;
    private Map<String, Object> attributes;
    private boolean mandatory;

    public QualifiedPropertyNameInputField(InputField src) {
        this.src = src;
        setAttributes(src.getAttributes());
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
        return attributes;
    }

    protected abstract String qualifyPropertyName(String propertyName);

    @SuppressWarnings("unchecked")
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = new LinkedHashMap<String, Object>(attributes);
        if (attributes.containsKey(SUBFIELDS)) {
            List<InputField> subfields = (List<InputField>) attributes.get(SUBFIELDS);
            List<InputField> newSubfields = new ArrayList<InputField>(subfields.size());
            for (InputField subfield : subfields) {
                newSubfields.add(qualifySubfield(subfield));
            }
            this.attributes.put(SUBFIELDS, newSubfields);
        }
    }

    protected abstract InputField qualifySubfield(InputField subfield);

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
            .append("[propertyName=").append(getPropertyName())
            .append("; source propertyName=").append(getSourceField().getPropertyName())
            .append(']').toString();
    }
}
