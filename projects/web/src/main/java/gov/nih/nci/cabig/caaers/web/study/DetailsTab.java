package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class DetailsTab extends StudyTab {


	private CtcDao ctcDao;

	private MeddraVersionDao meddraVersionDao;

	InputFieldGroup fieldGroup, fundSponsorFieldGroup, studyCodeFieldGroup, studyDiseaseCodeFieldGroup,coordinatingCenterFieldGroup;

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
			InputFieldAttributes.setColumns(longTitleField,50);
			fields.add(longTitleField);
			InputField precisField = InputFieldFactory.createTextArea("precis", "Precis", false);
			InputFieldAttributes.setColumns(precisField, 50);
			fields.add(precisField);
			InputField descField = InputFieldFactory.createTextArea("description", "Description", false);
			InputFieldAttributes.setColumns(descField, 50);
			
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
			fields.add(InputFieldFactory.createSelectField("terminology.term", "Terminology", true, InputFieldFactory
					.collectOptions(Arrays.asList(Term.values()), null, "displayName")));
		
			// TODO: Add validation for when terminology.term = Term.CTC
			fields.add(InputFieldFactory.createSelectField("terminology.ctcVersion", "CTC version", false,
					collectOptions(ctcDao.getAll(), "id", "name")));
			fields.add(InputFieldFactory.createSelectField("terminology.meddraVersion", "MedDRA version", false,
					collectOptions(meddraVersionDao.getAll(), "id", "name")));
		}
		
		if(studyDiseaseCodeFieldGroup == null){
			studyDiseaseCodeFieldGroup = new DefaultInputFieldGroup("sdcFieldGroup");
			List<InputField> fields = studyDiseaseCodeFieldGroup.getFields();
			fields.add(InputFieldFactory.createSelectField("diseaseTerminology.diseaseCodeTerm", "Disease Terminology", true, InputFieldFactory
					.collectOptions(Arrays.asList(DiseaseCodeTerm.values()), null, "displayName")));
		}
		
		InputFieldGroupMap map = new InputFieldGroupMap();
		map.addInputFieldGroup(fieldGroup);
		map.addInputFieldGroup(fundSponsorFieldGroup);
		map.addInputFieldGroup(coordinatingCenterFieldGroup);
		map.addInputFieldGroup(studyCodeFieldGroup);
		map.addInputFieldGroup(studyDiseaseCodeFieldGroup);
		
		return map;
	}

	@Override
	protected void validate(final Study command, final BeanWrapper commandBean,
			final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
		if (command.getTerminology().getTerm() == Term.MEDDRA && command.getTerminology().getMeddraVersion() == null) {
			InputField field = fieldGroups.get("scFieldGroup").getFields().get(1);
			errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
		}

		if (command.getTerminology().getTerm() == Term.CTC && command.getTerminology().getCtcVersion() == null) {
			InputField field = fieldGroups.get("scFieldGroup").getFields().get(0);
			errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
		}
    }

	@Override
	public void postProcess(final HttpServletRequest request, final Study command, final Errors errors) {
		super.postProcess(request, command, errors);
		if (errors.hasErrors()) {
			return;
		}
		
		//set the sponsor assigned identifier.
		OrganizationAssignedIdentifier identifier = (OrganizationAssignedIdentifier)command.getIdentifiers().get(0);
		identifier.setOrganization(command.getPrimaryFundingSponsorOrganization());
		
		identifier = (OrganizationAssignedIdentifier)command.getIdentifiers().get(1);
		identifier.setOrganization(command.getStudyCoordinatingCenter().getOrganization());
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
