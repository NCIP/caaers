package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import java.util.Map;
import java.util.ListIterator;

import gov.nih.nci.cabig.caaers.domain.AdverseEventPreExistingCond;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

/**
 * @author Krikor Krumlian
 */
public class PreExistingConditionsTab extends AeTab {

    public PreExistingConditionsTab() {
        super("Pre-Existing Conditions", ExpeditedReportSection.PRE_EXISTING_CONDITION_SECTION.getDisplayName(), "ae/preExistingConds");
    }

    // TODO: eventually, this will be abstract
    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField preCondField = InputFieldFactory.createAutocompleterField("preExistingCondition", "Pre-Existing condition", false);
        InputFieldAttributes.setDetails(preCondField, "If the correct term is not available in this list, type the pre-condition below in the &quot;Other (pre-existing)&quot; field.");
        InputFieldAttributes.enableAutoCompleterClearButton(preCondField);
        InputField otherField = InputFieldFactory.createTextField("other", "Other (pre-existing)", false);
        InputFieldAttributes.setSize(otherField, 50);

        creator.createRepeatingFieldGroup("conmed", "adverseEventPreExistingConds",
            new SimpleNumericDisplayNameCreator("Pre-existing condition"),
            preCondField,
            otherField
        );
    }

    @Override
    protected void validate(
        ExpeditedAdverseEventInputCommand command, BeanWrapper commandBean,
        Map<String, InputFieldGroup> fieldGroups, Errors errors
    ) {
        for (ListIterator<AdverseEventPreExistingCond> it = command.getAeReport().getAdverseEventPreExistingConds().listIterator(); it.hasNext();) {
        	AdverseEventPreExistingCond preCondition = it.next();
            validateAdverseEventPreExistingCond(preCondition, it.previousIndex(), errors);
        }
    }

    private void validateAdverseEventPreExistingCond(AdverseEventPreExistingCond preCondition, int index, Errors errors) {
        if (preCondition.getPreExistingCondition() == null && preCondition.getOther() == null) {
            errors.rejectValue(
                String.format("aeReport.adverseEventPreExistingConds[%d]", index),
                "REQUIRED",
                "Either a known pre Existing Condition or other is required"
            );
        }
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.PRE_EXISTING_CONDITION_SECTION;
    }
}
