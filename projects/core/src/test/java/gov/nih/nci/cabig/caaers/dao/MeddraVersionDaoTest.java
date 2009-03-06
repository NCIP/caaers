package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;

import java.util.List;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases( { MAPPING_VOCAB })
public class MeddraVersionDaoTest extends DaoTestCase<MeddraVersionDao> {
    public void testGetV3() throws Exception {
        MeddraVersion v9 = getDao().getMeddraV9();
        assertNotNull("V9 not found", v9);
    }
    
    public void testGetAll() throws Exception {
    	List<MeddraVersion> versionList = getDao().getAll();
    	assertEquals("Incorrect number of MeddraVersions fetched by getAll method in the dao", 2, versionList.size());
    }
    
    public void testGetMeddraByName() throws Exception {
    	List<MeddraVersion> versionList = getDao().getMeddraByName("MedDRA v10");
    	assertEquals("Incorrect number of MeddraVersions fetched by getMeddraByName method in the dao", 1, versionList.size());
    }
    
    public void testSave() throws Exception{
    	MeddraVersion version = new MeddraVersion();
    	version.setId(11);
    	version.setName("MedDRA v11");
    	getDao().save(version);
    	// Interrupt the session
    	interruptSession();
    	
    	MeddraVersion meddraVersion = getDao().getById(11);
    	assertNotNull("MeddraVersion not being saved by the dao's save method", meddraVersion);
    }
    
    public void testDelete() throws Exception{
    	MeddraVersion version = getDao().getById(9);
    	getDao().delete(version);
    	// Interrupt the session
    	interruptSession();
    	
    	MeddraVersion meddraVersion = getDao().getById(9);
    	assertNull("MeddraVersion not being deleted by the dao's delete method", meddraVersion);
    }
}
