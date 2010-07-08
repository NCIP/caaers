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
 * Does the integration test on workflow service. 
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

    //creation of a process instance is tested here. 
	public void testCreateProcessInstance() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
	}

    //creating and testing whether the task instances are created
	public void testFetchTaskInstances() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER, variables);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
		List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
		assertNotNull(tasks);
		assertFalse(tasks.isEmpty());
		assertEquals("Submit Reporting Period for Data Coordinator Review",tasks.get(0).getName());
	}

    //test creation and feting the process and tasks correctly
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

    //checks whether the transition tokens are properly executed. 
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

    //identifies that the base workflow is correctly deployed and retrieves the transitions names correctly
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

    //makes sure that the CRA login see only the transitions designated to him.
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
	
	//makes sure that the DC see only the designated transitions. 
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

	//test the propogation of the workfow flow via a specified token.
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

    //when the workflow is terminated, makessure that the opent tasks are terminated properly. 
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

    
    //retrieves the assignees of the tasks
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
