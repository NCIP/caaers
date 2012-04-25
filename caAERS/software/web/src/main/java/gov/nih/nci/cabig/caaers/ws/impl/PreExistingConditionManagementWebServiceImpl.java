package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.PreExistingConditionManagementService;
import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditions;
import gov.nih.nci.cabig.caaers.ws.PreExistingConditionManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.PreExistingConditionManagementWebService", serviceName="PreExistingConditionManagementWebService", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class PreExistingConditionManagementWebServiceImpl implements PreExistingConditionManagementWebService{
	
	private static Log logger = LogFactory.getLog(PreExistingConditionManagementWebServiceImpl.class);
	private PreExistingConditionManagementService preExistingConditionLOVService;

	public void setPreExistingConditionLOVService(
			PreExistingConditionManagementService preExistingConditionLOVService) {
		this.preExistingConditionLOVService = preExistingConditionLOVService;
	}

	public CaaersServiceResponse createOrUpdatePreExistingCondition(PreExistingConditions xmlPreExistingConditions) throws SecurityExceptionFaultMessage {


		try {
			return preExistingConditionLOVService.importPreExistingConditions(xmlPreExistingConditions);
		} catch (Exception e) {
            logger.error(e);
            CaaersServiceResponse caaersResponse = Helper.createResponse();
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage());
            return caaersResponse;
		}
		

	}

}
