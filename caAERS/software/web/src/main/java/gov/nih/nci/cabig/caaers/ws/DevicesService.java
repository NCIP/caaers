package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.device.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(name = "DevicesServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common")
public interface DevicesService {

    @WebMethod
    public CaaersServiceResponse createOrUpdateDevices(@WebParam(name = "Devices", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common") Devices devices);
}
