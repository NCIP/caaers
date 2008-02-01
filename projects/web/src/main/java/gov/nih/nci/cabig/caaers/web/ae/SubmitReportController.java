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
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Krikor Krumlian
 */
public class SubmitReportController extends AbstractAdverseEventInputController {
	
	//private AdeersReportGenerator adeersReportGenerator;
	//private ReportDao reportDao;


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
    	SubmitExpeditedAdverseEventCommand command
            = new SubmitExpeditedAdverseEventCommand(getDao(), reportDefinitionDao, assignmentDao, expeditedReportTree);
        String reportId = request.getParameter("reportId");
        command.setReportId(reportId);
        command.setFrom(request.getParameter("from"));
        return command;
        
    }

    @Override	
   // @Transactional
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
    	SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
        Integer reportIndex = Integer.valueOf(command.getReportIndex());
        
        ExpeditedAdverseEventReport aeReport = command.getAeReport();
        Report report = aeReport.getReports().get(((int)reportIndex));
        
       ReportStatus status = null;
       Date date = new Date();
       String message = "";
       String url = "";
        
        boolean endPointUrl = false;
        for (ReportDelivery delivery: report.getReportDeliveries()) {
			ReportDeliveryDefinition rdd = delivery.getReportDeliveryDefinition();

			if (rdd.getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_URL)) {
				endPointUrl=true;
				break;
			}
		}
        
        System.out.println("END POINT URL .. " + endPointUrl);
        
        if (!endPointUrl) {
	        // TODO: take out
        	status = ReportStatus.COMPLETED;
    	} else {
        	status = ReportStatus.INPROCESS;      	
        }
        
    	//generate report and send ...
    	AdeersReportGenerator aegen = (AdeersReportGenerator)getApplicationContext().getBean("adeersReportGenerator");
     	//command.save();

    	AdverseEventReportSerializer aeser = new AdverseEventReportSerializer();
		String xml = aeser.serialize(aeReport);
		//expeditedAdverseEventReportDao.save(aeReport);
		
    	
    	//String xml = "<AdverseEventReport>   <id>99</id> </AdverseEventReport>";
		
		try {
			aegen.generateAndNotify(aeReport.getId()+"", report , xml);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error broadcasting message to ESB " + e.getMessage());
			status = ReportStatus.FAILED;
			message = "Problem communicating with ESB "+e.getMessage();
		}
		report.setStatus(status);
		report.setSubmissionUrl(url);
		report.setSubmissionMessage(message);
		report.setSubmittedOn(date);
		
		report.getLastVersion().setReportStatus(status);
		report.getLastVersion().setSubmissionUrl(url);
		report.getLastVersion().setSubmissionMessage(message);
		report.getLastVersion().setSubmittedOn(date);		
		
		command.save();
    	
    	
    	ModelAndView modelAndView;
    	if (command.getFrom()!= null && command.getFrom().equals("list") ){
    		Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
            model.put("study", command.getStudy().getId());
            modelAndView =  new ModelAndView("redirectToAeList", model);
    	}else{
    		Map<String, Object> model = new ModelMap("aeReport", aeReport.getId());
            model.put("action", "reportSubmission");
    		modelAndView = new ModelAndView("redirectToExpeditedAeEdit", model);
    	}
    	
        return modelAndView;
    }


}
