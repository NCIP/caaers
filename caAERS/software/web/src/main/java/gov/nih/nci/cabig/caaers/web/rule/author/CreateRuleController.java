package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.rule.AbstractRuleInputController;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RuleAuthoringService;
import com.semanticbits.rules.api.RuleDeploymentService;
import com.semanticbits.rules.brxml.RuleSet;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Ion C. Olaru
 */
public class CreateRuleController extends AbstractRuleInputController<CreateRuleCommand> {

	public static final String SPONSOR_LEVEL = "Sponsor";

    public static final String INSTITUTIONAL_LEVEL = "Institution";

    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";

    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
    
    private RuleAuthoringService ruleAuthoringService;

    private StudyDao studyDao;

    private NotificationDao notificationDao;

    private ReportDefinitionDao reportDefinitionDao;

    private OrganizationDao organizationDao;

    private CaaersRulesEngineService caaersRulesEngineService;

    private CtcDao ctcDao;
    
    private RuleDeploymentService ruleDeploymentService;
    
    private RepositoryService repositoryService;

    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public CreateRuleController() {
        super();
        setBindOnNewForm(false);
        addTabs();
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object oCommand, BindException arg3) throws Exception {

        CreateRuleCommand command = (CreateRuleCommand) oCommand;
        if(command.getMode().equals(CreateRuleCommand.CREATE_MODE))
        	command.deployRuleSet();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ruleSet", command.getRuleSet());
        return new ModelAndView("redirectToTriggerList", model);

    }
    
