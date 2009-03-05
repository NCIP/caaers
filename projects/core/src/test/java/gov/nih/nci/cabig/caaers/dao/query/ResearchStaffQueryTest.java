package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class ResearchStaffQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        ResearchStaffQuery reserachStaffQuery = new ResearchStaffQuery();
        assertEquals(
                        "wrong parsing for constructor",
                        "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization order by rs.id",
                        reserachStaffQuery.getQueryString());

    }

    public void testFilterByTitle() throws Exception {
        ResearchStaffQuery reserachStaffQuery = new ResearchStaffQuery();
        reserachStaffQuery.filterByOrganizationName("a");
        assertEquals(
                        "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE lower(rs.organization.name) LIKE :name order by rs.id",
                        reserachStaffQuery.getQueryString());
        assertEquals("wrong number of parameters", reserachStaffQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", reserachStaffQuery.getParameterMap().containsKey(
                        "name"));
        assertEquals("wrong parameter value", "%a%", reserachStaffQuery.getParameterMap().get("name"));

    }
    
    public void testFilterByFirstName() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.filterByFirstName("a");
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey(
        "firstName"));
        assertEquals("wrong parameter value", "%a%", researchStaffQuery.getParameterMap().get("firstName"));
        assertEquals("Incorrect query created",
                "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE lower(rs.firstName) LIKE :firstName order by rs.id",
                researchStaffQuery.getQueryString());
    }
    
    public void testFilterByLastName() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.filterByLastName("a");
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey(
        "lastName"));
    	assertEquals("wrong parameter value", "%a%",researchStaffQuery.getParameterMap().get("lastName"));
    	assertEquals("Incorrect query created",
                "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE lower(rs.lastName) LIKE :lastName order by rs.id",
                researchStaffQuery.getQueryString());
    }
    
    public void testFilterByEmailAddress() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.filterByEmailAddress("a");
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey(
        "emailAddress"));
    	assertEquals("wrong parameter value", "%a%", researchStaffQuery.getParameterMap().get("emailAddress"));
    	assertEquals("Incorrect query created",
                "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE lower(rs.emailAddress) LIKE :emailAddress order by rs.id",
                researchStaffQuery.getQueryString());
    }
    
    public void testFilterByLoginId() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.filterByLoginId("a");
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey(
        "loginId"));
    	assertEquals("wrong parameter value", "%a%", researchStaffQuery.getParameterMap().get("loginId"));
    	assertEquals("Incorrect query created",
                "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE lower(rs.loginId) LIKE :loginId order by rs.id",
                researchStaffQuery.getQueryString());
    }
    
    public void testFilterByNciIdentifier() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.filterByNciIdentifier("a");
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey(
        "nciIdentifier"));
    	assertEquals("wrong parameter value", "%a%", researchStaffQuery.getParameterMap().get("nciIdentifier"));
    	assertEquals("Incorrect query created",
                "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE lower(rs.nciIdentifier) LIKE :nciIdentifier order by rs.id",
                researchStaffQuery.getQueryString());
    }
    
    public void testExcudeHavingId() throws Exception{
    	ResearchStaffQuery researchStaffQuery = new ResearchStaffQuery();
    	researchStaffQuery.excludeHavingId(1);
    	assertEquals("wrong number of parameters", researchStaffQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", researchStaffQuery.getParameterMap().containsKey(
        "rsId"));
    	assertEquals("wrong parameter value", 1, researchStaffQuery.getParameterMap().get("rsId"));
    	assertEquals("Incorrect query created",
                "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE rs.id != :rsId order by rs.id",
                researchStaffQuery.getQueryString());
    }
}
