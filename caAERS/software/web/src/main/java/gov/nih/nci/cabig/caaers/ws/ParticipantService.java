/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;


import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantRef;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Webservice Interface for Participant Creation and Updating.
 * @author Monish Dombla
 * @author Ion C. Olaru
 *
 */
@WebService(name="ParticipantServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/participant")
public interface ParticipantService {

	/**
	 * This operation will accept a Participant which is a jaxb study and creates it.
	 * @param xmlParticipants
	 */
	@WebMethod
	public CaaersServiceResponse createParticipant(@WebParam(name="Participants", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/participant") Participants xmlParticipants);

	/**
	 * This operation will accept a Participant which is a jaxb Participant and updates it.
	 * @param xmlParticipants
	 */
	@WebMethod
	public CaaersServiceResponse updateParticipant(@WebParam(name="Participants", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/participant") Participants xmlParticipants);
	
	/**
	 * This operation will accept a Participant which is a jaxb Participant and deletes it provided there are no reporting periods.
	 * @param xmlParticipants
	 */
	@WebMethod
	public CaaersServiceResponse deleteParticipant(@WebParam(name="Participants", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/participant") Participants xmlParticipants);
	
	/**
	 * This operation will accept a Participant which is a jaxb Participant and deletes it provided there are no reporting periods.
	 * @param xmlParticipants
	 */
	@WebMethod
	public CaaersServiceResponse getParticipant(@WebParam(name="ParticipantRef", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/participant") ParticipantRef xmlParticipant);

}
