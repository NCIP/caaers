package gov.nih.nci.cabig.caaers.ws;

import icsr.Ichicsr;
import icsr.Ichicsrack;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author: Biju Joseph
 */
@WebService(name="SafetyReportManagementServiceInterface")
public interface SafetyReportManagementService {

    public Ichicsrack submitSafetyReport(@WebParam Ichicsr icsr);
}
