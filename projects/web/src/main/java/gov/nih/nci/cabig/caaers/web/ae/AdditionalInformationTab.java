package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;

import java.util.Map;
import java.util.Arrays;


/**
 * @author Krikor Krumlian
 */
public class AdditionalInformationTab extends AeTab {

    public AdditionalInformationTab() {
        super("Additional Information", "Additional Info", "ae/additionalInformation");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(ExpeditedAdverseEventInputCommand command) {
    	InputFieldGroup allFields = new DefaultInputFieldGroup("desc");
        String baseProp = "aeReport.additionalInformation";

        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".autopsyReport", "Autopsy Report", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".consults", "Consults", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".dischargeSummary", "Discharge Summary", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".flowCharts", "Flow Sheets/Case Report Forms", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".labReports", "Laboratory Reports", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".obaForm", "OBA Form", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".other", "Other", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".pathologyReport", "Pathology Report", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".progressNotes", "Progress Notes", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".radiologyReports", "Radiology Report", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".referralLetters", "Referral Letters", false));
        allFields.getFields().add(InputFieldFactory.createBooleanSelectField(baseProp + ".irbReport", "Summary Report Sent to IRB", false));
        allFields.getFields().add(InputFieldFactory.createTextArea(baseProp + ".otherInformation", "Other Information", false));

    	return createFieldGroupMap(Arrays.asList(allFields));
    }

    @Override
    public ExpeditedReportSection section() {
    	return ExpeditedReportSection.ADDITIONAL_INFO_SECTION;
    }
}