    @SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request, Object o,	Errors errors, int page) throws Exception {
    	
    	CreateRuleCommand createRuleCommand  = (CreateRuleCommand) o;
		Map referenceData =  super.referenceData(request, createRuleCommand, errors, page);
    	
    	// Create and put the summary in reference data.
        Map<String, String> summary = new LinkedHashMap<String, String>();
        summary.put("Rule level", (createRuleCommand.getLevel() == null) ? "" : createRuleCommand.getLevelDescription());
        summary.put("Rule set name", (createRuleCommand.getRuleSetName() == null) ? "" : createRuleCommand.getRuleSetName());
        if(createRuleCommand.getLevel() != null){
        	if(createRuleCommand.getLevel().equals(CreateRuleCommand.SPONSOR_LEVEL) || createRuleCommand.getLevel().equals(CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL))
        		summary.put("Sponsor", (createRuleCommand.getOrganizationName() == null ? "" : createRuleCommand.getOrganizationName()));
        	if(createRuleCommand.getLevel().equals(CreateRuleCommand.INSTITUTIONAL_LEVEL) || createRuleCommand.getLevel().equals(CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL))
        		summary.put("Institution", (createRuleCommand.getInstitutionName() == null ? "" : createRuleCommand.getInstitutionName()));
        	if(createRuleCommand.getLevel().equals(CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL) || createRuleCommand.getLevel().equals(CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL))
        		summary.put("Study", createRuleCommand.getCategoryIdentifier() == null ? "" : createRuleCommand.getCategoryIdentifier());
        }
    	referenceData.put("ruleFlowSummary", summary);
    	
    	return referenceData;
    }
    
    /**
	 * Will return true if we are entering into create rule flow from Manage Rules 
	 */
	@Override
    protected boolean isFormSubmission(HttpServletRequest request) {
		String fromListPage = WebUtils.getStringParameter(request, "from");
		if(StringUtils.isNotEmpty(fromListPage) && StringUtils.equals(fromListPage, "list")) return true;
		return super.isFormSubmission(request);
    }
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
				throws Exception {

		String fromListPage = WebUtils.getStringParameter(request, "from");
		// Form submission or new form to show?
		if (isFormSubmission(request)) {
		// Fetch form object from HTTP session, bind, validate, process submission.
		try {
			Object command = null;
			if(StringUtils.isNotEmpty(fromListPage) && StringUtils.equals(fromListPage, "list"))
				command = formBackingObject(request);
			else
				command = getCommand(request);
			ServletRequestDataBinder binder = bindAndValidate(request, command);
			BindException errors = new BindException(binder.getBindingResult());
			return processFormSubmission(request, response, command, errors);
		}
		catch (HttpSessionRequiredException ex) {
			// Cannot submit a session form if no form object is in the session.
			if (logger.isDebugEnabled()) {
				logger.debug("Invalid submit detected: " + ex.getMessage());
			}
			return handleInvalidSubmit(request, response);
		}
		}
		else {
			// New form to show: render form view.
			return showNewForm(request, response);
		}
	}
	
	/**
	 * If the entry to capture adverse event is from Manage reports, we need to handle the invalid submit case, as it the isFormSubmission is flaged 'true'. 
	 */
	
	@Override
	protected ModelAndView handleInvalidSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fromListPage = WebUtils.getStringParameter(request, "from");
		if(StringUtils.isEmpty(fromListPage)) return  super.handleInvalidSubmit(request, response);
		
		//generate the form, validate , processFormSubmission.
		Object command = formBackingObject(request);
		ServletRequestDataBinder binder = bindAndValidate(request, command);
		BindException errors = new BindException(binder.getBindingResult());
		
		return processFormSubmission(request, response, command, errors);
		
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) {
    	CreateRuleCommand command = new CreateRuleCommand(ruleAuthoringService, studyDao, notificationDao, caaersRulesEngineService, 
    			reportDefinitionDao, organizationDao, ctcDao, ruleDeploymentService, repositoryService);
    	
    	String sourcePage = (String) findInRequest(request, "from");
    	if(sourcePage != null && sourcePage.equals("list")){
    		String ruleSetId = (String) findInRequest(request, "ruleSetId");
    		List<RuleSet> ruleSets = ruleAuthoringService.getAllRuleSets();
    		RuleSet rs = null;
    		for(RuleSet ruleSet: ruleSets){
    			if(ruleSet.getId().equals(ruleSetId))
    				rs = ruleSet;
    		}
    		if(rs != null){
    			command.setRuleSetName(rs.getDescription());
    			command.setCategoryIdentifier("");
    			if(rs.getSubject().startsWith("Sponsor defined rules for a study")){
    				command.setLevel(SPONSOR_DEFINED_STUDY_LEVEL);
    				command.setCategoryIdentifier(rs.getStudy());
    				command.setSponsorName(rs.getOrganization());
    			}
    			else if(rs.getSubject().startsWith("Sponsor rules")){
    				command.setLevel(SPONSOR_LEVEL);
    				command.setSponsorName(rs.getOrganization());
    			}
    			else if(rs.getSubject().startsWith("Institution rules")){
    				command.setLevel(INSTITUTIONAL_LEVEL);
    				command.setInstitutionName(rs.getOrganization());
    			}
    			else if(rs.getSubject().startsWith("Institution defined rules for a study")){
    				command.setLevel(INSTITUTION_DEFINED_STUDY_LEVEL);
    				command.setCategoryIdentifier(rs.getStudy());
    				command.setInstitutionName(rs.getOrganization());
    			}
    		}
    	}
    	
    	return command;
    }

    @Override
    protected void initFlow() {
        super.initFlow();

    }

    @Override
    protected String getFlowName() {
        return "Create Rules";
    }

    protected void addTabs() {
        getFlow().addTab(new SelectRuleTypeTab());
        //getFlow().addTab(new DisplayRuleSetsTab());
        getFlow().addTab(new RuleTab());
        getFlow().addTab(new ReviewTab());
    }
    
    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }

    /*
    *
    * The methos is responsible for the cases when the page validation should be skipped on Submit,
    * in this case when the user goes back in the flow.
    *
    * */
    @Override
    protected boolean suppressValidation(HttpServletRequest httpServletRequest) {
        int curPage = getCurrentPage(httpServletRequest);
        int targetPage = getTargetPage(httpServletRequest, curPage);
        if (targetPage < curPage) return true;
        return super.suppressValidation(httpServletRequest);
    }

    public RuleAuthoringService getRuleAuthoringService() {
        return ruleAuthoringService;
    }

    public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
        this.ruleAuthoringService = ruleAuthoringService;
    }

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public CaaersRulesEngineService getCaaersRulesEngineService() {
        return caaersRulesEngineService;
    }

    public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService) {
        this.caaersRulesEngineService = caaersRulesEngineService;
    }

    public ReportDefinitionDao getReportDefinitionDao() {
        return reportDefinitionDao;
    }

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public OrganizationDao getOrganizationDao() {
        return organizationDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setCtcDao(CtcDao ctcDao) {
        this.ctcDao = ctcDao;
    }
    
    public RuleDeploymentService getRuleDeploymentService() {
        return ruleDeploymentService;
    }

    public void setRuleDeploymentService(RuleDeploymentService ruleDeploymentService) {
        this.ruleDeploymentService = ruleDeploymentService;
    }
    
    public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
}