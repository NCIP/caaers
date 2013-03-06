/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

 
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
	
	/** The transitions. */
	private List<TransitionConfig> transitions;
	
	/** The assignees. */
	private List<Assignee> assignees; // List of NotificationRecipient (USERIDs) /ROLES who will receive notifications on the task creation and will be assigned
								// the tasks too.
	
	/** The task name. */
								private String taskName;  	// The name of the task, that is being 
	
	/** The applicable. */
	private Boolean applicable; //Flag to enable/disable the task.
	
	/** The location. */
	private Location location; //Determine the site from where persons of the respecive roles will be assigned
								// the tasks and will be receiving the notifications.
	
	/** The status name. */
								private String statusName; // The ReviewStatus, to which this task is associated to. 
	
	/** The message. */
	private String message; //notification body message
	
	/** The content type. */
	private String contentType = "text/html"; //In this release, lets fix the content type as text/html
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	@Type(type = "location")
    @Column(name = "location")
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Gets the applicable.
	 *
	 * @return the applicable
	 */
	public Boolean getApplicable() {
		return applicable;
	}
	
	/**
	 * Sets the applicable.
	 *
	 * @param applicable the new applicable
	 */
	public void setApplicable(Boolean applicable) {
		this.applicable = applicable;
	}
	
	/**
	 * Gets the task name.
	 *
	 * @return the task name
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * Sets the task name.
	 *
	 * @param taskName the new task name
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	/**
	 * Gets the assignees.
	 *
	 * @return the assignees
	 */
	@OneToMany (orphanRemoval = true)
    @JoinColumn(name = "task_config_id", nullable = false)
    @Cascade(value = { CascadeType.ALL  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<Assignee> getAssignees(){
		return assignees;
	}
	
	/**
	 * Sets the assignees.
	 *
	 * @param assignees the new assignees
	 */
	public void setAssignees(List<Assignee> assignees) {
		this.assignees = assignees;
	}
	
	/**
	 * Adds the assignee.
	 *
	 * @param assignee the assignee
	 */
	public void addAssignee(Assignee assignee){
		if(assignees == null) assignees = new ArrayList<Assignee>();
		assignees.add(assignee);
	}
	
	/**
	 * Gets the transitions.
	 *
	 * @return the transitions
	 */
	@OneToMany (orphanRemoval = true)
    @JoinColumn(name = "task_config_id", nullable = false)
    @Cascade(value = { CascadeType.ALL  })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<TransitionConfig> getTransitions() {
		return transitions;
	}
	
	/**
	 * Sets the transitions.
	 *
	 * @param transitions the new transitions
	 */
	public void setTransitions(List<TransitionConfig> transitions) {
		this.transitions = transitions;
	}
	
	/**
	 * Adds the transition.
	 *
	 * @param transitionConfig the transition config
	 */
	public void addTransition(TransitionConfig transitionConfig){
		if(transitions == null) transitions = new ArrayList<TransitionConfig>();
		transitions.add(transitionConfig);
	}
	
	/**
	 * Gets the status name.
	 *
	 * @return the status name
	 */
	public String getStatusName() {
		return statusName;
	}
	
	/**
	 * Sets the status name.
	 *
	 * @param statusName the new status name
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskConfig [" + taskName + ", " + statusName + " , " + applicable + " ]";
	}
	
	/**
	 * Find transition config.
	 *
	 * @param transitionName the transition name
	 * @return the transition config
	 */
	public TransitionConfig findTransitionConfig(String transitionName){
		if(transitions == null || transitions.isEmpty()) return null;
		for(TransitionConfig transitionConfig : transitions) {
			if(StringUtils.equals(transitionName, transitionConfig.getTransitionName())){
				return transitionConfig;
			}
		}
		return null;
	}
}
