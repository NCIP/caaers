package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class ParticipantQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();

        assertEquals("wrong parsing for constructor",
                        "SELECT distinct p from Participant p order by p.id", participantQuery
                                        .getQueryString());

    }

    public void testFilterByName() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers");
        participantQuery.filterByFirstName("a");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE lower(p.firstName) LIKE :firstName order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "firstName"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("firstName"),
                        "%a%");

        participantQuery.filterByLastName("b");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE lower(p.firstName) LIKE :firstName AND lower(p.lastName) LIKE :lastName order by p.id",
                        participantQuery.getQueryString());

        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "lastName"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("lastName"),
                        "%b%");

    }

    public void testFilterByIdentifierValue() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers");
        participantQuery.filterByIdentifierValue("a");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE lower(p.identifiers.value) LIKE :identifier order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "identifier"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("identifier"),
                        "%a%");

    }
    
    public void testFilterByIdentifierValueExactMatch() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers");
        participantQuery.filterByIdentifierValueExactMatch("a");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE p.identifiers.value = :identifier order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "identifier"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("identifier"),
                        "a");

    }
    
    public void testFilterByIdentifierTypeExactMatch() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers");
        participantQuery.filterByIdentifierTypeExactMatch("a");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE p.identifiers.type = :type order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "type"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("type"),
                        "a");

    }

    public void testFilterByNotMachingStudySiteId() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.filterByFirstName("a");
        participantQuery.filterByNotMachingStudySiteId(Integer.valueOf(1));
        assertEquals(
                        "SELECT distinct p from Participant p WHERE lower(p.firstName) LIKE :firstName AND "
                                        + "p.id not in (select assignments.participant.id from  StudyParticipantAssignment assignments where assignments.studySite.id=:studySiteId) order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "firstName"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("firstName"),
                        "%a%");
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "studySiteId"));
        assertEquals("wrong parameter value",
                        participantQuery.getParameterMap().get("studySiteId"), Integer.valueOf(1));

    }
    
    public void testExcludeHavingGender() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers");
        participantQuery.filterByFirstName("a");
        participantQuery.excludeHavingGender("Male");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE lower(p.firstName) LIKE :firstName AND p.gender != :gender order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "firstName"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("firstName"),
                        "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("gender"),
        "Male");
    }
    
    public void testExcludeHavingRace() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers");
        participantQuery.filterByFirstName("a");
        participantQuery.excludeHavingRace("Asian");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE lower(p.firstName) LIKE :firstName AND p.race != :race order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "firstName"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("firstName"),
                        "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("race"),
        "Asian");
    }
    
    
    public void testExcludeHavingEthnicity() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers");
        participantQuery.filterByFirstName("a");
        participantQuery.excludeHavingEthnicity("Ethnicity");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers WHERE lower(p.firstName) LIKE :firstName AND p.ethnicity != :ethnicity order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "firstName"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("firstName"),
                        "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("ethnicity"),
        "Ethnicity");
    }

}