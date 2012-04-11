package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.ws.DevicesService;
import gov.nih.nci.cabig.caaers.api.impl.DevicesServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.devices.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.DevicesService", serviceName="DevicesService", targetNamespace="http://webservice.caaers.cabig.nci.nih.gov")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class DevicesWebService implements DevicesService {

    private DevicesServiceImpl s;

    @WebMethod
    public CaaersServiceResponse createOrUpdateDevices(@WebParam(name = "devices", targetNamespace = "http://webservice.caaers.cabig.nci.nih.gov") DevicesType devices) {
        return s.createOrUpdateDevices(devices);
    }

    public DevicesServiceImpl getDevicesServiceImpl() {
        return s;
    }

    public void setDevicesServiceImpl(DevicesServiceImpl s) {
        this.s = s;
    }
}
