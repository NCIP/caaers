package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: Biju Joseph
 */
@WebService(name="SafetyReportManagementServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport")
public interface SafetyReportManagementService {

    public CaaersServiceResponse submitSafetyReport(@WebParam(name = "AdverseEventReport",targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") AdverseEventReport xmlAdverseEventReport);
}
