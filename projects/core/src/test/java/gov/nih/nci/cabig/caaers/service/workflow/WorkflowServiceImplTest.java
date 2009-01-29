package gov.nih.nci.cabig.caaers.service.workflow;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Location;
import gov.nih.nci.cabig.caaers.domain.PersonRole;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.PersonAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.service.FreeMarkerService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.workflow.callback.CreateTaskJbpmCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;
import org.jbpm.JbpmException;
import org.jbpm.context.exe.ContextInstance;
import org.jbpm.graph.def.Node;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;
import org.springmodules.workflow.jbpm31.JbpmTemplate;
/**
 * 
 * @author Biju Joseph
 *
 */
public class WorkflowServiceImplTest extends AbstractTestCase {
	
	WorkflowServiceImpl wfService;
	WorkflowConfigDao wfConfigDao;
	WorkflowConfig wfConfig;
	UserDao userDao;
	AdverseEventReportingPeriodDao reportingPeriodDao;
	Map<String, Object> variables = new HashMap<String, Object>();
	
	ResearchStaff r1;
	
	JbpmTemplate template;
	Configuration configuration;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		wfService = new WorkflowServiceImpl();
		
		List<ProcessDefinition> defs = new ArrayList<ProcessDefinition>();
		ProcessDefinition def = new ProcessDefinition();
		def.setName("test");
		defs.add(def);		
		wfService.setProcessDefinitions(defs);
		
		wfConfigDao = registerDaoMockFor(WorkflowConfigDao.class);
		wfConfig = Fixtures.createWorkflowConfig("Test");
		wfConfig.setEnabled(true);
		
		
		TaskConfig tc = wfConfig.findTaskConfig("a1");
		PersonAssignee a1 = new PersonAssignee();
		r1  = new ResearchStaff();
		r1.setEmailAddress("joel@abcd.com");
		r1.setLoginId("joel");
		a1.setUser(r1);
		tc.addAssignee(a1);
		
		wfService.setWorkflowConfigDao(wfConfigDao);
		
		template = registerMockFor(JbpmTemplate.class);
		wfService.setJbpmTemplate(template);
		
		reportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
		wfService.setAdverseEventReportingPeriodDao(reportingPeriodDao);
		wfService.setFreeMarkerService(new FreeMarkerService());
		configuration = registerMockFor(Configuration.class);
		wfService.setConfiguration(configuration);
		
	}
	
	public void testFindProcessDefinitionByName(){
		assertNotNull(wfService.findProcessDefinitionByName("test"));
	}
	public void testFindProcessDefinitionByName_ReturningNull() {
		assertNull(wfService.findProcessDefinitionByName("tester"));
	}
	
	
	public void testCreateProcessInstance_ThrowingException() {
		try {
			EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName((String) EasyMock.anyObject())).andReturn(wfConfig);
			replayMocks();
			wfService.createProcessInstance("abcd", variables);
			fail("create process should throw exception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		verifyMocks();
	}
	public void testCreateProcessInstance() {
		try {
			EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName((String) EasyMock.anyObject())).andReturn(wfConfig);
			EasyMock.expect(template.saveProcessInstance((ProcessInstance) EasyMock.anyObject())).andReturn(1L);
			replayMocks();
			ProcessInstance l = wfService.createProcessInstance("test", variables);
			assertNotNull(l);
			verifyMocks();
		} catch (JbpmException e) {
			assertEquals("token 'Token(/)' can't be signalled cause it is currently not positioned in a node", e.getMessage());
		}
	}
	
	


	public void testFindTaskAssignees() {
		EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName(wfConfig.getWorkflowDefinitionName())).andReturn(wfConfig);
		ProcessInstance pInstance = registerMockFor(ProcessInstance.class);
		ProcessDefinition def = new ProcessDefinition();
		def.setName(wfConfig.getWorkflowDefinitionName());
		EasyMock.expect(pInstance.getProcessDefinition()).andReturn(def).anyTimes();
		replayMocks();
		List<User> assignees = wfService.findTaskAssignees(pInstance, "a1");
		verifyMocks();
		assertNotNull(assignees);
		assertEquals(1, assignees.size());
		assertEquals("joel", assignees.get(0).getLoginId());
	}

	public void testFindTaskConfig() {
		
		EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName(wfConfig.getWorkflowDefinitionName())).andReturn(wfConfig);
		replayMocks();
		TaskConfig tConfig = wfService.findTaskConfig("Test", "a1");
		assertNotNull(tConfig);
		assertTrue(tConfig.getApplicable());
		assertEquals(tConfig.getTaskName() , "a1");
		
	}
	public void testFindTaskConfigThrowingException() {
		
		EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName(wfConfig.getWorkflowDefinitionName())).andReturn(null);
		replayMocks();
		try {
			wfService.findTaskConfig("Test", "a1");
			fail("must throw exception");
		} catch (CaaersSystemException e) {
			assertEquals("WF-0011", e.getErrorCode());
		}
		
		
	}
	

	public void testFetchProcessInstance(){
		Long processInstanceId = new Long(5);
		ProcessInstance pInstance = new ProcessInstance();
		EasyMock.expect(template.findProcessInstance(processInstanceId)).andReturn(pInstance);
		replayMocks();
		ProcessInstance pInstanceReturned = wfService.fetchProcessInstance(processInstanceId);
		verifyMocks();
		assertSame(pInstance, pInstanceReturned);
	}
	
	public void testCreatTaskInstances() {
		String nodeName = "a1";
		
		List<User> taskAssigneesList = new ArrayList<User>();
		Investigator inv1 = Fixtures.createInvestigator("test1");
		inv1.setEmailAddress("joel1@abc.com");
		inv1.setLoginId("joel1@abc.com");
		
		Investigator inv2 = Fixtures.createInvestigator("test2");
		inv2.setEmailAddress("joel2@abc.com");
		inv2.setLoginId("joel2@abc.com");
		
		taskAssigneesList.add(inv1);
		taskAssigneesList.add(inv2);
		

		ProcessDefinition processDefinition = new ProcessDefinition();
		processDefinition.setName("Test");
		ProcessInstance pInstance = new ProcessInstance();
		pInstance.setProcessDefinition(processDefinition);
		
		final Node node = new Node();
		node.setName(nodeName);
		
		Token token = new Token();
		token.setNode(node);
		token.setProcessInstance(pInstance);
		final TaskMgmtInstance taskMgmtInstance = registerMockFor(TaskMgmtInstance.class);
		
		ExecutionContext executionContext = new ExecutionContext(token){
			@Override
			public TaskMgmtInstance getTaskMgmtInstance() {
				return taskMgmtInstance;
			}
			@Override
			public ContextInstance getContextInstance() {
				ContextInstance c = super.getContextInstance();
				Map variables = new HashMap();
				c.setVariables(variables);
				return c;
			}
		};
		
		
		CaaersJavaMailSender caaersJavaMailSender = registerMockFor(CaaersJavaMailSender.class);
		CreateTaskJbpmCallback callback = new CreateTaskJbpmCallback(executionContext, taskAssigneesList);
		EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName(processDefinition.getName())).andReturn(wfConfig);
		EasyMock.expect(template.execute(callback)).andReturn(null);
		caaersJavaMailSender.sendMail((String[])EasyMock.anyObject(), (String)EasyMock.anyObject(), (String) EasyMock.anyObject(), (String[])EasyMock.anyObject());
		expect(configuration.get(Configuration.CAAERS_BASE_URL)).andReturn("www.abcd.com");
		replayMocks();
		wfService.setCaaersJavaMailSender(caaersJavaMailSender);
		wfService.createTaskInstances(callback);
		verifyMocks();
	}
	
	public void testFindUsersHavingRole(){
		
		List<User> users = new ArrayList<User>();
		Investigator inv = Fixtures.createInvestigator("Joel");
		users.add(inv);
		
		PersonRole personRole = PersonRole.ADVERSE_EVENT_COORDINATOR;
		ProcessInstance pInstance = registerMockFor(ProcessInstance.class);
		ContextInstance ctxInstance = registerMockFor(ContextInstance.class);
		expect(pInstance.getContextInstance()).andReturn(ctxInstance);
		Map<String, Object> ctxVariableMap = new HashMap<String, Object>();
		ctxVariableMap.put(WorkflowService.VAR_WF_TYPE, AdverseEventReportingPeriod.class.getName());
		ctxVariableMap.put(WorkflowService.VAR_REPORTING_PERIOD_ID, 5);
		expect(ctxInstance.getVariables()).andReturn(ctxVariableMap);
		
		AdverseEventReportingPeriod reportingPeriod = registerMockFor(AdverseEventReportingPeriod.class);
		expect(reportingPeriodDao.getById(5)).andReturn(reportingPeriod);
		StudyParticipantAssignment assignment = registerMockFor(StudyParticipantAssignment.class);
		expect(reportingPeriod.getAssignment()).andReturn(assignment);
		StudySite site = registerMockFor(StudySite.class);
		gov.nih.nci.cabig.caaers.domain.Study study = registerMockFor(gov.nih.nci.cabig.caaers.domain.Study.class);
		expect(assignment.getStudySite()).andReturn(site);
		expect(site.getStudy()).andReturn(study);
		expect(site.findUsersByRole(PersonRole.ADVERSE_EVENT_COORDINATOR)).andReturn(users);
		
		replayMocks();
		List<User> returnedUsers = wfService.findUsersHavingRole(personRole, pInstance, Location.STUDY_SITE);
		verifyMocks();
		assertNotNull(returnedUsers);
		assertEquals(1, returnedUsers.size());
		assertSame(users.get(0), returnedUsers.get(0));
	}
	

}
