package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Investigator;

import java.util.Date;

/**
 * @author Kulasekaran
 */
public class InvestigatorDaoTest extends DaoTestCase<InvestigatorDao>{
   
    public void testGetById() throws Exception {
        Investigator investigator = getDao().getById(-100);    	
        assertNotNull("Investigator not found", investigator);
        assertEquals("Wrong last name", "Scott", investigator.getLastName());
        assertEquals("Wrong first name", "Dilbert", investigator.getFirstName());        
    }
        
    public void testSaveNewInvestigator() throws Exception {
        Integer savedId;
        {
        	Investigator investigator = new Investigator();
        	investigator.setFirstName("Jeff");
        	investigator.setLastName("Someone");
        	investigator.setGender("Male");
        	investigator.setDateOfBirth(new Date());
        	investigator.setEthnicity("ethnicity");
        	investigator.setRace("race");

            getDao().save(investigator);
            savedId = investigator.getId();
            assertNotNull("The saved investigator id", savedId);
        }

        interruptSession();

        {
        	Investigator loaded = getDao().getById(savedId);
            assertNotNull("Could not reload investigator id " + savedId, loaded);
            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
            assertEquals("Wrong gender", "Male", loaded.getGender());
        }
    }
}
