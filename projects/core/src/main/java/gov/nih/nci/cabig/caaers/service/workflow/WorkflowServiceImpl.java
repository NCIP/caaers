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
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.workflow.jbpm31.JbpmCallback;
import org.springmodules.workflow.jbpm31.JbpmTemplate;

/**
 * This class has methods, that deals with the JBPM workflow engine.
 * @author Sameer
 * @author Biju Joseph
 */

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
			
			Token token = pInstance.getRootToken();
	        token.signal();
			
	        Long processId =  jbpmTemplate.saveProcessInstance(pInstance);
			assert processId != null;
			
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
	 * This method lists the {@link ReviewStatus} to which the workflow can transition to. 
	 */
	public List<ReviewStatus> nextStatuses(Integer workflowId) {
		ProcessInstance processInstance = fetchProcessInstance(workflowId.longValue());
		String workflowDefinitionName = processInstance.getProcessDefinition().getName();
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		List<Transition> transitions = possibleTransitionsResolver.fetchNextTransitions(wfConfig, processInstance);
		
		List<ReviewStatus> statuses = new ArrayList<ReviewStatus>();
		for(Transition transition : transitions){
			TaskConfig tConfig = wfConfig.findTaskConfig(transition.getTo().getName());
			statuses.add(ReviewStatus.valueOf(tConfig.getStatusName()));
		}
		
		return statuses;
	}
	
	/**
	 * @see WorkflowService#advanceWorkflow(Integer, ReviewStatus)
	 */
	/*
	 * Will fetch the workflow process
	 * Get the current node, 
	 *   Find the next nodes, and see if the status mentioned is associated to that task.
	 */
	public List<ReviewStatus> advanceWorkflow(Integer workflowId,ReviewStatus toStatus) {
		
		ProcessInstance processInstance = fetchProcessInstance(workflowId.longValue());
		String workflowDefName = processInstance.getProcessDefinition().getName();
		
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefName);
		
		Token token = processInstance.getRootToken();
		Node rootNode = token.getNode();
		
		//find all leaving nodes
		Transition defaultTransition = rootNode.getDefaultLeavingTransition();
		List<Transition> leavingTransitions = rootNode.getLeavingTransitions();
		
		//combine the default and the leaving transitions.
		List<Transition> allLeavingTransitions = new ArrayList<Transition>();
		allLeavingTransitions.add(defaultTransition);
		allLeavingTransitions.addAll(leavingTransitions);
		
		Transition transitionToTake = defaultTransition; //make it the default transition
		for(Transition transition : allLeavingTransitions){
			Node targetNode = transition.getTo();
			TaskConfig tConfig = wfConfig.findTaskConfig(targetNode.getName());
			if(tConfig.getStatusName().equals(toStatus.getName())){
				transitionToTake = transition;
				break;
			}
		}
		
		//proceed through the transition
		token.signal(transitionToTake);
		
		//save the workflow instance
		Long processId =  jbpmTemplate.saveProcessInstance(processInstance);
		
		List<ReviewStatus> nextStatuses =  nextStatuses(processId.intValue());
		
		//prepend, the current status, to the list of statuses
		LinkedHashSet<ReviewStatus> statuses = new LinkedHashSet<ReviewStatus>();
		statuses.add(toStatus);
		statuses.addAll(nextStatuses);
		return new ArrayList<ReviewStatus>(statuses);
		
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
	public void createTaskInstances(final ExecutionContext context, final List<User> taskAssigneesList){
		
		final Node curNode = context.getNode();
		jbpmTemplate.execute(new JbpmCallback(){
			
			public Object doInJbpm(JbpmContext jbpmContext) throws JbpmException {
				
				Node curNode = context.getNode();
				TaskMgmtInstance tmi = context.getTaskMgmtInstance();
				TaskInstance tInstance = tmi.createTaskInstance(null, context);
				tInstance.setName(curNode.getName());

				int userCount = taskAssigneesList.size();
				String[] pooleActorIds = new String[userCount];
				int i = 0;
				for(User user : taskAssigneesList){
					pooleActorIds[i] = user.getLoginId();
					i++;
				}
				tInstance.setPooledActors(pooleActorIds);

				return null;
			}
		});
		
		
		// Send Notifications
		notifiyTaskAssignees(context.getProcessDefinition().getName(), curNode.getName(), taskAssigneesList);
	}
	
	/**
	 * This method will close all the open task instances
	 */
	@SuppressWarnings("unchecked")
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
		return (List<TaskInstance>)jbpmTemplate.findTaskInstances(actorId);
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
			throw new CaaersConfigurationException("The workflow is not configured for " + workflowDefinitionName);
		}
		
		TaskConfig taskConfig = wfConfig.findTaskConfig(taskNodeName);
		if(taskConfig == null) throw new CaaersConfigurationException("The task [" + taskNodeName + "] is not configured for workflow [" + workflowDefinitionName + "]");
		return taskConfig;
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
	
	/**
	 * This method will return the {@link WorkflowConfig} associated to a domain object type
	 */
	public WorkflowConfig findWorkflowConfigForDomainObject(Class<? extends DomainObject> klass) {
		return workflowConfigDao.getByDomainObject(klass);
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
	
	public JbpmTemplate getJbpmTemplate() {
		return jbpmTemplate;
	}
	
	public void setJbpmTemplate(JbpmTemplate jbpmTemplate) {
		this.jbpmTemplate = jbpmTemplate;
	}
	public List<ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}
	public void setProcessDefinitions(List<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}
	
}