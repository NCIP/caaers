package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * User: janakiram.gollapudi@semanticbits.com
 * Date: 5/5/15
 * */
public class AdverseEventQueryTest extends TestCase {
    AdverseEventQuery adverseEventQuery;

    protected void setUp() throws Exception {
        super.setUp();
        adverseEventQuery = new AdverseEventQuery(""){

        };
    }

    public void testFilterByAEAwarenessDate() throws Exception {
        adverseEventQuery.filterByAEAwarenessDate("04/01/2015", "=");
        assertEquals("select distinct ae,  from AdverseEvent ae  WHERE (year(ae.gradedDate) = '2015' AND month(ae.gradedDate) = '4' AND day(ae.gradedDate) = '1')", adverseEventQuery.getQueryString());
    }
    
    public void testFilterByRequiresReporting() {
    	adverseEventQuery.filterByRequiresReporting("true", "=");
    	assertEquals("select distinct ae,  from AdverseEvent ae  WHERE ae.requiresReporting = :param0", adverseEventQuery.getQueryString());
    	adverseEventQuery = new AdverseEventQuery(""){ };
    	adverseEventQuery.filterByRequiresReporting("false", "!=");
    	assertEquals("select distinct ae,  from AdverseEvent ae  WHERE ae.requiresReporting != :param0", adverseEventQuery.getQueryString());
    	adverseEventQuery = new AdverseEventQuery(""){ };
    	adverseEventQuery.filterByRequiresReporting("null", "=");
    	assertEquals("select distinct ae,  from AdverseEvent ae  WHERE ae.requiresReporting IS NULL", adverseEventQuery.getQueryString());
    	adverseEventQuery = new AdverseEventQuery(""){ };
    	adverseEventQuery.filterByRequiresReporting("null", "!=");
    	assertEquals("select distinct ae,  from AdverseEvent ae  WHERE ae.requiresReporting IS NOT NULL", adverseEventQuery.getQueryString());
    }

    public void testFilterByAECreatedDate() throws Exception {
        adverseEventQuery.filterByAECreatedDate("04/01/2015", "=");
        assertEquals("select distinct ae,  from AdverseEvent ae  WHERE (year(ae.createdDate) = '2015' AND month(ae.createdDate) = '4' AND day(ae.createdDate) = '1')", adverseEventQuery.getQueryString());
    }
}
