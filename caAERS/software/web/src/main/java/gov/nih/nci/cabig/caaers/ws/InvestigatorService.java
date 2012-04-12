package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="InvestigatorServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/investigator")
public interface InvestigatorService {
	
	@WebMethod
	public CaaersServiceResponse saveInvestigator(@WebParam(name="Staff", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/investigator") gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff) ;
	
}
