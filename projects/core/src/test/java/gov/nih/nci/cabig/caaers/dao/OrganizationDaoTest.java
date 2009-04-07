/**
 * 
 */
package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
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
		try{
			
			Organization localOrg = new LocalOrganization();
			localOrg.setName("Local Org");
			localOrg.setCity("Local City");
			localOrg.setState("Local State");
			localOrg.setCountry("Country");
			localOrg.setNciInstituteCode("Local Code");
			getDao().save(localOrg);
			
			fail("Expecting an exception control is not expected to hit this statement");
		}catch(Exception e){
			//TestCase Pass.
		}
	}
	
	public void testSaveRemoteOrg(){
		Organization remoteOrg = new RemoteOrganization();
		remoteOrg.setNciInstituteCode("Remote Code");
		remoteOrg.setExternalId("externalId");
		getDao().save(remoteOrg);
		Organization loaded = getDao().getByNCIcode("Remote Code");
	    assertNotNull("Organization not found", loaded);
	    assertEquals("Remote Code", loaded.getNciInstituteCode());
	}
	
	//REVISIT
	
//	public void testGetRemoteOrg(){
//		Organization remoteOrg = getDao().getByNCIcode("REMOTE_RTOG");
//		assertNotNull("Organization not found", remoteOrg);
//		assertEquals("REMOTE_RTOG", remoteOrg.getNciInstituteCode());
//	}
	
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
 
  //REVISIT
//    public void testGetAll() throws Exception {
//        List<Organization> orgs = getDao().getAll();
//        assertEquals(5, orgs.size());
//    }
    
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
    	List<Organization> orgs = getDao().getOrganizationsHavingStudySites();
    	assertEquals(0, orgs.size());
    }
    
    public void testGetBySubNames(){
    	List<Organization> orgs = getDao().getBySubnames(new String[] { "nothing" });
    	assertEquals(0,orgs.size());
    }
    
}
