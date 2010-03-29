package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class OrganizationFromStudySiteQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();

        assertEquals("wrong parsing for constructor",
                        "SELECT distinct ss.organization from StudySite ss order by ss.organization.name", query.getQueryString());

    }
    
    public void testFilterByOrganizationName() throws Exception{
    	OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();
    	query.filterByOrganizationName("test");
    	assertEquals("wrong query generated", "SELECT distinct ss.organization from StudySite ss WHERE lower(name) LIKE :name order by ss.organization.name", query.getQueryString());
    	assertEquals("Incorrect number of parameters", 1, query.getParameterMap().size());
    }
    
    public void testFilterByStudy() throws Exception{
    	OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();
    	query.filterByStudy(3);
    	assertEquals("Incorrect query generated", "SELECT distinct ss.organization from StudySite ss WHERE ss.study.id = :STUDY_ID order by ss.organization.name", query.getQueryString());
    	assertEquals("Incorrect number of parameters", 1, query.getParameterMap().size());
    }
}