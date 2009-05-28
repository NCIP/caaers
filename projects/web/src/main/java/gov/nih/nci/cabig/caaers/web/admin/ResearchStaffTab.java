package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

/**
 * @author Saurabh Agrawal
 */
public class ResearchStaffTab extends TabWithFields<ResearchStaff> {

    protected static final Log log = LogFactory.getLog(ResearchStaffTab.class);
    private static final String RESEARCH_STAFF_FIELD_GROUP = "researchStaff";
    private static final String SITE_FIELD_GROUP = "site";

    private static final UserGroupType[] ASSIGNABLE_USER_GROUP_TYPES = {UserGroupType.caaers_ae_cd, 
    	UserGroupType.caaers_participant_cd, 
    	UserGroupType.caaers_site_cd, 
    	UserGroupType.caaers_study_cd,
    	UserGroupType.caaers_central_office_sae_cd,
    	UserGroupType.caaers_data_cd};
    private CSMUserRepository csmUserRepository;

    public ResearchStaffTab() {
        super("Research Staff Details", "Research Staff Details", "admin/research_staff_details");
        setAutoPopulateHelpKey(true);
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ResearchStaff staff) {
        Map<String, Object> refdata = super.referenceData(request, staff);
        // populate information related to USER Groups
        for (UserGroupType type : ASSIGNABLE_USER_GROUP_TYPES) {
            refdata.put(type.name(), staff.isAssociatedToUserGroup(type));
        }
        return refdata;
    }

    @Override
    public void onBind(HttpServletRequest request, ResearchStaff staff, Errors errors) {
        super.onBind(request, staff, errors);

        // populate the user groups correctly.
        staff.getUserGroupTypes().clear();
        for (UserGroupType type : ASSIGNABLE_USER_GROUP_TYPES) {
            if (BooleanUtils.toBoolean(request.getParameter(type.name()))) {
                staff.getUserGroupTypes().add(UserGroupType.valueOf(type.name()));
            }
        }
    }

    @Override
    protected void validate(final ResearchStaff command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        HashSet<String> set = new HashSet<String>();
        List<UserGroupType> userGroupTypes = command.getUserGroupTypes();
        if (userGroupTypes == null || userGroupTypes.isEmpty()) {
            errors.reject("USR_002", "You must select at least one user role..!");

        }
        if (command ==null || command.getId() == null) {
        	String loginId = (StringUtils.isEmpty(command.getLoginId())) ? command.getEmailAddress() : command.getLoginId();
            boolean loginIdExists = csmUserRepository.loginIDInUse(loginId);
            if(loginIdExists){
            	 errors.reject("USR_001", new Object[]{loginId},  "Username or Email address already in use..!");
            }
        }
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final ResearchStaff command) {
    	boolean remoteEntity = false;
    	if (command instanceof RemoteResearchStaff) {
    		remoteEntity = true;
    	}
        InputFieldGroup researchStaffFieldGroup;

        InputFieldGroup siteFieldGroup;

        siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);
        if (!remoteEntity) {
        	InputField orgInputField = InputFieldFactory.createAutocompleterField("organization", "Organization", true);
        	InputFieldAttributes.enableAutoCompleterClearButton(orgInputField);
        	siteFieldGroup.getFields().add(orgInputField);
        } else {
        	siteFieldGroup.getFields().add(InputFieldFactory.createLabelField("organization.name", "Organization", true));
        }
        
        researchStaffFieldGroup = new DefaultInputFieldGroup(RESEARCH_STAFF_FIELD_GROUP);
        InputField firstNameField = null;
        if (!remoteEntity) {
        	 firstNameField = InputFieldFactory.createTextField("firstName", "First name", true);
        } else {
        	 firstNameField = InputFieldFactory.createLabelField("firstName", "First name", true);
        }        
        InputFieldAttributes.setSize(firstNameField, 30);
        researchStaffFieldGroup.getFields().add(firstNameField);

        InputField middleNameField = null;
        if (!remoteEntity) {
           middleNameField = InputFieldFactory.createTextField("middleName", "Middle name", false);
        } else {
    	   middleNameField = InputFieldFactory.createLabelField("middleName", "Middle name", false);
        }  
        InputFieldAttributes.setSize(middleNameField, 30);
        researchStaffFieldGroup.getFields().add(middleNameField);

        InputField lastNameField = null;
        if (!remoteEntity) {
        	lastNameField = InputFieldFactory.createTextField("lastName", "Last name", true);
        } else {
        	lastNameField = InputFieldFactory.createLabelField("lastName", "Last name", true);
        }
        InputFieldAttributes.setSize(lastNameField, 30);
        researchStaffFieldGroup.getFields().add(lastNameField);

        
        InputField ncidIdField = null;
        if (!remoteEntity) {
        	ncidIdField = InputFieldFactory.createTextField("nciIdentifier", "Researcher ID", false);
        } else {
        	ncidIdField = InputFieldFactory.createLabelField("nciIdentifier", "Researcher ID", false);
        }
        InputFieldAttributes.setSize(ncidIdField, 30);
        researchStaffFieldGroup.getFields().add(ncidIdField);

        InputField emailAddressField = null;
        if (!remoteEntity) {
        	emailAddressField = InputFieldFactory.createEmailField("emailAddress", "Email address", true);
        } else {
        	emailAddressField = InputFieldFactory.createLabelField("emailAddress", "Email address", true);
        }
        InputFieldAttributes.setSize(emailAddressField, 30);
        researchStaffFieldGroup.getFields().add(emailAddressField);

        
        InputField phoneNumberField = null;
        if (!remoteEntity) {
        	phoneNumberField = InputFieldFactory.createPhoneField("phoneNumber", "Phone", true);
        } else {
        	phoneNumberField = InputFieldFactory.createLabelField("phoneNumber", "Phone", false);
        }        
        InputFieldAttributes.setSize(phoneNumberField, 30);        
        researchStaffFieldGroup.getFields().add(phoneNumberField);

        
        InputField faxNumberField = null;
        if (!remoteEntity) {
        	faxNumberField = InputFieldFactory.createPhoneField("faxNumber", "Fax", false);
        } else {
        	faxNumberField = InputFieldFactory.createLabelField("faxNumber", "Fax", false);
        }
        InputFieldAttributes.setSize(faxNumberField, 30);        
        researchStaffFieldGroup.getFields().add(faxNumberField);
        
        
        InputField loginIdField = InputFieldFactory.createTextField("loginId", "Username", true);
        InputFieldAttributes.setSize(loginIdField, 30);
        researchStaffFieldGroup.getFields().add(loginIdField);

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(researchStaffFieldGroup);
        map.addInputFieldGroup(siteFieldGroup);

        return map;
    }


    
    @Required
    public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}

}
