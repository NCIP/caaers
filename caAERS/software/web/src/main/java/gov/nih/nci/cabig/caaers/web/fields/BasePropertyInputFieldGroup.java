package gov.nih.nci.cabig.caaers.web.fields;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class BasePropertyInputFieldGroup extends AbstractInputFieldGroup {
    private String baseProperty;

    private List<InputField> fields;

    public BasePropertyInputFieldGroup(String name, String baseProperty) {
        this(name, null, baseProperty);
    }

    public BasePropertyInputFieldGroup(String name, String displayName, String baseProperty) {
        super(name, displayName);
        this.baseProperty = baseProperty;
        this.fields = new ArrayList<InputField>();
    }

    public BasePropertyInputFieldGroup addField(InputField newField) {
        fields.add(new InputFieldWrapper(newField));
        return this;
    }

    public List<InputField> getFields() {
        // use #addField to add a field, not #getFields().add()
        return Collections.unmodifiableList(fields);
    }

    public class InputFieldWrapper extends QualifiedPropertyNameInputField {
        public InputFieldWrapper(InputField src) {
            super(src);
        }

        @Override
        protected String qualifyPropertyName(String propertyName) {
            return new StringBuilder(baseProperty).append('.').append(propertyName).toString();
        }

        @Override
        protected InputField qualifySubfield(InputField subfield) {
            return new InputFieldWrapper(subfield);
        }
    }
}
