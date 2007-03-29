package gov.nih.nci.cabig.caaers.web.ae;

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
            if (field instanceof SelectField) {
                wrappedFields.add(new SelectFieldWrapper((SelectField) field, group));
            } else {
                wrappedFields.add(new InputFieldWrapper<InputField>(field, group));
            }
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

    private class InputFieldWrapper<T extends InputField> implements InputField {
        protected T src;
        private RepeatingFieldGroup group;

        public InputFieldWrapper(T src, RepeatingFieldGroup group) {
            this.src = src;
            this.group = group;
        }

        public Category getCategory() {
            return src.getCategory();
        }

        public String getCategoryName() {
            return src.getCategoryName();
        }

        public String getDisplayName() {
            return src.getDisplayName();
        }

        public boolean isRequired() {
            return src.isRequired();
        }

        public String getPropertyName() {
            return qualifiedPropertyName(src.getPropertyName());
        }

        public Map<String, Object> getAttributes() {
            return src.getAttributes();
        }

        private String qualifiedPropertyName(String propertyName) {
            return new StringBuilder(listPropertyName).append('[').append(group.getIndex()).append("].")
                .append(propertyName).toString();
        }
    }

    private class SelectFieldWrapper extends InputFieldWrapper<SelectField> implements SelectField {
        public SelectFieldWrapper(SelectField src, RepeatingFieldGroup group) {
            super(src, group);
        }

        public Map<Object, Object> getOptions() {
            return src.getOptions();
        }
    }

    public static interface DisplayNameCreator {
        String createDisplayName(int index);
    }
}
