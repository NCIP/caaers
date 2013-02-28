/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.api.impl.SafetyReportServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
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
    public CaaersServiceResponse submitSafetyReport(@WebParam(name = "AdverseEventReport", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") AdverseEventReport xmlAdverseEventReport){
		try {
			return safetySvcImpl.submitSafetyReport(xmlAdverseEventReport);
		} catch (Exception e) {
            logger.error(e);
            CaaersServiceResponse caaersResponse = Helper.createResponse();
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage());
            return caaersResponse;
		}
		
    }

}
