/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersFaultInfo;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomeType;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;
import gov.nih.nci.cabig.caaers.integration.schema.common.Fault;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;
import gov.nih.nci.cabig.caaers.ws.faults.CaaersFault;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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


    public static CaaersServiceResponse populateError(CaaersServiceResponse response, String errorCode, String desc){
        String description = desc == null ? "Processing failure" : desc;
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
        serviceResponse.setStatus(Status.PROCESSED);
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
    	ServiceResponse serviceRespons = response.getServiceResponse();
    	
    	EntityProcessingOutcomes entityProcessingOutcomes = serviceRespons.getEntityProcessingOutcomes();
        if(entityProcessingOutcomes == null){
            entityProcessingOutcomes = new EntityProcessingOutcomes();
            serviceRespons.setEntityProcessingOutcomes(entityProcessingOutcomes);
        }
    	EntityProcessingOutcomeType entityProcessingOutcomeType = createProcessingOutcomeType(outcome.isFailed(), outcome.getKlassName(),
        		StringUtils.isEmpty(outcome.getBusinessId()) ? "UNKNOWN" : outcome.getBusinessId(), outcome.getCaaersDBId(), null, outcome.getMessages());
    	entityProcessingOutcomes.getEntityProcessingOutcome().add(entityProcessingOutcomeType);
    	
        if(outcome.isFailed()) {
        	serviceRespons.setStatus(Status.PARTIALLY_PROCESSED);
        } else{
            Status existingStatus = serviceRespons.getStatus();
            if(existingStatus == null) serviceRespons.setStatus(Status.PROCESSED);
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
    
    public static String concatenateMessagesFromOutcome(DomainObjectImportOutcome<? extends MutableDomainObject> outcome){
    	StringBuffer sb = new StringBuffer();
    	for(Message msg:outcome.getMessages()){
    		sb.append(msg.getMessage());
    		sb.append(";");
    		sb.append("\n");
    	}
    	return sb.toString();
    }
    
    public static String toString(CaaersServiceResponse response){
        StringBuffer sb = new StringBuffer();
        sb.append(response.getServiceResponse().getStatus());
        sb.append(" Errors  {");
        for(WsError e : response.getServiceResponse().getWsError()){
            sb.append(e.getErrorCode()) .append(":").append(e.getErrorDesc());
        }
        sb.append("}");

        if(response.getServiceResponse().getEntityProcessingOutcomes() != null){
            sb.append(" Outcomes {");
            for(EntityProcessingOutcomeType o : response.getServiceResponse().getEntityProcessingOutcomes().getEntityProcessingOutcome()){
                   sb.append(" outcome : ").append(o.getMessage()).append("::").append(o.getDataBaseId()) ;
                   sb.append(",") ;
            }
            sb.append("}");
        }

        return sb.toString();
        
    }
    
    public static CaaersFault createCaaersFault(String message, String errorCode, String desc){        
        return createCaaersFault(message, errorCode, desc, null);
    }
    
    public static CaaersFault createCaaersFault(String message, String errorCode, String desc, String exception){     
    	CaaersFault caaersFault = new CaaersFault(message, new CaaersFaultInfo());
        populateCaaersFault(caaersFault, errorCode, desc, exception);
        return caaersFault;
    }
    
    public static void populateCaaersFault(CaaersFault caaersFault, String errorCode, String desc){        
        populateCaaersFault(caaersFault, errorCode, desc, null);
    }
    
    public static void populateCaaersFault(CaaersFault caaersFault, String errorCode, String desc, String exception){        
        List<Fault> faults = caaersFault.getFaultInfo().getFault();
        faults.add(createFault(errorCode, desc, exception));
    }
    
    public static Fault createFault(String errorCode, String desc, String exception){
    	String description = desc == null ? "Processing failure" : desc;
    	
    	Fault fault = new Fault();
    	fault.setCode(errorCode);
        fault.setMessage(description);
        fault.setException(description);
        
        return fault;
    }
}
