package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.Design;
import gov.nih.nci.cabig.caaers.domain.INDType;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.repository.ConfigPropertyRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.spring.tabbedflow.AutomaticSaveAjaxableFormController;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.Date;
import java.util.Map;

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
 * @author Priyatam
 * @author Biju Joseph
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
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private MeddraVersionDao meddraVersionDao;
    private ConditionDao conditionDao;
    protected ConfigPropertyRepositoryImpl configPropertyRepository;
    private StudyRepository studyRepository;
    private LowLevelTermDao lowLevelTermDao;
    
    private Configuration configuration;

    // validator needs to be called in onBindAndValidate()
    protected WebControllerValidator webControllerValidator;

    public StudyController() {
        setCommandClass(StudyCommand.class);
        Flow<C> flow = new Flow<C>("Enter Study");
        layoutTabs(flow);
        setFlow(flow);
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

    /**
     * Template method to let the subclass decide the order of tab
     */
    protected abstract void layoutTabs(Flow<C> flow);

    @Override
    protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, agentDao);
        ControllerTools.registerDomainObjectEditor(binder, siteInvestigatorDao);
        ControllerTools.registerDomainObjectEditor(binder, researchStaffDao);
        ControllerTools.registerDomainObjectEditor(binder, siteResearchStaffDao);
        ControllerTools.registerDomainObjectEditor(binder, ctcDao);
        ControllerTools.registerDomainObjectEditor(binder, investigationalNewDrugDao);
        ControllerTools.registerDomainObjectEditor(binder, meddraVersionDao);
        ControllerTools.registerDomainObjectEditor(binder, lowLevelTermDao);
        ControllerTools.registerEnumEditor(binder, Term.class);
        ControllerTools.registerEnumEditor(binder, Design.class);
        ControllerTools.registerEnumEditor(binder, INDType.class);
    }

    /**
     * Override this in sub controller if summary is needed
     *
     * @return
     */
    protected boolean isSummaryEnabled() {
        return false;
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
        // supress for ajax and delete requests
        Object isAjax = findInRequest(request, "_isAjax");
        if (isAjax != null) {
            return true;
        }
        String action = (String) findInRequest(request, "_action");
        if (StringUtils.isNotEmpty(action) && !StringUtils.containsIgnoreCase(action, "add")) {
            return true;
        }

        return super.suppressValidation(request, command);

    }

    @Override
    protected boolean shouldSave(final HttpServletRequest request, final C command, final Tab<C> tab) {
        return super.shouldSave(request, command, tab) && findInRequest(request, "_isAjax") == null;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
        super.onBindAndValidate(request, command, errors, page);
        if (!suppressValidation(request,command)) {
            webControllerValidator.validate(request, command, errors);

        }
    }
    
    /**
     * Do not show flash message when we change site.
     */
    @Override
    public void populateSaveConfirmationMessage(Map refdata,HttpServletRequest request, Object command, Errors errors, int page) {
    	if(StringUtils.isEmpty(request.getParameter("_action")))  	super.populateSaveConfirmationMessage(refdata, request, command, errors, page);
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
}
