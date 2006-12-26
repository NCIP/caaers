package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class StudyDaoTest extends DaoTestCase<StudyDao>{

	
    public void testGet() throws Exception {
        Study loaded = (Study)getDao().getById(-2);
        assertNotNull("Study not found", loaded);
        assertEquals("Short Title", loaded.getShortTitle());
    }
    
    public void testGetAllStudies() throws Exception {
    	
    }
    	
	
    public void testSave() throws Exception {
        Integer savedId;
        {
            Study newStudy = new Study();
            newStudy.setShortTitle("Short Title Inserted");
            newStudy.setLongTitle("Long Title Inserted");            
            newStudy.setPrincipalInvestigatorCode("ICODE_101");
            newStudy.setPrincipalInvestigatorName("Investigator Name Inserted");
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setPrimarySponsorCode("SCODE_101");
            newStudy.setPrimarySponsorName("Sponsor Name Inserted");
            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            savedId = newStudy.getId();
        }

        interruptSession();

        {
            Study reloaded = (Study)getDao().getById(savedId);
            assertNotNull("Saved Study not found", reloaded);
            assertEquals("ICODE_101", reloaded.getPrincipalInvestigatorCode());
            assertEquals("Investigator Name Inserted", reloaded.getPrincipalInvestigatorName());
            assertEquals("SCODE_101", reloaded.getPrimarySponsorCode());
            assertEquals("Sponsor Name Inserted", reloaded.getPrimarySponsorName());
        }
    }
}
