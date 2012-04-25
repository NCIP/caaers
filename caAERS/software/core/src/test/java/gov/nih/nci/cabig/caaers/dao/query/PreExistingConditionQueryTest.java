package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class PreExistingConditionQueryTest extends TestCase {
    public void testFilterByMeddraCode() throws Exception {
        PreExistingConditionQuery q = new PreExistingConditionQuery();
        q.filterByMeddraCode("99");
        assertEquals("SELECT p FROM PreExistingCondition p WHERE p.meddraCode = :mc", q.getQueryString());
    }
}
