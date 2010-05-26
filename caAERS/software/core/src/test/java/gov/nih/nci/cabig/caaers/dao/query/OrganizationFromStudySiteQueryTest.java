package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class OrganizationFromStudySiteQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();

        assertEquals("wrong parsing for constructor",
                        "SELECT distinct organization from Organization organization join organization.studyOrganizations ss WHERE ss.class='SST'  order by organization.name", query.getQueryString());

    }
    
    public void testFilterByOrganizationName() throws Exception{
    	OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();
    	query.filterByOrganizationName("test");
    	assertEquals("wrong query generated", "SELECT distinct organization from Organization organization join organization.studyOrganizations ss WHERE ss.class='SST' AND lower(organization.name) LIKE :name  order by organization.name", query.getQueryString());
    	assertEquals("Incorrect number of parameters", 1, query.getParameterMap().size());
    }
    
    public void testFilterByStudy() throws Exception{
    	OrganizationFromStudySiteQuery query = new OrganizationFromStudySiteQuery();
    	query.filterByStudy(3);
    	assertEquals("Incorrect query generated", "SELECT distinct organization from Organization organization join organization.studyOrganizations ss WHERE ss.class='SST' AND ss.study.id = :STUDY_ID  order by organization.name", query.getQueryString());
    	assertEquals("Incorrect number of parameters", 1, query.getParameterMap().size());
    }
}