package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.asael.ASAELType;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(name = "ASAELServiceInterface", targetNamespace = "http://webservice.caaers.cabig.nci.nih.gov/asael")
public interface ASAELService {

    @WebMethod
    public CaaersServiceResponse createOrUpdateASAEL(@WebParam(name = "asael", targetNamespace = "http://webservice.caaers.cabig.nci.nih.gov/asael") ASAELType inputMessage);
}
