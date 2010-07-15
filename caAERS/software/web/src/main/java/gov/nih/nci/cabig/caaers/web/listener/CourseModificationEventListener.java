package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.event.StudyModificationEvent;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class CourseModificationEventListener extends AbstractEventListener {
    @Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof StudyModificationEvent;
    }
}