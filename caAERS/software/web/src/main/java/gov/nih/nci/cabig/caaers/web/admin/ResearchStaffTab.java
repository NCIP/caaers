package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import com.semanticbits.rules.utils.DateUtil;

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

        if (request.getParameter("_action") != null && (request.getParameter("_action").equals("deactivate") || request.getParameter("_action").equals("activate"))) {

            boolean activate = request.getParameter("_action").equals("activate");

            int srsID = Integer.parseInt(request.getParameter("srsID"));
            String srsrID = request.getParameter("srsrID");

            ResearchStaff rs = command.getResearchStaff();
            SiteResearchStaffCommandHelper siteResearchStaff = null;

            if (srsID > 0) {
                for (SiteResearchStaffCommandHelper srsch : command.getSiteResearchStaffCommandHelper()) {
                    if (srsch.getId() == srsID) { siteResearchStaff = srsch; break;}
                }
            }

            // role click
            if (srsID > 0 && !srsrID.equals("0")) {
                for (SiteResearchStaffRoleCommandHelper srsrch : siteResearchStaff.getRsRoles()) {
                    if (srsrch.getRoleCode().equals(srsrID)) {
                        if (!activate) srsrch.setEndDate(DateUtils.yesterday());
                        else {
                            srsrch.setStartDate(DateUtils.today());
                            srsrch.setEndDate(null);
                        }
                    }
                }
            } else if (srsID > 0 && srsrID.equals("")) {
                for (SiteResearchStaffRoleCommandHelper srsrch : siteResearchStaff.getRsRoles()) {
                    if (!activate) srsrch.setEndDate(DateUtils.yesterday());
                    else {
                        srsrch.setStartDate(DateUtils.today());
                        srsrch.setEndDate(null);
                    }
                }
            } else {
                // ResearchStaff Click
                for (SiteResearchStaffCommandHelper srsch : command.getSiteResearchStaffCommandHelper()) {
                    for (SiteResearchStaffRoleCommandHelper srsrch : srsch.getRsRoles()) {
                        if (!activate) srsrch.setEndDate(DateUtils.yesterday());
                        else {
                            srsrch.setStartDate(DateUtils.today());
                            srsrch.setEndDate(null);
                        }
                    }
                }
            }
        }
        
    }

    @Override
    protected void validate(final ResearchStaffCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {
        super.validate(command, commandBean, fieldGroups, errors);
        HashSet<String> set = new HashSet<String>();

        List<SiteResearchStaff> srs = command.getResearchStaff().getSiteResearchStaffs();
        for (int i=0; i<srs.size(); i++) {
            if (srs.get(i).getOrganization() == null || srs.get(i).getOrganization().getId() == null)
                errors.reject("USR_004", new Object[] {new Integer(i)}, "Provide the organization");

            String email = srs.get(i).getEmailAddress();
            if (email != null && !email.trim().equals("") && !GenericValidator.isEmail(email))
                errors.rejectValue(String.format("researchStaff.siteResearchStaffs[%d].emailAddress", i), "USR_006", "Invalid email");
        }


        if (command.getSiteResearchStaffCommandHelper() != null) {
            if (command.getSiteResearchStaffCommandHelper().size() != command.getResearchStaff().getSiteResearchStaffs().size()) {
                log.error("Error while tryign to get the prepopulated roles for SiteResearchStaff objects");
            } else {
                byte i = 0;
                for (SiteResearchStaffCommandHelper srsch : command.getSiteResearchStaffCommandHelper()) {
                    boolean hasRoles = false;
                    if (srsch == null || srsch.getRsRoles() == null) {
                        log.error("Error while tryign to get the prepopulated roles for SiteResearchStaff objects");
                    } else {
                        for (SiteResearchStaffRoleCommandHelper srsrch : srsch.getRsRoles()) {
                            if (srsrch.getChecked()) hasRoles = true;
                        }
                    }
                    if (!hasRoles) errors.reject("USR_003", "Please provide research staff roles for every organization.");
                    i++;
                }
            }
        } else {
            errors.reject("USR_005", "Provide at least one  organization");
        }

        // validate only create mode
        if (command.getResearchStaff().getId() == null) {
        	String loginId = command.getResearchStaff().getLoginId();
            boolean loginIdExists = csmUserRepository.loginIDInUse(loginId);
            if(loginIdExists) {
            	 errors.reject("USR_001", new Object[]{loginId},  "Username or Email address already in use..!");
            }
        }


        byte i = 0;
        for (SiteResearchStaffCommandHelper srsch : command.getSiteResearchStaffCommandHelper()) {
            byte j = 0;
                for (SiteResearchStaffRoleCommandHelper srsrch : srsch.getRsRoles()) {
                    if (srsrch.getChecked()) {
/*
                        if (srsrch.getStartDate() != null && DateUtils.compareDate(srsrch.getStartDate(), DateUtils.today()) < 0){
                            errors.rejectValue(String.format("siteResearchStaffCommandHelper[%d].rsRoles[%d].startDate", i, j), "USR_007", "Start date cannot be before today's date.");
                        }
                        if (srsrch.getEndDate() != null && DateUtils.compareDate(srsrch.getEndDate(), DateUtils.today()) < 0){
                            errors.rejectValue(String.format("siteResearchStaffCommandHelper[%d].rsRoles[%d].endDate", i, j), "USR_008", "End date cannot be before today's date.");
                        }
*/
                        if (srsrch.getStartDate() != null && srsrch.getEndDate() != null && DateUtils.compareDate(srsrch.getEndDate(), srsrch.getStartDate()) < 0){
                            errors.rejectValue(String.format("siteResearchStaffCommandHelper[%d].rsRoles[%d].endDate", i, j),"USR_009","End date cannot be before Start date.");
                        }
                    }
                    j++;
                }
            i++;
        }
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

        InputField emailField = InputFieldFactory.createTextField("researchStaff.emailAddress", "Primary email", true);
        InputFieldAttributes.setSize(emailField, 30);
        researchStaffFieldGroup.getFields().add(emailField);

        InputField loginIdField = InputFieldFactory.createTextField("researchStaff.loginId", "Login ID", true);
        InputFieldAttributes.setSize(loginIdField, 30);
        researchStaffFieldGroup.getFields().add(loginIdField);

/*
        InputField activeDate = InputFieldFactory.createDateField("researchStaff.activeDate", "Active date", true);
        InputFieldAttributes.setSize(activeDate, 10);
        researchStaffFieldGroup.getFields().add(activeDate);
*/

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
    	return org.springframework.web.util.WebUtils.hasSubmitParameter(request, "currentItem") && org.springframework.web.util.WebUtils.hasSubmitParameter(request, "task");
    }

    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
        System.out.println(methodNameMap.get(task + currentItem));
    	return methodNameMap.get(task + currentItem);
    }

    //
    public ModelAndView addSiteResearchStaff(HttpServletRequest request, Object object, Errors errors) {
        ResearchStaffCommand  command = (ResearchStaffCommand)object;
        SiteResearchStaff srs = new SiteResearchStaff();
        command.getResearchStaff().addSiteResearchStaff(srs);
        srs.setResearchStaff(command.getResearchStaff());
        command.addSiteResearchStaffCommandHelper();

        ModelAndView modelAndView = new ModelAndView("admin/ajax/researchStaffFormSection");
        // modelAndView.getModel().put("objects", command.getResearchStaff().getSiteResearchStaffs());
        modelAndView.getModel().put("indexes", new Integer[]{command.getResearchStaff().getSiteResearchStaffs().size() - 1});
        return modelAndView;
    }

}
