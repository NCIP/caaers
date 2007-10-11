package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.ListIterator;

import gov.nih.nci.cabig.caaers.domain.AdverseEventPriorTherapy;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

/**
 * @author Rhett Sutphin
 */
public class PriorTherapyTab extends AeTab {

    public PriorTherapyTab() {
        super("Prior Therapies", "Prior Therapies", "ae/priorTherapies");

    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField priorTherapyField = InputFieldFactory.createAutocompleterField("priorTherapy", "Prior therapy", false);
        InputFieldAttributes.setDetails(priorTherapyField, "If the appropriate therapy is not listed, type the therapy in the &quot;Comments(prior therapy)&quot; field below.");
        //TODO: change the name of other to "comment"
        InputField otherField = InputFieldFactory.createTextArea("other", "Comments (prior therapy)", false);
        InputFieldAttributes.setColumns(otherField, 50);
        InputField startDateField = InputFieldFactory.createDateField("startDate", "Therapy start Date", false);
        InputFieldAttributes.setDetails(startDateField, "If known, enter start date for prior therapy.");
        InputField endDateField = InputFieldFactory.createDateField("endDate", "Therapy end date", false);
        InputFieldAttributes.setDetails(endDateField, "If known, enter end date for prior therapy.");

        creator.createRepeatingFieldGroup("priorTherapy", "adverseEventPriorTherapies",
            new SimpleNumericDisplayNameCreator("Prior therapy"),
            priorTherapyField,
            otherField,
            startDateField,
            endDateField,
            InputFieldFactory.createAutocompleterField("priorTherapyAgents.agent", "Agent", false)
        );

        creator.createRepeatingFieldGroup("ptAgent", "adverseEventPriorTherapies");
        // There were no fields in this group when I converted it.  Don't know what it's for.  RMS20071005.
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        for (ListIterator<AdverseEventPriorTherapy> it = command.getAeReport().getAdverseEventPriorTherapies().listIterator(); it.hasNext();) {
            AdverseEventPriorTherapy aePriorTherapy = it.next();
            validatePriorTherapy(aePriorTherapy, it.previousIndex(), errors);
        }
    }

    private void validatePriorTherapy(AdverseEventPriorTherapy aePriorTherapy, int index, Errors errors) {
//        if (aePriorTherapy.getPriorTherapy() == null && aePriorTherapy.getOther() == null) {
//            errors.rejectValue(
//                String.format("aeReport.adverseEventPriorTherapies[%d]", index),
//                "REQUIRED",
//                "Either Prior Therapy or Other is required"
//            );
//        }
    }
    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.PRIOR_THERAPIES_SECTION;
    }
}
