package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;

import java.util.List;

/**
 * 
 * @author sakkala
 * This is a Query API to query Adverse Events based on different Criterion
 *
 */
public interface AdverseEventQueryService {
		
	/**
	 * Search for Adverse Events for a given participant
	 * @param participant
	 * @return
	 */
	public List<AdverseEvent> getByParticipant(Participant participant) ;

	
	/**
	 * Search for Adverse Events for a given participant and adverseEvent
	 * @param participant
	 * @param adverseEvent
	 * @return
	 */

	public List<AdverseEvent> getByParticipant(Participant participant, AdverseEvent adverseEvent);

	/**
	 * Search for Adverse Events based on a given Study
	 * @param study
	 * @return
	 */
	public List<AdverseEvent> getByStudy(Study study);
	
	/**
	 * Search for Adverse Events based on a given Study and adverseEvent
	 * @param study
	 * @param adverseEvent
	 * @return
	 */	
	public List<AdverseEvent> getByStudy(Study study, AdverseEvent adverseEvent);

	/**
	 * Convert result AEs to XML
	 * @param adverseEvents
	 * @return
	 */
	public String getXML(List<AdverseEvent> adverseEvents) throws Exception ;
	
	//public List<AdverseEvent> getByStudyParticipantAssignment(StudyParticipantAssignment studyParticipantAssignment);
		


}
