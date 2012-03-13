package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.web.ControllerTools;
import gov.nih.nci.cabig.caaers.web.rule.AbstractRuleInputController;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    
    private RuleSetDao ruleSetDao;


    public CreateRuleController() {
        super();
        setBindOnNewForm(false);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        ControllerTools.registerDomainObjectEditor(binder, organizationDao);
        ControllerTools.registerDomainObjectEditor(binder, studyDao);
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object oCommand, BindException arg3) throws Exception {
        CreateRuleCommand command = (CreateRuleCommand) oCommand;
        if(command.isCreateMode()) command.saveAndDeploy();
        Map<String, Object> model = new HashMap<String, Object>();
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
            summary = createRuleCommand.getSummary();
        }
    	referenceData.put("ruleFlowSummary", summary);
    	
    	return referenceData;
    }

    /**
     * Retrieves the RuleSet based on the "ruleSetId" request parameter.
     * @param request
     * @return
     */
    @Override
    protected Object formBackingObject(HttpServletRequest request) {
    	CreateRuleCommand command = new CreateRuleCommand(caaersRulesEngineService,reportDefinitionDao, organizationDao, studyDao);
        command.setRuleManager(SecurityUtils.checkAuthorization(UserGroupType.ae_rule_and_report_manager));
        
    	String sourcePage = (String) findInRequest(request, "from");
        
        if(StringUtils.equals(sourcePage, "list")){
            String ruleSetId = (String) findInRequest(request, "ruleSetId");
            gov.nih.nci.cabig.caaers.domain.RuleSet domainRuleSet = ruleSetDao.getById(Integer.parseInt(ruleSetId));
            if(domainRuleSet != null) command.setCaaersRuleSet(domainRuleSet);
            RuleSet rs = caaersRulesEngineService.getRuleSet(domainRuleSet.getRuleBindURI());
            if(rs != null) command.setRuleSet(rs);
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
    * The meathod is responsible for the cases when the page validation should be skipped on Submit,
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

    public RuleSetDao getRuleSetDao() {
        return ruleSetDao;
    }

    public void setRuleSetDao(RuleSetDao ruleSetDao) {
        this.ruleSetDao = ruleSetDao;
    }
}