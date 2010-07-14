package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.acegisecurity.Authentication;

/**
 * Raised when Study is modified.
 * @author: Biju Joseph
 */
public class ResearchStaffModificationEvent extends EntityModificationEvent {

    public ResearchStaffModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.RESEARCH_STAFF);
    }
}