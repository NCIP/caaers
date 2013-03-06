/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.*;

 
/**
 * The purpose of this class is to capture the customizations applied on a workflow template, in the current
 * instance. 
 * @author Biju Joseph
 * @author Sameer Sawant
 */
@Entity
@Table(name = "workflow_configuration")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_workflow_configuration_id")})
public class WorkflowConfig extends AbstractMutableDomainObject{
	
	/** The name. */
	private String name; // a customized name for this workflow
	
	/** The workflow definition name. */
	private String workflowDefinitionName; // the workflow definition name (used in getting the workflow definition file)
	
	/** The task configs. */
	private List<TaskConfig> taskConfigs; //list of the tasks that are applicable in the workflow (configured by the admin)
	
	/** The default assignee. */
	private String defaultAssignee; //loginId of the user, who will be the default assignee,
							 		//when the Role (assignee) cannot be derived.
	/** The enabled. */
		 							private Boolean enabled;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the workflow definition name.
	 *
	 * @return the workflow definition name
	 */
	public String getWorkflowDefinitionName() {
		return workflowDefinitionName;
	}
	
	/**
	 * Sets the workflow definition name.
	 *
	 * @param workflowDefinitionName the new workflow definition name
	 */
	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		this.workflowDefinitionName = workflowDefinitionName;
	}
	
	/**
	 * Gets the default assignee.
	 *
	 * @return the default assignee
	 */
	public String getDefaultAssignee() {
		return defaultAssignee;
	}
	
	/**
	 * Sets the default assignee.
	 *
	 * @param defaultAssignee the new default assignee
	 */
	public void setDefaultAssignee(String defaultAssignee) {
		this.defaultAssignee = defaultAssignee;
	}
	
	
	/**
	 * Gets the task configs.
	 *
	 * @return the task configs
	 */
	@OneToMany
    @JoinColumn(name = "workflow_config_id", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<TaskConfig> getTaskConfigs() {
		return taskConfigs;
	}
	
	/**
	 * Sets the task configs.
	 *
	 * @param taskConfigs the new task configs
	 */
	public void setTaskConfigs(List<TaskConfig> taskConfigs) {
		this.taskConfigs = taskConfigs;
	}
	
	/**
	 * Adds the task configs.
	 *
	 * @param taskConfig the task config
	 */
	public void addTaskConfigs(TaskConfig taskConfig){
		if(taskConfigs == null) this.taskConfigs = new ArrayList<TaskConfig>();
		taskConfigs.add(taskConfig);
	}
	
	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	
	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	/**
	 * Checks if is task active.
	 *
	 * @param name the name
	 * @return true, if is task active
	 */
	@Transient
	public boolean isTaskActive(String name){
		for(TaskConfig tc: getTaskConfigs()){
			if(tc.getTaskName().equals(name) && tc.getApplicable())
				return true;
		}
		return false;
	}
	
	/**
	 * This method returns the task configuration, identified by the <b>taskNodeName</b>.
	 *
	 * @param taskNodeName the task node name
	 * @return the task config
	 */
	public TaskConfig findTaskConfig(String taskNodeName){
		for(TaskConfig taskConfig : getTaskConfigs()){
			if(taskConfig.getTaskName().equals(taskNodeName)) return taskConfig;
		}
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultAssignee == null) ? 0 : defaultAssignee.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((workflowDefinitionName == null) ? 0
						: workflowDefinitionName.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof WorkflowConfig))
			return false;
		WorkflowConfig other = (WorkflowConfig) obj;
		if (defaultAssignee == null) {
			if (other.defaultAssignee != null)
				return false;
		} else if (!defaultAssignee.equals(other.defaultAssignee))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (workflowDefinitionName == null) {
			if (other.workflowDefinitionName != null)
				return false;
		} else if (!workflowDefinitionName.equals(other.workflowDefinitionName))
			return false;
		return true;
	}
	
	
}
