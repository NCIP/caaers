package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class SiteResearchStaffQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        SiteResearchStaffQuery siteReserachStaffQuery = new SiteResearchStaffQuery();
        assertEquals("wrong parsing for constructor", "SELECT...", siteReserachStaffQuery.getQueryString());
    }

    public void testFilterByFirstName() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.filterByFirstName("THIS_IS_A_FIRST_NAME");
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey("firstName"));
        assertEquals("wrong parameter value", "%THIS_IS_A_FIRST_NAME%", researchStaffQuery.getParameterMap().get("firstName"));
        assertEquals("Incorrect query created","SELECT ...",researchStaffQuery.getQueryString());
    }

}