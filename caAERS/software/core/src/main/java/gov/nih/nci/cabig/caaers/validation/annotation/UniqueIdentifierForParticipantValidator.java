package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by IntelliJ IDEA. User: admin User: Biju Joseph
 * 
 * Checks whether already a Participant with the given identifier exist.
 */
public class UniqueIdentifierForParticipantValidator implements Validator<UniqueIdentifierForParticipant> {

    Log logger = LogFactory.getLog(UniqueIdentifierForParticipantValidator.class);

    String message;

    private ParticipantDao participantDao;

    /**
     * Will check in the DB whether there exist another participant with the same ID.
     */
    public boolean validate(final Object value) {
        // is value null ?
        if (value == null) return true;

        // is value not an Identifier instance ?
        if (!(value instanceof Identifier)) return true;
        Identifier other = (Identifier) value;

        ParticipantQuery participantQuery = new ParticipantQuery();
        participantQuery.leftJoinFetchOnIdentifiers();

        if (other.getValue() != null) participantQuery.filterByIdentifierValueExactMatch(other
                        .getValue());
        if (other.getType() != null) participantQuery.filterByIdentifierTypeExactMatch(other
                        .getType());

        List<Participant> participants = participantDao.searchParticipant(participantQuery);

        // there exist another participant with the same identifier ?.
        if (participants == null || participants.size() <= 0) {
            return true;
        }

        for (Participant p : participants) {
            for (Identifier identifier : p.getIdentifiers()) {
                if (StringUtils.equals(other.getValue(), identifier.getValue())
                                && StringUtils.equals(other.getType(), identifier.getType())
                                && !ObjectUtils.equals(other.getId(), identifier.getId())) return false;
            }
        }
        return true;

    }

    public void initialize(UniqueIdentifierForParticipant parameters) {
        message = parameters.message();
    }

    public String message() {
        return message;
    }

    @Required
    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }
}