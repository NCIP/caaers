package gov.nih.nci.cabig.caaers.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Webservice Interface for Study Creation and Updation.
 * @author Monish Dombla
 *
 */
@WebService(name="StudyProcessorIntf",targetNamespace="http://webservice.caaers.cabig.nci.nih.gov")
public interface StudyProcessor {
	
	/**
	 * This operation will accept a Study which is a jaxb study and creates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlStudy
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.webservice.StudyServiceResponse createStudy(@WebParam(name="Study") gov.nih.nci.cabig.caaers.webservice.Study xmlStudy);
	
	
	
	/**
	 * This operation will accept a Study which is a jaxb Study and updates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlStudy
	 */
	@WebMethod
	public gov.nih.nci.cabig.caaers.webservice.StudyServiceResponse updateStudy(@WebParam(name="Study") gov.nih.nci.cabig.caaers.webservice.Study xmlStudy);

}
