package gov.nih.nci.cabig.caaers.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Webservice Interface for Participant Creation and Updation.
 * @author Monish Dombla
 *
 */
@WebService(name="ParticipantServiceIntf",targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/participant")
public interface ParticipantService {
	
	
	/**
	 * This operation will accept a Participant which is a jaxb study and creates it.
	 * @param xmlParticipant
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse createParticipant(@WebParam(name="Participants") gov.nih.nci.cabig.caaers.webservice.participant.Participants xmlParticipants);
	
	
	
	/**
	 * This operation will accept a Participant which is a jaxb Participant and updates it.
	 * @param xmlParticipant
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse updateParticipant(@WebParam(name="Participants") gov.nih.nci.cabig.caaers.webservice.participant.Participants xmlParticipants);

}
