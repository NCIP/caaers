package gov.nih.nci.cabig.caaers.web.fields;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

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

        public InputFieldWrapper(InputField src, RepeatingFieldGroup group) {
            super(src);
            this.group = group;
        }

        @Override
        protected String qualifyPropertyName(String propertyName) {
            return new StringBuilder(listPropertyName).append('[').append(group.getIndex()).append("].")
                .append(propertyName).toString();
        }
    }

    public static interface DisplayNameCreator {
        String createDisplayName(int index);
    }
}
