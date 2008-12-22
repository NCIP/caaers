package gov.nih.nci.cabig.caaers.service.workflow;

import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;

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
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_REPORTING);
		assertNotNull(pInstance);
		assertEquals( "Publish Report For Review" ,pInstance.getRootToken().getNode().getName());
	}
	
	public void testFetchTaskInstances() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_REPORTING);
		assertNotNull(pInstance);
		assertEquals( "Publish Report For Review" ,pInstance.getRootToken().getNode().getName());
		List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
		System.out.println(tasks);
	}

	public void testNextTransitions() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_REPORTING);
		Long l = pInstance.getId();
		Integer id = new Integer(l.intValue());
		
		List<Transition> nextTransitions = wfService.nextTransitions(id);
		assertEquals("Ready For Review", nextTransitions.get(0).getTo().getName());
	}

	public void testFetchProcessInstance() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_REPORTING);
		ProcessInstance loadedInstance = wfService.fetchProcessInstance(pInstance.getId());
		assertNotNull(loadedInstance);
	}




}
