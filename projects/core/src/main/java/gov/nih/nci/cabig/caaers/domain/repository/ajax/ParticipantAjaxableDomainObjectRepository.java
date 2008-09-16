package gov.nih.nci.cabig.caaers.domain.repository.ajax;

import gov.nih.nci.cabig.caaers.dao.query.ajax.ParticipantAjaxableDomainObjectQuery;
import gov.nih.nci.cabig.caaers.domain.ajax.ParticipantAjaxableDomainObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */

@Transactional(readOnly = true)
public class ParticipantAjaxableDomainObjectRepository extends AbstractAjaxableDomainObjectRepository {
    public List<ParticipantAjaxableDomainObject> findParticipants(ParticipantAjaxableDomainObjectQuery query) {
        List<Object[]> objects = super.find(query);
        List<ParticipantAjaxableDomainObject> participantAjaxableDomainObjects = new ArrayList<ParticipantAjaxableDomainObject>();

        for (Object[] o : objects) {
            ParticipantAjaxableDomainObject participantAjaxableDomainObject = (ParticipantAjaxableDomainObject) getObjectById(participantAjaxableDomainObjects, (Integer) o[0]);

            if (participantAjaxableDomainObject == null) {
                participantAjaxableDomainObject = new ParticipantAjaxableDomainObject();
                participantAjaxableDomainObject.setId((Integer) o[0]);
                participantAjaxableDomainObject.setFirstName((String) o[1]);
                participantAjaxableDomainObject.setLastName((String) o[2]);
                if (o[4] != null && (Boolean) o[4]) {
                    participantAjaxableDomainObject.setPrimaryIdentifierValue((String) o[3]);
                }
                participantAjaxableDomainObjects.add(participantAjaxableDomainObject);

            } else if (participantAjaxableDomainObject != null && o[4] != null && (Boolean) o[4]) {
                participantAjaxableDomainObject.setPrimaryIdentifierValue((String) o[3]);
            }


        }
        return participantAjaxableDomainObjects;

    }


}