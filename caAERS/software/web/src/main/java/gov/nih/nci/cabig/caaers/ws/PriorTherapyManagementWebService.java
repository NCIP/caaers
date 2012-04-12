package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapies;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Agent Creation and Update.
 * @author Ramakrishna
 *
 */

@WebService(name="PriorTherapyServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
public interface PriorTherapyManagementWebService {
	
	/**
	 * This operation will accept jaxb Agents and either saves or updates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlPriorTherapies
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse importPriorTherapies(@WebParam(name="PriorTherapies",
            targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common") PriorTherapies xmlPriorTherapies) throws SecurityExceptionFaultMessage;

}
