package gov.nih.nci.cabig.caaers.web.ae;

import java.util.List;
import java.util.LinkedList;

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

    public List<InputField> getFields() {
        return fields;
    }

    public void setFields(List<InputField> fields) {
        this.fields = fields;
    }
}
