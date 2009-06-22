package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;

import org.easymock.classextension.EasyMock;

/**
 * @author Biju Joseph
 */
public class ParticipantRepositoryTest extends AbstractTestCase {

    private ParticipantRepository participantRepository;
    private ParticipantDao participantDao;
    private Participant participant;
    private SystemAssignedIdentifier systemAssignedIdentifier;

    protected void setUp() throws Exception {
        participantRepository = new ParticipantRepository();
        participantDao = registerDaoMockFor(ParticipantDao.class);
        participantRepository.setParticipantDao(participantDao);
        participant = Fixtures.createParticipant("first", "last");
        systemAssignedIdentifier = Fixtures.createSystemAssignedIdentifier("value");
    }

    public void testCheckIfParticipantExistsForGivenIdentifiersForNoIdentifiers() {
        //first check for no identifiers
        boolean participantExists = participantRepository.
                checkIfParticipantExistsForGivenIdentifiers(participant.getIdentifiers());

        assertFalse("participant should not exists as there are no identifiers", participantExists);


    }

    public void testCheckIfParticipantExistsForGivenIdentifiersIfNoParticipantExits() {

        //check if participant does not exits
        participant.addIdentifier(systemAssignedIdentifier);
        EasyMock.expect(participantDao.getByIdentifier(systemAssignedIdentifier)).andReturn(null);
        replayMocks();
        boolean participantExists = participantRepository.
                checkIfParticipantExistsForGivenIdentifiers(participant.getIdentifiers());
        verifyMocks();
        assertFalse("participant should not exists as there is are participants for given identifiers", participantExists);


    }

    public void testCheckIfParticipantExistsForGivenIdentifiersIfParticipantDoesExits() {
       
        //check if participant  exits
        participant.addIdentifier(systemAssignedIdentifier);
        EasyMock.expect(participantDao.getByIdentifier(systemAssignedIdentifier)).andReturn(new Participant());
        replayMocks();
        boolean participantExists = participantRepository.
                checkIfParticipantExistsForGivenIdentifiers(participant.getIdentifiers());
        verifyMocks();
        assertTrue("participant should  exists as there  are participants for given identifiers", participantExists);

    }


}
