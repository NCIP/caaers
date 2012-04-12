package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.asael.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:05 AM
 */
@WebService(name = "ASAELServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common")
public interface ASAELService {

    @WebMethod
    public CaaersServiceResponse createOrUpdateASAEL(@WebParam(name = "asael", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common") ASAELType inputMessage);
}
