/**
 * 
 */
package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationFromStudySiteQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.ActiveInactiveStatus;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;

import java.util.List;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
@CaaersUseCases( { STUDY_ABSTRACTION })
public class OrganizationDaoTest extends DaoNoSecurityTestCase<OrganizationDao> {
	
	
	public void testSaveLocalOrg(){
		Organization localOrg = new LocalOrganization();
		localOrg.setName("Local Org");
		localOrg.setCity("Local City");
		localOrg.setState("Local State");
		localOrg.setCountry("Country");
		localOrg.setNciInstituteCode("Local Code");
		getDao().save(localOrg);
		
		interruptSession();
		
		localOrg = getDao().getByNCIcode("Local Code");
		assertNotNull(localOrg);
		assertEquals("Local Org", localOrg.getName());
		assertEquals("Country", localOrg.getCountry());
	}
	
	public void testSaveRemoteOrg(){
		Organization remoteOrg = new RemoteOrganization();
		remoteOrg.setName("my remote org");
		remoteOrg.setNciInstituteCode("Remote Code");
		remoteOrg.setExternalId("externalId");
		getDao().save(remoteOrg);
		Organization loaded = getDao().getByNCIcode("Remote Code");
	    assertNotNull("Organization not found", loaded);
	    assertEquals("Remote Code", loaded.getNciInstituteCode());
	}
	
	public void testGetRemoteOrg(){
		Organization remoteOrg = getDao().getByNCIcode("REMOTE_RTOG");
		assertNotNull("Organization not found", remoteOrg);
		assertEquals("REMOTE_RTOG", remoteOrg.getNciInstituteCode());
	}
	
    public void testGetByNCIcode(){
    	Organization org = getDao().getByNCIcode("RTOG");
    	assertNotNull(org);
    	assertEquals("Radiation Therapy Oncology Group",org.getName());
    }

    public void testGet() throws Exception {
        Organization loaded = getDao().getById(-1001);
        assertNotNull("Organization not found", loaded);
        assertEquals("New Site", loaded.getName());
    }

    public void testGetByGridId() throws Exception {
        Organization loaded = getDao().getByGridId("gridSite");
        assertNotNull("Organization not found", loaded);
        assertEquals("New Site", loaded.getName());
    }
    public void testGetByName() throws Exception {
        Organization loaded = getDao().getByName("New Site");
        assertNotNull("Organization not found", loaded);
        assertEquals("New Site", loaded.getName());
    }
 
    public void testInitializeAndLock(){
    	Organization org = getDao().getById(-1001);
    	getDao().initialize(org);
    	getDao().lock(org);
    }
    
    public void testGetDefaultOrganization(){
    	Organization org = getDao().getDefaultOrganization();
    	assertNotNull(org);
    }
    
    public void testGetOrganizationsHavingStudySites(){
    	OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();
    	List<Organization> orgs = getDao().getOrganizationsHavingStudySites(query);
    	assertEquals(1, orgs.size());
    }
    
    public void testGetBySubNames(){
 		String text = "nothing";
    	OrganizationQuery query = new OrganizationQuery();
    	query.filterByOrganizationNameOrNciCode(text);
    	List<Organization> orgs = getDao().getBySubnames(query);
    	assertEquals(0,orgs.size());
    }
    
    public void testGetApplicableStudySites(){
    	OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();
    	query.filterByOrganizationName("New");
    	List<Organization> orgs = getDao().getApplicableOrganizationsFromStudySites(query);
    	assertEquals(1, orgs.size());
    }
    
    public void testOrganizationStatus(){
    	Organization organization = getDao().getDefaultOrganization();
    	assertEquals(ActiveInactiveStatus.AC, organization.getStatus());
    }
    
    public void testDeActivateOrganizationStatus(){
    	Organization organization = getDao().getById(-1004);
    	assertEquals(ActiveInactiveStatus.AC, organization.getStatus());
    	
    	organization.setStatus(ActiveInactiveStatus.IN);
    	getDao().save(organization);
    	
    	interruptSession();
    	
    	Organization reloadedOrg = getDao().getById(-1004);
    	assertEquals(ActiveInactiveStatus.IN, reloadedOrg.getStatus());
    }
    
    public void testSaveMergedOrganization(){
    	
    	Organization org1 = getDao().getById(-1004);
    	assertNull(org1.getMergedOrganization());
    	
    	Organization org2 = getDao().getById(-1005);
    	org1.setMergedOrganization(org2);
    	getDao().save(org1);
    	
    	interruptSession();
    	
    	Organization reloadedOrg1 = getDao().getById(-1004);
    	assertNotNull(reloadedOrg1.getMergedOrganization());
    	assertEquals(org2, reloadedOrg1.getMergedOrganization());
    	assertEquals(new Integer(-1005), reloadedOrg1.getMergedOrganization().getId());
    }
    
    
}
