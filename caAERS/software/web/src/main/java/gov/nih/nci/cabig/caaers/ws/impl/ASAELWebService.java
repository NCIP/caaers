package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.ws.ASAELService;
import gov.nih.nci.cabig.caaers.api.impl.ASAELServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.asael.ASAELType;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.ASAELService", serviceName="ASAELService", targetNamespace="http://webservice.caaers.cabig.nci.nih.gov")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class ASAELWebService implements ASAELService {

    private ASAELServiceImpl s;

    @WebMethod
    public CaaersServiceResponse createOrUpdateASAEL(@WebParam(name = "asael", targetNamespace = "http://webservice.caaers.cabig.nci.nih.gov") ASAELType asael) {
        return s.createOrUpdateASAEL(asael);
    }

    public ASAELServiceImpl getAsaelServiceImpl() {
        return s;
    }

    public void setAsaelServiceImpl(ASAELServiceImpl asaelServiceImpl) {
        this.s = asaelServiceImpl;
    }
}
