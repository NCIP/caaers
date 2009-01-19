package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.CaaersConfigurationException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.Assignee;
import gov.nih.nci.cabig.caaers.domain.workflow.PersonAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.workflow.PossibleTransitionsResolver;
import gov.nih.nci.cabig.caaers.workflow.callback.CreateTaskJbpmCallback;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jbpm.JbpmContext;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.def.Transition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.workflow.jbpm31.JbpmCallback;
import org.springmodules.workflow.jbpm31.JbpmTemplate;

/**
 * This class has methods, that deals with the JBPM workflow engine.
 * @author Sameer
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class WorkflowServiceImpl implements WorkflowService {
	private JbpmTemplate jbpmTemplate;
	private List<ProcessDefinition> processDefinitions;
	
	
	private CaaersJavaMailSender caaersJavaMailSender;
	private WorkflowConfigDao workflowConfigDao;
	private PossibleTransitionsResolver possibleTransitionsResolver;
	
	protected ProcessDefinition findProcessDefinitionByName(String wfDefName){
		for(ProcessDefinition pd : processDefinitions){
			if(pd.getName().equals(wfDefName)){
				return pd;
			}
		}
		return null;
	}
	
	/**
	 * This method is used to create and persist processInstance
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public ProcessInstance createProcessInstance(String workflowDefinitionName){
		
		//check if workflow is enabled
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		if( wfConfig != null && wfConfig.getEnabled()){
			
			//the process definition must be available
			final ProcessDefinition pDefinition = findProcessDefinitionByName(workflowDefinitionName);
			if(pDefinition == null){
				throw new CaaersSystemException("WF-0010", "Unknown process definition [" + workflowDefinitionName + "]");
			}
			
			
			//instantiate the process, then jump to the first node
			ProcessInstance pInstance = new ProcessInstance(pDefinition);
			Long processId =  jbpmTemplate.saveProcessInstance(pInstance);
			assert processId != null;
			Token token = pInstance.getRootToken();
	        token.signal();
			
	        processId =  jbpmTemplate.saveProcessInstance(pInstance);
						
			return pInstance;	
		}
		return null;
	}
	
	/**
	 * This method, return the list of possible transitions available at a given time on a specific workflow process instance
	 */
	public List<Transition> nextTransitions( Integer workflowId){
		ProcessInstance processInstance = fetchProcessInstance(workflowId.longValue());
		String workflowDefinitionName = processInstance.getProcessDefinition().getName();
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		return possibleTransitionsResolver.fetchNextTransitions(wfConfig, processInstance);
	}
	
	/**
	 * This method will return the list of all possible transitions available at a given time on a specific process instance
	 */
	public List<String> nextTransitionNames(Integer workflowId) {
		List<Transition> transitions = nextTransitions(workflowId);
		List<String> transitionNames = new ArrayList<String>();
		for(Transition transition : transitions){
			transitionNames.add(transition.getName());
		}
		return transitionNames;
	}
	

	/**
	 * @see WorkflowService#advanceWorkflow(Integer, String)
	 */
	/*
	 * Will fetch the workflow process
	 * Get the current node, 
	 *   Find the next nodes, and see if the status mentioned is associated to that task.
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public ReviewStatus advanceWorkflow(Integer workflowId,String leavingTransitionName) {
		
		//fetch the process instance
		ProcessInstance processInstance = fetchProcessInstance(workflowId.longValue());
		Token token = processInstance.getRootToken();
		
		//figure out the transition to take, based upon our crude logic of applicable/not applicable, 
		//the leavingTransitionName, may not be a valid leaving transition in the current workflow node.
		Transition transitionToTake = token.getNode().getDefaultLeavingTransition();
		for(Iterator it = token.getNode().getLeavingTransitions().iterator(); it.hasNext(); ){
			Transition transition = (Transition) it.next();
			if(StringUtils.equals(transition.getName(), leavingTransitionName)){
				transitionToTake = transition;
				break;
			}
		}
		
		//leave through the transition
		token.signal(transitionToTake);
		jbpmTemplate.saveProcessInstance(processInstance);
		
		//now fetch the node where we are at after the transition.
		Node currentNode =  processInstance.getRootToken().getNode();
		String workflowDefinitionName =processInstance.getProcessDefinition().getName();
		
		//fetch the current nodes status from task config
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		if(wfConfig == null){
			throw new CaaersSystemException("WF-0011", "Workflow is not configured for [" + workflowDefinitionName + "]");
		}
		
		TaskConfig taskConfig = wfConfig.findTaskConfig(currentNode.getName());
		assert taskConfig != null;
		
		String reviewStatusName =  taskConfig.getStatusName();
		return ReviewStatus.valueOf(reviewStatusName);
	}
	
	/*
	 * This method is used to fetch the latest processInstance given the processInstance name
	 */
	public ProcessInstance fetchProcessInstance(Long id){
		return jbpmTemplate.findProcessInstance(id);
	}
		
	/*
	 *  This method is used to create a bunch of TaskInstances for the actors involved
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public void createTaskInstances(CreateTaskJbpmCallback createTaskCallback){
		jbpmTemplate.execute(createTaskCallback);
		
		// Send Notifications
		notifiyTaskAssignees(createTaskCallback.getProcessDefinitionName(), createTaskCallback.getCurrentNode().getName(), createTaskCallback.getTaskAssigneesList());
	}
	
	/**
	 * This method will close all the open task instances
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public void closeAllOpenTaskInstances(ExecutionContext executionContext) {
		List<TaskInstance> taskInstances = jbpmTemplate.findTaskInstancesByToken(executionContext.getToken());
		for(TaskInstance tInstance : taskInstances){
			if(tInstance != null && tInstance.isOpen()){
				tInstance.end();
			}
		}
	}
	
	/*
	 *  This method returns the list of active tasks for a given userId
	 */
	public List<TaskInstance> fetchTaskInstances(String actorId){
		return (List<TaskInstance>)jbpmTemplate.findPooledTaskInstances(actorId);
	}
	
	/**
	 * @see WorkflowService#findTaskAssignees(String)
	 */
	public List<User> findTaskAssignees(String workflowDefinitionName, String taskNodeName) {
		List<User> assignees = new ArrayList<User>();
		TaskConfig taskConfig = findTaskConfig(workflowDefinitionName, taskNodeName);
		for(Assignee assignee : taskConfig.getAssignees()){
			
			if(assignee.isRole()){
				//TODO : We need to figure out the API for this
			}else if(assignee.isUser()) {
				User user = ((PersonAssignee) assignee).getUser();
				assignees.add(user);
			}
		}
		
		return assignees;
		
		
	}
	/**
	 * @see WorkflowService#findTaskConfig(String, String)
	 */
	public TaskConfig findTaskConfig(String workflowDefinitionName,	String taskNodeName) {
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		if(wfConfig == null){
			throw new CaaersSystemException("WF-0011", "Workflow is not configured for [" + workflowDefinitionName + "]");
		}
		
		return wfConfig.findTaskConfig(taskNodeName);
	}
	
	/**
	 * Will notifiy the assignees about the creation of a task. 
	 */
	public void notifiyTaskAssignees(String workflowDefinitionName,	String taskNodeName, List<User> recipients) {
		TaskConfig taskConfig = findTaskConfig(workflowDefinitionName, taskNodeName);
		String message = taskConfig.getMessage();
		String subject = "Task : " + taskNodeName;
		String[] to = new String[recipients.size()];
		int i = 0;
		for(User user : recipients){
			to[i] = user.getEmailAddress();
			i++;
		}
		caaersJavaMailSender.sendMail(to, subject, message, new String[0]);
	}
	

	
	public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender){
		this.caaersJavaMailSender = caaersJavaMailSender;
	}

	
	public void setPossibleTransitionsResolver(PossibleTransitionsResolver possibleTransitionsResolver){
		this.possibleTransitionsResolver = possibleTransitionsResolver;
	}
	
	public void setWorkflowConfigDao(WorkflowConfigDao workflowConfigDao){
		this.workflowConfigDao = workflowConfigDao;
	}
	
	
	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}

	public void setProcessDefinitions(List<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}
	
}