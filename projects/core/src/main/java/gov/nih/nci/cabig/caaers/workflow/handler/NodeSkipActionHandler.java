package gov.nih.nci.cabig.caaers.workflow.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.NotificationRecipient;
import gov.nih.nci.cabig.caaers.domain.workflow.RoleRecipient;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.workflow.WorkflowService;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springframework.beans.factory.annotation.Required;
/**
 * This action handler is responsible to determine whether this node should act as an auto-node or not. 
 *  - Should check whether, the task in context is marked ("applicable=yes"), if no should force leaving this node.
 * @author Biju Joseph
 */
public class NodeSkipActionHandler extends Action{
	
	private WorkflowConfigDao workflowConfigDao;
	private WorkflowConfig wConfig;
	private JbpmConfiguration jbpmConfiguration;
	private UserDao userDao;
	private WorkflowService workflowService;
	
	
	public void execute(ExecutionContext context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Ganapati Bappa Morya !!!!");
		String workflowDefinitionName = (String)context.getContextInstance().getVariable("workflowDefinitionName");
		context.getContextInstance().deleteVariable("workflowDefinitionName");
		setWorkflowConfigDao((WorkflowConfigDao)context.getVariable("workflowConfigDao"));
		context.getContextInstance().deleteVariable("workflowConfigDao");
		setWorkflowService((WorkflowService)context.getVariable("workflowService"));
		context.getContextInstance().deleteVariable("workflowService");
		setUserDao((UserDao)context.getVariable("userDao"));
		context.getContextInstance().deleteVariable("userDao");
		//String workflowConfigId = (String)context.getContextInstance().getVariable("workflowConfigId");
		//if(workflowConfigId != null && !workflowConfigId.equals(""))
		//	wConfig = (WorkflowConfig) workflowConfigDao.getById(Integer.parseInt(workflowConfigId));
		List<WorkflowConfig> wConfigList = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		if(wConfigList != null && wConfigList.size() > 0)
			wConfig = wConfigList.get(0);
		
		ProcessInstance pInstance = context.getProcessInstance();
		Node rootNode = pInstance.getRootToken() == null ? null : pInstance.getRootToken().getNode();

		while(!wConfig.isTaskActive(rootNode.getName())){
			rootNode.leave(context);
		}
		
		ArrayList<String> taskAssigneeList = fetchTaskAssignees(wConfig, rootNode.getName());
		
		workflowService.createTaskInstances(context, taskAssigneeList);
		// Make a call to workflowService.createTasks ()
	}
	
	public ArrayList<String> fetchTaskAssignees(WorkflowConfig wConfig, String taskDefinitionName){
		ArrayList<String> taskAssignees = new ArrayList<String>();
		List<NotificationRecipient> notificationRecipients = new ArrayList<NotificationRecipient>();
		List<RoleRecipient> roleRecipients = new ArrayList<RoleRecipient>(); 
		
		// Get the users using the roleRecipients.
		notificationRecipients = wConfig.getNotificationRecipientsForTask(taskDefinitionName);
		if(notificationRecipients != null && notificationRecipients.size() > 0){
			User user = null;
			for(NotificationRecipient nr: notificationRecipients){
				user = userDao.getByEmailAddress(nr.getName());
				taskAssignees.add(user.getLoginId());
			}
		}
		
		// TODO..
		// Add to taskAssignees based on the roles. (roleRecipients)
		return taskAssignees;
	}
	
	@Required
	public void setWorkflowConfigDao(WorkflowConfigDao workflowConfigDao) {
		this.workflowConfigDao = workflowConfigDao;
	}
	
	public WorkflowConfigDao getWorkflowConfigDao(){
		return workflowConfigDao;
	}
	
	@Required
	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration){
		this.jbpmConfiguration = jbpmConfiguration;
	}
	
	@Required 
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	@Required
	public void setWorkflowService(WorkflowService workflowService){
		this.workflowService = workflowService;
	}
	
}
