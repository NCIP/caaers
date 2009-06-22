package gov.nih.nci.cabig.caaers.workflow.callback;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.User;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CreateTaskJbpmCallbackTest extends AbstractTestCase {
	ExecutionContext context;
	List<User> taskAssigneesList;
	Node node ;
	TaskMgmtInstance taskMgmtInstance;
	JbpmContext jbpmContext;
	protected void setUp() throws Exception {
		super.setUp();
		
		
		taskAssigneesList = new ArrayList<User>();
		Investigator inv1 = Fixtures.createInvestigator("test1");
		inv1.setEmailAddress("joel1@abc.com");
		inv1.setLoginId("joel1@abc.com");
		
		Investigator inv2 = Fixtures.createInvestigator("test2");
		inv2.setEmailAddress("joel2@abc.com");
		inv2.setLoginId("joel2@abc.com");
		
		taskAssigneesList.add(inv1);
		taskAssigneesList.add(inv2);
		
		node = registerMockFor(Node.class);
		taskMgmtInstance = registerMockFor(TaskMgmtInstance.class);
		context = registerMockFor(ExecutionContext.class);
	}

	public void testDoInJbpm() {
		String nodeName = "a1";
		
		TaskInstance taskInstance = new TaskInstance();
		ProcessDefinition processDefinition = new ProcessDefinition();
		processDefinition.setName("Test");
		
		EasyMock.expect(node.getName()).andReturn(nodeName).anyTimes();
		EasyMock.expect(context.getNode()).andReturn(node);
		EasyMock.expect(context.getTaskMgmtInstance()).andReturn(taskMgmtInstance);
		EasyMock.expect(context.getProcessDefinition()).andReturn(processDefinition);
		EasyMock.expect(taskMgmtInstance.createTaskInstance(null, context)).andReturn(taskInstance);
		
		replayMocks();
		CreateTaskJbpmCallback callback = new CreateTaskJbpmCallback(context, taskAssigneesList);
		TaskInstance returnedTaskInstance = (TaskInstance) callback.doInJbpm(jbpmContext);
		verifyMocks();
		
		assertEquals(nodeName,returnedTaskInstance.getName());
		assertEquals(2, returnedTaskInstance.getPooledActors().size());
		assertTrue(returnedTaskInstance.getPooledActors().toString().contains(("joel2@abc.com")));
		assertTrue(returnedTaskInstance.getPooledActors().toString().contains(("joel1@abc.com")));
		assertFalse(returnedTaskInstance.getPooledActors().toString().contains(("joel12@abc.com")));

		
	}

}
