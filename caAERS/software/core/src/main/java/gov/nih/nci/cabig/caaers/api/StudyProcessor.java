package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.webservice.Studies;


/**
 * Webservices Interface to Manage {@link Study}.
 * @author Monish Dombla
 *
 */

public interface StudyProcessor {
	
	/**
	 * This operation will accept a Study which is a jaxb study and creates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlStudies
	 */
	public gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse createStudy(Studies xmlStudies);
	
	
	
	/**
	 * This operation will accept a Study which is a jaxb Study and updates it.
	 * @TODO
	 * This operation should allow for response.
	 * Need to modify schema for Response
	 * @param xmlStudies
	 */
	public gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse updateStudy(Studies xmlStudies);

}
