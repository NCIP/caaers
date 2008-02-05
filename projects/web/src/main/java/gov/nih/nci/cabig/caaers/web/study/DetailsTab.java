package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
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
 */
public class DetailsTab extends StudyTab {


	private CtcDao ctcDao;

	private MeddraVersionDao meddraVersionDao;

	InputFieldGroup fieldGroup, reportFormatFieldGroup, fundSponsorFieldGroup, studyCodeFieldGroup, studyDiseaseCodeFieldGroup,coordinatingCenterFieldGroup, dcpCodeFieldGroup;

	public DetailsTab() {
		super("Basic Details", "Details", "study/study_details");
		setAutoPopulateHelpKey(true);
	}

	@Override
	public Map<String, Object> referenceData() {
		Map<String, Object> refdata = super.referenceData();
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
			InputFieldAttributes.setColumns(longTitleField,70);
			fields.add(longTitleField);
			InputField precisField = InputFieldFactory.createTextArea("precis", "Precis", false);
			InputFieldAttributes.setColumns(precisField, 70);
			fields.add(precisField);
			InputField descField = InputFieldFactory.createTextArea("description", "Description", false);
			InputFieldAttributes.setColumns(descField, 70);
			
			fields.add(descField);
					fields.add(InputFieldFactory.createSelectField("phaseCode", "Phase", true, collectOptionsFromConfig(
					"phaseCodeRefData", "desc", "desc")));
			fields.add(InputFieldFactory.createSelectField("status", "Status", true, 
					collectOptionsFromConfig("statusRefData", "code", "desc")));
			Map<Object, Object> options = new LinkedHashMap<Object, Object>();
			options.put("", "Please select");
			options.put(Boolean.FALSE, "No");
			options.put(Boolean.TRUE, "Yes");
			fields.add(InputFieldFactory.createSelectField("multiInstitutionIndicator", "Multi Institutional",true, options));
			fields.add(InputFieldFactory.createSelectField("adeersReporting", "AdEERS  reporting required",true, options));
			
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
		
		if(fundSponsorFieldGroup == null){
			fundSponsorFieldGroup = new DefaultInputFieldGroup("fsFieldGroup");
			List<InputField> fields = fundSponsorFieldGroup.getFields();
			InputField sponsorField = InputFieldFactory.createAutocompleterField("primaryFundingSponsorOrganization",
					"Funding sponsor", true);
			// sponsorField.getAttributes().put(InputField.DETAILS,"Enter a portion of the sponsor name you are looking for");
			fields.add(sponsorField);
			InputField sponsorIdentiferField = InputFieldFactory.createTextField("identifiers[0].value", "Funding sponsor study identifier", true);
			fields.add(sponsorIdentiferField);
	
		}
		if ( coordinatingCenterFieldGroup == null) {

			coordinatingCenterFieldGroup = new DefaultInputFieldGroup("ccFieldGroup");
			List<InputField> fields = coordinatingCenterFieldGroup.getFields();
			fields.add(InputFieldFactory.createAutocompleterField(
					"studyCoordinatingCenter.organization", "Coordinating center", true));
			fields.add(InputFieldFactory.createTextField("identifiers[1].value",
					"Coordinating center study identifier", true));

		}
		if(studyCodeFieldGroup == null){
			studyCodeFieldGroup = new DefaultInputFieldGroup("scFieldGroup");
			List<InputField> fields = studyCodeFieldGroup.getFields();
			fields.add(InputFieldFactory.createSelectField("aeTerminology.term", "Terminology", true, InputFieldFactory
					.collectOptions(Arrays.asList(Term.values()), null, "displayName")));
		
			// TODO: Add validation for when terminology.term = Term.CTC
			fields.add(InputFieldFactory.createSelectField("aeTerminology.ctcVersion", "CTC version", false,
					collectOptions(ctcDao.getAll(), "id", "name")));
			fields.add(InputFieldFactory.createSelectField("aeTerminology.meddraVersion", "MedDRA version", false,
					collectOptions(meddraVersionDao.getAll(), "id", "name")));
		}
		
		if(studyDiseaseCodeFieldGroup == null){
			studyDiseaseCodeFieldGroup = new DefaultInputFieldGroup("sdcFieldGroup");
			List<InputField> fields = studyDiseaseCodeFieldGroup.getFields();
			fields.add(InputFieldFactory.createSelectField("diseaseTerminology.diseaseCodeTerm", "Terminology", true, InputFieldFactory
					.collectOptions(Arrays.asList(DiseaseCodeTerm.values()), null, "displayName")));
		}
		
		if(dcpCodeFieldGroup == null){
			dcpCodeFieldGroup = new DefaultInputFieldGroup("dcpFieldGroup");
			List<InputField> fields = dcpCodeFieldGroup.getFields();
			Map<Object, Object> designOpts = new LinkedHashMap<Object, Object>();
			designOpts.put("", "Please select");
			designOpts.putAll(collectOptions(
	            Arrays.asList(Design.values()), null, "displayName"));
			fields.add(InputFieldFactory.createSelectField("design", "Study design",false, designOpts));
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
	protected void validate(final Study command, final BeanWrapper commandBean,
			final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
		if (command.getAeTerminology().getTerm() == Term.MEDDRA && command.getAeTerminology().getMeddraVersion() == null) {
			InputField field = fieldGroups.get("scFieldGroup").getFields().get(1);
			errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
		}

		if (command.getAeTerminology().getTerm() == Term.CTC && command.getAeTerminology().getCtcVersion() == null) {
			InputField field = fieldGroups.get("scFieldGroup").getFields().get(0);
			errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
		}
    }

	@Override
	public void onBind(HttpServletRequest request, Study study, Errors errors) {
		super.onBind(request, study, errors);
		List<ReportFormat> reportFormats = study.getReportFormats();

		if (study.getAdeersPDFType()
				&& study.getReportFormat(ReportFormatType.ADEERSPDF) == null) {
			ReportFormat adeersPDFReportFormat = new ReportFormat();
			adeersPDFReportFormat.setStudy(study);
			adeersPDFReportFormat.setReportFormatType(ReportFormatType.ADEERSPDF);
			study.getReportFormats().add(adeersPDFReportFormat);
		}
		else if (!study.getAdeersPDFType()
				&& study.getReportFormat(ReportFormatType.ADEERSPDF) != null) {
			reportFormats.remove(study.getReportFormat(ReportFormatType.ADEERSPDF));
		}

		if (study.getCaaersXMLType()
				&& study.getReportFormat(ReportFormatType.CAAERSXML) == null) {
			ReportFormat caaersXMLFormat = new ReportFormat();
			caaersXMLFormat.setStudy(study);
			caaersXMLFormat.setReportFormatType(ReportFormatType.CAAERSXML);
			study.getReportFormats().add(caaersXMLFormat);
		}
		else if (!study.getCaaersXMLType()
				&& study.getReportFormat(ReportFormatType.CAAERSXML) != null) {
			reportFormats.remove(study.getReportFormat(ReportFormatType.CAAERSXML));
		}

		if (study.getCiomsPDFType()
				&& study.getReportFormat(ReportFormatType.CIOMSFORM) == null) {
			ReportFormat ciomsPDFFormat = new ReportFormat();
			ciomsPDFFormat.setStudy(study);
			ciomsPDFFormat.setReportFormatType(ReportFormatType.CIOMSFORM);
			study.getReportFormats().add(ciomsPDFFormat);
		}
		else if (!study.getCiomsPDFType()
				&& study.getReportFormat(ReportFormatType.CIOMSFORM) != null) {
			reportFormats.remove(study.getReportFormat(ReportFormatType.CIOMSFORM));
		}

		if (study.getCiomsSaePDFType()
				&& study.getReportFormat(ReportFormatType.CIOMSSAEFORM) == null) {
			ReportFormat ciomsSaePDFFormat = new ReportFormat();
			ciomsSaePDFFormat.setStudy(study);
			ciomsSaePDFFormat.setReportFormatType(ReportFormatType.CIOMSSAEFORM);
			study.getReportFormats().add(ciomsSaePDFFormat);
		}
		else if (!study.getCiomsSaePDFType()
				&& study.getReportFormat(ReportFormatType.CIOMSSAEFORM) != null) {
			reportFormats.remove(study.getReportFormat(ReportFormatType.CIOMSSAEFORM));
		}

		if (study.getDcpSAEPDFType()
				&& study.getReportFormat(ReportFormatType.DCPSAEFORM) == null) {
			ReportFormat dcpSaePDFFormat = new ReportFormat();
			dcpSaePDFFormat.setStudy(study);
			dcpSaePDFFormat.setReportFormatType(ReportFormatType.DCPSAEFORM);
			study.getReportFormats().add(dcpSaePDFFormat);
		}
		else if (!study.getDcpSAEPDFType()
				&& study.getReportFormat(ReportFormatType.DCPSAEFORM) != null) {
			reportFormats.remove(study.getReportFormat(ReportFormatType.DCPSAEFORM));
		}

		if (study.getMedwatchPDFType()
				&& study.getReportFormat(ReportFormatType.MEDWATCHPDF) == null) {
			ReportFormat medwatchPDFFormat = new ReportFormat();
			medwatchPDFFormat.setStudy(study);
			medwatchPDFFormat.setReportFormatType(ReportFormatType.MEDWATCHPDF);
			study.getReportFormats().add(medwatchPDFFormat);
		}
		else if (!study.getMedwatchPDFType()
				&& study.getReportFormat(ReportFormatType.MEDWATCHPDF) != null) {
			reportFormats.remove(study.getReportFormat(ReportFormatType.MEDWATCHPDF));
		}
	}
	
	@Override
	public void postProcess(final HttpServletRequest request, final Study command, final Errors errors) {
		super.postProcess(request, command, errors);
		if (errors.hasErrors()) {
			return;
		}
		
		for (int i=0; i<=1; i++){
			OrganizationAssignedIdentifier identifier = (OrganizationAssignedIdentifier)command.getIdentifiers().get(i);
			if (identifier.getType().equals(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE)){
				identifier.setOrganization(command.getPrimaryFundingSponsorOrganization());
			}
			if (identifier.getType().equals(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)){
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
