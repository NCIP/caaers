package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class CreateParticipantTab<T extends ParticipantInputCommand> extends TabWithFields<T> {

    public CreateParticipantTab() {
        super("Enter Subject Information", "Details", "par/par_create_participant");
    }

    OrganizationRepository organizationRepository;
    private ListValues listValues;
    private ConfigProperty configurationProperty;

    private static final String PARTICIPANT_FIELD_GROUP = "participant";
    private static final String SITE_FIELD_GROUP = "site";

    public Map<String, InputFieldGroup> createFieldGroups(ParticipantInputCommand command) {
        InputFieldGroup participantFieldGroup;
        InputFieldGroup siteFieldGroup;
        RepeatingFieldGroupFactory repeatingFieldGroupFactoryOrg;
        RepeatingFieldGroupFactory repeatingFieldGroupFactorySys;

        siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);

        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        List<Organization> organizations = organizationRepository.getOrganizationsHavingStudySites();
        if (organizations != null) {
            options.putAll(WebUtils.collectOptions(organizations, "id", "fullName"));
        }
        siteFieldGroup.getFields().add(InputFieldFactory.createSelectField("organization", "Site", true, options));

        participantFieldGroup = new DefaultInputFieldGroup(PARTICIPANT_FIELD_GROUP);
        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.firstName", "First Name", FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.ALPHANUMERIC_VALIDATOR));
        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.lastName", "Last Name", FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.ALPHANUMERIC_VALIDATOR));
        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.maidenName", "Maiden Name", FieldValidator.ALPHANUMERIC_VALIDATOR));
        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.middleName", "Middle Name", FieldValidator.ALPHANUMERIC_VALIDATOR));

        InputField dobField = InputFieldFactory.createSplitDateField("participant.dateOfBirth", "Date of birth", false, true, true, true);

//        CompositeField dobField = new CompositeField("participant.dateOfBirth", new DefaultInputFieldGroup(null, "Date of birth").
//                addField(dobYear).addField(dobMonth).addField(dobDay));

//        dobField.setRequired(true);
//        dobField.getAttributes().put(InputField.HELP, "par.par_create_participant.participant.dateOfBirth");

        participantFieldGroup.getFields().add(dobField);

        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.gender", "Gender", true, collectOptions(listValues.getParticipantGender())));
        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.ethnicity", "Ethnicity", true, collectOptions(listValues.getParticipantEthnicity())));
        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.race", "Race", true, collectOptions(listValues.getParticipantRace())));

        repeatingFieldGroupFactoryOrg = new RepeatingFieldGroupFactory("mainOrg", "participant.organizationIdentifiers");
        repeatingFieldGroupFactorySys = new RepeatingFieldGroupFactory("mainSys", "participant.systemAssignedIdentifiers");

        repeatingFieldGroupFactoryOrg.addField(InputFieldFactory.createTextField("value", "Identifier", FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.IDENTIFIER_VALIDATOR));
        repeatingFieldGroupFactorySys.addField(InputFieldFactory.createTextField("value", "Identifier", FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.IDENTIFIER_VALIDATOR));

        options = new LinkedHashMap<Object, Object>();
        List<Lov> list = configurationProperty.getMap().get("participantIdentifiersType");
        options.put("", "Please select");
        options.putAll(WebUtils.collectOptions(list, "code", "desc"));

        repeatingFieldGroupFactoryOrg.addField(InputFieldFactory.createSelectField("type", "Identifier Type", true, options));
        repeatingFieldGroupFactorySys.addField(InputFieldFactory.createSelectField("type", "Identifier Type", true, options));

        repeatingFieldGroupFactoryOrg.addField(InputFieldFactory.createAutocompleterField("organization", "Organization Identifier", true));
        repeatingFieldGroupFactorySys.addField(InputFieldFactory.createTextField("systemName", "System Name", true));

/*
        repeatingFieldGroupFactoryOrg.addField(InputFieldFactory.createCheckboxField("primaryIndicator", "Primary Indicator"));
        repeatingFieldGroupFactorySys.addField(InputFieldFactory.createCheckboxField("primaryIndicator", "Primary Indicator"));
*/

        InputFieldGroupMap map = new InputFieldGroupMap();
        if (command.getParticipant() != null) {
            map.addRepeatingFieldGroupFactory(repeatingFieldGroupFactoryOrg, command.getParticipant().getOrganizationIdentifiers().size());
            map.addRepeatingFieldGroupFactory(repeatingFieldGroupFactorySys, command.getParticipant().getSystemAssignedIdentifiers().size());
        }

        map.addInputFieldGroup(participantFieldGroup);
        map.addInputFieldGroup(siteFieldGroup);
        return map;
    }

    protected Map<Object, Object> collectOptions(final List<ListValues> list) {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.putAll(WebUtils.collectOptions(list, "code", "desc"));
        return options;
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, T command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        refdata.put("action", "New");
        return refdata;
    }

    @Override
    public void onBind(HttpServletRequest request, T command, Errors errors) {
        Object isAjax = request.getAttribute("_isAjax");
        if (isAjax != null) return;
        command.getParticipant().getOrganizationIdentifiers().get(0).setOrganization(command.getOrganization());
        super.onBind(request, command, errors); 
    }

    @Override
    protected ModelAndView postProcessInPlaceEditing(HttpServletRequest request, T command, String property, String value) throws Exception {
        return super.postProcessInPlaceEditing(request, command, property, value);    
    }

    @Override
    protected void validate(T command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        boolean hasPrimaryID = false;
        DateValue dob = command.getParticipant().getDateOfBirth();
        if (dob.checkIfDateIsInValid()) {
            errors.rejectValue("participant.dateOfBirth", "PT_010", "Incorrect Date Of Birth");
        }

        for (Identifier identifier : command.getParticipant().getIdentifiersLazy()) {

            if (identifier.isPrimary()) {
                if (hasPrimaryID) {
                    // there are at least 2 Primary ID selected
                    hasPrimaryID = false;
                    break;
                }

                hasPrimaryID = true;
            }
        }

        if (!hasPrimaryID)
            errors.rejectValue("participant.identifiers", "PT_011", "Please Include exactly One Primary Identifier");

    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
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

    public ModelAndView addOrganizationIdentifier(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        ModelAndView modelAndView = new ModelAndView(getAjaxViewName(request), map);

        ParticipantInputCommand command = (ParticipantInputCommand) cmd;
        List<OrganizationAssignedIdentifier> list = command.getParticipant().getOrganizationIdentifiers();

        // store the new index for the new Identifier
        int size = list.size();
        Integer[] indexes = new Integer[]{size};
        modelAndView.getModel().put("indexes", indexes);

        // store the new Identifier object into the command.participant
        OrganizationAssignedIdentifier newIdentifier = new OrganizationAssignedIdentifier();
        command.getParticipant().addIdentifier(newIdentifier);

        return modelAndView;
    }

    public ModelAndView removeOrganizationIdentifier(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        ModelAndView modelAndView = new ModelAndView(getAjaxViewName(request), map);

        ParticipantInputCommand command = (ParticipantInputCommand) cmd;
        List<OrganizationAssignedIdentifier> list = command.getParticipant().getOrganizationIdentifiers();
        list.remove(list.get(command.getIndex()));

        // update the array of remainning indexes after deleting
        int size = list.size();
        Integer[] indexes = new Integer[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = i;
        }

        modelAndView.getModel().put("indexes", indexes);
        modelAndView.getModel().put("remove", "remove");
        return modelAndView;
    }

    public ModelAndView addSystemIdentifier(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        ModelAndView modelAndView = new ModelAndView(getAjaxViewName(request), map);

        ParticipantInputCommand command = (ParticipantInputCommand) cmd;
        List<SystemAssignedIdentifier> list = command.getParticipant().getSystemAssignedIdentifiers();

        // store the new index for the new Identifier
        int size = list.size();
        Integer[] indexes = new Integer[]{size};
        modelAndView.getModel().put("indexes", indexes);

        // store the new Identifier object into the command.participant
        SystemAssignedIdentifier newIdentifier = new SystemAssignedIdentifier();
        command.getParticipant().addIdentifier(newIdentifier);

        return modelAndView;
    }

    public ModelAndView removeSystemIdentifier(HttpServletRequest request, Object cmd, Errors error) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        ModelAndView modelAndView = new ModelAndView(getAjaxViewName(request), map);

        ParticipantInputCommand command = (ParticipantInputCommand) cmd;
        List<SystemAssignedIdentifier> list = command.getParticipant().getSystemAssignedIdentifiers();

        list.remove(list.get(command.getIndex()));

        // update the array of remainning indexes after deleting
        int size = list.size();
        Integer[] indexes = new Integer[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = i;
        }

        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
    }


}
