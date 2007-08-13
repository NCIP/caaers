package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
class IdentifiersTab extends StudyTab {
	private OrganizationDao organizationDao;

	private RepeatingFieldGroupFactory rfgFactory;

	public IdentifiersTab() {
		super("Study Identifiers", "Identifiers", "study/study_identifiers");
		setAutoPopulateHelpKey(true);
	}

	@Override
	public Map<String, Object> referenceData() {
		Map<String, Object> refdata = super.referenceData();
		Map<String, List<Lov>> configMap = getConfigurationProperty().getMap();

		refdata.put("identifiersSourceRefData", organizationDao.getAll());
		refdata.put("identifiersTypeRefData", configMap.get("identifiersType"));
		return refdata;
	}

	@Override
	public void postProcess(final HttpServletRequest request, final Study command, final Errors errors) {
		String action = request.getParameter("_action");
		String selected = request.getParameter("_selected");
		if ("removeIdentifier".equals(action)) {
			Study study = command;
			study.getIdentifiersLazy().remove(Integer.parseInt(selected));
		}
	}

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(final Study command) {
		if (rfgFactory == null) {
			rfgFactory = new RepeatingFieldGroupFactory("main", "identifiersLazy");
			rfgFactory.addField(InputFieldFactory.createTextField("value", "Identifier", true));
			rfgFactory.addField(InputFieldFactory.createSelectField("type", "Identifier Type", true,
					collectOptionsFromConfig("identifiersType", "desc", "desc")));
			// InputField sourceField = InputFieldFactory.createAutocompleterField("source", "Assigning Authority", true);
			// sourceField.getAttributes().put(InputField.DETAILS,"Enter a portion of the site name you are looking for");
			// //rfgFactory.addField(sourceField);
			// rfgFactory.addField(InputFieldFactory.createSelectField("source", "Assigning Authority", true,
			// collectOptions(organizationDao.getAll(), "name", "name")));

			rfgFactory.addField(InputFieldFactory.createTextField("systemName", "System Name", false));
			rfgFactory.addField(InputFieldFactory.createAutocompleterField("organization", "Organization", false));
			// rfgFactory.addField(InputFieldFactory.createAutocompleterField("source", "Source"));
			rfgFactory.addField(InputFieldFactory.createCheckboxField("primaryIndicator", "Primary Indicator"));

		}
		Study study = command;
		InputFieldGroupMap map = new InputFieldGroupMap();
		map.addRepeatingFieldGroupFactory(rfgFactory, study.getIdentifiersLazy().size());
		return map;
	}

	@Override
	protected void validate(final Study command, final BeanWrapper commandBean,
			final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
		super.validate(command, commandBean, fieldGroups, errors);

		List<Identifier> identifiers = command.getIdentifiers();
		for (int i = 0; i < identifiers.size(); i++) {
			Identifier identifier = identifiers.get(i);
			if (identifier instanceof OrganizationAssignedIdentifier
					&& ((OrganizationAssignedIdentifier) identifier).getOrganization() == null) {
				errors
						.rejectValue("identifiersLazy[" + i + "].organization", "REQUIRED",
								"Organization is required..!");

			}
			else if (identifier instanceof SystemAssignedIdentifier
					&& (((SystemAssignedIdentifier) identifier).getSystemName() == null || ((SystemAssignedIdentifier) identifier)
							.getSystemName().equals(""))) {

				errors.rejectValue("identifiersLazy[" + i + "].systemName", "REQUIRED", "System Name is required..!");
			}
		}

	}

	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}
