package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.devices.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(name = "DevicesServiceInterface", targetNamespace = "http://webservice.caaers.cabig.nci.nih.gov/devices")
public interface DevicesService {

    @WebMethod
    public CaaersServiceResponse createOrUpdateDevices(@WebParam(name = "devices", targetNamespace = "http://webservice.caaers.cabig.nci.nih.gov/devices") DevicesType inputMessage);
}
