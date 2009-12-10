package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saurabh Agrawal
 */
public class InvestigatorTab extends TabWithFields<Investigator> {

    protected static final Log log = LogFactory.getLog(InvestigatorTab.class);

    private static final String INVESTIGATOR_FIELD_GROUP = "investigator";

    private ConfigProperty configurationProperty;
    private Configuration configuration;
    private OrganizationDao organizationDao;
    private CSMUserRepository csmUserRepository;
    private InvestigatorRepository investigatorRepository;
    
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
    public void postProcess(final HttpServletRequest request, final Investigator command, final Errors errors) {
        String action = request.getParameter("_action");
        String selected = request.getParameter("_selected");
        if ("removeInvestigator".equals(action)) {
            command.getSiteInvestigators().remove(Integer.parseInt(selected));
        }
    }

    protected Map<Object, Object> collectOptions(final List list, final String nameProperty, final String valueProperty) {
        Map<Object, Object> options = new LinkedHashMap<Object, Object>();
        options.put("", "Please select");
        options.putAll(WebUtils.collectOptions(list, nameProperty, valueProperty));
        return options;
    }

    protected Map<Object, Object> collectOptionsFromConfig(final String configPropertyName, final String nameProperty, final String valueProperty) {
        return collectOptions(configurationProperty.getMap().get(configPropertyName), nameProperty,valueProperty);
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, Investigator command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        Map<String, List<Lov>> configMap = getConfigurationProperty().getMap();

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
        
        InputField orgInputField = InputFieldFactory.createAutocompleterField("organization", "Organization", true);
    	//InputFieldAttributes.enableAutoCompleterClearButton(orgInputField);
        rfgFactory.addField(orgInputField);

        //startDate
        InputField startDateField = null;
        startDateField = InputFieldFactory.createDateField("startDate", "Start date", true);
        rfgFactory.addField(startDateField);
        InputFieldAttributes.setSize(startDateField, 10);
 
        //endDate
        InputField endDateField = null;
        endDateField = InputFieldFactory.createDateField("endDate", "End date", false);
        rfgFactory.addField(endDateField);       
        InputFieldAttributes.setSize(endDateField, 10);
        
        //status
        InputField statusField = null;
        statusField = InputFieldFactory.createLabelField("status", "Status", false);
        rfgFactory.addField(statusField);       
        InputFieldAttributes.setSize(statusField, 10);
        
        investigatorFieldGroup = new DefaultInputFieldGroup(INVESTIGATOR_FIELD_GROUP);
        
        if (!remoteEntity) {
        	investigatorFieldGroup.getFields().add(InputFieldFactory.createTextField("firstName", "First name", true));
        } else {
        	investigatorFieldGroup.getFields().add(InputFieldFactory.createLabelField("firstName", "First name", true));
        }
        if (!remoteEntity) {
        	investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("middleName", "Middle name", false));
        } else {
        	investigatorFieldGroup.getFields().add(
                    InputFieldFactory.createLabelField("middleName", "Middle name", false));
        }
        if (!remoteEntity) {
        	investigatorFieldGroup.getFields().add(
                InputFieldFactory.createTextField("lastName", "Last name", true));
        } else {
        	investigatorFieldGroup.getFields().add(
                    InputFieldFactory.createLabelField("lastName", "Last name", true));
        }
        
        InputField ncidIdField = null;

        if(remoteEntity){
        	ncidIdField = InputFieldFactory.createLabelField("nciIdentifier", "Investigator number", false);
        }else{
        	ncidIdField = InputFieldFactory.createTextField("nciIdentifier", "Investigator number", false);
        }

        InputFieldAttributes.setLabelProperty(ncidIdField, "investigator.nciIdentifier");
        investigatorFieldGroup.getFields().add(ncidIdField);
        
        InputField allowToLoginField = null;
        allowToLoginField = InputFieldFactory.createCheckboxField("allowedToLogin", "Allow Login");
        InputFieldAttributes.setLabelProperty(allowToLoginField, "investigator.allowedToLogin");
        investigatorFieldGroup.getFields().add(allowToLoginField);
        
        InputField emailAddressField = emailAddressField = InputFieldFactory.createEmailField("emailAddress","Email address", true);
        
        // InputFieldAttributes.setSize(emailAddressField, 30);
        investigatorFieldGroup.getFields().add(emailAddressField);
        
        InputField phoneNumberField = phoneNumberField = InputFieldFactory.createPhoneField("phoneNumber", "Phone", false);
        investigatorFieldGroup.getFields().add(phoneNumberField);
        
        InputField faxNumberField = faxNumberField = InputFieldFactory.createPhoneField("faxNumber", "Fax",false);
        investigatorFieldGroup.getFields().add(faxNumberField);
        
        InputField loginIdField = null;
        if(command.getWasLoginIdNull()){
        	loginIdField = InputFieldFactory.createTextField("loginId", "Username", true);
            InputFieldAttributes.setSize(loginIdField, 30);
        }else{
        	loginIdField = InputFieldFactory.createLabelField("loginId", "Username", false);
        }
        investigatorFieldGroup.getFields().add(loginIdField);
        
        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(investigatorFieldGroup);
        map.addRepeatingFieldGroupFactory(rfgFactory, command.getSiteInvestigators().size());

