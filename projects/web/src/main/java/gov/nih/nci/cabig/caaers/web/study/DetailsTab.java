package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Design;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ReportFormat;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyTherapy;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 * @author Ion C. Olaru
 */
public class DetailsTab extends StudyTab {

    private CtcDao ctcDao;
    private MeddraVersionDao meddraVersionDao;
    InputFieldGroup fieldGroup, reportFormatFieldGroup, fundSponsorFieldGroup, coordinatingCenterFieldGroup, dcpCodeFieldGroup;

    public DetailsTab() {
        super("Details", "Details", "study/study_details");
        setAutoPopulateHelpKey(true);
        /*addHelpKeyExclusion("multiInstitutionIndicator", "adeersReporting", "term", "ctcVersion", "meddraVersion", "diseaseCodeTerm");*/
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, Study command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        // TODO : to be removed from reference data, as they are no longer used.
        refdata.put("ctcVersion", ctcDao.getAll()); // remove
        return refdata;
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final Study command) {
        if (fieldGroup == null) {
            // set up the fields
            fieldGroup = new DefaultInputFieldGroup("studyDetails");
            List<InputField> fields = fieldGroup.getFields();
            InputField shortTitleField = InputFieldFactory.createTextField("shortTitle", "Short title", true);
            InputFieldAttributes.setSize(shortTitleField, 50);
            fields.add(shortTitleField);
            InputField longTitleField = InputFieldFactory.createTextArea("longTitle", "Long title", true);
            InputFieldAttributes.setColumns(longTitleField, 70);
            fields.add(longTitleField);
            InputField precisField = InputFieldFactory.createTextArea("precis", "Precis", false);
            InputFieldAttributes.setColumns(precisField, 70);
            fields.add(precisField);
            InputField descField = InputFieldFactory.createTextArea("description", "Description", false);
            InputFieldAttributes.setColumns(descField, 70);

            fields.add(descField);
            fields.add(InputFieldFactory.createSelectField("phaseCode", "Phase", true, collectOptionsFromConfig("phaseCodeRefData", "desc", "desc")));
            fields.add(InputFieldFactory.createSelectField("status", "Status", true, collectOptionsFromConfig("statusRefData", "code", "desc")));
            Map<Object, Object> options = new LinkedHashMap<Object, Object>();
            options.put("", "Please select");
            options.put(Boolean.FALSE, "No");
            options.put(Boolean.TRUE, "Yes");
            fields.add(InputFieldFactory.createSelectField("multiInstitutionIndicator", "Multi Institutional", true, options));
            fields.add(InputFieldFactory.createSelectField("adeersReporting", "AdEERS  reporting required", true, options));

        }

        if (reportFormatFieldGroup == null) {
            reportFormatFieldGroup = new DefaultInputFieldGroup("rfFieldGroup");
            List<InputField> fields = reportFormatFieldGroup.getFields();

            InputField caaersXMLTypeField = InputFieldFactory.createCheckboxField("caaersXMLType", "caAERS XML");
            InputFieldAttributes.setSize(caaersXMLTypeField, 50);
            fields.add(caaersXMLTypeField);

            InputField adeersPDFTypeField = InputFieldFactory.createCheckboxField("adeersPDFType", "AdEERS PDF");
            InputFieldAttributes.setSize(adeersPDFTypeField, 50);
            fields.add(adeersPDFTypeField);

            InputField ciomsPDFTypeField = InputFieldFactory.createCheckboxField("ciomsPDFType", "CIOMS Form");
            InputFieldAttributes.setSize(ciomsPDFTypeField, 50);
            fields.add(ciomsPDFTypeField);

            InputField ciomsSaePDFTypeField = InputFieldFactory.createCheckboxField("ciomsSaePDFType", "CIOMS SAE Form");
            InputFieldAttributes.setSize(ciomsSaePDFTypeField, 50);
            fields.add(ciomsSaePDFTypeField);

            InputField dcpSAEPDFTypeField = InputFieldFactory.createCheckboxField("dcpSAEPDFType", "DCP SAE Form");
            InputFieldAttributes.setSize(dcpSAEPDFTypeField, 50);
            fields.add(dcpSAEPDFTypeField);

            InputField medwatchPDFTypeField = InputFieldFactory.createCheckboxField("medwatchPDFType", "MedWatch PDF");
            InputFieldAttributes.setSize(medwatchPDFTypeField, 50);
            fields.add(medwatchPDFTypeField);
        }

        if (fundSponsorFieldGroup == null) {
            fundSponsorFieldGroup = new DefaultInputFieldGroup("fsFieldGroup");
            List<InputField> fields = fundSponsorFieldGroup.getFields();
            InputField sponsorField = InputFieldFactory.createAutocompleterField("primaryFundingSponsorOrganization", "Funding sponsor", true);
            // sponsorField.getAttributes().put(InputField.DETAILS,"Enter a portion of the sponsor
            // name you are looking for");
            fields.add(sponsorField);
            InputField sponsorIdentiferField = InputFieldFactory.createTextField("identifiers[0].value", "Funding sponsor study identifier", true);
            fields.add(sponsorIdentiferField);

        }
        if (coordinatingCenterFieldGroup == null) {

            coordinatingCenterFieldGroup = new DefaultInputFieldGroup("ccFieldGroup");
            List<InputField> fields = coordinatingCenterFieldGroup.getFields();
            fields.add(InputFieldFactory.createAutocompleterField("studyCoordinatingCenter.organization", "Coordinating center", true));
            fields.add(InputFieldFactory.createTextField("identifiers[1].value", "Coordinating center study identifier", true));

        }
        // Create fieldGroup for AeTerminology
        InputFieldGroup studyCodeFieldGroup = new DefaultInputFieldGroup("scFieldGroup");
        List<InputField> scFields = studyCodeFieldGroup.getFields();
        scFields.add(InputFieldFactory.createSelectField("aeTerminology.term", "Terminology", true, WebUtils.collectOptions(Arrays.asList(Term.values()), null, "displayName")));

        // TODO: Add validation for when terminology.term = Term.CTC
        List<Ctc> ctcList = ctcDao.getAll();
        for(Ctc ctc : ctcList){
        	ctc.getCategories().size();
        }

        scFields.add(InputFieldFactory.createSelectField("aeTerminology.ctcVersion", "CTC version", false, collectOptions(ctcList, "id", "name")));
        scFields.add(InputFieldFactory.createSelectField("aeTerminology.meddraVersion", "MedDRA version", false, collectOptions(meddraVersionDao.getAll(), "id", "name")));
        scFields.add(InputFieldFactory.createSelectField("otherMeddra", "Other MedDRA Version", false, collectOptions(meddraVersionDao.getAll(),"id", "name")));
        
        // Create fieldGroup for DiseaseTerminology
        InputFieldGroup studyDiseaseCodeFieldGroup = new DefaultInputFieldGroup("sdcFieldGroup");
        List<InputField> sdFields = studyDiseaseCodeFieldGroup.getFields();
        sdFields.add(InputFieldFactory.createSelectField("diseaseTerminology.diseaseCodeTerm", "Terminology", true, WebUtils.collectOptions(Arrays.asList(DiseaseCodeTerm.values()), null, "displayName")));
        sdFields.add(InputFieldFactory.createSelectField("diseaseTerminology.meddraVersion", "MedDRA version", false, collectOptions(meddraVersionDao.getAll(), "id", "name")));

        if (dcpCodeFieldGroup == null) {
            dcpCodeFieldGroup = new DefaultInputFieldGroup("dcpFieldGroup");
            List<InputField> fields = dcpCodeFieldGroup.getFields();
            Map<Object, Object> designOpts = new LinkedHashMap<Object, Object>();
            designOpts.put("", "Please select");
            designOpts.putAll(collectOptions(Arrays.asList(Design.values()), null, "displayName"));
            fields.add(InputFieldFactory.createSelectField("design", "Study design", false, designOpts));
        }

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(fieldGroup);
        map.addInputFieldGroup(reportFormatFieldGroup);
        map.addInputFieldGroup(fundSponsorFieldGroup);
        map.addInputFieldGroup(coordinatingCenterFieldGroup);
        map.addInputFieldGroup(studyCodeFieldGroup);
        map.addInputFieldGroup(studyDiseaseCodeFieldGroup);
        map.addInputFieldGroup(dcpCodeFieldGroup);

        return map;
    }

