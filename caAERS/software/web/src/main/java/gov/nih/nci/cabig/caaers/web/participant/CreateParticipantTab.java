package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.*;
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

import java.util.*;

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
    private ParticipantDao participantDao;

    private static final String PARTICIPANT_FIELD_GROUP = "participant";
    private static final String SITE_FIELD_GROUP = "site";
    @Override
    public Map<String, InputFieldGroup> createFieldGroups(T command) {
        InputFieldGroup participantFieldGroup;
        InputFieldGroup siteFieldGroup;
        RepeatingFieldGroupFactory repeatingFieldGroupFactoryOrg = null;
        RepeatingFieldGroupFactory repeatingFieldGroupFactorySys = null;

        siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);

        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        List<Organization> organizations = organizationRepository.getOrganizationsHavingStudySites();
        if (organizations != null) {
            options.putAll(WebUtils.collectOptions(organizations, "id", "fullName"));
        }
        siteFieldGroup.getFields().add(InputFieldFactory.createSelectField("organization", "Site", true, options));

        participantFieldGroup = new DefaultInputFieldGroup(PARTICIPANT_FIELD_GROUP);

        FieldValidator fv = FieldValidator.ALPHANUMERIC_VALIDATOR; 
        if (!command.isUnidentifiedMode()) {
            participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.firstName", "First Name", FieldValidator.NOT_NULL_VALIDATOR, fv));
            participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.lastName", "Last Name", FieldValidator.NOT_NULL_VALIDATOR, fv));
            participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.maidenName", "Maiden Name", fv));
            participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.middleName", "Middle Name", fv));

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

            repeatingFieldGroupFactoryOrg.addField(InputFieldFactory.createAutocompleterField("organization", "Organization", true));
            repeatingFieldGroupFactorySys.addField(InputFieldFactory.createTextField("systemName", "System Name", true));
        }
        InputField dobField = InputFieldFactory.createSplitDateField("participant.dateOfBirth", "Date of birth", false, true, true, true);

        participantFieldGroup.getFields().add(dobField);

        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.gender", "Gender", true, collectOptions(listValues.getParticipantGender())));
        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.ethnicity", "Ethnicity", true, collectOptions(listValues.getParticipantEthnicity())));
        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.race", "Race", true, collectOptions(listValues.getParticipantRace())));

        InputFieldGroupMap map = new InputFieldGroupMap();
        if (command.getParticipant() != null && !command.isUnidentifiedMode()) {
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
        command.getParticipant().getOrganizationIdentifiers().get(0).setPrimaryIndicator(true);
        super.onBind(request, command, errors);
    }

    @Override
    protected ModelAndView postProcessInPlaceEditing(HttpServletRequest request, T command, String property, String value) throws Exception {
        return super.postProcessInPlaceEditing(request, command, property, value);    
    }

    @Override
    protected void validate(T command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        DateValue dob = command.getParticipant().getDateOfBirth();
        if (dob.checkIfDateIsInValid()) {
            errors.rejectValue("participant.dateOfBirth", "PT_010", "Incorrect Date Of Birth");
        }

// CHECK Participant Identifiers
        List<Identifier> sitePrimaryIdentifiers = participantDao.getSitePrimaryIdentifiers(command.getOrganization().getId().intValue());
        for (int i=0; i<sitePrimaryIdentifiers.size(); i++) {
            Identifier sID = sitePrimaryIdentifiers.get(i);
            if (sID == null || sID.getValue() == null) continue;

            for (int j=0; j<command.getParticipant().getIdentifiers().size(); j++) {
                Identifier pID = command.getParticipant().getIdentifiers().get(j);

                if (pID == null || pID.getValue() == null || (pID.getId() != null && sID.getId() != null && pID.getId().intValue() == sID.getId().intValue())) continue;
                if (sID.getValue().toLowerCase().equals(pID.getValue().toLowerCase())) {
                    errors.reject("ERR_DUPLICATE_SITE_PRIMARY_IDENTIFIER", new Object[] {command.getOrganization().getName(), pID.getValue()}, "Duplicate identifiers for the same site.");
                }
            }
        }

        Set<Identifier> set = new HashSet<Identifier>();

        byte x = 0;
        byte y = 0;

        int size = command.getParticipant().getIdentifiers().size(); 
        for (int i = 0; i < size; i++) {
            Identifier identifier = command.getParticipant().getIdentifiers().get(i);

            if (identifier instanceof OrganizationAssignedIdentifier) {
                if (!set.add(identifier)) {
                    errors.rejectValue("participant.organizationIdentifiers[" + x + "].value", "STU_009", "Duplicate, already an identifier of this type is present");
                }
                x++;
            }

            if (identifier instanceof SystemAssignedIdentifier) {
                if (!set.add(identifier)) {
                    errors.rejectValue("participant.systemAssignedIdentifiers[" + y + "].value", "STU_009", "Duplicate, already an identifier of this type is present");
                }
                y++;
            }
        }
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

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }
}
