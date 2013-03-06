/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class SiteResearchStaffQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        SiteResearchStaffQuery siteReserachStaffQuery = new SiteResearchStaffQuery();
        assertEquals("wrong parsing for constructor", "SELECT distinct srs from SiteResearchStaff srs left join fetch srs.researchStaff rs left join fetch srs.organization org order by srs.id", siteReserachStaffQuery.getQueryString());
    }

    public void testFilterByFirstName() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.filterByFirstName("THIS_IS_A_FIRST_NAME");
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey("firstName"));
        assertEquals("wrong parameter value", "%this_is_a_first_name%", researchStaffQuery.getParameterMap().get("firstName"));
        assertEquals("Incorrect query created","SELECT distinct rs from ResearchStaff rs left join fetch rs.siteResearchStaffsInternal srs WHERE lower(rs.firstName) LIKE :firstName  order by rs.id",researchStaffQuery.getQueryString());
    }

    public void testFilterByName() throws Exception {
        SiteResearchStaffQuery q = new SiteResearchStaffQuery();
        q.filterByName("Andrew");
        assertEquals("wrong number of parameters", q.getParameterMap().size(), 3);
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("FIRST_NAME"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("MIDDLE_NAME"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("LAST_NAME"));
        assertEquals("wrong parameter value", "%andrew%", q.getParameterMap().get("FIRST_NAME"));
        assertEquals("Incorrect query created", "SELECT distinct srs from SiteResearchStaff srs left join fetch srs.researchStaff rs left join fetch srs.organization org WHERE (lower(rs.firstName) LIKE :FIRST_NAME OR lower(rs.lastName) LIKE :LAST_NAME OR lower(rs.middleName) LIKE :MIDDLE_NAME)  order by srs.id", q.getQueryString());
    }

}
