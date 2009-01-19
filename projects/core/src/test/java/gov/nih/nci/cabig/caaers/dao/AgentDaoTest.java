package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Agent;

import java.util.List;

/**
 * @author Sameer Sawant
 */

@CaaersUseCases( { MAPPING_VOCAB })
public class AgentDaoTest extends DaoTestCase<AgentDao> {
	
	public void testGetById() throws Exception {
        Agent loaded = getDao().getById(1002);
        assertNotNull(loaded);
        assertEquals("Wrong id", 1002, (int) loaded.getId());
    }
	
	public void testGetAll() throws Exception {
		List<Agent> loadedList = getDao().getAll();
		assertNotNull(loadedList);
		assertEquals("Wrong number of agents fetched by getAll()", 3, loadedList.size());
	}
	
	public void testGetBySubnames() throws Exception {
		List<Agent> loadedList = getDao().getBySubnames(new String[] { "agent" });
		assertNotNull(loadedList);
		assertEquals("Wrong number of agents fetched by getBySubnames()", 3, loadedList.size());
		
		loadedList.clear();
		loadedList = getDao().getBySubnames(new String[] { "one" });
		assertNotNull(loadedList);
		assertEquals("Wrong number of agents fetched by getBySubnames()", 1, loadedList.size());
	}
	
	public void testGetByName() throws Exception {
		Agent loaded = getDao().getByName("agent one");
		assertNotNull(loaded);
		assertEquals("Wrong agent fetched by getByName()", 1001, (int) loaded.getId());
	}
	
	public void testGetByNscNumber() throws Exception {
		Agent loaded = getDao().getByNscNumber("7000002");
		assertNotNull(loaded);
		assertEquals("Wrong agent fetched by getByNscNumber()", 1002, (int) loaded.getId());
	}
}
