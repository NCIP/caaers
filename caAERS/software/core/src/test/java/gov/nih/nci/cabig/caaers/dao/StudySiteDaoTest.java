package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.workflow.StudySiteWorkflowConfig;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowConfig;

import java.util.Arrays;
import java.util.List;

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
		assertTrue(site.isActive());
	}
	
	public void testMatchByStudyAndOrgNciId() {
		StudySite site = getDao().matchByStudyAndOrgNciId("NCI", "1138-43", "local");
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
		assertNotNull(site.getStudySiteWorkflowConfigs());
		assertNotNull(site.getReportingPeriodWorkflowConfig());
	}
	public void testSaveSiteWorkflowConfigAssociation(){
		{
			StudySite site = getDao().getById(-1000);
			assertNotNull(site);
			assertNotNull(site.getStudySiteWorkflowConfigs());
			assertNotNull(site.getReportingPeriodWorkflowConfig());
			assertNull(site.findWorkflowConfig("mytest"));
		}
		interruptSession();
		{
			StudySite site = getDao().getById(-1000);
			assertNotNull(site);
			assertNotNull(site.getReportingPeriodWorkflowConfig());
			WorkflowConfig wfconfig = new WorkflowConfig();
			wfconfig.setId(-3000);
			wfconfig.setVersion(0);
			
			StudySiteWorkflowConfig ssWfCfg = new StudySiteWorkflowConfig("mytest", site, wfconfig);
			site.addStudySiteWorkflowConfig(ssWfCfg);
			
			assertNotNull(site.findWorkflowConfig("mytest"));
			getDao().save(site);
			
		}
		interruptSession();
		{
			StudySite site = getDao().getById(-1000);
			assertNotNull(site);
			assertNotNull(site.getReportingPeriodWorkflowConfig());
			assertNotNull(site.findWorkflowConfig("mytest"));
			assertEquals("MyTest", site.findWorkflowConfig("mytest").getName());
		}
	}

/*
    public void test2() {
        List codes = getDao().getSitesOfCoordinatedStudiesByOrganizationCodes(Arrays.asList("CALGB"));
        System.out.println(codes);
        System.out.println(codes.size());
    }
*/
}