        return map;
    }

    /**
     * Will validate the following when an investigator is allowed to login.
     *  1. UserName must be provided and should be unique.
     *  2. NCI identifier must be provided and should be unique. 
     *  3. Start and End date validation
     */
    @Override
    protected void validate(final Investigator command, final BeanWrapper commandBean,
                            final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        
        //Allowed to login checked
        if(command.getAllowedToLogin()) {
        	
        	if(StringUtils.isEmpty(command.getLoginId())){
        		errors.rejectValue("loginId", "USR_014", "User name must not be empty, while allowed to login.");
        	}else{
        		try {
					//login id should be unique. 
					User anotherUser = csmUserRepository.getUserByName(command.getLoginId());
					if(anotherUser != null && !ObjectUtils.equals(command.getId(), anotherUser.getId())){
						errors.rejectValue("loginId","USR_001", new Object[]{command.getLoginId()},"The loginId is in use.");
					}
				} catch (CaaersNoSuchUserException e) {
				}
        	}
        	
        	// Check if there is another investigator with same primary email-address.
            InvestigatorQuery investigatorQuery = new InvestigatorQuery();
            investigatorQuery.filterByEmailAddress(command.getEmailAddress());
            List<Investigator> investigatorList = investigatorRepository.searchInvestigator(investigatorQuery);
            
            if(investigatorList.size() > 1)
            	errors.rejectValue("emailAddress", "USR_010");
            
            if(investigatorList.size() == 1 && command.getId() == null){
            	errors.rejectValue("emailAddress", "USR_010");
            }
        	
        }
        
        /*
        
        if (command ==null || command.getId() == null) {
        	if(command.getAllowedToLogin()){
            	String loginId = (StringUtils.isEmpty(command.getLoginId())) ? command.getEmailAddress() : command.getLoginId();
                boolean loginIdExists = csmUserRepository.loginIDInUse(loginId);
                if(loginIdExists){
                	 errors.reject("USR_001", new Object[]{loginId},  "Username already in use..!");
                }
        	}else{
        		String emailAddress = command.getEmailAddress();
                boolean loginIdExists = csmUserRepository.loginIDInUse(emailAddress);
                if(loginIdExists){
                	 errors.reject("USR_001", new Object[]{emailAddress},  "Username address already in use..!");
                }
        	}
        }
        
     
       */
        
        List<SiteInvestigator> investigators = command.getSiteInvestigators();
        Date now = new Date();
        for (int i = 0; i < investigators.size(); i++) {
            SiteInvestigator siteInvestigator = investigators.get(i);
            
            if(siteInvestigator.getId() == null){
            	//startdate cannot be less than today's date
                if(siteInvestigator.getStartDate() != null){
                	if(DateUtils.compareDate(siteInvestigator.getStartDate(),now) < 0){
                		errors.rejectValue("siteInvestigators["+i+"].startDate","INV_001","Start date cannot be before today's date.");
                	}
                }
                
                if(siteInvestigator.getEndDate() != null){
                	if(DateUtils.compareDate(siteInvestigator.getEndDate(),now) < 0){
                    	errors.rejectValue("siteInvestigators["+i+"].endDate","INV_003","End date cannot be before today's date.");
                    }
                }
            }
            
            if(siteInvestigator.getStartDate() != null && siteInvestigator.getEndDate() != null){
            	if(DateUtils.compareDate(siteInvestigator.getEndDate(), siteInvestigator.getStartDate()) < 0){
            		errors.rejectValue("siteInvestigators["+i+"].endDate","INV_004","End date cannot be before Start date.");
            	}
            }
        }
    }
    
    public ModelAndView addSiteInvestigator(HttpServletRequest request , Object cmd, Errors errors){
    	Investigator command =(Investigator)cmd;
    	List<SiteInvestigator> siteInvestigators = command.getSiteInvestigators();
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/addSiteInvestigatorSection");
    	//SiteInvestigator siteInvestigator = new SiteInvestigator();
    	//command.addSiteInvestigator(siteInvestigator);
    	modelAndView.getModel().put("siteInvestigators", siteInvestigators);
    	int size = siteInvestigators.size();
    	modelAndView.getModel().put("index", size);
    	
    	return modelAndView;
    }
    
    public ModelAndView removeSiteInvestigator(HttpServletRequest request , Object cmd, Errors errors){
    	Investigator command =(Investigator)cmd;
    	List<SiteInvestigator> siteInvestigators = command.getSiteInvestigators();
    	siteInvestigators.remove(siteInvestigators.get(Integer.parseInt(request.getParameter("index")))); //remove the element
    	
    	ModelAndView modelAndView = new ModelAndView("par/ajax/removeSiteInvestigatorSection");
    	modelAndView.getModel().put("siteInvestigators", siteInvestigators);
    	
    	return modelAndView;
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
    
    @Required
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public InvestigatorRepository getInvestigatorRepository() {
		return investigatorRepository;
	}
	public void setInvestigatorRepository(
			InvestigatorRepository investigatorRepository) {
		this.investigatorRepository = investigatorRepository;
	}
	
}
