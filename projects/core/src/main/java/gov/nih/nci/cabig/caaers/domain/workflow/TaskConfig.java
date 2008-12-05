package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.Location;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
/**
 * This class will capture the customizations applied for each task.
 * Eg: - Applicablblity , Status to which this node corresponds to, list of assignees and notification recipients
 * @author Biju Joseph
 * @author Sameer Sawant
 *
 */
@Entity
@Table(name = "task_configuration")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_task_configuration_id")})
public class TaskConfig extends AbstractMutableDomainObject{
	
	private List<Assignee> assignees; // List of NotificationRecipient (USERIDs) /ROLES who will receive notifications on the task creation and will be assigned
								// the tasks too.
	
	private String taskName;  	// The name of the task, that is being 
	private Boolean applicable; //Flag to enable/disable the task.
	
	private Location location; //Determine the site from where persons of the respecive roles will be assigned
								// the tasks and will be receiving the notifications.
	
	private String statusName; // The ReviewStatus, to which this task is associated to. 
	
	private String message; //notification body message
	
	private String contentType = "text/html"; //In this release, lets fix the content type as text/html
	
	@Type(type = "location")
    @Column(name = "location")
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public Boolean getApplicable() {
		return applicable;
	}
	
	public void setApplicable(Boolean applicable) {
		this.applicable = applicable;
	}
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@OneToMany
    @JoinColumn(name = "task_config_id", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<Assignee> getAssignees(){
		return assignees;
	}
	
	public void setAssignees(List<Assignee> assignees) {
		this.assignees = assignees;
	}
	
	public void addAssignee(Assignee assignee){
		if(assignees == null) assignees = new ArrayList<Assignee>();
		assignees.add(assignee);
	}
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}