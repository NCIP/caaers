package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import java.util.List;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class PreExistingConditionDaoTest extends DaoTestCase<PreExistingConditionDao> {
    public void testGetById() throws Exception {
        PreExistingCondition loaded = getDao().getById(3001);
        assertNotNull(loaded);
        assertEquals("Correct id", 3001, (int) loaded.getId());
    }

    public void testGetBySubnamesText() throws Exception {
        List<PreExistingCondition> matches = getDao().getBySubnames(new String[] { "anem" });
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", 3001, (int) matches.get(0).getId());
    }

    public void testGetBySubnamesMeddraLlt() throws Exception {
        List<PreExistingCondition> matches = getDao().getBySubnames(new String[] { "haemoltic" });
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", 3001, (int) matches.get(0).getId());
    }
    
    public void testGetAll(){
    	List<PreExistingCondition> conds = getDao().getAll();
    	assertTrue(conds != null);
    	assertFalse(conds.isEmpty());
    	assertEquals(3, conds.size());
    	assertEquals("Anemia", conds.get(0).getText());
    	assertEquals("Jnemia" , conds.get(2).getText());
    }

}
