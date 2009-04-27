package gov.nih.nci.cabig.caaers.workflow.handler;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;

import org.jbpm.graph.def.Action;
import org.jbpm.graph.exe.ExecutionContext;

@SuppressWarnings("serial")
public class TaskCloseActionHandler extends Action{
	
	WorkflowService workflowService;
	@Override
 	public void execute(ExecutionContext executionContext) throws Exception {
		//will close open tasks that are associated to the current node
		workflowService.closeAllOpenTaskInstances(executionContext);
 	}
	
	
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	
}
