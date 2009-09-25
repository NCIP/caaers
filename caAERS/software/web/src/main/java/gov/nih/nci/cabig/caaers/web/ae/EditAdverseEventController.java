package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
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
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
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
    private ResearchStaffDao researchStaffDao;
    private InvestigatorDao investigatorDao;
	
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
    	HttpSession session = request.getSession();
    	session.removeAttribute(CreateAdverseEventController.class.getName() + ".FORM.command");
    	session.removeAttribute(CreateAdverseEventController.class.getName() + ".FORM.command.to-replace");
		   
    	
    	RenderDecisionManager renderDecisionManager = renderDecisionManagerFactoryBean.getRenderDecisionManager();
    	EditExpeditedAdverseEventCommand command = new EditExpeditedAdverseEventCommand(reportDao, reportDefinitionDao, 
    				assignmentDao, reportingPeriodDao, expeditedReportTree, renderDecisionManager, reportRepository, adverseEventRoutingAndReviewRepository, evaluationService);
    	command.setWorkflowEnabled(getConfiguration().get(getConfiguration().ENABLE_WORKFLOW));
    	
    	
        return command;
    }
    
    /**
     * This method will do the following, make the command to be in a consistent state. 
     * 
     * If from Review Report page?
     *  1.- If this is a new data collection:
     *    1.1. Create Expedited Report, associate it with Reporting period.
     *    1.2. Initialize the Treatment information
     *    1.3. Initialize the reporter.
     *  2. Add/Remove adverse events. 
     *  3. Find the mandatory sections.  
     *  4. Pre-initialize the mandatory section fields.    
     *   
     * If from Manage reports page?
     *  1. Find the mandatory sections
     *  2. Refresh/Initialize the Not applicable and mandatory fields. 
     *  
     */
    @Override
    protected void onBindOnNewForm(HttpServletRequest request, Object cmd) throws Exception {
        super.onBindOnNewForm(request, cmd);
        
        HttpSession session = request.getSession();
        
        String screenFlowSource = request.getParameter("from");
        
        EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
        command.setScreenFlowSource(screenFlowSource);
        
        
    	command.getNewlySelectedReportDefinitions().clear();
    	command.getSelectedReportDefinitions().clear();
    	command.getApplicableReportDefinitions().clear();
    	
    	
    	ReviewAndReportResult reviewResult = (ReviewAndReportResult)session.getAttribute("reviewResult"); 
        ExpeditedAdverseEventReport aeReport = command.getAeReport();
        if( (reviewResult != null) && StringUtils.equals("captureAE", screenFlowSource)){
        	
        	//If a new data collection?
        	if(reviewResult.getAeReportId() == 0){
        		//create expedited report.
        		aeReport = new ExpeditedAdverseEventReport();
        		aeReport.setCreatedAt(nowFactory.getNowTimestamp());
        		command.setAeReport(aeReport);
        		
        		//populate the reporting period.
        		AdverseEventReportingPeriod adverseEventReportingPeriod = adverseEventReportingPeriodDao.getById(reviewResult.getReportingPeriodId());
            	command.getAeReport().setReportingPeriod(adverseEventReportingPeriod);
            	//command.reassociate();
        		command.getAeReport().synchronizeMedicalHistoryFromAssignmentToReport();
        		
        		//initialize treatment information
        		command.initializeTreatmentInformation();
        		
        		//set the default reporter as the logged-in person
        		String loginId = SecurityUtils.getUserLoginName();
                if(loginId != null){
             	   User loggedInUser = null;
             	   ResearchStaff researchStaff = researchStaffDao.getByLoginId(loginId);
             	   SiteResearchStaff siteResearchStaff = null;
             	   
             	   
             	   // Now if there is a siteResearchStaff then we need to copy details like emailAddress, phoneNumber and faxNumber from the
             	   // siteResearchStaff.
             	   if(researchStaff != null){
             		   siteResearchStaff = siteResearchStaffDao.getOrganizationResearchStaff(command.getAssignment().getStudySite().getOrganization(), researchStaff);
             		   loggedInUser = researchStaff;
             	   }else{
          			   loggedInUser = investigatorDao.getByLoginId(loginId); 
             	   }
             	  command.getAeReport().getReporter().copy(loggedInUser);
             	   if(siteResearchStaff != null){
             		   if(siteResearchStaff.getEmailAddress() != null)
             			   command.getAeReport().getReporter().setEmailAddress(siteResearchStaff.getEmailAddress());
             		   if(siteResearchStaff.getPhoneNumber() != null)
             			   command.getAeReport().getReporter().setPhoneNumber(siteResearchStaff.getPhoneNumber());
             		   if(siteResearchStaff.getFaxNumber() != null)
             			   command.getAeReport().getReporter().setFax(siteResearchStaff.getFaxNumber());
             		   if(siteResearchStaff.getAddress() != null)
             			   command.getAeReport().getReporter().setAddress(siteResearchStaff.getAddress());
             	   }
                }
        		
               	
        	}
        	
        	//Add all the aes to be added 
        	for(Integer aeId : reviewResult.getAeList()){
        		AdverseEvent ae = command.getAdverseEventReportingPeriod().findAdverseEventById(aeId);
        		if(aeReport.findAdverseEventById(aeId) == null){
        			aeReport.addAdverseEvent(ae);
        		}
        	}
        	
        	//remove all the aes to be removed
        	for(Integer aeId : reviewResult.getUnwantedAEList()){
        		AdverseEvent ae = aeReport.findAdverseEventById(aeId);
        		if(ae != null && aeReport.getAdverseEvents().remove(ae)){
        			ae.clearAttributions();
        			ae.setReport(null);
        		}
        	}
        	
        	//modify the primary ae if necessary
            command.makeAdverseEventPrimary(reviewResult.getPrimaryAdverseEventId());
          
        	//reload- the report definitions (from create & edit list)
            for(ReportDefinition rd : reviewResult.getCreateList()){
            	ReportDefinition loaded = reportDefinitionDao.getById(rd.getId());
            	reportDefinitionDao.initialize(loaded);
            	command.getSelectedReportDefinitions().add(loaded);
            	command.getNewlySelectedReportDefinitions().add(loaded);
            }
            for(ReportDefinition rd : reviewResult.getEditList()){
            	ReportDefinition loaded = reportDefinitionDao.getById(rd.getId());
            	command.getSelectedReportDefinitions().add(loaded);
            }
            
            //update the applicable report definitions.
            command.getApplicableReportDefinitions().addAll(command.getSelectedReportDefinitions());
           
            
        }else{
        	//from manage reports / review and reports, so do cleanup of session attributes explicitly
        	session.removeAttribute("reviewResult");
        	
        	String action = request.getParameter(ACTION_PARAMETER);
        	String strReportId = request.getParameter("report");
        	int reportId = -999;
        	if(StringUtils.isNumeric(strReportId)){
        		reportId = Integer.parseInt(strReportId);
        	}
        	
        	//find the report. 
        	Report selectedReport = aeReport.findReportById(reportId);
        	
        	if(selectedReport  != null){
        		command.getSelectedReportDefinitions().add(selectedReport.getReportDefinition());
        		if(!selectedReport.isActive()){
        			//if selected report is not active, add it into applicable reports
        			command.getApplicableReportDefinitions().add(selectedReport.getReportDefinition());
        		}
        	
        		for(Report report : aeReport.getActiveReports()){
        			command.getApplicableReportDefinitions().add(report.getReportDefinition());
        		}
        		
        		//pre initialize all the report mandatory fields.
        		for(ReportDefinition reportDefinition : command.getApplicableReportDefinitions()){
        			reportDefinition.getMandatoryFields().size();;
        		}
        		

            	//if action is amend, keep a mock reviewResult in session. 
            	if(StringUtils.equals(action, "amendReport")){
            		command.getNewlySelectedReportDefinitions().add(selectedReport.getReportDefinition());
            		reviewResult = new ReviewAndReportResult();
            		reviewResult.getAmendList().add(selectedReport.getReportDefinition());
            		reviewResult.getReportsToAmmendList().add(selectedReport);
            		reviewResult.getCreateList().add(selectedReport.getReportDefinition());
            		session.setAttribute("reviewResult", reviewResult);
            	}
            	
        	}
        
        	
        }
       
        //synchronize the outcomes
        command.updateOutcomes();
        
        //find the mandatory sections.
        command.refreshMandatorySections();
        
    	// pre-init the mandatory section fields & set present status
        if(aeReport.getId() == null){
        	command.initializeMandatorySectionFields();
        	
        	//present status. 
        	for(AdverseEvent ae : aeReport.getAdverseEvents()){
        		if(ae.getGrade().equals(Grade.DEATH)){
        			aeReport.getResponseDescription().setPresentStatus(PostAdverseEventStatus.DEAD);
        			break;
        		}
        	}
        }
        
        
        //will pre determine the display/render-ability of fields 
        command.initializeNotApplicableFields();
        command.refreshMandatoryProperties();
        
      
    }

    /**
     * Handles switching of reporting context. 
     * 
     */
    @Override
    protected void onBind(HttpServletRequest request, Object command, BindException errors) throws Exception {
        super.onBind(request, command, errors);
        //bind the context report definitions
        int[] rdIds = {-9999};
        try {
			rdIds = ServletRequestUtils.getIntParameters(request, "reportingContextRdId");
		} catch (Exception e) {
		}
		
		EditExpeditedAdverseEventCommand editCommand = (EditExpeditedAdverseEventCommand) command;
		//AJAX requests will not have reporting context information
		if(!(isAjaxRequest(request) || request.getParameter(AJAX_SUBVIEW_PARAMETER) != null) ){
			editCommand.getSelectedReportDefinitions().clear();
			for(ReportDefinition rd : editCommand.getApplicableReportDefinitions()){
				if(ArrayUtils.contains(rdIds, rd.getId().intValue())){
					editCommand.getSelectedReportDefinitions().add(rd);
				}
			}
		}
		
		
		//now refresh the not applicable/mandatory fields.
		editCommand.refreshMandatorySections();
		editCommand.initializeNotApplicableFields();
		editCommand.refreshMandatoryProperties();
    }


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
  
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao){
    	this.researchStaffDao = researchStaffDao;
    }
    
    public ResearchStaffDao getResearchStaffDao(){
    	return researchStaffDao;
    }
    
    public void setInvestigatorDao(InvestigatorDao investigatorDao){
    	this.investigatorDao = investigatorDao;
    }
    
    public InvestigatorDao getInvestigatorDao(){
    	return investigatorDao;
    }
}
