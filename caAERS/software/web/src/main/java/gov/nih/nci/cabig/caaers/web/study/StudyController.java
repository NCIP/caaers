/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.caaers.web.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * Base Controller class to handle the basic work flow in the Creation / Updation of a Study Design
 * This uses AbstractTabbedFlowFormController to implement tabbed workflow
 *
 * @author Biju Joseph
 * @author Ion C. Olaru
 */
public abstract class StudyController<C extends StudyCommand> extends AutomaticSaveAjaxableFormController<C, Study, StudyDao> {

    private static final Log log = LogFactory.getLog(StudyController.class);
    public static final String AJAX_SUBVIEW_PARAMETER = "_subview";
    
    protected StudyDao studyDao;
    private OrganizationDao organizationDao;
    private AgentDao agentDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;
    private SiteResearchStaffDao siteResearchStaffDao;
    private CtcDao ctcDao;
    protected InvestigationalNewDrugDao investigationalNewDrugDao;
    private MeddraVersionDao meddraVersionDao;
    private ConditionDao conditionDao;
    protected ConfigPropertyRepositoryImpl configPropertyRepository;
    private StudyRepository studyRepository;
    private LowLevelTermDao lowLevelTermDao;
    private DeviceDao deviceDao;
    private TreatmentAssignmentDao treatmentAssignmentDao;

    private Configuration configuration;

    private EventFactory eventFactory;

    // validator needs to be called in onBindAndValidate()
    protected WebControllerValidator webControllerValidator;

    public StudyController() {
        setCommandClass(StudyCommand.class);
        setAllowDirtyBack(false);
        setAllowDirtyForward(false);
    }

    // /LOGIC
    @Override
    protected Study getPrimaryDomainObject(final C studyCommand) {
        return studyCommand.getStudy();
    }

