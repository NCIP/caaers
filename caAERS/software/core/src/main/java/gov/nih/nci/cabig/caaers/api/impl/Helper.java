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
        
        CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        caaersServiceResponse.setServiceResponse(serviceResponse);
        return caaersServiceResponse;
    }
    
    public static CaaersServiceResponse createErrorResponse(String errorCode, String description){
        return populateError(createResponse(), errorCode, description);
    }
    
    public static CaaersServiceResponse populateError(CaaersServiceResponse response, String errorCode, String description){
        ServiceResponse serviceRespons = response.getServiceResponse();
        serviceRespons.setStatus(Status.FAILED_TO_PROCESS);
        serviceRespons.setResponsecode("1");
        WsError error = new WsError();
        error.setErrorCode(errorCode);
        error.setErrorDesc(description);
        serviceRespons.getWsError().add(error);
        return response;
    }
    
    public static CaaersServiceResponse populateErrorOutcome(CaaersServiceResponse response, String businessId, String caaersId, String corelationId, String... messages){

        ServiceResponse serviceRespons = response.getServiceResponse();
        serviceRespons.setStatus(Status.FAILED_TO_PROCESS);
        serviceRespons.setResponsecode("1");

        EntityErrorMessages msgs = new EntityErrorMessages();
        EntityErrorMessageType  msg = new EntityErrorMessageType();
        msg.setBusinessIdentifier(businessId);
        msg.setCorrelationId(corelationId);
        msg.setDataBaseId(caaersId);
        msg.setKlassName("NA");
        for(String m : messages) msg.getMessage().add(m);
        msgs.getEntityErrorMessage().add(msg);
        serviceRespons.setEntityErrorMessages(msgs);
        return response;
    }
    

}
