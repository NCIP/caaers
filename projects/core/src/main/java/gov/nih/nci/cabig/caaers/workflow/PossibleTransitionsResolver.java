package gov.nih.nci.cabig.caaers.workflow;

import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ProcessInstance;

/**
 * The purpose of this class is to identify the next possible transitions from the current
 * state of the workflow. 
 * 
 * @author Sameer Sawant
 *
 */
public class PossibleTransitionsResolver{
	
	/**
	 * This method determines the next possible transitions possible in the workflow from the current node. The states available for the 
	 * workflow and the user-role restrict the next possible states. The parameters needed for this method are the workflowConfig (which is
	 * used to determine the available states and the user-roles assigned to these states) and the parameterInstance(which is used to deter-
	 * mine the current active state). Following is the algorithm used by this method :
	 * 			a. Determine the current NODE from the processInstance.
	 * 			b. Get all the possible transitions from this NODE.
	 *          c. For all non-default transitions check if the target NODE is available.
	 *          	c.1 If the target NODE is available, add the corresponding transition to the list of possible transitions (result)
	 *          	c.2 If the target NODE is unavailable, ignore the corresponding transition.
	 *          d. Follow the process-definition graph taking the default-transitions on each step, till you hit an available NODE.
	 *          e. Add the transition that enters into this active NODE (found in step d) to the final list of possible transitions (result)
	 *          f. Return.
	 *
	 * @param wConfig (WorkflowConfig)
	 * @param pInstance (ProcessInstance)
	 * @return List<String>, A list of all the possible transitions from the current node based on the applicable states and user-role.
	 */
	public  List<Transition> fetchNextTransitions(WorkflowConfig wConfig, ProcessInstance pInstance){
		List<Transition> nextTransitions = new ArrayList<Transition>();
		
		// Get a handle to the currentNode of the processInstance.
		// Step(a) in the algorithm above.
		Node rootNode = pInstance.getRootToken() == null ? null : pInstance.getRootToken().getNode();
		
		// Get a list of all leaving transitions (non-default) and the default-transition from the currentNode.
		if(rootNode != null){
			// Step(b) in the algorithm above.
			Transition defaultTransition = rootNode.getDefaultLeavingTransition();
			List<Transition> leavingTransitions = rootNode.getLeavingTransitions();
			
			// Remove the defaultTransition from the list of leavingTransitions and create a list otherLeavingTransitions.
			List<Transition> otherLeavingTransitions = new ArrayList<Transition>();
			for(Transition t: leavingTransitions){
				if(!defaultTransition.equals(t))
					otherLeavingTransitions.add(t);
			}
			
			// Step(c) in the algorithm above.
			for(Transition t: otherLeavingTransitions){
				if(wConfig.isTaskActive(t.getTo().getName()))
					nextTransitions.add(t);
			}
			
			// Step(d) in the algorithm above.
			Node currentNode = rootNode;
			Node nextNode = defaultTransition.getTo();
			while(!wConfig.isTaskActive(nextNode.getName()) || currentNode.getDefaultLeavingTransition() == null){
				currentNode = nextNode;
				if(currentNode.getDefaultLeavingTransition() != null)
					nextNode = currentNode.getDefaultLeavingTransition().getTo();
			}
			
			// Step(e) in the algorithm above.
			nextTransitions.add(currentNode.getDefaultLeavingTransition());
			
		}
		
		return nextTransitions;
	}
}