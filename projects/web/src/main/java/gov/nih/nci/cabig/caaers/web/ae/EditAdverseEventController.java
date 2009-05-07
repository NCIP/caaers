package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class EditAdverseEventController extends AbstractAdverseEventInputController {

    private Task task;
	//validator needs to be called in onBindAndValidate()
	protected WebControllerValidator webControllerValidator;
	private static final String ACTION_PARAMETER = "action";
	private static final String AE_LIST_PARAMETER = "adverseEventList";
    private static final String AE_REPORT_ID_PARAMETER = "aeReportId";
    private static final String REPORTING_PERIOD_PARAMETER = "reportingPeriodParameter";
    private static final String REPORT_DEFN_LIST_PARAMETER ="reportDefnList";
    private static final String REPORT_ID_PARAMETER = "reportId";
    private static final String PRIMARY_ADVERSE_EVENT_ID_PARAMETER = "primaryAEId";
    
    private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
    public EditAdverseEventController() {
        setCommandClass(EditExpeditedAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected FlowFactory<ExpeditedAdverseEventInputCommand> createFlowFactory() {
        return new ExpeditedFlowFactory("Edit expedited report");
    }

    
    
    @Override
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page) throws Exception {
        Map<String, Object> refdata = super.referenceData(request, oCommand, errors, page);
        refdata.put("currentTask", task);
        return refdata;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	
    	 //remove the edit command from the session
		request.getSession(true).removeAttribute(CreateAdverseEventController.class.getName() + ".FORM.command");
		request.getSession().removeAttribute(CreateAdverseEventController.class.getName() + ".FORM.command.to-replace");
		   
    	String action = (String) request.getSession().getAttribute(ACTION_PARAMETER);
    	RenderDecisionManager renderDecisionManager = renderDecisionManagerFactoryBean.getRenderDecisionManager();
    	EditExpeditedAdverseEventCommand command = new EditExpeditedAdverseEventCommand(reportDao, reportDefinitionDao, assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, reportRepository, adverseEventRoutingAndReviewRepository, evaluationService);
    	command.setWorkflowEnabled(getConfiguration().get(getConfiguration().ENABLE_WORKFLOW));
    	if(action != null){
        	if(action.equals("createNew")){
        		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
        		aeReport.setCreatedAt(nowFactory.getNowTimestamp());
        		command.setAeReport(aeReport);
        	}
        }
    	
        return command;
    }
    
    /**
     * Find the parameters and initialize accordingly.
     *  1- AE_LIST_PARAMETER, contains newly selected adverse events. 
     *  2-
     */
    @Override
    protected void onBindOnNewForm(HttpServletRequest request, Object cmd) throws Exception {
        super.onBindOnNewForm(request, cmd);
        
        HttpSession session = request.getSession();
        
        EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
        
        
        String action = (String) request.getSession().getAttribute(ACTION_PARAMETER);
        List<AdverseEvent> aeList = (List<AdverseEvent>) session.getAttribute(AE_LIST_PARAMETER);
        Integer primaryAdverseEventId = (Integer) session.getAttribute(PRIMARY_ADVERSE_EVENT_ID_PARAMETER);
        
        
        AdverseEventReportingPeriod reportingPeriod = (AdverseEventReportingPeriod) session.getAttribute(REPORTING_PERIOD_PARAMETER);
        List<ReportDefinition> rdList = (List<ReportDefinition>) session.getAttribute(REPORT_DEFN_LIST_PARAMETER);
        
        //if there are report definitions, choosen, add them to the command, after loading them to current session. 
        if(rdList != null){
        	for(ReportDefinition rd : rdList){
        		command.getSelectedReportDefinitions().add(reportDefinitionDao.getById(rd.getId()));
        	}
        }
        
        
        // This is to handle the case where the report is amended to add AEs. There is no new reportDefinition selected.
        if(StringUtils.equals("amendReport", action)){
        	
        	//update the graded date on aes, as we are going to amend the report.
        	command.getAeReport().updateAdverseEventGradedDate();
        	
        	if(rdList == null || rdList.isEmpty()){
            	
            	//find  amendable reports of the sponsor, that are completed, they need to be amended.
            	String nciInstituteCode = command.getAeReport().getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode();
            	List<Report> completedAmendableReports = command.getAeReport().findCompletedAmendableReports(nciInstituteCode);
            	for(Report report: completedAmendableReports){
            			command.getSelectedReportDefinitions().add(report.getReportDefinition());
            	}
            	
        	}

        }
        
        //add the newly selected adverse events.
        if(aeList != null){
        	for(AdverseEvent ae: aeList){
    			command.getAeReport().addAdverseEvent(ae);
    		}
        }
        
        //modify the primary ae if necessary
        command.makeAdverseEventPrimary(primaryAdverseEventId);
      
        
        if(StringUtils.equals("createNew", action)){
        	AdverseEventReportingPeriod adverseEventReportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriod.getId());
        	command.getAeReport().setReportingPeriod(adverseEventReportingPeriod);
        	command.reassociate();
    		command.getAeReport().synchronizeMedicalHistoryFromAssignmentToReport();
    		
    		// Initialize the treatment assignment & start date of course
           command.initializeTreatmentInformation();
           
           //set the reporter, as the login person
           String loginId = SecurityUtils.getUserLoginName();
           if(loginId != null){
        	   User loggedInUser = userDao.getByLoginId(loginId);
        	  command.getAeReport().getReporter().copy(loggedInUser);
           }
        }
        
        session.removeAttribute(AE_LIST_PARAMETER);
        session.removeAttribute(AE_REPORT_ID_PARAMETER);
        session.removeAttribute(REPORTING_PERIOD_PARAMETER);
        session.removeAttribute(REPORT_DEFN_LIST_PARAMETER);
        session.removeAttribute(PRIMARY_ADVERSE_EVENT_ID_PARAMETER);
       
        //====================================================================================
        // Check whether the request is coming from ManageReports and is to amend a report
        String pramAction = request.getParameter(ACTION_PARAMETER);
        if(StringUtils.equals(pramAction, "amendReport")){
        	// Get the aeReportId from the request. Check all the submitted/ withdrawn reports and amend them
        	String aeReportId = request.getParameter(AE_REPORT_ID_PARAMETER);
        	String reportId = request.getParameter(REPORT_ID_PARAMETER);
        	if(reportId != null){
        		
        		for(Report report: command.getAeReport().getReports()){
        			if(report.getId().equals(Integer.parseInt(reportId))){
        				command.getAmendedReportsMap().put(report.getReportDefinition(), report);
        				break;
        			}
        		}
        		
        		//update the graded date on aes, as we are going to amend the report.
            	command.getAeReport().updateAdverseEventGradedDate();
        		
        		//now amend the reports
        		command.amendReports();
        	}
        }
        //====================================================================================

        if(StringUtils.equals(action, "createNew")){
        	command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport(), rdList.toArray(new ReportDefinition[]{})));
        	// pre-init the mandatory section fields
            command.initializeMandatorySectionFields();
        }else{
        	command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport()));
        }
        
        command.updateOutcomes();
        
        //will pre determine the display/renderability of fields 
        command.initializeNotApplicableFields();
        command.refreshMandatoryProperties();
     
    }

    @Override
    protected void onBind(HttpServletRequest request, Object command, BindException errors) throws Exception {
        super.onBind(request, command, errors);
    }

    /*
     * Attempt at not rebinding the aeReport with every request. Exposes flow to lazy init
     * exceptions, so it is disabled for now. TODO: make it work. // Same as the
     * super-implementation, except that it skips binding the aeReport parameter @Override protected
     * ServletRequestDataBinder createBinder(HttpServletRequest request, Object command) throws
     * Exception { ServletRequestDataBinder binder = new ServletRequestDataBinder(command,
     * getCommandName()) { @Override public void bind(ServletRequest request) {
     * MutablePropertyValues mpvs = new ServletRequestParameterPropertyValues(request);
     * mpvs.removePropertyValue("aeReport"); doBind(mpvs); } }; prepareBinder(binder);
     * initBinder(request, binder); return binder; }
     */

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
        EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) oCommand;

        // everything is saved as you move from page to page, so no action required here
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToAeList", model);
    }
    /**
     * Will call the validate method on web controller.
     */
    @Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors, int page) throws Exception {
		super.onBindAndValidate(request, command, errors, page);
		webControllerValidator.validate(request, command, errors);
	}
    
    /**
     * Supress validation, when we are on attribution page and is trying to go back.  
     */
    @Override
    protected boolean suppressValidation(HttpServletRequest request,Object command) {
    	 if (super.suppressValidation(request, command)) return true;
    	 EditExpeditedAdverseEventCommand aeCommand = (EditExpeditedAdverseEventCommand) command;
    	 
    	  //special case, if it is attribution page allow go back and forward
    	 String shortTitle = getFlow(aeCommand).getTab(getCurrentPage(request)).getShortTitle();
    	 if(shortTitle.equals(ExpeditedReportSection.ATTRIBUTION_SECTION.getDisplayName())){
    		 return true;
    	 }
    	 
    	//intervention page, allow go back.
         if(shortTitle.equals(ExpeditedReportSection.STUDY_INTERVENTIONS.getDisplayName())){
        	 return super.getCurrentPage(request) > aeCommand.getNextPage();
         }
         
         return false;
    }
    
    @Override
    protected ExpeditedAdverseEventInputCommand save(ExpeditedAdverseEventInputCommand command, Errors errors) {
    	command.save();
    	return null;
    }
    
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
	
	@Required
	public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
	    this.webControllerValidator = webControllerValidator;
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
    
    public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao){
    	this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
    }
    
    public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao(){
    	return adverseEventReportingPeriodDao;
    }
  
}
