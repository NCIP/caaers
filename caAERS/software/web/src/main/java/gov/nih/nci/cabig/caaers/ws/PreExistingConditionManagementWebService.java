package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditions;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Agent Creation and Update.
 * @author Ramakrishna
 *
 */

@WebService(name="PreExistingConditionServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
public interface PreExistingConditionManagementWebService {
	
	/**
	 * This operation will accept jaxb Agents and either saves or updates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlPreExistingConditions
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse createOrUpdatePreExistingCondition(@WebParam(name="PreExistingConditions",
            targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common") PreExistingConditions xmlPreExistingConditions) throws SecurityExceptionFaultMessage;

}
