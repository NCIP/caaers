package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.event.ExpeditedReportModificationEvent;

import org.springframework.context.ApplicationEvent;

public class ExpeditedReportModificationEventListener extends AbstractEventListener {
    @Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof ExpeditedReportModificationEvent;
    }
}