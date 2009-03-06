package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;
/**
 * 
 * @author Biju Joseph
 *
 */
public class StudySiteDaoTest extends DaoTestCase<StudySiteDao> {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testMatchByStudyAndOrg() {
		StudySite site = getDao().matchByStudyAndOrg("National Cancer Institute", "1138-43", "local");
		assertNotNull(site);
	}
	

	public void testFindByStudyAndOrganization() {
		StudySite site = getDao().findByStudyAndOrganization(-2, -1001);
		assertNotNull(site);
	}
	
	public void testFindByStudyAndOrganizationInvalidStudy(){
		StudySite site = getDao().findByStudyAndOrganization(-21, -1001);
		assertNull(site);
	}
	public void testFindByStudyAndOrganizationInvalidOrganization(){
		StudySite site = getDao().findByStudyAndOrganization(-2, -1002);
		assertNull(site);
	}
	
	public void testGetById(){
		StudySite site = getDao().getById(-1000);
		assertNotNull(site);
		assertNotNull(site.getWorkflowConfigs());
		assertNotNull(site.getWorkflowConfigs().get("reportingPeriod"));
	}
	public void testSaveSiteWorkflowConfigAssociation(){
		{
			StudySite site = getDao().getById(-1000);
			assertNotNull(site);
			assertNotNull(site.getWorkflowConfigs());
			assertNotNull(site.getWorkflowConfigs().get("reportingPeriod"));
			assertNull(site.getWorkflowConfigs().get("mytest"));
		}
		interruptSession();
		{
			StudySite site = getDao().getById(-1000);
			assertNotNull(site);
			assertNotNull(site.getWorkflowConfigs());
			assertNotNull(site.getWorkflowConfigs().get("reportingPeriod"));
			WorkflowConfig wfconfig = new WorkflowConfig();
			wfconfig.setId(-3000);
			wfconfig.setVersion(0);
			
			site.getWorkflowConfigs().put("mytest", wfconfig);
			assertNotNull(site.getWorkflowConfigs().get("mytest"));
			getDao().save(site);
			
		}
		interruptSession();
		{
			StudySite site = getDao().getById(-1000);
			assertNotNull(site);
			assertNotNull(site.getWorkflowConfigs());
			assertNotNull(site.getWorkflowConfigs().get("reportingPeriod"));
			assertNotNull(site.getWorkflowConfigs().get("mytest"));
			assertEquals("MyTest", site.getWorkflowConfigs().get("mytest").getName());
		}
	}
}
