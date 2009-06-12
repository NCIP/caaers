package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Rhett Sutphin
 */
public class OtherCausesTab extends AeTab {
    public OtherCausesTab() {
        super("Other contributing causes", ExpeditedReportSection.OTHER_CAUSE_SECTION.getDisplayName(), "ae/other");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField otherField = InputFieldFactory.createTextArea("text", "Cause", true);
        InputFieldAttributes.setColumns(otherField, 50);
        creator.createRepeatingFieldGroup("otherCause", "otherCauses", new SimpleNumericDisplayNameCreator("Other Cause"), otherField);
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.OTHER_CAUSE_SECTION};
    }
    
    @Override
    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command, HttpServletRequest request) {
    	boolean hasEmptyFields =  super.hasEmptyMandatoryFields(command, request);
    	hasEmptyFields |= CollectionUtils.isEmpty(command.getAeReport().getOtherCauses());
    	return hasEmptyFields;
    }
}
