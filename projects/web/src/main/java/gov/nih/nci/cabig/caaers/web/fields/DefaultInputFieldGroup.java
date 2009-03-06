package gov.nih.nci.cabig.caaers.web.fields;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class DefaultInputFieldGroup extends AbstractInputFieldGroup {
    private List<InputField> fields = new LinkedList<InputField>();

    public DefaultInputFieldGroup() {
    }

    public DefaultInputFieldGroup(String name) {
        super(name);
    }

    public DefaultInputFieldGroup(String name, String displayName) {
        super(name, displayName);
    }

    public DefaultInputFieldGroup addField(InputField newField) {
        getFields().add(newField);
        return this;
    }

    public List<InputField> getFields() {
        return fields;
    }

    public void setFields(List<InputField> fields) {
        this.fields = fields;
    }
}
