package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import java.util.Map;

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
        fieldFactory.addField(InputFieldFactory.createTextField("name", "Lab test name", true));
        fieldFactory.addField(InputFieldFactory.createTextField("units", "Units", true));
        addLabValueFields("baseline", "Baseline");
        addLabValueFields("nadir", "Worst");
        addLabValueFields("recovery", "Recovery");
    }

    private void addLabValueFields(String propName, String displayName) {
        fieldFactory.addField(InputFieldFactory.createTextField(propName + ".value", displayName + " value", false));
        fieldFactory.addField(InputFieldFactory.createDateField(propName + ".date", displayName + " date", false));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroupMap groups = new InputFieldGroupMap();
        groups.addRepeatingFieldGroupFactory(fieldFactory, command.getAeReport().getLabs().size());
        return groups;
    }
    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.LABS_SECTION;
    }
}
