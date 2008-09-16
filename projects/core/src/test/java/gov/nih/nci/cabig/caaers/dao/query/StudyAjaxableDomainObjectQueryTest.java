package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class StudyAjaxableDomainObjectQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        AbstractAjaxableDomainObjectQuery query = new StudyAjaxableDomainObjectQuery();

        assertEquals("wrong parsing for constructor",
                "Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator from Study study left join study.identifiers as identifier order by study.shortTitle", query
                        .getQueryString());

    }

    public void testFilterByParticipantId() throws Exception {
        StudyAjaxableDomainObjectQuery query = new StudyAjaxableDomainObjectQuery();
        query.filterByParticipant(1);

        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator from Study study left join study.identifiers as identifier " +
                "left join study.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p WHERE p.id =:participantId order by study.shortTitle",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "participantId"));
        assertEquals("wrong parameter value", query.getParameterMap().get("participantId"),
                Integer.valueOf(1));


    }


    public void testStudiesWithMatchingText() throws Exception {
        StudyAjaxableDomainObjectQuery query = new StudyAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("a");

        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator from Study study " +
                "left join study.identifiers as identifier WHERE (lower(study.shortTitle) LIKE :shortTitle or lower(study.longTitle) LIKE :longTitle " +
                "or  lower(identifier.type) LIKE :type or lower(identifier.value) LIKE :identifierValue) order by study.shortTitle",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 4);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "longTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("longTitle"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "type"));
        assertEquals("wrong parameter value", query.getParameterMap().get("type"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "identifierValue"));
        assertEquals("wrong parameter value", query.getParameterMap().get("identifierValue"),
                "%a%");
        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "shortTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("shortTitle"),
                "%a%");


    }

    public void testFilterByBothParticipantAndText() throws Exception {
        StudyAjaxableDomainObjectQuery query = new StudyAjaxableDomainObjectQuery();
        query.filterStudiesWithMatchingText("a");
        query.filterByParticipant(1);

        assertEquals("Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator from Study study " +
                "left join study.identifiers as identifier " +
                "left join study.studyOrganizations as ss " +
                "join ss.studyParticipantAssignments as spa " +
                "join spa.participant as p " +
                "WHERE (lower(study.shortTitle) LIKE :shortTitle or lower(study.longTitle) LIKE :longTitle or  " +
                "lower(identifier.type) LIKE :type or lower(identifier.value) LIKE :identifierValue) " +
                "AND p.id =:participantId order by study.shortTitle",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 5);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "longTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("longTitle"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "type"));
        assertEquals("wrong parameter value", query.getParameterMap().get("type"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "identifierValue"));
        assertEquals("wrong parameter value", query.getParameterMap().get("identifierValue"),
                "%a%");
        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "shortTitle"));
        assertEquals("wrong parameter value", query.getParameterMap().get("shortTitle"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "participantId"));
        assertEquals("wrong parameter value", query.getParameterMap().get("participantId"),
                Integer.valueOf(1));


    }


}