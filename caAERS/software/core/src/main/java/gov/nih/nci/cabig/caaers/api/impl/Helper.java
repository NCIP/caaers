package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.integration.schema.common.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 */
public class Helper {
    
    public static CaaersServiceResponse createResponse(){
    	
    	EntityProcessingOutcomes entityProcessingOutcomes = new EntityProcessingOutcomes();
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setEntityProcessingOutcomes(entityProcessingOutcomes);
        serviceResponse.setStatus(Status.PROCESSED);
        serviceResponse.setResponsecode("0");
        serviceResponse.setWsError(new ArrayList<WsError>());
        CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        caaersServiceResponse.setServiceResponse(serviceResponse);
        
        return caaersServiceResponse;
    }


    public static EntityProcessingOutcomeType createProcessingOutcomeType(boolean failed, String klass, String businessId, String caaersId, String correlationId, List<String> messages){
        EntityProcessingOutcomeType  outcomeType = new EntityProcessingOutcomeType();
        outcomeType.setBusinessIdentifier(businessId);
        outcomeType.setCorrelationId(correlationId);
        outcomeType.setDataBaseId(caaersId);
        outcomeType.setKlassName(klass);
        outcomeType.setFailed(failed);
        if(messages != null) {
            for(String m : messages) outcomeType.getMessage().add(m);
        }
        return outcomeType;
    }


    public static CaaersServiceResponse populateError(CaaersServiceResponse response, String errorCode, String description){
        ServiceResponse serviceResponse = response.getServiceResponse();
        serviceResponse.setStatus(Status.FAILED_TO_PROCESS);
        serviceResponse.setResponsecode("1");
        WsError error = new WsError();
        error.setErrorCode(errorCode);
        error.setErrorDesc(description);
        error.setException(description);
        serviceResponse.getWsError().add(error);
        return response;
    }

    public static CaaersServiceResponse populateMessage(CaaersServiceResponse response, String message) {
        ServiceResponse serviceResponse = response.getServiceResponse();
        serviceResponse.setResponsecode("0");
        serviceResponse.setMessage(message);
        return response;
    }

    public static CaaersServiceResponse populateErrorOutcome(CaaersServiceResponse response, String businessId, String caaersId, String corelationId, List<String> messages){

        ServiceResponse serviceRespons = response.getServiceResponse();
        serviceRespons.setStatus(Status.FAILED_TO_PROCESS);
        serviceRespons.setResponsecode("1");

        EntityProcessingOutcomes entityProcessingOutcomes = serviceRespons.getEntityProcessingOutcomes();
        if(entityProcessingOutcomes == null){
            entityProcessingOutcomes = new EntityProcessingOutcomes();
            serviceRespons.setEntityProcessingOutcomes(entityProcessingOutcomes);
        }
        EntityProcessingOutcomeType entityProcessingOutcomeType = createProcessingOutcomeType(true, "NA",
                StringUtils.isEmpty(businessId) ? "UNKNOWN" : businessId, caaersId, corelationId, messages);
        entityProcessingOutcomes.getEntityProcessingOutcome().add(entityProcessingOutcomeType);

        return response;
    }

    
    public static CaaersServiceResponse populateProcessingOutcome(CaaersServiceResponse response, ProcessingOutcome outcome){
        EntityProcessingOutcomeType entityProcessingOutcomeType = createProcessingOutcomeType(outcome.isFailed(), outcome.getKlassName(),
                outcome.getBusinessId(), outcome.getCaaersDBId(), null, outcome.getMessages());
        response.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome().add(entityProcessingOutcomeType);
        if(outcome.isFailed()) {
            response.getServiceResponse().setStatus(Status.PARTIALLY_PROCESSED);
        } else{
            Status existingStatus = response.getServiceResponse().getStatus();
            if(existingStatus == null) response.getServiceResponse().setStatus(Status.PROCESSED);
        }
        return response;
    }

    public static ProcessingOutcome createOutcome(Class<?> c, String businessId,  boolean failed , String... notes){
        return createOutcome(c, businessId, null, failed, notes);
    }

    public static ProcessingOutcome createOutcome(Class<?> c, String businessId, String caaersId ,boolean failed , String... notes){
        ProcessingOutcome outcome = new ProcessingOutcome();
        outcome.setBusinessId(businessId);
        outcome.setKlassName(c.getName());
        outcome.setFailed(failed);
        outcome.setCaaersDBId(caaersId);
        for(String n : notes) outcome.getMessages().add(n);
        return outcome;
    }
}
