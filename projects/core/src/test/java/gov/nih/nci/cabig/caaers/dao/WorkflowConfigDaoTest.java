package gov.nih.nci.cabig.caaers.dao;

import java.util.List;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.Location;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.workflow.NotificationRecipient;
import gov.nih.nci.cabig.caaers.domain.workflow.RoleRecipient;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;

/**
 * 
 * @author Sameer Sawant
 */
public class WorkflowConfigDaoTest extends DaoTestCase<WorkflowConfigDao> {

	public void testGet() throws Exception {
        WorkflowConfig workflowConfig = getDao().getById(1000);
        assertEquals(3, workflowConfig.getTaskConfigs().size());
        TaskConfig t = null;
        for(TaskConfig tc: workflowConfig.getTaskConfigs())
        	if(tc.getStatusName().equals("Level 1 Review"))
        		t = tc;
        assertEquals(2, t.getNotificationRecipients().size());
        assertEquals(2, t.getRoleRecipients().size());
	}

	public void testGetByWorkflowDefinitionName() throws Exception {
		List<WorkflowConfig> wcList = getDao().getByWorkflowDefinitionName("routineFlow");
		assertEquals(1, wcList.size());
		assertEquals(3, wcList.get(0).getTaskConfigs().size());
		TaskConfig t = null;
        for(TaskConfig tc: wcList.get(0).getTaskConfigs())
        	if(tc.getStatusName().equals("Level 1 Review"))
        		t = tc;
        assertEquals(2, t.getNotificationRecipients().size());
        assertEquals(2, t.getRoleRecipients().size());
	}
	
	public void testSave() throws Exception {
		WorkflowConfig wc = new WorkflowConfig();
		wc.setWorkflowDefinitionName("aeReportFlow");
		wc.setDefaultAssignee("system_admin");
		wc.setEnabled(true);
		
		TaskConfig tc1 = new TaskConfig();
		tc1.setApplicable(true);
		tc1.setLocation(Location.COORDINATING_CENTER);
		tc1.setStatusName("Level 1 Review");
		
		TaskConfig tc2 = new TaskConfig();
		tc2.setApplicable(true);
		tc2.setLocation(Location.ALL);
		tc2.setStatusName("Level 2 Review");
		
		TaskConfig tc3 = new TaskConfig();
		tc3.setApplicable(true);
		tc3.setLocation(Location.ALL);
		tc3.setStatusName("Review Complete");
		
		wc.addTaskConfigs(tc1);
		wc.addTaskConfigs(tc2);
		wc.addTaskConfigs(tc3);
		
		RoleRecipient rr1 = new RoleRecipient();
		rr1.setUserGroupType(UserGroupType.caaers_ae_cd);
		
		RoleRecipient rr2 = new RoleRecipient();
		rr1.setUserGroupType(UserGroupType.caaers_admin);
		
		tc1.addRoleRecipient(rr1);
		tc2.addRoleRecipient(rr2);
		
		NotificationRecipient nr1 = new NotificationRecipient();
		nr1.setName("userId1");
		
		NotificationRecipient nr2 = new NotificationRecipient();
		nr2.setName("userId2");
		
		NotificationRecipient nr3 = new NotificationRecipient();
		nr3.setName("userId3");
		
		NotificationRecipient nr4 = new NotificationRecipient();
		nr4.setName("userId4");
		
		tc1.addNotificationRecipients(nr1);
		tc1.addNotificationRecipients(nr2);
		tc2.addNotificationRecipients(nr3);
		tc3.addNotificationRecipients(nr4);
		
		getDao().save(wc);
		Integer savedId = wc.getId();
		
		interruptSession();
		
		List<WorkflowConfig> list = getDao().getByWorkflowDefinitionName("aeReportFlow");
		assertEquals(1, list.size());
		
		WorkflowConfig workflowConfig = list.get(0);
		
		assertEquals(3, workflowConfig.getTaskConfigs().size());
        TaskConfig t = null;
        for(TaskConfig tc: workflowConfig.getTaskConfigs())
        	if(tc.getStatusName().equals("Level 1 Review"))
        		t = tc;
        assertEquals(2, t.getNotificationRecipients().size());
        assertEquals(1, t.getRoleRecipients().size());
	}
}