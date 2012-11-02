package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class InvestigatorQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        assertEquals(
                "wrong parsing for constructor",
                "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si order by i.id",

                investigatorQuery.getQueryString());

    }

    public void testFilterByDifferentInvestigatorId() {
        InvestigatorQuery query = new InvestigatorQuery();
        assertEquals("SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si order by i.id", query.getQueryString());
        query.excludeHavingId(null);
        query.excludeHavingId(4);
        assertEquals("SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE  i.id != :iid  order by i.id", query.getQueryString());
    }

    public void testFilterByFirstName() throws Exception {
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        investigatorQuery.filterByFirstName("a");
        assertEquals("SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.firstName) LIKE :firstName  order by i.id", investigatorQuery.getQueryString());
        assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey("firstName"));
        assertEquals("wrong parameter value", investigatorQuery.getParameterMap().get("firstName"), "%a%");
        investigatorQuery.filterByLastName("b");
        assertEquals("SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.firstName) LIKE :firstName AND " + "lower(i.lastName) LIKE :lastName  order by i.id", investigatorQuery.getQueryString());
        assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey("lastName"));
        assertEquals("wrong parameter value", investigatorQuery.getParameterMap().get("lastName"), "%b%");
    }

    public void testFilterByEmailAddress() throws Exception {
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        investigatorQuery.filterByEmailAddress("a");
        assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey(
                "emailAddress"));
        assertEquals("wrong parameter value", "%a%", investigatorQuery.getParameterMap().get("emailAddress"));
        assertEquals("Incorrect query created",
                "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.emailAddress) LIKE :emailAddress  order by i.id",
                investigatorQuery.getQueryString());
    }

    public void testFilterByIdentifier() throws Exception {
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        investigatorQuery.filterByNciIdentifier("a");
        assertEquals(
                "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.nciIdentifier) LIKE :nciIdentifier  order by i.id",
                investigatorQuery.getQueryString());
        assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey(
                "nciIdentifier"));
        assertEquals("wrong parameter value", investigatorQuery.getParameterMap().get(
                "nciIdentifier"), "%a%");

    }


    public void testFilterByName() throws Exception {
        InvestigatorQuery q = new InvestigatorQuery();
        q.filterByName("Andrew");
        assertEquals("wrong number of parameters", q.getParameterMap().size(), 3);
        assertTrue("missing paramenter name" + q.getParameterMap(), q.getParameterMap().containsKey("FIRST_NAME_0"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("MIDDLE_NAME_0"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("LAST_NAME_0"));
        assertEquals("wrong parameter value", "%andrew%", q.getParameterMap().get("FIRST_NAME_0"));
        assertEquals("Incorrect query created", "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE (lower(i.firstName) LIKE :FIRST_NAME_0 OR lower(i.lastName) LIKE :LAST_NAME_0 OR lower(i.middleName) LIKE :MIDDLE_NAME_0)  order by i.id", q.getQueryString());
    }

    public void testFilterByFullName() throws Exception {
        InvestigatorQuery q = new InvestigatorQuery();
        q.filterByName("Ann Setser");
        assertEquals("wrong number of parameters", q.getParameterMap().size(), 6);

        assertTrue("missing paramenter name" + q.getParameterMap(), q.getParameterMap().containsKey("FIRST_NAME_0"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("MIDDLE_NAME_0"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("LAST_NAME_0"));
        assertEquals("wrong parameter value", "%ann%", q.getParameterMap().get("FIRST_NAME_0"));

        assertTrue("missing paramenter name" + q.getParameterMap(), q.getParameterMap().containsKey("FIRST_NAME_1"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("MIDDLE_NAME_1"));
        assertTrue("missing paramenter name", q.getParameterMap().containsKey("LAST_NAME_1"));
        assertEquals("wrong parameter value", "%setser%", q.getParameterMap().get("FIRST_NAME_1"));

        assertEquals("Incorrect query created", "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE (lower(i.firstName) LIKE :FIRST_NAME_0 OR lower(i.lastName) LIKE :LAST_NAME_0 OR lower(i.middleName) LIKE :MIDDLE_NAME_0) AND (lower(i.firstName) LIKE :FIRST_NAME_1 OR lower(i.lastName) LIKE :LAST_NAME_1 OR lower(i.middleName) LIKE :MIDDLE_NAME_1)  order by i.id", q.getQueryString());
    }

    public void testFilterByNullName() throws Exception {
        InvestigatorQuery q = new InvestigatorQuery();
        q.filterByName(null);
        assertEquals("wrong number of parameters", q.getParameterMap().size(), 0);
        assertEquals("Incorrect query created", "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si order by i.id", q.getQueryString());
    }

}