package gov.nih.nci.cabig.caaers.workflow.handler;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowServiceImpl;
import gov.nih.nci.cabig.caaers.workflow.callback.CreateTaskJbpmCallback;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * This action handler is responsible to determine whether this node should act as an auto-node or not. 
 *  - Should check whether, the task in context is marked ("applicable=yes"), if no should force leaving this node.
 * @author Biju Joseph
 * @author Sameer
 */

@SuppressWarnings("serial")
@Transactional
public class NodeSkipActionHandler extends Action{
	
	private WorkflowService workflowService;
	
	/**
	 * Checks whether this node is applicable (configured in admin page by admin)
	 * If applicable, 
	 *   - Identify the assignees of the task and create tasks. 
	 * If not applicable,
	 *   - The flow will leave the node (to the default transition)
	 */
	public void execute(ExecutionContext context) throws Exception {
		
		String workflowDefinitionName = context.getProcessDefinition().getName();
		ProcessInstance pInstance = context.getProcessInstance();
		Token rootToken = pInstance.getRootToken();
		
		Node currentNode = rootToken == null ? null : rootToken.getNode();
		String taskNodeName = currentNode.getName();
		
		TaskConfig taskConfig = workflowService.findTaskConfig(workflowDefinitionName, taskNodeName);
		if(taskConfig == null || BooleanUtils.isNotTrue(taskConfig.getApplicable())){
			//not applicable
			currentNode.leave(context);
		}else {
			//applicable, so create tasks
			List<User> assignees = workflowService.findTaskAssignees(pInstance, taskNodeName);
			workflowService.createTaskInstances(new CreateTaskJbpmCallback(context, assignees));	
		}
		
	}
	

	
	@Required
	public void setWorkflowService(WorkflowService workflowService){
		this.workflowService = workflowService;
	}
	

	
}
