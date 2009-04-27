package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;

public class WorkflowServiceImplIntegrationExpeditedDomesticTest extends CaaersDbTestCase {
	WorkflowServiceImpl wfService;
	Map<String, Object > variables = new HashMap<String, Object>();
	protected void setUp() throws Exception {
		super.setUp();
		wfService = (WorkflowServiceImpl)getDeployedApplicationContext().getBean("workflowService");
	}
	
	public void testCreateProcessInstance() {
		ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
		assertNotNull(pInstance);
		assertEquals( "Submit Report To Physician" ,pInstance.getRootToken().getNode().getName());
	}
	
	public void testNextTransitions() {
		Integer id = null;
		String loginId = "SYSTEM_ADMIN";
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Send to Physician for Review", nextTransitions.get(1).getName());
			assertEquals("Physician Review", nextTransitions.get(1).getTo().getName());
		}
	}
	
	public void testAdvanceWorkflow_ToSAECoordinatorReview(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Submit to Central Office SAE Coordinator");
			assertEquals(ReviewStatus.CENTRAL_OFFICE_REVIEW, status);
		}
		interruptSession();
		
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			
			assertEquals("Request Additional Information", nextTransitions.get(0).getName());
			assertEquals("Provide Additional Information To Central Office", nextTransitions.get(0).getTo().getName());
			
			assertEquals("Approve Report", nextTransitions.get(1).getName());
			assertEquals("End Expedited Domestic Flow", nextTransitions.get(1).getTo().getName());
		}
	}
	
	public void testAdvanceWorkflow_ToPhysicianReview(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Send to Physician for Review");
			assertEquals(ReviewStatus.PHYSICIAN_REVIEW, status);
			
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Approve Report", nextTransitions.get(1).getName());
			assertEquals("Submit Report To Central Office", nextTransitions.get(1).getTo().getName());
			
			assertEquals("Request Additional Information", nextTransitions.get(0).getName());
			assertEquals("Provide Additional Information To Physician", nextTransitions.get(0).getTo().getName());
		}
		
		
	}
	
	public void testAdvanceWorkflow_ToApproveReportByPhysician(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Send to Physician for Review");
			assertEquals(ReviewStatus.PHYSICIAN_REVIEW, status);
			
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Approve Report", nextTransitions.get(1).getName());
			assertEquals("Submit Report To Central Office", nextTransitions.get(1).getTo().getName());
			
			assertEquals("Request Additional Information", nextTransitions.get(0).getName());
			assertEquals("Provide Additional Information To Physician", nextTransitions.get(0).getTo().getName());
		}
		interruptSession();
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Approve Report");
			assertEquals(ReviewStatus.PHYSICIAN_APPROVED, status);
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Submit to Central Office SAE Coordinator", nextTransitions.get(1).getName());
			assertEquals("Central Office SAE Coordinator Review", nextTransitions.get(1).getTo().getName());
			assertEquals("Send to Physician for Review", nextTransitions.get(0).getName());
		}
	}
	
	public void testAdvanceWorkfow_toSendToPhysicianForReviewMultipleTimes(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Send to Physician for Review");
			assertEquals(ReviewStatus.PHYSICIAN_REVIEW, status);
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Approve Report", nextTransitions.get(1).getName());
			assertEquals("Submit Report To Central Office", nextTransitions.get(1).getTo().getName());
			
			assertEquals("Request Additional Information", nextTransitions.get(0).getName());
			assertEquals("Provide Additional Information To Physician", nextTransitions.get(0).getTo().getName());
		}
		interruptSession();
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Approve Report");
			assertEquals(ReviewStatus.PHYSICIAN_APPROVED, status);
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Submit to Central Office SAE Coordinator", nextTransitions.get(1).getName());
			assertEquals("Central Office SAE Coordinator Review", nextTransitions.get(1).getTo().getName());
			assertEquals("Send to Physician for Review", nextTransitions.get(0).getName());
		}
		interruptSession();
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Send to Physician for Review");
			assertEquals(ReviewStatus.PHYSICIAN_REVIEW, status);
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Approve Report", nextTransitions.get(1).getName());
			assertEquals("Submit Report To Central Office", nextTransitions.get(1).getTo().getName());
			
			assertEquals("Request Additional Information", nextTransitions.get(0).getName());
			assertEquals("Provide Additional Information To Physician", nextTransitions.get(0).getTo().getName());
		}
	}
	
	public void testAdvanceWorkflow_ToCentralOfficeSAECoordinatorReview(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Submit For Physician Review");
			assertEquals(ReviewStatus.PHYSICIAN_REVIEW, status);
			
		}
		interruptSession();
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Approve Report");
			assertEquals(ReviewStatus.PHYSICIAN_APPROVED, status);
			
			status = wfService.advanceWorkflow(id, "Submit to Central Office SAE Coordinator");
			assertEquals(ReviewStatus.CENTRAL_OFFICE_REVIEW, status);
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertFalse(nextTransitions.isEmpty());
			assertEquals(2, nextTransitions.size());
			assertEquals("Approve Report", nextTransitions.get(1).getName());
			assertEquals("End Expedited Domestic Flow", nextTransitions.get(1).getTo().getName());
			
			assertEquals("Request Additional Information", nextTransitions.get(0).getName());
			assertEquals("Provide Additional Information To Central Office", nextTransitions.get(0).getTo().getName());
		}
	}
	
	public void testAdvanceWorkflow_ToSubmitToSponsor(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Submit For Physician Review");
			assertEquals(ReviewStatus.PHYSICIAN_REVIEW, status);
			
			status = wfService.advanceWorkflow(id, "Approve Report");
			assertEquals(ReviewStatus.PHYSICIAN_APPROVED, status);
			
			status = wfService.advanceWorkflow(id, "Submit to Central Office SAE Coordinator");
			assertEquals(ReviewStatus.CENTRAL_OFFICE_REVIEW, status);
			
			status = wfService.advanceWorkflow(id, "Approve Report");
			assertEquals(ReviewStatus.SUBMIT_TO_SPONSOR, status);
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertTrue(nextTransitions.isEmpty());
			assertEquals(0, nextTransitions.size());
			
		}
	}
	
	public void testAdvanceWorkflow_ToSubmittedToSponsor(){
		String loginId = "SYSTEM_ADMIN";
		Integer id = null;
		{
			ProcessInstance pInstance  = wfService.createProcessInstance(WorkflowService.WORKFLOW_EXPEDITED_FLOW_DOMESTIC, variables);
			Long l = pInstance.getId();
			id = new Integer(l.intValue());
		}
		interruptSession();
		
		{
			ReviewStatus status = wfService.advanceWorkflow(id, "Submit For Physician Review");
			assertEquals(ReviewStatus.PHYSICIAN_REVIEW, status);
			
			status = wfService.advanceWorkflow(id, "Approve Report");
			assertEquals(ReviewStatus.PHYSICIAN_APPROVED, status);
			
			status = wfService.advanceWorkflow(id, "Submit to Central Office SAE Coordinator");
			assertEquals(ReviewStatus.CENTRAL_OFFICE_REVIEW, status);
			
			status = wfService.advanceWorkflow(id, "Approve Report");
			assertEquals(ReviewStatus.SUBMIT_TO_SPONSOR, status);
			
		}
		interruptSession();
		{
			List<Transition> nextTransitions = wfService.nextTransitions(id, loginId);
			assertNotNull(nextTransitions);
			assertTrue(nextTransitions.isEmpty());
		}
	}
}
