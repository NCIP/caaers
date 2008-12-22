package gov.nih.nci.cabig.caaers.service.workflow;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.PersonAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.springmodules.workflow.jbpm31.JbpmTemplate;
/**
 * 
 * @author Biju Joseph
 *
 */
public class WorkflowServiceImplTest extends CaaersTestCase {
	
	WorkflowServiceImpl wfService;
	WorkflowConfigDao wfConfigDao;
	WorkflowConfig wfConfig;
	UserDao userDao;
	
	ResearchStaff r1;
	
	JbpmTemplate template;
	
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
		a1.setResearchStaff(r1);
		tc.addAssignee(a1);
		
		wfService.setWorkflowConfigDao(wfConfigDao);
		
		template = registerMockFor(JbpmTemplate.class);
		wfService.setJbpmTemplate(template);
		
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
			wfService.createProcessInstance("abcd");
			fail("create process should throw exception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		verifyMocks();
	}
	public void testCreateProcessInstance() {
		try {
			EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName((String) EasyMock.anyObject())).andReturn(wfConfig);
//			EasyMock.expect(template.saveProcessInstance((ProcessInstance) EasyMock.anyObject())).andReturn(1L);
			replayMocks();
			ProcessInstance l = wfService.createProcessInstance("test");
			assertNotNull(l);
			verifyMocks();
		} catch (JbpmException e) {
			assertEquals("token 'Token(/)' can't be signalled cause it is currently not positioned in a node", e.getMessage());
		}
	}
	
	


	public void testFindTaskAssignees() {
		EasyMock.expect(wfConfigDao.getByWorkflowDefinitionName(wfConfig.getWorkflowDefinitionName())).andReturn(wfConfig);
		replayMocks();
		List<User> assignees = wfService.findTaskAssignees("Test", "a1");
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
	
	public void testFindWorkflowConfigForDomainObject(){
		Class klass = AdverseEventReportingPeriod.class;
		EasyMock.expect(wfConfigDao.getByDomainObject(klass)).andReturn(wfConfig);
		replayMocks();
		WorkflowConfig config = wfService.findWorkflowConfigForDomainObject(klass);
		verifyMocks();
		assertNotNull(config);
	}

	

}
