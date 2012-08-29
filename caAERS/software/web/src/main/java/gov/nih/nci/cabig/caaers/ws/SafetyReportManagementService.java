package gov.nih.nci.cabig.caaers.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: Biju Joseph
 */
@WebService(name="SafetyReportManagementServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/icsr")
public interface SafetyReportManagementService {

    public Object submitSafetyReport(@WebParam Object icsr);
}
