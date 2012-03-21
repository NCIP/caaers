package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.ParticipantService;
import gov.nih.nci.cabig.caaers.api.impl.ParticipantServiceImpl;
import gov.nih.nci.cabig.caaers.webservice.participant.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.participant.Participants;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Exposes the ParticipantManagementWebservie, and will delegate all the requests to ParticipantServiceImpl
 * @author Biju Joseph
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.ParticipantService",
        serviceName="ParticipantService",
        targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/participant")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)

public class ParticipantManagementWebService implements ParticipantService {
     private ParticipantServiceImpl impl;
    /**
     * This operation will accept a Participant which is a jaxb study and creates it.
     * @param xmlParticipants
     */
    @WebMethod
    public CaaersServiceResponse createParticipant(@WebParam(name="Participants",
            targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/participant") Participants xmlParticipants){
        return impl.createParticipant(xmlParticipants);
    }
    /**
     * This operation will accept a Participant which is a jaxb Participant and updates it.
     * @param xmlParticipants
     */

    @WebMethod
    public CaaersServiceResponse updateParticipant(@WebParam(name="Participants",
            targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/participant") Participants xmlParticipants){
        return impl.updateParticipant(xmlParticipants);
    }
    
    /**
     * This operation will delete a Participant which is a jaxb Participant provided there are no reporting periods.
     * @param xmlParticipants
     */

    @WebMethod
    public CaaersServiceResponse deleteParticipant(@WebParam(name="Participants",
            targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/participant") Participants xmlParticipants){
        return impl.deleteParticipant(xmlParticipants);
    }

    public ParticipantServiceImpl getImpl() {
        return impl;
    }

    public void setImpl(ParticipantServiceImpl impl) {
        this.impl = impl;
    }

}
