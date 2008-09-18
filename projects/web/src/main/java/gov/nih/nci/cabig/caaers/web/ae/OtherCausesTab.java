package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

/**
 * @author Rhett Sutphin
 */
public class OtherCausesTab extends AeTab {
    public OtherCausesTab() {
        super("Other contributing causes", ExpeditedReportSection.OTHER_CAUSE_SECTION
                        .getDisplayName(), "ae/other");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        InputField otherField = InputFieldFactory.createTextArea("text", "Cause", false);
        InputFieldAttributes.setColumns(otherField, 50);

        creator.createRepeatingFieldGroup("otherCause", "otherCauses",
                        new SimpleNumericDisplayNameCreator("Other Cause"), otherField);
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.OTHER_CAUSE_SECTION};
    }
}
