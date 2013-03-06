/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="InvestigatorMigratorServiceIntf",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/investigator")

public interface InvestigatorMigratorService {
	
	@WebMethod
	public CaaersServiceResponse saveInvestigator(@WebParam(name="Staff") gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff) ;
	
}
