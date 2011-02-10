package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.domain.workflow.Assignee;
import gov.nih.nci.cabig.caaers.domain.workflow.PersonAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.PersonTransitionOwner;
import gov.nih.nci.cabig.caaers.domain.workflow.RoleAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.RoleTransitionOwner;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.TransitionConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.TransitionOwner;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.service.FreeMarkerService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.workflow.PossibleTransitionsResolver;
import gov.nih.nci.cabig.caaers.workflow.callback.CreateTaskJbpmCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jbpm.graph.def.*;
import org.jbpm.graph.exe.*;
import org.jbpm.taskmgmt.exe.*;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.workflow.jbpm31.*;

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
	
	private ReportDao reportDao;
	
	private AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	
	private StudyDao studyDao;
	
	private CaaersJavaMailSender caaersJavaMailSender;
	
	private WorkflowConfigDao workflowConfigDao;
	
	private PossibleTransitionsResolver possibleTransitionsResolver;
	
	private FreeMarkerService freeMarkerService;
	
	private Configuration configuration;

    private UserRepository userRepository;
	
	private Logger log = Logger.getLogger(WorkflowServiceImpl.class);
	
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
	public ProcessInstance createProcessInstance(String workflowDefinitionName, Map<String, Object> contextVariables){
		
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
			processInstance.getContextInstance().addVariables(contextVariables);
			Long processId =  saveProcessInstance(processInstance);
			
			assert processId != null;
			
			Token token = processInstance.getRootToken();
	        token.signal();
			
	        saveProcessInstance(processInstance);
	        return processInstance;	
		}
		return null;
	}

	
	public Long saveProcessInstance(ProcessInstance processInstance) {
		return jbpmTemplate.saveProcessInstance(processInstance);
	}
	
	/**
	 * This method, return the list of possible transitions available at a given time on a specific workflow process instance
	 */
	public List<Transition> nextTransitions( Integer workflowId, String loginId){
		ProcessInstance processInstance = fetchProcessInstance(workflowId.longValue());
		String workflowDefinitionName = processInstance.getProcessDefinition().getName();
		WorkflowConfig wfConfig = workflowConfigDao.getByWorkflowDefinitionName(workflowDefinitionName);
		List<Transition> possibleTransitions =  possibleTransitionsResolver.fetchNextTransitions(wfConfig, processInstance);
		//now filter based on login roles
	
		String taskNodeName = processInstance.getRootToken().getNode().getName();
		TaskConfig taskConfig = wfConfig.findTaskConfig(taskNodeName);
		if(taskConfig == null )  return possibleTransitions; // task is not configured
		
        List<UserGroupType> userGroupTypes = userRepository.getUserByLoginName(loginId).getUserGroupTypes();  //CAAERS-4586
		
        Map<String, Transition> filteredTransitionMap = new HashMap<String, Transition>();

		for(Transition transition : possibleTransitions){
			TransitionConfig transitionConfig = taskConfig.findTransitionConfig(transition.getName());
			if(transitionConfig == null) continue; //transition is not configured so no body can move it expect sysadmin
			
			List<TransitionOwner> owners = transitionConfig.getOwners();
			if(owners == null) continue; //no body owns the transition
			
			for(TransitionOwner owner : owners){
				if(owner.isPerson()){
					PersonTransitionOwner personOwner = (PersonTransitionOwner) owner;
					if(StringUtils.equals(personOwner.getPerson().getLoginId(), loginId)) {
                        if(!filteredTransitionMap.containsKey(transition.getName()) ){
                           filteredTransitionMap.put(transition.getName(), transition);
                        }
					}
				}else{
					RoleTransitionOwner roleOwner = (RoleTransitionOwner) owner;
					PersonRole ownerRole = roleOwner.getUserRole();
					UserGroupType[] ownerGroupTypes = ownerRole.getUserGroups();
					
					for(UserGroupType userGroupType : userGroupTypes){
						if(ArrayUtils.contains(ownerGroupTypes, userGroupType)){
							if(!filteredTransitionMap.containsKey(transition.getName()) ){
                                filteredTransitionMap.put(transition.getName(), transition);
                            }
							break;
						}
					}
					
				}
			}
		}
		
		return new ArrayList<Transition>(filteredTransitionMap.values());
	}
	
	public List<ReviewStatus> allowedReviewStatuses(String loginId){
		Map<ReviewStatus, Boolean> allowedReviewStatusMap = new HashMap<ReviewStatus, Boolean>();
		try{

            List<UserGroupType> userGroupTypes = userRepository.getUserByLoginName(loginId).getUserGroupTypes(); //CAAERS-4586
            //first fetch all the possible workflow configs.
            List<WorkflowConfig> workflowConfigList = workflowConfigDao.getAllWorkflowConfigs();
            for(WorkflowConfig wc : workflowConfigList){
                for(TaskConfig tc: wc.getTaskConfigs()){
                    for(Assignee assignee: tc.getAssignees()){
                        if(assignee.isUser()){
                            PersonAssignee personAssignee = (PersonAssignee) assignee;
                            if(personAssignee.getPerson().getLoginId().equals(loginId)){
                                allowedReviewStatusMap.put(ReviewStatus.valueOf(tc.getStatusName()), true);
                            }
                        }else if(assignee.isRole()){
                            RoleAssignee roleAssignee = (RoleAssignee) assignee;
                            PersonRole role = roleAssignee.getUserRole();
                            for(UserGroupType type: userGroupTypes){
                                if(ArrayUtils.contains(role.getUserGroups(), type)){
                                    allowedReviewStatusMap.put(ReviewStatus.valueOf(tc.getStatusName()), true);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }catch(CaaersNoSuchUserException noUser){
            log.warn("No user is present within caAERS having loginId : " + loginId);
        }
		List<ReviewStatus> allowedReviewStatusList = new ArrayList<ReviewStatus>(allowedReviewStatusMap.keySet());
		return allowedReviewStatusList;
	}
	
	
	public List<String> nextTransitionNames(Integer workflowId, String loginId) {
		List<Transition> transitions = nextTransitions(workflowId, loginId);
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
		
		
			ExecutionContext context = createTaskCallback.getContext();
			String expediteReportUrl = " -- ";
			String reportingPeriodUrl = "--" ;
			Map variablesMap = context.getContextInstance().getVariables();
			
			if(variablesMap != null){
				expediteReportUrl = configuration.get(Configuration.CAAERS_BASE_URL) + URL_EXPEDITED_REPORT + String.valueOf(variablesMap.get(VAR_EXPEDITED_REPORT_ID))
											+ "&report=" + String.valueOf(variablesMap.get(WorkflowService.VAR_REPORT_ID));
				reportingPeriodUrl = configuration.get(Configuration.CAAERS_BASE_URL) + URL_REPORTING_PERIOD + String.valueOf(variablesMap.get(VAR_REPORTING_PERIOD_ID));
					
			}
			
			Map<Object, Object> contextVariables = new HashMap<Object, Object>();
			contextVariables.put(REPLACEMENT_EXPEDITED_REPORT_LINK, expediteReportUrl);
			contextVariables.put(REPLACEMENT_REPORTING_PERIOD_LINK, reportingPeriodUrl);
			
			String taskDescription = generateTaskDescription(createTaskCallback.getProcessDefinitionName(), createTaskCallback.getTaskName(), contextVariables);
			createTaskCallback.setTaskDescription(taskDescription);
			
			//create the tasks
			jbpmTemplate.execute(createTaskCallback);
			
			// Send Notifications
			try {
				if(taskDescription != null)
					notifiyTaskAssignees(taskDescription, createTaskCallback.getTaskName(),  createTaskCallback.getTaskAssigneesList());
			
			} catch (CaaersSystemException e) {
				log.error("Workflow Service : Error while sending email to task assignees", e);
			}
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
	public List<Person> findTaskAssignees(ProcessInstance pInstance, String taskNodeName) {
		List<Person> assignees = new ArrayList<Person>();
		TaskConfig taskConfig = findTaskConfig(pInstance.getProcessDefinition().getName(), taskNodeName);
		for(Assignee assignee : taskConfig.getAssignees()){
			
			if(assignee.isRole()){
				RoleAssignee roleAssignee = (RoleAssignee) assignee;
				
				assignees.addAll(findUsersHavingRole(roleAssignee.getUserRole(), pInstance, taskConfig.getLocation()));
				
			}else if(assignee.isUser()) {
				Person user = ((PersonAssignee) assignee).getPerson();
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
	public void notifiyTaskAssignees(String message, String taskNodeName, List<Person> recipients) {
		if(recipients.isEmpty())
			return;
		String subject = "Task : " + taskNodeName;
		String[] to = new String[recipients.size()];
		int i = 0;
		for(Person person : recipients){
			to[i] = person.getEmailAddress();
			i++;
		}
		caaersJavaMailSender.sendMail(to, subject, message, new String[0]);
	}
	
	
	public String generateTaskDescription(String workflowDefinitionName,String taskNodeName, Map<Object, Object> contextVariables){
		TaskConfig taskConfig = findTaskConfig(workflowDefinitionName, taskNodeName);
		if(taskConfig.getMessage() != null && !taskConfig.getMessage().equals("")){
			String message = freeMarkerService.applyRuntimeReplacementsForReport(taskConfig.getMessage(), contextVariables);
			return message;
		}else
			return null;
	}
	
	
	public List<Person> findUsersHavingRole(PersonRole personRole,  ProcessInstance pInstance , Location location){
		List<Person> people = new ArrayList<Person>();
		Map<Object, Object> contextVariables = pInstance.getContextInstance().getVariables(); 
		
		String wfType = (String)contextVariables.get(VAR_WF_TYPE);
		Integer reportingPeriodId = (Integer) contextVariables.get(VAR_REPORTING_PERIOD_ID);
		Integer expeditedReportId = (Integer) contextVariables.get(VAR_EXPEDITED_REPORT_ID);
		Integer reportId = (Integer) contextVariables.get(VAR_REPORT_ID);

		Report report = null;
		Study study = null;
		AdverseEventReportingPeriod reportingPeriod = null;
		StudyParticipantAssignment assignment = null;
		StudySite site = null;
		if(StringUtils.equals(wfType, Report.class.getName())){
			report = reportDao.getById(reportId);
			reportingPeriod = report.getAeReport().getReportingPeriod();
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
		
		studyDao.reassociateStudyOrganizations(studyOrganizations);
		
		switch(personRole){
			case AE_EXPEDITED_REPORT_REVIEWER:
				List<Person> saeCoordinators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				people.addAll(saeCoordinators);
				break;
			case AE_STUDY_DATA_REVIEWER:
				List<Person> dataCoordinators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				people.addAll(dataCoordinators);
				break;
			case ADVERSE_EVENT_COORDINATOR:
				List<Person> aeCoordinators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				people.addAll(aeCoordinators);
				break;
			case AE_REPORTER:
				List<Person> participantCoordinators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				people.addAll(participantCoordinators);
				break;
			case PHYSICIAN:
				Person physician = report.getAeReport().getPhysician().getPerson();
				if(physician != null && physician.isUser()){
					people.add(physician);
				}
				break;
			case PRINCIPAL_INVESTIGATOR:
				List<Person> principalInvestigators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				people.addAll(principalInvestigators);
				break;
			case REPORTER:
				Person reporter = report.getAeReport().getReporter().getPerson();
				if(reporter != null && reporter.isUser()){
					people.add(reporter);
				}
				break;
			case SITE_INVESTIGATOR:
				List<Person> siteInvestigators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				people.addAll(siteInvestigators);
				break;
			case SITE_PRINCIPAL_INVESTIGATOR:
				List<Person> sitePrincipalInvestigators = fetchUsersHavingRoleFromStudyOrganizations(studyOrganizations, personRole);
				people.addAll(sitePrincipalInvestigators);
				break;
			case STUDY_COORDINATOR:
				break;
		}
		return people;
	}
	
	

	private List<Person> fetchUsersHavingRoleFromStudyOrganizations(List<StudyOrganization> studyOrganizations, PersonRole role){
		List<Person> people = new ArrayList<Person>();
		for(StudyOrganization studyOrg : studyOrganizations){
			people.addAll(studyOrg.findUsersByRole(role));
		}
		return people;
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

	
	public FreeMarkerService getFreeMarkerService() {
		return freeMarkerService;
	}
	public void setFreeMarkerService(FreeMarkerService freeMarkerService) {
		this.freeMarkerService = freeMarkerService;
	}
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public StudyDao getStudyDao() {
		return studyDao;
	}
	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}
	
	public void setReportDao(ReportDao reportDao){
		this.reportDao = reportDao;
	}
	
	public ReportDao getReportDao(){
		return reportDao;
	}

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}