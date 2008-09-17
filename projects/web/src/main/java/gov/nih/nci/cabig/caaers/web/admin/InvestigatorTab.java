package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Saurabh Agrawal
 */
public class InvestigatorTab extends TabWithFields<Investigator> {

    protected static final Log log = LogFactory.getLog(InvestigatorTab.class);

    private static final String INVESTIGATOR_FIELD_GROUP = "investigator";

    private ConfigProperty configurationProperty;

    private OrganizationDao organizationDao;

    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(final ConfigProperty configProperty) {
        configurationProperty = configProperty;
    }

    public InvestigatorTab() {
        super("Investigator Details", "Investigator Details", "admin/investigator_details");
        setAutoPopulateHelpKey(true);
        addHelpKeyExclusion("firstName", "middleName", "lastName", "emailAddress", "phoneNumber",
                "faxNumber", "statusCode");
    }

    @Override
    public void postProcess(final HttpServletRequest request, final Investigator command,
                            final Errors errors) {
        String action = request.getParameter("_action");
        String selected = request.getParameter("_selected");
        if ("removeInvestigator".equals(action)) {
            command.getSiteInvestigators().remove(Integer.parseInt(selected));
        }
    }

    protected Map<Object, Object> collectOptions(final List list, final String nameProperty,
                                                 final String valueProperty) {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        options.putAll(WebUtils.collectOptions(list, nameProperty, valueProperty));
        return options;
    }

    protected Map<Object, Object> collectOptionsFromConfig(final String configPropertyName,
                                                           final String nameProperty, final String valueProperty) {
        return collectOptions(configurationProperty.getMap().get(configPropertyName), nameProperty,
                valueProperty);
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, Investigator command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        Map<String, List<Lov>> configMap = getConfigurationProperty().getMap();
        refdata.put("sitesRefData", getOrganizations());

        refdata.put("studySiteStatusRefData", configMap.get("studySiteStatusRefData"));
        refdata.put("studySiteRoleCodeRefData", configMap.get("studySiteRoleCodeRefData"));
        return refdata;
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final Investigator command) {

        InputFieldGroup investigatorFieldGroup = null;
        RepeatingFieldGroupFactory rfgFactory = null;

        rfgFactory = new RepeatingFieldGroupFactory("main", "siteInvestigators");

        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        List<Organization> organizations = getOrganizations();
        if (organizations != null) {
            options.putAll(WebUtils.collectOptions(organizations, "id", "name"));
        }

        // rfgFactory.addField(InputFieldFactory.createSelectField("organization", "Organization",
        // false, options));
        rfgFactory.addField(InputFieldFactory.createAutocompleterField("organization",
                "Organization", true));

        rfgFactory.addField(InputFieldFactory.createSelectField("statusCode", "Status", true,
                collectOptionsFromConfig("studySiteStatusRefData", "code", "desc")));

        investigatorFieldGroup = new DefaultInputFieldGroup(INVESTIGATOR_FIELD_GROUP);
        investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("firstName", "First Name", true));

        investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("middleName", "Middle Name", false));
        investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("lastName", "Last Name", true));
        investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("nciIdentifier", "Investigator number",
                        false));

        InputField emailAddressField = InputFieldFactory.createEmailField("emailAddress",
                "Email address", true);
        // InputFieldAttributes.setSize(emailAddressField, 30);

        investigatorFieldGroup.getFields().add(emailAddressField);

        InputField phoneNumberField = InputFieldFactory.createPhoneField("phoneNumber", "Phone",
                true);
        phoneNumberField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        // InputFieldAttributes.setSize(phoneNumberField, 30);
        investigatorFieldGroup.getFields().add(phoneNumberField);

        InputField faxNumberField = InputFieldFactory.createTextField("faxNumber", "Fax",
                FieldValidator.PHONE_VALIDATOR);
        faxNumberField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        // InputFieldAttributes.setSize(faxNumberField, 30);
        investigatorFieldGroup.getFields().add(faxNumberField);

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(investigatorFieldGroup);
        map.addRepeatingFieldGroupFactory(rfgFactory, command.getSiteInvestigators().size());

        return map;
    }

    @Override
    protected void validate(final Investigator object, final BeanWrapper commandBean,
                            final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {

        super.validate(object, commandBean, fieldGroups, errors);
        List<SiteInvestigator> investigators = object.getSiteInvestigators();
        for (int i = 0; i < investigators.size(); i++) {
            SiteInvestigator siteInvestigator = investigators.get(i);
            if (siteInvestigator.getOrganization() == null) {
                errors.rejectValue("siteInvestigators[" + i + "].organization", "REQUIRED",
                        "Site is required..!");

            }
            if (siteInvestigator.getStatusCode() == null) {
                errors.rejectValue("siteInvestigators[" + i + "].statusCode", "REQUIRED",
                        "Status type is required..!");

            }

        }
    }

    protected List<Organization> getOrganizations() {
        return organizationDao.getAll();
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }
}
