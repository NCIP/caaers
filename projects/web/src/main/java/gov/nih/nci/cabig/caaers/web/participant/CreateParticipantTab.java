package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

public class CreateParticipantTab extends Tab<NewParticipantCommand> {

	public CreateParticipantTab() {
		super("Enter Subject Information", "Details", "par/par_create_participant");
	}

	private OrganizationDao organizationDao;

	private ListValues listValues;
	private ConfigProperty configurationProperty;

	private static final String PARTICIPANT_FIELD_GROUP = "participant";

	private static final String SITE_FIELD_GROUP = "site";

	@Override
	public Map<String, Object> referenceData(final NewParticipantCommand command) {
		Map<String, Object> refdata = referenceData();
		Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
		refdata.put("fieldGroups", groupMap);

		return refdata;
	}

	protected Map<Object, Object> collectOptions(final List<ListValues> list) {
		Map<Object, Object> options = new LinkedHashMap<Object, Object>();
		options.putAll(InputFieldFactory.collectOptions(list, "code", "desc"));
		return options;
	}

	private Map<String, InputFieldGroup> createFieldGroups(final NewParticipantCommand command) {

		InputFieldGroup participantFieldGroup;
		InputFieldGroup siteFieldGroup;
		RepeatingFieldGroupFactory rfgFactory;

		siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);

		Map<Object, Object> options = new LinkedHashMap<Object, Object>();
		options.put("", "Please select");
		List<Organization> organizations = organizationDao.getOrganizationsHavingStudySites();
		if (organizations != null) {
			options.putAll(InputFieldFactory.collectOptions(organizations, "id", "name"));
		}
		siteFieldGroup.getFields().add(InputFieldFactory.createSelectField("organization", "Site", true, options));

		participantFieldGroup = new DefaultInputFieldGroup(PARTICIPANT_FIELD_GROUP);
		participantFieldGroup.getFields().add(
				InputFieldFactory.createTextField("participant.firstName", "First Name", true));

		participantFieldGroup.getFields().add(
				InputFieldFactory.createTextField("participant.lastName", "Last Name", true));
		participantFieldGroup.getFields().add(
				InputFieldFactory.createTextField("participant.maidenName", "Maiden Name", false));
		participantFieldGroup.getFields().add(
				InputFieldFactory.createTextField("participant.middleName", "Middle Name", false));
		participantFieldGroup.getFields().add(
				InputFieldFactory.createDateField("participant.dateOfBirth", "Date of Birth", true));

		participantFieldGroup.getFields().add(
				InputFieldFactory.createSelectField("participant.gender", "Gender", true, collectOptions(listValues
						.getParticipantGender())));
		participantFieldGroup.getFields().add(
				InputFieldFactory.createSelectField("participant.ethnicity", "Ethnicity", true,
						collectOptions(listValues.getParticipantEthnicity())));

		participantFieldGroup.getFields().add(
				InputFieldFactory.createSelectField("participant.race", "Race", true, collectOptions(listValues
						.getParticipantRace())));
		rfgFactory = new RepeatingFieldGroupFactory("main", "participant.identifiers");
		rfgFactory.addField(InputFieldFactory.createTextField("value", "Identifier", true));

		options = new LinkedHashMap<Object, Object>();
		List<Lov> list = configurationProperty.getMap().get("participantIdentifiersType");
		options.put("", "Please select");
		options.putAll(InputFieldFactory.collectOptions(list, "code", "desc"));

		rfgFactory.addField(InputFieldFactory.createSelectField("type", "Identifier Type", true, options));

		rfgFactory.addField(InputFieldFactory.createTextField("systemName", "System Name", false));
		rfgFactory.addField(InputFieldFactory
				.createAutocompleterField("organization", "Organization Identifier", false));
		rfgFactory.addField(InputFieldFactory.createCheckboxField("primaryIndicator", "Primary Indicator"));

