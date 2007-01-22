/**
 * 
 */
package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public interface StudyService {

    /**
     * Assigns a Participant to a Study at a particular Site.
     * The Study and Site must already exist and be associated.
     * 
     * @param study
     * @param participant
     * @param site
     * @return StudyParticipantAssignment for the Participant
     */
    StudyParticipantAssignment assignParticipant(Study study, Participant participant, Site site);

}
