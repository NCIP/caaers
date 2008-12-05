package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.CaaersConfigurationException;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.Assignee;
import gov.nih.nci.cabig.caaers.domain.workflow.PersonAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.workflow.PossibleTransitionsResolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.db.GraphSession;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springframework.beans.factory.annotation.Required;

/**
 * This class has methods, that deals with the JBPM workflow engine.
 * @author Sameer
 * @author Biju Joseph
 */
public class WorkflowServiceImpl implements WorkflowService {
	private JbpmConfiguration jbpmConfiguration;
	private ProcessDefinition routineFlowProcessDefinition;
	private CaaersJavaMailSender caaersJavaMailSender;
	private WorkflowConfigDao workflowConfigDao;
	private SessionFactory sessionFactory;
	private PossibleTransitionsResolver possibleTransitionsResolver;
	/*
	 * This method is used to create and persist processInstance
	 */
	public Long createProcessInstance(String definitionType){
		JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
		Session session = sessionFactory.getCurrentSession();
		jbpmContext.setSession(sessionFactory.getCurrentSession());
		try{
			if(definitionType.equals("routineFlow")){
				ProcessInstance pInstance = new ProcessInstance(routineFlowProcessDefinition);
				jbpmContext.save(pInstance);
				return pInstance.getId();
			}else if(definitionType.equals("")){
			
			}
		}finally{
			jbpmContext.close();
		}
		return null;
	}
	
	/**
	 * This method, return the list of possible transitions available at a given time on a specific workflow process instance
	 */
	public List<String> nextTransitions(String workflowDefinitionName, Long workflowId){
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		ProcessInstance processInstance = fetchProcessInstance(workflowId);
		return possibleTransitionsResolver.fetchNextTransitions(wfConfig, processInstance);
	}
		
	
	/*
	 * This method is used to fetch the latest processInstance given the processInstance name
	 */
	public ProcessInstance fetchProcessInstance(Long id){
		JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
		jbpmContext.setSession(sessionFactory.getCurrentSession());
		try{
			GraphSession graphSession = jbpmContext.getGraphSession();
			return graphSession.getProcessInstance(id);
		}finally{
			jbpmContext.close();
		}
	}
		
	/*
	 *  This method is used to create a bunch of TaskInstances for the actors involved
	 */
	public void createTaskInstances(ExecutionContext context, List<User> taskAssigneesList){
		JbpmContext jbpmContext = jbpmConfiguration.createJbpmContext();
		Session session = sessionFactory.getCurrentSession();
		jbpmContext.setSession(sessionFactory.getCurrentSession());
		try{
			// First close the existing tasks for the processInstance in context.
			Collection<TaskInstance> existingTasks = context.getProcessInstance().getTaskMgmtInstance().getTaskInstances();
			if(existingTasks != null && existingTasks.size() > 0){
				for(TaskInstance ti: existingTasks){
					if(ti.isOpen())
						ti.end();
				}
			}
			Token token = context.getToken();
			TaskMgmtInstance tmi = context.getTaskMgmtInstance();
			Task task = new Task();
			for(User taskAssignee: taskAssigneesList){
				TaskInstance tInstance = tmi.createTaskInstance(task, context);
				tInstance.setActorId(taskAssignee.getLoginId());
				jbpmContext.save(tInstance);
			}
		}finally{
			jbpmContext.close();
		}
		// Send Notifications
		notifyAssignees(taskAssigneesList, null, null);

	}
	
	/*
	 *  This method returns the list of active tasks for a given userId
	 */
	public List<TaskInstance> fetchTaskInstances(String actorId){
		
		return null;
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
	
	
	
	/*
	 *  This method sends notifications.
	 */
	public void notifyAssignees(List<User> emailRecipients, String subject, String content){
		String[] pdfFilePaths = new String[0];
		String[] ppl = {"sameer.sawant@semanticbits.com"};
		caaersJavaMailSender.sendMail(ppl, "Test title", "Test content", pdfFilePaths);
	}
	
	public void setJbpmConfiguration(JbpmConfiguration jbpmConfiguration){
		this.jbpmConfiguration = jbpmConfiguration;
	}
	
	public void setRoutineFlowProcessDefinition(ProcessDefinition routineFlowProcessDefinition){
		this.routineFlowProcessDefinition = routineFlowProcessDefinition;
	}
	
	public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender){
		this.caaersJavaMailSender = caaersJavaMailSender;
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public void setPossibleTransitionsResolver(PossibleTransitionsResolver possibleTransitionsResolver){
		this.possibleTransitionsResolver = possibleTransitionsResolver;
	}
	
	public void setWorkflowConfigDao(WorkflowConfigDao workflowConfigDao){
		this.workflowConfigDao = workflowConfigDao;
	}
	
	
}