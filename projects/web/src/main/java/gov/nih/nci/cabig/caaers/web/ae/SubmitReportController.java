package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

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

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	log.debug("In form backing object");
    	RenderDecisionManager renderDecisionManager = renderDecisionManagerFactoryBean.getRenderDecisionManager();
        SubmitExpeditedAdverseEventCommand command = new SubmitExpeditedAdverseEventCommand(
                        getDao(), reportDefinitionDao, assignmentDao, reportingPeriodDao,
                        expeditedReportTree, renderDecisionManager, reportRepository);
        String reportId = request.getParameter("reportId");
        command.setReportId(reportId);
        command.setFrom(request.getParameter("from"));
        command.setWorkflowEnabled(getConfiguration().get(getConfiguration().ENABLE_WORKFLOW));
        return command;

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
        Integer reportIndex = Integer.valueOf(command.getReportIndex());

        log.debug("Report Index :" + reportIndex.intValue());
        ExpeditedAdverseEventReport aeReport = command.getAeReport();
        Report report = aeReport.getReports().get(((int) reportIndex));

        reportSubmissionService.submitReport(report);
        
        ModelAndView modelAndView;
        if (command.getFrom() != null && command.getFrom().equals("list")) {
            Map<String, Object> model = new ModelMap("participant", command.getParticipant()
                            .getId());
            model.put("study", command.getStudy().getId());
            modelAndView = new ModelAndView("redirectToAeList", model);
        } else {
            Map<String, Object> model = new ModelMap("aeReport", aeReport.getId());
            model.put("action", "reportSubmission");
            modelAndView = new ModelAndView("redirectToExpeditedAeEdit", model);
        }

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
   
}
