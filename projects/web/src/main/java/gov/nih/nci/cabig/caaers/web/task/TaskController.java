package gov.nih.nci.cabig.caaers.web.task;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Biju Joseph
 */
public class TaskController extends AbstractController {

    private List<TaskGroup> taskGroups = new ArrayList<TaskGroup>();

    public ModelAndView handleRequestInternal(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("main/mainPage");
        mav.addObject("taskgroups", taskGroups);
        return mav;
    }

    @Required
    public void setTaskGroups(List<TaskGroup> taskGroups) {
        this.taskGroups = taskGroups;
    }
}
