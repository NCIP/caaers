package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

/**
 * @author Rhett Sutphin
 */
public class LabsTab extends AeTab {
    private ConfigProperty configurationProperty;

    public LabsTab() {
        super("Diagnostic test and lab results", "Labs", "ae/labs");
    }

    private void addLabValueFields(RepeatingFieldGroupFactory fieldFactory, String propName, String displayName) {
        fieldFactory.addField(createLabValueField(propName, displayName));
        fieldFactory.addField(createLabDateField(propName, displayName));
    }

    private InputField createLabDateField(String propName, String displayName) {
        return InputFieldFactory.createDateField(propName
            + ".date", displayName + " date", false);
    }

    private InputField createLabValueField(String propName, String displayName) {
        return InputFieldFactory.createTextField(propName
            + ".value", displayName + " value", false);
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField otherField = InputFieldFactory.createTextField("other", "Other", false);
        InputFieldAttributes.setSize(otherField, 50);

        creator.createRepeatingFieldGroup("lab", "labs",
            createNameCreator(),
            InputFieldFactory.createAutocompleterField("name", "Lab test name"),
            otherField,
            InputFieldFactory.createSelectField("units",
                "Units", false,
                InputFieldFactory.collectOptions(configurationProperty.getMap().get("labUnitsRefData"),
                    "code", "desc", "Please select")),
            createLabValueField("baseline", "Baseline"),
            createLabDateField( "baseline", "Baseline"),
            createLabValueField("nadir", "Worst"),
            createLabDateField( "nadir", "Worst"),
            createLabValueField("recovery", "Recovery"),
            createLabDateField( "recovery", "Recovery")
        );
    }

    private RepeatingFieldGroupFactory.DisplayNameCreator createNameCreator() {
        return new RepeatingFieldGroupFactory.DisplayNameCreator() {
            public String createDisplayName(int index) {
                char c = (char) ('A' + index);
                return "Lab " + c;
            }
        };
    }

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(ConfigProperty configurationProperty) {
        this.configurationProperty = configurationProperty;
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.LABS_SECTION;
    }
}
