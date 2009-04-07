package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Saurabh Agrawal
 */
public class InvestigatorTab extends TabWithFields<Investigator> {

    protected static final Log log = LogFactory.getLog(InvestigatorTab.class);

    private static final String INVESTIGATOR_FIELD_GROUP = "investigator";

    private ConfigProperty configurationProperty;

    private OrganizationDao organizationDao;
    private CSMUserRepository csmUserRepository;
    public ConfigProperty getConfigurationProperty() {
        return configurationProperty;
    }

    public void setConfigurationProperty(final ConfigProperty configProperty) {
        configurationProperty = configProperty;
    }

    public InvestigatorTab() {
        super("Investigator Details", "Investigator Details", "admin/investigator_details");
        setAutoPopulateHelpKey(true);
        /*addHelpKeyExclusion("firstName", "middleName", "lastName", "emailAddress", "phoneNumber",
                "faxNumber", "statusCode");*/
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
    	boolean remoteEntity = false;
    	if (command instanceof RemoteInvestigator) {
    		remoteEntity = true;
    	}
    	
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
        
        if (!remoteEntity) {
        	investigatorFieldGroup.getFields().add(InputFieldFactory.createTextField("firstName", "First Name", true));
        } else {
        	investigatorFieldGroup.getFields().add(InputFieldFactory.createLabelField("firstName", "First Name", true));
        }
        if (!remoteEntity) {
        	investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("middleName", "Middle Name", false));
        } else {
        	investigatorFieldGroup.getFields().add(
                    InputFieldFactory.createLabelField("middleName", "Middle Name", false));
        }
        if (!remoteEntity) {
        	investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("lastName", "Last Name", true));
        } else {
        	investigatorFieldGroup.getFields().add(
                    InputFieldFactory.createLabelField("lastName", "Last Name", true));
        }
        if (!remoteEntity) {
        	investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("nciIdentifier", "Investigator number",
                        false));
        } else {
        	investigatorFieldGroup.getFields().add(
                    InputFieldFactory.createLabelField("nciIdentifier", "Investigator number",
                            false));
        }
        InputField emailAddressField = null;
        if (!remoteEntity) {
        	emailAddressField = InputFieldFactory.createEmailField("emailAddress",
                "Email address", true);
        } else {
        	emailAddressField = InputFieldFactory.createLabelField("emailAddress",
                    "Email address", true);
        }
        // InputFieldAttributes.setSize(emailAddressField, 30);
        investigatorFieldGroup.getFields().add(emailAddressField);
        
        InputField phoneNumberField = null;
        if (!remoteEntity) {
        	phoneNumberField = InputFieldFactory.createPhoneField("phoneNumber", "Phone", true);
        	phoneNumberField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        } else {
        	phoneNumberField = InputFieldFactory.createLabelField("phoneNumber", "Phone", true);
        }                
        // InputFieldAttributes.setSize(phoneNumberField, 30);
        investigatorFieldGroup.getFields().add(phoneNumberField);
        
        InputField faxNumberField = null;
        if (!remoteEntity) {
        	faxNumberField = InputFieldFactory.createTextField("faxNumber", "Fax",
                FieldValidator.PHONE_VALIDATOR);
        	faxNumberField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        } else {
        	faxNumberField = InputFieldFactory.createLabelField("faxNumber", "Fax",
                    FieldValidator.PHONE_VALIDATOR);
        }
        
        // InputFieldAttributes.setSize(faxNumberField, 30);
        investigatorFieldGroup.getFields().add(faxNumberField);
        
        InputField loginIdField = InputFieldFactory.createTextField("loginId", "Grid identity", false);
        InputFieldAttributes.setSize(loginIdField, 30);
        investigatorFieldGroup.getFields().add(loginIdField);
        
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(investigatorFieldGroup);
        map.addRepeatingFieldGroupFactory(rfgFactory, command.getSiteInvestigators().size());

        return map;
    }

    @Override
    protected void validate(final Investigator command, final BeanWrapper commandBean,
                            final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {

        super.validate(command, commandBean, fieldGroups, errors);
        
        if (command ==null || command.getId() == null) {
        	String loginId = (StringUtils.isEmpty(command.getLoginId())) ? command.getEmailAddress() : command.getLoginId();
            boolean loginIdExists = csmUserRepository.loginIDInUse(loginId);
            if(loginIdExists){
            	 errors.reject("USR_001", new Object[]{loginId},  "Login ID or Email address already in use..!");
            }
        }
        
        List<SiteInvestigator> investigators = command.getSiteInvestigators();
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
    
    @Required
    public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}
}
