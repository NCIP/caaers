package gov.nih.nci.cabig.caaers.web.participant;

//java imports

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import org.springframework.validation.Errors;
import org.springframework.beans.BeanWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class EditParticipantTab<T extends ParticipantInputCommand> extends TabWithFields<T> {

    public EditParticipantTab() {
        super("Enter Subject Information", "Details", "par/par_edit_participant");
    }

    private OrganizationDao organizationDao;
    private ListValues listValues;
    private ConfigProperty configurationProperty;

    private static final String PARTICIPANT_FIELD_GROUP = "participant";
    private static final String SITE_FIELD_GROUP = "site";

    @Override
    public Map<String, Object> referenceData(final ParticipantInputCommand command) {
        Map<String, Object> refdata = referenceData();
        Map<String, InputFieldGroup> groupMap = createFieldGroups(command);
        refdata.put("fieldGroups", groupMap);
        refdata.put("action", "New");
        return refdata;
    }

    protected Map<Object, Object> collectOptions(final List<ListValues> list) {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.putAll(WebUtils.collectOptions(list, "code", "desc"));
        return options;
    }

    public Map<String, InputFieldGroup> createFieldGroups(final ParticipantInputCommand command) {

        InputFieldGroup participantFieldGroup;
        InputFieldGroup siteFieldGroup;
//        RepeatingFieldGroupFactory repeatingFieldGroupFactory;

        siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);

        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        List<Organization> organizations = organizationDao.getOrganizationsHavingStudySites();
        if (organizations != null) {
            options.putAll(WebUtils.collectOptions(organizations, "id", "fullName"));
        }
        siteFieldGroup.getFields().add(InputFieldFactory.createSelectField("organization", "Site", true, options));

        participantFieldGroup = new DefaultInputFieldGroup(PARTICIPANT_FIELD_GROUP);
        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.firstName", "First Name", true));

        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.lastName", "Last Name", true));
        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.maidenName", "Maiden Name", false));
        participantFieldGroup.getFields().add(InputFieldFactory.createTextField("participant.middleName", "Middle Name", false));

        InputField dobYear = InputFieldFactory.createTextField("year", "Year", true);
        InputFieldAttributes.setSize(dobYear, 4);
        InputField dobMonth = InputFieldFactory.createTextField("month", "Month");
        InputFieldAttributes.setSize(dobMonth, 2);
        InputField dobDay = InputFieldFactory.createTextField("day", "Day");
        InputFieldAttributes.setSize(dobDay, 2);

        CompositeField dobField = new CompositeField("participant.dateOfBirth", new DefaultInputFieldGroup(null, "Date of birth")
                .addField(dobYear)
                .addField(dobMonth)
                .addField(dobDay));

        dobField.setRequired(true);
        dobField.getAttributes().put(InputField.HELP, "par.par_create_participant.participant.dateOfBirth");

        participantFieldGroup.getFields().add(dobField);
        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.gender", "Gender", true, collectOptions(listValues.getParticipantGender())));
        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.ethnicity", "Ethnicity", true,collectOptions(listValues.getParticipantEthnicity())));

        participantFieldGroup.getFields().add(InputFieldFactory.createSelectField("participant.race", "Race", true, collectOptions(listValues.getParticipantRace())));
/*
        repeatingFieldGroupFactory = new RepeatingFieldGroupFactory("main", "participant.identifiers");
        repeatingFieldGroupFactory.addField(InputFieldFactory.createTextField("value", "Identifier", true));
*/

        options = new LinkedHashMap<Object, Object>();
        List<Lov> list = configurationProperty.getMap().get("participantIdentifiersType");
        options.put("", "Please select");
        options.putAll(WebUtils.collectOptions(list, "code", "desc"));

        InputFieldGroupMap map = new InputFieldGroupMap();

        byte i = 0;
        byte j = 0;

        InputFieldGroup idtFieldGroupOrg;
        InputFieldGroup idtFieldGroupSys;

        for (Identifier idt : command.participant.getIdentifiers()) {

            if (idt instanceof SystemAssignedIdentifier) {
                String s = "participant.systemAssignedIdentifiers[" + i + "]." ;
                idtFieldGroupSys = new DefaultInputFieldGroup("mainSys" + i);

                idtFieldGroupSys.getFields().add(InputFieldFactory.createTextField(s + "value", "Identifier", true));
                idtFieldGroupSys.getFields().add(InputFieldFactory.createSelectField(s + "type", "Identifier Type", true,options));
                idtFieldGroupSys.getFields().add(InputFieldFactory.createTextField(s + "systemName", "System Name", true));
                idtFieldGroupSys.getFields().add(InputFieldFactory.createCheckboxField(s + "primaryIndicator", "Primary Indicator"));
                map.addInputFieldGroup(idtFieldGroupSys);
                
                i++;

            } else {
                String s = "participant.organizationIdentifiers[" + j + "]." ;
                idtFieldGroupOrg = new DefaultInputFieldGroup("mainOrg" + j);
                
                idtFieldGroupOrg.getFields().add(InputFieldFactory.createTextField(s + "value", "Identifier", true));
                idtFieldGroupOrg.getFields().add(InputFieldFactory.createSelectField(s + "type", "Identifier Type", true, options));
                idtFieldGroupOrg.getFields().add(InputFieldFactory.createAutocompleterField(s + "organization", "Organization Identifier", true));
                idtFieldGroupOrg.getFields().add(InputFieldFactory.createCheckboxField(s + "primaryIndicator", "Primary Indicator"));
                map.addInputFieldGroup(idtFieldGroupOrg);

                j++;
            }

        }
  
        map.addInputFieldGroup(participantFieldGroup);
        map.addInputFieldGroup(siteFieldGroup);
        
        return map;
    }

    @Override
    public void postProcess(final HttpServletRequest request, final ParticipantInputCommand command, final Errors errors) {
        String action = request.getParameter("_action");
        String selected = request.getParameter("_selected");
        if ("removeIdentifier".equals(action)) {
            ParticipantInputCommand cmd = command;
            cmd.getParticipant().getIdentifiers().remove(Integer.parseInt(selected));
        }
    }

    protected void validate(ParticipantInputCommand command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        boolean hasPrimaryID = false;
        DateValue dob = command.getParticipant().getDateOfBirth();
        
        if (dob.checkIfDateIsInValid()) {
            errors.rejectValue("participant.dateOfBirth", "REQUIRED", "Incorrect Date Of Birth");
        }

        for (Identifier identifier : command.getParticipant().getIdentifiersLazy()) {
            hasPrimaryID |= identifier.isPrimary();
            if (hasPrimaryID) break;
        }
        if (!hasPrimaryID) errors.rejectValue("participant.identifiers", "REQUIRED",
                "Please Include at least a single primary Identifier");
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

    public void addOrganizationIdentifier() {
        // ToDo
        // add a new element in the command list of participant identifiers.
        System.out.println("AJAX::addOrganizationIdentifier");
    }
}