		InputFieldGroupMap map = new InputFieldGroupMap();
		map.addRepeatingFieldGroupFactory(rfgFactory, command.getParticipant().getIdentifiers().size());
		map.addInputFieldGroup(participantFieldGroup);
		map.addInputFieldGroup(siteFieldGroup);
		return map;
	}

	@Override
	public Map<String, Object> referenceData() {

		Map<String, Object> refdata = super.referenceData();
		refdata.put("action", "New");
		return refdata;
	}

	@Override
	public void postProcess(final HttpServletRequest request, final NewParticipantCommand command, final Errors errors) {
		String action = request.getParameter("_action");
		String selected = request.getParameter("_selected");
		if ("removeIdentifier".equals(action)) {
			NewParticipantCommand newParticipantCommand = command;
			newParticipantCommand.getParticipant().getIdentifiers().remove(Integer.parseInt(selected));
		}
	}

	@Override
	public void validate(final NewParticipantCommand command, final Errors errors) {
		boolean noPrimaryIdentifier = true;

		boolean organization = command.getOrganization() == null || command.getOrganization().getName().equals("");

		boolean firstName = command.getParticipant().getFirstName() == null
				|| command.getParticipant().getFirstName().equals("");
		boolean lastName = command.getParticipant().getLastName() == null
				|| command.getParticipant().getLastName().equals("");
		boolean dateOfBirth = command.getParticipant().getDateOfBirth() == null;
		boolean gender = command.getParticipant().getGender().equals("---");
		boolean ethnicity = command.getParticipant().getEthnicity().equals("---");
		boolean race = command.getParticipant().getRace().equals("---");
		if (organization) {
			errors.rejectValue("organization", "REQUIRED", "Missing Site");
		}
		if (firstName) {
			errors.rejectValue("participant.firstName", "REQUIRED", "Missing First Name");
		}
		if (lastName) {
			errors.rejectValue("participant.lastName", "REQUIRED", "Missing Last Name");
		}
		if (dateOfBirth) {
			errors.rejectValue("participant.dateOfBirth", "REQUIRED", "Missing Date Of Birth");
		}
		if (gender) {
			errors.rejectValue("participant.gender", "REQUIRED", "Please Specify a Gender");
		}
		if (ethnicity) {
			errors.rejectValue("participant.ethnicity", "REQUIRED", "Please Specify the Ethnicity");
		}
		if (race) {
			errors.rejectValue("participant.race", "REQUIRED", "Please specify the Race");
		}

		List<Identifier> identifiers = command.getParticipant().getIdentifiers();
		for (int i = 0; i < identifiers.size(); i++) {
			Identifier identifier = identifiers.get(i);
			if (identifier instanceof OrganizationAssignedIdentifier
					&& ((OrganizationAssignedIdentifier) identifier).getOrganization() == null) {
				errors.rejectValue("participant.identifiers[" + i + "].organization", "REQUIRED",
						"Organization is required..!");

			}
			else if (identifier instanceof SystemAssignedIdentifier
					&& (((SystemAssignedIdentifier) identifier).getSystemName() == null || ((SystemAssignedIdentifier) identifier)
							.getSystemName().equals(""))) {

				errors.rejectValue("participant.identifiers[" + i + "].systemName", "REQUIRED",
						"System Name is required..!");
			}
			if (identifier.getValue() == null || identifier.getValue().trim().equals("")) {

				errors.rejectValue("participant.identifiers[" + i + "].value", "REQUIRED", "Identifier is required..!");
			}
			if (identifier.getType() == null || identifier.getType().trim().equals("")) {

				errors.rejectValue("participant.identifiers[" + i + "].type", "REQUIRED",
						"Identifier type is required..!");
			}
			noPrimaryIdentifier = false;
		}
		if (noPrimaryIdentifier) {
			errors.rejectValue("participant.identifiers", "REQUIRED",
					"Please Include at least a single primary Identifier");
		}
	}

	public void setOrganizationDao(final OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setListValues(final ListValues listValues) {
		this.listValues = listValues;
	}
	
	public ConfigProperty getConfigurationProperty() {
		return configurationProperty;
	}
	public void setConfigurationProperty(ConfigProperty configurationProperty) {
		this.configurationProperty = configurationProperty;
	}
}
