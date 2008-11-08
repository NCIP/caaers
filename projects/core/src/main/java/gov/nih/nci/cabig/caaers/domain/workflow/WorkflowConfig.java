package gov.nih.nci.cabig.caaers.domain.workflow;

import java.util.List;

/**
 * The purpose of this class is to capture the customizations applied on a workflow template, in the current
 * instance. 
 * @author biju
 *
 */
public class WorkflowConfig {
	private String workflowDefinitionName;
	private List<TaskConfig> taskConfigs;
	
	private String defaultAssignee; //loginId of the user, who will be the default assignee,
							 		//when the Role (assignee) cannot be derived.
	
}
