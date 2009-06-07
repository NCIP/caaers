package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.webservice.adverseevent.CaaersServiceResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * API to import adverse events to caAERS System. 
 * Adverse Events can be created in caAERS System via web service.
 * 
 * @author sakkala
 *
 */
@WebService(name="AdverseEventManagementServiceIntf",targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/adverseevent")
public interface AdverseEventManagementService {
	public static final String CREATE = "create";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
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
	 * @param importAdverseEvents
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public CaaersServiceResponse createAdverseEvent(@WebParam(name="AdverseEventsInputMessage") gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage adverseEventsInputMessage) ;
	
	/**
	 * Update Adverse Events for a Study Participant Assignment on a given Course/Cycle.
	 * @param importAdverseEvents
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public CaaersServiceResponse createOrUpdateAdverseEvent(@WebParam(name="AdverseEventsInputMessage") gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage adverseEventsInputMessage) ;
	
	/**
	 * Delete Adverse Events for a Study Participant Assignment on a given Course/Cycle.
	 * @param importAdverseEvents
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public CaaersServiceResponse deleteAdverseEvent(@WebParam(name="AdverseEventsInputMessage") gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage adverseEventsInputMessage) ;
	
}
