package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	Map<String, Object > variables = new HashMap<String, Object>();
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		wfService = (WorkflowServiceImpl)getDeployedApplicationContext().getBean("workflowService");
		
	}
	
	public void testCreateProcessInstance() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
	}
	
	public void testFetchTaskInstances() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
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
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
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
		String loginId = "SYSTEM_ADMIN";
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(1, nextTransitions.size());
			assertEquals("Approve Reporting Period", nextTransitions.get(0).getName());
			assertEquals("Approved", nextTransitions.get(0).getTo().getName());
		}
	}
	public void testNextTransitionNames() {
		Integer id = null;
		String loginId = "SYSTEM_ADMIN";
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER,variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		{
			List<String> nextTransitions = wfService.nextTransitionNames(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(1, nextTransitions.size());
			assertEquals("Approve Reporting Period", nextTransitions.get(0));
		}
	}
	public void testNextTransitions_SiteCRA() {
		Integer id = null;
		String loginId = "pc@def.com";
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(1, nextTransitions.size());
			assertEquals("Approve Reporting Period", nextTransitions.get(0).getName());
			assertEquals("Approved", nextTransitions.get(0).getTo().getName());
		}
	}
	
	
	public void testNextTransitions_DataCoordinator() {
		Integer id = null;
		String loginId = "aec@def.com";
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertTrue(nextTransitions.isEmpty());
		}
	}

	
	public void testAdvanceWorkflow(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		String nextTransition = null;
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertEquals("Approve Reporting Period", nextTransitions.get(0).getName());
			assertEquals("Approved", nextTransitions.get(0).getTo().getName());
			nextTransition = "Approve Reporting Period";
		}
		interruptSession();
		{
			ReviewStatus status = wfService.advanceWorkflow(id, nextTransition);
			assertEquals(ReviewStatus.APPROVED, status);
			List<TaskInstance> taskInstances = wfService.fetchTaskInstances("datacoordinator@abc.com");
			assertNotNull(taskInstances);
			assertTrue(taskInstances.isEmpty());
			
		}
		
		
	}
	
	public void testCloseAllOpenTaskInstances() {
		Long id = null;
		{
			List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
			assertTrue(tasks.isEmpty());
		}
		
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
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
			assertEquals(ReviewStatus.APPROVED, status);
			List<TaskInstance> taskInstances = wfService.fetchTaskInstances("datacoordinator@abc.com");
			assertNotNull(taskInstances);
			assertTrue(taskInstances.isEmpty());
		}
		interruptSession();
		{
			List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
			assertTrue(tasks.isEmpty());
		}
		
	}
	
	
	public void testFindTaskAssignees() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
		
		List<User> assignees = wfService.findTaskAssignees(pInstance, "Submit Reporting Period for Data Coordinator Review");
		assertNotNull(assignees);
		assertEquals(2, assignees.size());
		assertEquals("abc@def.com", assignees.get(0).getLoginId());
		assertEquals("bush@def.com", assignees.get(1).getLoginId());
	}


}
