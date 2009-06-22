package gov.nih.nci.cabig.caaers.domain.workflow;

import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The purpose of this class is to capture the customizations applied on a workflow template, in the current
 * instance. 
 * @author biju
 * @author Sameer Sawant
 */
@Entity
@Table(name = "workflow_configuration")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_workflow_configuration_id")})
public class WorkflowConfig extends AbstractMutableDomainObject{
	
	private String name; // a customized name for this workflow
	private String workflowDefinitionName; // the workflow definition name (used in getting the workflow definition file)
	private List<TaskConfig> taskConfigs; //list of the tasks that are applicable in the workflow (configured by the admin)
	private String defaultAssignee; //loginId of the user, who will be the default assignee,
							 		//when the Role (assignee) cannot be derived.
	private Boolean enabled;
	
	private StudySite studySite;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWorkflowDefinitionName() {
		return workflowDefinitionName;
	}
	
	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		this.workflowDefinitionName = workflowDefinitionName;
	}
	
	public String getDefaultAssignee() {
		return defaultAssignee;
	}
	
	public void setDefaultAssignee(String defaultAssignee) {
		this.defaultAssignee = defaultAssignee;
	}
	
	
	@OneToMany
    @JoinColumn(name = "workflow_config_id", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<TaskConfig> getTaskConfigs() {
		return taskConfigs;
	}
	
	public void setTaskConfigs(List<TaskConfig> taskConfigs) {
		this.taskConfigs = taskConfigs;
	}
	
	public void addTaskConfigs(TaskConfig taskConfig){
		if(taskConfigs == null) this.taskConfigs = new ArrayList<TaskConfig>();
		taskConfigs.add(taskConfig);
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

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
	 * @param taskNodeName
	 * @return
	 */
	public TaskConfig findTaskConfig(String taskNodeName){
		for(TaskConfig taskConfig : getTaskConfigs()){
			if(taskConfig.getTaskName().equals(taskNodeName)) return taskConfig;
		}
		return null;
	}
}
