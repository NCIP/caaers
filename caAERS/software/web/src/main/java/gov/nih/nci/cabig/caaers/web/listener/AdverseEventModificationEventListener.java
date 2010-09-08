package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.event.AdverseEventModificationEvent;

import org.springframework.context.ApplicationEvent;

public class AdverseEventModificationEventListener extends AbstractEventListener {

    @Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof AdverseEventModificationEvent;
    }
}