package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Condition;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

@CaaersUseCases( { MAPPING_VOCAB })
public class ConditionDaoTest extends DaoTestCase<ConditionDao> {
    public void testGetById() throws Exception {
        Condition condition = getDao().getById(-18);
        assertNotNull(condition);
        assertEquals("Wrong Object", -18, (int) condition.getId());
    }

    public void testSave() throws Exception {
        Condition condition = new Condition();
        condition.setConditionName("ABCD.");
        getDao().save(condition);
        assertEquals(true, condition.getId() > 0);
    }

    public void testGetAll() throws Exception {
        List<Condition> all = getDao().getAll();
        System.out.println();
    }
}