    @Override
    protected StudyDao getDao() {
        return studyDao;
    }

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);
        ControllerTools.registerDomainObjectEditor(binder, siteInvestigatorDao);
        ControllerTools.registerDomainObjectEditor(binder, researchStaffDao);
        ControllerTools.registerDomainObjectEditor(binder, siteResearchStaffDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcDao);
        ControllerTools.registerDomainObjectEditor(binder, investigationalNewDrugDao);
        ControllerTools.registerDomainObjectEditor(binder, meddraVersionDao);
        ControllerTools.registerDomainObjectEditor(binder, lowLevelTermDao);
        ControllerTools.registerDomainObjectEditor(binder, deviceDao);
        ControllerTools.registerEnumEditor(binder, Term.class);
        ControllerTools.registerEnumEditor(binder, Design.class);
        ControllerTools.registerEnumEditor(binder, INDType.class);
        ControllerTools.registerEnumEditor(binder, StudyTherapyType.class);
    }

    /**
     * If true - Shows the Study Summary on top of the page while navigating the flow
     * 
     */
    protected boolean isSummaryEnabled() {
        return true;
    }

    @Override
    protected String getViewName(final HttpServletRequest request, final Object command, final int page) {
        Object subviewName = findInRequest(request, StudyController.AJAX_SUBVIEW_PARAMETER);
        if (subviewName != null) {
            return "study/ajax/" + subviewName;
        } else {
            return super.getViewName(request, command, page);
        }
    }

    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final HttpServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }

    @Override
    protected boolean suppressValidation(final HttpServletRequest request, final Object command) {
    	
    	//suppress validation for AJAX requests
        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null || isAjaxRequest(request)) {
            return true;
        }
        
        //suppress validation for request having sub-action
        String action = (String) findInRequest(request, "_action");
        if (StringUtils.isNotEmpty(action) && !StringUtils.containsIgnoreCase(action, "add")) {
            return true;
        }

        return super.suppressValidation(request, command);

    }


    @Override
    protected boolean shouldSave(final HttpServletRequest request, final C command, final Tab<C> tab) {

        String action = request.getParameter("_action");
        if (StringUtils.equals(action, "removeSite")) {
            return false;
        }

        boolean asyncReqParamPresent = isAjaxRequest(request);
        
        //save an Ajax request when it is "a remove" task
        Object removeTask = findInRequest(request, "task");
        if(asyncReqParamPresent && "remove".equals(String.valueOf(removeTask))){
            return true;
        }

        //save an Ajax request when we are adding/editing an epoch.
        String asyncMethodName = String.valueOf(findInRequest(request, "_asyncMethodName"));
        if(asyncReqParamPresent && ( "addEpoch".equals(asyncMethodName) ||  "doInPlaceEdit".equals(asyncMethodName) ) )  {
            return true;
        }

    	//do not save if it is an AJAX request,
        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null || asyncReqParamPresent) return false;

        // always save - otherwise
        return true; 
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
        super.onBindAndValidate(request, command, errors, page);
        if (!suppressValidation(request,command)) {
            webControllerValidator.validate(request, command, errors);

        }
    }

    @Override
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page) throws Exception {
        Map refData = super.referenceData(request, oCommand, errors, page);
        StudyCommand cmd = (StudyCommand) oCommand;
        Study study = cmd.getStudy();

        boolean showSummary = false;
        if (isSummaryEnabled()) {
            List<ListValues> summary = new ArrayList<ListValues>();
            if (study.getShortTitle() != null) {
                summary.add(new ListValues(getMessage("LBL_Study", "Study."), study.getDisplayName()));
                showSummary = true;
            }


            if (showSummary) refData.put("studySummary", summary);
        }
        
        return refData;
    }

    /**
     * Do not show flash message when we change site.
     */
    @Override
    public void populateSaveConfirmationMessage(Map refdata,HttpServletRequest request, Object command, Errors errors, int page) {
    	if(StringUtils.isEmpty(request.getParameter("_action")))  	super.populateSaveConfirmationMessage(refdata, request, command, errors, page);
    }

    /**
     * Will make sure it will reload the study from database for every request. This will discard the study loaded in previous session.
     * @param request   - The HttpServletRequest
     * @param oCommand  - An instance of StudyCommand
     * @return
     * @throws Exception
     */
    @Override
    protected Object currentFormObject(HttpServletRequest request, Object oCommand) throws Exception {
        StudyCommand cmd = (StudyCommand) super.currentFormObject(request, oCommand);

        String action = request.getParameter("_action");
        if (StringUtils.equals(action, "removeSite")) { return cmd; }
        
        boolean asyncReqParamPresent = isAjaxRequest(request);
        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null || asyncReqParamPresent) return cmd;

        //only reload for non-ajax requests
        if(cmd.getStudy().getId() != null){
            cmd.reloadStudy();
        }
        return cmd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected C save(C command, Errors errors) {

        if(errors.hasErrors()) return null;
        command.save();

        return null;
    }

    // /BEAN PROPERTIES

    public AgentDao getAgentDao() {
        return agentDao;
    }

    public void setAgentDao(final AgentDao agentDao) {
        this.agentDao = agentDao;
    }

    public ResearchStaffDao getResearchStaffDao() {
        return researchStaffDao;
    }

    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(final OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public SiteInvestigatorDao getSiteInvestigatorDao() {
        return siteInvestigatorDao;
    }

    public void setSiteInvestigatorDao(final SiteInvestigatorDao siteInvestigatorDao) {
        this.siteInvestigatorDao = siteInvestigatorDao;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(final StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public CtcDao getCtcDao() {
        return ctcDao;
    }

    public void setCtcDao(final CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }

    public MeddraVersionDao getMeddraVersionDao() {
        return meddraVersionDao;
    }

    public void setMeddraVersionDao(final MeddraVersionDao meddraVersionDao) {
        this.meddraVersionDao = meddraVersionDao;
    }

    public InvestigationalNewDrugDao getInvestigationalNewDrugDao() {
        return investigationalNewDrugDao;
    }

    public void setInvestigationalNewDrugDao(final InvestigationalNewDrugDao investigationalNewDrugDao) {
        this.investigationalNewDrugDao = investigationalNewDrugDao;
    }

    @Required
    public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
        this.webControllerValidator = webControllerValidator;
    }
    
    public LowLevelTermDao getLowLevelTermDao() {
		return lowLevelTermDao;
	}
    
    public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}

    public ConditionDao getConditionDao() {
        return conditionDao;
    }

    public void setConditionDao(ConditionDao conditionDao) {
        this.conditionDao = conditionDao;
    }
    
    @Required
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

    public SiteResearchStaffDao getSiteResearchStaffDao() {
        return siteResearchStaffDao;
    }

    public void setSiteResearchStaffDao(SiteResearchStaffDao siteResearchStaffDao) {
        this.siteResearchStaffDao = siteResearchStaffDao;
    }

    public ConfigPropertyRepositoryImpl getConfigPropertyRepository() {
        return configPropertyRepository;
    }

    public void setConfigPropertyRepository(ConfigPropertyRepositoryImpl configPropertyRepository) {
        this.configPropertyRepository = configPropertyRepository;
    }

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Required
    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public void setEventFactory(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public void setDeviceDao(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public TreatmentAssignmentDao getTreatmentAssignmentDao() {
        return treatmentAssignmentDao;
    }

    public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
        this.treatmentAssignmentDao = treatmentAssignmentDao;
    }
}
