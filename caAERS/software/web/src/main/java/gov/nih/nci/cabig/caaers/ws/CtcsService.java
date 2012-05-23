package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.Ctcs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(name = "CtcsServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common")
public interface CtcsService {

    @WebMethod
    public CaaersServiceResponse createOrUpdateCtcs(@WebParam(name = "Ctcs", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common") Ctcs ctcs);
}
