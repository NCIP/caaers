package gov.nih.nci.cabig.caaers.workflow.callback;

import gov.nih.nci.cabig.caaers.domain.User;

import java.util.List;

import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springmodules.workflow.jbpm31.JbpmCallback;

/**
 * This class will create task instances, this is written in this way to do the easymock junit testing.
 * @author Biju Joseph
 *
 */
public class CreateTaskJbpmCallback implements JbpmCallback {
	private List<User> taskAssigneesList;
	private ExecutionContext context;
	
	private String processDefinitionName;
	private Node currentNode;
	
	public CreateTaskJbpmCallback(ExecutionContext context, List<User> taskAssigneesList){
		this.taskAssigneesList = taskAssigneesList;
		this.context = context;
		this.currentNode = context.getNode();
		this.processDefinitionName = context.getProcessDefinition().getName();
	}
	
	public Object doInJbpm(JbpmContext jbpmContext) throws JbpmException {
		
		TaskMgmtInstance tmi = context.getTaskMgmtInstance();
		TaskInstance tInstance = tmi.createTaskInstance(null, context);
		tInstance.setName(currentNode.getName());

		int userCount = taskAssigneesList.size();
		String[] pooleActorIds = new String[userCount];
		int i = 0;
		for(User user : taskAssigneesList){
			pooleActorIds[i] = user.getLoginId();
			i++;
		}
		tInstance.setPooledActors(pooleActorIds);

		return tInstance;
	}
	
	
	public Node getCurrentNode() {
		return currentNode;
	}
	
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public List<User> getTaskAssigneesList() {
		return taskAssigneesList;
	}
	
}