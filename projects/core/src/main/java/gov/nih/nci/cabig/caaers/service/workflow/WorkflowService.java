package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.jbpm.JbpmConfiguration;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * This interface consists of service methods that facilitates Routing & Review use-case.
 * There are method for creating ProcessInstances, creating TaskInstances, fetching them 
 * and closing taskInstances.
 *
 * @author Sameer Sawant
 */
public interface WorkflowService{
	
	/*
	 * This method is used to create and persist processInstance
	 */
	public Long createProcessInstance(String definitionType);
		
	
	/*
	 * This method is used to fetch the processInstance given the processInstance identifier
	 */
	public ProcessInstance fetchProcessInstance(Long id);
		
	/*
	 *  This method is used to create a bunch of TaskInstances for the actors involved
	 */
	public void createTaskInstances(ExecutionContext context, List<User> taskAssigneesList);
	
	/*
	 *  This method returns the list of active tasks for a given userId
	 */
	public List<TaskInstance> fetchTaskInstances(String actorId);
	
	public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender);
	
	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration);
	
	public void setRoutineFlowProcessDefinition(ProcessDefinition routineFlowProcessDefinition);
	
	public void setSessionFactory(SessionFactory sessionFactory);
	
	public List<String> nextTransitions(String workflowDefinitionName, Long workflowId);
	
	/**
	 * This method is used to identify the assignees configured on the task. 
	 * @param taskNodeName
	 * @return
	 */
	public List<User> findTaskAssignees(String workflowDefinitionName, String taskNodeName);
	
	/**
	 * This method returns the task configuration identified by the taskNodeName, 
	 * associated to the workflow identified ty workflowDefinitionName
	 * @param workflowDefinitionName
	 * @param taskNodeName
	 * @return
	 */
	public TaskConfig findTaskConfig(String workflowDefinitionName, String taskNodeName);
	
}