package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.api.AdverseEventReportSerializer;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Krikor Krumlian
 */
public class SubmitReportController extends AbstractAdverseEventInputController {

	private SchedulerService schedulerService;
	
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
        return command;

    }
    
    @Override
    protected ExpeditedAdverseEventInputCommand save(
    		ExpeditedAdverseEventInputCommand command, Errors errors) {
    	log.debug("In overriden save");
    	command.save();
    	return null;
    }

    @Override
    // @Transactional
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                    Object oCommand, BindException errors) throws Exception {
    	log.debug("In processFinish");
        SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
        Integer reportIndex = Integer.valueOf(command.getReportIndex());

        log.debug("Report Index :" + reportIndex.intValue());
        ExpeditedAdverseEventReport aeReport = command.getAeReport();
        Report report = aeReport.getReports().get(((int) reportIndex));

        ReportStatus status = null;
        Date date = new Date();
        String message = "";
        String url = "";

        boolean endPointUrl = false;
        for (ReportDelivery delivery : report.getReportDeliveries()) {
            ReportDeliveryDefinition rdd = delivery.getReportDeliveryDefinition();

            if (rdd.getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_URL)) {
                endPointUrl = true;
                break;
            }
        }


        if (!endPointUrl) {
            // TODO: take out
            status = ReportStatus.COMPLETED;
            schedulerService.unScheduleNotification(report);
        } else {
            status = ReportStatus.INPROCESS;
        }

        // generate report and send ...
        AdeersReportGenerator aegen = (AdeersReportGenerator) getApplicationContext().getBean(
                        "adeersReportGenerator");
        
       
        
        AdverseEventReportSerializer aeser = new AdverseEventReportSerializer();
        String xml = aeser.serialize(aeReport);
        
        report.setStatus(status);
        report.setSubmissionUrl(url);
        report.setSubmissionMessage(message);
        report.setSubmittedOn(date);
        report.getLastVersion().setReportStatus(status);
        report.getLastVersion().setSubmissionUrl(url);
        report.getLastVersion().setSubmissionMessage(message);
        report.getLastVersion().setSubmittedOn(date);
        log.debug("About to call save");
        command.save();
        command.flush();
        log.debug("Saved and flushed");
        try {
            aegen.generateAndNotify(aeReport.getId() + "", report, xml);
            log.debug("sucessfully notified");
        } catch (Exception e) {
            log.error("Error broadcasting message to ESB " ,e);
            status = ReportStatus.FAILED;
            message = "Problem communicating with ESB <br> Please try to resubmit the report <br>"
                            + e.getMessage();
            
            report.getLastVersion().setReportStatus(status);
            report.getLastVersion().setSubmissionMessage(message);
            command.save();
            log.debug("Saved and updated the status as failed");
        }
       

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
    
    public void setSchedulerService(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

}
