package gov.nih.nci.cabig.caaers.web.ae;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;

/**
 * @author Sameer Sawant
 */
public class EditExpeditedAdverseEventReportController extends AbstractExpeditedAdverseEventReportController {
	
	//validator needs to be called in onBindAndValidate()
	protected WebControllerValidator webControllerValidator;
	
	public EditExpeditedAdverseEventReportController() {
        setCommandClass(EditExpeditedAdverseEventReportCommand.class);
        setBindOnNewForm(true);
    }
	
	@Override
	public FlowFactory<ExpeditedAdverseEventReportCommand> getFlowFactory() {
		return new FlowFactory<ExpeditedAdverseEventReportCommand>() {
			public Flow<ExpeditedAdverseEventReportCommand> createFlow(ExpeditedAdverseEventReportCommand cmd) {
				Flow<ExpeditedAdverseEventReportCommand> flow = new Flow<ExpeditedAdverseEventReportCommand>("Edit Expedited Report Flow");
				flow.addTab(new AeReportPersonnelDetailsTab());
				flow.addTab(new AeReportSummaryTab());
				flow.addTab(new AeReportAdverseEventDetailsTab());
				flow.addTab(new AeReportStudyInterventionsTab());
				flow.addTab(new AeReportPatientDetailsTab());
				flow.addTab(new AeReportAdditionalInformationTab());
				flow.addTab(new AeReportAttributionTab());
				flow.addTab(new AeReportSubmitTab());
				return flow;
			}
		};
	}
	
	@Override
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page)
                    throws Exception {
        Map<String, Object> refdata = super.referenceData(request, oCommand, errors, page);
        return refdata;
    }
	
	@Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        EditExpeditedAdverseEventReportCommand command = new EditExpeditedAdverseEventReportCommand(getDao(),
                        reportDefinitionDao, assignmentDao, expeditedReportTree);
        
        return command;
    }
	
	@Override
    protected void onBindOnNewForm(HttpServletRequest request, Object cmd) throws Exception {
        super.onBindOnNewForm(request, cmd);
        // In edit flow from begining the tab must be hilighted.
        EditExpeditedAdverseEventReportCommand command = (EditExpeditedAdverseEventReportCommand) cmd;
    
    }
	
	@Override
    protected void onBind(HttpServletRequest request, Object command, BindException errors)
                    throws Exception {
        super.onBind(request, command, errors);
        log.debug("onBind");
        EditExpeditedAdverseEventReportCommand cmd = (EditExpeditedAdverseEventReportCommand) command;
        
	}
	
	@Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response,
                    Object oCommand, BindException errors) throws Exception {
        EditExpeditedAdverseEventReportCommand command = (EditExpeditedAdverseEventReportCommand) oCommand;

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
    
    @Override
    protected boolean suppressValidation(HttpServletRequest request,Object command) {
    	 if (super.suppressValidation(request, command)) return true;
    
    	 return false;
    }
    
    @Override
    protected ExpeditedAdverseEventReportCommand save(
    		ExpeditedAdverseEventReportCommand command, Errors errors) {
    	command.save();
    	return null;
    }
    
	@Required
	public void setWebControllerValidator(WebControllerValidator webControllerValidator) {
	    this.webControllerValidator = webControllerValidator;
	}
}