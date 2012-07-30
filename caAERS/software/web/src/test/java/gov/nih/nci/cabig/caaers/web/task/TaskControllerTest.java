package gov.nih.nci.cabig.caaers.web.task;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class TaskControllerTest extends TestCase {
	
	private TaskController taskController;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		taskController = new TaskController();
	}

	public void testHandleRequestInternal() throws Exception{
		ModelAndView mv = taskController.handleRequestInternal(new MockHttpServletRequest(), new MockHttpServletResponse());
		assertEquals("main/mainPage", mv.getViewName());
		List<TaskGroup> taskGroups = (List<TaskGroup>)mv.getModel().get("taskgroups");
		assertEquals(0, taskGroups.size());
		List<TaskGroup> newTaskGroups = new ArrayList<TaskGroup>();
		newTaskGroups.add(new TaskGroup());
		taskController.setTaskGroups(newTaskGroups);
		mv = taskController.handleRequestInternal(new MockHttpServletRequest(), new MockHttpServletResponse());
		taskGroups = (List<TaskGroup>)mv.getModel().get("taskgroups");
		assertEquals(1, taskGroups.size());
	}
	
}
