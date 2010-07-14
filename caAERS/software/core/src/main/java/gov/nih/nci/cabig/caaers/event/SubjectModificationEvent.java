package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.acegisecurity.Authentication;

/**
 * Raised when Study is modified.
 * @author: Biju Joseph
 */
public class SubjectModificationEvent extends EntityModificationEvent {

    public SubjectModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.SUBJECT);
    }
}