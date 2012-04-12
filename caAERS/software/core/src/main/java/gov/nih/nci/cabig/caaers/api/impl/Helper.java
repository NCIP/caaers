package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.integration.schema.common.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: BJW7
 * Date: 4/11/12
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Helper {
    
    public static CaaersServiceResponse createResponse(){

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(Status.PROCESSED);
        serviceResponse.setResponsecode("0");
        serviceResponse.setWsError(new ArrayList<WsError>());
        CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        caaersServiceResponse.setServiceResponse(serviceResponse);
        return caaersServiceResponse;
    }
    
    public static CaaersServiceResponse createErrorResponse(String errorCode, String description){
        return populateError(createResponse(), errorCode, description);
    }
    
    public static CaaersServiceResponse populateError(CaaersServiceResponse response, String errorCode, String description){
        ServiceResponse serviceResponse = response.getServiceResponse();
        serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
        serviceResponse.setResponsecode("1");
        WsError error = new WsError();
        error.setErrorCode(errorCode);
        error.setErrorDesc(description);
        serviceResponse.getWsError().add(error);
        return response;
    }

    public static CaaersServiceResponse populateMessage(CaaersServiceResponse response, String message) {
        ServiceResponse serviceResponse = response.getServiceResponse();
        serviceResponse.setStatus(Status.PROCESSED);
        serviceResponse.setResponsecode("0");
        serviceResponse.setMessage(message);
        return response;
    }

    public static CaaersServiceResponse populateErrorOutcome(CaaersServiceResponse response, String businessId, String caaersId, String corelationId, List<String> messages){

        ServiceResponse serviceRespons = response.getServiceResponse();
        serviceRespons.setStatus(Status.FAILED_TO_PROCESS);
        serviceRespons.setResponsecode("1");

        EntityProcessingOutcomes msgs = new EntityProcessingOutcomes();
        EntityProcessingOutcomeType  msg = new EntityProcessingOutcomeType();
        msg.setBusinessIdentifier(businessId);
        msg.setCorrelationId(corelationId);
        msg.setDataBaseId(caaersId);
        msg.setKlassName("NA");
        for(String m : messages) msg.getMessage().add(m);
        msgs.getEntityProcessingOutcome().add(msg);
        serviceRespons.setEntityProcessingOutcomes(msgs);
        return response;
    }
    

}
