package gov.nih.nci.cabig.caaers.validation.annotation;

import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: Dec 14, 2007
 * Time: 10:44:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class UniqueIdentifierForParticipantValidator implements Validator<UniqueIdentifierForParticipant> {

    Log logger = LogFactory.getLog(UniqueIdentifierForParticipantValidator.class);

    String message;
    private ParticipantDao participantDao;

    public boolean validate(final Object value) {
        if (value instanceof Identifier) {

            ParticipantQuery participantQuery = new ParticipantQuery();

            participantQuery.leftJoinFetchOnIdentifiers();

            Identifier primaryIdentifier = (Identifier) value;
            //participantQuery.filterByIdentifierTypeExactMatch(primaryIdentifier.getType());

            participantQuery.filterByIdentifierValueExactMatch(primaryIdentifier.getValue());

            List<Participant> participants = participantDao.searchParticipant(participantQuery);

            if (participants != null && participants.size() > 0) {
                logger.info("found participants:" + participants.size());
                //check if the participants has the same identifier

                for (Participant p : participants) {

                    for (Identifier identifier : p.getIdentifiers()) {
                          //check for edit participant mode and create participant mode..
                        if (!(primaryIdentifier.getId()!=null && primaryIdentifier.getId().equals(identifier.getId()))
                                && identifier.getValue().equals(primaryIdentifier.getValue())) return false;

                    }

                }

            }


            return true;

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