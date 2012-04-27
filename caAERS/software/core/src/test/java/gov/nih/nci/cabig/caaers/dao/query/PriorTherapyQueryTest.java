package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class PriorTherapyQueryTest extends TestCase {
    public void testFilterByMeddraCode() throws Exception {
        PriorTherapyQuery q = new PriorTherapyQuery();
        q.filterByMeddraCode("99");
        assertEquals("select p from PriorTherapy p WHERE p.meddraCode = :mc", q.getQueryString());
    }
}
