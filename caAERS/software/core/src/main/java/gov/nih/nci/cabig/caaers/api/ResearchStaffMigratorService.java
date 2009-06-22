package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="ResearchStaffMigratorServiceIntf",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/researchstaff")
public interface ResearchStaffMigratorService {

	@WebMethod
	public CaaersServiceResponse saveResearchStaff(@WebParam(name="Staff") gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff) ;
	
}
