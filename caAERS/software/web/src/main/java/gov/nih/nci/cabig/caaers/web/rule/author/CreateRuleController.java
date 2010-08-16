package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.utils.RuleUtil;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.rule.AbstractRuleInputController;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
 * @author Biju Joseph
 */
public class CreateRuleController extends AbstractRuleInputController<CreateRuleCommand> {

    private StudyDao studyDao;

    private ReportDefinitionDao reportDefinitionDao;

    private OrganizationDao organizationDao;

    private CaaersRulesEngineService caaersRulesEngineService;


    public CreateRuleController() {
        super();
        setBindOnNewForm(false);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, studyDao);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object oCommand, BindException arg3) throws Exception {
        CreateRuleCommand command = (CreateRuleCommand) oCommand;
        if(command.getMode().equals(CreateRuleCommand.CREATE_MODE)) command.deployRuleSet();
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
        if(WebUtils.getTargetPage(request) > 0){
        	summary.put("Rule level", (createRuleCommand.getLevel() == null || createRuleCommand.getLevel().equals("")) ? "" : createRuleCommand.getLevelDescription());
        	summary.put("Rule set name", (createRuleCommand.getRuleSetName() == null || createRuleCommand.getRuleSetName().equals("")) ? "" : createRuleCommand.getRuleSetName());
            
            if(createRuleCommand.isSponsorBased()){
                summary.put("Sponsor", createRuleCommand.getSponsor() != null ? createRuleCommand.getSponsor().getFullName() : "");
            }

            if(createRuleCommand.isInstitutionBased()){
                summary.put("Institution", createRuleCommand.getInstitution() != null ? createRuleCommand.getInstitution().getFullName() : "");
            }

            if(createRuleCommand.isStudyBased() && createRuleCommand.getStudy() != null){
               summary.put("Study", "(" + createRuleCommand.getStudy().getPrimaryIdentifierValue() + ") " + createRuleCommand.getStudy().getShortTitle() );
            }

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
	 * If the entry to author rules from list rules page, at that point in time there is no command in session. 
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

    /**
     * Retrieves the RuleSet based on the "ruleSetId" request parameter.
     * @param request
     * @return
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) {
    	CreateRuleCommand command = new CreateRuleCommand(caaersRulesEngineService,reportDefinitionDao, organizationDao);
        command.setRuleManager(SecurityUtils.checkAuthorization(UserGroupType.ae_rule_and_report_manager));
    	String sourcePage = (String) findInRequest(request, "from");
        if(StringUtils.equals(sourcePage, "list")){
            String ruleSetId = (String) findInRequest(request, "ruleSetId");
            RuleSet rs = caaersRulesEngineService.getRuleSetById(ruleSetId);
            if(rs != null){

                //populate the Study/Sponsor/Institution
                command.setLevel(caaersRulesEngineService.parseRuleLevel(rs.getName()));
                command.setRuleSetName(rs.getDescription());
                String orgId = caaersRulesEngineService.parseOrganizationId(rs.getName());
                String stuId = caaersRulesEngineService.parseStudyId(rs.getName());

                if(StringUtils.isNotEmpty(orgId)){
                   Organization org = organizationDao.getById(Integer.parseInt(orgId));
                   if(command.isSponsorBased()){
                       command.setSponsorNameInitialValue(org.getFullName());
                       command.setSponsor(org);
                   }
                   if(command.isInstitutionBased()){
                       command.setInstitutionNameInitialValue(org.getFullName());
                       command.setInstitution(org);
                   }
                }

                if(StringUtils.isNotEmpty(stuId)){
                    Study study = studyDao.getById(Integer.parseInt(stuId));
                    command.setStudy(study);
                }

                command.setRuleSet(rs);
                
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

    @Override
    public FlowFactory<RuleInputCommand> getFlowFactory() {
        return new FlowFactory<RuleInputCommand>(){
            
            public Flow<RuleInputCommand> createFlow(RuleInputCommand command) {
                Flow<RuleInputCommand> flow = new Flow<RuleInputCommand>(getFlowName());

                CreateRuleCommand createRuleCommand = (CreateRuleCommand) command;
                boolean editMode = createRuleCommand.getRuleSet() == null ? false : createRuleCommand.getRuleSet().getId() != null;

                if(SecurityUtils.checkAuthorization(UserGroupType.ae_rule_and_report_manager)){
                    flow.addTab(new SelectRuleTypeTab());
                    flow.addTab(new RuleTab()); 
                }
                flow.addTab(new ReviewTab());

                return flow;
            }
        };
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

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService) {
        this.caaersRulesEngineService = caaersRulesEngineService;
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

   
}