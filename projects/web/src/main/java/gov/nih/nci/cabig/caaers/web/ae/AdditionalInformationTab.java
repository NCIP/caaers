package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.fields.BooleanSelectField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextArea;

import java.util.Map;
import java.util.Arrays;


/**
 * @author Krikor Krumlian
 */
public class AdditionalInformationTab extends AeTab {
    private InputFieldGroup allFields;

    public AdditionalInformationTab() {
        super("Additional Information", "Additional Info", "ae/additionalInformation");
        allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.additionalInformation";

        allFields.getFields().add(new BooleanSelectField(baseProp + ".autopsyReport", "Autopsy Report", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".consults", "Consults", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".dischargeSummary", "Discharge Summary", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".flowCharts", "Flow Sheets/Case Report Forms", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".labReports", "Laboratory Reports", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".obaForm", "OBA Form", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".other", "Other", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".pathologyReport", "Pathology Report", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".progressNotes", "Progress Notes", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".radiologyReports", "Radiology Report", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".referralLetters", "Referral Letters", false));
        allFields.getFields().add(new BooleanSelectField(baseProp + ".irbReport", "Summary Report Sent to IRB", false));
        allFields.getFields().add(new DefaultTextArea(baseProp + ".otherInformation", "Other Information", false));
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
        return createFieldGroupMap(Arrays.asList(allFields));
    }
}
