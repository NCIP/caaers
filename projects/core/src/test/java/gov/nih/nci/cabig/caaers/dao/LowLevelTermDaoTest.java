package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.List;

/**
 * @author Krikor Krumlian
 * 
 */
@CaaersUseCases( { MAPPING_VOCAB })
public class LowLevelTermDaoTest extends DaoTestCase<LowLevelTermDao> {

	private MeddraVersionDao meddraVersionDao = (MeddraVersionDao) getApplicationContext().getBean(
					  "meddraVersionDao");
	

    public void testGetById() throws Exception {
        LowLevelTerm llt = getDao().getById(12);
        assertNotNull("LowLevelTerm not found", llt);
    }

    public void testGetByMeddraCode() throws Exception {
        List<LowLevelTerm> llt = getDao().getByMeddraCode("123");
        assertNotNull("LowLevelTerm not found", llt.get(0));
    }

    public void testFailGetByMeddraCodePartial() throws Exception {
        List<LowLevelTerm> llt = getDao().getByMeddraCode("12");
        assertEquals("LowLevelTerm not found", 0, llt.size());
    }
 
    public void testGetByVersionSubnames() throws Exception {
    	String[] subnames = {"Abruption"};
    	List<LowLevelTerm> llt = getDao().getByVersionSubnames(1, subnames);
    	llt.get(0).getPreferredTerm();
    	assertNotNull("LowLevelTerm found", llt);
    }
    
    public void testLoadMeddraGetByVersionSubnames() throws Exception {
    	String[] subnames = {"%"};
    	List<MeddraVersion> meddraVersionList = meddraVersionDao.getAll();
    	assertNotNull("MeddraVersion not found", meddraVersionList);
    	List<LowLevelTerm> llt = getDao().getByVersionSubnames(1, subnames);
    	assertNotNull("LowLevelTerm not found", llt);
    }
    
    public void testGetByMeddraCodeandVersion(){
    	List<LowLevelTerm> llt = getDao().getByMeddraCodeandVersion("-123", 1);
    	assertNotNull(llt);
    	assertEquals(1, llt.size());
    	assertEquals("Test Abruption", llt.get(0).getPreferredTerm().getMeddraTerm());
    }
    
    public void testGetBySubnames(){
    	String[] subnames = {"Abruption"};
    	List<LowLevelTerm> llt = getDao().getBySubnames(subnames);
    	assertNotNull(llt);
    }
    
    public void testGetAll(){
    	List<LowLevelTerm> llt = getDao().getAll();
    	assertNotNull(llt);
    	assertTrue(llt.size() > 0);
    }
}
