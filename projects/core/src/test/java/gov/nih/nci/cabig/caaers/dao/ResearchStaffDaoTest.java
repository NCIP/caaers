package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

/**
 * @author Kulasekaran
 */
@CaaersUseCases( { CREATE_STUDY, STUDY_ABSTRACTION })
public class ResearchStaffDaoTest extends DaoNoSecurityTestCase<ResearchStaffDao> {
    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
                    "organizationDao");

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testGetById() throws Exception {
        ResearchStaff researchStaff = getDao().getById(-1000);
        assertNotNull("ResearchStaff not found", researchStaff);
        assertEquals("Wrong last name", "Gates", researchStaff.getLastName());
        assertEquals("Wrong first name", "Bill", researchStaff.getFirstName());
    }
    
    public void testGetByLoginId(){
    	ResearchStaff researchStaff = getDao().getByLoginId("abcd");
    	assertNotNull(researchStaff);
    	assertEquals("Bill", researchStaff.getFirstName());
    }

    public void testSaveNewResearchStaff() throws Exception {
        interruptSession();

        Integer savedId;
        {
            ResearchStaff researchStaff = new ResearchStaff();
            researchStaff.setFirstName("Jeff");
            researchStaff.setLastName("Someone");
            researchStaff.setEmailAddress("abc@def.com");
            researchStaff.setPhoneNumber("123-456-789");
            researchStaff.setNciIdentifier("nci id");

            researchStaff.setOrganization(organizationDao.getById(-1000));

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
