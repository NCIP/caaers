package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Biju Joseph
 */
public class ReconciliationReportQueryTest extends TestCase {
    ReconciliationReportQuery query;
    
    public void testQuery(){
        query = new ReconciliationReportQuery();
        Set<Integer> a = new HashSet<Integer>();
        a.add(1);
        a.add(2);
        query.filerByReportingPeriodId(a);
        String s = query.getQueryString();
        assertEquals("select rr from ReconciliationReport rr left join fetch rr.adverseEventReportingPeriod rp WHERE rp.id in(:rpIds)", s);
    }
}
