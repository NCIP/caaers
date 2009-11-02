package gov.nih.nci.cabig.caaers.web.fields;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.Errors;

/**
 * An {@link InputField} which actually should be rendered as multiple HTML inputs. The multiple
 * inputs are provided as an {@link InputFieldGroup}; the effective property names of the fields in
 * that group will be (the property name of the <code>CompositeField</code>) + <code>'.'</code> +
 * (the property name of the field in the group)
 * 
 * @author Rhett Sutphin
 */
public class CompositeField extends AbstractInputField {
    private InputFieldGroup group;

    public CompositeField() {
    }

    public CompositeField(String propertyName, InputFieldGroup group) {
        super();
        setGroup(group);
        setPropertyName(propertyName);
        getAttributes().put(SUBFIELDS, createSubfields());
    }

    @SuppressWarnings( { "unchecked" })
    public static List<InputField> getSubfields(InputField compositeField) {
        return (List<InputField>) compositeField.getAttributes().get(SUBFIELDS);
    }

    public static List<String> getEffectivePropertyNames(InputField compositeField) {
        List<String> properties = new LinkedList<String>();
        for (InputField field : getSubfields(compositeField)) {
            if (compositeField.getPropertyName() == null) {
                properties.add(field.getPropertyName());
            } else {
                properties.add(compositeField.getPropertyName() + '.' + field.getPropertyName());
            }
        }
        return properties;
    }

    @Override
    public Category getCategory() {
        return Category.COMPOSITE;
    }

    @Override
    public void validate(BeanWrapper commandBean, Errors errors) {
        for (InputField field : CompositeField.getSubfields(this)) {
            field.validate(commandBean, errors);
        }
    }

    @Override
    public String getDisplayName() {
        return getGroup().getDisplayName();
    }

    private InputFieldGroup getGroup() {
        return group;
    }

    private void setGroup(InputFieldGroup group) {
        this.group = group;
    }

    protected List<InputField> createSubfields() {
        List<InputField> fields = new ArrayList<InputField>(getGroup().getFields().size());
        for (InputField srcField : getGroup().getFields()) {
            fields.add(new InputFieldWrapper(srcField));
        }
        return fields;
    }

    private class InputFieldWrapper extends QualifiedPropertyNameInputField {
        public InputFieldWrapper(InputField src) {
            super(src);
        }

        @Override
        protected String qualifyPropertyName(String propertyName) {
            StringBuilder qual = new StringBuilder();
            if (CompositeField.this.getPropertyName() != null) {
                qual.append(CompositeField.this.getPropertyName()).append('.');
            }
            return qual.append(propertyName).toString();
        }

        @Override
        protected InputField qualifySubfield(InputField subfield) {
            return new InputFieldWrapper(subfield);
        }

    }
}
