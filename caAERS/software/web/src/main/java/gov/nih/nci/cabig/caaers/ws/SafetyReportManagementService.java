package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.icsr.Ichicsr;
import gov.nih.nci.cabig.caaers.integration.schema.icsr.Ichicsrack;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: Biju Joseph
 */
@WebService(name="SafetyReportManagementServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/icsr")
public interface SafetyReportManagementService {

    public Ichicsrack submitSafetyReport(@WebParam Ichicsr icsr);
}
