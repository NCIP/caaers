package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.ae.ExpeditedAdverseEventInputCommand;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Saurabh Agrawal
 */
public class ResearchStaffTab extends TabWithFields<ResearchStaffCommand> {

    protected static final Log log = LogFactory.getLog(ResearchStaffTab.class);
    private static final String RESEARCH_STAFF_FIELD_GROUP = "researchStaff";

    Map<String, String> methodNameMap = new HashMap<String, String>();
    
    private CSMUserRepository csmUserRepository;

    public ResearchStaffTab() {
        super("Research Staff Details", "Research Staff Details", "admin/researchStaff");
        setAutoPopulateHelpKey(true);
        methodNameMap.put("addsiteResearchStaff", "addSiteResearchStaff");
    }

    @Override
    public Map<String, Object> referenceData(HttpServletRequest request, ResearchStaffCommand command) {
        Map<String, Object> refdata = super.referenceData(request, command);
        refdata.put("allRoles", command.getAllRoles());
        return refdata;
    }

    @Override
    public void onBind(HttpServletRequest request, ResearchStaffCommand command, Errors errors) {
        super.onBind(request, command, errors);
        for (SiteResearchStaff rss: command.getResearchStaff().getSiteResearchStaffs()) {
            // System.out.println(rss.getOrganization().getFullName());
        }

        // populate the user groups correctly.
        // command.getResearchStaff().getUserGroupTypes().clear();
/*
        for (command.getConfigPropertyRepository().getByType(ConfigPropertyType.RESEARCH_STAFF_ROLE_TYPE) type : ) {
            if (BooleanUtils.toBoolean(request.getParameter(type.name()))) {
                command.getResearchStaff().getUserGroupTypes().add(UserGroupType.valueOf(type.name()));
            }
        }
*/
    }

    @Override
    protected void validate(final ResearchStaffCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        HashSet<String> set = new HashSet<String>();
        // List<UserGroupType> userGroupTypes = command.getResearchStaff().getUserGroupTypes();

/*
        if (userGroupTypes == null || userGroupTypes.isEmpty()) {
            errors.reject("USR_002", "You must select at least one user role..!");
        }
St*/

        if (command == null || command.getResearchStaff().getId() == null) {
        	String loginId = (StringUtils.isEmpty(command.getResearchStaff().getLoginId())) ? command.getResearchStaff().getEmailAddress() : command.getResearchStaff().getLoginId();
            boolean loginIdExists = csmUserRepository.loginIDInUse(loginId);
            if(loginIdExists){
            	 errors.reject("USR_001", new Object[]{loginId},  "Username or Email address already in use..!");
            }
        }
        errors.reject("USR_002", "STOP here on red.");
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final ResearchStaffCommand command) {
        boolean remoteEntity = false;
        if (command.getResearchStaff() instanceof RemoteResearchStaff) {
            remoteEntity = true;
        }
        InputFieldGroup researchStaffFieldGroup;

        researchStaffFieldGroup = new DefaultInputFieldGroup(RESEARCH_STAFF_FIELD_GROUP);
        InputField firstNameField = null;
        if (!remoteEntity) {
            firstNameField = InputFieldFactory.createTextField("researchStaff.firstName", "First name", true);
        } else {
            firstNameField = InputFieldFactory.createLabelField("researchStaff.firstName", "First name", true);
        }
        InputFieldAttributes.setSize(firstNameField, 30);
        researchStaffFieldGroup.getFields().add(firstNameField);

        InputField middleNameField = null;
        if (!remoteEntity) {
            middleNameField = InputFieldFactory.createTextField("researchStaff.middleName", "Middle name", false);
        } else {
            middleNameField = InputFieldFactory.createLabelField("researchStaff.middleName", "Middle name", false);
        }
        InputFieldAttributes.setSize(middleNameField, 30);
        researchStaffFieldGroup.getFields().add(middleNameField);

        InputField lastNameField = null;
        if (!remoteEntity) {
            lastNameField = InputFieldFactory.createTextField("researchStaff.lastName", "Last name", true);
        } else {
            lastNameField = InputFieldFactory.createLabelField("researchStaff.lastName", "Last name", true);
        }
        InputFieldAttributes.setSize(lastNameField, 30);
        researchStaffFieldGroup.getFields().add(lastNameField);

        InputField loginIdField = InputFieldFactory.createTextField("researchStaff.loginId", "Username", true);
        InputFieldAttributes.setSize(loginIdField, 30);
        researchStaffFieldGroup.getFields().add(loginIdField);

        InputFieldGroupMap map = new InputFieldGroupMap();
        map.addInputFieldGroup(researchStaffFieldGroup);
        // map.addInputFieldGroup(siteFieldGroup);

        return map;
    }

    @Required
    public void setCsmUserRepository(CSMUserRepository csmUserRepository) {
		this.csmUserRepository = csmUserRepository;
	}

    @Override
    protected boolean methodInvocationRequest(HttpServletRequest request) {
        System.out.println("--- methodInvocationRequest");
    	return org.springframework.web.util.WebUtils.hasSubmitParameter(request, "currentItem") && org.springframework.web.util.WebUtils.hasSubmitParameter(request, "task");
    }

    @Override
    public String getMethodName(HttpServletRequest request) {
        System.out.println("--- getMethodName");
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
    	return methodNameMap.get(task + currentItem);
    }

    //
    public ModelAndView addSiteResearchStaff(HttpServletRequest request, Object object, Errors errors) {
        System.out.println("--- addSiteResearchStaff");
        System.err.println("addSiteResearchStaff");

        ResearchStaffCommand  command = (ResearchStaffCommand)object;
        SiteResearchStaff srs = new SiteResearchStaff();
        command.getResearchStaff().addSiteResearchStaff(srs);
        srs.setResearchStaff(command.getResearchStaff());

        ModelAndView modelAndView = new ModelAndView("admin/ajax/researchStaffFormSection");
        // modelAndView.getModel().put("objects", command.getResearchStaff().getSiteResearchStaffs());
        modelAndView.getModel().put("indexes", new Integer[]{command.getResearchStaff().getSiteResearchStaffs().size() - 1});
        return modelAndView;
    }


}
