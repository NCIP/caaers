package gov.nih.nci.cabig.caaers.workflow.handler;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

import gov.nih.nci.cabig.caaers.CaaersNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.workflow.NotificationRecipient;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
/**
 * 
 * @author Biju Joseph
 *
 */
public class NodeSkipActionHandlerTest extends CaaersNoSecurityTestCase {
	
	private UserDao userDao;
	private WorkflowConfigDao wfConfigDao;
	private WorkflowConfig wfConfig;
	
	private String taskDefName = "MyTask";
	private String emailAddress = "biju.joseph@semanticbits.com";
	
	private NodeSkipActionHandler handler;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		userDao = registerDaoMockFor(UserDao.class);
		wfConfigDao = registerDaoMockFor(WorkflowConfigDao.class);
		wfConfig = registerMockFor(WorkflowConfig.class);
		handler = new NodeSkipActionHandler();
		handler.setUserDao(userDao);
		handler.setWorkflowConfigDao(wfConfigDao);
		
	}
	

	public void testFetchTaskAssignees() {
		NotificationRecipient nfR = new NotificationRecipient();
		nfR.setName(emailAddress);
		ArrayList<NotificationRecipient> recipients = new ArrayList<NotificationRecipient>();
		recipients.add(nfR);
		
		ResearchStaff staff = new ResearchStaff();
		staff.setLoginId("biju");
		
		EasyMock.expect(wfConfig.getNotificationRecipientsForTask(taskDefName)).andReturn(recipients);
		EasyMock.expect(userDao.getByEmailAddress(emailAddress)).andReturn(staff);
		
		replayMocks();
		
		List<String> assignees = handler.fetchTaskAssignees(wfConfig, taskDefName);
		
		verifyMocks();
		assertTrue(assignees.size() == 1);
		assertEquals(assignees.get(0), "biju");
	}
	
	
	public void testDependencyInjectionOnBean(){
		handler = (NodeSkipActionHandler)getDeployedApplicationContext().getBean("nodeSkipActionHandler");
		assertTrue(handler.getWorkflowConfigDao() != null);
	}

}
