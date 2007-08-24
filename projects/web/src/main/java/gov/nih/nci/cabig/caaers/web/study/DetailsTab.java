package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class DetailsTab extends StudyTab {
	private CtcDao ctcDao;

	private MeddraVersionDao meddraVersionDao;

	InputFieldGroup fieldGroup;

	InputFieldGroup organizationFieldGroup;

	public DetailsTab() {
		super("Basic Details", "Details", "study/study_details");
		setAutoPopulateHelpKey(true);
	}

	@Override
	public Map<String, Object> referenceData() {
		Map<String, Object> refdata = super.referenceData();
		// TODO : to be removed from reference data, as they are no longer used.
		addConfigMapToRefdata(refdata, "phaseCodeRefData"); // remove
		addConfigMapToRefdata(refdata, "sponsorCodeRefData"); // remove
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
			InputFieldAttributes.setColumns(longTitleField, 50);
			fields.add(longTitleField);
			InputField precisField = InputFieldFactory.createTextArea("precis", "Precis", false);
			InputFieldAttributes.setColumns(precisField, 50);
			fields.add(precisField);
			InputField descField = InputFieldFactory.createTextArea("description", "Description", false);
			InputFieldAttributes.setColumns(descField, 50);
			fields.add(descField);
			InputField sponsorField = InputFieldFactory.createAutocompleterField("primaryFundingSponsorOrganization",
					"Primary sponsor", true);
			// sponsorField.getAttributes().put(InputField.DETAILS,"Enter a portion of the sponsor name you are looking for");
			fields.add(sponsorField);
			fields.add(InputFieldFactory.createSelectField("phaseCode", "Phase", true, collectOptionsFromConfig(
					"phaseCodeRefData", "desc", "desc")));
			fields.add(InputFieldFactory.createSelectField("terminology.term", "Terminology", true, InputFieldFactory
					.collectOptions(Arrays.asList(Term.values()), null, "displayName")));
			// TODO: Add validation for when terminology.term = Term.CTC
			fields.add(InputFieldFactory.createSelectField("terminology.ctcVersion", "CTC version", false,
					collectOptions(ctcDao.getAll(), "id", "name")));
			fields.add(InputFieldFactory.createSelectField("terminology.meddraVersion", "MedDRA version", false,
					collectOptions(meddraVersionDao.getAll(), "id", "name")));
		}
		if (organizationFieldGroup == null) {

			// for organization assigned identifier
			// fields.add(InputFieldFactory.createTextField("studyCoordinatingCenter", "Coordinating Center", false));

			organizationFieldGroup = new DefaultInputFieldGroup("organizationFieldGroup");
			List<InputField> organizationFields = organizationFieldGroup.getFields();

			organizationFields.add(InputFieldFactory.createBooleanSelectField("multiInstitutionIndicator",
					"Multi-Institutional", true));

			organizationFields.add(InputFieldFactory.createTextField("organizationAssignedIdentifier.value",
					"Coordinating Center Study Identifier", false));
			organizationFields.add(InputFieldFactory.createAutocompleterField(
					"organizationAssignedIdentifier.organization", "Coordinating Center", false));

		}
		InputFieldGroupMap map = new InputFieldGroupMap();
		map.addInputFieldGroup(fieldGroup);
		map.addInputFieldGroup(organizationFieldGroup);
		return map;
	}

	@Override
	protected void validate(final Study command, final BeanWrapper commandBean,
			final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
		if (command.getTerminology().getTerm() == Term.MEDDRA && command.getTerminology().getMeddraVersion() == null) {
			InputField field = fieldGroups.get("studyDetails").getFields().get(8);
			errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
		}

		if (command.getTerminology().getTerm() == Term.CTC && command.getTerminology().getCtcVersion() == null) {
			InputField field = fieldGroups.get("studyDetails").getFields().get(7);
			errors.rejectValue(field.getPropertyName(), "REQUIRED", "Missing " + field.getDisplayName());
		}
		OrganizationAssignedIdentifier identifier = command.getOrganizationAssignedIdentifier();
		Boolean multiInstitution = command.getMultiInstitutionIndicator();
		if (multiInstitution.equals(Boolean.TRUE) && identifier.getOrganization() == null) {
			errors.rejectValue("organizationAssignedIdentifier.organization", "REQUIRED",
					"Coordinating Center is required..!");

		}
		if (multiInstitution.equals(Boolean.TRUE) && identifier.getValue() == null) {
			errors.rejectValue("organizationAssignedIdentifier.value", "REQUIRED", "Identifier is required..!");

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
