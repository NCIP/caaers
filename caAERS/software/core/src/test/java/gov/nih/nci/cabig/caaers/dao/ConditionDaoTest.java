package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Condition;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

@CaaersUseCases( { MAPPING_VOCAB })
public class ConditionDaoTest extends DaoTestCase<ConditionDao> {

/*
* Test the loading by ID functionality
*
* */
    public void testGetById() throws Exception {
        Condition condition = getDao().getById(-18);
        assertNotNull(condition);
        assertEquals("Wrong Object", -18, (int) condition.getId());
    }

/*
* Test the save functionality of Conditions
*
* */

    public void testSave() throws Exception {
        Condition condition = new Condition();
        condition.setConditionName("ABCD.");
        condition.setGridId("");
        getDao().save(condition);
        assertEquals(true, condition.getId() > 0);
    }

/*
* Test the method of loading all Conditions from DB
*
* */
    public void testGetAll() throws Exception {
        List<Condition> all = getDao().getAll();
        assertEquals(3, all.size());
    }

/*
* Test the method of loading a Condition by the text contained in its name
*
* */
    public void testGetAllByText() throws Exception {
        List<Condition> all = getDao().getAllByText("18");
        assertEquals(1, all.size());
    }
}