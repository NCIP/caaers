package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;


/**
 * @author Krikor Krumlian
 */
public class SurgeryInterventionTab extends AeTab {
    public SurgeryInterventionTab() {
        super("Surgery Intervention", "Surgery", "ae/surgeryIntervention");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField descField = InputFieldFactory.createTextArea("description", "Treatment arm description", false);
        InputFieldAttributes.setColumns(descField, 45);

        creator.createRepeatingFieldGroup("surgeryIntervention", "surgeryInterventions",
            new SimpleNumericDisplayNameCreator("Surgery"),
            InputFieldFactory.createTextField("treatmentArm", "Treatment arm", false),
            descField,
            InputFieldFactory.createAutocompleterField("anatomicSite", "Intervention site", false),
            InputFieldFactory.createDateField("interventionDate", "Date of intervention",  false)
        );
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.SURGERY_INTERVENTION_SECTION;
    }
}
