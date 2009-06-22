package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class ParticipantRepository {

    private ParticipantDao participantDao;

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
     * Search using a sample. Populate a Participant object
     *
     * @param participant object
     * @return List of Participant objects based on the sample participant object
     * @throws Runtime exception
     */
    public List<Participant> search(Participant participant) throws Exception {
        return participantDao.searchByExample(participant);
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
