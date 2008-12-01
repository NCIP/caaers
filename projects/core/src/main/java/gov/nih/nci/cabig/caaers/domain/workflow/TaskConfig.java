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
import org.hibernate.annotations.IndexColumn;
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
	private List<NotificationRecipient> notificationRecipients; // List of NotificationRecipient (USERIDs) 
								// who will receive notifications on the task creation and will be assigned
								// the tasks too.
	
	private List<RoleRecipient> roleRecipients; // List of RoleRecipient (userGroupTypes)
								// who will be receiving notifications on the task creation and will be
								// assigned the tasks too.
	
	private String statusName;  //The status enum name (Or the state name) of
								//the domain object when workflow reaches this task
	
	private Boolean applicable; //Flag to enable/disable the task.
	
	public Location location; //Determine the site from where persons of the respecive roles will be assigned
								// the tasks and will be receiving the notifications.
	
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
	
	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	@OneToMany
    @JoinColumn(name = "task_config_id", nullable = false)
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<NotificationRecipient> getNotificationRecipients() {
		if(this.notificationRecipients == null) this.notificationRecipients = new ArrayList<NotificationRecipient>();
		return notificationRecipients;
	}
	
	public void setNotificationRecipients(
			List<NotificationRecipient> notificationRecipients) {
		this.notificationRecipients = notificationRecipients;
	}
	
	public void addNotificationRecipients(NotificationRecipient notificationRecipient){
		getNotificationRecipients().add(notificationRecipient);
	}

	@OneToMany
    @JoinColumn(name = "task_config_id", nullable = false)
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<RoleRecipient> getRoleRecipients() {
		if(this.roleRecipients == null) this.roleRecipients = new ArrayList<RoleRecipient>();
		return roleRecipients;
	}
	
	public void setRoleRecipients(List<RoleRecipient> roleRecipients) {
		this.roleRecipients = roleRecipients;
	}

	public void addRoleRecipient(RoleRecipient roleRecipient){
		getRoleRecipients().add(roleRecipient);
	}
}