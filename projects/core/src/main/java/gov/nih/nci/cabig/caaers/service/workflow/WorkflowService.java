package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.workflow.callback.CreateTaskJbpmCallback;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.jbpm.JbpmConfiguration;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.transaction.annotation.Transactional;

/**
 * This interface consists of service methods that facilitates Routing & Review use-case.
 * There are method for creating ProcessInstances, creating TaskInstances, fetching them 
 * and closing taskInstances.
 *
 * @author Sameer Sawant
 * @author Biju Joseph
 */
@Transactional(readOnly = false)
public interface WorkflowService{
	
	
	String WORKFLOW_REPORTING = "reporting";
	String WORKFLOW_EXPEDITED_FLOW_INTERNATIONAL = "";
	String WORKFLOW_EXPEDITED_FLOW_DOMESTIC = "expedited_domestic";
	String WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER = "reportingperiod_coordinating_center";
	String WORKFLOW_EVALUATION_PERIOD_MAIN_MEMBER = "";
	
	//variable names
	String VAR_STUDY_ID = "var_study_id";
	String VAR_REPORTING_PERIOD_ID = "var_rpperiod_id";
	String VAR_EXPEDITED_REPORT_ID = "var_expedited_report_id";
	String VAR_REPORT_ID = "var_report_id";
	String VAR_WF_TYPE = "var_wf_type";
	
	
	/**
	 * This method will create an instance of a workflow definition.
	 * 
	 * @param workflowDefinitionName
	 * @return
	 */
	public ProcessInstance createProcessInstance(String workflowDefinitionName);
	
	/**
	 * This method will save a process instance. 
	 * @param processInstance
	 */
	public Long saveProcessInstance(ProcessInstance processInstance);
	
	/*
	 * This method is used to fetch the processInstance given the processInstance identifier
	 */
	public ProcessInstance fetchProcessInstance(Long id);
		
	/*
	 *  This method is used to create a bunch of TaskInstances for the actors involved
	 */
	public void createTaskInstances(CreateTaskJbpmCallback createTaskCallback);
	
	/*
	 *  This method returns the list of active tasks for a given userId
	 */
	public List<TaskInstance> fetchTaskInstances(String actorId);
	
	/**
	 * This method will close all the open tasks, within an execution context.
	 * @param executionContext
	 */
	public void closeAllOpenTaskInstances(ExecutionContext executionContext);
	
	

	/**
	 * This will advance the workflow, to the next step (identified by the transition), and will return the statuses associated to 
	 * the current node.
	 * @param workflowId
	 * @param transition
	 * @return
	 */
	public ReviewStatus advanceWorkflow(Integer workflowId, String transition);
	
	/**
	 * Lists all the available transitions of the workflow. 
	 * 
	 * @param workflowId - A process instance ID
	 * @return
	 */
	public List<Transition> nextTransitions(Integer workflowId);
	
	/**
	 * List all the available transitions of the workflow
	 * @param workflowId
	 * @return
	 */
	public List<String> nextTransitionNames(Integer workflowId);
	
	/**
	 * This method is used to identify the assignees configured on the task. 
	 * @param taskNodeName
	 * @return
	 */
	public List<User> findTaskAssignees(ProcessInstance pInstance, String taskNodeName);
	
	/**
	 * This method returns the task configuration identified by the taskNodeName, 
	 * associated to the workflow identified ty workflowDefinitionName
	 * @param workflowDefinitionName
	 * @param taskNodeName
	 * @return
	 */
	public TaskConfig findTaskConfig(String workflowDefinitionName, String taskNodeName);
	
	/**
	 * This method is used to notify the assignees about the creation of a task
	 * @param workflowDefinitionName
	 * @param taskNodeName
	 */
	public void notifiyTaskAssignees(String workflowDefinitionName, String taskNodeName,List<User> recipients);


}