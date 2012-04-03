package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.webservice.participant.Participants;

public interface ParticipantService {
	
	/**
	 * This operation will accept a Participant which is a jaxb study and creates it.
	 * @param xmlParticipants
	 */
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse createParticipant(Participants xmlParticipants);
	
	
	/**
	 * This operation will accept a Participant which is a jaxb Participant and updates it.
	 * @param xmlParticipants
	 */
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse updateParticipant(Participants xmlParticipants);
	
	/**
	 * This operation will accept a Participant which is a jaxb Participant and deletes it provided there are no reporting periods.
	 * @param xmlParticipants
	 */
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse deleteParticipant(Participants xmlParticipants);


}
