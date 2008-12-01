package gov.nih.nci.cabig.caaers.workflow;

import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;

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
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;




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
	
	public List<String> nextTransitions(String workflowDefinitionName, Long workflowId){
		List<WorkflowConfig> workflowConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		ProcessInstance processInstance = fetchProcessInstance(workflowId);
		return possibleTransitionsResolver.fetchNextTransitions(workflowConfig.get(0), processInstance);
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
	public void createTaskInstances(ExecutionContext context, ArrayList<String> taskAssigneesList){
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
			for(String taskAssignee: taskAssigneesList){
				TaskInstance tInstance = tmi.createTaskInstance(task, context);
				tInstance.setActorId(taskAssignee);
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
	
	/*
	 *  This method sends notifications.
	 */
	public void notifyAssignees(ArrayList<String> emailRecipients, String subject, String content){
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