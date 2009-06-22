/**
 *@author Biju Joseph
 */
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class StudyHavingStudySiteQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
        assertEquals("wrong parsing for constructor", "select distinct ss.study from StudySite ss",
                        query.getQueryString());

    }

    public void testFilterByStudySiteName() throws Exception {
        StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
        query.filterByStudySiteName("a");
        assertEquals(
                        "select distinct ss.study from StudySite ss WHERE lower(ss.organization.name) LIKE :organizationName",
                        query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                        "organizationName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("organizationName"),
                        "%a%");

        query.filterByStudyShortTile("b");
        assertEquals(
                        "select distinct ss.study from StudySite ss WHERE lower(ss.organization.name) LIKE :organizationName AND lower(ss.study.shortTitle) LIKE :shortTitle",
                        query.getQueryString());

        assertEquals("wrong number of parameters", query.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", query.getParameterMap().containsKey("shortTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("shortTitle"), "%b%");

    }

    public void testFilterByIdentifier() throws Exception {
        StudyHavingStudySiteQuery query = new StudyHavingStudySiteQuery();
        query.filterByIdentifierValue("a");
        assertEquals(
                        "select distinct ss.study from StudySite ss WHERE lower(ss.study.identifiers.value) LIKE :identifier",
                        query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", query.getParameterMap().containsKey("identifier"));
        assertEquals("wrong parameter value", query.getParameterMap().get("identifier"), "%a%");
        
        
        query = new StudyHavingStudySiteQuery();
        query.filterByIdentifierValueExactMatch("a");
        assertEquals(
                "select distinct ss.study from StudySite ss WHERE lower(ss.study.identifiers.value) LIKE :identifier",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", query.getParameterMap().containsKey("identifier"));
        assertEquals("wrong parameter value", query.getParameterMap().get("identifier"), "a");

    }
    
    

}
