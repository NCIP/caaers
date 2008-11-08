package gov.nih.nci.cabig.caaers.domain.workflow;

import java.util.List;
/**
 * This class will capture the customizations applied for each task.
 * Eg: - Applicablblity , Status to which this node corresponds to, list of assignees and notification recipients
 * @author Biju Joseph
 *
 */
public class TaskConfig {
	private List<Assignee> assignees;
	private List<NotificationRecipient> notificationRecipients;
	
	private String statusName;  //The status enum name (Or the state name) of
								//the domain object when workflow reaches this task
	
	private boolean applicable; 
	
	public boolean localSiteFirst; //if true, in all role user identification local user will be preffered.
}
