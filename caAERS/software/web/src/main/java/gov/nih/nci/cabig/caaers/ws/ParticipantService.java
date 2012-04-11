package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.webservice.participant.Participants;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Webservice Interface for Participant Creation and Updating.
 * @author Monish Dombla
 * @author Ion C. Olaru
 *
 */
@WebService(name="ParticipantServiceInterface",targetNamespace="http://webservice.caaers.cabig.nci.nih.gov")
public interface ParticipantService {

	/**
	 * This operation will accept a Participant which is a jaxb study and creates it.
	 * @param xmlParticipants
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse createParticipant(@WebParam(name="Participants", targetNamespace="http://webservice.caaers.cabig.nci.nih.gov") Participants xmlParticipants);

	/**
	 * This operation will accept a Participant which is a jaxb Participant and updates it.
	 * @param xmlParticipants
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse updateParticipant(@WebParam(name="Participants", targetNamespace="http://webservice.caaers.cabig.nci.nih.gov") Participants xmlParticipants);
	
	/**
	 * This operation will accept a Participant which is a jaxb Participant and deletes it provided there are no reporting periods.
	 * @param xmlParticipants
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse deleteParticipant(@WebParam(name="Participants", targetNamespace="http://webservice.caaers.cabig.nci.nih.gov") Participants xmlParticipants);

}
