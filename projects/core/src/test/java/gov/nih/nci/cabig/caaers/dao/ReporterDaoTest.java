package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ContactMechanism;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Reporter;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Kulasekaran
 */
public class ReporterDaoTest extends DaoTestCase<ReporterDao>{
   
    public void testGetById() throws Exception {
    	Reporter reporter = getDao().getById(-100);    	
        assertNotNull("Reporter not found", reporter);
        assertEquals("Wrong last name", "Scott", reporter.getLastName());
        assertEquals("Wrong first name", "Dilbert", reporter.getFirstName());        
    }
        
   public void testSaveNewRepoter() throws Exception {
        Integer savedId;
        {
        	Reporter reporter = new Reporter();
        	reporter.setFirstName("Jeff");
        	reporter.setLastName("Someone");
        	ContactMechanism cm = new ContactMechanism();
        	cm.setType("email");
        	cm.setValue("abc1@abc.com");
        	ArrayList<ContactMechanism> arr = new ArrayList<ContactMechanism>();
        	arr.add(cm);
        	reporter.setContactMechanims(arr);
        	
            getDao().save(reporter);
            savedId = reporter.getId();
            assertNotNull("The saved repoter id", savedId);
        }
/*
        interruptSession();

        {
        	Investigator loaded = getDao().getById(savedId);
            assertNotNull("Could not reload investigator id " + savedId, loaded);
            assertEquals("Wrong firstname", "Jeff", loaded.getFirstName());
            assertEquals("Wrong lastname", "Someone", loaded.getLastName());
            assertEquals("Wrong gender", "Male", loaded.getGender());
        } */
    } 
}
