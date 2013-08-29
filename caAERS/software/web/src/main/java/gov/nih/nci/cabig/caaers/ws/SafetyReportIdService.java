/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.aereportid.ReportIdCriteria;
import gov.nih.nci.cabig.caaers.integration.schema.aereportid.SafetyReportIdentifer;
import gov.nih.nci.cabig.caaers.ws.faults.CaaersFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: vinodh
 */
@WebService(name="SafetyReportIdServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereportid")
public interface SafetyReportIdService {

    /**
     * Will generate a new SafetyReportId
     * @param ReportIdCriteria - Criteria for generating the safety report id with
     * @return SafetyReportIdentifer instance
     */
	@WebMethod
    public SafetyReportIdentifer generateSafetyReportId(@WebParam(name = "ReportIdCriteria",targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereportid") ReportIdCriteria reportIdCriteria) throws CaaersFault;


}
