package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.ws.DevicesService;
import gov.nih.nci.cabig.caaers.api.impl.DevicesServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.device.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.DevicesService",
        serviceName="DevicesService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class DevicesWebService implements DevicesService {

    private DevicesServiceImpl s;

    @WebMethod
    public CaaersServiceResponse createOrUpdateDevices(Devices devices) {
        return s.createOrUpdateDevices(devices);
    }

    public DevicesServiceImpl getDevicesServiceImpl() {
        return s;
    }

    public void setDevicesServiceImpl(DevicesServiceImpl s) {
        this.s = s;
    }
}
