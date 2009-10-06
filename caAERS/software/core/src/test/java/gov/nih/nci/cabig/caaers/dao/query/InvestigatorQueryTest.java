package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 * 
 */
public class InvestigatorQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        assertEquals(
                        "wrong parsing for constructor",
                        "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si order by i.id",
                        
                        investigatorQuery.getQueryString());

    }

    public void testFilterByName() throws Exception {
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        investigatorQuery.filterByFirstName("a");
        assertEquals(
                        "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.firstName) LIKE :firstName order by i.id",
                        investigatorQuery.getQueryString());
        assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey(
                        "firstName"));
        assertEquals("wrong parameter value", investigatorQuery.getParameterMap().get("firstName"),
                        "%a%");

        investigatorQuery.filterByLastName("b");
        assertEquals(
                        "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.firstName) LIKE :firstName AND "
                                        + "lower(i.lastName) LIKE :lastName order by i.id",
                        investigatorQuery.getQueryString());

        assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey(
                        "lastName"));
        assertEquals("wrong parameter value", investigatorQuery.getParameterMap().get("lastName"),
                        "%b%");

    }
    
    public void testFilterByEmailAddress() throws Exception{
    	InvestigatorQuery investigatorQuery = new InvestigatorQuery();
    	investigatorQuery.filterByEmailAddress("a");
    	assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 1);
    	assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey(
        "emailAddress"));
    	assertEquals("wrong parameter value", "%a%", investigatorQuery.getParameterMap().get("emailAddress"));
    	assertEquals("Incorrect query created",
                "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.emailAddress) LIKE :emailAddress order by i.id",
                investigatorQuery.getQueryString());
    }

    public void testFilterByIdentifier() throws Exception {
        InvestigatorQuery investigatorQuery = new InvestigatorQuery();
        investigatorQuery.filterByNciIdentifier("a");
        assertEquals(
                        "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si WHERE lower(i.nciIdentifier) LIKE :nciIdentifier order by i.id",
                        investigatorQuery.getQueryString());
        assertEquals("wrong number of parameters", investigatorQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", investigatorQuery.getParameterMap().containsKey(
                        "nciIdentifier"));
        assertEquals("wrong parameter value", investigatorQuery.getParameterMap().get(
                        "nciIdentifier"), "%a%");

    }

}