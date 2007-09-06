package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/**
 * @author Saurabh Agrawal
 * 
 */
public class ParticipantQueryTest extends TestCase {

	public void testQueryConstructor() throws Exception {
		ParticipantQuery participantQuery = new ParticipantQuery();
		assertEquals("wrong parsing for constructor",
				"SELECT distinct p from Participant p left join fetch p.identifiersInternal order by p.id",
				participantQuery.getQueryString());

	}

	public void testFilterByName() throws Exception {
		ParticipantQuery participantQuery = new ParticipantQuery();
		participantQuery.filterByFirstName("a");
		assertEquals(
				"SELECT distinct p from Participant p left join fetch p.identifiersInternal WHERE lower(p.firstName) LIKE :firstName order by p.id",
				participantQuery.getQueryString());
		assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey("firstName"));
		assertEquals("wrong parameter value", participantQuery.getParameterMap().get("firstName"), "%a%");

		participantQuery.filterByLastName("b");
		assertEquals(
				"SELECT distinct p from Participant p left join fetch p.identifiersInternal WHERE lower(p.firstName) LIKE :firstName AND lower(p.lastName) LIKE :lastName order by p.id",
				participantQuery.getQueryString());

		assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 2);
		assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey("lastName"));
		assertEquals("wrong parameter value", participantQuery.getParameterMap().get("lastName"), "%b%");

	}

	public void testFilterByIdentifier() throws Exception {
		ParticipantQuery participantQuery = new ParticipantQuery();
		participantQuery.filterByIdentifierValue("a");
		assertEquals(
				"SELECT distinct p from Participant p left join fetch p.identifiersInternal WHERE lower(p.identifiersInternal.value) LIKE :identifier order by p.id",
				participantQuery.getQueryString());
		assertEquals("wrong number of parameters", participantQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", participantQuery.getParameterMap().containsKey("identifier"));
		assertEquals("wrong parameter value", participantQuery.getParameterMap().get("identifier"), "%a%");

	}

}