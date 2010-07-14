package gov.nih.nci.cabig.caaers.accesscontrol.filter.query;

import gov.nih.nci.cabig.caaers.dao.query.SiteResearchStaffQuery;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * QuerySecurityFilterer Tester.
 *
 * @author Biju Joseph
 * @since <pre>07/13/2010</pre>
 * 
 */
public class QuerySecurityFiltererTest extends TestCase {

    SiteResearchStaffQuery q;
    QuerySecurityFilterer filterer;

    public QuerySecurityFiltererTest(String name) {
        super(name);


    }

    public void setUp() throws Exception {
        super.setUp();
        q = new SiteResearchStaffQuery();
        filterer = new QuerySecurityFilterer();

    }


    public void testApplyFilter(){
        
        filterer.setEntityName("SiteResearchStaff");
        filterer.setIndexAlias("rsIdx");
        filterer.setEntityAssociation("researchStaff");



        System.out.println(q.getQueryString());

        filterer.applyFilter(q);

       System.out.println(q.getQueryString());

        
        
    }
    public void tearDown() throws Exception {
        super.tearDown();
    }

    

}
