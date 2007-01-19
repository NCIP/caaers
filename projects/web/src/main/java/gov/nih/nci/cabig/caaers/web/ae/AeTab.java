package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.tabbedflow.Tab;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author Rhett Sutphin
*/
public class AeTab extends Tab<CreateAdverseEventCommand> {
    private Map<String, List<InputField>> fieldGroups = new LinkedHashMap<String, List<InputField>>();

    public AeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
        initFields();
    }

    protected void initFields() {
    }

    public void addField(String group, InputField field) {
        if (!getFieldGroups().containsKey(group)) {
            fieldGroups.put(group, new LinkedList<InputField>());
        }
        fieldGroups.get(group).add(field);
    }

    public Map<String, List<InputField>> getFieldGroups() {
        return fieldGroups;
    }
}
