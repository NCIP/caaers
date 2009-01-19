package gov.nih.nci.cabig.caaers.workflow.handler;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowServiceImpl;
import gov.nih.nci.cabig.caaers.workflow.callback.CreateTaskJbpmCallback;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
/**
 * 
 * @author Biju Joseph
 *
 */
public class NodeSkipActionHandlerTest extends AbstractTestCase {
	
	
	private String taskDefName = "MyTask";
	private String emailAddress = "biju.joseph@semanticbits.com";
	
	private NodeSkipActionHandler handler;
	private WorkflowServiceImpl wfService;
	
	TaskConfig tConfig;
	

	protected void setUp() throws Exception {
		super.setUp();
		handler = new NodeSkipActionHandler();
		wfService = registerMockFor(WorkflowServiceImpl.class);
		
		handler.setWorkflowService(wfService);
	}
	

	
	
	public void testExecute_NotApplicable_Case() throws Exception {
		String wfDefName = "Test";
		String taskDefName = "a1";
		ProcessDefinition pDef = new ProcessDefinition();
		pDef.setName(wfDefName);
		
		ProcessInstance pInstance = registerMockFor(ProcessInstance.class);
		Node n = registerMockFor(Node.class);
		Token token = registerMockFor(Token.class);
		tConfig = Fixtures.createTaskConfig(taskDefName, false);
		
		ExecutionContext context = registerMockFor(ExecutionContext.class);
		EasyMock.expect(context.getProcessDefinition()).andReturn(pDef);
		EasyMock.expect(context.getProcessInstance()).andReturn(pInstance);
		EasyMock.expect(pInstance.getRootToken()).andReturn(token);
		EasyMock.expect(token.getNode()).andReturn(n);
		EasyMock.expect(n.getName()).andReturn(taskDefName);
		
		EasyMock.expect(wfService.findTaskConfig(wfDefName, taskDefName)).andReturn(tConfig);
		n.leave(context);
		replayMocks();
		handler.execute(context);
		verifyMocks();
		
	}
	

	public void testExecute_Applicable_Case() throws Exception {
		String wfDefName = "Test";
		String taskDefName = "a1";
		ProcessDefinition pDef = new ProcessDefinition();
		pDef.setName(wfDefName);
		
		ProcessInstance pInstance = registerMockFor(ProcessInstance.class);
		Node n = registerMockFor(Node.class);
		Token token = registerMockFor(Token.class);
		tConfig = Fixtures.createTaskConfig(taskDefName, true);
		
		ExecutionContext context = registerMockFor(ExecutionContext.class);
		EasyMock.expect(context.getProcessDefinition()).andReturn(pDef).anyTimes();
		EasyMock.expect(context.getNode()).andReturn(n).anyTimes();
		EasyMock.expect(context.getProcessInstance()).andReturn(pInstance);
		EasyMock.expect(pInstance.getRootToken()).andReturn(token);
		EasyMock.expect(token.getNode()).andReturn(n);
		EasyMock.expect(n.getName()).andReturn(taskDefName);
		
		EasyMock.expect(wfService.findTaskConfig(wfDefName, taskDefName)).andReturn(tConfig);
		
		List<User> users = new ArrayList<User>();
		User u1 = new ResearchStaff();
		u1.setLoginId("joel@efg.com");
		users.add(u1);
		
		EasyMock.expect(wfService.findTaskAssignees(wfDefName, taskDefName)).andReturn(users);
		
		wfService.createTaskInstances((CreateTaskJbpmCallback)EasyMock.anyObject());
		replayMocks();
		
		handler.execute(context);
		verifyMocks();
		
	}
	
	
}
