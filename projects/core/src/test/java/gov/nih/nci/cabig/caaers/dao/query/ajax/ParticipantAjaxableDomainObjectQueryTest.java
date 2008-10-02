package gov.nih.nci.cabig.caaers.dao.query.ajax;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class ParticipantAjaxableDomainObjectQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        AbstractAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();

        assertEquals("wrong parsing for constructor",
                "Select participant.id,participant.firstName,participant.lastName,identifier.value,identifier.primaryIndicator from Participant participant left join participant.identifiers as identifier order by participant.firstName", query
                        .getQueryString());

    }

    public void testFilterByStudyId() throws Exception {
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterByStudy(1);

        assertEquals("Select participant.id,participant.firstName,participant.lastName,identifier.value,identifier.primaryIndicator from Participant participant " +
                "left join participant.identifiers as identifier " +
                "left join participant.assignments as spa join spa.studySite as ss join ss.study as study WHERE study.id =:studyId order by participant.firstName",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 1);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "studyId"));
        assertEquals("wrong parameter value", query.getParameterMap().get("studyId"),
                Integer.valueOf(1));


    }


    public void testParticipantsWithMatchingText() throws Exception {
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterParticipantsWithMatchingText("a");

        assertEquals("Select participant.id,participant.firstName,participant.lastName,identifier.value,identifier.primaryIndicator from Participant participant " +
                "left join participant.identifiers as identifier WHERE (lower(participant.firstName) LIKE :firstName or lower(participant.lastName) LIKE :lastName " +
                "or  lower(identifier.type) LIKE :type or lower(identifier.value) LIKE :identifierValue) order by participant.firstName",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 4);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "lastName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("lastName"),
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
                "firstName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
                "%a%");


    }

    public void testFilterByBothParticipantAndText() throws Exception {
        ParticipantAjaxableDomainObjectQuery query = new ParticipantAjaxableDomainObjectQuery();
        query.filterParticipantsWithMatchingText("a");
        query.filterByStudy(1);

        assertEquals("Select participant.id,participant.firstName,participant.lastName,identifier.value,identifier.primaryIndicator from Participant participant " +
                "left join participant.identifiers as identifier " +
                "left join participant.assignments as spa join spa.studySite as ss join ss.study as study " +
                "WHERE (lower(participant.firstName) LIKE :firstName or lower(participant.lastName) LIKE :lastName or  " +
                "lower(identifier.type) LIKE :type or lower(identifier.value) LIKE :identifierValue) " +
                "AND study.id =:studyId order by participant.firstName",
                query.getQueryString());
        assertEquals("wrong number of parameters", query.getParameterMap().size(), 5);

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "lastName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("lastName"),
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
                "firstName"));
        assertEquals("wrong parameter value", query.getParameterMap().get("firstName"),
                "%a%");

        assertTrue("missing paramenter name", query.getParameterMap().containsKey(
                "studyId"));
        assertEquals("wrong parameter value", query.getParameterMap().get("studyId"),
                Integer.valueOf(1));


    }


}