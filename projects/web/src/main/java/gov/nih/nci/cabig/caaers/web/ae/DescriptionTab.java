package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createBooleanSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createDateField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextArea;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class DescriptionTab extends AeTab {

    public DescriptionTab() {
        super("Event and response description", ExpeditedReportSection.DESCRIPTION_SECTION
                        .getDisplayName(), "ae/description");
        setAutoPopulateHelpKey(true);
        addHelpKeyExclusion("presentStatus", "recoveryDate", "retreated");
    }
    
    
    @Override
    protected void createFieldGroups(AeInputFieldCreator creator,
                    ExpeditedAdverseEventInputCommand command) {
        String baseProp = "responseDescription";

        InputField desc = createTextArea(baseProp + ".eventDescription", "Description", false);
        InputFieldAttributes.setColumns(desc, 70);
        InputFieldAttributes.setRows(desc, 8);

        Map<Object, Object> statusOpts = new LinkedHashMap<Object, Object>();
        statusOpts.put("", "Please select");
        statusOpts.putAll(WebUtils.collectOptions(Arrays.asList(PostAdverseEventStatus.values()), null,
                        "displayName"));

        InputField removedDateField = createDateField(baseProp + ".dateRemovedFromProtocol",
                        "Date removed from protocol", false);

        creator.createFieldGroup("desc", desc, 
        		createSelectField(baseProp + ".presentStatus","Present status", false, statusOpts), 
        		createDateField(baseProp + ".recoveryDate", "Date of recovery or death", false),
                createBooleanSelectField(baseProp + ".retreated","Has the participant been re-treated?", false),
                removedDateField);

        InputField reducedDose = InputFieldFactory.createTextField(baseProp + ".reducedDose","If reduced, specify: New dose", false);

        creator.createFieldGroup("DCP_INFO",
        		createSelectField(baseProp + ".blindBroken","Was blind broken due to event?", false,createBooleanOptions()),
                createSelectField(baseProp + ".studyDrugInterrupted","Was Study Drug stopped/interrupted/reduced in response to event?",false, createBooleanOptions()),
                reducedDose,
                createDateField(baseProp + ".reducedDate","Date dose reduced", false),
                InputFieldFactory.createTextField(baseProp + ".daysNotGiven","If interrupted, specify total number of days not given",false),
                InputFieldFactory.createCheckboxField(baseProp + ".autopsyPerformed", "Autopsy performed?"),
                InputFieldFactory.createTextField(baseProp + ".causeOfDeath", "Cause of death"),
                createSelectField(baseProp + ".eventAbate","Did event abate after study drug was stopped or dose reduced?",false, createBooleanOptions()),
                createSelectField(baseProp + ".eventReappear","Did event reappear after study drug was reintroduced?",false, createBooleanOptions()));
    }

    private Map<Object, Object> createBooleanOptions() {
        Map<Object, Object> expectedOptions = new LinkedHashMap<Object, Object>();
        expectedOptions.put("", "Please select");
        expectedOptions.put(Boolean.TRUE, "Yes");
        expectedOptions.put(Boolean.FALSE, "No");
        return expectedOptions;
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.DESCRIPTION_SECTION;
    }

}
