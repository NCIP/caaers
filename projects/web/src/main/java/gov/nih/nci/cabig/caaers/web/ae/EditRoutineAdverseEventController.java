package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;

import gov.nih.nci.cabig.caaers.domain.CtcCategory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Krikor Krumlian
 */
public class EditRoutineAdverseEventController extends AbstractRoutineAdverseEventInputController<EditRoutineAdverseEventCommand> {
    public EditRoutineAdverseEventController() {
        setCommandClass(EditRoutineAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected String getFlowName() {
        return "Edit expedited report";
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new EditRoutineAdverseEventCommand(ruleExecutionService);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
    	EditRoutineAdverseEventCommand command = (EditRoutineAdverseEventCommand) oCommand;
        
        //command.fireAERules();
        // everything is saved as you move from page to page, so no action required here
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToAeList", model);
    }
    
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
}
