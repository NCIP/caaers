package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.Date;

/**
 * @author Kulasekaran
 */
public class ResearchStaffDaoTest extends DaoTestCase<ResearchStaffDao>{
   
    public void testGetById() throws Exception {
    	ResearchStaff researchStaff = getDao().getById(-100);    	
        assertNotNull("ResearchStaff not found", researchStaff);
        assertEquals("Wrong last name", "Scott", researchStaff.getLastName());
        assertEquals("Wrong first name", "Dilbert", researchStaff.getFirstName());        
    }
        
    public void testSaveNewResearchStaff() throws Exception {
        Integer savedId;
        {
        	ResearchStaff researchStaff = new ResearchStaff();
        	researchStaff.setFirstName("Jeff");
        	researchStaff.setLastName("Someone");
        	researchStaff.setGender("Male");
        	researchStaff.setDateOfBirth(new Date());
        	researchStaff.setEthnicity("ethnicity");
        	researchStaff.setRace("race");

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
            assertEquals("Wrong gender", "Male", loaded.getGender());
        }
    }
}
