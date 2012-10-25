package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocument;
import gov.nih.nci.cabig.caaers.domain.AdditionalInformationDocumentType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.TreeNode;
import gov.nih.nci.cabig.caaers.service.adverseevent.AdditionalInformationDocumentService;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createCheckboxField;
import static gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory.createTextArea;


/**
 * @author Krikor Krumlian
 */
public class AdditionalInformationTab extends AeTab {

    private AdditionalInformationDocumentService additionalInformationDocumentService;

    public AdditionalInformationTab() {
        super("Additional Information", ExpeditedReportSection.ADDITIONAL_INFO_SECTION.getDisplayName(), "ae/additionalInformation");
    }


    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ExpeditedAdverseEventInputCommand command) {
        Map<String, Object> refdata = super.referenceData(command);

        Map<String, List<AdditionalInformationDocument>> documents = new HashMap<String, List<AdditionalInformationDocument>>();

        Integer additionalInformationId = command.getAeReport().getAdditionalInformation().getId();
        List<AdditionalInformationDocument> additionalInformationDocuments = additionalInformationDocumentService.
                findByAdditionalInformationId(additionalInformationId);


        String baseName = "aeReport.additionalInformation";

        for (final AdditionalInformationDocumentType documentType : AdditionalInformationDocumentType.values()) {
            List<AdditionalInformationDocument> select =  new ArrayList<AdditionalInformationDocument>();

            CollectionUtils.select(additionalInformationDocuments, new Predicate() {

                public boolean evaluate(Object o) {
                    AdditionalInformationDocument additionalInformationDocument = (AdditionalInformationDocument) o;
                    return additionalInformationDocument.getAdditionalInformationDocumentType().equals(documentType);
                }
            }, select);

            documents.put(String.format("%s.%s", baseName, documentType.getCode()),select);
        }

       refdata.put("documents",documents);
       refdata.put("additionalInformationId",additionalInformationId);

        return refdata;
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
        return new ExpeditedReportSection[]{ExpeditedReportSection.ADDITIONAL_INFO_SECTION};
    }

    @Override
    public boolean hasEmptyMandatoryFields(ExpeditedAdverseEventInputCommand command, HttpServletRequest request) {
        boolean emptyMandatoryField = super.hasEmptyMandatoryFields(command, request);
        boolean allFieldsUnfilled = true;

        TreeNode aiNode = expeditedReportTree.find("additionalInformation");
        for (TreeNode aNode : aiNode.getChildren()) {
            allFieldsUnfilled &= (aNode.getPropertyValuesFrom(command.getAeReport()).getPropertyValues()[0].getValue() == null);
        }
        return emptyMandatoryField || allFieldsUnfilled;
    }


    public AdditionalInformationDocumentService getAdditionalInformationDocumentService() {
        return additionalInformationDocumentService;
    }

    public void setAdditionalInformationDocumentService(AdditionalInformationDocumentService additionalInformationDocumentService) {
        this.additionalInformationDocumentService = additionalInformationDocumentService;
    }
}
