package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.event.ResearchStaffModificationEvent;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class ResearchStaffModificationEventListener extends AbstractEventListener {
    @Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof ResearchStaffModificationEvent;
    }
}