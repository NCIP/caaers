/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
 * The Class ParticipantRepository.
 *
 * @author Biju Joseph
 */
@Transactional(readOnly = true)
public class ParticipantRepository {

    /** The participant dao. */
    private ParticipantDao participantDao;
    
    /** The log. */
    private static Log log = LogFactory.getLog(ParticipantRepository.class);

    /**
     * Checks if participant exist for given identifiers.
     *
     * @param identifiers the identifiers
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
     * @throws Exception the exception
     */
    public List<Participant> search(Participant participant) throws Exception {
        return participantDao.searchByExample(participant);
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
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

    /**
     * Sets the participant dao.
     *
     * @param participantDao the new participant dao
     */
    @Required
    public void setParticipantDao(final ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    /**
     * Gets the participant by id.
     *
     * @param id the id
     * @return the participant by id
     */
    public Participant getParticipantById(int id) {
        return participantDao.getParticipantById(id);
    }
}
