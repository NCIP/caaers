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
        assertEquals("title", loaded.getShortTitle());
    }
    
    public void testGetAllStudies() throws Exception {
    	
    }
    	
	
    public void testSave() throws Exception {
        Integer savedId;
        {
            Study newStudy = new Study();
            newStudy.setPrincipalInvestigatorCode("investigatorCode");
            newStudy.setPrincipalInvestigatorName("investigatorName");
            newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
            newStudy.setPrimarySponsorCode("sponsorCode");
            newStudy.setPrimarySponsorName("sponsorName");
            getDao().save(newStudy);
            assertNotNull("No ID for newly saved study", newStudy.getId());
            savedId = newStudy.getId();
        }

        interruptSession();

        {
            Study reloaded = (Study)getDao().getById(savedId);
            assertNotNull("Saved Study not found", reloaded);
            assertEquals("investigatorCode", reloaded.getPrincipalInvestigatorCode());
            assertEquals("investigatorName", reloaded.getPrincipalInvestigatorName());
            assertEquals("sponsorCode", reloaded.getPrimarySponsorCode());
            assertEquals("sponsorName", reloaded.getPrimarySponsorName());
        }
    }
}
