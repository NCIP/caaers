package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextField;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

/**
 * @author Krikor Krumlian
 */
public class SurgeryInterventionTab extends AeTab {
    public SurgeryInterventionTab() {
        super("Surgery Intervention", ExpeditedReportSection.SURGERY_INTERVENTION_SECTION
                        .getDisplayName(), "ae/surgeryIntervention");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {

        String code = command.getAeReport().getTreatmentInformation().getTreatmentAssignment() != null ? command
                        .getAeReport().getTreatmentInformation().getTreatmentAssignment().getCode()
                        : null;

        String description = code != null ? command.getAeReport().getTreatmentInformation()
                        .getTreatmentAssignmentDescription() : command.getAeReport()
                        .getTreatmentInformation().getTreatmentDescription();

        InputField descField = InputFieldFactory.createTextArea("description",
                        "Treatment arm description", false);
        InputFieldAttributes.setColumns(descField, 45);
        InputFieldAttributes.setDetails(descField, description);

        InputField codeField = createTextField("treatmentArm", "Treatment arm", false);
        InputFieldAttributes.setDetails(codeField, code);

        creator.createRepeatingFieldGroup("surgeryIntervention", "surgeryInterventions",
                        new SimpleNumericDisplayNameCreator("Surgery"), codeField, descField,
                        InputFieldFactory.createAutocompleterField("interventionSite",
                                        "Intervention site", false), InputFieldFactory
                                        .createPastDateField("interventionDate",
                                                        "Date of intervention", false));
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.SURGERY_INTERVENTION_SECTION};
    }
}
