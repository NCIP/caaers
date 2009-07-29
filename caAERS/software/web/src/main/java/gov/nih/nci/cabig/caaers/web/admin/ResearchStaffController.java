package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaffRole;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepositoryImpl;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.user.ResetPasswordController;
import gov.nih.nci.cabig.ctms.editors.DaoBasedEditor;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a ResearchStaff
 * Design. This uses AbstractTabbedFlowFormController to implement tabbed workflow
 *
 * @author Saurabh
 */
public abstract class ResearchStaffController<C extends ResearchStaffCommand> extends AutomaticSaveAjaxableFormController<C, ResearchStaff, ResearchStaffDao> {

    public static final String AJAX_SUBVIEW_PARAMETER = "subview";
    private static final Log log = LogFactory.getLog(ResearchStaffController.class);
    protected ResearchStaffRepository researchStaffRepository;
    protected ConfigPropertyRepositoryImpl configPropertyRepository;
    private OrganizationDao organizationDao;
    protected WebControllerValidator webControllerValidator;
    private String authenticationMode;

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public ResearchStaffController() {
        setCommandClass(ResearchStaffCommand.class);
        Flow<C> flow = new Flow<C>("Create Research Staff");
        layoutTabs(flow);
        setFlow(flow);
/*
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
*/
    }

    // /LOGIC
    @Override
    protected ResearchStaff getPrimaryDomainObject(final C command) {
        return command.getResearchStaff();
    }

    @Required
    public void setResearchStaffRepository(final ResearchStaffRepository researchStaffRepository) {
        this.researchStaffRepository = researchStaffRepository;
    }

    @Override
    protected ResearchStaffDao getDao() {
        return null;
    }

    protected abstract void layoutTabs(Flow<C> flow);

    @Override
    protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Organization.class, new DaoBasedEditor(organizationDao));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));

    }

    @SuppressWarnings("unchecked")
	@Override
    protected ModelAndView processFinish(final HttpServletRequest request, final HttpServletResponse response, final Object researchStaffCommand, final BindException errors) throws Exception {

        ResearchStaffCommand command = (ResearchStaffCommand)researchStaffCommand;
        ResearchStaff researchStaff = command.getResearchStaff();

        // sync the Sites Roles
        short i = 0;
        for (SiteResearchStaffCommandHelper srsch : command.getSrs()) {
            SiteResearchStaff srs = researchStaff.getSiteResearchStaffs().get(i);
            srs.getSiteResearchStaffRoles().clear();
            
            for (short j=0; j<srsch.getRsRoles().size(); j++) {
                if (srsch.getRsRoles().get(j).getChecked()) {
                    SiteResearchStaffRole srsr = new SiteResearchStaffRole();
                    srsr.setRoleCode(srsch.getRsRoles().get(j).getRoleCode());
                    srsr.setStartDate(srsch.getRsRoles().get(j).getStartDate());
                    srsr.setEndDate(srsch.getRsRoles().get(j).getEndDate());
                    srsr.setSiteResearchStaff(srs);
                    srs.addSiteResearchStaffRole(srsr);
                }
            }
            i++;
        }
        //

        ModelAndView modelAndView = new ModelAndView("admin/researchStaffReview");
        String emailSendingErrorMessage = "";
        try {
            if ("saveRemoteRs".equals(request.getParameter("_action"))) {

                ResearchStaff remoteRStoSave = researchStaff.getExternalResearchStaff().get(Integer.parseInt(request.getParameter("_selected")));
                researchStaff.setEmailAddress(remoteRStoSave.getEmailAddress());
                researchStaff.setFirstName(remoteRStoSave.getFirstName());
                researchStaff.setLastName(remoteRStoSave.getLastFirst());
                researchStaff.setPhoneNumber(remoteRStoSave.getPhoneNumber());
                researchStaff.setFaxNumber(remoteRStoSave.getFaxNumber());
                researchStaffRepository.save(remoteRStoSave, ResetPasswordController.getURL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath()));
            } else {
                researchStaffRepository.save(researchStaff, ResetPasswordController.getURL(request.getScheme(), request.getServerName(), request.getServerPort(), request.getContextPath()));
            }
        } catch (MailException e) {
            emailSendingErrorMessage = "Could not send email to user.";
            logger.error("Could not send email to user.", e);
        }
        if (!errors.hasErrors()) {
            String statusMessage = "Successfully created ResearchStaff.";
            
            if (!StringUtils.isBlank(emailSendingErrorMessage)) {
                statusMessage = statusMessage + " But we could not send email to user";
            }
        }
        modelAndView.addAllObjects(errors.getModel());
        modelAndView.addObject("researchStaff", researchStaff);
        return modelAndView;
    }


    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
        System.out.println("--- onBindAndValidate");
        super.onBindAndValidate(request, command, errors, page);

/*
        ResearchStaffCommand researchStaffCommand = (ResearchStaffCommand)command;
        ResearchStaff researchStaff = researchStaffCommand.getResearchStaff();
        
        if(researchStaff.getId() == null) {
    		if(!"saveRemoteRs".equals(request.getParameter("_action"))){
    			ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    			researchStaffQuery.filterByLoginId(researchStaff.getLoginId());
    			List<ResearchStaff> localRs = researchStaffRepository.getResearchStaff(researchStaffQuery);
    			if(localRs != null && localRs.size() > 0){
    				errors.reject("LOCAL_RS_EXISTS","ResearchStaff with LoginID " +researchStaff.getLoginId() + " already exisits");
    				return;
    			}
        		List<ResearchStaff> remoteRs = researchStaffRepository.getRemoteResearchStaff(researchStaff);
        		if(remoteRs != null && remoteRs.size() > 0){
        			researchStaff.setExternalResearchStaff(remoteRs);
        			errors.reject("REMOTE_RS_EXISTS","ResearchStaff with EmailAddress " +researchStaff.getEmailAddress()+ " exisits in external system");
        		}
        	}
        }
*/
    }

    @Required
    public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
        this.webControllerValidator = webControllerValidator;
    }

    @Override
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page) throws Exception {
        Map refData = super.referenceData(request, oCommand, errors, page);
        refData.put("authenticationMode", getAuthenticationMode());
        return refData;
    }

    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }

    @Override
    protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
        Object subviewName = findInRequest(request, ResearchStaffController.AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            return "admin/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }

    protected Object findInRequest(final HttpServletRequest request, final String attributName) {
        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }

    public ConfigPropertyRepositoryImpl getConfigPropertyRepository() {
        return configPropertyRepository;
    }

    public void setConfigPropertyRepository(ConfigPropertyRepositoryImpl configPropertyRepository) {
        this.configPropertyRepository = configPropertyRepository;
    }

    @Override
    protected boolean shouldSave(HttpServletRequest request, C command, Tab<C> cTab) {
        System.out.println("...shouldSave.");
        if (isAjaxRequest(request)) return false;
        return super.shouldSave(request, command, cTab);
    }
}
