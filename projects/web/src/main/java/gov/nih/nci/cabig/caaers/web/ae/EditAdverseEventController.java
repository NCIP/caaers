package gov.nih.nci.cabig.caaers.web.ae;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class EditAdverseEventController extends AbstractAdverseEventInputController<EditAdverseEventCommand> {
    public EditAdverseEventController() {
        setCommandClass(EditAdverseEventCommand.class);
        setBindOnNewForm(true);
    }

    @Override
    protected String getFlowName() {
        return "Edit AE";
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new EditAdverseEventCommand(ruleExecutionService);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object oCommand, BindException errors) throws Exception {
        EditAdverseEventCommand command = (EditAdverseEventCommand) oCommand;
        
        command.fireAERules();
        // everything is saved as you move from page to page, so no action required here
        Map<String, Object> model = new ModelMap("participant", command.getParticipant().getId());
        model.put("study", command.getStudy().getId());
        return new ModelAndView("redirectToAeList", model);
    }
}
