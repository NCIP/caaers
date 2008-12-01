package gov.nih.nci.cabig.caaers.workflow.handler;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;

/**
 * The purpouse of this class is to translate identify the users associated to the task. 
 * 
 * @author biju
 *
 */
public class UserAssignmentHandler implements AssignmentHandler{
	
	/**
	 * This method should identify all the users, and add them as pooled users.
	 *   - Should find the users associated to roles,
	 *   - Should consider localSiteFirst
	 *   - Should consider gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig.defaultAssignee, 
	 *     when role is not deriveable.
	 *  
	 */
	public void assign(Assignable assignable, ExecutionContext context) throws Exception {
		
	}
}
