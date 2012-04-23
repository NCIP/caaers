/**
 * 
 */
package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

import java.util.List;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * @author Ion C. Olaru
 */
public interface StudyService {

    /**
     * Assigns a Participant to a Study at a particular Site. The Study and Site must already exist
     * and be associated.
     * 
     * @param study
     * @param participant
     * @param organization
     * @return StudyParticipantAssignment for the Participant
     */
    StudyParticipantAssignment assignParticipant(Study study, Participant participant, Organization organization, String registrationGridId);

    /**
     * Build a hash usable key for the Study based on FSIdentifier value and ShortTitle
     * @param s Study
     * @return hash key
     */
    String getStudyKey(Study s);

    /**
     * Determines which studies from the adEERS list are to be imported as new (not present in caAERS)
     * or otherwise updated.
     * @param adEERSStudies List of adEERS studies
     * @param caAERSStudies  List of caAERS studies
     */
    void searchAdEERSStudiesInCaAERS(List<Study> adEERSStudies, List<Study> caAERSStudies);
}
