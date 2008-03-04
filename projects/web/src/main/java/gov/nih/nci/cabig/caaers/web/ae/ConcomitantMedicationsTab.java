package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

/**
 * @author Rhett Sutphin
 */
public class ConcomitantMedicationsTab extends AeTab {

    public ConcomitantMedicationsTab() {
        super("Concomitant medications", ExpeditedReportSection.CONCOMITANT_MEDICATION_SECTION
                        .getDisplayName(), "ae/conMed");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        InputField agentNameField = InputFieldFactory.createTextField("agentName",
                        "Information about concomitant medication", false);
        InputFieldAttributes.setSize(agentNameField, 50);

        creator.createRepeatingFieldGroup("conmed", "concomitantMedications",
                        new SimpleNumericDisplayNameCreator("Medication"), agentNameField);
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.CONCOMITANT_MEDICATION_SECTION;
    }
}
