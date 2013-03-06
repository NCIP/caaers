/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.study.Studies;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Webservices Interface to Manage {@link Study}.
 * @author Monish Dombla
 *
 */
@WebService(name="StudyProcessorInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/study")
public interface StudyProcessor {
	
	/**
	 * This operation will accept a Study which is a jaxb study and creates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlStudies
	 */
	@WebMethod
	public CaaersServiceResponse createStudy(@WebParam(name="Studies", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/study") Studies xmlStudies);

	/**
	 * This operation will accept a Study which is a jaxb Study and updates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlStudies
	 */
	@WebMethod
	public CaaersServiceResponse updateStudy(@WebParam(name="Studies", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/study") Studies xmlStudies);

}
