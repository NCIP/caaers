package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.acegisecurity.Authentication;

/**
 * Raised when Study is modified. 
 * @author: Biju Joseph
 */
public class StudyModificationEvent extends EntityModificationEvent {

    public StudyModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.STUDY);
    }
}
