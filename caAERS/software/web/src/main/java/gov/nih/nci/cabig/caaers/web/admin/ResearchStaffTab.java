/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.RemoteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.web.fields.*;
import gov.nih.nci.cabig.caaers.web.fields.validators.FieldValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Ram Seethiraju
 */
public class ResearchStaffTab extends TabWithFields<ResearchStaffCommand> {

    protected static final Log log = LogFactory.getLog(ResearchStaffTab.class);
    private static final String RESEARCH_STAFF_FIELD_GROUP = "researchStaff";
    Map<String, String> methodNameMap = new HashMap<String, String>();
    private CSMUserRepository csmUserRepository;
    private ResearchStaffRepository researchStaffRepository;

    public ResearchStaffTab() {
        super("Research Staff Details", "Research Staff Details", "admin/researchStaff");
        setAutoPopulateHelpKey(true);
        methodNameMap.put("addsiteResearchStaff", "addSiteResearchStaff");
        methodNameMap.put("removesiteResearchStaff", "removeSiteResearchStaff");
        methodNameMap.put("UnlockUser", "unlockUser");
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
        boolean hasSRSRUpdate = CaaersSecurityFacadeImpl.getInstance().checkAuthorization(SecurityUtils.getAuthentication(), "gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole", "UPDATE");
        boolean hasRSCreate = CaaersSecurityFacadeImpl.getInstance().checkAuthorization(SecurityUtils.getAuthentication(), "gov.nih.nci.cabig.caaers.domain.ResearchStaff", "CREATE");

        super.validate(command, commandBean, fieldGroups, errors);

        String em = command.getResearchStaff().getEmailAddress();
        if (hasRSCreate && em != null && !em.trim().equals("") && !GenericValidator.isEmail(em)) {
            errors.rejectValue("researchStaff.emailAddress", "USR_006", "Invalid email");
        }

        List<SiteResearchStaff> srs = command.getResearchStaff().getSiteResearchStaffs();
        for (int i=0; i<srs.size(); i++) {
            if (hasRSCreate && (srs.get(i).getOrganization() == null || srs.get(i).getOrganization().getId() == null))
                errors.reject("USR_004", new Object[] {new Integer(i)}, "Provide the organization");

            String email = srs.get(i).getEmailAddress();
            if (hasRSCreate && email != null && !email.trim().equals("") && !GenericValidator.isEmail(email))
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
                    if (!hasRoles && hasRSCreate) errors.reject("USR_003", "Please provide research staff roles for every organization.");
                    i++;
                }
            }
        } else {
            if (hasRSCreate)
                errors.reject("USR_005", "Provide at least one  organization");
        }

        // validate only create mode
        if (command.getResearchStaff().getId() == null) {
        	String loginId = command.getResearchStaff().getLoginId();
            boolean loginIdExists =  false;
            if(StringUtils.isNotBlank(loginId)){

                try{
                    User user = csmUserRepository.getUserByName(loginId);
                    loginIdExists = user != null;
                }catch(CaaersNoSuchUserException ignore){

                }
            }

            if(loginIdExists) {
                 command.setCanSync(false);
                 command.setShouldSync(false);
            	 errors.reject("USR_001", new Object[]{loginId},  "Username already in use..!");
            }
        }

        // Check if there is another research staff with same primary email-address.
        ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
        researchStaffQuery.filterByEmailAddress(command.getResearchStaff().getEmailAddress());
        List<ResearchStaff> researchStaffList = researchStaffRepository.searchResearchStaff(researchStaffQuery);
        
        if (researchStaffList.size() > 0) {
            if (command.getResearchStaff().getId() == null) {
                errors.rejectValue("researchStaff.emailAddress", "USR_010");
            } else {
                for (ResearchStaff rs : researchStaffList) {
                    if (!command.getResearchStaff().getId().equals(rs.getId())) {
                        errors.rejectValue("researchStaff.emailAddress", "USR_010");
                        break;
                    }
                }
            }
        }

        // DATES validation
        if (hasSRSRUpdate) {
            byte i = 0;
            if (command.getSiteResearchStaffCommandHelper() != null) {
                for (SiteResearchStaffCommandHelper srsch : command.getSiteResearchStaffCommandHelper()) {
                    byte j = 0;
                        for (SiteResearchStaffRoleCommandHelper srsrch : srsch.getRsRoles()) {
                            if (srsrch.getChecked()) {
                                if (srsrch.getStartDate() != null && srsrch.getEndDate() != null && DateUtils.compareDate(srsrch.getEndDate(), srsrch.getStartDate()) < 0){
                                    errors.rejectValue(String.format("siteResearchStaffCommandHelper[%d].rsRoles[%d].endDate", i, j),"USR_009","End date cannot be before Start date.");
                                }
                            }
                            j++;
                        }
                    i++;
                }
            }
        }
        
    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final ResearchStaffCommand command) {
        boolean hasSRSRUpdate = CaaersSecurityFacadeImpl.getInstance().checkAuthorization(SecurityUtils.getAuthentication(), "gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole", "UPDATE");
               
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

        InputField emailField = InputFieldFactory.createTextField("researchStaff.emailAddress", "Primary email", FieldValidator.NOT_NULL_VALIDATOR, FieldValidator.EMAIL_VALIDATOR);
        InputFieldAttributes.setSize(emailField, 30);
        researchStaffFieldGroup.getFields().add(emailField);

        InputField loginIdField = InputFieldFactory.createTextField("researchStaff.loginId", "Login ID", hasSRSRUpdate);
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
    
    public ResearchStaffRepository getResearchStaffRepository() {
		return researchStaffRepository;
	}
    
    public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

    @Override
    public String getMethodName(HttpServletRequest request) {
    	String currentItem = request.getParameter("currentItem");
    	String task = request.getParameter("task");
        log.debug(methodNameMap.get(task + currentItem));
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
    
    //
    public ModelAndView removeSiteResearchStaff(HttpServletRequest request, Object object, Errors errors) {
        ResearchStaffCommand  command = (ResearchStaffCommand)object;
        ModelAndView modelAndView = new ModelAndView("admin/ajax/researchStaffFormSection");

        // DELETE
        int index;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (NumberFormatException e) {
            index = -1;
            log.debug("Wrong <index> for <ResearchStaff.SiteResearchStaff> list: " + e.getMessage());
        }

        if (index >= 0) {
            command.getResearchStaff().getSiteResearchStaffs().remove(index);
            command.getSiteResearchStaffCommandHelper().remove(index);
        }
        // DELETE

        int size = command.getResearchStaff().getSiteResearchStaffs().size();
        Integer[] indexes = new Integer[size];
        for(int i = 0 ; i < size ; i++) {
            indexes[i] = size - (i + 1);
        }
        
        modelAndView.getModel().put("indexes", indexes);
        return modelAndView;
    }

    /**
     * This method unlocks the user and saves the status of the user when UNLOCK button is clicked in the ReseachStaff edit mode
     * @param request
     * @param cmd
     * @param errors
     * @return
     */
    public ModelAndView unlockUser(HttpServletRequest request , Object cmd, Errors errors){
    	ResearchStaffCommand command = (ResearchStaffCommand)cmd;
//    	researchStaffRepository.unlockResearchStaff(command.getResearchStaff());
    	
    	ModelAndView modelAndView = new ModelAndView("");
    	//modelAndView.getModel().get(getFreeTextModelName());
    	modelAndView.getModel().put(getFreeTextModelName(),"Unlocked");
    	return modelAndView;
    }

}