    @Override
    protected void validate(final Study command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {

        // Adverse event coding terminology
        if (command.getAeTerminology().getTerm() == Term.MEDDRA && command.getAeTerminology().getMeddraVersion() == null) {
            InputField field = fieldGroups.get("scFieldGroup").getFields().get(2);
            errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
        }

        if (command.getAeTerminology().getTerm() == Term.CTC) {

            if (command.getAeTerminology().getCtcVersion() == null) {
                InputField field = fieldGroups.get("scFieldGroup").getFields().get(1);
                errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
            }

            if (command.getOtherMeddra() == null) {
                InputField field = fieldGroups.get("scFieldGroup").getFields().get(3);
                errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
            }
        }

        // Disease coding terminology
        // This is to validate if meddra version is selected incase the disease term is of type meddra
        if (command.getDiseaseTerminology().getDiseaseCodeTerm() == DiseaseCodeTerm.MEDDRA && command.getDiseaseTerminology().getMeddraVersion() == null) {
        	InputField field = fieldGroups.get("sdcFieldGroup").getFields().get(1);
        	errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
        }
    }

    @Override
    public void postProcess(final HttpServletRequest request, final Study command, final Errors errors) {

        System.out.println("term="+command.getDiseaseTerminology().getDiseaseCodeTerm().getDisplayName());
        super.postProcess(request, command, errors);
        if (errors.hasErrors()) {
            return;
        }

        for (int i = 0; i <= 1; i++) {
            OrganizationAssignedIdentifier identifier = (OrganizationAssignedIdentifier) command.getIdentifiers().get(i);
            if (identifier.getType().equals(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE)) {
                identifier.setOrganization(command.getPrimaryFundingSponsorOrganization());
            }
            if (identifier.getType().equals(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)) {
                identifier.setOrganization(command.getStudyCoordinatingCenter().getOrganization());
            }
        }

    }

    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public void setCtcDao(final CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public MeddraVersionDao getMeddraVersionDao() {
        return meddraVersionDao;
    }

    public void setMeddraVersionDao(final MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }
}
