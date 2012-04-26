package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.ws.ASAELService;
import gov.nih.nci.cabig.caaers.api.impl.ASAELServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.asael.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.ASAELService", serviceName="ASAELService", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class ASAELWebService implements ASAELService {

    private ASAELServiceImpl s;

    @WebMethod
    public CaaersServiceResponse createOrUpdateASAEL(Asael asael) {
        return s.createOrUpdateASAEL(asael);
    }

    public ASAELServiceImpl getAsaelServiceImpl() {
        return s;
    }

    public void setAsaelServiceImpl(ASAELServiceImpl asaelServiceImpl) {
        this.s = asaelServiceImpl;
    }
}
