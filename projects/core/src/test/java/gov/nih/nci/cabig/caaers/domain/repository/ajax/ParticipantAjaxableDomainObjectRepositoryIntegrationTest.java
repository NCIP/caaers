package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class ParticipantAjaxableDomainObjectRepositoryIntegrationTest extends CaaersDbNoSecurityTestCase {
    private ParticipantAjaxableDomainObjectQuery participantAjaxableDomainObjectQuery;

    private ParticipantAjaxableDomainObjectRepository participantAjaxableDomainObjectRepository = (ParticipantAjaxableDomainObjectRepository)
            getApplicationContext().getBean("participantAjaxableDomainObjectRepository");

    public void testMatchParticipantByStudy() throws Exception {
        participantAjaxableDomainObjectQuery = new ParticipantAjaxableDomainObjectQuery();
        participantAjaxableDomainObjectQuery.filterByStudy(-2000);
        participantAjaxableDomainObjectQuery.filterParticipantsWithMatchingText("sey");

        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(participantAjaxableDomainObjectQuery);

        assertNotNull(participantAjaxableDomainObjects);
        assertFalse(participantAjaxableDomainObjects.isEmpty());

        assertEquals("Wrong number of results", 1, participantAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Dilbert", participantAjaxableDomainObjects.get(0).getFirstName());
    }

    public void testMatchParticipantByStudyByIdentifier() throws Exception {

        participantAjaxableDomainObjectQuery = new ParticipantAjaxableDomainObjectQuery();
        participantAjaxableDomainObjectQuery.filterByStudy(-2000);
        participantAjaxableDomainObjectQuery.filterParticipantsWithMatchingText("13js77");

        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(participantAjaxableDomainObjectQuery);

        assertNotNull(participantAjaxableDomainObjects);
        assertFalse(participantAjaxableDomainObjects.isEmpty());

        // full identifier value
        assertEquals("Wrong number of results", 1, participantAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Dilbert", participantAjaxableDomainObjects.get(0).getFirstName());

        // partial identifier value
        participantAjaxableDomainObjectQuery = new ParticipantAjaxableDomainObjectQuery();
        participantAjaxableDomainObjectQuery.filterByStudy(-2000);
        participantAjaxableDomainObjectQuery.filterParticipantsWithMatchingText("13js");
        participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(participantAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 1, participantAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Dilbert", participantAjaxableDomainObjects.get(0).getFirstName());

        // partial identifier type
        participantAjaxableDomainObjectQuery = new ParticipantAjaxableDomainObjectQuery();
        participantAjaxableDomainObjectQuery.filterByStudy(-2000);
        participantAjaxableDomainObjectQuery.filterParticipantsWithMatchingText("cosey");
        participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(participantAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 1, participantAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Dilbert", participantAjaxableDomainObjects.get(0).getFirstName());
    }

    public void testGetBySubnamesJoinOnIdentifier() throws Exception {
        // full identifier value
        participantAjaxableDomainObjectQuery = new ParticipantAjaxableDomainObjectQuery();
        participantAjaxableDomainObjectQuery.filterParticipantsWithMatchingText("13js77");

        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(participantAjaxableDomainObjectQuery);
        assertEquals("Wrong number of results", 1, participantAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Dilbert", participantAjaxableDomainObjects.get(0).getFirstName());

        // firstName
        participantAjaxableDomainObjectQuery = new ParticipantAjaxableDomainObjectQuery();
        participantAjaxableDomainObjectQuery.filterParticipantsWithMatchingText("sey");

        participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(participantAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 1, participantAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Dilbert", participantAjaxableDomainObjects.get(0).getFirstName());

        // lastName
        participantAjaxableDomainObjectQuery = new ParticipantAjaxableDomainObjectQuery();
        participantAjaxableDomainObjectQuery.filterParticipantsWithMatchingText("cosey");

        participantAjaxableDomainObjects = participantAjaxableDomainObjectRepository.findParticipants(participantAjaxableDomainObjectQuery);

        assertEquals("Wrong number of results", 1, participantAjaxableDomainObjects.size());
        assertEquals("Wrong match", "Dilbert", participantAjaxableDomainObjects.get(0).getFirstName());

    }

}