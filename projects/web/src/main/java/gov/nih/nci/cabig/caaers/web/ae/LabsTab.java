package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;

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
    protected List<InputFieldGroup> createFieldGroups(AdverseEventInputCommand command) {
        List<InputFieldGroup> groups = new LinkedList<InputFieldGroup>();
        while (groups.size() < command.getAeReport().getLabs().size()) {
            groups.add(fieldFactory.createGroup(groups.size()));
        }
        return groups;
    }
}
