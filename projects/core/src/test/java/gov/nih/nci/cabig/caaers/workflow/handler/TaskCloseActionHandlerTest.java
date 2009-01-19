package gov.nih.nci.cabig.caaers.workflow.handler;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowServiceImpl;

import org.jbpm.graph.exe.ExecutionContext;
/**
 * 
 * @author Biju Joseph
 *
 */
public class TaskCloseActionHandlerTest extends AbstractTestCase {
	private WorkflowServiceImpl wfService;
	TaskCloseActionHandler handler;
	ExecutionContext context;
	
	protected void setUp() throws Exception {
		super.setUp();
		context = registerMockFor(ExecutionContext.class);
		handler = new TaskCloseActionHandler();
		wfService = registerMockFor(WorkflowServiceImpl.class);
		
		handler.setWorkflowService(wfService);
	}

	public void testExecuteExecutionContext() {
		wfService.closeAllOpenTaskInstances(context);
		replayMocks();
		try {
			handler.execute(context);
		} catch (Exception e) {
			e.printStackTrace();
			fail("closing of task, should not throw exception");
		}
		verifyMocks();
	}

}
