package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="InvestigatorMigratorServiceIntf",targetNamespace="http://investigator.schema.integration.caaers.cabig.nci.nih.gov")

public interface InvestigatorMigratorService {
	
	@WebMethod
	public CaaersServiceResponse saveInvestigator(@WebParam(name="Staff") gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff) ;
	
}
