/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.event.StudyModificationEvent;
import gov.nih.nci.cabig.caaers.event.SubjectModificationEvent;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Biju Joseph
 */
public class SubjectModificationEventListener extends AbstractEventListener {
    @Override
    public boolean isSupported(ApplicationEvent event) {
        return event instanceof SubjectModificationEvent;
    }
}
