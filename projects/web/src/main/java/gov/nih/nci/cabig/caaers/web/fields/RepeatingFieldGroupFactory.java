package gov.nih.nci.cabig.caaers.web.fields;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;

/**
 * @author Rhett Sutphin
 */
public class RepeatingFieldGroupFactory {
    private String basename;
    private String listPropertyName;
    private DisplayNameCreator displayNameCreator;

    private List<InputField> baseFields = new LinkedList<InputField>();

    public RepeatingFieldGroupFactory(String basename, String listPropertyName) {
        this.basename = basename;
        this.listPropertyName = listPropertyName;
    }

    public InputFieldGroup createGroup(int index) {
        RepeatingFieldGroup group = new RepeatingFieldGroup(createName(index), index);
        group.setFields(createWrappedFields(group));
        if (displayNameCreator != null) group.setDisplayName(displayNameCreator.createDisplayName(index));
        return group;
    }

    private List<InputField> createWrappedFields(RepeatingFieldGroup group) {
        List<InputField> wrappedFields = new LinkedList<InputField>();
        for (InputField field : baseFields) {
            wrappedFields.add(new InputFieldWrapper(field, group));
        }
        return wrappedFields;
    }

    private String createName(int index) {
        return basename + index;
    }

    public void addField(InputField field) {
        baseFields.add(field);
    }

    public void setDisplayNameCreator(DisplayNameCreator displayNameCreator) {
        this.displayNameCreator = displayNameCreator;
    }

    public String getBasename() {
        return basename;
    }

    public static class RepeatingFieldGroup extends DefaultInputFieldGroup {
        private int index;

        public RepeatingFieldGroup(String name, int index) {
            super(name);
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    private class InputFieldWrapper extends QualifiedPropertyNameInputField {
        private RepeatingFieldGroup group;
        private Map<String, Object> attributes;

        public InputFieldWrapper(InputField src, RepeatingFieldGroup group) {
            super(src);
            this.group = group;
            setAttributes(getSourceField().getAttributes());
        }

        @Override
        protected String qualifyPropertyName(String propertyName) {
            StringBuilder qual = new StringBuilder(listPropertyName)
                .append('[').append(group.getIndex()).append(']');
            if (propertyName != null) {
                qual.append('.').append(propertyName);
            }
            return qual.toString();
        }

        @SuppressWarnings("unchecked")
        public void setAttributes(Map<String, Object> attributes) {
            this.attributes = new LinkedHashMap<String, Object>(attributes);
            if (attributes.containsKey(SUBFIELDS)) {
                List<InputField> subfields = (List<InputField>) attributes.get(SUBFIELDS);
                List<InputField> newSubfields = new ArrayList<InputField>(subfields.size());
                for (InputField subfield : subfields) {
                    newSubfields.add(new InputFieldWrapper(subfield, group));
                }
                this.attributes.put(SUBFIELDS, newSubfields);
            }
        }

        @Override
        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }

    public static interface DisplayNameCreator {
        String createDisplayName(int index);
    }
}
