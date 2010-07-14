package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.acegisecurity.Authentication;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class EntityModificationEvent extends ApplicationEvent {

    private EventType eventType;
    private Authentication authentication;
    

    public EntityModificationEvent(Authentication authentication, DomainObject entity, EventType eventType) {
        super(entity);
        this.authentication = authentication;
        this.eventType = eventType;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

   

    public DomainObject getEntity() {
        return (DomainObject)getSource();
    }



    public EventType getEventType() {
        return eventType;
    }


}
