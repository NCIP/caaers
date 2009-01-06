/**
 * 
 */
package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.List;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
@CaaersUseCases( { STUDY_ABSTRACTION })
public class OrganizationDaoTest extends DaoNoSecurityTestCase<OrganizationDao> {

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

    public void testGetAll() throws Exception {
        List<Organization> orgs = getDao().getAll();
        assertEquals(4, orgs.size());
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
    
    public void testGetByNCIcode(){
    	Organization org = getDao().getByNCIcode("RTOG");
    	assertNotNull(org);
    	assertEquals("Radiation Therapy Oncology Group",org.getName());
    }
    
    public void testGetOrganizationsHavingStudySites(){
    	List<Organization> orgs = getDao().getOrganizationsHavingStudySites();
    	assertEquals(0, orgs.size());
    }
    
    public void testSearchOrganization(){
        OrganizationQuery orgQuery = new OrganizationQuery();
        orgQuery.filterByNciCodeExactMatch("RTOG");
        List<Organization> orgList = getDao().searchOrganization(orgQuery);
        assertEquals(1, orgList.size());
    }
    
    public void testRestrictBySubNames(){
    	List<Organization> orgs = getDao().restrictBySubnames(new String[] { "nothing" });
    	assertEquals(0,orgs.size());
    }
    
    public void testGetBySubNames(){
    	List<Organization> orgs = getDao().getBySubnames(new String[] { "nothing" });
    	assertEquals(0,orgs.size());
    }

}
