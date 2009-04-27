package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createCheckboxField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextArea;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
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
        	createCheckboxField("autopsyReport", "Autopsy Report"),
        	createCheckboxField("consults", "Consults"),
            createCheckboxField("dischargeSummary", "Discharge Summary"),
            createCheckboxField("flowCharts", "Flow Sheets/Case Report Forms"),
            createCheckboxField("labReports", "Laboratory Reports"),
            createCheckboxField("obaForm", "OBA Form"),
            createCheckboxField("pathologyReport", "Pathology Report"),
            createCheckboxField("progressNotes", "Progress Notes"),
            createCheckboxField("radiologyReports", "Radiology Report"),
            createCheckboxField("referralLetters", "Referral Letters"),
            createCheckboxField("irbReport", "Summary Report Sent to IRB"),
            createCheckboxField("other", "Other"),
            otherInfoField
        );
    }

    @Override
    public ExpeditedReportSection[] section() {
        return new ExpeditedReportSection[] {ExpeditedReportSection.ADDITIONAL_INFO_SECTION};
    }
    
    @Override
    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command,HttpServletRequest request) {
    	boolean emptyMandatoryField = super.hasEmptyMandatoryFields(command, request);
    	boolean allFieldsUnfilled = true;
    	
    	TreeNode aiNode = expeditedReportTree.find("additionalInformation");
    	for(TreeNode aNode : aiNode.getChildren()){
    		allFieldsUnfilled &= (aNode.getPropertyValuesFrom(command.getAeReport()).getPropertyValues()[0].getValue() == null);
    	}
    	return emptyMandatoryField || allFieldsUnfilled;
    }
}
