package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Date;

/**
 * @author Krikor Krumlian
 */
public class SubmitReportController extends AbstractAdverseEventInputController {
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
            = new SubmitExpeditedAdverseEventCommand(getDao(), reportDefinitionDao, assignmentDao);
        String reportIndex = request.getParameter("reportIndex");
        command.setReportIndex(reportIndex);
        return command;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
    	SubmitExpeditedAdverseEventCommand command = (SubmitExpeditedAdverseEventCommand) oCommand;
        Integer reportIndex = Integer.valueOf(command.getReportIndex());
    	command.getAeReport().getReports().get(((int)reportIndex)).setSubmittedOn(new Date());
    	command.getAeReport().getReports().get(((int)reportIndex)).setStatus(ReportStatus.COMPLETED);
    	command.save();
        // everything is saved as you move from page to page, so no action required here
        Map<String, Object> model = new ModelMap("aeReport", command.getAeReport().getId());
        model.put("action", "reportSubmission");
        return new ModelAndView("redirectToExpeditedAeEdit", model);
    }
}
