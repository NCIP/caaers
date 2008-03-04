package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.ChemoAgent;

import java.util.List;

/**
 * @author Krikor Krumlian
 */

@CaaersUseCases( { MAPPING_VOCAB })
public class ChemoAgentDaoTest extends DaoTestCase<ChemoAgentDao> {
    public void testGetById() throws Exception {
        ChemoAgent loaded = getDao().getById(1002);
        assertNotNull(loaded);
        assertEquals("Wrong id", 1002, (int) loaded.getId());
    }

    public void testGetBySubnameMatchesTermSubstring() throws Exception {
        List<ChemoAgent> matches = getDao().getBySubname(new String[] { "test" });
        assertEquals("Wrong number of matches", 1, matches.size());
        assertEquals("Wrong match", 1001, (int) matches.get(0).getId());
    }
}
