package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.collectOptions;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createBooleanSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createDateField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextArea;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class DescriptionTab extends AeTab {

    public DescriptionTab() {
        super("Event and response description",
        	ExpeditedReportSection.DESCRIPTION_SECTION.getDisplayName(),
        	"ae/description");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        String baseProp = "responseDescription";

        InputField desc = createTextArea(baseProp + ".eventDescription",
            "Description", false);
        InputFieldAttributes.setColumns(desc, 70);
        InputFieldAttributes.setRows(desc, 8);
        InputFieldAttributes.setDetails(desc,"Type in a narrative describing the following: (1) presentation of the event, (2), clinical findings, (3) the treatment of the events, and (4) the timing of events related to agent administration or investigational intervention.");

        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(collectOptions(
            Arrays.asList(PostAdverseEventStatus.values()), null, "displayName"));

        InputField removedDateField = createDateField(baseProp + ".dateRemovedFromProtocol",
            "Date removed from protocol", false);
        InputFieldAttributes.setDetails(removedDateField, "If the participant was removed from the protocol, enter the date here.  Otherwise, leave it blank.");

        creator.createFieldGroup("desc",
            desc,
            createSelectField(baseProp + ".presentStatus", "Present status",
                false, statusOpts),
            createDateField(baseProp + ".recoveryDate", "Date of recovery or death",  false),
            createBooleanSelectField(baseProp + ".retreated", "Has the particpant been re-treated?", false),
            removedDateField
        );
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.DESCRIPTION_SECTION;
    }
}
