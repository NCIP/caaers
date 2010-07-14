package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.acegisecurity.Authentication;

/**
 * Raised when Study is modified.
 * @author: Biju Joseph
 */
public class ReportModificationEvent extends EntityModificationEvent {

    public ReportModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.REPORT);
    }
}