package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.security.UserProvisioningManager;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Saurabh Agrawal
 */
public class ResearchStaffTab extends TabWithFields<ResearchStaff> {

    protected static final Log log = LogFactory.getLog(ResearchStaffTab.class);

    private static final String RESEARCH_STAFF_FIELD_GROUP = "researchStaff";

    private static final String SITE_FIELD_GROUP = "site";

    private static final UserGroupType[] ASSIGNABLE_USER_GROUP_TYPES = {
            UserGroupType.caaers_ae_cd, UserGroupType.caaers_participant_cd,
            UserGroupType.caaers_site_cd, UserGroupType.caaers_study_cd};
    private ResearchStaffDao researchStaffDao;

    public ResearchStaffTab() {
        super("Research Staff Details", "Details", "admin/research_staff_details");
        setAutoPopulateHelpKey(true);
        /*addHelpKeyExclusion("firstName", "middleName", "lastName", "emailAddress", "phoneNumber",
                "faxNumber");*/
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
    protected void validate(final ResearchStaff command, final BeanWrapper commandBean,
                            final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        HashSet<String> set = new HashSet<String>();
        List<UserGroupType> userGroupTypes = command.getUserGroupTypes();
        if (userGroupTypes == null || userGroupTypes.isEmpty()) {
            errors.reject("REQUIRED", "You must select at least one user role..!");

        }

        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        researchStaffQuery.filterByEmailAddressOrLoginId(command.getEmailAddress());
        List<ResearchStaff> researchStaffList = researchStaffDao
                .searchResearchStaff(researchStaffQuery);
        for (ResearchStaff researchStaff : researchStaffList) {
            if (!researchStaff.getId().equals(command.getId())) {
                errors.reject("REQUIRED", "Email address already exits in database..!");
                break;
            }
        }
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final ResearchStaff command) {
        InputFieldGroup researchStaffFieldGroup;

        InputFieldGroup siteFieldGroup;

        siteFieldGroup = new DefaultInputFieldGroup(SITE_FIELD_GROUP);
        siteFieldGroup.getFields().add(
                InputFieldFactory.createAutocompleterField("organization", "Organization",
                        true));
        researchStaffFieldGroup = new DefaultInputFieldGroup(RESEARCH_STAFF_FIELD_GROUP);

        InputField firstNameField = InputFieldFactory.createTextField("firstName", "First name",
                true);
        InputFieldAttributes.setSize(firstNameField, 30);
        researchStaffFieldGroup.getFields().add(firstNameField);

        InputField middleNameField = InputFieldFactory.createTextField("middleName", "Middle name",
                false);
        InputFieldAttributes.setSize(middleNameField, 30);
        researchStaffFieldGroup.getFields().add(middleNameField);

        InputField lastNameField = InputFieldFactory.createTextField("lastName", "Last name", true);
        InputFieldAttributes.setSize(lastNameField, 30);
        researchStaffFieldGroup.getFields().add(lastNameField);

        InputField ncidIdField = InputFieldFactory.createTextField("nciIdentifier",
                "Researcher ID", false);
        InputFieldAttributes.setSize(ncidIdField, 30);
        researchStaffFieldGroup.getFields().add(ncidIdField);

        InputField emailAddressField = InputFieldFactory.createEmailField("emailAddress",
                "Email address", true);
        InputFieldAttributes.setSize(emailAddressField, 30);
        researchStaffFieldGroup.getFields().add(emailAddressField);

        InputField phoneNumberField = InputFieldFactory.createPhoneField("phoneNumber", "Phone",
                true);
        InputFieldAttributes.setSize(phoneNumberField, 30);
        phoneNumberField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        researchStaffFieldGroup.getFields().add(phoneNumberField);

        InputField faxNumberField = InputFieldFactory.createPhoneField("faxNumber", "Fax",
                false);
        InputFieldAttributes.setSize(faxNumberField, 30);
        faxNumberField.getAttributes().put(InputField.EXTRA_VALUE_PARAMS, "phone-number");
        researchStaffFieldGroup.getFields().add(faxNumberField);

        InputField loginIdField = InputFieldFactory.createTextField("loginId",
                "Grid identity", false);
        InputFieldAttributes.setSize(loginIdField, 30);
        researchStaffFieldGroup.getFields().add(loginIdField);

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(researchStaffFieldGroup);
        map.addInputFieldGroup(siteFieldGroup);

        return map;
    }


    @Required
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

  }
