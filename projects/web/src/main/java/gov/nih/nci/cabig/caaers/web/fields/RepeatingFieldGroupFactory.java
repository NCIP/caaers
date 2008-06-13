package gov.nih.nci.cabig.caaers.web.fields;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class RepeatingFieldGroupFactory {
    protected String basename;

    protected String listPropertyName;

    protected DisplayNameCreator displayNameCreator;

    protected List<InputField> baseFields = new LinkedList<InputField>();

    public RepeatingFieldGroupFactory(String basename, String listPropertyName) {
        this.basename = basename;
        this.listPropertyName = listPropertyName;
    }

    public InputFieldGroup createGroup(int index) {
        RepeatingFieldGroup group = new RepeatingFieldGroup(createName(index), index);
        if (displayNameCreator != null) group.setDisplayName(displayNameCreator
                        .createDisplayName(index));
        return group;
    }

    protected String createName(int index) {
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

    public class RepeatingFieldGroup extends DefaultInputFieldGroup {
        private int index;

        public RepeatingFieldGroup(String name, int index) {
            super(name);
            this.index = index;
            createWrappedFields();
        }

        private void createWrappedFields() {
            for (InputField field : baseFields) {
                addField(new InputFieldWrapper(field));
            }
        }

        public int getIndex() {
            return index;
        }

        public class InputFieldWrapper extends QualifiedPropertyNameInputField {
            public InputFieldWrapper(InputField src) {
                super(src);
            }

            @Override
            protected String qualifyPropertyName(String propertyName) {
                StringBuilder qual = new StringBuilder(listPropertyName).append('[').append(
                                RepeatingFieldGroup.this.getIndex()).append(']');
                if (propertyName != null) {
                    qual.append('.').append(propertyName);
                }
                return qual.toString();
            }

            @Override
            protected InputField qualifySubfield(InputField subfield) {
                return new InputFieldWrapper(subfield);
            }
        }
    }

    public static interface DisplayNameCreator {
        String createDisplayName(int index);
    }
}
