package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;

import org.acegisecurity.Authentication;

public class ExpeditedReportModificationEvent extends EntityModificationEvent {

    public ExpeditedReportModificationEvent(Authentication authentication, DomainObject entity) {
        super(authentication, entity, EventType.REPORT);
    }
}