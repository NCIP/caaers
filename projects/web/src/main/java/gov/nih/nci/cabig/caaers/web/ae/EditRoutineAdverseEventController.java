package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import gov.nih.nci.cabig.ctms.web.chrome.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Krikor Krumlian
 */
public class EditRoutineAdverseEventController extends AbstractRoutineAdverseEventInputController {
	private Task task;
    public EditRoutineAdverseEventController() {
        setCommandClass(EditRoutineAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected FlowFactory<RoutineAdverseEventInputCommand> createFlowFactory() {
        return new RoutineFlowFactory("Edit routine AEs");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new EditRoutineAdverseEventCommand(getDao(), reportDao, nowFactory, evaluationService);
        //new CreateRoutineAdverseEventCommand(assignmentDao, routineReportDao, reportDao, nowFactory, evaluationService);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(
        HttpServletRequest request, Object oCommand, Errors errors, int page
    ) throws Exception {
    	 Map<String, Object> refdata = super.referenceData(request, oCommand, errors, page);
    	 refdata.put("currentTask", task);
    	 return refdata;
		}
    
    
    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
        EditRoutineAdverseEventCommand command = (EditRoutineAdverseEventCommand) oCommand;
        command.checkRoutineAesForExpeditedness();
        // everything is saved as you move from page to page, so no action required here
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToAeList", model);
    }
    
    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command,BindException errors,int page)throws Exception {

    	// populate categories
    	// TODO See if you do the below with binding
    	EditRoutineAdverseEventCommand adverseEventCommand = (EditRoutineAdverseEventCommand)command;

    	if (adverseEventCommand.getCtcCatIds() != null && page ==0 ) {
			for (String st : adverseEventCommand.getCtcCatIds()) {
				CtcCategory ctcCategory = ctcCategoryDao.getById(Integer.parseInt(st));
				adverseEventCommand.getCategories().add(ctcCategory);
			}
    	}

    }

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
