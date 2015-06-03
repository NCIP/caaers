/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 */
public class ParticipantQueryTest extends TestCase {

    public void testQueryConstructor() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();

        assertEquals("wrong parsing for constructor",
                        "SELECT distinct p from Participant p  order by p.id", participantQuery
                                        .getQueryString());

    }

    public void testFilterByName() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers identifier");
        participantQuery.filterByParticipantFirstName("a","like");
        assertEquals("SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE lower(p.firstName) like :param0  order by p.id", participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey("param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"), "%a%");

        participantQuery.filterByParticipantFirstName("b","like");
        assertEquals("SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE lower(p.firstName) like :param0 AND lower(p.firstName) like :param1  order by p.id", participantQuery.getQueryString());

        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"), "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param1"), "%b%");
    }

    public void testFilterByIdentifierValue() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers identifier");
        participantQuery.filterByIdentifierValue("a");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE lower(identifier.value) LIKE :param0  order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"),
                        "%a%");

    }
    
    public void testFilterByIdentifierValueExactMatch() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers identifier");
        participantQuery.filterByIdentifierValueExactMatch("a");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE identifier.value = :param0  order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"),
                        "a");

    }
    
    public void testFilterByIdentifierTypeExactMatch() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers identifier");
        participantQuery.filterByIdentifierTypeExactMatch("a");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE identifier.type = :param0  order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"),
                        "a");

    }

    public void testFilterByNotMachingStudySiteId() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.filterByParticipantFirstName("a","like");
        participantQuery.filterByNotMachingStudySiteId(Integer.valueOf(1));
        assertEquals(
                        "SELECT distinct p from Participant p  WHERE lower(p.firstName) like :param0 AND "
                                        + "p.id not in (select assignments.participant.id from  StudyParticipantAssignment assignments where assignments.studySite.id = :param1)  order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"),
                        "%a%");
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "param1"));
        assertEquals("wrong parameter value",
                        participantQuery.getParameterMap().get("param1"), Integer.valueOf(1));

    }
    
    public void testExcludeHavingGender() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers identifier");
        participantQuery.filterByParticipantFirstName("a","like");
        participantQuery.excludeHavingGender("Male");
        assertEquals(
                        "SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE lower(p.firstName) like :param0 AND p.gender != :param1  order by p.id",
                        participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"),
                        "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param1"),
        "Male");
    }
    
    public void testExcludeHavingRace() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers identifier");
        participantQuery.filterByParticipantFirstName("a","like");
        participantQuery.excludeHavingRace("Asian");
        assertEquals("SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE lower(p.firstName) like :param0 AND p.race != :param1  order by p.id", participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey("param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"), "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param1"), "Asian");
    }
    
    
    public void testExcludeHavingEthnicity() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetch("p.identifiers identifier");
        participantQuery.filterByParticipantFirstName("a","like");
        participantQuery.excludeHavingEthnicity("Ethnicity");
        assertEquals("SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE lower(p.firstName) like :param0 AND p.ethnicity != :param1  order by p.id", participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey(
                        "param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"),
                        "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param1"),
        "Ethnicity");
    }
    
    public void testLeftJoinFetchOnIdentifiers() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetchOnIdentifiers();
        participantQuery.filterByParticipantFirstName("a","like");
        participantQuery.excludeHavingEthnicity("Ethnicity");
        assertEquals("SELECT distinct p from Participant p left join fetch p.identifiers identifier  WHERE lower(p.firstName) like :param0 AND p.ethnicity != :param1  order by p.id", participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey("param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"), "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param1"), "Ethnicity");
    }
    
    public void testJoinOnIdentifiers() throws Exception {
        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.joinOnIdentifiers();
        participantQuery.filterByParticipantFirstName("a","like");
        participantQuery.excludeHavingEthnicity("Ethnicity");
        assertEquals("SELECT distinct p from Participant p join p.identifiers identifier  WHERE lower(p.firstName) like :param0 AND p.ethnicity != :param1  order by p.id", participantQuery.getQueryString());
        assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
        assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey("param0"));
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param0"), "%a%");
        assertEquals("wrong parameter value", participantQuery.getParameterMap().get("param1"), "Ethnicity");
    }

}
