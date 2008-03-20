package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Required
    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }
}
