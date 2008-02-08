package gov.nih.nci.cabig.caaers.web.participant;

//java imports
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Calendar;
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
		
		InputField dobYear = InputFieldFactory.createTextField("year", "Year", true);
		InputFieldAttributes.setSize(dobYear, 4);
		InputField dobMonth = InputFieldFactory.createTextField("month", "Month");
		InputFieldAttributes.setSize(dobMonth, 2);
		InputField dobDay = InputFieldFactory.createTextField("day", "Day");
		InputFieldAttributes.setSize(dobDay, 2);
		
		CompositeField dobField = new CompositeField("participant.dateOfBirth", new DefaultInputFieldGroup(null, "Date of birth").addField(dobYear).addField(dobMonth).addField(dobDay));
		dobField.setRequired(true);
		dobField.getAttributes().put(InputField.HELP,"par.par_create_participant.participant.dateOfBirth");
		participantFieldGroup.getFields().add(dobField);

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

		rfgFactory.addField(InputFieldFactory.createTextField("systemName", "System Name", true));
		rfgFactory.addField(InputFieldFactory
				.createAutocompleterField("organization", "Organization Identifier", true));
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
		boolean hasPrimaryID = false;
		Calendar now = Calendar.getInstance();
		DateValue dob = command.getParticipant().getDateOfBirth();
		if( (dob.getMonth() != null && (dob.getMonth() < 0 || dob.getMonth() > 12)) ||
			(dob.getDay() != null && (dob.getDay() < 0 || dob.getDay() > 31)) || 
			(dob.getYear() == null || dob.getYear() < 1900 || dob.getYear() > now.get(Calendar.YEAR)) ||
			 now.after(dob.toDate())
		){
			errors.rejectValue("participant.dateOfBirth", "REQUIRED", "Incorrect Date Of Birth");
		}
		
		
		for (Identifier identifier : command.getParticipant().getIdentifiersLazy()) {
			hasPrimaryID |= identifier.isPrimary();
			if(hasPrimaryID) break;
		}
		if (!hasPrimaryID) errors.rejectValue("participant.identifiers", 
					"REQUIRED", "Please Include at least a single primary Identifier");
		
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
