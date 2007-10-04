package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.*;

import java.util.Arrays;


/**
 * @author Krikor Krumlian
 */
public class AdditionalInformationTab extends AeTab {

    public AdditionalInformationTab() {
        super("Additional Information", "Attachments", "ae/additionalInformation");
    }

    @Override
    public InputFieldGroupMap createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        InputFieldGroup allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.additionalInformation";

        allFields.getFields().add(createBooleanSelectField(baseProp + ".autopsyReport", "Autopsy Report", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".consults", "Consults", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".dischargeSummary", "Discharge Summary", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".flowCharts", "Flow Sheets/Case Report Forms", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".labReports", "Laboratory Reports", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".obaForm", "OBA Form", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".other", "Other", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".pathologyReport", "Pathology Report", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".progressNotes", "Progress Notes", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".radiologyReports", "Radiology Report", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".referralLetters", "Referral Letters", false));
        allFields.getFields().add(createBooleanSelectField(baseProp + ".irbReport", "Summary Report Sent to IRB", false));
        InputField otherInfoField = createTextArea(baseProp + ".otherInformation", "Other Information", false);
        InputFieldAttributes.setColumns(otherInfoField,45);
        allFields.getFields().add(otherInfoField);

        return InputFieldGroupMap.create(allFields);
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.ADDITIONAL_INFO_SECTION;
    }
}
