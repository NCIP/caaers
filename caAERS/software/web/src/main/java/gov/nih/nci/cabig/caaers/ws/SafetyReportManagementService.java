/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: Biju Joseph
 */
@WebService(name="SafetyReportManagementServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/icsr")
public interface SafetyReportManagementService {

    public Object submitSafetyReport(@WebParam(name = "report") Object icsr);
}
