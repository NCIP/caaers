package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.acegisecurity.Authentication;

/**
 * Raised when Study is modified.
 * @author: Biju Joseph
 */
public class CourseModificationEvent extends EntityModificationEvent {

    public CourseModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.COURSE);
    }
}