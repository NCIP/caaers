/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.impl.OrganizationManagementServiceImpl;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationServiceTest extends CaaersDbNoSecurityTestCase{

    private OrganizationDao organizationDao;
		private OrganizationManagementServiceImpl organizationManagementService;
	
	 @Override
	    protected void setUp() throws Exception {
	        super.setUp();

	        organizationManagementService = (OrganizationManagementServiceImpl) getDeployedApplicationContext().getBean("organizationManagementService");
	        organizationDao = (OrganizationDao) getDeployedApplicationContext().getBean("organizationDao");
	    }
	 
	 public void testAddOrganizations(){
			List<Organization> dbOrgs = organizationDao.getAll();
			 assertEquals(3,dbOrgs.size());
			 
			 List<Organization> orgs = new ArrayList<Organization>();
			 
			 Organization organization1 = new LocalOrganization();
			 organization1.setName("Organization 1");
			 organization1.setDescriptionText("Description");
			 organization1.setNciInstituteCode("NCI01");
			 orgs.add(organization1);
			 
			 Organization organization2 = new LocalOrganization();
			 organization2.setName("Organization 2");
			 organization2.setDescriptionText("Organization 2 description");
			 organization2.setNciInstituteCode("NCI02");
			 orgs.add(organization2);
			 
			 organizationManagementService.createOrUpdateOrganizations(orgs);
			 
			 interruptSession();
			 
			 dbOrgs = organizationDao.getAll();
			 assertEquals(5,dbOrgs.size());
		 }
	 
	 public void testAddAndUpdateOrganizatinos(){
			List<Organization> existingOrganizations = organizationDao.getAll();
			 assertEquals(3,existingOrganizations.size());
			 assertEquals("Northwestern University", existingOrganizations.get(1).getName());
			 assertEquals("NUCC", existingOrganizations.get(1).getDescriptionText());
			 assertEquals("WAKE", existingOrganizations.get(1).getNciInstituteCode());
			 assertFalse(existingOrganizations.get(1).getRetiredIndicator());
			 
			 // update existing organization
			Organization updatedOrganization = new LocalOrganization();
			updatedOrganization.setName("updated Organization");
			updatedOrganization.setDescriptionText("updated description");
			updatedOrganization.setNciInstituteCode("WAKE");
			updatedOrganization.setRetiredIndicator(true);
			 
			 List<Organization> organizations = new ArrayList<Organization>();
			 organizations.add(updatedOrganization);
			 
			 // add new organization
			 Organization organization1 = new LocalOrganization();
			 organization1.setName("Arkansas Medical Center");
			 organization1.setDescriptionText("University of Arkansas Medical Center");
			 organization1.setNciInstituteCode("AR006");
			 organizations.add(organization1);
			 
				// call the service		 
			 List<ProcessingOutcome> errorMssgs= organizationManagementService.createOrUpdateOrganizations(organizations);
			 
			 assertEquals(2,errorMssgs.size());
			 for(ProcessingOutcome errMssg : errorMssgs){
				 assertNotNull(errMssg.getBusinessId());
				 assertEquals(1,errMssg.getMessages().size());
			 }
			 
			 interruptSession();
			 
			 List<Organization> updatedOrganizations = organizationDao.getAll();
			 assertEquals(4,updatedOrganizations.size());
			 
			 Organization updatedOrg = organizationDao.getByNCIcode("WAKE");
			 assertEquals("updated Organization",updatedOrg.getName());
			 assertEquals("updated description",updatedOrg.getDescriptionText());
			 assertTrue(updatedOrg.getRetiredIndicator());
			 
		 }
	 
	 public void testUnSuccessfulMergeOrganization(){
			List<Organization> dbOrgs = organizationDao.getAll();
			 assertEquals(3,dbOrgs.size());
			 
			 Organization org1 = organizationDao.getByNCIcode("WAKE");
			 assertEquals("Northwestern University",org1.getName());
			 assertNull(org1.getMergedOrganization());
			 
			 // create copy of existing org with same NCI code
			 Organization existingOrg = new LocalOrganization();
			 existingOrg.setNciInstituteCode("WAKE");
			 existingOrg.setName("Northwestern University");
			 
			 List<Organization> orgs = new ArrayList<Organization>();
			 
			 // add new organization
			 Organization newMergedOrg = new LocalOrganization();
			 newMergedOrg.setName("New Merged Org name");
			 newMergedOrg.setDescriptionText("New Merged Org name description");
			 newMergedOrg.setNciInstituteCode("Merged_NCI_Code");
			 newMergedOrg.setRetiredIndicator(true);
			 
			 newMergedOrg.setMergedOrganization(existingOrg);
			 
			 orgs.add(newMergedOrg);
			 
			 
			 Organization organization2 = new LocalOrganization();
			 organization2.setName("Organization 2");
			 organization2.setDescriptionText("Organization 2 description");
			 organization2.setNciInstituteCode("NCI02");
			 orgs.add(organization2);
			 
			List<ProcessingOutcome> entityErrors = organizationManagementService.mergeOrganizations(orgs);
			assertEquals(2,entityErrors.size());
			 
			 interruptSession();
			 assertEquals(3,organizationDao.getAll().size());
			 Organization mergedOrg = organizationDao.getByNCIcode("Merged_NCI_Code");
			 assertNull(mergedOrg);
		 }
	 
	 public void testSuccessfulMergeOrganization(){
			List<Organization> dbOrgs = organizationDao.getAll();
			 assertEquals(3,dbOrgs.size());
			 
			 Organization org1 = organizationDao.getByNCIcode("WAKE");
			 assertEquals("Northwestern University",org1.getName());
			 assertNull(org1.getMergedOrganization());
			 
			 // case 1: create copy of existing org with same NCI code
			 Organization existingOrg1 = new LocalOrganization();
			 existingOrg1.setNciInstituteCode("WAKE");
			 existingOrg1.setName("Northwestern University");
			 
			 List<Organization> orgs = new ArrayList<Organization>();
			 
			 // crate new merged organization and try to persist existing local org with new merged org
			 Organization newMergedOrg = new LocalOrganization();
			 newMergedOrg.setName("New Merged Org name");
			 newMergedOrg.setDescriptionText("New Merged Org name description");
			 newMergedOrg.setNciInstituteCode("Merged_NCI_Code");
			 newMergedOrg.setRetiredIndicator(false);
			 
			 existingOrg1.setMergedOrganization(newMergedOrg);
			 
			 orgs.add(existingOrg1);
			 
			 // Case 2. Merge Default org to an existing Org MAYO
			 Organization org2 = organizationDao.getByNCIcode("DEFAULT");
			 assertEquals("Duke University Comprehensive Cancer Center",org2.getName());
			 assertNull(org2.getMergedOrganization());
			 
			 
			 Organization existingOrg2 = new LocalOrganization();
			 existingOrg2.setName("Default");
			 existingOrg2.setNciInstituteCode("DEFAULT");
			 
			 // set existing org 3 as merged org
			 
			 Organization existingOrg3 = new LocalOrganization();
			 existingOrg3.setName("Mayo Clinic");
			 existingOrg3.setNciInstituteCode("MAYO");
			 existingOrg2.setMergedOrganization(existingOrg3);
			 
			 
			 orgs.add(existingOrg2);
			 
			List<ProcessingOutcome> entityErrors = organizationManagementService.mergeOrganizations(orgs);
			 assertEquals(2,entityErrors.size());
			 
			 interruptSession();
			 assertEquals(4,organizationDao.getAll().size());
			 
			 Organization reloadDefault= organizationDao.getByNCIcode("DEFAULT");
			 assertNotNull(reloadDefault.getMergedOrganization());
			 assertTrue(reloadDefault.getRetiredIndicator());
			 assertEquals("MAYO",reloadDefault.getMergedOrganization().getNciInstituteCode());
			 
			 Organization reloadWake= organizationDao.getByNCIcode("WAKE");
			 assertNotNull(reloadWake.getMergedOrganization());
			 assertTrue(reloadWake.getRetiredIndicator());
			 assertEquals("Merged_NCI_Code",reloadWake.getMergedOrganization().getNciInstituteCode());
		 }
}
