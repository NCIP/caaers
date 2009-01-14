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
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
	}
	
	public void testFetchTaskInstances() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
		assertNotNull(pInstance);
		assertEquals( "Submit Reporting Period for Data Coordinator Review" ,pInstance.getRootToken().getNode().getName());
		List<TaskInstance>tasks = wfService.fetchTaskInstances("bush@def.com");
		System.out.println(tasks);
	}

//	public void testNextTransitions() {
//		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
//		Long l = pInstance.getId();
//		Integer id = new Integer(l.intValue());
//		
//		List<Transition> nextTransitions = wfService.nextTransitions(id);
//		assertEquals("Data Coordinator Review", nextTransitions.get(0).getTo().getName());
//	}
//
//	public void testFetchProcessInstance() {
//		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
//		ProcessInstance loadedInstance = wfService.fetchProcessInstance(pInstance.getId());
//		assertNotNull(loadedInstance);
//	}




}
