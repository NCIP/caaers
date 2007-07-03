package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Organization;

/**
 * @author Kulasekaran
 */
public class ResearchStaffDaoTest extends DaoTestCase<ResearchStaffDao>{
   
	OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
			
    public void testGetById() throws Exception {
    	ResearchStaff researchStaff = getDao().getById(-1000);    	
        assertNotNull("ResearchStaff not found", researchStaff);
        assertEquals("Wrong last name", "Gates", researchStaff.getLastName());
        assertEquals("Wrong first name", "Bill", researchStaff.getFirstName());        
    }
        
    public void testSaveNewResearchStaff() throws Exception {
        Integer savedId;
        {
        	ResearchStaff researchStaff = new ResearchStaff();
        	researchStaff.setFirstName("Jeff");
        	researchStaff.setLastName("Someone");
        	
        	Organization organization = organizationDao.getById(-1000);
        	researchStaff.setOrganization(organization);
        	
            getDao().save(researchStaff);
            savedId = researchStaff.getId();
            assertNotNull("The saved researchStaff id", savedId);
        }

        interruptSession();

        {
        	ResearchStaff loaded = getDao().getById(savedId);
            assertNotNull("Could not reload researchStaff id " + savedId, loaded);
            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
        }
    }
}
