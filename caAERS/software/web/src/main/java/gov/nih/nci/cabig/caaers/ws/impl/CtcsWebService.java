package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.CtcService;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.ctc.Ctcs;
import gov.nih.nci.cabig.caaers.ws.CtcsService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author Ion C. Olaru
 *         Date: 4/2/12 -10:03 AM
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.CtcsService",
        serviceName="CtcsService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class CtcsWebService implements CtcsService {

    private CtcService ctcService;

    @WebMethod
    public CaaersServiceResponse createOrUpdateCtcs(Ctcs ctcs) {
        return ctcService.createOrUpdateCtc(ctcs);
    }

	public void setCtcService(CtcService ctcService) {
		this.ctcService = ctcService;
	}
}
