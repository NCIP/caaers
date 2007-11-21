package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createBooleanSelectField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextArea;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;


/**
 * @author Krikor Krumlian
 */
public class AdditionalInformationTab extends AeTab {

    public AdditionalInformationTab() {
        super("Additional Information", ExpeditedReportSection.ADDITIONAL_INFO_SECTION.getDisplayName(), "ae/additionalInformation");
    }

    @Override
    protected void createFieldGroups(AeInputFieldCreator creator, ExpeditedAdverseEventInputCommand command) {
        InputField otherInfoField = createTextArea("otherInformation", "Other Information", false);
        InputFieldAttributes.setColumns(otherInfoField,70);
        InputFieldAttributes.setRows(otherInfoField,5);
        InputFieldAttributes.setDetails(otherInfoField, "If the additional information being provided" +
        		" is not listed above,type the information being provided in the &quot;" +
        		"Other Information&quot; field.Separate each item with a comma &quot;,&quot;.");

        creator.createFieldGroup("desc", null, "additionalInformation",
            createBooleanSelectField("autopsyReport", "Autopsy Report", false),
            createBooleanSelectField("consults", "Consults", false),
            createBooleanSelectField("dischargeSummary", "Discharge Summary", false),
            createBooleanSelectField("flowCharts", "Flow Sheets/Case Report Forms", false),
            createBooleanSelectField("labReports", "Laboratory Reports", false),
            createBooleanSelectField("obaForm", "OBA Form", false),
            createBooleanSelectField("other", "Other", false),
            createBooleanSelectField("pathologyReport", "Pathology Report", false),
            createBooleanSelectField("progressNotes", "Progress Notes", false),
            createBooleanSelectField("radiologyReports", "Radiology Report", false),
            createBooleanSelectField("referralLetters", "Referral Letters", false),
            createBooleanSelectField("irbReport", "Summary Report Sent to IRB", false),
            otherInfoField
        );
    }

    @Override
    public ExpeditedReportSection section() {
        return ExpeditedReportSection.ADDITIONAL_INFO_SECTION;
    }
}
