package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.CaaersConfigurationException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Location;
import gov.nih.nci.cabig.caaers.domain.PersonRole;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.Assignee;
import gov.nih.nci.cabig.caaers.domain.workflow.PersonAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.RoleAssignee;
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
import java.util.Map;

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
	
	private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
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
			ProcessInstance processInstance = new ProcessInstance(pDefinition);
			Long processId =  saveProcessInstance(processInstance);
			assert processId != null;
			return processInstance;	
		}
		return null;
	}
	
	public void makeDefaultTransition(Long workflowId) {
		ProcessInstance processInstance = fetchProcessInstance(workflowId.longValue());
		Token token = processInstance.getRootToken();
        token.signal();
		
        saveProcessInstance(processInstance);
	}
	
	public Long saveProcessInstance(ProcessInstance processInstance) {
		return jbpmTemplate.saveProcessInstance(processInstance);
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
	 * @see WorkflowService#findTaskAssignees(ProcessInstance, String)
	 */
	public List<User> findTaskAssignees(ProcessInstance pInstance, String taskNodeName) {
		List<User> assignees = new ArrayList<User>();
		TaskConfig taskConfig = findTaskConfig(pInstance.getProcessDefinition().getName(), taskNodeName);
		for(Assignee assignee : taskConfig.getAssignees()){
			
			if(assignee.isRole()){
				RoleAssignee roleAssignee = (RoleAssignee) assignee;
				
				assignees.addAll(findUsersHavingRole(roleAssignee.getUserRole(), pInstance, taskConfig.getLocation()));
				
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
		if(recipients.isEmpty())
			return;
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
	
	
	public List<User> findUsersHavingRole(PersonRole personRole,  ProcessInstance pInstance , Location location){
		List<User> users = new ArrayList<User>();
		Map<Object, Object> contextVariables = pInstance.getContextInstance().getVariables(); 
		
		String wfType = (String)contextVariables.get(VAR_WF_TYPE);
		Integer reportingPeriodId = (Integer) contextVariables.get(VAR_REPORTING_PERIOD_ID);
		Integer expeditedReportId = (Integer) contextVariables.get(VAR_EXPEDITED_REPORT_ID);
		
		ExpeditedAdverseEventReport aeReport = null;
		Study study = null;
		AdverseEventReportingPeriod reportingPeriod = null;
		StudyParticipantAssignment assignment = null;
		StudySite site = null;
		if(StringUtils.equals(wfType, ExpeditedAdverseEventReport.class.getName())){
			aeReport = expeditedAdverseEventReportDao.getById(expeditedReportId);
			reportingPeriod = aeReport.getReportingPeriod();
			assignment = reportingPeriod.getAssignment();
			site = assignment.getStudySite();
			study = site.getStudy();
		}else if (StringUtils.equals(wfType, AdverseEventReportingPeriod.class.getName())){
			reportingPeriod = adverseEventReportingPeriodDao.getById(reportingPeriodId);
			assignment = reportingPeriod.getAssignment();
			site = assignment.getStudySite();
			study = site.getStudy();
		}
		
		List<StudyOrganization> studyOrganizations = new ArrayList<StudyOrganization>();
		switch(location){
		
		case COORDINATING_CENTER:
			StudyCoordinatingCenter coordinatingCenter = study.getStudyCoordinatingCenter();
			if(coordinatingCenter != null) studyOrganizations.add(coordinatingCenter);
			break;
		case STUDY_SITE:
			studyOrganizations.add(site);
			break;
		case ALL:
			studyOrganizations.add(site);
			StudyCoordinatingCenter coordinatingCenter2 = study.getStudyCoordinatingCenter();
			if(coordinatingCenter2 != null) studyOrganizations.add(coordinatingCenter2);
			break;
		}
		
		switch(personRole){
			case ADVERSE_EVENT_COORDINATOR:
				List<User> aeCoordinators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				users.addAll(aeCoordinators);
				break;
			case PARTICIPANT_COORDINATOR:
				List<User> participantCoordinators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				users.addAll(participantCoordinators);
				break;
			case PHYSICIAN:
				User physician = aeReport.getPhysician().getUser();
				if(physician != null){
					users.add(physician);
				}
				break;
			case PRINCIPAL_INVESTIGATOR:
				List<User> principalInvestigators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				users.addAll(principalInvestigators);
				break;
			case REPORTER:
				User reporter = aeReport.getReporter().getUser();
				if(reporter != null){
					users.add(reporter);
				}
				break;
			case SITE_INVESTIGATOR:
				List<User> siteInvestigators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				users.addAll(siteInvestigators);
				break;
			case SITE_PRINCIPAL_INVESTIGATOR:
				List<User> sitePrincipalInvestigators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				users.addAll(sitePrincipalInvestigators);
				break;
			case STUDY_COORDINATOR:
				break;
		}
		return users;
	}
	
	private List<User> fetchUsersHavingRoleFromStudyOrganizations(List<StudyOrganization> studyOrganizations, PersonRole role){
		List<User> users = new ArrayList<User>();
		for(StudyOrganization studyOrg : studyOrganizations){
			users.addAll(studyOrg.findUsersByRole(role));
		}
		return users;
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
	
	public ExpeditedAdverseEventReportDao getExpeditedAdverseEventReportDao() {
		return expeditedAdverseEventReportDao;
	}
	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}
	public AdverseEventReportingPeriodDao getAdverseEventReportingPeriodDao() {
		return adverseEventReportingPeriodDao;
	}
	public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}
}