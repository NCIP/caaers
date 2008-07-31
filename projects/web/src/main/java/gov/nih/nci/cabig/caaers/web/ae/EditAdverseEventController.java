package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rhett Sutphin
 */
public class EditAdverseEventController extends AbstractAdverseEventInputController {
    private Task task;
	//validator needs to be called in onBindAndValidate()
	protected WebControllerValidator webControllerValidator;
	
    public EditAdverseEventController() {
        setCommandClass(EditExpeditedAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected FlowFactory<ExpeditedAdverseEventInputCommand> createFlowFactory() {
        return new ExpeditedFlowFactory("Edit expedited report");
    }

    @Override
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page)
                    throws Exception {
        Map<String, Object> refdata = super.referenceData(request, oCommand, errors, page);
        refdata.put("currentTask", task);
        return refdata;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        EditExpeditedAdverseEventCommand command = new EditExpeditedAdverseEventCommand(getDao(),
                        reportDefinitionDao, assignmentDao, expeditedReportTree);
        
        return command;
    }

    @Override
    protected void onBindOnNewForm(HttpServletRequest request, Object cmd) throws Exception {
        super.onBindOnNewForm(request, cmd);
        // In edit flow from begining the tab must be hilighted.
        EditExpeditedAdverseEventCommand command = (EditExpeditedAdverseEventCommand) cmd;
        command.setMandatorySections(evaluationService.mandatorySections(command.getAeReport()));
        command.refreshMandatoryProperties();

    }

    @Override
    protected void onBind(HttpServletRequest request, Object command, BindException errors)
                    throws Exception {
        super.onBind(request, command, errors);
        log.debug("onBind");
        EditExpeditedAdverseEventCommand cmd = (EditExpeditedAdverseEventCommand) command;
        // Amendment implementation
        // Test this
        if (request.getParameter("reportId") != null) {
            Integer reportId = Integer.parseInt(request.getParameter("reportId"));
            for (Report report : cmd.getAeReport().getReports()) {
                if (report.getId().equals(reportId)
                                && !report.getLastVersion().getReportStatus().equals(
                                                ReportStatus.PENDING)) {
                    ReportVersion reportVersion = new ReportVersion();
                    reportVersion.setCreatedOn(nowFactory.getNow());
                    reportVersion.setReportStatus(ReportStatus.PENDING);
                    report.addReportVersion(reportVersion);
                    break;
                }
            }
        }
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
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                    Object oCommand, BindException errors) throws Exception {
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
	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors, int page) throws Exception {
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
    	  //special case, if it is attribution page, allow go back.
         if(getFlow(aeCommand).getTab(getCurrentPage(request)).getShortTitle().equals(ExpeditedReportSection.ATTRIBUTION_SECTION.getDisplayName())){
        	 return super.getCurrentPage(request) > aeCommand.getNextPage();
         }
         return false;
    }
    
    @Override
    protected ExpeditedAdverseEventInputCommand save(
    		ExpeditedAdverseEventInputCommand command, Errors errors) {
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
}
