package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.workflow.WorkflowConfigDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.workflow.RoleAssignee;
import gov.nih.nci.cabig.caaers.domain.workflow.TaskConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
import gov.nih.nci.cabig.caaers.service.workflow.WorkflowService;

/**
 * 
 * @author Sameer Sawant
 * @author Biju Joseph
 */
public class WorkflowConfigDaoTest extends DaoTestCase<WorkflowConfigDao> {

	public void testGet() throws Exception {
        WorkflowConfig workflowConfig = getDao().getById(1000);
        assertEquals(3, workflowConfig.getTaskConfigs().size());
        TaskConfig t = workflowConfig.findTaskConfig("Level 1 Review");
        assertNotNull(t);
        assertEquals("L1R", t.getStatusName());
	}

	public void testGetByWorkflowDefinitionName() throws Exception {
		WorkflowConfig workflowConfig = getDao().getByWorkflowDefinitionName(WorkflowService.WORKFLOW_EVALUATION_PERIOD_COORDINATING_CENTER);
		assertEquals(3, workflowConfig.getTaskConfigs().size());
        TaskConfig t = workflowConfig.findTaskConfig("Level 1 Review");
        assertNotNull(t);
        assertEquals("L1R", t.getStatusName());
	}
	
	
	public void testSave() {
		TaskConfig t = null;
		{
			 WorkflowConfig workflowConfig = getDao().getById(1000);
			 workflowConfig.setDefaultAssignee("sysadm");
			 StudySite oldSite = workflowConfig.getStudySite();
			 assertNotNull(oldSite);
			 assertEquals(-1000, oldSite.getId().intValue());
			 StudySite site = new StudySite();
			 site.setId(-1003);
			 site.setVersion(0);
			 workflowConfig.setStudySite(site);
			
			 getDao().save(workflowConfig);
		}
		interruptSession();
		{
			WorkflowConfig workflowConfig = getDao().getById(1000);
			assertEquals("sysadm", workflowConfig.getDefaultAssignee());
			StudySite oldSite = workflowConfig.getStudySite();
			 assertNotNull(oldSite);
			 assertEquals(-1003, oldSite.getId().intValue());
			
		}
	}
	
	public void testEdit(){
		{
			 WorkflowConfig workflowConfig = getDao().getById(1000);
			 TaskConfig t = workflowConfig.findTaskConfig("Level 2 Review");
			 assertTrue(t.getApplicable());
			 t.setApplicable(Boolean.FALSE);
			 RoleAssignee a = new RoleAssignee();
			 a.setName("test");
			 a.setUserRole(UserGroupType.caaers_admin);
			 t.addAssignee(a);
			 getDao().save(workflowConfig);
		}
		interruptSession();
		{
			WorkflowConfig workflowConfig = getDao().getById(1000);
			TaskConfig t = workflowConfig.findTaskConfig("Level 2 Review");
			assertEquals(1, t.getAssignees().size());
			assertEquals("test", t.getAssignees().get(0).getName());
		}
		
	}
	
	public void testGetDomainClass() {
		assertEquals(WorkflowConfig.class, getDao().domainClass());
	}
		
	
}