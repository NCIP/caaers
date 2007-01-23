package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * @author Rhett Sutphin
 */
public class LabsTab extends AeTab {
    private RepeatingFieldGroupFactory fieldFactory;

    public LabsTab() {
        super("Diagnostic test and lab results", "Labs", "ae/labs");
        fieldFactory = new RepeatingFieldGroupFactory("lab", "aeReport.labs");
        fieldFactory.setDisplayNameCreator(new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                char c = (char) ('A' + index);
                return "Lab " + c;
            }
        });
        fieldFactory.addField(new DefaultTextField("name", "Lab name", true));
        fieldFactory.addField(new DefaultTextField("units", "Units", true));
        addLabValueFields("baseline", "Baseline");
        addLabValueFields("nadir", "Nadir");
        addLabValueFields("recovery", "Recovery");
    }

    private void addLabValueFields(String propName, String displayName) {
        fieldFactory.addField(new DefaultTextField(propName + ".value", displayName + " value", false));
        fieldFactory.addField(new DefaultDateField(propName + ".date", displayName + " date", false));
    }

    @Override
    protected Map<String, InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        Map<String, InputFieldGroup> groups = new LinkedHashMap<String, InputFieldGroup>();
        while (groups.size() < command.getAeReport().getLabs().size()) {
            InputFieldGroup group = fieldFactory.createGroup(groups.size());
            groups.put(group.getName(), group);
        }
        return groups;
    }
}
