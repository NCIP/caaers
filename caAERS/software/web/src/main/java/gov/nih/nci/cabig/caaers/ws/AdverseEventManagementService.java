/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.manageae.AdverseEventsInputMessage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


/**
 * API to import adverse events to caAERS System. 
 * Adverse Events can be created in caAERS System via web service.
 * 
 * @author sakkala
 * @author Ion C. Olaru
 *
 */
@WebService(name="AdverseEventManagementServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/manageae")
public interface AdverseEventManagementService {
	/**
	 * Create Adverse Events for a Study Participant Assignment on a given Course/Cycle.<br/>
	 *  Refer to ManageAdverseEvents.xsd for schema definition.<br/>
	 * Few rules to enforce in implementation , return messages to client in CaaersServiceResponse.<br/>
	 *	1. Participant and Study should be existing in caAERS.<br/>
	 *	2. Adverse Event Management Service creates course if course is not present in caAERS System.  <br/>
     *	4. Start date of this course/cycle cannot be earlier than the Start date of first course/cycle.<br/>
     *	5. Course End date cannot be earlier than Start date.<br/>
     *	6. For Non-Baseline treatment type Start date cannot be equal to End date.<br/>
     *	7. Course/cycle cannot overlap with an existing course/cycle.<br/>
	 *	8. Baseline treatment type cannot start after an existing Non-Baseline treatment type.<br/>
	 *	9. Non-Baseline treatment type cannot start before an existing Baseline treatment type.		<br/>
	 * @param adverseEventsInputMessage
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public @WebResult(name="CaaersServiceResponse", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common") CaaersServiceResponse createAdverseEvent(@WebParam(name="AdverseEventsInputMessage", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/manageae") AdverseEventsInputMessage adverseEventsInputMessage) ;
	
	/**
	 * Create Provisional Adverse Events for a Study Participant Assignment on a given Course/Cycle.<br/>
	 *  Refer to ManageAdverseEvents.xsd for schema definition.<br/>
	 * Few rules to enforce in implementation , return messages to client in CaaersServiceResponse.<br/>
	 *	1. Participant and Study should be existing in caAERS.<br/>
	 *	2. Adverse Event Management Service creates course if course is not present in caAERS System.  <br/>
     *	4. Start date of this course/cycle cannot be earlier than the Start date of first course/cycle.<br/>
     *	5. Course End date cannot be earlier than Start date.<br/>
     *	6. For Non-Baseline treatment type Start date cannot be equal to End date.<br/>
     *	7. Course/cycle cannot overlap with an existing course/cycle.<br/>
	 *	8. Baseline treatment type cannot start after an existing Non-Baseline treatment type.<br/>
	 *	9. Non-Baseline treatment type cannot start before an existing Baseline treatment type.		<br/>
	 * @param adverseEventsInputMessage
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public @WebResult(name="CaaersServiceResponse", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common") CaaersServiceResponse createProvisionalAdverseEvents(@WebParam(name="AdverseEventsInputMessage", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/manageae") AdverseEventsInputMessage adverseEventsInputMessage) ;
	
	/**
	 * Update Adverse Events for a Study Participant Assignment on a given Course/Cycle.
	 * @param adverseEventsInputMessage
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public @WebResult(name="CaaersServiceResponse", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common") CaaersServiceResponse createOrUpdateAdverseEvent(@WebParam(name="AdverseEventsInputMessage", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/manageae") AdverseEventsInputMessage adverseEventsInputMessage) ;
	
	/**
	 * Delete Adverse Events for a Study Participant Assignment on a given Course/Cycle.
	 * @param adverseEventsInputMessage
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public @WebResult(name="CaaersServiceResponse", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common") CaaersServiceResponse deleteAdverseEvent(@WebParam(name="AdverseEventsInputMessage", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/manageae") AdverseEventsInputMessage adverseEventsInputMessage) ;
	
}
