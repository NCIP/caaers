/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import java.util.List;

import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.api.impl.SafetyReportServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BaseAdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.ws.SafetyReportManagementService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author: Biju Joseph
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.SafetyReportManagementService",
        serviceName="SafetyReportManagementService", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)

public class SafetyReportManagementServiceImpl implements SafetyReportManagementService {
	
	private static Log logger = LogFactory.getLog(SafetyReportManagementServiceImpl.class);
	
	private SafetyReportServiceImpl safetySvcImpl;
	
	public SafetyReportServiceImpl getSafetySvcImpl() {
		return safetySvcImpl;
	}

	public void setSafetySvcImpl(SafetyReportServiceImpl safetySvcImpl) {
		this.safetySvcImpl = safetySvcImpl;
	}
	
	@WebMethod
    public CaaersServiceResponse initiateSafetyReportAction(@WebParam(name = "AdverseEventReport", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") BaseAdverseEventReport xmlAdverseEventReport){
		try {
			return safetySvcImpl.initiateSafetyReportAction(xmlAdverseEventReport);
		} catch (Exception e) {
            logger.error(e);
            CaaersServiceResponse caaersResponse = Helper.createResponse();
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage());
            return caaersResponse;
		}
		
    }
	
	private String getErrors(List<WsError> list) {
		if(list == null) {
			return "Unknown Error";
		}
		if(list.size() == 1) {
			return list.get(0).getErrorDesc();
		}
		String val = list.size() + " Errors; ";
		for(WsError err : list) {
			val += err.getErrorDesc() +  "; ";
		}
		return val;
	}
	

	@WebMethod
    public CaaersServiceResponse submitSafetyReport(@WebParam(name = "AdverseEventReport", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") AdverseEventReport xmlAdverseEventReport){
		try {
			CaaersServiceResponse val = safetySvcImpl.submitSafetyReport(xmlAdverseEventReport);
			if ("1".equals(val.getServiceResponse().getResponsecode())) {
				throw new Exception(getErrors(val.getServiceResponse().getWsError()));
			}
				
			return val;
		} catch (Exception e) {
            logger.error(e);
            CaaersServiceResponse caaersResponse = Helper.createResponse();
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage() + ".\n Error occured in " + Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.SYSTEM_NAME));
            return caaersResponse;
		}
		
    }
    @WebMethod
    public CaaersServiceResponse saveSafetyReport(@WebParam(name = "AdverseEventReport", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") AdverseEventReport xmlAdverseEventReport){
		try {
			CaaersServiceResponse val = safetySvcImpl.saveSafetyReport(xmlAdverseEventReport);
			if ("1".equals(val.getServiceResponse().getResponsecode())) {
				throw new Exception(getErrors(val.getServiceResponse().getWsError()));
			}
			
			return val;
		} catch (Exception e) {
            logger.error(e);
            CaaersServiceResponse caaersResponse = Helper.createResponse();
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage() + ".\n Error occured in " + Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.SYSTEM_NAME));
            return caaersResponse;
		}

    }

}
