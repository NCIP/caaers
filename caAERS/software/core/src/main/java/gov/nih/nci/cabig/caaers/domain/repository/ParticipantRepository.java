package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class ParticipantRepository {

    private ParticipantDao participantDao;
    private static Log log = LogFactory.getLog(ParticipantRepository.class);

    /**
     * Checks if participant exist for given identifiers
     *
     * @param identifiers
     * @return true, if any participant exists for given identifiers, false otherwise
     */
    public boolean checkIfParticipantExistsForGivenIdentifiers(List<Identifier> identifiers) {

        for (Identifier identifier : identifiers) {
            Participant tempParticipant = participantDao.getByIdentifier(identifier);
            if (tempParticipant != null) {
                return true;

            }
        }
        return false;
    }
    
    /**
     * Gets the participant by id. This initializes the participant and loads all the objects.
     *
     * @param identifier the id.
     * @return the participant by id.
     */
    public Participant getByIdentifier(final Identifier identifier) {
        return participantDao.getByIdentifier(identifier);
    }



    /**
     * Search using a sample. Populate a Participant object
     *
     * @param participant object
     * @return List of Participant objects based on the sample participant object
     * @throws Runtime exception
     */
    public List<Participant> search(Participant participant) throws Exception {
        return participantDao.searchByExample(participant);
    }

    public List<Participant> getAll() {
        ParticipantQuery q = new ParticipantQuery();
        q.joinStudy();
        List<Participant> participants = participantDao.searchParticipant(q);
        log.debug(String.format(">>> Found %d Participants", participants.size()));
        return participants;
    }

    public List<Participant> getAll(int firstrow, int maxrows) {
        ParticipantQuery q = new ParticipantQuery();
        q.joinStudy();
        List<Participant> participants = participantDao.searchParticipant(q, firstrow, maxrows);
        log.debug(String.format(">>> Found %d Participants", participants.size()));
        return participants;
    }

    /**
     * Search for participants using query.
     *
     * @param query The query used to search for participants
     * @return The list of participants.
     */
    public List<Participant> searchParticipant(final ParticipantQuery query) {
        return participantDao.searchParticipant(query);
    }

    @Required
    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public Participant getParticipantById(int id) {
        return participantDao.getParticipantById(id);
    }
}
