package gov.nih.nci.cabig.caaers.service.workflow;

import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.User;

import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * 
 * @author Biju Joseph
 *
 */
public class WorkflowServiceImplIntegrationTest extends CaaersDbTestCase {
	
	
	WorkflowServiceImpl wfService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		wfService = (WorkflowServiceImpl)getDeployedApplicationContext().getBean("workflowService");
	}
	
	public void testCreateProcessInstance() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
	}
	
	public void testFetchTaskInstances() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
		List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
		assertNotNull(tasks);
		assertFalse(tasks.isEmpty());
		assertEquals("Submit Reporting Period for Data Coordinator Review",tasks.get(0).getName());
	}
	
	public void testFetchProcessInstance() {
		Long id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
			assertNotNull(pInstance);
			id = pInstance.getId();
		}
		interruptSession();
		{
			ProcessInstance loadedInstance = wfService.fetchProcessInstance(id);
			assertNotNull(loadedInstance);
		}
	}

	public void testNextTransitions() {
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(1, nextTransitions.size());
			assertEquals("Approve Reporting Period", nextTransitions.get(0).getName());
			assertEquals("Finalize Reporting Period", nextTransitions.get(0).getTo().getName());
		}
	}
	
	public void testNextTransitionNames() {
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		{
			List<String> nextTransitions = wfService.nextTransitionNames(id);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(1, nextTransitions.size());
			assertEquals("Approve Reporting Period", nextTransitions.get(0));
		}
	}
	
	public void testAdvanceWorkflow(){
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		String nextTransition = null;
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id);
			assertEquals("Approve Reporting Period", nextTransitions.get(0).getName());
			assertEquals("Finalize Reporting Period", nextTransitions.get(0).getTo().getName());
			nextTransition = "Approve Reporting Period";
		}
		interruptSession();
		{
			ReviewStatus status = wfService.advanceWorkflow(id, nextTransition);
			assertEquals(ReviewStatus.READY_FOR_FINALIZE, status);
			List<TaskInstance> taskInstances = wfService.fetchTaskInstances("datacoordinator@abc.com");
			assertNotNull(taskInstances);
			assertFalse(taskInstances.isEmpty());
			assertEquals("Finalize Reporting Period", taskInstances.get(0).getName());
			assertEquals("Only one task to be created",1, taskInstances.size());
		}
		
		
	}
	
	public void testCloseAllOpenTaskInstances() {
		Long id = null;
		{
			List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
			assertTrue(tasks.isEmpty());
		}
		
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
			assertNotNull(pInstance);
			id = pInstance.getId();
			assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
			List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
			assertNotNull(tasks);
			assertFalse(tasks.isEmpty());
			assertEquals("Submit Reporting Period for Data Coordinator Review",tasks.get(0).getName());
		}
		interruptSession();
		String nextTransition ="Approve Reporting Period";
		{
			ReviewStatus status = wfService.advanceWorkflow(id.intValue(), nextTransition);
			assertEquals(ReviewStatus.READY_FOR_FINALIZE, status);
			List<TaskInstance> taskInstances = wfService.fetchTaskInstances("datacoordinator@abc.com");
			assertNotNull(taskInstances);
			assertFalse(taskInstances.isEmpty());
		}
		interruptSession();
		{
			List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
			assertTrue(tasks.isEmpty());
		}
		
	}
	
	
	public void testFindTaskAssignees() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
		
		List<User> assignees = wfService.findTaskAssignees(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, "Submit Reporting Period for Data Coordinator Review");
		assertNotNull(assignees);
		assertEquals(2, assignees.size());
		assertEquals("abc@def.com", assignees.get(0).getLoginId());
		assertEquals("bush@def.com", assignees.get(1).getLoginId());
	}


}
