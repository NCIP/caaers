package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Biju Joseph
 * @author Krikor Krumlian
 */
public class SubmitReportController extends AbstractAdverseEventInputController {

	protected ReportSubmissionService reportSubmissionService;
	
	protected final Log log = LogFactory.getLog(getClass());
	
    public SubmitReportController() {
        setCommandClass(SubmitExpeditedAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected FlowFactory<ExpeditedAdverseEventInputCommand> createFlowFactory() {
        return new SubmitReportFlowFactory("Submit the Report");
    }

    @SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request, Object o,	Errors errors, int page) throws Exception {
        SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) o;
		Map referenceData =  super.referenceData(request, command, errors, page);
		
        Integer reportIndex = Integer.valueOf(command.getReportIndex());

        log.debug("Report Index :" + reportIndex.intValue());
        ExpeditedAdverseEventReport aeReport = command.getAeReport();
        Report report = aeReport.getReports().get(((int) reportIndex));
	
        Map<String, String> aeSummaryMap = (HashMap<String, String>) referenceData.get("aesummary");
        if(aeSummaryMap != null && report != null){
        	aeSummaryMap.put("Report Name", report.getName());
        }
        
		return referenceData;
    }
    
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	log.debug("In form backing object");
    	RenderDecisionManager renderDecisionManager = renderDecisionManagerFactoryBean.getRenderDecisionManager();
        SubmitExpeditedAdverseEventCommand command = new SubmitExpeditedAdverseEventCommand(getDao(), reportDefinitionDao, assignmentDao, reportingPeriodDao,
                        expeditedReportTree, renderDecisionManager, reportRepository, adverseEventRoutingAndReviewRepository);
        String reportId = request.getParameter("reportId");
        command.setReportId(reportId);
        command.setFrom(request.getParameter("from"));
        command.setWorkflowEnabled(getConfiguration().get(getConfiguration().ENABLE_WORKFLOW));
        return command;

    }
    
    @Override
    protected Object currentFormObject(HttpServletRequest request, Object oCommand) throws Exception {
    	SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
    	/*if(!command.getReportSubmitted()){
    		log.debug("In currentFormObject :" + oCommand );
    		((ExpeditedAdverseEventInputCommand) oCommand).reassociate();
    		log.debug("After calling reassociate");
    		oCommand = super.currentFormObject(request, oCommand);
    		log.debug("After calling super class currentFormObject :" + oCommand);
    	}else{
    		ExpeditedAdverseEventReport aeReport = reportDao.getById(command.getAeReport().getId());
    		(SubmitExpeditedAdverseEventCommand)
    	}*/
    	if(!command.getReportSubmitted()){
    		log.debug("In currentFormObject :" + command);
    		command.reassociate();
    		log.debug("After calling reassociate");
    		command = (SubmitExpeditedAdverseEventCommand)super.currentFormObject(request, command);
    		log.debug("After calling super class currentFormObject : " + command);
    	}else{
    		//ExpeditedAdverseEventReport aeReport = reportDao.getById(command.getAeReport().getId());
    		//command.setLastVersion(aeReport.getReports().get(Integer.parseInt(command.getReportIndex())).getLastVersion());
    		//Report report = reportDao.getById(command.getAeReport().getReports().get(Integer.parseInt(command.getReportIndex())).getId());
		 	//command.setLastVersion(report.getLastVersion());
    	}
        return command;
    }
    
    @Override
	protected boolean shouldSave(HttpServletRequest request,ExpeditedAdverseEventInputCommand oCommand,Tab<ExpeditedAdverseEventInputCommand> tab) {
    	SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
    	if(command.getReportSubmitted())
    		return false;
    	return true;
    }
    
    @Override
    protected ExpeditedAdverseEventInputCommand save(ExpeditedAdverseEventInputCommand command, Errors errors) {
    	log.debug("In overriden save");
    	command.save();
    	return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
    	log.debug("In processFinish");
        SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
        ModelAndView modelAndView;
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        modelAndView = new ModelAndView("redirectToAeList", model);
        log.debug("Returning the viewname as :" + modelAndView.getViewName());
        return modelAndView;
    }
    
   public ReportSubmissionService getReportSubmissionService() {
	   return reportSubmissionService;
   }
  
   @Required
   public void setReportSubmissionService(ReportSubmissionService reportSubmissionService) {
	   this.reportSubmissionService = reportSubmissionService;
   }
   
   @Override
   protected boolean displayReportContextMenu(int page) {
	return false;
   }
   
}
