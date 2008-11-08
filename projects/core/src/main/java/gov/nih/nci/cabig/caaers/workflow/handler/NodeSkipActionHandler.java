package gov.nih.nci.cabig.caaers.workflow.handler;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
/**
 * This action handler is responsible to determine whether this node should act as an auto-node or not. 
 *  - Should check whether, the task in context is marked ("applicable=yes"), if no should force leaving this node.
 * @author Biju Joseph
 */
public class NodeSkipActionHandler implements ActionHandler{
	
	public void execute(ExecutionContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
